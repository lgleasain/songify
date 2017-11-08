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
import com.purrprogramming.songify.dagger.components.DaggerListCategoryActivityComponent;
import com.purrprogramming.songify.dagger.components.ListCategoryActivityComponent;
import com.purrprogramming.songify.dagger.modules.MockApiModule;
import com.purrprogramming.songify.databinding.ActivityListCategoryBinding;
import com.purrprogramming.songify.recyclerviews.adapters.CategoryAdapter;
import com.purrprogramming.songify.viewmodels.CategoryViewModel;

import javax.inject.Inject;

public class ListCategoriesActivity extends BaseActivity {

    public static final String CATEGORY_ID = "CATEGORY_ID";
    public static final String TAG_NAME = "TAG_NAME";

    @Inject
    ViewModelFactory viewModelFactory;

    private CategoryViewModel categoryViewModel;

    private ActivityListCategoryBinding activityListCategoryBinding;

    private CategoryAdapter categoryAdapter;

    private String tagsName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for espresso tests
        setupIdlingResource();

        activityListCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_category);
        ListCategoryActivityComponent listCategoryActivityComponent = DaggerListCategoryActivityComponent.builder()
                .mockApiModule(new MockApiModule())
                .build();

        ListCategoriesActivity listCategoriesActivity = this;

        Intent intent = getIntent();
        String categoryId = intent.getStringExtra(CATEGORY_ID);
        tagsName = intent.getStringExtra(TAG_NAME);

        AsyncTask.execute(() -> {
                    listCategoryActivityComponent.inject(listCategoriesActivity);
                    categoryViewModel = ViewModelProviders.of(listCategoriesActivity, viewModelFactory).get(CategoryViewModel.class);
                    subscribeToDataChanges();
                    runOnUiThread(() -> {
                        createRecyclerAdapter();
                        categoryViewModel.fetchCategory(categoryId);
                    });
                }
        );

        String title = tagsName  + " -> " + getString(R.string.categories);
        getSupportActionBar().setTitle(title);
    }

    private void subscribeToDataChanges() {
        categoryViewModel.categoryItems.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                categoryAdapter.setTags(categoryViewModel.categoryItems.get());

                // for espresso tests
                idlingResource.setIdleState(true);
                // end espresso
            }
        });
    }

    private void createRecyclerAdapter() {
        LinearLayoutManager recyclerViewLinearLayoutManager = new LinearLayoutManager(this);
        activityListCategoryBinding.categoryRecyclerView.setLayoutManager(recyclerViewLinearLayoutManager);
        categoryAdapter = new CategoryAdapter(categoryViewModel.categoryItems.get(), this);
        activityListCategoryBinding.categoryRecyclerView.setAdapter(categoryAdapter);
    }

    public String getTagsName() {
        return tagsName;
    }
}
