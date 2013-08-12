package com.brianthetall.cs;

import java.lang.Object;
import java.lang.NullPointerException;

public class Queue{

    public static class Node{

	private Object e;
	private Node prev,next;

	public Node(Object e){
	    this(e,null,null);
	}

	public Node(Object e,Node prev,Node next){
	    this.e=e;
	    this.prev=prev;
	    this.next=next;
	}

	public Object value(){
	    return e;
	}

	public void setNext(Node next){
	    this.next=next;
	}

	public Node next(){
	    return next;
	}

	public void setPrev(Node prev){
	    this.prev=prev;
	}

	public Node prev(){
	    return prev;
	}

	public String toString(){
	    return e.toString();
	}

    }
    //=================================end node class++++++++++++++++++++++++++++

    private Node head,tail;
    private int counter;

    /**
     * Construct empty Queue
     */
    public Queue(){
	counter=0;
    }

    /**
     * Construct a Queue from an array of objects
     * @param objects to add to new queue
     * @throws NullPointerException
     */
    public Queue(Object[] objects){
	if(objects==null)
	    throw new NullPointerException("Null objects[] passed to Queue Constructor");
	counter=0;
	for(Object o:objects)
	    add(o);
    }

    /**
     * Return the head of the Queue
     * @return head object; null if queue is empty
     */
    public Object poll(){

	if(head==null)
	    return null;
	Object retval=head.value();
	if(tail!=head){//if not already the last-object
	    head=head.prev();
	    head.setNext(null);//in front of line
	}
	else{
	    head=null;
	    tail=null;
	}
	counter--;
	return retval;
    }

    /**
     * Peek at the top of queue
     * @return the oldest object in the line
     */
    public Object peek(){
	if(head==null)
	    return null;
	return head.value();
    }

    /**
     * Add an Object to the line
     * @param e object to add
     * @returns true on success; else false
     */
    public boolean add(Object e){
	if(e==null)
	    return false;
	Node node=new Node(e);
	counter++;
	if(head==null){
	    head=node;
	    tail=head;
	    return true;
	}

	else{
	    tail.setPrev(node);
	    node.setNext(tail);
	    node.setPrev(null);//its at back of line
	    tail=node;
	    return true;
	}
    }

    /**
     * @return size of the queue; number of objects
     */
    public int size(){
	return counter;
    }

    public String toString(){

	if(head==null)
	    return null;

	String r="";
	Node temp=head;
	while(temp!=null){
	    r=r.concat(temp.toString()+"::");
	    temp=temp.prev();
	}
	
	return r;
    }

    /**
     * Determines if two Queues are built the same
     * Objects must be in same order; queue sizes must match
     * @return true iff queues are equal
     * @throwsy NullPointerException
     */
    public boolean equals(Object o){
	if(o==null)
	    throw new NullPointerException("Queue.equals: Null object parameter");
	Queue q=(Queue)o;
	if(q.size() != this.size())
	    return false;//fail fast
	Object[] left=q.array();
	Object[] right=this.array();
	for(int i=0;i<left.length;i++){
	    if(!left[i].equals(right[i]))
		return false;
	}
	return true;
    }

    /**
     * Traverse the Queue and populate an Object[]
     * For JUnit testing
     * @return array, [0] is head of queue
     */
    Object[] array(){
	Object[] retval=new Object[size()];
	Queue.Node buffer=head;
	for(int i=0;i<retval.length;i++){
	    retval[i]=buffer.value();
	    buffer=buffer.prev();
	}
	return retval;
    }

    public static void main(String argv[]){
	Queue q=new Queue();
	q.add(new String("zero"));
	q.add(new String("one"));
	q.add(new Double(69.69));
	q.add(new String("two"));
	System.out.println(q);
	System.out.println("PEEK "+q.peek());
	System.out.println("POLL "+q.poll());
	System.out.println("POLL "+q.poll());
	System.out.println("POLL "+q.poll());
	System.out.println("POLL "+q.poll());
	System.out.println(q);
    }
}
