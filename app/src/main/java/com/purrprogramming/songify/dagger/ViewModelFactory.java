package com.purrprogramming.songify.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.purrprogramming.songify.viewmodels.CategoryViewModel;
import com.purrprogramming.songify.viewmodels.SongsViewModel;
import com.purrprogramming.songify.viewmodels.TagsViewModel;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final TagsViewModel tagsViewModel;

    private final CategoryViewModel categoryViewModel;

    private final SongsViewModel songsViewModel;

    @Inject
    public ViewModelFactory(TagsViewModel tagsViewModel, CategoryViewModel categoryViewModel, SongsViewModel songsViewModel) {
        this.tagsViewModel = tagsViewModel;
        this.categoryViewModel = categoryViewModel;
        this.songsViewModel = songsViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TagsViewModel.class)) {
            //noinspection unchecked
            return (T) tagsViewModel;
        } else if (modelClass.isAssignableFrom(CategoryViewModel.class)) {
            return (T) categoryViewModel;
        } else if (modelClass.isAssignableFrom(SongsViewModel.class)) {
            return (T) songsViewModel;
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
