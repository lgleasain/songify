package com.purrprogramming.songify.dagger.components;

import com.purrprogramming.songify.activities.ListCategoriesActivity;
import com.purrprogramming.songify.dagger.modules.ListCategoryActivityModule;
import com.purrprogramming.songify.dagger.modules.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {ListCategoryActivityModule.class, MockApiModule.class})
public interface ListCategoryActivityComponent {
    void inject(ListCategoriesActivity mainActivity);
}
