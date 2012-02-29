import java.net.*;
import java.io.*;
public class socketSimple{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public socketSimple(){
	try{
	    socket=new Socket();
	}catch(Exception e){System.out.println(e.getMessage());}
    }
    public socketSimple(String site,int port){
	String s;
	try{
	    //	    System.out.println("INET:"+InetAddress.getByName("http://google.com").toString());
	    socket=new Socket(site,port);
	    in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    out=new PrintWriter(socket.getOutputStream());
	    out.println("HelloFromClient");
	    out.flush();
	    s=in.readLine();
	    System.out.println("fromSERVER:"+s);
	}
	/*
catch(Unkno/*HERE!?!?!?!!?!?Exeption e){}
catch(Exeption e){}
catch(Exeption e){}*/
catch(Exception e){System.out.println("EOOR"+e.getMessage());}
    }
    public String toString(){
	return (socket.toString());
    }
    public static void main(String args[]){
	socketSimple ss=new socketSimple(args[0],80);
	System.out.println(ss.toString());
    }
}