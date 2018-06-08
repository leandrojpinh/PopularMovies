package com.pos.lejapinh.apps.popularmovies.entities;

import com.google.gson.annotations.SerializedName;

public class TheMovie {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String url_img;

    @SerializedName("overview")
    private String description;

    @SerializedName("release_date")
    private String release;

    public TheMovie(int id, String title, String url_img, String description, String release) {
        this.id = id;
        this.title = title;
        this.url_img = url_img;
        this.description = description;
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl_img() {
        return url_img;
    }

    public String getDescription() {
        return description;
    }

    public String getRelease() {
        return release;
    }

    public int getId() { return id; }
}