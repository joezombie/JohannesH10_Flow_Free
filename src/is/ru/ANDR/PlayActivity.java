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

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class PlayActivity extends Activity {
    private PuzzleReference currentPuzzle;
    private BoardView boardView;
    private Global global;
    private PuzzleTimer puzzleTimer;
    private TextView timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        this.global = Global.getSingleInstance();
        this.boardView = (BoardView) findViewById(R.id.board_view);
        this.puzzleTimer = new PuzzleTimer();
        this.timer = (TextView) findViewById(R.id.puzzle_timer);

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
            puzzleTimer.start(new PuzzleTimerTask(){
                @Override
                public void run(){
                    updatePuzzleTimer();
                }
            });
            boardView.setPuzzle(puzzle);
            saveLastPuzzle(puzzleReference);
            showDialog();
        }
    }

    private void updatePuzzleTimer(){
        this.timer.setText(puzzleTimer.toString());
    }

    private void showDialog(){
        //NextPuzzleDialogFragment dialog = new NextPuzzleDialogFragment();
        //dialog.show(dialog.getFragmentManager(), "Next Puzzle");
    }
}