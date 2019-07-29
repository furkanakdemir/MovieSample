package net.furkanakdemir.moviesample.ui;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import net.furkanakdemir.moviesample.R;
import net.furkanakdemir.moviesample.data.Movie;
import net.furkanakdemir.moviesample.data.MovieDomainMapper;
import net.furkanakdemir.moviesample.data.MovieRepository;
import net.furkanakdemir.moviesample.data.RealMovieRepository;
import net.furkanakdemir.moviesample.network.MovieService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment implements MovieAdapter.OnMovieCallback {

    private static final long DELAY_SEARCH_IN_MS = 800;


    @BindView(R.id.moviesRecyclerView)
    RecyclerView moviesRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MovieAdapter movieAdapter;

    private MovieRepository movieRepository;

    PublishSubject<String> publishSubject = PublishSubject.create();


    @Inject
    MovieService movieService;

    @Inject
    MovieDomainMapper movieDomainMapper;


    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    int getLayoutId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_list, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        setupSearchView(searchView);
    }

    @SuppressLint("CheckResult")
    private void setupSearchView(@NonNull SearchView searchView) {

        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);

        publishSubject
                .debounce(DELAY_SEARCH_IN_MS, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .skip(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String query) {
                        Timber.i("Query %s", query);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                publishSubject.onNext(newText);

                return false;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();


        movieRepository = new RealMovieRepository(movieService, movieDomainMapper);


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
                        Timber.e(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(null);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Timber.i("Name: %s", movie.getName());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_movieListFragment_to_movieDetailFragment);
    }
}
