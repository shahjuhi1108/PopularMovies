package com.example.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[0];
        }
    };
    private String movieTitle;
    private String posterPath;
    private String movieOverview;
    private String originalTitle;
    private String releaseDate;
    private Double voteAverage;

    public Movie() {

    }

    public Movie(String movieTitle,
                 String posterPath,
                 String movieOverview,
                 String originalTitle,
                 String releaseDate,
                 Double voteAverage) {
        this.movieTitle = movieTitle;
        this.posterPath = posterPath;
        this.movieOverview = movieOverview;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public Movie(Parcel in) {
        this.movieTitle = in.readString();
        this.posterPath = in.readString();
        this.movieOverview = in.readString();
        this.originalTitle = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieTitle='" + movieTitle + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", movieOverview='" + movieOverview + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.movieTitle);
        dest.writeString(this.posterPath);
        dest.writeString(this.movieOverview);
        dest.writeString(this.originalTitle);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);

    }
}
