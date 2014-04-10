package com.example.tenpin;

public class Game extends Record{

	private int score;
	private String date;		//current title will change later
	private int score_sheet[];
	
	public Game(String d)
	{
		super(d);
	}
	
	
	@Override
	public String toString()
	{
		return date + "\t" + Integer.toString(score);
	}
}
