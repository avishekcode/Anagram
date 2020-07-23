package com.audition;

import java.util.*;

public class AnagramHelper {

    public static String sortWord(String wordString) {
        if (wordString.isEmpty()) {
            return null;
        }
        byte[] charBytes = wordString.getBytes();
        Arrays.sort(charBytes);
        return new String(charBytes);
    }

    public static boolean isSubset(char[] needle, char[] haystack) {
        if (needle.length > haystack.length) {
            return false;
        }
        List<Character> charList1 = toList(needle);
        List<Character> charList2 = toList(haystack);
        for (Character charValue : charList1) {
            if (charList2.contains(charValue)) {
                charList2.remove(charValue);
            } else {
                return false;
            }
        }
        return true;
    }

    private static List<Character> toList(char[] charArr) {
        assert charArr != null;
        List<Character> charList = new ArrayList<Character>();
        for (char ch : charArr) {
            charList.add(ch);
        }
        return charList;
    }

    private static char[] toCharArray(List<Character> charList) {
        if (charList == null || charList.isEmpty()) {
            return new char[0];
        }
        char[] charArr = new char[charList.size()];
        for (int index = 0; index < charList.size(); index++) {
            charArr[index] = charList.get(index);
        }
        return charArr;
    }

    public static boolean isEquivalent(char[] charArr1, char[] charArr2) {
        if (charArr1.length != charArr2.length) return false;
        int sum1 = 0, sum2 = 0;
        for (int index = 0; index < charArr1.length; index++) {
            sum1 += charArr1[index];
            sum2 += charArr2[index];
        }
        if (sum1 != sum2) return false;
        List<Character> charList1 = toList(charArr1);
        List<Character> charList2 = toList(charArr2);
        for (Character charValue : charList1) {
            charList2.remove(charValue);
        }
        return charList2.isEmpty();
    }

    //calculate set difference : charArr1 - charArr2 removes all charArr2 elements from charArr1
    public static char[] setDifference(char[] charArr1, char[] charArr2) {
        List<Character> list1 = toList(charArr1);
        List<Character> list2 = toList(charArr2);
        for (Character charObj : list2) {
            list1.remove(charObj);
        }
        return toCharArray(list1);
    }

    //perform set multiplication of all the sets of strings
    public static Set<Set<String>> setMultiplication(Set<String>... setsArray) {
        if (setsArray == null || setsArray.length == 0) {
            return null;
        }
        return setMultiplication(0, setsArray);
    }

    //calculate cartesian product of all the sets of strings
    private static Set<Set<String>> setMultiplication(int index, Set<String>... setsArray) {
        Set<Set<String>> setsMultiplied = new HashSet<Set<String>>();
        if (index == setsArray.length) {
            setsMultiplied.add(new HashSet<String>());
        } else {
            for (String obj : setsArray[index]) {
                for (Set<String> set : setMultiplication(index + 1, setsArray)) {
                    set.add(obj);
                    setsMultiplied.add(set);
                }
            }
        }
        return setsMultiplied;
    }
}
