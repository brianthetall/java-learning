package com.brianthetall.graph;

public interface GraphInterface{

    public void addVertex(Good good);

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
	public static class Edge<W extends Comparable>{
	    Vertex target;
	    Edge next;
	    W weight;
	    
	    public void setTargetVertex(Vertex v){
		target=v;
	    }
	    
	    public void linkEdge(Edge e){
		next=e;
	    }
	    
	    public Edge(W weight){
		this.weight=weight;
	    }
	    
	}
		
	public Vertex(Good good){
	    this.good=good;
	}

	public Good getGood(){
	    return good;
	}

	public String toString(){
	    return good.toString();
	}

	/*
	public <? extends Comparable> Edge addEdge(<?> weight){
	    Edge<?> e=new Edge<>(weight);
	    Edge last=first;
	    while(last.next!=null)
		last=last.next;
	    last.next=e;
	    return e;
	}
	*/

    }
    
}
