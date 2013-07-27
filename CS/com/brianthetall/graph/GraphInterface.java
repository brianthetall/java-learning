package com.brianthetall.graph;

import java.util.List;

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
	    Edge next;
	    W weight;

	    public boolean equals(Object o){
		if(o==null)
		    return false;
		Edge e=(Edge)o;
		if(target.equals(e.getTarget()) && next.equals(e.getNext()) && weight.equals(e.getWeight()) )
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

	    public int compareTo(Object w){
		W weight=(W)w;
		if(this.weight.compareTo(weight)<0)
		    return -1;
		else if(this.weight.compareTo(weight)==0)
		    return 0;
		else return 1;
	    }	    

	    public Vertex getTarget(){return target;}

	    public Edge getNext(){return next;}

	    public W getWeight(){return weight;}

	    public void setTargetVertex(Vertex v){
		target=v;
	    }
	    
	    public void linkEdge(Edge e){
		next=e;
	    }
	    
	    public Edge(W weight){
		this.weight=weight;
	    }

	    public String toString(){
		return "TargetVertex="+target.toString()+" Weight="+weight.toString();
	    }
	    
	}
	//end edge class
	
	public Vertex(Good good){
	    this.good=good;
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
	    Edge<W> e=new Edge<>(weight);
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
