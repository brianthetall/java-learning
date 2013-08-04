package com.brianthetall.cs;

public class Heap<T extends Comparable>{

    public static class Node<T extends Comparable> implements Comparable{
	T t;

	public Node(T t){
	    this.t=t;
	}

	@Override
	public int compareTo(Object node){
	    if(node==null)
		return -1;
	    Node<T> temp=(Node<T>)node;
	    return temp.getValue().equals(t) ? 0 : temp.getValue().compareTo(t) > 0 ? 1 : -1;
	}

	public T getValue(){
	    return t;
	}

	public String toString(){  return t.toString();	}
    }
    //++++++++++++++++++++++++++++++++++++END NODE CLASS=================================
    private Node[] data;
    private int cursor;
    private int base;

    public Heap(int size,int base){
	cursor=0;
	this.base=base;
	if(size>0)
	    data=new Node[size];
	else
	    data=new Node[16];
    }

    private int findParent(int index){

	if(index<=0)
	    return -1;

	int parent=index/base;
	boolean lastChild = index%base==0?true:false;
	return lastChild?parent-1:parent;
	
    }

    private int findChild(int index){

	if(index<0)
	    return -1;
	
	return index * base + 1;
    }

    private void heapup(int index){

	int p=findParent(index);
	if( p >= 0 && data[index].compareTo(data[p]) < 0 ){

	    Node<T> temp = data[p];
	    data[p]=data[index];
	    data[index]=temp;

	    heapup(p);
	}

    }

    private void heapdown(int index){
	
	int firstChild=findChild(index);
	if(data[index]!=null){
	    T largestChild=(T)data[index].getValue();//init to parent's value
	    int largestIndex=-1;
	    for(int i=firstChild ; i<firstChild+base ; i++){
		
		if(data[i] == null)
		    break;
		
		if(data[i].getValue().compareTo(largestChild) > 0){
		    largestChild=(T)data[i].getValue();
		    largestIndex=i;
		}
	    }
	    
	    //if any children are larger, the bigest is now in largestChild/Index   
	    if(largestChild.compareTo(data[index].getValue()) > 0){
		
		Node<T> temp=data[largestIndex];
		data[largestIndex] = data[index];
		data[index] = temp;
		heapdown(largestIndex);//recurse
	    }
	}
    }

    public void add(T t){
	data[cursor] = new Node<T>(t);
	heapup(cursor);
	cursor++;
    }

    public T poll(){

	if(data[0]==null)
	    return null;

	Node<T> retval=data[0];
	data[0] = data[--cursor];
	data[cursor]=null;
	heapdown(0);

	return retval.getValue();
    }

    public T peek(){
	return (T)data[0].getValue();
    }

    public Object[] sort(){
	
	Object[] retval=new Object[cursor];
	for(int i=0;i<retval.length;i++)
	    retval[i]=poll();

	return retval;
    }

    public String toString(){
	String r="";
	int i=0;
	for(Node n:data){

	    if(n==null)
		break;

	    int p2=(int)(Math.log(i+1)/Math.log(base));
	    Double d=new Double(Math.log(i+1)/Math.log(base));

	    if( d-p2==0 )
		r=r.concat("|");

	    if(n!=null)
		r=r.concat(n.toString()+" ");
	    
	    i++;
	}
	return r;
    }

    public static void main(String[] args){

	if(args.length<1){
	    System.err.println("Heap [list of numbers]");
	    System.exit(-1);
	}

	Double[] data = new Double[args.length];
	for(int i=0;i<data.length;i++)
	    data[i]=new Double(args[i]);

	int base=2;
	Heap<Double> heap=new Heap<>(data.length*10,base);

	for(Double d:data)
	    heap.add(d);
	System.out.println(heap);

	Double peek = (Double)heap.peek();
	Double peek1 = (Double)heap.peek();
	System.out.println(peek +"=="+peek1);

	Double pulled = (Double)heap.poll();
	System.out.println("Polled:"+pulled);
	System.out.println(heap);

	for(Double d:data)
	    heap.add(d);
	System.out.println(heap);

	pulled = (Double)heap.poll();
	System.out.println("pulled="+pulled);
	pulled = (Double)heap.poll();
	System.out.println("pulled="+pulled);
	pulled = (Double)heap.poll();
	System.out.println("pulled="+pulled);
	System.out.println(heap);

	Object[] sorted = heap.sort();
	for(Object o:sorted)
	    System.out.println(o);

    }

}
