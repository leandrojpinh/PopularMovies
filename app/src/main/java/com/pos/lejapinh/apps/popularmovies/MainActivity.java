package com.pos.lejapinh.apps.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pos.lejapinh.apps.popularmovies.adapters.TheMovieAdapter;
import com.pos.lejapinh.apps.popularmovies.controllers.TheMovieController;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_list_movies;
    RecyclerView.Adapter theMovieAdapter;
    TheMovieController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_list_movies = (RecyclerView) findViewById(R.id.rv_list_movies);
        rv_list_movies.setHasFixedSize(true);
        rv_list_movies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        new ClientTheMovie().execute();
    }

    public class ClientTheMovie extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("https://api.themoviedb.org/3/movie/popular?api_key=a6ab6c6e92a1444a6633b3d1b30eb4c9").build();
                okhttp3.Response response = client.newCall(request).execute();
                String responseGson = response.body().string();

                JSONObject josonMovie;
                List<TheMovie> listMovies = new ArrayList<TheMovie>();
                try {
                    josonMovie = new JSONObject(responseGson);
                    JSONArray jsonMoviesArray = josonMovie.getJSONArray("results");
                    for (int i = 0; i < jsonMoviesArray.length(); i++) {
                        JSONObject jsonObject = new JSONObject(jsonMoviesArray.getString(i));

                        TheMovie movie = new TheMovie(
                                jsonObject.getBoolean("adult"),
                                jsonObject.getString("backdrop_path"),
                                jsonObject.getInt("id"),
                                jsonObject.getString("original_language"),
                                jsonObject.getString("original_title"),
                                jsonObject.getString("overview"),
                                jsonObject.getString("popularity"),
                                jsonObject.getString("poster_path"),
                                jsonObject.getString("release_date"),
                                jsonObject.getString("title"),
                                jsonObject.getBoolean("video"),
                                jsonObject.getString("vote_average"),
                                jsonObject.getString("vote_count"));

                        listMovies.add(movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                theMovieAdapter = new TheMovieAdapter(listMovies, getApplicationContext());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rv_list_movies.setAdapter(theMovieAdapter);
                    }
                });

            } catch (IOException e) {
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
