package com.purrprogramming.songify.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.purrprogramming.songify.viewmodels.TagsViewModel;

import javax.inject.Inject;

/**
 * Created by Lance Gleason on 10/30/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final TagsViewModel tagsViewModel;

    @Inject
    public ViewModelFactory(TagsViewModel tagsViewModel) {
        this.tagsViewModel = tagsViewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TagsViewModel.class)) {
            //noinspection unchecked
            return (T) tagsViewModel;
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
