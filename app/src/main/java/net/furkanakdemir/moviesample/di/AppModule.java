package net.furkanakdemir.moviesample.di;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideApplication(Application application) {
        return application;
    }
}
