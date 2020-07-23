package com.audition;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnagramApp {
    public int numberOfWordsInAnagram = 2;
    public boolean twoWordAnagramsOnly = true;
    private int minWordSize = 2;
    public WordList sortedDictionary;
    public String anagramWords = "documenting";

    public static void main(String[] args) {
        AnagramApp anagramApp = new AnagramApp();
        anagramApp.sortedDictionary = new WordList();
        System.out.println("All the anagrams for \"" + anagramApp.anagramWords + "\" are:");
        Set<Set<String>> anagrams = anagramApp.findAllAnagrams(anagramApp.anagramWords);
        if (anagrams == null || anagrams.isEmpty()) {
            System.out.println("\nNo anagrams found\n");
        } else {
            System.out.println("\n" + anagrams.size() + " anagram(s) found\n");
        }
    }

    public Set<Set<String>> findAllAnagrams(String wordString) {
        Set<Set<String>> anagramsSet = new HashSet<Set<String>>();
        sortedDictionary.loadDictionaryWithSubsets(wordString, minWordSize);
        List<String> keyList = sortedDictionary.getDictionaryKeyList();
        int count = 0;
        // checking key list for anagrams
        for (int index = 0; index < keyList.size(); index++) {
            char[] charInventory = wordString.toCharArray();
            Set<Set<String>> dictWordAnagramsSet = findAnagrams(0, index, charInventory, keyList);
            Set<Set<String>> tempAnagramSet = new HashSet<Set<String>>();
            if (dictWordAnagramsSet != null && !dictWordAnagramsSet.isEmpty()) {
                Set<Set<String>> mergeResult = null;
                for (Set<String> anagramSet : dictWordAnagramsSet) {
                    mergeResult = mergeAnagramKeyWords(anagramSet);
                    tempAnagramSet.addAll(mergeResult);
                }
                System.out.println("");
                sortedDictionary.findSingleWordAnagrams(keyList.get(index)).toString().replace(",", "");
                for (Set<String> anagramSet : tempAnagramSet) {
                    System.out.println("" + ++count + ".\t" + anagramSet.toString().replace(",", ""));
                }
                anagramsSet.addAll(tempAnagramSet);
            }
        }
        return anagramsSet;
    }

    private Set<Set<String>> findAnagrams(int recurDepth, int dictionaryIndex, char[] charInventory, List<String> keyList) {
        if (recurDepth > (numberOfWordsInAnagram - 1)) return null;
        String searchWord = keyList.get(dictionaryIndex);
        char[] searchWordChars = searchWord.toCharArray();

        //Checking if allowing two-word anagrams only
        if ((twoWordAnagramsOnly && (numberOfWordsInAnagram != 2 || recurDepth != 0)) || !twoWordAnagramsOnly) {
            //Find anagrams for whole word
            if (AnagramHelper.isEquivalent(searchWordChars, charInventory)) {
                Set<Set<String>> anagramsSet = new HashSet<Set<String>>();
                Set<String> anagramSet = new HashSet<String>();
                anagramSet.add(searchWord);
                anagramsSet.add(anagramSet);
                return anagramsSet;
            }
        }

        //find anagrams with multiple words
        if (AnagramHelper.isSubset(searchWordChars, charInventory)) {
            //remove characters that are already matched
            char[] newCharInventory = AnagramHelper.setDifference(charInventory, searchWordChars);
            if (newCharInventory.length >= minWordSize) {
                Set<Set<String>> anagramsSet = new HashSet<Set<String>>();
                for (int index = dictionaryIndex + 1; index < keyList.size(); index++) {
                    Set<Set<String>> searchWordAnagramsKeysSet = findAnagrams(recurDepth + 1, index, newCharInventory, keyList);
                    if (searchWordAnagramsKeysSet != null) {
                        Set<Set<String>> mergedSets = mergeWordToSets(searchWord, searchWordAnagramsKeysSet);
                        anagramsSet.addAll(mergedSets);
                    }
                }
                return anagramsSet.isEmpty() ? null : anagramsSet;
            }
        }
        return null;    //not anagram
    }

    private Set<Set<String>> mergeAnagramKeyWords(Set<String> anagramKeySet) {
        Set<Set<String>> anagramSet = new HashSet<Set<String>>();
        for (String word : anagramKeySet) {
            Set<String> anagramWordSet = sortedDictionary.findSingleWordAnagrams(word);
            anagramSet.add(anagramWordSet);
        }
        Set<String>[] anagramsSetArray = anagramSet.toArray(new Set[0]);
        return AnagramHelper.setMultiplication(anagramsSetArray);
    }

    private Set<Set<String>> mergeWordToSets(String word, Set<Set<String>> sets) {
        Set<Set<String>> mergedSet = new HashSet<Set<String>>();
        for (Set<String> set : sets) {
            set.add(word);
            mergedSet.add(set);
        }
        return mergedSet;
    }
}
