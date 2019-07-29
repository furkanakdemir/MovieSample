package net.furkanakdemir.moviesample.di;


import net.furkanakdemir.moviesample.MainActivity;
import net.furkanakdemir.moviesample.network.NetworkModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {FragmentBuilderModule.class, NetworkModule.class})
    abstract MainActivity bindMainActivity();
}
