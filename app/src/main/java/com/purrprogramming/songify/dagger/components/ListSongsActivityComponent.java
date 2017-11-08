package com.purrprogramming.songify.dagger.components;

import com.purrprogramming.songify.activities.ListSongsActivity;
import com.purrprogramming.songify.dagger.modules.ListSongsActivityModule;
import com.purrprogramming.songify.dagger.modules.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {ListSongsActivityModule.class, MockApiModule.class})
public interface ListSongsActivityComponent {
    void inject(ListSongsActivity mainActivity);
}
