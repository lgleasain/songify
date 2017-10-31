package com.purrprogramming.songify.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lance Gleason on 10/23/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

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
