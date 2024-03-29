package net.furkanakdemir.moviesample.di;


import net.furkanakdemir.moviesample.ui.MovieDetailFragment;
import net.furkanakdemir.moviesample.ui.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract MovieListFragment bindMovieListFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract MovieDetailFragment bindMovieDetailFragment();
}
