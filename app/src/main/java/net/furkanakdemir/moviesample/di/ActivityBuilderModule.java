package net.furkanakdemir.moviesample.di;


import net.furkanakdemir.moviesample.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity bindMainActivity();
}
