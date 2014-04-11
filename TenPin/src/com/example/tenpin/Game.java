package com.example.tenpin;

public class Game extends Record{

	private int score;
	private String date;		//current title will change later
	private int score_sheet[];
	
	public Game(String d)
	{
		super(d);
		score = 0;
		score_sheet = new int[21];
	}
	
	
	@Override
	public String toString()
	{
		return date + "\t" + Integer.toString(score);
	}
	
	/*
	 * returns score of the game
	 */
	public int getScore()
	{
		return score;
	}
	
	/*
	 * function used to set the final score of the game.
	 */
	public void setFinalScore(int gameScore)
	{
		score = gameScore;
	}
	
	/*
	 * num is the number of pins you knocked down for the last ball thrown.
	 * frame represents which frame you are in
	 * ballThrown represents if this is the first or second ball of the frame.
	 */
	public void setScore(int num, int frame, int ballThrown)
	{
		score_sheet[frame+ballThrown] = num;
	}
}
