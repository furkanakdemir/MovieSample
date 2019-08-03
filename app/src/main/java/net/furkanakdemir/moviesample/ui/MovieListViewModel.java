package net.furkanakdemir.moviesample.ui;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieDomainMapper;
import net.furkanakdemir.moviesample.data.MovieRepository;
import net.furkanakdemir.moviesample.network.MovieService;
import net.furkanakdemir.moviesample.paging.MovieDataSourceFactory;
import net.furkanakdemir.moviesample.paging.MovieSearchDataSourceFactory;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class MovieListViewModel extends ViewModel {


    private static final int LIMIT_SEARCH = 2;
    private final MovieDataSourceFactory factory;

    public LiveData<PagedList<Movie>> movies;


    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MovieRepository movieRepository;
    private MovieService movieService;
    private MovieDomainMapper mapper;

    private CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<String> searchLiveData = new MutableLiveData<>();
    public LiveData<PagedList<Movie>> searchResultMovies = Transformations.switchMap(searchLiveData, new Function<String, LiveData<PagedList<Movie>>>() {
        @Override
        public LiveData<PagedList<Movie>> apply(String input) {

            MovieSearchDataSourceFactory searchDataSourceFactory = new MovieSearchDataSourceFactory(movieService, mapper, input);

            PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(20).setPageSize(20).build();
            return new LivePagedListBuilder<>(searchDataSourceFactory, config).build();
        }
    });


    @Inject
    public MovieListViewModel(MovieRepository movieRepository, MovieService movieService, MovieDomainMapper mapper) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.mapper = mapper;

        factory = new MovieDataSourceFactory(movieService, mapper);


        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20).setPageSize(20).build();

        movies = new LivePagedListBuilder<>(factory, config).build();


    }

    public LiveData<PagedList<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loading;
    }


    public void search(String query) {

        if (query.length() > LIMIT_SEARCH) {
            searchLiveData.setValue(query);
        } else {
            refreshMovies();
        }

    }

    public void refreshMovies() {
        if (factory.getSource().getValue() != null) {
            factory.getSource().getValue().invalidate();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}