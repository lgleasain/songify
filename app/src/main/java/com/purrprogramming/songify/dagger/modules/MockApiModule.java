package com.purrprogramming.songify.dagger.modules;

import android.support.annotation.NonNull;

import com.purrprogramming.songify.utils.LocalResponseDispatcher;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by Lance Gleason on 10/28/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */
@Module
public class MockApiModule extends ApiModule {
    @Override
    protected void addOkHttpConfig(OkHttpClient.Builder builder) {
        builder.readTimeout(2, TimeUnit.SECONDS);
    }

    private final LocalResponseDispatcher dispatcher;
    private final MockWebServer mockWebServer;

    public MockApiModule() {
        dispatcher = new LocalResponseDispatcher();
        this.mockWebServer = getMockWebServer(dispatcher);
    }

    @Override
    protected String getBaseUrl() {
        return mockWebServer.url("/").toString();
    }

    @Provides
    @Singleton
    public MockWebServer provideDefaultMockWebServer(Dispatcher dispatcher) {
        return mockWebServer;
    }

    @NonNull
    private MockWebServer getMockWebServer(Dispatcher dispatcher) {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(dispatcher);
        return mockWebServer;
    }


    @Provides
    @Singleton
    public Dispatcher getTestDispatcher() {
        return dispatcher;
    }

}
