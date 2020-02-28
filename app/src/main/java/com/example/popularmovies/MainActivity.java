package com.example.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    private static final int NUMBER_OF_COLUMNS = 2;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        adapter = new MovieAdapter(this, Collections.<Movie>emptyList());
        recyclerView.setAdapter(adapter);

        URL getTheURL = NetworkUtils.buildUrl();
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
                mToast = Toast.makeText(this, "Item popular clicked.", Toast.LENGTH_LONG);
                mToast.show();
                return true;

            case R.id.action_topRated:
                mToast = Toast.makeText(this, "Item top rated clicked.", Toast.LENGTH_LONG);
                mToast.show();
                return true;

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
