package com.purrprogramming.songify.recyclerviews.viewholders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purrprogramming.songify.activities.ListCategoriesActivity;
import com.purrprogramming.songify.activities.ListSongsActivity;
import com.purrprogramming.songify.databinding.HolderCategoryItemBinding;
import com.purrprogramming.songify.models.Category;

public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

    private final HolderCategoryItemBinding holderCategoryItemBinding;
    private final ListCategoriesActivity context;

    public CategoryItemViewHolder(HolderCategoryItemBinding holderCategoryItemBinding, ListCategoriesActivity context) {
        super(holderCategoryItemBinding.getRoot());
        this.holderCategoryItemBinding = holderCategoryItemBinding;
        this.context = context;
    }

    public void setCategoryItem(Category category) {
        holderCategoryItemBinding.setCategory(category);
        holderCategoryItemBinding.executePendingBindings();
        holderCategoryItemBinding.categoryItem.getRootView().setOnClickListener((View v) -> {
            Intent intent = new Intent(context, ListSongsActivity.class);
            intent.putExtra(ListSongsActivity.SONG_IDS, category.getSongIds());
            intent.putExtra(ListSongsActivity.CATEGORY_NAME, category.getName());
            intent.putExtra(ListCategoriesActivity.TAG_NAME, context.getTagsName());
            context.startActivity(intent);
        });
    }

}
