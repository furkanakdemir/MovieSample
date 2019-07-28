package net.furkanakdemir.moviesample.data;

import java.util.List;

import io.reactivex.Observable;

public interface MovieRepository {

    Observable<List<Movie>> getMovies();
}
