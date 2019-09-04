package com.example.sub3.Movie;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sub3.R;

public class DatailMovActivity extends AppCompatActivity {

    public static final String KIRIM_MOVIE = "kirim_movie";

    public ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datail_mov);

        progressBar = findViewById(R.id.progress_Bar);
        progressBar.bringToFront();

        Movies movies = getIntent().getParcelableExtra(KIRIM_MOVIE);


        String tvTitle = movies.getTitle();
        String tvRelease = movies.getReleaseDate();
        String tvOverview = movies.getOverview();

        TextView title = findViewById(R.id.tv_title_mov);
        title.setText(tvTitle);

        TextView rilis = findViewById(R.id.tv_releasemov);
        rilis.setText(tvRelease);

        TextView overview = findViewById(R.id.tv_deskripsimov);
        overview.setText(tvOverview);


        ImageView ivPosterMov = findViewById(R.id.iv_img__mov);

        String urlpicmov = "https://image.tmdb.org/t/p/w500" + movies.getPosterPath();
        Glide.with(DatailMovActivity.this)
                .load(urlpicmov)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(ivPosterMov);


        }
    }

