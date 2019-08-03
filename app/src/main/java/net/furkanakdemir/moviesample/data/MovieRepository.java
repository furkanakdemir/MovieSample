package net.furkanakdemir.moviesample.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public interface MovieRepository {

    void refresh();

    LiveData<PagedList<Movie>> getMoviesLiveData();

    LiveData<PagedList<Movie>> getMovieSearchLiveData(String query);

}
