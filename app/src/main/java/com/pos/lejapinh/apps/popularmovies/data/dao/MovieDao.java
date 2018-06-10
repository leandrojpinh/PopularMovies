package com.pos.lejapinh.apps.popularmovies.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pos.lejapinh.apps.popularmovies.data.data_entities.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE id IN (:movieIds)")
    List<Movie> loadAllByIds(int[] movieIds);

    @Query("SELECT * FROM movie WHERE original_title LIKE :title LIMIT 1")
    Movie findByTitle(String title);

    @Insert
    void insertAll(Movie... movies);

    @Delete
    void delete(Movie movie);

    @Insert
    void insert(Movie movie);

}
