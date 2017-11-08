package com.purrprogramming.songify;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.purrprogramming.songify.activities.ListCategoriesActivity;
import com.purrprogramming.songify.activities.ListSongsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;

public class ListSongsActivityForCategoryTagWithNoResultsTest {

    IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<ListSongsActivity> activityRule =
            new ActivityTestRule<ListSongsActivity>(ListSongsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, ListSongsActivity.class);
                    ArrayList<String> songIds = new ArrayList<>(Arrays.asList("34"));
                    result.putExtra(ListSongsActivity.SONG_IDS, songIds);
                    result.putExtra(ListCategoriesActivity.TAG_NAME, "Dread Zepplin");
                    result.putExtra(ListSongsActivity.CATEGORY_NAME, "Greatest Hits");
                    return result;
                }
            };

    @Before
    public void registerIdlingResource() {
        idlingResource = activityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void testCorrectTitlebarShown() throws Exception {
        onView(allOf(isDescendantOfA(withClassName(containsString("ActionBarContainer"))), withText("Dread Zepplin -> Greatest Hits -> Songs"))).check(matches(isDisplayed()));
    }

    @Test
    public void testNoSongsAreShown() throws Exception {
        onView(withId(R.id.songsRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.song_layout)).check(doesNotExist());
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }

}


