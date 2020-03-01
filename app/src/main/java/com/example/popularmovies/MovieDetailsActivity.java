package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            Bundle movieInfo = getIntent().getExtras();
            Movie movie = movieInfo.getParcelable(Intent.EXTRA_TEXT);
            populateUI(movie);
        }
    }

    private void populateUI(Movie movie) {

        TextView movieTitle = findViewById(R.id.tv_movie_title);
        movieTitle.setText(movie.getMovieTitle());

        TextView originalTitle = findViewById(R.id.tv_original_title);
        originalTitle.setText(movie.getOriginalTitle());

        TextView releaseDate = findViewById(R.id.tv_release_date);
        releaseDate.setText(movie.getReleaseDate());

        ImageView moviePoster = findViewById(R.id.iv_poster_thumbnail);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w185/" + movie.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(400, 600)
                .into(moviePoster);

        TextView overview = findViewById(R.id.tv_overview);
        overview.setText(movie.getMovieOverview());

        TextView average = findViewById(R.id.tv_vote_average);
        average.setText(movie.getVoteAverage().toString());


    }


}
