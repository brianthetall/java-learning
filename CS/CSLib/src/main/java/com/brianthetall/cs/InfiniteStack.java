package com.brianthetall.cs;

import com.brianthetall.cs.Stack;

public class InfiniteStack implements Stack{

    public static class Element{
	Element next;
	final Object o;
	public Element(Object o){
	    this.o=o;
	}
	public void setNext(Element next){
	    this.next=next;
	}
	public Element next(){
	    return next;
	}
	public Object value(){
	    return o;
	}
    }

    Element lastAdded;

    public void push(Object o){
	Element top=new Element(o);
	top.setNext(lastAdded);
	lastAdded=top;
    }

    public Object pop(){
	Element retval=lastAdded;
	if(retval==null)
	    return null;
	lastAdded=retval.next();
	return retval.value();
    }
    public InfiniteStack(){
    }

}
