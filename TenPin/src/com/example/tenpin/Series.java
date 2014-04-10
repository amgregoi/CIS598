package com.example.tenpin;

public class Series extends Record{

	private int series_total;
	private String date;		//current title will change later
	private Game games[];
	
	public Series(String d)
	{
		super(d);	
		series_total = 10;
	}
	
	
	@Override
	public String toString()
	{
		return date + "\t" + Integer.toString(series_total);
	}
}
