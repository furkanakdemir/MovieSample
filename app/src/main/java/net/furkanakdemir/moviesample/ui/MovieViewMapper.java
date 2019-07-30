package net.furkanakdemir.moviesample.ui;


import net.furkanakdemir.moviesample.data.Movie;

import javax.inject.Inject;

import static net.furkanakdemir.moviesample.ui.MovieViewItem.MovieViewItemBuilder.movieViewItemBuilder;

public class MovieViewMapper {


    @Inject
    public MovieViewMapper() {

    }


    public MovieViewItem map(Movie movie) {
        return movieViewItemBuilder().name(movie.getName())
                .releaseDate(movie.getReleaseDate())
                .posterUrl(movie.getPosterUrl())
                .overview(movie.getOverview())
                .backdropUrl(movie.getBackdropUrl())
                .build();
    }
}
