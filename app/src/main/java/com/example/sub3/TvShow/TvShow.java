package com.example.sub3.TvShow;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShow implements Parcelable{


    private String overview;

    private String name;

    private String posterPath;

    private String firstAirDate;

    private int id;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    protected TvShow(Parcel in) {
        this.overview = in.readString();
        this.name = in.readString();
        this.posterPath = in.readString();
        this.firstAirDate = in.readString();
        this.id = in.readInt();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.overview);
        dest.writeString(this.name);
        dest.writeString(this.posterPath);
        dest.writeString(this.firstAirDate);
        dest.writeInt(this.id);
    }

    TvShow(JSONObject object){
        try {
            int id = object.getInt("id");
            String name =  object.getString("name");
            String poster_path = object.getString("poster_path");
            String overview = object.getString("overview");
            String first_air_date = object.getString("first_air_date");


            this.id = id;
            this.name = name;
            this.posterPath = poster_path;
            this.overview = overview;
            this.firstAirDate = first_air_date;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };



}
