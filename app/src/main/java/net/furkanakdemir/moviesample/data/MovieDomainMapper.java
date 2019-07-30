package net.furkanakdemir.moviesample.data;

import net.furkanakdemir.moviesample.util.DateFormatter;

import javax.inject.Inject;

import static net.furkanakdemir.moviesample.data.Movie.MovieBuilder.movieBuilder;

public class MovieDomainMapper {


    private DateFormatter dateFormatter;

    @Inject
    public MovieDomainMapper(DateFormatter dateFormatter) {

        this.dateFormatter = dateFormatter;
    }


    public Movie map(MoviePageResponse.MovieRaw movieRaw) {
        return movieBuilder().name(movieRaw.title)
                .releaseDate(dateFormatter.formatDate(movieRaw.releaseDate))
                .posterUrl(movieRaw.posterPath)
                .overview(movieRaw.overview)
                .backdropUrl(movieRaw.backdropPath)
                .build();
    }
}
