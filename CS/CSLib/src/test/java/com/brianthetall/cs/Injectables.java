package com.brianthetall.cs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.util.Random;
import java.lang.Object;
import java.util.List;
import java.util.ArrayList;
import java.lang.Exception;

@Configuration public class Injectables{

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
