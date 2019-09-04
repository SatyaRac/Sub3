package com.example.sub3.Movie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sub3.R;

import java.util.ArrayList;
import java.util.List;

public class CardViewMovAdapter extends RecyclerView.Adapter<CardViewMovAdapter.CardViewViewHolder> {

    private List<Movies> mData = new ArrayList<>();
    private Activity activity;
    private Context context;

    CardViewMovAdapter(Activity activity){
        this.activity = activity;
    }

     void setMovie(List<Movies> data){
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View Mview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_movie, viewGroup, false);
        return new CardViewViewHolder(Mview);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int i) {
         final Movies movies = mData.get(i);

        holder.tvTitle.setText(mData.get(i).getTitle());
        holder.tvReleaseDate.setText(mData.get(i).getReleaseDate());
        String urlPosterMov = "https://image.tmdb.org/t/p/w500" + mData.get(i).getPosterPath();
        Glide.with(holder.itemView.getContext())
                .load(urlPosterMov)
                .apply(new RequestOptions().override(50,75))
                .into(holder.imgPos);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveMovie = new Intent(activity, DatailMovActivity.class);
                moveMovie.putExtra(DatailMovActivity.KIRIM_MOVIE, movies);
                activity.startActivity(moveMovie);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

     class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvReleaseDate;
        Button btnDetail;
        ImageView imgPos;

        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_judul_mov);
            tvReleaseDate = itemView.findViewById(R.id.tv_rilis_mov);
            btnDetail = itemView.findViewById(R.id.btn_detailmov);
            imgPos = itemView.findViewById(R.id.iv_poster_mov);
        }
    }


}
