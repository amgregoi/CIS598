package com.example.tenpin;

import android.os.Parcel;
import android.os.Parcelable;

public class Series extends Record{
	int seriesScore;
	int[] gameIds = new int[3];
	
	public Series(String name, String owner){
		super(name, owner, "Series");
	}
	
	public int[] getGameIds()
	{
		return gameIds;
	}
	
	public void setGameIds(int[] ids)
	{
		gameIds = ids;
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
		return super.toString();
	}
}
