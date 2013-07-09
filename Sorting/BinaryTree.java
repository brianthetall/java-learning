public class BinaryTree<T extends Comparable>{

    public static class Node<T>{

	private T t;
	private Node left,right,parent;

	public Node(T t){this.t=t;}

	public void setLeft(Node n){left=n;}
	public void setRight(Node n){right=n;}
	public Node getLeft(){return left;}
	public Node getRight(){return right;}

	public T value(){return t;}

	public String toString(){
	    return t.toString();
	}
    }
    //++++++++++End Node++++++++++++++++
    private Node<T> root;
    private int size;

    public BinaryTree(){
	size=0;
    }

    public void add(T t){
	add(t,root);
    }

    public void add(T t,Node r){
	if(r == null){
	    r = new Node(t);
	    size++;
	    System.out.println("Node Added:"+r);
	}
	else{
	    if(t.compareTo(r.value()) < 0)
		add(t,r.getLeft());
	    else
		add(t,r.getRight());
	}

    }

    private void traverse(Node n){

    }

    public Node[] sort(){
	Node[] retval=new Node[size];
	//traverse
	return null;
    }

    private String BFS_toString(Node n){

	//	String left = n.getLeft()==null ? "":BFS_toString(n.getLeft());
	//	String right = n.getRight()==null ? "":BFS_toString(n.getRight());

	return n.value().toString();//+" "+left+" "+right;
    }

    public String toString(){
	return BFS_toString(root);
    }

    public static void main(String argv[]){

	if(argv.length<1)
	    System.err.println("BinaryTree <list of numbers>");

	BinaryTree<Double> tree=new BinaryTree<>();
	for(String s:argv)
	    tree.add(new Double(s));
	System.out.println("TREE_DUMP: "+tree);
    }

}
