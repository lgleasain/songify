package com.purrprogramming.songify.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.purrprogramming.songify.MusicAPI;
import com.purrprogramming.songify.models.Category;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryViewModel extends ViewModel{

    @Inject
    MusicAPI musicAPI;

    @Inject
    public CategoryViewModel() {
    }

    public final ObservableField<ArrayList<Category>> categoryItems = new ObservableField<>();

    public void fetchCategory(String id) {
        musicAPI.getCategory(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((ArrayList<Category> categories) -> this.categoryItems.set(categories), (Throwable onError) -> {
                    this.categoryItems.set(new ArrayList<>());
                });
    }
}
