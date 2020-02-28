package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.utilities.JsonUtils;
import com.example.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.example.popularmovies.utilities.JsonUtils.*;

public class MainActivity extends AppCompatActivity {

    private ImageView mMoviePoster;
    private TextView mPopMovieResult;
    private TextView mErrorMessage;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviePoster = (ImageView) findViewById(R.id.image_iv);

        mPopMovieResult = (TextView) findViewById(R.id.tv_pop_movies_result);

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message_display);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        URL getTheURL = NetworkUtils.buildUrl();
        new PopMovieQueryTask().execute(getTheURL);

    }

    private void showJsonDataView() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mPopMovieResult.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mPopMovieResult.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class PopMovieQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
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
            mProgressBar.setVisibility(View.INVISIBLE);
            if (movieURL != null && !movieURL.equals("")) {
                List<Movie> movies = JsonUtils.parseMovieJson(movieURL);
                if (movies == null) {
                    closeOnError();
                    return;
                }
                populateUI(movies);


            }
        }
    }
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_LONG).show();

    }

    private void populateUI(List<Movie> movies) {
//        TextView title = findViewById(R.id.tv_pop_movies_result);
//        title.setText(movies.get(0).getMovieTitle());

        String posterURL = "https://image.tmdb.org/t/p/w185/" + movies.get(0).getPosterPath();

        Picasso picasso = Picasso.get();
        picasso.setIndicatorsEnabled(true);
        picasso.load(posterURL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mMoviePoster);

    }

}
