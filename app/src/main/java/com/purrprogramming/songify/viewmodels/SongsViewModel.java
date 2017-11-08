package com.purrprogramming.songify.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.purrprogramming.songify.MusicAPI;
import com.purrprogramming.songify.models.ExtendedInformation;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SongsViewModel extends ViewModel {

    @Inject
    MusicAPI musicAPI;

    @Inject
    public SongsViewModel() {
    }

    public final ObservableField<ArrayList<ExtendedInformation>> songs = new ObservableField<>();

    public void fetchCategory(ArrayList<Integer> ids) {
        musicAPI.getMultiSongs(ids).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((ArrayList<ExtendedInformation> songs) -> this.songs.set(songs),
                        (Throwable onError) -> {
                            this.songs.set(new ArrayList<>());
                        });
    }
}
