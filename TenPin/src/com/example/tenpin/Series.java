package com.example.tenpin;

import android.os.Parcel;
import android.os.Parcelable;

public class Series extends Record{
	String title;
	public Series(String name){
		super(name, "Series");
		title = name;
	}
	
	public int describeContents() {
		return this.hashCode();
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getType());
		super.writeToParcel(dest, flags);
	}
	
	public Series(Parcel source) {
		super(source);	
	}
	
	public static final Parcelable.Creator<Series> CREATOR = new Parcelable.Creator<Series>() {
		public Series createFromParcel(Parcel in) {
			return new Series(in);
		}
		
		public Series[] newArray(int size) {
			return new Series[size];
		}
	};
	
	@Override
	public String toString()
	{
		return title;
	}

}















/*
public class Series extends Record{

	private int series_total;
	private String date;		//current title will change later
	private Game games[];
	
	public Series(String d)
	{
		super(d, "Series");
		date = d;
		series_total = 10;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
	}

	public Series(Parcel source) {
		super(source);	
	}

	public static final Parcelable.Creator<Series> CREATOR = new Parcelable.Creator<Series>() {
		public Series createFromParcel(Parcel in) {
			return new Series(in);
		}
	
		public Series[] newArray(int size) {
			return new Series[size];
		}
	};	
	
	
	@Override
	public String toString()
	{
		return date;
	}
}*/
