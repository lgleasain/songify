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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;

public class ListSongsActivityForCategoryTag1Test {

    IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<ListSongsActivity> activityRule =
            new ActivityTestRule<ListSongsActivity>(ListSongsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, ListSongsActivity.class);
                    ArrayList<String> songIds = new ArrayList<>(Arrays.asList("1", "2"));
                    result.putExtra(ListSongsActivity.SONG_IDS, songIds);
                    result.putExtra(ListCategoriesActivity.TAG_NAME, "Karen Zoid");
                    result.putExtra(ListSongsActivity.CATEGORY_NAME, "Afrikaans");
                    return result;
                }
            };

    @Before
    public void registerIdlingResource() {
        idlingResource = activityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Assert.assertEquals("com.purrprogramming.songify", appContext.getPackageName());
    }

    @Test
    public void testCorrectTitlebarShown() throws Exception {
        onView(allOf(isDescendantOfA(withClassName(containsString("ActionBarContainer"))), withText("Karen Zoid -> Afrikaans -> Songs"))).check(matches(isDisplayed()));
    }

    @Test
    public void testSongsAreShown() throws Exception {
        onView(withId(R.id.songsRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText("Afrikaners is Plesierig")).check(matches(isDisplayed()));
        onView(withText("Beautiful")).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}
