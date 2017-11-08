package com.purrprogramming.songify.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Category {

    private String id;

    private String name;

    @SerializedName("song_ids")
    private ArrayList<Integer> songIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getSongIds() {
        return songIds;
    }

    public void setSongIds(ArrayList<Integer> songIds) {
        this.songIds = songIds;
    }
}
