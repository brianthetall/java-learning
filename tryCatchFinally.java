import java.io.*;
import java.lang.*;

public class tryCatchFinally{

    public static Object test(){
	try{
	    DataInputStream dis=new DataInputStream(new FileInputStream("tryCatchFinally.java"));
	    System.out.println("dis made:"+dis);
	    return dis;
	}catch(Exception e){
	    System.out.println("ERROR:"+e.getMessage());
	}finally{
	    return null;
	}
    }
    public static void main(String argv[]){
	if(test()==null)
	    System.out.println("WTF Finally is fucking powerful.");
	else
	    System.out.println("cool.");
    }

}
