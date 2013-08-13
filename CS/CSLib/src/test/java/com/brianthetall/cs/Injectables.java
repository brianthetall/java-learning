package com.brianthetall.cs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.util.Random;
import java.lang.Object;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.lang.Exception;
import java.lang.String;

@Configuration public class Injectables{

    public static String[] stringArray(int size){
	Random r=new Random();
	String[] s=new String[size];
	for(int i=0;i<s.length;i++)
	    s[i]=new String(new Integer(r.nextInt()).toString());
	return s;
    }

    @Bean public static String[] stringArray()throws Exception{
	Random r=new Random();
	String[] s=new String[boundedInt(128,4096)];
	for(int i=0;i<s.length;i++)
	    s[i]=new String(new Integer(r.nextInt()).toString());
	return s;
    }

    @Bean public static Map<String,Double> map()throws Exception{
	Map<String,Double> map=new java.util.LinkedHashMap<String,Double>(128,(float)0.5);
	String[] names=stringArray();
	double[] values=doubleArray(names.length);
	for(int i=0;i<names.length;i++)
	    map.put(names[i],new Double(values[i]));
	return map;
    }

    @Bean public static Good[] goodArray()throws Exception{
	Good[] g=new Good[boundedInt(64,128)];
	String[] names=stringArray(g.length);
	for(int i=0;i<g.length;i++)
	    g[i]=new Good<String,Map>(names[i],map());
	return g;
    }

    @Bean public static UndirectedGraph undirectedGraph()throws Exception{
	UndirectedGraph graph=new UndirectedGraph();
	Good[] goods=goodArray();
	for(Good g:goods)
	    graph.addVertex(g);
	return graph;
    }

    /**
     * Return an Integer[] between 512 & 4096 elements
     * Their values are randomly assigned
     * @return Integer[] of random numbers
     */
    @Bean public static Integer[] integerArray()throws Exception{
	Integer[] i=new Integer[boundedInt(512,4096)];
	Random r=new Random();
	for(int o=0;o<i.length;o++)
	    i[o]=new Integer(r.nextInt());
	return i;
    }

    public static Integer[] integerArray(int size)throws Exception{
	if(size<=0)
	    throw new Exception("Injectables.integerArray(int size) Invalid input");
    	Integer[] i=new Integer[size];
	Random r=new Random();
	for(int q=0;q<i.length;q++)
	    i[q]=new Integer(r.nextInt());
	return i;
    }

    /**
     * Produce a bounded, POSITIVE random number
     * @param lower bound
     * @param upper bound
     * @return a random number bounded
     */
    public static int boundedInt(int lower,int upper)throws Exception{
	if(lower>upper || lower<0 || upper<0)
	    throw new Exception("Injectables.boundedInt: input error");
	Random r=new Random();
	while(true){
	    int i=r.nextInt();
	    i%=upper;//fit under upper-bound
	    if(i>lower)
		return i;//success
	}//repeat process if failure
    }
    
    /**
     * Generate a random number of Doubles between 128,16000 elements
     * @return populated list of Doubles
     */
    @Bean public static List<Double> doubleList()throws Exception{
	int size=boundedInt(128,1600);
	List<Double> list=new ArrayList<Double>(size);
	double[] numberList=doubleArray(size);
	for(double d:numberList)
	    list.add(d);
	return list;
    }
    
    /**
     * Generate 'size' random doubles
     * @return array of populated doubles
     */
    private static double[] doubleArray(int size){
	Random r=new Random();
	double[] d=new double[size];
	for(int i=0;i<d.length;i++)
	    d[i]=r.nextDouble();
	return d;
    }

    /**
     * Bean that contains a random positive int
     * Between 1 & 1024
     */
    @Bean public static int randomPositive()throws Exception{
	return boundedInt(1,1024);
    }

    /**
     * @return Provides an Array of Objects
     */
    @Bean public static Object[] objectArray(){
	Random r=new Random();
	int size=r.nextInt();
	size=size<0?size*=-1:size;//positive
	size=size==0?128:size;//non-zero
	size=size%16000;//under a limit
	Object[] o=new Object[size];
	for(int i=0;i<o.length;i++)
	    o[i]=new Object();
	return o;
    }

}
