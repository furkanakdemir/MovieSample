package net.furkanakdemir.moviesample.data;

import java.util.List;

import io.reactivex.Observable;

interface MovieRepository {

    Observable<List<Movie>> getMovies();
}
