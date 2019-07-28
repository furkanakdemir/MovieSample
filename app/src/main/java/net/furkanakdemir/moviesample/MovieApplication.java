package net.furkanakdemir.moviesample;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class MovieApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        setupTimber();

    }

    private void setupTimber() {
        Timber.uprootAll();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
