package net.furkanakdemir.moviesample.di;


import net.furkanakdemir.moviesample.MovieApplication;
import net.furkanakdemir.moviesample.ui.MovieModule;
import net.furkanakdemir.moviesample.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
        AppModule.class, ActivityBuilderModule.class, ViewModelModule.class, MovieModule.class})
public interface AppComponent extends AndroidInjector<MovieApplication> {

    @Component.Factory
    abstract class Factory implements AndroidInjector.Factory<MovieApplication> {
    }
}