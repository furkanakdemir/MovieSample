package net.furkanakdemir.moviesample.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class MovieListViewModel extends ViewModel {


    private static final int LIMIT_SEARCH = 2;

    private MutableLiveData<List<Movie>> movies;
    private MovieRepository movieRepository;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public MovieListViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<Movie>> getMovies() {
        if (movies == null) {
            movies = new MutableLiveData<>();
            loadMovies();
        }
        return movies;
    }

    private void loadMovies() {
        movieRepository.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<Movie> movieList) {
                        movies.setValue(movieList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void search(String query) {

        if (query.length() > LIMIT_SEARCH) {
            searchInternal(query);
        } else {
            loadMovies();
        }

    }

    private void searchInternal(String query) {
        movieRepository.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<Movie> movieList) {
                        movies.setValue(movieList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}