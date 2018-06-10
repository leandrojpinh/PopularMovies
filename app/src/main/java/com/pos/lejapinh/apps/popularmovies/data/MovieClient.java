package com.pos.lejapinh.apps.popularmovies.data;

import android.content.Context;
import android.os.AsyncTask;

import com.pos.lejapinh.apps.popularmovies.data.dao.DatabaseContext;
import com.pos.lejapinh.apps.popularmovies.data.dao.MovieDao;
import com.pos.lejapinh.apps.popularmovies.data.data_entities.Movie;

import java.util.List;

public class MovieClient implements MovieDao {
    private static MovieClient movieClient;
    private Context context;
    private DatabaseContext db;
    private List<Movie> list;
    private Movie Movie;

    public static MovieClient getInstance(Context context) {
        movieClient = new MovieClient(context);

        return movieClient;
    }

    private MovieClient(Context context) {
        this.context = context;
        db = DatabaseClient.getInstance(context);
    }

    @Override
    public List<Movie> getAll() {
        Runnable getAll = new Runnable() {
            @Override
            public void run() {
                list = db.movieDao().getAll();
            }
        };

        AsyncTask.execute(getAll);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Movie> loadAllByIds(final int[] MovieIds) {
        Runnable getAllByIds = new Runnable() {
            @Override
            public void run() {
                list = db.movieDao().loadAllByIds(MovieIds);
            }
        };

        AsyncTask.execute(getAllByIds);

        return list;
    }

    @Override
    public Movie findByTitle(final String email) {
        Runnable getAllByIds = new Runnable() {
            @Override
            public void run() {
                Movie = db.movieDao().findByTitle(email);
            }
        };

        AsyncTask.execute(getAllByIds);

        while (Movie == null) {

        }

        return Movie;
    }

    @Override
    public void insertAll(final Movie... Movies) {
        Runnable insert = new Runnable() {
            @Override
            public void run() {
                db.movieDao().insertAll(Movies);
            }
        };

        AsyncTask.execute(insert);
    }

    @Override
    public void delete(final Movie Movie) {
        Runnable insert = new Runnable() {
            @Override
            public void run() {
                db.movieDao().delete(Movie);
            }
        };

        AsyncTask.execute(insert);
    }

    @Override
    public void insert(final Movie Movie) {
        Runnable insert = new Runnable() {
            @Override
            public void run() {
                db.movieDao().insert(Movie);
            }
        };

        AsyncTask.execute(insert);
    }
}
