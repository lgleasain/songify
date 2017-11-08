package com.purrprogramming.songify.dagger;

import com.purrprogramming.songify.dagger.modules.MockApiModule;

import org.junit.After;
import org.junit.Before;

import javax.inject.Inject;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class BaseTest {

    private BaseTestComponent component;

    @Inject
    MockWebServer mockWebServer;
    @Inject
    Dispatcher dispatcher;

    @Before
    public void setUp() throws Exception {

    }

    protected BaseTestComponent getComponent() {
        if (component == null) {
            component = DaggerBaseTestComponent.builder()
                    .mockApiModule(new MockApiModule())
                    .build();
        }

        return component;
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.setDispatcher(dispatcher);
        mockWebServer.shutdown();
    }

}
