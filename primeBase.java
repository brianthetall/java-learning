//import java.io.*;
//import java.util.;
import java.lang.*; //string class

public class primeBase{

    private int prime;
    private int multiple;
    
    public primeBase(){
    }

    public primeBase(int prime){
	this.prime=prime;
    }
    
    public primeBase(int prime, int multiple){
	this.prime=prime;
	this.multiple=multiple;
    }

    public int getPrime(){
	return prime;
    }

    public int getMultiple(){
	return multiple;
    }

    public void setMultiple(int multiple){
	this.multiple=multiple;
    }
    
    public String toString(){
	String ret=new String();
	ret.concat("Prime=");
	ret.concat(String.valueOf(prime));
	ret.concat(" Multiple=");
	ret.concat(String.valueOf(multiple));
	return ret;
    }
}
