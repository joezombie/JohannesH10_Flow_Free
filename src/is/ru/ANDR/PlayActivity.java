package is.ru.ANDR;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class PlayActivity extends Activity {
    private PuzzleReference currentPuzzle;
    private BoardView boardView;
    private Global global;
    private Timer timer = null;
    private int seconds = 0;
    private TextView timerText;
    private TextView titleText;
    private TextView oldTimeText;
    private static SoundPool soundPool;
    private static int SoundBump;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        if(soundPool == null){
            initSounds(this);
        }

        this.global = Global.getSingleInstance();
        this.boardView = (BoardView) findViewById(R.id.board_view);
        this.timerText = (TextView) findViewById(R.id.puzzle_timer);
        this.titleText = (TextView) findViewById(R.id.puzzle_title);
        this.oldTimeText = (TextView) findViewById(R.id.puzzle_old_time);

        Bundle extras = getIntent().getExtras();

        this.currentPuzzle = new PuzzleReference(extras.getInt("pack_id"),
                                                 extras.getInt("challenge_id"),
                                                 extras.getInt("puzzle_id"));

        setPuzzle(currentPuzzle);
    }

    public void puzzleDone(){
        stopPuzzleTimer();
        showDialog();

        PuzzleAdapter puzzleAdapter = new PuzzleAdapter(this);
        Puzzle puzzle = global.getPuzzle(currentPuzzle);
        if(seconds < puzzle.getBestTime() || puzzle.getBestTime() < 1){
            puzzle.setBestTime(seconds);
            if(puzzleAdapter.insertPuzzle(currentPuzzle, seconds) < 0){
                puzzleAdapter.updatePuzzle(currentPuzzle, seconds);
            }
        }
    }

    public void flowConnected(){
        float volume = 1;
        soundPool.play(SoundBump, volume, volume, 1, 0, 1);
    }

    private static void initSounds(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        SoundBump = soundPool.load(context, R.raw.bump, 1);
    }

    private PuzzleReference getNextPuzzle(){
        return new PuzzleReference(currentPuzzle.getPackId(), currentPuzzle.getChallengeId(), currentPuzzle.getPuzzleId() + 1);
    }

    private void saveLastPuzzle(PuzzleReference puzzleReference){
        getSharedPreferences("last_puzzle", MODE_PRIVATE).edit()
                .putInt("pack_id", puzzleReference.getPackId())
                .putInt("challenge_id", puzzleReference.getChallengeId())
                .putInt("puzzle_id", puzzleReference.getPuzzleId())
                .commit();
    }

    private void setPuzzle(PuzzleReference puzzleReference){
        currentPuzzle = puzzleReference;
        Puzzle puzzle = global.getPuzzle(puzzleReference);
        if(puzzle != null){
            boardView.setPuzzle(puzzle);
            boardView.invalidate();
            startPuzzleTimer();
            updatePuzzleTimer();
            titleText.setText(global.getPuzzleDisplayName(currentPuzzle));
            oldTimeText.setText(puzzle.bestTimeToString());
            saveLastPuzzle(puzzleReference);
        }else {
            quit();
        }
    }

    private void startPuzzleTimer(){
        timer = new Timer();
        seconds = 0;
        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seconds += 1;
                        updatePuzzleTimer();
                    }
                });
            }
        }, 1000, 1000);

    }

    private void stopPuzzleTimer(){
        if(timer != null) {
            timer.cancel();
        }
    }

    private void updatePuzzleTimer(){
        this.timerText.setText(secondsToString());
    }

    private void showDialog(){
        String message = getString(R.string.puzzle_dialog_completed_in) + " " + secondsToString() + "\n"
                       + getString(R.string.puzzle_dialog_text);
        new AlertDialog.Builder(this)
            .setTitle(R.string.puzzle_dialog_title)
            .setMessage(message)
            .setPositiveButton(R.string.puzzle_continue, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setPuzzle(getNextPuzzle());
                }
            })
            .setNegativeButton(R.string.puzzle_quit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    quit();
                }
            })
            .show();
    }

    private void quit(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private String secondsToString(){
        return intToSecOrMin(seconds/60) + ":" + intToSecOrMin(seconds%60);
    }

    private String intToSecOrMin(int t){
        return t < 10 ? "0" + Integer.toString(t) : Integer.toString(t);
    }
}