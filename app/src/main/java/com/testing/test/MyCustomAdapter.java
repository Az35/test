package com.testing.test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Az on 10/29/2014.
 */
public class MyCustomAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	public ArrayList<Group> groups;
	public MyDatabase db;

	public MyCustomAdapter (Activity activity, ArrayList<Group> groups)
	{
		this.activity = activity;
		this.groups = groups;
	}
	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		ArrayList<Child> chList = groups.get(groupPosition).getItems();
		return chList.size();
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		return groups.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		ArrayList<Child> chList = groups.get(groupPosition).getItems();
		return chList.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
	{
		Group group = (Group) getGroup(groupPosition);

		if(convertView == null)
		{
			LayoutInflater inf = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.group_layout,null);
		}

		convertView.setTag(getGroup(groupPosition).toString());
		TextView tv = (TextView) convertView.findViewById(R.id.group_name);
		tv.setText(group.getName());

		return convertView;
	}

	static class ViewHolder
	{
		protected TextView value1;
		protected CheckBox cb;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		db = new MyDatabase(activity);
		ViewHolder viewHolder = null;

		final Child child = (Child) getChild(groupPosition,childPosition);
		if(convertView == null)
		{
			LayoutInflater inflaterZ = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
			convertView = inflaterZ.inflate(R.layout.child_layout,null);

			viewHolder = new ViewHolder();
			viewHolder.value1 = (TextView) convertView.findViewById(R.id.value1);
			viewHolder.cb = (CheckBox) convertView.findViewById(R.id.checkBox);

			viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					int getPosition = (Integer) compoundButton.getTag();
					groups.get(groupPosition).getItems().get(getPosition).setState(compoundButton.isChecked());
				}
			});

			convertView.setTag(viewHolder);
			convertView.setTag(R.id.value1, viewHolder.value1);
			convertView.setTag(R.id.checkBox, viewHolder.cb);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();

		}

		viewHolder.cb.setTag(childPosition);
		viewHolder.value1.setText(String.valueOf(child.getValue()));
		viewHolder.cb.setChecked(child.getValue()==0);

		viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				if (isChecked)
				{

					String id = String.valueOf(6*groupPosition+childPosition+1);
					db.updateIsChecked(id);
				}
				else
				{
					String id = String.valueOf(6*groupPosition+childPosition+1);
					db.updateIsNotChecked(id);
				}
				//notifyDataSetChanged();
			}
		});

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int i, int i2) {
		return true;
	}


}
