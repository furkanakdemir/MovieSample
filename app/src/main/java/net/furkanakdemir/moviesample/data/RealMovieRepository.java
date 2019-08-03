package net.furkanakdemir.moviesample.data;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import net.furkanakdemir.moviesample.network.MovieService;
import net.furkanakdemir.moviesample.paging.MovieDataSourceFactory;
import net.furkanakdemir.moviesample.paging.MovieSearchDataSourceFactory;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RealMovieRepository implements MovieRepository {

    private final MovieDataSourceFactory factory;
    private MovieService movieService;
    private MovieDomainMapper mapper;
    private CompositeDisposable disposables;

    @Inject
    public RealMovieRepository(MovieService movieService,
                               MovieDomainMapper mapper,
                               CompositeDisposable disposables) {

        this.movieService = movieService;
        this.mapper = mapper;
        this.disposables = disposables;


        factory = new MovieDataSourceFactory(movieService, mapper, disposables);
    }


    @Override
    public void refresh() {
        if (factory.getSource().getValue() != null) {
            factory.getSource().getValue().invalidate();
        }
    }

    @Override
    public LiveData<PagedList<Movie>> getMoviesLiveData() {

        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20).setPageSize(20).build();
        return new LivePagedListBuilder<>(factory, config).build();
    }


    @Override
    public LiveData<PagedList<Movie>> getMovieSearchLiveData(String query) {
        MovieSearchDataSourceFactory searchDataSourceFactory = new MovieSearchDataSourceFactory(movieService, mapper, disposables, query);

        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20).setPageSize(20).build();
        return new LivePagedListBuilder<>(searchDataSourceFactory, config).build();
    }
}



