package com.pos.lejapinh.apps.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;

public class ItemActivity extends AppCompatActivity {

    private ImageView img_movie;
    private TextView txt_title, txt_release, txt_description;
    private Button btn_favorite;
    private int movie_id;
    private TheMovie tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        movie_id = Integer.parseInt(getIntent().getStringExtra("movie_id"));

        InitializeComponents();

        new ClientTheMovieItem(movie_id).execute();
    }

    private void InitializeComponents() {
        img_movie = (ImageView) findViewById(R.id.img_movie);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_release = (TextView) findViewById(R.id.txt_release);
        txt_description = (TextView) findViewById(R.id.txt_description);
        btn_favorite = (Button) findViewById(R.id.btn_favorite);
    }

    public class ClientTheMovieItem extends AsyncTask<Void, Void, Void> {

        private int movie_id;

        public ClientTheMovieItem(int movie_id) {
            this.movie_id = movie_id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("https://api.themoviedb.org/3/movie/" + movie_id + "?api_key=a6ab6c6e92a1444a6633b3d1b30eb4c9").build();
                okhttp3.Response response = client.newCall(request).execute();
                String responseGson = response.body().string();

                final JSONObject josonMovie;
                try {
                    josonMovie = new JSONObject(responseGson);
                    tm = new TheMovie(
                            josonMovie.getBoolean("adult"),
                            josonMovie.getString("backdrop_path"),
                            josonMovie.getInt("id"),
                            josonMovie.getString("original_language"),
                            josonMovie.getString("original_title"),
                            josonMovie.getString("overview"),
                            josonMovie.getString("popularity"),
                            josonMovie.getString("poster_path"),
                            josonMovie.getString("release_date"),
                            josonMovie.getString("title"),
                            josonMovie.getBoolean("video"),
                            josonMovie.getString("vote_average"),
                            josonMovie.getString("vote_count"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt_title.setText(tm.getTitle().toString());
                        txt_release.setText(tm.getRelease_date());
                        txt_description.setText(tm.getOverview());

                        Picasso.get()
                                .load("https://image.tmdb.org/t/p/w780" + tm.getPoster_path())
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.loading_error)
                                .into(img_movie);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
