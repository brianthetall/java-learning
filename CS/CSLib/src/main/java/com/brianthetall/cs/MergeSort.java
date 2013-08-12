package com.brianthetall.cs;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class MergeSort{

    //    private List<T> list;
    private MergeSort(){}

    /*
    public MergeSort(List<T> unorderedList){
	if(unorderedList!=null)
	    this.list=unorderedList;
    }

    public MergeSort(T[] unorderedArray){
	list=new ArrayList<T>(unorderedArray.length);
	for(T t:unorderedArray)
	    list.add(t);
    }
    */

    /**
     * Perform mergesort; Nlog(N)
     * @return Sorted List<T>
     */
    public static <T extends Comparable> List<T> sort(List<T> list){

	if(list==null)
	    return null;

	else if(list.size()==1)
	    return list;

	else if(list.size()==2){
	    List<T> retval=new ArrayList<T>(2);
	    boolean dontCare = ( list.get(0).compareTo(list.get(1)) > 0 ) ? retval.add(list.get(1)) : retval.add(list.get(0));
	    boolean b=retval.get(0).equals(list.get(0)) ? retval.add(list.get(1)):retval.add(list.get(0));
	    return retval;
	}

	int middle=list.size()/2;
	//	List<T> left=new MergeSort(list.subList(0,middle)).sort();
	//	List<T> right=new MergeSort(list.subList(middle,list.size())).sort();
	List<T> left=MergeSort.sort(list.subList(0,middle));
	List<T> right=MergeSort.sort(list.subList(middle,list.size()));
	int l_index=0,r_index=0;
	List<T> retval=new ArrayList<T>(list.size());

	while(l_index<left.size() && r_index<right.size()){
	    boolean b= (left.get(l_index).compareTo(right.get(r_index))) > 0 ?retval.add(right.get(r_index++)) :retval.add(left.get(l_index++));
	}
	if(l_index>=left.size()){
	    for(int i=r_index;i<right.size();i++)
		retval.add(right.get(i));
	}else if(r_index>=right.size()){
	    for(int i=l_index;i<left.size();i++)
		retval.add(left.get(i));
	}

	return retval;
    }

    public static void main(String args[]){
	List<Double> input = new ArrayList<>(args.length);
	for(int i=0;i<args.length;i++)
	    input.add(new Double(args[i]));
	List<Double> sortedList = MergeSort.sort(input);
	for(Double d:sortedList)
	    System.out.print(d+" ");
	System.out.println("");
    }
}
