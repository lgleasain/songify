package com.purrprogramming.songify.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.purrprogramming.songify.dagger.ViewModelFactory;
import com.purrprogramming.songify.viewmodels.CategoryViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
@Reusable
public class ListCategoryActivityModule {

    @Provides
    ViewModel provideCategoryViewModel(CategoryViewModel categoryViewModel){
        return categoryViewModel;
    }

    @Provides
    ViewModelProvider.Factory provideTagsViewModelFactory(ViewModelFactory viewModelFactory){
        return viewModelFactory;
    }
}
