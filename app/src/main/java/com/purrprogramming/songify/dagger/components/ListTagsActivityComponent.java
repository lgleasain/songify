package com.purrprogramming.songify.dagger.components;

import com.purrprogramming.songify.activities.ListTagsActivity;
import com.purrprogramming.songify.dagger.modules.ListTagsActivityModule;
import com.purrprogramming.songify.dagger.modules.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lance Gleason on 10/27/17 of Polyglot Programming LLC.
 * Web: http://www.polygotprogramminginc.com
 * Twitter: @lgleasain
 * Github: @lgleasain
 */

@Singleton
@Component(
        modules = {ListTagsActivityModule.class, MockApiModule.class})
public interface ListTagsActivityComponent {
    void inject(ListTagsActivity mainActivity);
}
