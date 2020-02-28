package com.example.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private LayoutInflater inflater;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.inflater = LayoutInflater.from(context);
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.moviegrid_item, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        String posterURL = "https://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath();


        Picasso.get()
                .load(posterURL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(530, 0)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public MovieViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.iv_posters);
        }


    }

}
