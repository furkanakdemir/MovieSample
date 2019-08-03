package net.furkanakdemir.moviesample.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieDomainMapper;
import net.furkanakdemir.moviesample.network.MovieService;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    public MutableLiveData<MoviePageKeyedDataSource> getSource() {
        return source;
    }

    private MutableLiveData<MoviePageKeyedDataSource> source = new MutableLiveData<>();


    private MovieService movieService;
    private MovieDomainMapper mapper;
    private CompositeDisposable disposables;

    public MovieDataSourceFactory(MovieService movieService, MovieDomainMapper mapper, CompositeDisposable disposables) {
        this.movieService = movieService;
        this.mapper = mapper;
        this.disposables = disposables;
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        MoviePageKeyedDataSource source = new MoviePageKeyedDataSource(movieService, mapper, disposables);
        this.source.postValue(source);
        return source;
    }
}
