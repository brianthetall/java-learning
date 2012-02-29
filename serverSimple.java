import java.net.*;
import java.io.*;
public class serverSimple{
    private Socket socket;
    private ServerSocket server;
    private BufferedReader incoming;
    private PrintWriter outgoing;
    public serverSimple(){
	try{server=new ServerSocket(69);}
	catch(Exception e){System.out.println(e.getMessage());}
    }
    public serverSimple(int port){
	String s;
	try{
	    server=new ServerSocket(port);
	    socket=server.accept();
	    server.close();
	    incoming=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    outgoing=new PrintWriter(socket.getOutputStream());
	    outgoing.println("HelloFromSERVER");
	    outgoing.flush();
	    s=incoming.readLine();
	    System.out.println("FromClient:"+s);
	}
	catch(Exception e){System.out.println(e.getMessage());}
    }
    public static void main(String args[]){
	if(args.length==1)
	    new serverSimple((new Integer(args[0])).intValue());
    }
}
