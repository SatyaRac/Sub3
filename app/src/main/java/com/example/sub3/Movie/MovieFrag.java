package com.example.sub3.Movie;

import android.arch.lifecycle.ViewModelProviders;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sub3.R;
import android.arch.lifecycle.Observer;
import java.util.ArrayList;
import java.util.List;


public class MovieFrag extends Fragment{

    private RecyclerView rvMovie;
    private ProgressBar progressBar;
    private ModelMovie modelMovie;
    private CardViewMovAdapter adapter;
    private List<Movies> movies;


    public MovieFrag() {

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.menu_movie);
        progressBar = view.findViewById(R.id.progres_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            modelMovie = ViewModelProviders.of(getActivity()).get(ModelMovie.class);
            modelMovie.getMovies().observe(getActivity(), getMovies);

            adapter = new CardViewMovAdapter(getActivity());
            adapter.notifyDataSetChanged();

            rvMovie.setHasFixedSize(true);
            rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvMovie.setAdapter(adapter);

            modelMovie.setListMov();
            showLoading(false);
        }


    private Observer<ArrayList<Movies>> getMovies = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movies> movies) {
            if (movies != null){
                adapter.setMovie(movies);
                showLoading(true);
            }

        }
    };

    private void showLoading(Boolean ya) {
        if (ya) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
