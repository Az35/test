package com.testing.test;

import java.util.ArrayList;

/**
 * Created by Az on 10/29/2014.
 */
public class Group {

	private String Name;
	private boolean status;

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}

	private ArrayList<Child> Items;

	public ArrayList<Child> getItems() {
		return Items;
	}
	public void setItems(ArrayList<Child> Items) {
		this.Items = Items;
	}

	public boolean getState()
	{
		return status;
	}
	public void setState(boolean status)
	{
		this.status = status;
	}
}
