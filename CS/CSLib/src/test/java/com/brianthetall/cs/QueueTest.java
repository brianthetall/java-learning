package com.brianthetall.cs;

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
     * Run no-arg constructor for each test
     */
    @Before public void setup(){
	q=new Queue();
    }

    /**
     * Cleanup after each test
     * Null the queue reference
     */
    @After public void destroy(){
	q=null;
    }

    /**
     * Test the second constructor for Queue
     * Also tests Queue.equals(Object o)
     */
    @Test public void queue(){
	Object[] data=Injectables.objectArray();
	Queue q2=new Queue(data);
	for(Object o:data)
	    q.add(o);
	assert(q.equals(q2));
    }

    /**
     * Test Queue.poll()
     * Random number of Objects go in
     * Assert that every poll() returns the correct Object
     * Stop when poll() returns null; the line is empty
     */
    @Test public void poll(){
	Object[] data=Injectables.objectArray();
	assert(q.poll()==null);
	for(Object o:data)
	    q.add(o);
	Object buffer=q.poll();
	int i=0;
	while(buffer!=null){
	    assert(buffer.equals(data[i++]));
	    buffer=q.poll();
	}
    }

    /**
     * Load queue with injected object[]
     * Check that peek() returns correct values
     * as the queue is polled.
     */
    @Test public void peek(){
	Object[] data=Injectables.objectArray();
	assert(q.peek()==null);
	for(Object o:data)
	    q.add(o);
	for(int i=0;i<data.length;i++){
	    assert(data[i].equals(q.peek()));
	    q.poll();
	}
    }
    
    /**
     * Test Queue.add(Object)
     * Verify queue size as additions are made
     * Assert that array that went in matches queue contents
     */
    @Test public void add(){
	Object[] o=Injectables.objectArray();
	assert(q.size()==0);
	for(Object p:o)
	    q.add(p);
	assert(q.size()==o.length);
	Object[] test=q.array();
	assert(test.length==o.length);
	for(int i=0;i<test.length;i++)
	    assert(o[i].equals(test[i]));
    }

}
