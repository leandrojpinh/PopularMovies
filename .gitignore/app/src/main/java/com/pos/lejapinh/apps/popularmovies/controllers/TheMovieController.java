package com.pos.lejapinh.apps.popularmovies.controllers;

import com.pos.lejapinh.apps.popularmovies.apis.TheMovieApi;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;

import java.io.IOException;
import java.util.List;

public class TheMovieController {

    private TheMovieApi api;

    public List<TheMovie> getAllPopularMovies() throws IOException {
        api = TheMovieApi.getInstance();
        api.getTheMovieService().getAllPopularMovies(api.api_key);

        List<TheMovie> list = api.getTheMovieService().getAllPopularMovies(api.api_key).execute().body();

        return list;
    }
}
