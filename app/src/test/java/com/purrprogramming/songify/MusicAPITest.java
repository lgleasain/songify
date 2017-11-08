package com.purrprogramming.songify;

import com.purrprogramming.songify.dagger.BaseTest;
import com.purrprogramming.songify.models.Category;
import com.purrprogramming.songify.models.ExtendedInformation;
import com.purrprogramming.songify.models.Tag;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MusicAPITest extends BaseTest {

    @Inject
    MusicAPI musicAPI;

    @Override
    public void setUp() throws Exception {
        getComponent().inject(this);
        super.setUp();
    }

    private ArrayList<Tag> makeValidTagsCall() {
        TestObserver observer = new TestObserver();
        musicAPI.getTags().subscribe(observer);
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        List rawList = observer.values();
        return (ArrayList<Tag>) rawList.get(0);
    }

    private ArrayList<Category> makeValidCatagoryCall(String id) {
        TestObserver observer = new TestObserver();
        musicAPI.getCategory(id).subscribe(observer);
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        List rawList = observer.values();
        return (ArrayList<Category>) rawList.get(0);
    }

    private ArrayList<ExtendedInformation> makeValidSongMulitCall(ArrayList<Integer> ids) {
        TestObserver observer = new TestObserver();
        musicAPI.getMultiSongs(ids).subscribe(observer);
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        List rawList = observer.values();
        return (ArrayList<ExtendedInformation>) rawList.get(0);
    }

    @Test
    public void testGetTagsObservableHas12Items() {
        ArrayList<Tag> tagList = makeValidTagsCall();
        assertEquals(12, tagList.size());
    }

    @Test
    public void testGetTagsObservableHasAValidItem() {
        ArrayList<Tag> tagList = makeValidTagsCall();
        Tag tagToTest = tagList.get(0);
        assertEquals(1, tagToTest.getId());
        assertEquals("Karen Zoid", tagToTest.getTitle());
    }

    @Test
    public void testGetCategoryObservableHas1Items() {
        ArrayList<Category> categoryList = makeValidCatagoryCall("1");
        assertEquals(1, categoryList.size());
    }

    @Test
    public void testGetCategoryObservableHasAValidCategory() {
        ArrayList<Category> categoryList = makeValidCatagoryCall("1");
        Category category = categoryList.get(0);
        assertEquals("Afrikaans", category.getName());
        assertEquals("1", category.getId());
        ArrayList<Integer> songIds = new ArrayList<>();
        songIds.add(1);
        songIds.add(2);
        assertEquals(songIds, category.getSongIds());
    }

    @Test
    public void testGetExtendedInformationObservableHas2Items() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ArrayList<ExtendedInformation> extendedInformationList = makeValidSongMulitCall(ids);
        assertEquals(2, extendedInformationList.size());
    }

    @Test
    public void testGetExtendedInformationObservableHasValidInformation() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ArrayList<ExtendedInformation> extendedInformationList = makeValidSongMulitCall(ids);
        ExtendedInformation extendedInformation = extendedInformationList.get(0);
        assertEquals(1, extendedInformation.getId());
        assertEquals("Afrikaners is Plesierig", extendedInformation.getName());
        assertEquals("artist", extendedInformation.getType());
        assertEquals("Karen Zoids #1 hit", extendedInformation.getDescription());
        assertEquals("https://gp1.wac.edgecastcdn.net/802892/http_public_production/artists/images/655005/original/hash:1466626743/1332185565_zoid_afrika.jpg?1466626743",
                extendedInformation.getCoverUrl());
        extendedInformation = extendedInformationList.get(1);
        assertEquals(2, extendedInformation.getId());
        assertEquals("Beautiful", extendedInformation.getName());
        assertEquals("artist", extendedInformation.getType());
        assertEquals("From Chasing the Sun", extendedInformation.getDescription());
        assertEquals("https://cps-static.rovicorp.com/3/JPG_500/MI0002/108/MI0002108485.jpg?partner=allrovi.com",
                extendedInformation.getCoverUrl());
    }
}
