package net.furkanakdemir.moviesample.ui;


import net.furkanakdemir.moviesample.data.MovieDomainMapper;
import net.furkanakdemir.moviesample.data.MovieRepository;
import net.furkanakdemir.moviesample.data.RealMovieRepository;
import net.furkanakdemir.moviesample.network.MovieService;
import net.furkanakdemir.moviesample.network.NetworkModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = NetworkModule.class)
public class MovieModule {

    @Provides
    public MovieDomainMapper provideMovieDomainMapper() {
        return new MovieDomainMapper();
    }

    @Provides
    public MovieRepository provideMovieRepository(MovieService movieService,
                                                  MovieDomainMapper mapper) {
        return new RealMovieRepository(movieService, mapper);
    }

}
