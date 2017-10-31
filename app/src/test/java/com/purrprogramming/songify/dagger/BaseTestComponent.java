package com.purrprogramming.songify.dagger;

import com.purrprogramming.songify.MusicAPITest;
import com.purrprogramming.songify.TagsViewModelTest;
import com.purrprogramming.songify.dagger.modules.MockApiModule;
import com.purrprogramming.songify.viewmodels.TagsViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lance Gleason on 10/28/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

@Singleton
@Component(modules = {MockApiModule.class})
public interface BaseTestComponent {

    void inject(MusicAPITest musicAPITest);

    void inject(TagsViewModelTest tagsViewModelTest);

    void inject(TagsViewModel tagsViewModel);

}
