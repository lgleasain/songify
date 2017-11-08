package com.purrprogramming.songify;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.purrprogramming.songify.activities.ListTagsActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class ListTagsActivityTest {

    IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<ListTagsActivity> mActivityRule =
            new ActivityTestRule<>(ListTagsActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Assert.assertEquals("com.purrprogramming.songify", appContext.getPackageName());
    }

    @Test
    public void testCorrectTitlebarShown() throws Exception {
        onView(allOf(isDescendantOfA(withClassName(containsString("ActionBarContainer"))), withText("Songify - Tags"))).check(matches(isDisplayed()));
    }

    @Test
    public void testTagsShown() throws Exception {
        onView(withId(R.id.tagsRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText("Karen Zoid")).check(matches(isDisplayed()));
        onView(withText("The Police")).check(matches(isDisplayed()));
        onView(withText("U2")).check(matches(isDisplayed()));
    }

    @Test
    public void testClickingOnTagGoesToCategory() throws Exception {
        onView(withText("Karen Zoid")).perform(click());
        onView(allOf(isDescendantOfA(withClassName(containsString("ActionBarContainer"))), withText("Karen Zoid -> Categories"))).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
