package com.example.tenpin;
import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

	private String name;
	private String date;
	
	public Player(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return this.name;	
	}

    protected Player(Parcel in) {
        name = in.readString();
        date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}