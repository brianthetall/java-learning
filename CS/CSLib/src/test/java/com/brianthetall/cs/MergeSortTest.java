package com.brianthetall.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Assert;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.lang.Double;
import java.lang.Exception;

public class MergeSortTest{

    /**
     * Verify by comparing results with java.util.Collections.sort()
     * The data set is large, randomly generated every test
     */
    @Test public void sort()throws Exception{
	List<Double> list=Injectables.doubleList();
	List<Double> mySorted=MergeSort.sort(list);
	Collections.sort(list);//returns in same list reference!
	assert(mySorted.equals(list));
    }
}
