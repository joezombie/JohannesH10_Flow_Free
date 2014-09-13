package is.ru.ANDR;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

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
                startActivity(intent);
                return true;
            }
        });
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                /*
                for(int i = 0; i < groupList.size() - 1; i++){
                    if(groupPosition != i) {
                        parent.collapseGroup(i);
                    }
                }*/
                return false;
            }
        });
    }

    private void getListData(){
        this.groupList = new ArrayList<String>();
        this.childList = new HashMap<String, List<String>>();

        this.groupList.add("5x5");
        this.groupList.add("6x6");
        this.groupList.add("7x7");

        List<String> five = new ArrayList<String>();
        five.add("Level 1");
        five.add("Level 2");
        five.add("Level 3");
        five.add("Level 4");

        List<String> six = new ArrayList<String>();
        six.add("Level 1");
        six.add("Level 2");
        six.add("Level 3");
        six.add("Level 4");

        List<String> seven = new ArrayList<String>();
        seven.add("Level 1");
        seven.add("Level 2");
        seven.add("Level 3");
        seven.add("Level 4");

        childList.put(groupList.get(0), five);
        childList.put(groupList.get(1), six);
        childList.put(groupList.get(2), seven);

    }
}