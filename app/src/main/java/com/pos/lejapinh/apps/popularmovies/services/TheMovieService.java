package com.pos.lejapinh.apps.popularmovies.services;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TheMovieService {

	@GET("/movie/popular?api_key={api_key}")
    Call<List<TheMovie>> getAllPopularMovies(@Path("{api_key}") String api_key);

}