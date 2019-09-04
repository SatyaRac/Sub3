package com.example.sub3.TvShow;

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

public class ModelTv extends ViewModel {

    private static final String API = "API_YOUR_KEY";
    private MutableLiveData<ArrayList<TvShow>> listTv = new MutableLiveData<>();

    public void setListTv() {
        AsyncHttpClient client = new AsyncHttpClient();
        final  ArrayList<TvShow> listItem = new ArrayList<>();
        String urlTv = "https://api.themoviedb.org/3/discover/tv?api_key=" + API + "&language=en-US";

        client.get(urlTv, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String resultTv = new String(responseBody);
                    JSONObject tvObj = new JSONObject(resultTv);
                    JSONArray lis = tvObj.getJSONArray("results");
                        for (int i = 0  ; i < lis.length(); i++){
                            JSONObject tv = lis.getJSONObject(i);
                            TvShow tvShowI = new TvShow(tv);
                            listItem.add(tvShowI);
                        }
                    listTv.postValue(listItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Exception", e.getMessage());
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());

            }
        });
    }
    LiveData<ArrayList<TvShow>> getTv(){
        return listTv;
    }
    }
