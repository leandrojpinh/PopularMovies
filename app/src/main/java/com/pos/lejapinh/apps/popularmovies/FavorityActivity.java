package com.pos.lejapinh.apps.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pos.lejapinh.apps.popularmovies.adapters.FavoriteAdapter;
import com.pos.lejapinh.apps.popularmovies.adapters.TheMovieAdapter;
import com.pos.lejapinh.apps.popularmovies.controllers.TheMovieController;
import com.pos.lejapinh.apps.popularmovies.data.MovieClient;
import com.pos.lejapinh.apps.popularmovies.data.data_entities.Movie;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class FavorityActivity extends AppCompatActivity {
    RecyclerView rv_list_movies;
    RecyclerView.Adapter favorityAdapter;
    MovieClient movieClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favority_activity);

        rv_list_movies = (RecyclerView) findViewById(R.id.rv_list_movies);
        rv_list_movies.setHasFixedSize(true);
        rv_list_movies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        movieClient = MovieClient.getInstance(getApplicationContext());

        new ClientTheMovieFavority().execute();
    }

    public class ClientTheMovieFavority extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                List<Movie> movies = movieClient.getAll();
                favorityAdapter = new FavoriteAdapter(movies, getApplicationContext());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rv_list_movies.setAdapter(favorityAdapter);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /*public class TheMovieTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            controller = new TheMovieController();
            try {
                List<TheMovie> a = controller.getAllPopularMovies();
                Log.i("", "");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }*/
}
