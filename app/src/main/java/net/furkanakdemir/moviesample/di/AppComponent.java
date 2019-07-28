package net.furkanakdemir.moviesample.di;


import net.furkanakdemir.moviesample.MovieApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilderModule.class})
public interface AppComponent extends AndroidInjector<MovieApplication> {

    @Component.Factory
    abstract class Factory implements AndroidInjector.Factory<MovieApplication> {
    }
}