package com.purrprogramming.songify;

import com.purrprogramming.songify.models.Category;
import com.purrprogramming.songify.models.ExtendedInformation;
import com.purrprogramming.songify.models.Tag;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lance Gleason on 10/26/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

public interface MusicAPI {
    @GET("/api/1/tags")
    Observable<ArrayList<Tag>> getTags();

    @GET("/api/1/category/tag/{tagId}")
    Observable<ArrayList<Category>> getCategory(@Path("tagId") String id);

    @GET("/api/1/songs/multi")
    Observable<ArrayList<ExtendedInformation>> getMultiSongs(@Query(value="id", encoded = true) ArrayList<String> ids);
}
