package com.purrprogramming.songify;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.purrprogramming.songify.activities.ListCategoriesActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
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

public class ListCategoriesActivityTagIdTwoThreeResultsTest {

    IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<ListCategoriesActivity> activityRule = new ActivityTestRule<ListCategoriesActivity>(ListCategoriesActivity.class) {
            @Override
            protected Intent getActivityIntent() {
                Context targetContext = InstrumentationRegistry.getInstrumentation()
                        .getTargetContext();
                Intent result = new Intent(targetContext, ListCategoriesActivity.class);
                result.putExtra(ListCategoriesActivity.CATEGORY_ID, "2");
                result.putExtra(ListCategoriesActivity.TAG_NAME, "AKing");
                return result;
            }
        };

    @Before
    public void setupTestForCategory() {
        idlingResource = activityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void testCorrectTitlebarShown() throws Exception {
        onView(allOf(isDescendantOfA(withClassName(containsString("ActionBarContainer"))), withText("AKing -> Categories"))).check(matches(isDisplayed()));
    }

    @Test
    public void testCategoryNameIsShown() throws Exception {
        onView(withId(R.id.categoryRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText("Against All Odds")).check(matches(isDisplayed()));
        onView(withText("Morning After")).check(matches(isDisplayed()));
        onView(withText("Dutch Courage")).check(matches(isDisplayed()));
        onView(withText("The Police")).check(doesNotExist());
    }

    @Test
    public void testClickingOnCategoryGoesToSongs() throws Exception {
        onView(withText("Morning After")).perform(click());
        onView(allOf(isDescendantOfA(withClassName(containsString("ActionBarContainer"))), withText("AKing -> Morning After -> Songs"))).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }

}
