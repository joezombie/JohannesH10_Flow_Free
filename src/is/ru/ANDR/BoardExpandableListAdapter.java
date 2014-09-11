package is.ru.ANDR;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johannes Gunnar Heidarsson on 11.9.2014.
 */
public class BoardExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupList;
    private HashMap<String, List<String>> childList;

    public BoardExpandableListAdapter(Context context, List<String> groupList, HashMap<String, List<String>> childList){
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        return this.childList.get(this.groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        final String childText = (String) getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.board_child_item, null);
        }
        //ImageView imageView = (ImageView) convertView.findViewById(R.id.board_finished);
        //imageView.setImageDrawable();

        TextView childItemText = (TextView) convertView.findViewById(R.id.board_item);
        childItemText.setText(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return this.childList.get(this.groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return this.groupList.get(groupPosition);
    }

    @Override
    public int getGroupCount(){
        return this.groupList.size();
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        String groupTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.board_group_item, null);
        }

        TextView groupItemText = (TextView) convertView.findViewById(R.id.board_item);
        groupItemText.setTypeface(null, Typeface.BOLD);
        groupItemText.setText(groupTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }

}
