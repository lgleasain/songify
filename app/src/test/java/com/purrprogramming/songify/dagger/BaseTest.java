package com.purrprogramming.songify.dagger;

import android.support.annotation.NonNull;

import com.purrprogramming.songify.base.BaseSchedulerProvider;
import com.purrprogramming.songify.dagger.modules.MockApiModule;

import org.junit.After;
import org.junit.Before;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.QueueDispatcher;

/**
 * Created by Lance Gleason on 10/28/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

public class BaseTest {

    private BaseTestComponent component;

    @Inject
    MockWebServer mockWebServer;
    @Inject
    Dispatcher dispatcher;


    protected MockWebServer getErrorMockWebServer() {
        mockWebServer.setDispatcher(new QueueDispatcher());
        return mockWebServer;
    }

    protected MockWebServer getMockWebServer() {
        return mockWebServer;
    }


    protected TestScheduler testScheduler = new TestScheduler();

    protected BaseSchedulerProvider schedulersProvider = new BaseSchedulerProvider() {

        @NonNull
        @Override
        public Scheduler io() {
            return testScheduler;
        }

        @NonNull
        @Override
        public Scheduler ui() {
            return testScheduler;
        }

        @NonNull
        @Override
        public Scheduler computation() {
            return testScheduler;
        }

    };


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
