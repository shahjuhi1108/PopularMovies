package com.example.popularmovies.model;

public class Movie {

    private String movieTitle;
    private String posterPath;

    public Movie() {

    }

    public Movie(String movieTitle, String posterPath) {
        this.movieTitle = movieTitle;
        this.posterPath = posterPath;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
