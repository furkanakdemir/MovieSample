package net.furkanakdemir.moviesample.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieDomainMapper;
import net.furkanakdemir.moviesample.network.MovieService;

import io.reactivex.disposables.CompositeDisposable;

public class MovieSearchDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<MovieSearchPageKeyedDataSource> source = new MutableLiveData<>();


    private MovieService movieService;
    private MovieDomainMapper mapper;
    private CompositeDisposable disposables;
    private String query;

    public MovieSearchDataSourceFactory(MovieService movieService, MovieDomainMapper mapper, CompositeDisposable disposables, String query) {
        this.movieService = movieService;
        this.mapper = mapper;
        this.disposables = disposables;
        this.query = query;
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        MovieSearchPageKeyedDataSource source = new MovieSearchPageKeyedDataSource(movieService, mapper, disposables, query);
        this.source.postValue(source);
        return source;
    }
}
