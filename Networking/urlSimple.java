import java.io.*;
import java.net.*;
public class urlSimple{
    private URL url;
    private URLConnection urlc;
    private InputStream urlis;
    private InputStreamReader urlisr;
    private BufferedReader br;
    public urlSimple(){
	String temp;
	try{
	    url=new URL("https://www.google.com");
	    urlc=url.openConnection();
	    urlis=urlc.getInputStream();
	    urlisr=new InputStreamReader(urlis);
	    br=new BufferedReader(urlisr);
	    while((temp=br.readLine())!=null)
		{
		    System.out.println(temp);
		}
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
    }
    
    public urlSimple(String s){
	String temp;
	try{
	    url=new URL(s);
	    urlc=url.openConnection();
	    urlis=urlc.getInputStream();
	    urlisr=new InputStreamReader(urlis);
	    br=new BufferedReader(urlisr);
	    while((temp=br.readLine())!=null)
		{
		    System.out.println(temp);
		}
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
    }
    
    public static void main(String args[]){
	new urlSimple();
	new urlSimple(args[0]);
    }
}