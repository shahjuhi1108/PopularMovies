package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.utilities.JsonUtils;
import com.example.popularmovies.utilities.NetworkUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener{

    private MovieAdapter adapter;
    private static final int NUMBER_OF_COLUMNS = 2;
    private Toast mToast;
    private ImageView mMoviePoster;

    @Override
    public void onListItemClick(Movie clickedItemIndex) {

        Context context = this;
        Class detailsOfMovie = MovieDetailsActivity.class;

        Intent startMovieDetailsIntent = new Intent(context, detailsOfMovie);

        startMovieDetailsIntent.putExtra(Intent.EXTRA_TEXT, clickedItemIndex);

        startActivity(startMovieDetailsIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviePoster = findViewById(R.id.iv_posters);

        RecyclerView recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        adapter = new MovieAdapter(this, Collections.<Movie>emptyList(), this);
        recyclerView.setAdapter(adapter);

        URL getTheURL = NetworkUtils.buildURL();
        new PopMovieQueryTask().execute(getTheURL);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        if (mToast != null) {
            mToast.cancel();
        }

        switch (itemId) {

            case R.id.action_popular:
                URL getTheURL = NetworkUtils.buildPopularURL();
                new PopMovieQueryTask().execute(getTheURL);
                return true;

            case R.id.action_topRated:
                URL getTopRatedURL = NetworkUtils.buildTopRatedURL();
                new PopMovieQueryTask().execute(getTopRatedURL);

        }

        return super.onOptionsItemSelected(item);

    }

    public class PopMovieQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {

            URL url = params[0];
            String movieURL = null;

            try {
                movieURL = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return movieURL;
        }

        @Override
        protected void onPostExecute(String movieURL) {
            if (movieURL != null && !movieURL.equals("")) {
                List<Movie> movies = JsonUtils.parseMovieJson(movieURL);
                if (movies == null) {
                    closeOnError();
                    return;
                }
                adapter.setMovies(movies);
            }
        }
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_LONG).show();
    }

}
