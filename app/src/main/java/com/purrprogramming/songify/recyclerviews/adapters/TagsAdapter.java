package com.purrprogramming.songify.recyclerviews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purrprogramming.songify.databinding.HolderTagBinding;
import com.purrprogramming.songify.models.Tag;
import com.purrprogramming.songify.recyclerviews.viewholders.TagViewHolder;

import java.util.ArrayList;

public class TagsAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private ArrayList<Tag> tags;
    private Context context;

    public TagsAdapter(ArrayList<Tag> tags, Context context) {
        this.tags = tags;
        this.context = context;
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HolderTagBinding holderTagBinding = HolderTagBinding.inflate(layoutInflater, parent, false);
        return new TagViewHolder(holderTagBinding, context);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        Log.i("getting position ", String.valueOf(position));
        holder.setTag(tags.get(position));
    }

    @Override
    public int getItemCount() {
        if (tags == null) {
            return 0;
        } else {
            return tags.size();
        }
    }

    public void setTags(ArrayList<Tag> tags) {
        if (tags != null) {
            this.tags = tags;
            notifyDataSetChanged();
        }
    }

}
