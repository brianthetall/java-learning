public class Heap<T extends Comparable>{

    public static class Node<T>{
	T t;
	public Node(T t){ this.t=t;}
	public String toString(){  return t.toString();	}
    }

    private Node[] data;

    public Heap(int size){
	if(size>0)
	    data=new Node[size];
	else
	    data=new Node[16];
    }

    public void add(T t){

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

    public static void main(String[] args){
	if(args.length<1){
	    System.err.println("Heap [list of numbers]");
	    System.exit(-1);
	}
	Double[] data = new Double[args.length];
	for(int i=0;i<data.length;i++)
	    data[i]=new Double(args[i]);

	
    }

}
