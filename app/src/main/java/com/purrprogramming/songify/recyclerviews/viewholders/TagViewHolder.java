package com.purrprogramming.songify.recyclerviews.viewholders;

import android.support.v7.widget.RecyclerView;

import com.purrprogramming.songify.databinding.HolderTagBinding;
import com.purrprogramming.songify.models.Tag;

/**
 * Created by Lance Gleason on 10/29/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

public class TagViewHolder extends RecyclerView.ViewHolder {

    private final HolderTagBinding holderTagBinding;

    public TagViewHolder(HolderTagBinding holderTagBinding) {
        super(holderTagBinding.getRoot());
        this.holderTagBinding = holderTagBinding;

    }

    public void setTag(Tag tag) {
        holderTagBinding.setTag(tag);
        holderTagBinding.executePendingBindings();
    }
}
