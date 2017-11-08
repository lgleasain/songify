package com.purrprogramming.songify.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.purrprogramming.songify.dagger.ViewModelFactory;
import com.purrprogramming.songify.viewmodels.TagsViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
@Reusable
public class ListTagsActivityModule {

    @Provides
    ViewModel provideTagsViewModel(TagsViewModel tagsViewModel){
        return tagsViewModel;
    }

    @Provides
    ViewModelProvider.Factory provideTagsViewModelFactory(ViewModelFactory viewModelFactory){
        return viewModelFactory;
    }
}
