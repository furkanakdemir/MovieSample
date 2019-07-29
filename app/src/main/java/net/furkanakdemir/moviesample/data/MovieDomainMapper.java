package net.furkanakdemir.moviesample.data;

import javax.inject.Inject;

import static net.furkanakdemir.moviesample.data.Movie.MovieBuilder.movieBuilder;

public class MovieDomainMapper {


    @Inject
    public MovieDomainMapper() {

    }


    public Movie map(MoviePageResponse.MovieRaw movieRaw) {
        return movieBuilder().name(movieRaw.title)
                .releaseDate(movieRaw.releaseDate)
                .build();
    }
}
