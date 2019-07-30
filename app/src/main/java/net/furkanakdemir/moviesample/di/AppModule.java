package net.furkanakdemir.moviesample.di;


import android.content.Context;

import net.furkanakdemir.moviesample.MovieApplication;
import net.furkanakdemir.moviesample.image.GlideImageLoader;
import net.furkanakdemir.moviesample.image.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(MovieApplication application) {
        return application;
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Context context) {
        return new GlideImageLoader(context);
    }


}
