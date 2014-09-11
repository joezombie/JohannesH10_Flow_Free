package is.ru.ANDR;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Johannes Gunnar Heidarsson on 11.9.2014.
 */
public class SelectBoardActivity extends Activity {
    private ListView mBoardList;
    private ArrayAdapter<String> mBoardListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_board);

        mBoardList = (ListView) findViewById(R.id.select_board_list);

        String[] boards = new String[] {"Level 1", "Level2 ", "Level 3"};

        ArrayList<String> boardList = new ArrayList<String>();
        boardList.addAll(Arrays.asList(boards));

        mBoardListAdapter = new ArrayAdapter<String>(this, R.layout.board_row, boardList);

        mBoardList.setAdapter(mBoardListAdapter);

    }
}