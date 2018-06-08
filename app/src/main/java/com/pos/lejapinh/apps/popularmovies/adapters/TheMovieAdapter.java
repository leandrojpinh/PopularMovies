package com.pos.lejapinh.apps.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pos.lejapinh.apps.popularmovies.MainActivity;
import com.pos.lejapinh.apps.popularmovies.R;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;

import java.util.List;

public class TheMovieAdapter extends RecyclerView.Adapter<TheMovieAdapter.ViewHolderAdapter> {

    List<TheMovie> movies;
    Context context;

    public TheMovieAdapter(List<TheMovie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);
        return new ViewHolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position) {
        TheMovie tm = movies.get(position);

        holder.txt_title.setText(tm.getTitle());
        holder.txt_desc.setText(tm.getDescription());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        TextView txt_title, txt_desc;

        public ViewHolderAdapter(View itemView) {
            super(itemView);

            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_desc = (TextView) itemView.findViewById(R.id.txt_description);
        }
    }
}
