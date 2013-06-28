import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class MergeSort<T extends Comparable>{

    List<T> list;

    public MergeSort(){
	this(256);
    }

    public MergeSort(int size){
	list=new ArrayList<T>(size);
    }

    public MergeSort(List<T> unorderedList){
	if(unorderedList!=null)
	    this.list=unorderedList;
    }

    //mergesort implementation
    public List<T> sort(){

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
	List<T> left=new MergeSort(list.subList(0,middle)).sort();
	List<T> right=new MergeSort(list.subList(middle,list.size())).sort();
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

	List<Double> sortedList = new MergeSort(input).sort();
	
	for(Double d:sortedList)
	    System.out.print(d+" ");

	System.out.println("");
    }
}
