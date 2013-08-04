package com.brianthetall.cs;

import java.util.Set;
import java.util.Random;
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

    Random r;

    private Map<String,Vertex> vertexMap;
    //    private Map<Vertex,List> edgeMap;

    public int size(){
	return vertexMap.size();
    }

    public Vertex.Edge[] getSortedEdges(){

	Vertex.Edge[] edges=getEdges();
	//	for(Vertex.Edge test:edges)
	//	    System.out.println("EDGE:"+test);
	List<Vertex.Edge> list=new MergeSort<Vertex.Edge>(edges).sort();

	Vertex.Edge[] retval = list.toArray(new Vertex.Edge[list.size()]);

	/*	Vertex.Edge[] retval=new Vertex.Edge[list.size()];
	for(int i=0;i<list.size();i++)
	    retval[i]=list.get(i);
	*/

	return retval;
    }

    private Vertex getVertex(){
	Iterator<Map.Entry<String,Vertex>> vertexs=vertexMap.entrySet().iterator();
	if(vertexs.hasNext())
	    return vertexs.next().getValue();
	return null;
    }

    //HERE
    public Vertex.Edge[] getEdges(){

	List<Vertex.Edge> edges;
	Vertex root=getVertex();//get random Vertex from the VertexMap
	Queue q=new Queue();
	List<Vertex> visited=new ArrayList<Vertex>();
	visited.add(root);//add root to return List

	edges=root.getEdgeList();
	for(Vertex.Edge e:edges){//add root's edges to Queue
	    q.add(e);
	    //	    System.out.println("Added to Queue: "+e);
	}
	while(q.peek()!=null){
	    Vertex.Edge temp=(Vertex.Edge)q.poll();
	    if(visited.contains(temp.getTarget()))//that target Vertex is already hit
		continue;
	    //	    System.out.println("BFS:Vertex::"+temp.getTarget());
	    visited.add(temp.getTarget());
	    List<Vertex.Edge> edgesL=temp.getTarget().getEdgeList();
	    edges.addAll(edgesL);
	    for(Vertex.Edge e:edgesL)//add edges to Queue
		q.add(e);
	}
	return edges.toArray(new Vertex.Edge[edges.size()]);
    }

    public Vertex getVertex(String name){
	return (Vertex)vertexMap.get(name);
    }
    
    public void addVertex(Good good){

	Vertex v=new Vertex(good);//pass the Name+Map object
	vertexMap.put( (String)(good.getKey()),v);
	
    }

    public void connectNodes(Vertex a,Vertex b){

	Double weight=r.nextDouble();
	
	a.addEdge(weight,b);
	b.addEdge(weight,a);

    }

    public UndirectedGraph(){
	vertexMap=new HashMap<String,Vertex>(128,(float)0.5);
	r=new Random();
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

    public List<Vertex> dfs(Vertex root){

	if(root==null)
	    return null;
	
	List<Vertex> retval=new ArrayList<Vertex>();
	Stack s=new InfiniteStack();
	List<Vertex.Edge> edges=root.getEdgeList();
	for(Vertex.Edge e:edges)
	    s.push(e);
	//	System.out.println("Root:"+root);
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
		//System.out.println("Vertex: "+current.getTarget());
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

	if(root==null)
	    return null;

	Queue q=new Queue();
	List<Vertex> retval=new ArrayList<Vertex>();
	retval.add(root);//add root to return List

	//	System.out.println("BFS:Vertex::"+root);
	List<Vertex.Edge> edges=root.getEdgeList();
	for(Vertex.Edge e:edges){//add root's edges to Queue
	    q.add(e);
	    //	    System.out.println("Added to Queue: "+e);
	}
	while(q.peek()!=null){
	    Vertex.Edge temp=(Vertex.Edge)q.poll();
	    if(retval.contains(temp.getTarget()))//that target Vertex is already hit
		continue;
	    //	    System.out.println("BFS:Vertex::"+temp.getTarget());
	    retval.add(temp.getTarget());
	    List<Vertex.Edge> edgesL=temp.getTarget().getEdgeList();
	    for(Vertex.Edge e:edgesL)//add edges to Queue
		q.add(e);
	}
	return retval;
    }

    public static void main(String args[]){

	GraphInterface g=new UndirectedGraph();

	List<Good> goods=new ArrayList<Good>();
	//	goods.add(new Good<String,HashMap>("AU",new HashMap<String,Double>(16,(float)0.5)).addParameter("Price per ounce", 69.69));
	goods.add(new Good<String,HashMap>("AU",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("AG",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("GOOG",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("AAPL",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("INTL",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("IBM",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("PIGFEET",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("PORKLOIN",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("CHICKENFEET",new HashMap<String,Double>(16,(float)0.5)));
	goods.add(new Good<String,HashMap>("GAS",new HashMap<String,Double>(16,(float)0.5)));

	for(Good good:goods)
	    g.addVertex(good);

	//	System.out.println(g.toString());
	
	//Add edge connecting AU & AG
	g.connectNodes(g.getVertex("AU"),g.getVertex("AG"));
	g.connectNodes(g.getVertex("GOOG"),g.getVertex("AU"));
	g.connectNodes(g.getVertex("GOOG"),g.getVertex("AAPL"));
	g.connectNodes(g.getVertex("GOOG"),g.getVertex("INTL"));
	g.connectNodes(g.getVertex("GOOG"),g.getVertex("IBM"));
	g.connectNodes(g.getVertex("AAPL"),g.getVertex("INTL"));
	g.connectNodes(g.getVertex("AAPL"),g.getVertex("IBM"));
	g.connectNodes(g.getVertex("INTL"),g.getVertex("IBM"));
	g.connectNodes(g.getVertex("PIGFEET"),g.getVertex("AU"));
	g.connectNodes(g.getVertex("PIGFEET"),g.getVertex("PORKLOIN"));
	g.connectNodes(g.getVertex("PIGFEET"),g.getVertex("CHICKENFEET"));
	g.connectNodes(g.getVertex("PORKLOIN"),g.getVertex("CHICKENFEET"));
	g.connectNodes(g.getVertex("GAS"),g.getVertex("AU"));

	
	{//TEST DFS
	    List<Vertex> dfsTraversed = g.dfs(g.getVertex("AU"));
	    System.out.println("\r\nDFS:");
	    for(Vertex v:dfsTraversed)
		System.out.println(v);
	}

	{//TEST BFS
	    List<Vertex> bfsTraversed = g.bfs(g.getVertex("AU"));
	    System.out.println("\r\nBFS:");
	    for(Vertex v:bfsTraversed)
		System.out.println(v);
	}


	{//TEST EDGE SORT
	    Vertex.Edge[] sortedEdges=g.getSortedEdges();//get all Edges!
	    System.out.println("\r\nSorted EDGES:");
	    for(Vertex.Edge e:sortedEdges)
		System.out.println(e);
	}
	

	{//TEST MIN-SPANNING-TREE.Kruskal
	    UndirectedGraph graph=(UndirectedGraph)g;
	    List<Vertex.Edge> minspantree=MinimumSpanningTree.kruskal(graph);
	    System.out.println("\r\nKruskal Min. Spanning Tree");
	    int i=0;
	    for(Vertex.Edge e:minspantree){
		System.out.println(i+++":"+e);
	    }
	}
	
    }
    
}
