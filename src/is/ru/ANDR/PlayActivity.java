package is.ru.ANDR;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class PlayActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        Global global = Global.getSingleInstance();

        int packId = getIntent().getExtras().getInt("pack_id");
        int challengeId = getIntent().getExtras().getInt("challenge_id");
        int puzzleId = getIntent().getExtras().getInt("puzzle_id");

        BoardView boardView = (BoardView) findViewById(R.id.board_view);
        boardView.setPuzzle(global.getPuzzle(packId, challengeId, puzzleId));

        getSharedPreferences("last_puzzle", MODE_PRIVATE).edit()
                            .putInt("pack_id", packId)
                            .putInt("challenge_id", challengeId)
                            .putInt("puzzle_id", puzzleId)
                            .commit();
    }
}