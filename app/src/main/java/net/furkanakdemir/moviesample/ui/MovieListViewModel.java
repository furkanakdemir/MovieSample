package net.furkanakdemir.moviesample.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class MovieListViewModel extends ViewModel {


    private static final int LIMIT_SEARCH = 2;

    public LiveData<PagedList<Movie>> movies;
    private CompositeDisposable disposables;


    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MovieRepository movieRepository;

    private MutableLiveData<String> searchLiveData = new MutableLiveData<>();
    private LiveData<PagedList<Movie>> searchResultMovies;


    @Inject
    public MovieListViewModel(MovieRepository movieRepository, CompositeDisposable disposables) {
        this.movieRepository = movieRepository;
        this.disposables = disposables;
        movies = movieRepository.getMoviesLiveData();
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return movies;
    }

    public LiveData<PagedList<Movie>> getSearchResultMovies() {
        return Transformations.switchMap(searchLiveData, input -> movieRepository.getMovieSearchLiveData(input));
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
        movieRepository.refresh();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}