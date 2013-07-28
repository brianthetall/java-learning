package com.brianthetall.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class MinimumSpanningTree{

    private MinimumSpanningTree(){}
    
    public static List<GraphInterface.Vertex.Edge> kruskal(UndirectedGraph graph){

	GraphInterface.Vertex.Edge[] sorted=graph.getSortedEdges();
	Set visited=new HashSet(graph.size()*2,(float)0.6);
	List<GraphInterface.Vertex.Edge> retval=new ArrayList<GraphInterface.Vertex.Edge>(sorted.length);
	List<GraphInterface.Vertex.Edge> bridges=new ArrayList<GraphInterface.Vertex.Edge>();

	for(GraphInterface.Vertex.Edge e:sorted){

	    //	    if(visited.size() == graph.size()-1)
	    //		break;//we have enough edges to form the spanning tree

	    /*
	      clusters can wind up existing; non-spanning condition
	     */

	    if( visited.contains(e.getStart()) && visited.contains(e.getTarget()) ){//both Vertexs already added
		if(!retval.contains(new GraphInterface.Vertex.Edge(e.getWeight(),e.getTarget()).setTargetVertex(e.getStart()))){
		    if(!bridges.contains(new GraphInterface.Vertex.Edge(e.getWeight(),e.getTarget()).setTargetVertex(e.getStart())))
			bridges.add(e);//add only if this isnt an instance of an edge already in retval
		}
		continue;
	    }
	    else if(visited.contains(e.getStart())){
		retval.add(e);
		visited.add(e.getTarget());
	    }
	    else if(visited.contains(e.getTarget())){
		retval.add(e);
		visited.add(e.getStart());
	    }
	    else{
		retval.add(e);
		visited.add(e.getStart());
		visited.add(e.getTarget());
	    }
	}

	System.out.println("Bridges:");
	for(GraphInterface.Vertex.Edge bridge:bridges)
	    System.out.println(bridge);

	return retval;
    }

    public static void main(String args[]){
	
    }

}
