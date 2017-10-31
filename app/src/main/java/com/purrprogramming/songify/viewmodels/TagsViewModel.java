package com.purrprogramming.songify.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.purrprogramming.songify.MusicAPI;
import com.purrprogramming.songify.models.Tag;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lance Gleason on 10/27/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

public class TagsViewModel extends ViewModel {

    @Inject
    MusicAPI musicAPI;

    @Inject
    public TagsViewModel(){}

    public final ObservableField<ArrayList<Tag>> tags = new ObservableField<>();

    public void fetchTags(){
        musicAPI.getTags().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((ArrayList<Tag> tags) -> this.tags.set(tags));
    }
}
