package net.furkanakdemir.moviesample.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieDomainMapper;
import net.furkanakdemir.moviesample.data.MoviePageResponse;
import net.furkanakdemir.moviesample.network.MovieService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MovieSearchPageKeyedDataSource extends PageKeyedDataSource<Integer, Movie> {

    private MovieService movieService;
    private MovieDomainMapper mapper;
    private CompositeDisposable disposables;
    private String query;

    public MovieSearchPageKeyedDataSource(MovieService movieService, MovieDomainMapper mapper, CompositeDisposable disposables, String query) {
        this.movieService = movieService;
        this.mapper = mapper;
        this.disposables = disposables;
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        int currentPage = 1;
        int nextPage = currentPage + 1;

        requestInitial(params, callback, currentPage, nextPage);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        int currentPage = params.key;
        int nextPage = currentPage + 1;

        loadAfterRequest(params, callback, currentPage, nextPage);
    }


    private void requestInitial(LoadInitialParams<Integer> params,
                                LoadInitialCallback<Integer, Movie> callback,
                                int currentPage,
                                int nextPage) {

        movieService.search(query, currentPage)
                .map(moviePageResponse -> {

                    List<Movie> list = new ArrayList<>();

                    for (MoviePageResponse.MovieRaw movieRaw : moviePageResponse.movieRawList) {
                        list.add(mapper.map(movieRaw));
                    }

                    return list;
                })
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<Movie> movieList) {
                        callback.onResult(movieList, null, nextPage);
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

    private void loadAfterRequest(LoadParams<Integer> params,
                                  LoadCallback<Integer, Movie> callback,
                                  int currentPage,
                                  int nextPage) {

        movieService.search(query, currentPage)
                .map(moviePageResponse -> {

                    List<Movie> list = new ArrayList<>();

                    for (MoviePageResponse.MovieRaw movieRaw : moviePageResponse.movieRawList) {
                        list.add(mapper.map(movieRaw));
                    }

                    return list;
                })
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<Movie> movieList) {
                        callback.onResult(movieList, nextPage);
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
}
