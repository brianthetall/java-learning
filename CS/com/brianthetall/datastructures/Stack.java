package com.brianthetall.datastructures;

public class Stack{

    private Object[] stack;
    private int cursor;
    
    public Stack(int size){

	cursor=0;
	if(size>0)
	    stack=new Object[size];

    }

    public String toString(){
	String r="";
	for(int i=0;i<cursor;i++)
	    r=r.concat(stack[i]+" ");
	return r;
    }

    public void push(Object o){
	if(o!=null)
	    stack[cursor++]=o;
    }

    public Object pop(){

	if(cursor==0)
	    return null;//nothing to POP
	else if(stack[cursor-1] != null){
	    cursor--;
	    return stack[cursor];
	}
	return null;
    }

    public int freeSpace(){
	return stack.length - cursor;
    }
    
    public int stackSize(){
	return stack.length;
    }

    public static void main(String argv[]){
	Stack stack=new Stack(16);
	System.out.println("Free="+stack.freeSpace());
	System.out.println("Stack:"+stack);
	stack.push(new Integer(69));
	stack.push(new String("my Stack"));
	System.out.println("Free="+stack.freeSpace());
	System.out.println("Stack:"+stack);
	System.out.println("Popped:"+stack.pop());
	System.out.println("Popped:"+stack.pop());
	System.out.println("Stack:"+stack);
	for(String s:argv)
	    stack.push(new Double(s));
	System.out.println("Stack:"+stack);
	System.out.println("Free="+stack.freeSpace());
	int free=stack.freeSpace();
	for(int i=free ; i < stack.stackSize() ; i++)
	    System.out.println("Pop:"+stack.pop());
	System.out.println("Free="+stack.freeSpace());
    }

}
