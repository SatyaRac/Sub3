package com.example.sub3.Movie;

import android.os.Parcel;
import android.os.Parcelable;

/*import java.util.List;*/


import org.json.JSONObject;


public class Movies implements Parcelable {

	private String overview;

	private String title;

	private String posterPath;

	private String releaseDate;

	private int id;

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}


	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}


	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}


	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}


	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.overview);
		dest.writeString(this.title);
		dest.writeString(this.posterPath);
		dest.writeString(this.releaseDate);
		dest.writeInt(this.id);

	}

	Movies(JSONObject object) {
		try {
			int id = object.getInt("id");
			String title =  object.getString("title");
			String poster_path = object.getString("poster_path");
			String overview = object.getString("overview");
			String release_date = object.getString("release_date");


			this.id = id;
			this.title = title;
			this.posterPath = poster_path;
			this.overview = overview;
			this.releaseDate = release_date;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	protected Movies(Parcel in) {
		this.overview = in.readString();
		this.title = in.readString();
		this.posterPath = in.readString();
		this.releaseDate = in.readString();
		this.id = in.readInt();
	}

	public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
		@Override
		public Movies createFromParcel(Parcel source) {
			return new Movies(source);
		}

		@Override
		public Movies[] newArray(int size) {
			return new Movies[size];
		}
	};
}