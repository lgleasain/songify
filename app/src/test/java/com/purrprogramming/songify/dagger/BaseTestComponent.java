package com.purrprogramming.songify.dagger;

import com.purrprogramming.songify.MusicAPITest;
import com.purrprogramming.songify.TagsViewModelTest;
import com.purrprogramming.songify.dagger.modules.MockApiModule;
import com.purrprogramming.songify.viewmodels.TagsViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockApiModule.class})
public interface BaseTestComponent {

    void inject(MusicAPITest musicAPITest);

    void inject(TagsViewModelTest tagsViewModelTest);

    void inject(TagsViewModel tagsViewModel);

}
