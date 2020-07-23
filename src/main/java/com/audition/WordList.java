package com.audition;

import java.io.*;
import java.util.*;

public class WordList {
    //map with sorted characters as keys
    private final Map<String, Set<String>> sortedStringMap = new TreeMap<String, Set<String>>();
    private boolean isDictionaryLoaded = false;

    public void loadDictionaryWithSubsets(String wordString, int minWordSize) {
        try {
            File file = new File("wordlist.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            boolean skipFirstLine = false;
            while ((line = reader.readLine()) != null) {
                if (!skipFirstLine || line == null) {
                    skipFirstLine = true;
                    continue;
                }
                String[] words = line.split("[^a-z0-9]");
                for (String word : words) {
                    word = word.trim().toLowerCase();
                    if (word.isEmpty()) continue;
                    String sortedWord = AnagramHelper.sortWord(word);
                    if (sortedWord == null
                            || sortedWord.isEmpty()
                            || (wordString != null && !wordString.isEmpty()
                            && (sortedWord.length() < minWordSize
                            || !AnagramHelper.isSubset(sortedWord.toCharArray(), wordString.replaceAll("\\s", "").toLowerCase().toCharArray())))) {
                        continue;//Skipping words that are not subsets
                    }
                    Set<String> wordSet = sortedStringMap.get(sortedWord);
                    if (wordSet != null) {
                        wordSet.add(word);
                    } else {
                        wordSet = new TreeSet<String>();
                        wordSet.add(word);
                        sortedStringMap.put(sortedWord, wordSet);
                    }
                }
            }
            reader.close();
            isDictionaryLoaded = true;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file...");
        } catch (IOException e) {
            System.out.println("Couldn't read the file...");
        }
    }

    public Set<String> findSingleWordAnagrams(String wordString) {
        if (!isDictionaryLoaded) {
            throw new IllegalStateException("dictionary not loaded");
        } else {
            return sortedStringMap.get(AnagramHelper.sortWord(wordString));
        }
    }

    public List<String> getDictionaryKeyList() {
        assert sortedStringMap != null;
        return new ArrayList<String>(sortedStringMap.keySet());
    }

    public boolean isDictionaryLoaded() {
        return isDictionaryLoaded;
    }

    @Override
    public String toString() {
        return "isDictionaryLoaded?: " + isDictionaryLoaded + "\nDictionary: " + sortedStringMap;
    }
}
