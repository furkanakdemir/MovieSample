package net.furkanakdemir.moviesample.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import net.furkanakdemir.moviesample.R;
import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.image.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnMovieCallback onMovieCallback;
    private ImageLoader imageLoader;

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MovieAdapter(OnMovieCallback onMovieCallback, ImageLoader imageLoader) {
        super(DIFF_CALLBACK);

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

        Movie movie = getItem(position);

        holder.nameTextView.setText(movie.getName());
        holder.releaseDateTextView.setText(movie.getReleaseDate());
        holder.overviewTextView.setText(movie.getOverview());


        String imageUrl = "https://image.tmdb.org/t/p/w500" + movie.getPosterUrl();
        imageLoader.load(holder.posterImageView, imageUrl);


        holder.itemView.setOnClickListener(view -> onMovieCallback.onMovieClicked(movie));
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
