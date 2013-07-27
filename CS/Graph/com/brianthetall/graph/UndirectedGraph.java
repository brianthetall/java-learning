package com.brianthetall.graph;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.String;
import java.lang.Double;

public class UndirectedGraph implements GraphInterface{

    Map<String,Good> vertexMap;
    
    public void addVertex(Good good){
	
	Vertex v=new Vertex(good);//pass the Name+Map object
	vertexMap.put( (String)(good.getKey()), (Good)good);
	
    }

    public UndirectedGraph(){
	vertexMap=new HashMap<String,Good>(128,(float)0.5);
    }

    public String toString(){
	String s="";
	//traverse Graph; print Each Vertex's toString()
	//traverse can only run once the edges have been finished
	//for now, use the HashMap iterator to list all vertexs

	//	s=s.concat(
	Iterator<Good> nodes=vertexMap.values().iterator();
	while(nodes.hasNext())
       	    s=s.concat(nodes.next().toString());

	return s;
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

    }

}
