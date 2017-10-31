package com.purrprogramming.songify.dagger.components;

import com.purrprogramming.songify.MusicAPI;
import com.purrprogramming.songify.base.BaseSchedulerProvider;
import com.purrprogramming.songify.dagger.modules.ApiModule;
import com.purrprogramming.songify.dagger.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lance Gleason on 10/27/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
        BaseSchedulerProvider getSchedulerProvider();

        MusicAPI provideMusicAPI();
}
