package com.purrprogramming.songify.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.purrprogramming.songify.R;
import com.purrprogramming.songify.dagger.ViewModelFactory;
import com.purrprogramming.songify.dagger.components.DaggerListSongsActivityComponent;
import com.purrprogramming.songify.dagger.components.ListSongsActivityComponent;
import com.purrprogramming.songify.dagger.modules.MockApiModule;
import com.purrprogramming.songify.databinding.ActivityListSongsBinding;
import com.purrprogramming.songify.recyclerviews.adapters.SongsAdapter;
import com.purrprogramming.songify.viewmodels.SongsViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class ListSongsActivity extends BaseActivity {

    public static final String SONG_IDS = "SONG_IDS";

    @Inject
    ViewModelFactory viewModelFactory;

    private SongsViewModel songsViewModel;

    private ActivityListSongsBinding activityListSongsBinding;

    private SongsAdapter songsAdapter;

    public final static String CATEGORY_NAME = "CATEGORY_NAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for espresso tests
        setupIdlingResource();

        activityListSongsBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_songs);
        ListSongsActivityComponent listSongsActivityComponent = DaggerListSongsActivityComponent.builder()
                .mockApiModule(new MockApiModule())
                .build();

        ListSongsActivity listSongsActivity = this;

        Intent intent = getIntent();
        ArrayList<Integer> songIds = intent.getIntegerArrayListExtra(SONG_IDS);
        String tagName = intent.getStringExtra(ListCategoriesActivity.TAG_NAME);
        String categoryName = intent.getStringExtra(CATEGORY_NAME);

        AsyncTask.execute(() -> {
                    listSongsActivityComponent.inject(listSongsActivity);
                    songsViewModel = ViewModelProviders.of(listSongsActivity, viewModelFactory).get(SongsViewModel.class);
                    subscribeToDataChanges();
                    runOnUiThread(() -> {
                        createRecyclerAdapter();
                        songsViewModel.fetchCategory(songIds);
                    });
                }
        );

        String title = tagName + " -> " + categoryName + " -> " + getString(R.string.songs);
        getSupportActionBar().setTitle(title);
    }

    private void subscribeToDataChanges() {
        songsViewModel.songs.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                songsAdapter.setSongs(songsViewModel.songs.get());

                // for espresso tests
                idlingResource.setIdleState(true);
                // end espresso
            }
        });
    }

    private void createRecyclerAdapter() {
        LinearLayoutManager recyclerViewLinearLayoutManager = new LinearLayoutManager(this);
        activityListSongsBinding.songsRecyclerView.setLayoutManager(recyclerViewLinearLayoutManager);
        songsAdapter = new SongsAdapter(songsViewModel.songs.get());
        activityListSongsBinding.songsRecyclerView.setAdapter(songsAdapter);
    }
}
