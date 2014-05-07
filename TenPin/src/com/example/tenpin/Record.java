package com.example.tenpin;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Record implements Parcelable{
	private String name;
	private String type;
	private String Owner;
	private int id=-1;
	
	public String getName() {
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getOwner()
	{
		return Owner;
	}
	
	public void setId(int val){
		id = val;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Record(String name, String owner, String type) {
		super();
		this.name = name;
		this.type = type;
		this.Owner = owner;
	}
	
	public static final Parcelable.Creator<Record> CREATOR = new Parcelable.Creator<Record>() {
		public Record createFromParcel(Parcel in) {
			String recordType = in.readString();
			Record record = null;
			
			if(recordType.equals("Game")){
				record = (Record)new Game(in);
			}
			if(recordType.equals("Series")){
				record = (Record)new Series(in);
			}
			return record;
		}
		
		public Record[] newArray(int size) {
			return new Record[size];
		}
	};
	
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(getName());
	}
	
	protected Record(Parcel in) {
		super();
		this.type = in.readString();
		this.name = in.readString();
		this.Owner = in.readString();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}