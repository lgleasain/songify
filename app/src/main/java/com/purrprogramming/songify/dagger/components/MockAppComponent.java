package com.purrprogramming.songify.dagger.components;

import com.purrprogramming.songify.dagger.modules.AppModule;
import com.purrprogramming.songify.dagger.modules.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lance Gleason on 10/30/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

@Singleton
@Component(modules = {AppModule.class, MockApiModule.class})
public interface MockAppComponent {
}
