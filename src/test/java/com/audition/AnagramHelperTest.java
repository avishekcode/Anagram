package com.audition;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnagramHelperTest {
    @Test
    public void isSubsetTest() {
        Assert.assertTrue(AnagramHelper.isSubset("avi".toCharArray(), "avishek".toCharArray()));
        Assert.assertFalse(AnagramHelper.isSubset("avy".toCharArray(), "avishek".toCharArray()));
    }

    @Test
    public void isEquivalentTest() {
        Assert.assertTrue(AnagramHelper.isEquivalent("avishek".toCharArray(), "shekavi".toCharArray()));
        Assert.assertFalse(AnagramHelper.isEquivalent("avishe".toCharArray(), "shekavi".toCharArray()));
    }

    @Test
    public void setDifferenceTest() {
        Assert.assertTrue(Arrays.equals("av".toCharArray(), AnagramHelper.setDifference("avishek".toCharArray(), "ishek".toCharArray())));
        Assert.assertFalse(Arrays.equals("avi".toCharArray(), AnagramHelper.setDifference("avishek".toCharArray(), "ishek".toCharArray())));
    }

    @Test
    public void setMultiplicationTest() {
        Set<String> set1 = new HashSet<String>();
        set1.add("avi");
        set1.add("shek");
        Set<String> set2 = new HashSet<String>();
        set2.add("1");
        set2.add("2");
        Set<String> set3 = new HashSet<String>();
        set3.add("a");
        set3.add("a");
        System.out.println(AnagramHelper.setMultiplication(set1, set2, set3).size());
        Assert.assertEquals(4, AnagramHelper.setMultiplication(set1, set2, set3).size());
        set3.add("b");
        System.out.println(AnagramHelper.setMultiplication(set1, set2, set3).size());
        Assert.assertEquals(8, AnagramHelper.setMultiplication(set1, set2, set3).size());
        set2.add("3");
        System.out.println(AnagramHelper.setMultiplication(set1, set2, set3).size());
        Assert.assertEquals(12, AnagramHelper.setMultiplication(set1, set2, set3).size());
        set3.add("c");
        System.out.println(AnagramHelper.setMultiplication(set1, set2, set3).size());
        Assert.assertEquals(18, AnagramHelper.setMultiplication(set1, set2, set3).size());
    }
}