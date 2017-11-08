package com.purrprogramming.songify.recyclerviews.viewholders;

import android.support.v7.widget.RecyclerView;

import com.purrprogramming.songify.databinding.HolderSongBinding;
import com.purrprogramming.songify.models.ExtendedInformation;

public class SongViewHolder extends RecyclerView.ViewHolder {

    private final HolderSongBinding holderSongBinding;

    public SongViewHolder(HolderSongBinding holderSongBinding) {
        super(holderSongBinding.getRoot());
        this.holderSongBinding = holderSongBinding;
    }

    public void setSong(ExtendedInformation extendedInformation) {
        holderSongBinding.setSong(extendedInformation);
        holderSongBinding.executePendingBindings();
    }
}
