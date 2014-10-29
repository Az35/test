package com.testing.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Az on 10/29/2014.
 */
public class MyDatabase extends SQLiteAssetHelper {

	private static final String database_name = "test2.db";
	private static final int database_version = 1;

	public MyDatabase(Context context) {
		super(context, database_name, null, database_version);
	}

	public ArrayList<Group> getValue() {

		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Group> list = new ArrayList<Group>();
		ArrayList<Child> ch_list;

		String myQuery = "SELECT * FROM testing1";
		Cursor cursor = db.rawQuery(myQuery, null);

		if (cursor.moveToFirst()) {
			Group gru = new Group();
			gru.setName("group1");
			ch_list = new ArrayList<Child>();
			do {
				Child ch = new Child();
				ch.setValue(cursor.getInt(cursor.getColumnIndex("values1")));
				ch_list.add(ch);
			} while (cursor.moveToNext());
			gru.setItems(ch_list);
			list.add(gru);
		}

		cursor.close();
		db.close();
		return list;
	}


	public void updateIsChecked(String id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put("values1", 0);
		db.update("testing1",newValues,"id =? ",new String[] {id});

		db.close();
	}

	public void updateIsNotChecked(String id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put("values1", 50);
		db.update("testing1",newValues,"id =? ", new String[]{id});
		db.close();
	}
}
