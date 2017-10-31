package com.purrprogramming.songify.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.purrprogramming.songify.dagger.ViewModelFactory;
import com.purrprogramming.songify.viewmodels.TagsViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Created by Lance Gleason on 10/27/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

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
