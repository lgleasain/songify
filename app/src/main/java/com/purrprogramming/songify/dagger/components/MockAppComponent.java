package com.purrprogramming.songify.dagger.components;

import com.purrprogramming.songify.dagger.modules.AppModule;
import com.purrprogramming.songify.dagger.modules.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MockApiModule.class})
public interface MockAppComponent {
}
