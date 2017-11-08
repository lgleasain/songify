package com.purrprogramming.songify.recyclerviews.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purrprogramming.songify.activities.ListCategoriesActivity;
import com.purrprogramming.songify.databinding.HolderCategoryItemBinding;
import com.purrprogramming.songify.models.Category;
import com.purrprogramming.songify.recyclerviews.viewholders.CategoryItemViewHolder;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryItemViewHolder> {

    private ArrayList<Category> categoryItems;
    private final ListCategoriesActivity context;

    public CategoryAdapter(ArrayList<Category> categoryItems, ListCategoriesActivity context) {
        this.categoryItems = categoryItems;
        this.context = context;
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HolderCategoryItemBinding holderCategoryItemBinding = HolderCategoryItemBinding.inflate(layoutInflater, parent, false);
        return new CategoryItemViewHolder(holderCategoryItemBinding, context);
    }

    @Override
    public void onBindViewHolder(CategoryItemViewHolder holder, int position) {
        Log.i("getting position ", String.valueOf(position));
        holder.setCategoryItem(categoryItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (categoryItems == null) {
            return 0;
        } else {
            return categoryItems.size();
        }
    }

    public void setTags(ArrayList<Category> categoryItems) {
        if (categoryItems != null) {
            this.categoryItems = categoryItems;
            notifyDataSetChanged();
        }
    }

}
