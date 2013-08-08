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

	@Bean public HashMap hashmap(){
	    Random r=new Random();
	    HashMap<String,Integer> map=new HashMap<String,Integer>();
	    String[] s=strings();
	    for(String n:s){
		System.out.println(n);
		map.put(n,new Integer(r.nextInt()));//HERE NullPE
	    }
	    return map;
	}

	@Bean public String[] strings(){
	    /*
	    Random r=new Random();
	    String[] retval=new String[8];
	    for(String s:retval)
		s=new String(new Integer(r.nextInt()).toString());
	    */
	    String[] retval=new String[8];
	    for(String s:retval)
		s=new String("CannedString!");
	    return retval;
	}

    }
    
    private HashMap map;

    @Before public void setup(){

	AnnotationConfigApplicationContext di=new AnnotationConfigApplicationContext("com.brianthetall.cs");
	
	map=(HashMap)di.getBean("hashMap");

    }

    @Test public void print(){
	System.out.println(map);
    }

}
