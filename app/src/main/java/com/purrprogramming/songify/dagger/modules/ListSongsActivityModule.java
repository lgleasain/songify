package com.purrprogramming.songify.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.purrprogramming.songify.dagger.ViewModelFactory;
import com.purrprogramming.songify.viewmodels.SongsViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
@Reusable
public class ListSongsActivityModule {

    @Provides
    ViewModel provideSongsViewModel(SongsViewModel songsViewModel) {
        return songsViewModel;
    }

    @Provides
    ViewModelProvider.Factory provideSongsViewModelFactory(ViewModelFactory viewModelFactory) {
        return viewModelFactory;
    }
}

