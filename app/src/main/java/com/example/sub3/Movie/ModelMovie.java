package com.example.sub3.Movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class ModelMovie extends ViewModel {

    private static final String API = "YOUR API KEY";
    private MutableLiveData<ArrayList<Movies>> listMov = new MutableLiveData<>();

    public void setListMov(){
        AsyncHttpClient client = new AsyncHttpClient();
        final  ArrayList<Movies> listItem = new ArrayList<>();
        String urlMov = "https://api.themoviedb.org/3/discover/movie?api_key=" + API + "&language=en-US";

            client.get(urlMov, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String resultMovie = new String(responseBody);
                        JSONObject movieObj = new JSONObject(resultMovie);
                        JSONArray lis = movieObj.getJSONArray("results");
                        for (int i = 0  ; i < lis.length(); i++) {
                            JSONObject movie = lis.getJSONObject(i);
                            Movies moviesI = new Movies(movie);
                            listItem.add(moviesI);

                        }
                        listMov.postValue(listItem);
                    } catch (JSONException e) {
                        Log.d("Exception", e.getMessage());
                    }
                }



                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("onFailure", error.getMessage());
                }
            });
    }
    LiveData<ArrayList<Movies>> getMovies(){
        return listMov;
    }
}
