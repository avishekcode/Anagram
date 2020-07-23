package com.audition;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

public class AnagramAppTest {
    @Test
    public void findAll2WordAnagramsOnlyTest() {
        AnagramApp anagramApp = new AnagramApp();
        anagramApp.sortedDictionary = new WordList();
        anagramApp.numberOfWordsInAnagram = 2;
        anagramApp.twoWordAnagramsOnly = true;
        Set<Set<String>> anagrams = anagramApp.findAllAnagrams("documenting");
        Assert.assertEquals(1, anagrams.size());
    }

    @Test
    public void findAllUpto3WordAnagramsTest() {
        AnagramApp anagramApp = new AnagramApp();
        anagramApp.sortedDictionary = new WordList();
        anagramApp.numberOfWordsInAnagram = 3;
        anagramApp.twoWordAnagramsOnly = false;
        Set<Set<String>> anagrams = anagramApp.findAllAnagrams("documenting");
        Assert.assertNotNull(anagrams);
        Assert.assertEquals(3, anagrams.size());
    }

    @Test
    public void findAllUpto2WordAnagramsTest() {
        AnagramApp anagramApp = new AnagramApp();
        anagramApp.sortedDictionary = new WordList();
        anagramApp.numberOfWordsInAnagram = 2;
        anagramApp.twoWordAnagramsOnly = false;
        Set<Set<String>> anagrams = anagramApp.findAllAnagrams("documenting");
        Assert.assertEquals(2, anagrams.size());
    }
}
