package com.example.popularmovies.utilities;

import com.example.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_RESULTS = "results";
    public static final String KEY_TITLE = "title";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_ORIGINAL_TITLE = "original_title";
    public static final String KEY_MOVIE_OVERVIEW = "overview";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_VOTE_AVERAGE = "vote_average";

    public static List<Movie> parseMovieJson(String json) {

        List<Movie> movieList = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(json);

            JSONArray results = jsonObject.getJSONArray(KEY_RESULTS);

            for(int i = 0; i < results.length(); i++) {

                movieList.add(parseMovie(results.getJSONObject(i)));

            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;

    }

    private static Movie parseMovie(JSONObject jsonObject) {

        Movie movie = new Movie();

        try {

            movie.setMovieTitle(jsonObject.getString(KEY_TITLE));
            movie.setPosterPath(jsonObject.getString(KEY_POSTER_PATH));
            movie.setOriginalTitle(jsonObject.getString(KEY_ORIGINAL_TITLE));
            movie.setMovieOverview(jsonObject.getString(KEY_MOVIE_OVERVIEW));
            movie.setReleaseDate(jsonObject.getString(KEY_RELEASE_DATE));
            movie.setVoteAverage(jsonObject.getDouble(KEY_VOTE_AVERAGE));

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;
    }

}
