package com.example.tenpin;

import android.os.Parcel;
import android.os.Parcelable;

public class Game extends Record{

	float[][] pinLayout;
	String[][] scoreSheet = new String[10][3];

	/*
	 * Game Constructor
	 */
	public Game(String name, String owner){
		super(name, owner, "Game");
	}
	
	public int describeContents() {
		return this.hashCode();
	}
	
	/*
	 * setter for the scoreSheet
	 */
	public void setScoreSheet(String[][] score)
	{
		scoreSheet = score;
	}
	
	/*
	 * getter for scoreSheet
	 */
	public String[][] getScoreSheet()
	{
		return scoreSheet;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getType());
		super.writeToParcel(dest, flags);
	}
	
	public Game(Parcel source) {
		super(source);	
	}
	
	/*
	 * getter pinLayout
	 */
	public float[][] getPinLayout(){
		return pinLayout;
	}
	
	/*
	 * setter pinLayout
	 */
	public void setPinLayout(float[][] layout){
		this.pinLayout = layout.clone();
	}
	
	public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
		public Game createFromParcel(Parcel in) {
			return new Game(in);
		}
		
		public Game[] newArray(int size) {
			return new Game[size];
		}
	};
	
	/*
	 * (non-Javadoc)
	 * @see com.example.tenpin.Record#toString()
	 * Override tostring
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}
	
	


}//end class