public class Queue<E>{

    public static class Node<E>{

	private E e;
	private Node prev,next;

	public Node(E e){
	    this(e,null,null);
	}

	public Node(E e,Node prev,Node next){
	    this.e=e;
	    this.prev=prev;
	    this.next=next;
	}

	public E value(){
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

    Node<E> head,tail;

    public Queue(){}

    public E poll(){

	E retval=tail.value();
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

    public E peek(){
	return tail.value();
    }

    public boolean add(E e){
	if(e==null)
	    return false;
	Node<E> node=new Node<>(e);

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
	Node<E> temp=head;
	while(temp!=null){
	    r=r.concat(temp.toString()+"::");
	    temp=temp.next();
	}
	
	return r;
    }

    public static void main(String argv[]){
	Queue<String> q=new Queue<>();
	q.add(new String("zero"));
	q.add(new String("one"));
	q.add(new String("two"));
	System.out.println(q);
	System.out.println("PEEK "+q.peek());
	System.out.println("POLL "+q.poll());
	System.out.println("POLL "+q.poll());
	System.out.println("POLL "+q.poll());
	System.out.println(q);
	
    }

}
