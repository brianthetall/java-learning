package com.brianthetall.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.String;
import java.lang.Double;
import com.brianthetall.sorting.MergeSort;
import com.brianthetall.datastructures.Queue;
import com.brianthetall.datastructures.Stack;
import com.brianthetall.datastructures.InfiniteStack;

public class UndirectedGraph implements GraphInterface{

    private Map<String,Vertex> vertexMap;
    private Map<Vertex,List> edgeMap;

    public Vertex.Edge[] getSortedEdges(){
	Vertex.Edge[] edges=getEdges();
	List<Vertex.Edge> list=new MergeSort<Vertex.Edge>(edges).sort();
	Vertex.Edge[] retval=new Vertex.Edge[list.size()];
	for(int i=0;i<list.size();i++)
	    retval[i]=list.get(i);
	return retval;
    }

    public Vertex.Edge[] getEdges(){
	Vertex.Edge[] retval=null;
	//HERER?!?!?!?!!?
	return retval;
    }

    public Vertex getVertex(String name){
	return (Vertex)vertexMap.get(name);
    }
    
    public void addVertex(Good good){

	Vertex v=new Vertex(good);//pass the Name+Map object
	vertexMap.put( (String)(good.getKey()),v);
	
    }

    public void connectNodes(Vertex a,Vertex b){

	a.addEdge(0.23,b);
	b.addEdge(0.23,a);

    }

    public UndirectedGraph(){
	vertexMap=new HashMap<String,Vertex>(128,(float)0.5);
    }

    public String toString(){
	String s="";
	//traverse Graph; print Each Vertex's toString()
	//traverse can only run once the edges have been finished
	//for now, use the HashMap iterator to list all vertexs

	//	s=s.concat(
	Iterator<Vertex> nodes=vertexMap.values().iterator();
	while(nodes.hasNext())
       	    s=s.concat(nodes.next().toString());

	return s;
    }

    //HERE
    public List<Vertex> dfs(Vertex root){

	if(root==null)
	    return null;
	
	List<Vertex> retval=new ArrayList<Vertex>();
	Stack s=new InfiniteStack();
	List<Vertex.Edge> edges=root.getEdgeList();
	for(Vertex.Edge e:edges)
	    s.push(e);
	System.out.println("Root:"+root);
	retval.add(root);//first element in list is ROOT
	dfs(retval,s);

	return retval;
    }

    /** Partner to public-dfs
     */
    private List<Vertex> dfs(List<Vertex> retval, Stack stack){

	Vertex.Edge current=(Vertex.Edge)stack.pop();

	while(current!=null){

	    if(retval.contains(current.getTarget()))
		current=(Vertex.Edge)stack.pop();

	    else{//evaluate; this touches something new
		System.out.println("Vertex: "+current.getTarget());
		retval.add(current.getTarget());//add to RETVAL

		List<Vertex.Edge> newEdges=current.getTarget().getEdgeList();
		for(Vertex.Edge e:newEdges)
		    stack.push(e);//add new edges to STACK
		current=(Vertex.Edge)stack.pop();		
	    }
	}
	return retval;
    }

    public List<Vertex> bfs(Vertex root){
	List<Vertex> retval=new ArrayList<Vertex>();
	Queue q=new Queue();
	//q.add(Object o);
	//q.peek() , q.poll()
	
	return retval;
    }

    public static void main(String args[]){

	GraphInterface g=new UndirectedGraph();

	Good<String,? extends Map> gold=new Good<>("AU",new HashMap<String,Double>(16,(float)0.5));
	Good<String,? extends Map> silver=new Good<>("AG",new HashMap<String,Double>(16,(float)0.5));

	gold.addParameter("Price per ounce", 69.69);
	System.out.println("get parameter:"+gold.getParameter("Price per ounce")+"\r\n");

	g.addVertex(gold);
	g.addVertex(silver);

	System.out.println(g.toString());
	
	//Add edge connecting AU & AG
	g.connectNodes(g.getVertex("AU"),g.getVertex("AG"));

	//test Graph Traversal?
	List<Vertex> dfsTraversed = g.dfs(g.getVertex("AU"));
	System.out.println("DFS:");
	for(Vertex v:dfsTraversed)
	    System.out.println(v);
	
	//	Vertex.Edge[] sortedEdges=g.getSortedEdges();//get all Edges!
	

    }

}