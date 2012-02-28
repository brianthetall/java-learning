import java.io.*;
import java.net.*;
public class urlSimple{
    private URL url;
    private URLConnection urlc;
    private InputStream urlis;
    public urlSimple(){
	try{
	    url=new URL("https://www.google.com");
	    urlc=url.openConnection();
	    urlis=urlc.getInputStream();
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
    }
    public urlSimple(String s){
	try{
	    url=new URL(s);
	    urlc=url.openConnection();
	    urlis=urlc.getInputStream();
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
    }
    public static void main(String args[]){
	new urlSimple();
	new urlSimple(args[0]);
    }
}