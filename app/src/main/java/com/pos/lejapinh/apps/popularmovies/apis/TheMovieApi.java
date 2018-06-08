package com.pos.lejapinh.apps.popularmovies.apis;
import android.widget.Toast;

import com.pos.lejapinh.apps.popularmovies.adapters.TheMovieAdapter;
import com.pos.lejapinh.apps.popularmovies.entities.TheMovie;
import com.pos.lejapinh.apps.popularmovies.services.TheMovieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheMovieApi implements TheMovieService {

	private static TheMovieApi theMovieApi;
	private final String base_url = "https://api.themoviedb.org/3/movie/popular?api_key=a6ab6c6e92a1444a6633b3d1b30eb4c9";
	public final String api_key = "a6ab6c6e92a1444a6633b3d1b30eb4c9";

	private Retrofit retrofit;

	public static TheMovieApi getInstance() {
		if(theMovieApi == null) {
			theMovieApi = new TheMovieApi();
		}

		return theMovieApi;
	}

	private TheMovieApi() {
		retrofit = new Retrofit.Builder()
			.baseUrl(base_url)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
	}

	public TheMovieService getTheMovieService() {
		return this.retrofit.create(TheMovieService.class);
	}

	@Override
	public Call<List<TheMovie>> getAllPopularMovies(String api_key) {
		Call<List<TheMovie>> call = getTheMovieService().getAllPopularMovies(api_key);

		call.enqueue(new Callback<List<TheMovie>>() {
			@Override
			public void onResponse(Call<List<TheMovie>> call, Response<List<TheMovie>> response) {
				List<TheMovie> movies = response.body();
			}

			@Override
			public void onFailure(Call<List<TheMovie>> call, Throwable t) {
				//Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT);
			}
		});

		return call;
	}
}