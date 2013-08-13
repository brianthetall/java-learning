package com.brianthetall.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Assert;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Exception;

/**
 * Test a base-2 heap
 */
public class HeapTest{

    private Heap<Integer> heap;
    private int size;

    @Before public void base2Setup()throws Exception{
	size=Injectables.boundedInt(64,1024);
	heap=new Heap<Integer>(size,2);
	assert(0==heap.size());
    }

    /**
     * Test poll & add
     *
     */
    @Test public void pollAdd()throws Exception{
	Integer[] i=Injectables.integerArray(size);
	List<Integer> list=new ArrayList<Integer>(i.length);
	assert(heap.peek()==null);
	for(int q=0;q<i.length;q++){//do not over fill heap!
	    heap.add(i[q]);
	    list.add(i[q]);//for MergeSort
	}
	assert(heap.size()==i.length);

	List<Integer> sorted=MergeSort.sort(list);
	Integer buffer=heap.poll();
	int counter=i.length-1;
	while(buffer!=null){//verify heap outputs sorted list
	    assert(buffer.equals(sorted.get(counter--)));
	    buffer=heap.poll();
	}
    }

    /**
     * Test peek & add
     */
    @Test public void peek(){
	
    }

}
