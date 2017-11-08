package com.purrprogramming.songify.recyclerviews.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purrprogramming.songify.activities.ListCategoriesActivity;
import com.purrprogramming.songify.databinding.HolderTagBinding;
import com.purrprogramming.songify.models.Tag;

public class TagViewHolder extends RecyclerView.ViewHolder {

    private final HolderTagBinding holderTagBinding;
    private final Context context;

    public TagViewHolder(HolderTagBinding holderTagBinding, Context context) {
        super(holderTagBinding.getRoot());
        this.holderTagBinding = holderTagBinding;
        this.context = context;
    }

    public void setTag(Tag tag) {
        holderTagBinding.setTag(tag);
        holderTagBinding.executePendingBindings();
        holderTagBinding.tag.setOnClickListener((View v)->{
            Intent intent = new Intent(context, ListCategoriesActivity.class);
            intent.putExtra(ListCategoriesActivity.CATEGORY_ID, String.valueOf(tag.getId()));
            intent.putExtra(ListCategoriesActivity.TAG_NAME, String.valueOf(tag.getTitle()));
            context.startActivity(intent);
        });
    }
}
