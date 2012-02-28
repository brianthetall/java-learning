import java.net.*;
import java.io.*;
public class socketSimple{
    private Socket sock;
    public socketSimple(){
	try{
	    sock=new Socket();
	}catch(Exception e){System.out.println(e.getMessage());}
    }
    public socketSimple(String site,int port){
	try{
	    //	    System.out.println("INET:"+InetAddress.getByName("http://google.com").toString());
	    sock=new Socket(site,port);
	}catch(Exception e){System.out.println("EOOR"+e.getMessage());}
    }
    public String toString(){
	return (sock.toString());
    }
    public static void main(String args[]){
	socketSimple ss=new socketSimple(args[0],80);
	System.out.println(ss.toString());
    }
}