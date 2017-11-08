package com.purrprogramming.songify.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.purrprogramming.songify.activities.idlingresource.SimpleIdlingResource;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    protected SimpleIdlingResource idlingResource;

    /**
     * For testing
     */
    protected void setupIdlingResource() {
        idlingResource = new SimpleIdlingResource();
        idlingResource.setIdleState(false);
    }


    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }

}
