package com.purrprogramming.songify.dagger.components;

import com.purrprogramming.songify.activities.ListTagsActivity;
import com.purrprogramming.songify.dagger.modules.ListTagsActivityModule;
import com.purrprogramming.songify.dagger.modules.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {ListTagsActivityModule.class, MockApiModule.class})
public interface ListTagsActivityComponent {
    void inject(ListTagsActivity mainActivity);
}
