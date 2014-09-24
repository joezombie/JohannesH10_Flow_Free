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

/**
 * Created by Johannes Gunnar Heidarsson on 23.9.2014.
 */
public class TimeTrialActivity extends ListActivity {
    ArrayList<Seconds> times;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.times = new ArrayList<Seconds>();

        times.add(new Seconds(30));
        times.add(new Seconds(90));
        times.add(new Seconds(180));

        ArrayAdapter test = new ArrayAdapter(this, R.layout.time_trial_row, times);


        setListAdapter(test);

    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id){
        Intent intent = new Intent(this, TimeTrialChallengeActivity.class);
        intent.putExtra("time_trial_seconds", times.get(position).seconds);
        startActivity(intent);
    }


}