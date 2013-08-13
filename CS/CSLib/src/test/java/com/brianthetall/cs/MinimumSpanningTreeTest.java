package com.brianthetall.cs;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Test Min. Spanning Tree Class
 * MST is a static class: Kruskal
 */
public class MinimumSpanningTreeTest{

    @Autowired com.brianthetall.cs.UndirectedGraph graph;

    @Test public void kruskal(){

	AnnotationConfigApplicationContext spring=new AnnotationConfigApplicationContext("com.brianthetall.cs");
	
	graph=(UndirectedGraph)spring.getBean("undirectedGraph");

	System.out.println("MSTTest.graph:"+graph);
	assert(graph!=null);

    }

}
