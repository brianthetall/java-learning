package com.brianthetall.cs;

import java.lang.String;

/**
 * Good - 
 */
public class Good<K,V extends java.util.Map>{
    
    private final K key;//String of name
    private V value;//Map of properties
    
    public K getKey(){return key;}
    
    public V getValue(){return value;}
    
    public Good(K key,V value){
	this.key=key;
	this.value=value;
    }

    /** Builder Pattern
     * Add a parameter into this Good's Map of properties
     */
    public <T extends Comparable> Good addParameter(String name,T t){
	value.put(name,t);
	return this;
    }

    public <T extends Comparable> T getParameter(String name){
	return (T)value.get(name);
    }

    public String toString(){

	String s=key.toString();
	s=s.concat(value.toString());
	
	return s;
    }
    
}
