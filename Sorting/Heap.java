public class Heap<T extends Comparable>{

    public static class Node<T extends Comparable>{
	T t;
	Node[] nodes;

	public Node(T t,int children){
	    this.t=t;
	    if(children>0)
		nodes=new Node[children];
	}

	public boolean addChild(Node<T> t){
	    for(Node<T> child:nodes){
		if(child==null){
		    child=t;
		    return true;
		}
	    }
	    return false;
	}

	public String toString(){  return t.toString();	}
    }
    //++++++++++++++++++++++++++++++++++++END NODE CLASS=================================
    private Node[] data;
    private int cursor;

    public Heap(int size){
	cursor=0;
	if(size>0)
	    data=new Node[size];
	else
	    data=new Node[16];
    }

    private int findParent(int index){

	if(index<=0)
	    return -1;
	boolean odd = index%0==0?false:true;
	int parent=index/2;
	return odd?parent:parent-1;
	
    }

    public void add(T t){
	data[cursor] = new Node<T>(t,2);//2 children
	data[findParent(cursor)].addChild(data[cursor]);

	//re-structure heap if needed
	Node<T> temp=data[cursor];
	//	while(temp

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

	Heap<Double> heap=new Heap<>(data.length);
	Double[] data = new Double[args.length];

	for(int i=0;i<data.length;i++)
	    data[i]=new Double(args[i]);

	for(Double d:data)
	    heap.add(d);

	System.out.println(heap);
	    	
    }

}
