package com.brianthetall.cs;

import org.junit.*;

public class UndirectedGraphTest{

    private UndirectedGraph graph;

    @Before public void setup(){
	graph=new UndirectedGraph();
    }

    @Test public void dfs(){}
    @Test public void bfs(){}

    /**
     * Test UndirectedGraph.connectNodes(Vertex,Vertex)
     */
    @Test public void connectNodes(){
	
    }

    /**
     * Test UndirectedGraph.addVertex(Good)
     * @see Good
     */
    @Test public void addVertex()throws Exception{
	populate();
	
    }

    /**
     * Vertex getVertex(String)
     */
    @Test public void getVertex(){

    }

    /**
     * Vertex.Edge[] getEdges()
     */
    @Test public void getEdges(){}

    /**
     * Vertex.Edge[] getSortedEdges
     */
    @Test public void getSortedEdges(){}

    /**
     * Test: int UndirectedGraph.size()
     */
    @Test public void size(){}

    private void populate()throws Exception{
	Good[] goods=Injectables.goodArray();
	for(Good g:goods)
	    graph.addVertex(g);
	
    }
}
