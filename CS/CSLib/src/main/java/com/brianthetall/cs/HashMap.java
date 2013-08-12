package com.brianthetall.cs;

import java.lang.Math;
import java.lang.String;
import java.lang.Integer;

public class HashMap <K,V>{

    private static final boolean DEBUG=true;

    public static class Entry<K,V>{
	
	private K k;
	private V v;

	public Entry(K k,V v){

	    if(k!=null){
		this.k=k;
		this.v=v;
	    }
		
	}

	public int hashCode(){
	    
	    int h=0;
	    h+=k.hashCode();
	    h*= 71;
	    h+=v.hashCode();
	    System.out.println("Key.hashCode()="+k.hashCode());
	    System.out.println("Value.hashCode()="+v.hashCode());

	    return h;

	}

	public boolean equals(Object o){
	    if(o==null)
		return false;
	    
	    if(!(o instanceof HashMap.Entry))
		return false;

	    Entry<K,V> e=(Entry<K,V>)o;

	    if(e.getKey().equals(this.getKey())){
		if(e.getValue().equals(this.getValue()))
		    return true;
	    }
	    
	    return false;
	    
	}

	public K getKey(){
	    return k;
	}
	
	public V getValue(){
	    return v;
	}

	public String toString(){
	    return k.toString()+":"+v.toString();
	}
	
    }
    //===========================start hash map============
    
    private int counter;
    private Entry[] data;

    /**
     * default size; power of 2
     */
    public HashMap(){
	this(128);
    }

    public HashMap(int elements){
	counter=0;
	//check that elements is a power of 2
	double log2result=Math.log(elements)/Math.log(2);
	boolean powerOfTwo = Double.compare(0.0,log2result - new Double(log2result).intValue())==0 ? true:false;

	if(powerOfTwo)
	    data=new Entry[elements];//ignore warning
	else
	    data=new Entry[128];//default size if they dont insert pow of 2
	    
    }

    public V get(Object key){

	V retval=null;

	for(int i=getIndex(key) ; i<data.length ; i++){
	    if(data[i]!=null){
		if(data[i].getKey().equals(key)){
		    if(DEBUG)System.out.println("Correct index="+i);
		    return (V)data[i].getValue();
		}
	    }
	      
	}

	for(int i=0 ; i < getIndex(key) ; i++){
	    if(data[i]!=null){
		if(data[i].getKey().equals(key)){
		    if(DEBUG)System.out.println("Correct index="+i);
		    return (V)data[i].getValue();
		}
	    }

	}

	System.err.println("No Such Key in Map");

	return retval;

    }

    /** put
     * @param key - key to hash
     * @param value - value to store
     * @return Old value for this key
     */
    public V put(K key,V value){

	if(counter > data.length/2){
	    resize();
	    return put(key,value);
	}
	else
	    counter++;

	int index=getIndex(key);

	Entry<K,V> e = new Entry<>(key,value);

	if(data[index]==null){//no collision
	    data[index]=e;
	    if(DEBUG)System.out.println("NoCollision:"+e);
	    return null;
	}
	else{//collision
	    for(int i=index;i<data.length;i++){
		if(data[i]!=null){
		    if(data[i].getKey().equals(key)){
			V retval=(V)data[i].getValue();
			data[i]=e;
			if(DEBUG)System.out.println("Overwrote-to:"+e.toString());
			return retval;
		    }
		}
		else{//data[i] == null
		    data[i]=e;
		    if(DEBUG)System.out.println("Collision:"+e.toString());
		    return null;
		}
	    }
	    for(int i=0;i<index;i++){
		if(data[i]!=null){
		    if(data[i].getKey().equals(key)){
			V retval=(V)data[i].getValue();
			data[i]=e;
			if(DEBUG)System.out.println("Overwrote-to:"+e.toString());
			return retval;
		    }
		}
		else{//data[i] == null
		    data[i]=e;
		    if(DEBUG)System.out.println("Collision:"+e.toString());
		    return null;
		}
	    }

	}
	//	System.err.println("NO ROOM for: "+e);
	resize();
	put(key,value);
	return data[getIndex(key)].getValue()==null ? null : (V)(data[getIndex(key)].getValue());
    }


    private int getIndex(Object key){
	int hash=key.hashCode();
	hash &= data.length-1;//mask hash to fit size of map
	return hash;
    }

    /**
     * @return the size of the underlying array
     */
    public int size(){
	return data.length;
    }

    /**
     * @return number of populated Entries in the array
     */
    public int population(){
	return counter;
    }

    public String toString(){

	String retval="";
	for(int i=0;i<data.length;i++){
	    if(data[i]!=null)
		retval=retval.concat("Index["+i+"] "+data[i].toString()+"\r\n");
	}
	return retval;
    }

    public Entry[] getArray(){
	return data;
    }

    /**
     * Double the size of the map & repopulate the map
     */
    private void resize(){

	System.out.println("Resizing Map, current size="+data.length);
	if(data.length*2 <= Integer.MAX_VALUE){

	    HashMap<K,V> newMap=new HashMap<>(data.length*2);
	    for(Entry<K,V> e : data){
		if(e!=null)
		    newMap.put(e.getKey(),e.getValue());
	    }
	    data = newMap.getArray();

	}
	else
	    System.err.println("Hashmap is a max-size!");

    }

    public static void main(String args[]){

	//cant have arrays of Generics... thanks Java.
	if(args.length!=1){
	    System.err.println("HashMap <size> (size must be a power of 2 or default will be 128");
	    System.exit(-1);
	}

	Integer size=new Integer(args[0]);
	HashMap<String,Integer> zero=new HashMap<String,Integer>(size.intValue());

	System.out.println("Map.length="+zero.size());
	zero.put(new String("brian"),new Integer(69));
	zero.put(new String("Brian"),new Integer(69));
	zero.put(new String("brian"),new Integer(68));//test the update of a key's value

	for(int i=0;i<Integer.MAX_VALUE;i++)
	//	for(int i=0;i<1000000;i++)
	    zero.put(new String(new Integer(i).toString()),new Integer(i));

	//	System.out.println(zero.toString());

	//TEST the HashMap.get()
	Integer value=zero.get(new String("Brian"));
	Integer value1=zero.get(new String("brian"));
	Integer v11=zero.get(new String("11"));

	/*
	System.out.println("Brian="+value);
	System.out.println("brian="+value1);
	System.out.println("11="+v11);
	*/

    }

}
