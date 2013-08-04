package com.brianthetall.cs;

import java.util.List;
import java.util.ArrayList;

public interface GraphInterface{

    /**
     * Vertex class within GraphInterface
     * Contains start of linked list of 
     */
    public static class Vertex{

	Edge first;
	Good good;

	/**
	 * Edges exist iff a Vertex exists; inner class
	 */
	public static class Edge<W extends Comparable>implements Comparable{
	    Vertex target;
	    final Vertex start;
	    Edge next;
	    W weight;

	    public boolean equals(Object o){
		if(o==null)
		    return false;
		Edge e=(Edge)o;
		if(start.equals(e.getStart()) && target.equals(e.getTarget()) && weight.equals(e.getWeight()) )
		    return true;
		return false;
	    }

	    /**
	     * fill this in later...
	     */
	    public int hashCode(){
		int hash=0;
		
		return hash;
	    }

	    public int compareTo(Object e)throws NullPointerException{
		if(e==null)
		    throw new NullPointerException("Comparing Edge to null!\r\n");
		Edge edge=(Edge)e;
		W weight=(W)edge.getWeight();
		if(this.weight.compareTo(weight)<0)
		    return -1;
		else if(this.weight.compareTo(weight)==0)
		    return 0;
		else return 1;
	    }	    

	    public Vertex getTarget(){return target;}

	    public Vertex getStart(){return start;}

	    public Edge getNext(){return next;}

	    public W getWeight(){return weight;}

	    public Edge setTargetVertex(Vertex v){
		target=v;
		return this;
	    }
	    
	    public void linkEdge(Edge e){
		next=e;
	    }
	    
	    public Edge(W weight,Vertex start){
		this.weight=weight;
		this.start=start;
	    }

	    public String toString(){
		//TODO: there must be a better way to format this.......
		return "Start="+start+"\t\tTargetVertex="+target.toString()+"\tWeight="+weight.toString();
	    }
	    
	}
	//end edge class
	
	public Vertex(Good good){
	    this.good=good;
	}

	public List<Edge> getEdgeList(){
	    if(first==null)
		return null;
	    List<Edge> retval=new ArrayList<Edge>();
	    Edge e=first;
	    while(e!=null){
		retval.add(e);
		e=e.getNext();
	    }
	    return retval;
	}

	public String getGoodName(){
	    return (String)good.getKey();
	}

	public Good getGood(){
	    return good;
	}

	public String printEdges(){
	    String retval="";
	    Edge e=first;
	    while(e!=null)
		retval=retval.concat(e.toString());
	    return retval;
	}

	public String toString(){
	    return good.toString();
	}

	public <W extends Comparable> Edge addEdge(W weight, Vertex target){
	    Edge<W> e=new Edge<>(weight,this);
	    if(first==null)
		first=e;
	    else{
		Edge last=first;
		while(last.next!=null)//traverse linkedlist
		    last=last.next;
		last.next=e;
	    }
	    e.setTargetVertex(target);
	    return e;
	    
	}
    }//END OF VERTEX CLASS
    
    public Vertex.Edge[] getEdges();
    public Vertex.Edge[] getSortedEdges();
    public Vertex getVertex(String name);
    public void connectNodes(Vertex a,Vertex b);
    public void addVertex(Good good);
    public List<Vertex> dfs(Vertex root);
    public List<Vertex> bfs(Vertex root);
    
}
