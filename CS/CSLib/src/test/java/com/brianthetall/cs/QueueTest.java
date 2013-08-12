package com.brianthetall.cs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Assert;
import java.util.Random;
import java.lang.Object;

/**
 * JUnit test class for Queue
 * Does no direct testing on class: Queue.Node
 */
public class QueueTest{

    private Queue q;

    /**
     * Configuration class containing Spring-beans
     */
    @Configuration public static class Injectables{
	/**
	 * @return Provides an Array of Objects
	 */
	@Bean public static Object[] objectArray(){
	    Random r=new Random();
	    int size=r.nextInt();//THIS MUST BE POSITIVE, NON-ZERO, AND LESS THAN A MAX-VALUE!
	    size=size<0?size*=-1:size;//positive
	    size=size==0?128:size;//non-zero
	    size=size%16000;//under a limit
	    Object[] o=new Object[size];
	    for(int i=0;i<o.length;i++)
		o[i]=new Object();
	    return o;
	}

    }

    @Before public void setup(){
	q=new Queue();
    }

    @After public void destroy(){
	q=null;
    }

    @Test public void poll(){}
    @Test public void peek(){}
    @Test public void add(){
	Object[] o=Injectables.objectArray();
	
    }

}
