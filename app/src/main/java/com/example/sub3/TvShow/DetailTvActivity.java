package com.example.sub3.TvShow;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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

public class DetailTvActivity extends AppCompatActivity {

    public static final String KIRIM_TV = "kirim_tv";

    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        progressBar = findViewById(R.id.progress_Bar);
        progressBar.bringToFront();

        TvShow tvShow = getIntent().getParcelableExtra(KIRIM_TV);


        String tvTitle = tvShow.getName();
        String tvRelease = tvShow.getFirstAirDate();
        String tvOverview = tvShow.getOverview();

        TextView title = findViewById(R.id.tv_title_tv);
        title.setText(tvTitle);

        TextView rilis = findViewById(R.id.tv_releasetv);
        rilis.setText(tvRelease);

        TextView overview = findViewById(R.id.tv_deskripsitv);
        overview.setText(tvOverview);


        ImageView ivPosterTv = findViewById(R.id.iv_img_tv);

        String urlpictv = "https://image.tmdb.org/t/p/w500"+tvShow.getPosterPath();
        Glide.with(DetailTvActivity.this)
                .load(urlpictv)
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
                .into(ivPosterTv);
    }




}
