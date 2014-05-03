package com.example.tenpin;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

	private String title;
	private List<Record> record;
	
	public Player(String n)
	{
		title = n;
		record = new ArrayList<Record>();
	}
	
	public String getName()
	{
		return title;
	}
	
	public void setPlayerRecordList(List<Record> list)
	{
		record = list;
	}
	
	public List<Record> getPlayerRecordList()
	{
		return record;
	}
	
	@Override
	public String toString()
	{
		return this.title;	
	}

	public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
		public Player createFromParcel(Parcel in) {
			return new Player(in.readString());
		}
		
		public Player[] newArray(int size) {
			return new Player[size];
		}
	};
	
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(getName());
	}
	
	protected Player(Parcel in) {
		super();
		this.title = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}