package net.furkanakdemir.moviesample.ui;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import net.furkanakdemir.moviesample.R;
import net.furkanakdemir.moviesample.data.FakeMovieRepository;
import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieRepository;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment implements MovieAdapter.OnMovieCallback {

    @BindView(R.id.moviesRecyclerView)
    RecyclerView moviesRecyclerView;

    private MovieAdapter movieAdapter;

    private MovieRepository movieRepository;


    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    int getLayoutId() {
        return R.layout.fragment_movie_list;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieRepository = new FakeMovieRepository();


        movieAdapter = new MovieAdapter(this);
        moviesRecyclerView.setAdapter(movieAdapter);

        movieRepository.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Movie> movieList) {
                        movieAdapter.updateList(movieList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Timber.i("Name: %s", movie.getName());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_movieListFragment_to_movieDetailFragment);
    }
}
