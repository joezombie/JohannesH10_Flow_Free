package is.ru.ANDR;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Johannes Gunnar Heidarsson on 23.9.2014.
 */
public class TimeTrialChallengeActivity extends ListActivity {
    ArrayList<String> challenges;
    HashMap<String, PuzzleReference> map;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.challenges = new ArrayList<String>();
        this.map = new HashMap<String, PuzzleReference>();
        Global global = Global.getSingleInstance();

        for(Pack pack: global.getPacks()){
            for(Challenge challenge: pack.getChallenges()){
                challenges.add(challenge.name);
                map.put(challenge.name, new PuzzleReference(pack.id, challenge.id, 1));
            }
        }

        ArrayAdapter ch = new ArrayAdapter(this, R.layout.time_trial_row, challenges);
        setListAdapter(ch);


    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id){
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, PlayActivity.class);
        PuzzleReference puzzleReference = map.get(challenges.get(position));
        intent.putExtra("pack_id", puzzleReference.getPackId());
        intent.putExtra("challenge_id", puzzleReference.getChallengeId());
        intent.putExtra("puzzle_id", puzzleReference.getPuzzleId());
        intent.putExtra("time_trial", true);
        intent.putExtra("time_trial_seconds", extras.getInt("time_trial_seconds", 30));
        startActivity(intent);
    }
}