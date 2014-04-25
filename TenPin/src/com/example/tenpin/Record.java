package com.example.tenpin;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Record implements Parcelable{
	private String name;
	private String type;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Record(String name, String type) {
		super();
		this.name = name;
		this.setType(type);
	}
	
	public static final Parcelable.Creator<Record> CREATOR = new Parcelable.Creator<Record>() {
		public Record createFromParcel(Parcel in) {
			String recordType = in.readString();
			Record record = null;
			
			if(recordType.equals("Dog"))
			record = (Record)new Game(in);
			
			if(recordType.equals("Cat"))
			record = (Record)new Series(in);
			
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
		this.name = in.readString();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}



/*
abstract public class Record implements Parcelable {
	
	String date;
	private String type;
	
	public Record(String d, String type)
	{
		date = d;
		this.type = type;
	}
	
	@Override
	public String toString(){
		return date;
	}


    protected Record(Parcel in) {
        date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Record> CREATOR = new Parcelable.Creator<Record>() {
        @Override
        public Record createFromParcel(Parcel in) {
            String recordType = in.readString();
            Record record = null;
            if(recordType.equals("Game"))
            	record = (Record)new Game(in);
            if(recordType.equals("Series"))
            	record = (Record)new Series(in);
            return record;
            
        	
        	//return new Record(in);
        }

        @Override
        public Record[] newArray(int size) {
            return new Record[size];
        }
    };

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(date);
		
	}
}
*/