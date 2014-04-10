package com.example.tenpin;

import android.os.Parcel;
import android.os.Parcelable;

public class Record implements Parcelable {
	
	String date;
	
	public Record(String d)
	{
		date = d;
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
            return new Record(in);
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
