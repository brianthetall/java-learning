/**
   because long is signed in java, this is only useful for 32-bit numbers
 */
import java.lang.Long;
import java.lang.Math.*;
public class IsPrime{

    private Long number;

    public IsPrime(Long number){
	this.number=number;
    }
    
    public boolean isPrime(){
	if(number==null)
	    return false;
	else if(number==1)
	    return false;
	else if(number==2)
	    return true;
	else if(number%2==0)
	    return false;
	long n=number.longValue();
	int root=(int)Math.sqrt((double)n);
	for(int i=3 ; i<=root ; i+=2){
	    if(n%i==0)
		return false;
	}
	return true;
    }

    public static void main(String args[]){
	if(args.length==0)
	    System.err.println("IsPrime <number>\r\n");
	System.out.println("Input:"+args[0]);
	try{
	    boolean isPrime=new IsPrime(new Long(args[0])).isPrime();
	    System.out.println("Result: "+isPrime);
	}catch(NumberFormatException e){
	    System.err.println("Invalid Input: "+e.getMessage());
	}
    }

}
