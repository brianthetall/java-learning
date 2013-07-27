package com.brianthetall.datastructures;

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

    Node head,tail;

    public Queue(){}

    public Object poll(){

	Object retval=tail.value();
	if(tail!=head){
	    tail.prev().setNext(null);
	    tail=tail.prev();
	}
	else{
	    head=null;
	    tail=null;
	}
	return retval;
    }

    public Object peek(){
	return tail.value();
    }

    public boolean add(Object e){
	if(e==null)
	    return false;
	Node node=new Node(e);

	if(head==null){
	    head=node;
	    tail=head;
	    return true;
	}

	else{
	    tail.setNext(node);
	    node.setPrev(tail);
	    tail=tail.next();
	    return true;
	}
    }

    public String toString(){

	if(head==null)
	    return null;

	String r="";
	Node temp=head;
	while(temp!=null){
	    r=r.concat(temp.toString()+"::");
	    temp=temp.next();
	}
	
	return r;
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
