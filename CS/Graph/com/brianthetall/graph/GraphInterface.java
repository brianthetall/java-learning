package com.brianthetall.graph;

public interface GraphInterface{

    public Vertex getVertex(String name);
    public void connectNodes(Vertex a,Vertex b);
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

	public String getGoodName(){
	    return (String)good.getKey();
	}

	public Good getGood(){
	    return good;
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
    }
    
}
