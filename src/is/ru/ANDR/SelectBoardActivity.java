package is.ru.ANDR;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Johannes Gunnar Heidarsson on 11.9.2014.
 */

public class SelectBoardActivity extends Activity {
    private ExpandableListAdapter listAdapter;
    private ExpandableListView listView;
    List<String> groupList;
    HashMap<String, List<String>> childList;
    HashMap<String, PuzzleReference> puzzleMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_board);

        listView = (ExpandableListView) findViewById(R.id.select_board_list);

        getListData();

        listAdapter = new BoardExpandableListAdapter(this, groupList, childList);

        listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(listView.getContext(), PlayActivity.class);
                String puzzleName = (String) listAdapter.getChild(groupPosition, childPosition);
                PuzzleReference puzzle = puzzleMap.get(puzzleName);
                intent.putExtra("pack_id", puzzle.getPackId());
                intent.putExtra("challenge_id", puzzle.getChallengeId());
                intent.putExtra("puzzle_id", puzzle.getPuzzleId());
                startActivity(intent);
                return true;
            }
        });
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
    }

    private void getListData(){
        Global global = Global.getSingleInstance();
        this.groupList = new ArrayList<String>();
        this.childList = new HashMap<String, List<String>>();
        this.puzzleMap = new HashMap<String, PuzzleReference>();

        for(Pack pack : global.getPacks()){
            this.groupList.add(pack.name);
            List<String> puzzles = new ArrayList<String>();
            for(Challenge challenge : pack.getChallenges()){
                for(Puzzle puzzle : challenge.getPuzzles()){
                    String puzzleName = challenge.name + " Level " + Integer.toString(puzzle.getId());
                    puzzles.add(puzzleName);
                    puzzleMap.put(puzzleName, new PuzzleReference(pack.id, challenge.id, puzzle.id));
                }
            }
            childList.put(pack.name, puzzles);
        }
    }
}