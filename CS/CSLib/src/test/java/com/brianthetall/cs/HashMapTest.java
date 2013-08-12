package com.brianthetall.cs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Assert;
import java.util.Random;
import java.lang.Integer;
import java.lang.String;
import java.util.Iterator;

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

    @After public void unsetup(){
	map=null;
    }

    @Ignore @Test public void print(){
	System.out.println(map);
    }

    /**
     * Verify that the number of map-entries is correct
     */
    @Test public void size(){
	assert(map.population() == Injectables.TEST_SIZE);
	map.remove(Injectables.getKeys()[0]);	//remove some entries
	map.remove(Injectables.getKeys()[1]);
	map.remove(Injectables.getKeys()[2]);
	assert(map.population() == Injectables.TEST_SIZE-3);
	map.put(Injectables.getKeys()[0],Injectables.getValues()[0]);
	map.put(Injectables.getKeys()[1],Injectables.getValues()[1]);
	map.put(Injectables.getKeys()[2],Injectables.getValues()[2]);
	map.put("Key_Random0",Injectables.getValues()[0]);
	map.put("Key_Random1",Injectables.getValues()[1]);
	map.put("Key_Random2",Injectables.getValues()[2]);
	assert(map.population() == Injectables.TEST_SIZE+3);
    }

    /**
     * Test map's ability to get
     * And verify the contents of the map
     * Uses key and value arrays generated when map was built
     */
    @Test public void get(){
	String[] s=Injectables.getKeys();
	for(int i=0;i<s.length;i++){
	    Integer value=(Integer)map.get(s[i]);
	    assert(value.equals(Injectables.getValues()[i]));
	}
    }

    /**
     * 
     */
    @Ignore @Test public void put(){
	
    }

    /**
     * Verify that the Entry[] returned by getArray:
     * Has the correct size
     */
    @Ignore @Test public void getArray(){
	String[] keys=Injectables.getKeys();
	HashMap.Entry[] entries=map.getArray();
	System.out.println("Enrtries.length="+entries.length+" Keys.lengtth="+keys.length);

    }

    @Ignore @Test public void iterator(){
	Iterator<HashMap.Entry> i = map.iterator();
	int size=0;
	while(i.hasNext()){
	    i.next();
	    size++;
	}
	assert(size==Injectables.getKeys().length);
    }

}
