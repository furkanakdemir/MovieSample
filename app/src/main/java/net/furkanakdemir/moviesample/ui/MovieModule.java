package net.furkanakdemir.moviesample.ui;


import net.furkanakdemir.moviesample.data.MovieDomainMapper;
import net.furkanakdemir.moviesample.data.MovieRepository;
import net.furkanakdemir.moviesample.data.RealMovieRepository;
import net.furkanakdemir.moviesample.network.MovieService;
import net.furkanakdemir.moviesample.network.NetworkModule;
import net.furkanakdemir.moviesample.util.DateFormatter;
import net.furkanakdemir.moviesample.util.MovieDateFormatter;

import dagger.Module;
import dagger.Provides;

@Module(includes = NetworkModule.class)
public class MovieModule {

    @Provides
    public MovieDomainMapper provideMovieDomainMapper(DateFormatter dateFormatter) {
        return new MovieDomainMapper(dateFormatter);
    }

    @Provides
    public MovieViewMapper provideMovieViewMapper() {
        return new MovieViewMapper();
    }

    @Provides
    public MovieRepository provideMovieRepository(MovieService movieService,
                                                  MovieDomainMapper mapper) {
        return new RealMovieRepository(movieService, mapper);
    }

    @Provides
    DateFormatter provideDateFormatter() {
        return new MovieDateFormatter();
    }

}
