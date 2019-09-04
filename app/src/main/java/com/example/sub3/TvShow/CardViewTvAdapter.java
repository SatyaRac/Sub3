package com.example.sub3.TvShow;

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

public class CardViewTvAdapter extends RecyclerView.Adapter<CardViewTvAdapter.CardViewViewHolder> {

    private List<TvShow> tvData = new ArrayList<>();
    private Activity activity;
    private Context context;

    CardViewTvAdapter(Activity activity) {
        this.activity = activity;
    }

    void setTv(List<TvShow> data) {
        this.tvData.clear();
        this.tvData.addAll(data);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View Tview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tv, viewGroup, false);
        return new CardViewViewHolder(Tview);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int i) {
        final TvShow tvShow = tvData.get(i);
        holder.tvTitle.setText(tvData.get(i).getName());
        holder.tvReleaseDate.setText(tvData.get(i).getFirstAirDate());
        String urlPosterTv = "https://image.tmdb.org/t/p/w500" + tvData.get(i).getPosterPath();
        Glide.with(holder.itemView.getContext())
                .load(urlPosterTv)
                .apply(new RequestOptions().override(50, 75))
                .into(holder.imgPos);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveTv = new Intent(activity, DetailTvActivity.class);
                moveTv.putExtra(DetailTvActivity.KIRIM_TV, tvShow);
                activity.startActivity(moveTv);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvReleaseDate;
        Button btnDetail;
        ImageView imgPos;

        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_judul_tv);
            tvReleaseDate = itemView.findViewById(R.id.tv_rilis_tv);
            btnDetail = itemView.findViewById(R.id.btn_detailtv);
            imgPos = itemView.findViewById(R.id.iv_poster_tv);
        }
    }
}
