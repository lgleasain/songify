package com.purrprogramming.songify;

import android.app.Application;

import com.purrprogramming.songify.dagger.components.DaggerMockAppComponent;
import com.purrprogramming.songify.dagger.components.MockAppComponent;

import javax.inject.Inject;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class SongifyApplication extends Application {

    MockAppComponent appComponent;

    // for the mock server
    @Inject
    MockWebServer mockWebServer;
    @Inject
    Dispatcher dispatcher;


    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        this.appComponent = DaggerMockAppComponent.builder()
                .build();
    }

    public MockAppComponent getAppComponent() {
        return appComponent;
    }
}
