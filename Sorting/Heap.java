//import java.lang.ClassCastException;

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

    private void heapup(int index){

	int p=findParent(index);
	if( p >= 0 && data[index].compareTo(data[p]) < 0 ){

	    System.out.println("SWAP index="+index+"with parent@:"+p);	    
	    Node<T> temp = data[p];
	    data[p]=data[index];
	    data[index]=temp;

	    heapup(p);
	}

    }

    public void add(T t){
	data[cursor] = new Node<T>(t);
	heapup(cursor);
	cursor++;
    }

    public T poll(){
	return null;
    }

    public T peek(){
	return null;
    }

    public T[] sort(){
	return null;
    }

    public String toString(){
	String r="";
	for(Node n:data)
	    if(n!=null)
		r=r.concat(n.toString()+" ");
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
	Heap<Double> heap=new Heap<>(data.length,base);

	for(Double d:data){
	    heap.add(d);
	    System.out.println(heap);
	}
	    	
    }

}
