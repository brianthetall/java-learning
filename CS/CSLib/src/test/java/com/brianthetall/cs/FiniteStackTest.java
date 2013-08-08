package com.brianthetall.cs;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Assert;

public class FiniteStackTest{

    private FiniteStack stack;
    public static final int SIZE=16;

    @Before public void setup(){
	stack=new FiniteStack(SIZE);
    }

    @Test public void push(){
	stack.push(new String("Hat"));
	stack.push(new String("Red"));
	assert(stack.freeSpace()==stack.stackSize()-2);
	assert(stack.pop().equals("Red"));
	assert(stack.pop().equals("Hat"));
    }

    @Test public void pop(){
	stack.push(new Integer(69));
	Integer i=(Integer)stack.pop();
	assert(i.equals(new Integer(69)));
	assert(!i.equals(new Integer(42)));
    }

    @Test public void freeSpace(){
	assert(stack.freeSpace()==stack.stackSize());
	stack.push(new Object());
	stack.push(new Object());
	assert(stack.freeSpace() == stack.stackSize()-2);
    }

    @Test public void stackSize(){
	assert(stack.stackSize()==SIZE);
    }

}
