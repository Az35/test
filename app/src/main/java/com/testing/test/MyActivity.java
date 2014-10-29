package com.testing.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MyActivity extends Activity {

	public MyDatabase db;
	public MyCustomAdapter ExpAdapter;
	public ArrayList<Group> ExpListItems;
	public ExpandableListView ExpandList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);

		ExpandList = (ExpandableListView) findViewById(R.id.exp_list);
		db = new MyDatabase(this);

		ExpListItems = db.getValue();
		ExpAdapter = new MyCustomAdapter(MyActivity.this, ExpListItems);
		ExpandList.setAdapter(ExpAdapter);
	}

}