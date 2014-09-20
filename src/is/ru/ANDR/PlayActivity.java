package is.ru.ANDR;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class PlayActivity extends Activity {
    private PuzzleReference currentPuzzle;
    private BoardView boardView;
    private Global global;
    private Timer timer;
    private int seconds = 0;
    private TextView timerText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        this.global = Global.getSingleInstance();
        this.boardView = (BoardView) findViewById(R.id.board_view);

        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds += 1;
                updatePuzzleTimer();
            }
        }, 1000, 1000);

        this.timerText = (TextView) findViewById(R.id.puzzle_timer);

        Bundle extras = getIntent().getExtras();

        this.currentPuzzle = new PuzzleReference(extras.getInt("pack_id"),
                                                 extras.getInt("challenge_id"),
                                                 extras.getInt("puzzle_id"));


        boardView.setPuzzle(global.getPuzzle(currentPuzzle));
    }

    public void getNextPuzzle(){
        currentPuzzle = new PuzzleReference(currentPuzzle.getPackId(), currentPuzzle.getChallengeId(), currentPuzzle.getPuzzleId() + 1);
        setPuzzle(currentPuzzle);
    }

    private void saveLastPuzzle(PuzzleReference puzzleReference){
        getSharedPreferences("last_puzzle", MODE_PRIVATE).edit()
                .putInt("pack_id", puzzleReference.getPackId())
                .putInt("challenge_id", puzzleReference.getChallengeId())
                .putInt("puzzle_id", puzzleReference.getPuzzleId())
                .commit();
    }

    private void setPuzzle(PuzzleReference puzzleReference){
        Puzzle puzzle = global.getPuzzle(puzzleReference);
        if(puzzle != null){
            boardView.setPuzzle(puzzle);
            saveLastPuzzle(puzzleReference);
            showDialog();
        }
    }

    private void updatePuzzleTimer(){
        this.timerText.setText("");
    }

    private void showDialog(){
        //NextPuzzleDialogFragment dialog = new NextPuzzleDialogFragment();
        //dialog.show(dialog.getFragmentManager(), "Next Puzzle");
    }
}