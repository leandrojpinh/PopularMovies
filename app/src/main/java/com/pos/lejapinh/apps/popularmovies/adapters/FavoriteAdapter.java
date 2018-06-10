package com.pos.lejapinh.apps.popularmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pos.lejapinh.apps.popularmovies.ItemActivity;
import com.pos.lejapinh.apps.popularmovies.R;
import com.pos.lejapinh.apps.popularmovies.data.data_entities.Movie;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;
import com.pos.lejapinh.apps.popularmovies.interfaces.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter  extends RecyclerView.Adapter<FavoriteAdapter.ViewHolderAdapter> {
    private List<Movie> movies;
    private Context context;

    public FavoriteAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);
        return new FavoriteAdapter.ViewHolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteAdapter.ViewHolderAdapter holder, int position) {
        Movie tm = movies.get(position);

        holder.txt_title.setText(tm.getTitle());
        holder.txt_desc.setText(tm.getOverview());
        holder.txt_movie_id.setText(tm.getId() + "");

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w185" + tm.getPoster_path())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img_movie);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick){

                    String movie_id = holder.txt_movie_id.getText().toString();

                    Intent i = new Intent(context, ItemActivity.class);
                    i.putExtra("movie_id", movie_id);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener itemClickListener;
        private TextView txt_title, txt_desc, txt_movie_id;
        private ImageView img_movie;


        public ViewHolderAdapter(View itemView) {
            super(itemView);

            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_desc = (TextView) itemView.findViewById(R.id.txt_description);
            txt_movie_id = (TextView) itemView.findViewById(R.id.txt_movie_id);
            img_movie = (ImageView) itemView.findViewById(R.id.img_movie);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
