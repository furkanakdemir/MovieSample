package net.furkanakdemir.moviesample.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import net.furkanakdemir.moviesample.R;
import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.image.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnMovieCallback onMovieCallback;
    private ImageLoader imageLoader;


    public MovieAdapter(OnMovieCallback onMovieCallback, ImageLoader imageLoader) {
        this.onMovieCallback = onMovieCallback;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movies.get(position);

        holder.nameTextView.setText(movie.getName());
        holder.releaseDateTextView.setText(movie.getReleaseDate());
        holder.overviewTextView.setText(movie.getOverview());


        String imageUrl = "https://image.tmdb.org/t/p/w92" + movie.getPosterUrl();
        imageLoader.load(holder.posterImageView, imageUrl);


        holder.itemView.setOnClickListener(view -> onMovieCallback.onMovieClicked(movie));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public void updateList(List<Movie> newList) {
        movies.clear();
        movies.addAll(newList);
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView releaseDateTextView;
        public TextView overviewTextView;
        public ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            overviewTextView = itemView.findViewById(R.id.overviewTextView);
        }
    }

    interface OnMovieCallback {
        void onMovieClicked(Movie movie);
    }
}
