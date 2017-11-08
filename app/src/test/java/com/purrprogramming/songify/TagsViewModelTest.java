package com.purrprogramming.songify;

import com.purrprogramming.songify.dagger.BaseTest;
import com.purrprogramming.songify.models.Tag;
import com.purrprogramming.songify.viewmodels.TagsViewModel;

import org.junit.ClassRule;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class TagsViewModelTest extends BaseTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Override
    public void setUp() throws Exception {
        getComponent().inject(this);
        super.setUp();
    }

    @Test
    public void testGetTagsHas12Items() {
        TagsViewModel tagsViewModel = new TagsViewModel();
        getComponent().inject(tagsViewModel);
        tagsViewModel.fetchTags();
        ArrayList<Tag> tags = tagsViewModel.tags.get();

        assertEquals(12, tags.size());
    }

    @Test
    public void testGetTagsHasValidItems() {
        TagsViewModel tagsViewModel = new TagsViewModel();
        getComponent().inject(tagsViewModel);
        tagsViewModel.fetchTags();
        ArrayList<Tag> tags = tagsViewModel.tags.get();

        Tag firstItem = tags.get(0);
        Tag lastItem = tags.get(11);

        assertEquals( "Karen Zoid", firstItem.getTitle());
        assertEquals("The Police", lastItem.getTitle());
    }
}
