package com.example.tenpin;

import android.os.Parcel;
import android.os.Parcelable;

public class Game extends Record{

	char[][] pinLayout;
	String[][] scoreSheet = new String[10][3];

	public Game(String title, String owner){
		super(title, owner, "Game");
	}
	
	public int describeContents() {
		return this.hashCode();
	}
	
	public void setScoreSheet(String[][] score)
	{
		scoreSheet = score;
	}
	
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
	
	public char[][] getPinLayout(){
		return pinLayout;
	}
	
	public void setPinLayout(char[][] layout){
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
	
	@Override
	public String toString()
	{
		return super.toString();
	}
	


}//end class


























/*
public class Game extends Record{

	private int score;
	private String date;		//current title will change later
	private int score_sheet[];
	char[][] pinLayout;
	
	
	public Game(String d)
	{
		super(d, "Game");
		date = d;
		score = 0;
		score_sheet = new int[21];
		pinLayout = new char[10][10];
	}
	
	
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
	}

	public Game(Parcel source) {
		super(source);	
	}

	public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
		public Game createFromParcel(Parcel in) {
			return new Game(in);
		}
	
		public Game[] newArray(int size) {
			return new Game[size];
		}
	};	
	
	
	
	
	
	
	@Override
	public String toString()
	{
		return date;
	}
	
	public char[][] pinSetup()
	{
		return pinLayout;
	}
	
	 //
	 // returns score of the game
	 //
	public int getScore()
	{
		return score;
	}
	
	//
	 // function used to set the final score of the game.
	 //
	public void setFinalScore(int gameScore)
	{
		score = gameScore;
	}
	
	//
	 // num is the number of pins you knocked down for the last ball thrown.
	 // frame represents which frame you are in
	 // ballThrown represents if this is the first or second ball of the frame.
	 //
	public void setScore(int num, int frame, int ballThrown)
	{
		score_sheet[frame+ballThrown] = num;
	}
	
}
*/
