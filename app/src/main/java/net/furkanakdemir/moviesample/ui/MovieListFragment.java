package net.furkanakdemir.moviesample.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.furkanakdemir.moviesample.R;
import net.furkanakdemir.moviesample.data.FakeMovieRepository;
import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends DaggerFragment {

    @BindView(R.id.moviesRecyclerView)
    RecyclerView moviesRecyclerView;

    private MovieAdapter movieAdapter;

    private MovieRepository movieRepository;


    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieRepository = new FakeMovieRepository();

        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);


        movieAdapter = new MovieAdapter();
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




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_movieListFragment_to_movieDetailFragment);
            }
        });
    }
}
