package net.furkanakdemir.moviesample.data;

import net.furkanakdemir.moviesample.network.MovieService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RealMovieRepository implements MovieRepository {


    private MovieService movieService;
    private MovieDomainMapper mapper;

    @Inject
    public RealMovieRepository(MovieService movieService,
                               MovieDomainMapper mapper) {

        this.movieService = movieService;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        return movieService.discover(1)
                .map(new Function<MoviePageResponse, List<Movie>>() {
                    @Override
                    public List<Movie> apply(MoviePageResponse moviePageResponse) throws Exception {

                        List<Movie> list = new ArrayList<>();

                        for (MoviePageResponse.MovieRaw movieRaw : moviePageResponse.movieRawList) {
                            list.add(mapper.map(movieRaw));
                        }

                        return list;
                    }
                });
    }

    @Override
    public Observable<List<Movie>> search(String query) {
        return movieService.search(query, 1)
                .map(new Function<MoviePageResponse, List<Movie>>() {
                    @Override
                    public List<Movie> apply(MoviePageResponse moviePageResponse) throws Exception {

                        List<Movie> list = new ArrayList<>();

                        for (MoviePageResponse.MovieRaw movieRaw : moviePageResponse.movieRawList) {
                            list.add(mapper.map(movieRaw));
                        }

                        return list;
                    }
                });
    }
}
