package net.furkanakdemir.moviesample.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

import static net.furkanakdemir.moviesample.data.Movie.MovieBuilder.movieBuilder;

public class FakeMovieRepository implements MovieRepository {
    @Override
    public Observable<List<Movie>> getMovies() {

        List<Movie> list = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            list.add(movieBuilder().name("Movie " + x)
                    .releaseDate("201" + x)
                    .build());
        }
        return Observable.just(list);
    }

    @Override
    public Observable<List<Movie>> search(String query) {
        return Observable.just(Collections.emptyList());
    }
}
