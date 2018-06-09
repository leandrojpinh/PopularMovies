package com.pos.lejapinh.apps.popularmovies.services;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieService {

	@GET("/3/movie/popular")
    Call<List<TheMovie>> getAllPopularMovies(@Query("api_key") String api_key);

}