import java.lang.Math;
import java.lang.String;
import java.lang.Integer;

public class HashMap <K,V>{

    private K key;
    private V value;

    /**
     * default size; 
     */
    public HashMap(){
	this(128);
    }

    public HashMap(int elements){
	//check that elements is a power of 2
	double log2result=Math.log(elements)/Math.log(2);
	boolean powerOfTwo = Double.compare(0.0,log2result - new Double(log2result).intValue())==0 ? true:false;

    }

    public V get(Object key){
	V retval=null;

	return retval;
    }

    /** put
     * @param key - key to hash
     * @param value - value to store
     * @return Old value for this key
     */
    public V put(K key,V value){
	
	return null;
    }

    public static void main(String args[]){

	//	HashMap<String,Integer>[] maps=new HashMap<String,Integer>[args.length];
	//cant have arrays of Generics... thanks Java.

	HashMap<String,Integer> zero=new HashMap<String,Integer>(2048);
	HashMap<String,Integer> one=new HashMap<String,Integer>(1723);
	
    }

}
