package net.furkanakdemir.moviesample.network;

import net.furkanakdemir.moviesample.data.MoviePageResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/3/search/movie?api_key=2696829a81b1b5827d515ff121700838")
    Observable<MoviePageResponse> search(
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("/3/discover/movie?api_key=2696829a81b1b5827d515ff121700838")
    Observable<MoviePageResponse> discover(
            @Query("page") int page
    );

}
