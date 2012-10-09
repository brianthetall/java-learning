public class primeDecomposition{

    private primeBase primeBase;
    
    public primeDecomposition(){
	primeBase = new primeBase(1);
    }

    public static void main(String args[]){
	for(int i=0;i<args.length;i++){
	    System.out.printf("ARG[%d]=%s\r\n",i,args[i]);
	    //make sure args[0] is a number
	    //args[0] is the largest prime to use as a base
	    //make sure it is prime
	    
	}
    }
}