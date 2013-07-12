public class BinaryTree<T extends Comparable>{

    public static class Node<T extends Comparable>{

	private T t;
	private Node left,right,parent;

	public Node(T t){
	    this.t=t;
	    left=null;
	    right=null;
	    parent=null;
	}

	public void setParent(Node n){
	    parent=n;
	}

	public void setChild(Node n){
	
	    if(n==null){}
	    //	    System.out.println("TEE="+t+"t.compareTo(69)="+t.compareTo((T)69.0));

	    else if( t.compareTo(n.value()) < 0)
		right = n;
	    else
		left=n;


	}

	public void setLeft(Node n){left=n;}
	public void setRight(Node n){right=n;}
	public Node getParent(){return parent;}
	public Node getLeft(){
	    return left==null?null:left;
	}
	public Node getRight(){
	    return right==null?null:right;
	}

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
	add(t,root,null);
    }

    private void add(T t,Node root, Node parent){

	if(t==null){}//fuck off; do nothing

	else if(this.root==null)
	    this.root=new Node<T>(t);

	else if(root==null){
	    root=new Node<T>(t);
	    if(parent!=null){
		root.setParent(parent);
		parent.setChild(root);
	    }
	}
	
	else{//figure out which [left or right] node to pass the value to
	    if(t.compareTo(root.value()) < 0)
		add(t,root.getLeft(),root);
	    else
		add(t,root.getRight(),root);
	}

    }

    /**
     * Balance the binary tree
     * Run periodically to ensure log(n) search,insertion times
     * But not too often, as it is expensive
     * @return Node of new balanced tree root
     */
    public BinaryTree balance(){
	/*
	  sort the list using traverse
	  find new root in the middle; root=sorted[length/2]
	  move out from the middle and build out the tree
	 */
	Node[] sorted = sort(root);
	BinaryTree<T> newTree=new BinaryTree<T>();
	recursiveBalance(sorted,0,sorted.length,newTree);
	return newTree;
    }

    private void recursiveBalance(Node[] sorted,int start,int stop,BinaryTree tree){

	int length=stop-start;
	if(length<0){}
	else if(length==0)
	    tree.add((T)sorted[0].value());
	else if(length > 0){
	    tree.add((T)sorted[length/2].value());//add median node
	    recursiveBalance(sorted,start,length/2-1,tree);//left
	    recursiveBalance(sorted,length/2+1,stop,tree);//right
	}
    }

    public void traverse(){
	System.out.println("Traverse:");
	traverse(root);
    }

    /**
     * Traverse from smallest node, to largest
     * @param n Root node, from which the traversal should start
     */
    private void traverse(Node n){

	Node l,r;
	if(n==null)
	    System.err.println("ERROR: Passing null-Node to traverse()");
	l=n.getLeft();
	r=n.getRight();

	if(l!=null)
	    traverse(n.getLeft());

	System.out.println(n);

	if(r!=null)
	    traverse(n.getRight());
    }

    /**
     * Sort this Binary Tree
     * @return array of Nodes in sorted order, low-to-high
     */
    public Node[] sort(Node root){
	Node[] retval=new Node[size];
	//use traverse to do this
	traverse(root);//right now traverse has a Println-statement in it
	return null;
    }

    private String BFS_toString(Node n){

	String left = n.getLeft()==null ? "":BFS_toString(n.getLeft());
	String right = n.getRight()==null ? "":BFS_toString(n.getRight());

	return n.value().toString()+" "+left+" "+right;
	//return n.value().toString();
    }

    public String toString(){
	return BFS_toString(root);
    }

    public static void main(String argv[]){

	if(argv.length<1){
	    System.err.println("BinaryTree <list of numbers>");
	    System.exit(2);
	}

	BinaryTree<Double> tree=new BinaryTree<>();
	for(String s:argv)
	    tree.add(new Double(s));
	System.out.println("TREE_DUMP: "+tree);
	tree.traverse();
	//	tree = tree.balance();
    }

}
