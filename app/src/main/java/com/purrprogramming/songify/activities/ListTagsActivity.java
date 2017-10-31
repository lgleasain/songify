package com.purrprogramming.songify.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.purrprogramming.songify.R;
import com.purrprogramming.songify.dagger.ViewModelFactory;
import com.purrprogramming.songify.dagger.components.DaggerListTagsActivityComponent;
import com.purrprogramming.songify.dagger.components.ListTagsActivityComponent;
import com.purrprogramming.songify.dagger.modules.MockApiModule;
import com.purrprogramming.songify.databinding.ActivityListTagsBinding;
import com.purrprogramming.songify.recyclerviews.adapters.TagsAdapter;
import com.purrprogramming.songify.viewmodels.TagsViewModel;

import javax.inject.Inject;

/**
 * Created by Lance Gleason on 10/27/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

public class ListTagsActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private TagsViewModel tagsViewModel;

    private ActivityListTagsBinding activityListTagsBinding;

    private TagsAdapter tagsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListTagsBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_tags);
        ListTagsActivityComponent listTagsActivityComponent = DaggerListTagsActivityComponent.builder()
                .mockApiModule(new MockApiModule())
                .build();

        ListTagsActivity listTagsActivity = this;

        AsyncTask.execute(() -> {
                    listTagsActivityComponent.inject(listTagsActivity);
                    tagsViewModel = ViewModelProviders.of(listTagsActivity, viewModelFactory).get(TagsViewModel.class);
                    subscribeToDataChanges();
                    runOnUiThread(() -> {
                        createRecyclerAdapter();
                        tagsViewModel.fetchTags();
                    });
                }
        );
    }

    private void subscribeToDataChanges() {
        tagsViewModel.tags.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                tagsAdapter.setTags(tagsViewModel.tags.get());
            }
        });
    }

    private void createRecyclerAdapter() {
        LinearLayoutManager recyclerViewLinearLayoutManager = new LinearLayoutManager(this);
        activityListTagsBinding.tagsRecyclerView.setLayoutManager(recyclerViewLinearLayoutManager);
        tagsAdapter = new TagsAdapter(tagsViewModel.tags.get());
        activityListTagsBinding.tagsRecyclerView.setAdapter(tagsAdapter);
    }

}
