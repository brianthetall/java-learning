package com.brianthetall.cs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Assert;
import java.util.Random;
import java.lang.Integer;
import java.lang.String;

public class HashMapTest{

    @Configuration public static class Injectables{

	public static final int TEST_SIZE=512;
	private static String[] testKeys;
	private static Integer[] testValues;

	@Bean public HashMap hashmap(){
	    Random r=new Random();
	    HashMap<String,Integer> map=new HashMap<String,Integer>();
	    testKeys=strings();
	    testValues=new Integer[testKeys.length];
	    for(int i=0;i<testKeys.length;i++){
		testValues[i]=new Integer(r.nextInt());
		map.put(testKeys[i],testValues[i]);
	    }
	    return map;
	}

	@Bean public String[] strings(){
	    
	    Random r=new Random();
	    String[] retval=new String[TEST_SIZE];
	    //	    for(String s:retval)//this method leaves retval full of nulls....
	    for(int i=0;i<retval.length;i++)
		retval[i]=new String("CannedString:"+new Integer(r.nextInt()).toString());
	    return retval;
	}

	public static String[] getKeys(){
	    return testKeys;
	}

	public static Integer[] getValues(){
	    return testValues;
	}

    }
    
    private HashMap map;

    @Before public void setup(){

	AnnotationConfigApplicationContext di=new AnnotationConfigApplicationContext("com.brianthetall.cs");
	map=(HashMap)di.getBean("hashmap");

    }

    @Test public void print(){
	System.out.println(map);
    }

    @Test public void size(){
	assert(map.population() == Injectables.TEST_SIZE);
    }

    /**
     * 
     *
     */
    @Test public void get(){
	String[] s=Injectables.getKeys();
	for(int i=0;i<s.length;i++){
	    Integer value=(Integer)map.get(s[i]);
	    assert(value.equals(Injectables.getValues()[i]));
	}
    }

    @Test public void getArray(){
	String[] keys=Injectables.getKeys();
	HashMap.Entry[] entries=map.getArray();
	//verify that each of the keys that was used during construction are in this Entry[] obtained from the hashmap
	
    }    

}
