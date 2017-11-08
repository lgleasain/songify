package com.purrprogramming.songify.recyclerviews.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purrprogramming.songify.databinding.HolderSongBinding;
import com.purrprogramming.songify.models.ExtendedInformation;
import com.purrprogramming.songify.recyclerviews.viewholders.SongViewHolder;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongViewHolder>{

    private ArrayList<ExtendedInformation> songs;

    public SongsAdapter(ArrayList<ExtendedInformation> songs) {
        this.songs = songs;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HolderSongBinding holderSongBinding = HolderSongBinding.inflate(layoutInflater, parent, false);
        return new SongViewHolder(holderSongBinding);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Log.i("getting position ", String.valueOf(position));
        holder.setSong(songs.get(position));
    }

    @Override
    public int getItemCount() {
        if (songs == null) {
            return 0;
        } else {
            return songs.size();
        }
    }

    public void setSongs(ArrayList<ExtendedInformation> songs) {
        if (songs != null) {
            this.songs = songs;
            notifyDataSetChanged();
        }
    }

}