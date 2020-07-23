package com.audition;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordListTest {
    private WordList sortedWordDictionary;

    @Before
    public void setUp() {
        sortedWordDictionary = new WordList();
    }

    @Test
    public void fileLoadTest() {
        sortedWordDictionary.loadDictionaryWithSubsets("documenting", 2);
        Assert.assertTrue(sortedWordDictionary.isDictionaryLoaded());
    }
}