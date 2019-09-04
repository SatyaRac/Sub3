package com.example.sub3.TvShow;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sub3.R;

import java.util.ArrayList;

public class TvFrag extends Fragment {

    private RecyclerView rvTv;
    private ProgressBar progressBar;
    private ModelTv modelTv;
    private CardViewTvAdapter adapter;


    public TvFrag() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        rvTv = view.findViewById(R.id.menu_tv);
        progressBar = view.findViewById(R.id.progres_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        modelTv = ViewModelProviders.of(getActivity()).get(ModelTv.class);
        modelTv.getTv().observe(getActivity(), getTv);

        adapter = new CardViewTvAdapter(getActivity());
        adapter.notifyDataSetChanged();

        rvTv.setHasFixedSize(true);
        rvTv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTv.setAdapter(adapter);

        modelTv.setListTv();
        showLoading(false);
    }

    private Observer<ArrayList<TvShow>> getTv = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShows) {
            if (tvShows != null){
                adapter.setTv(tvShows);
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
