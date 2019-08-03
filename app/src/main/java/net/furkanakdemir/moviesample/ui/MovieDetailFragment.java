package net.furkanakdemir.moviesample.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import net.furkanakdemir.moviesample.BuildConfig;
import net.furkanakdemir.moviesample.R;
import net.furkanakdemir.moviesample.image.ImageLoader;
import net.furkanakdemir.moviesample.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends BaseFragment {


    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.detailNameTextView)
    TextView detailNameTextView;

    @BindView(R.id.detailDateTextView)
    TextView detailDateTextView;

    @BindView(R.id.overviewTextView)
    TextView overviewTextView;

    @BindView(R.id.posterImageView)
    ImageView posterImageView;

    @Inject
    ImageLoader imageLoader;


    private static final String BASE_URL_IMAGE = BuildConfig.BASE_URL_IMAGE;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieViewItem movie = MovieDetailFragmentArgs.fromBundle(getArguments()).getMovie();

        collapsingToolbarLayout.setTitle("");

        detailNameTextView.setText(movie.getName());
        detailDateTextView.setText(movie.getReleaseDate());
        overviewTextView.setText(movie.getOverview());
        String imageUrl = BASE_URL_IMAGE + movie.getBackdropUrl();

        imageLoader.load(posterImageView, imageUrl);


    }
}
