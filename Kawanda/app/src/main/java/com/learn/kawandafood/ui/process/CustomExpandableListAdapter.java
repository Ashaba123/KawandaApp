package com.learn.kawandafood.ui.process;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Process;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Process> processTitle;
    private HashMap<String, List<String>> subProcessDetail;

    public CustomExpandableListAdapter(Context context, List<Process> processTitle,
                                       HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.processTitle = processTitle;
        this.subProcessDetail = expandableListDetail;
    }

    public CustomExpandableListAdapter(BrowseProcessActivity context,
                                       List<Process> processTitle,
                                       HashMap<String, List<String>> subProcessDetail) {
        this.context = context;
        this.processTitle = processTitle;
        this.subProcessDetail = subProcessDetail;
    }

    public void setData(List<Process> processes) {
        this.processTitle = processes;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.subProcessDetail.get(this.processTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.subProcessDetail.get(this.processTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.processTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.processTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {

       // String listTitle = (String) getGroup(listPosition);
        Process process =processTitle.get(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);

        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(process.name);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
