/*
Recieves binary image
Returns 16 byte packet
*/
import java.net.*;
import java.io.*;
public class serverBinary{
    private Socket socket;
    private ServerSocket server;
    private DataInputStream dis;
    private DataOutputStream dos;
    private File imageFile,packetFile;
    private FileOutputStream imageFileStream;
    private FileInputStream packetFileStream;

    public serverBinary(){
	try{server=new ServerSocket(69);}
	catch(Exception e){System.out.println(e.getMessage());}
    }

    public serverBinary(int port){
	try{
	    server=new ServerSocket(port);
	    socket=server.accept();
	    server.close();
	    dis=new DataInputStream(socket.getInputStream());//for Recieving the Image
	    dos=new DataOutputStream(socket.getOutputStream());//for sending the Packet
	    imageFile=new File("SERVERzero.bin");//create File to store image From Client
	    imageFileStream=new FileOutputStream(imageFile);
	    try{
		while(true){//dis.readByte will throw EOFException when empty
		    //		    imageFileStream.write(dis.readByte());//write each byte into a waiting FILE...
		    System.out.print(dis.readByte());
		}
	    }catch(EOFException e){
		System.out.println("EOF"+e.getMessage());
	    }catch(IOException e){
		System.out.println(e.getMessage());
	    }

	    //	    incoming=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    //	    outgoing=new PrintWriter(socket.getOutputStream());
	}
	catch(Exception e){System.out.println(e.getMessage());}
    }

    public static void main(String args[]){
	if(args.length==1)
	    new serverBinary((new Integer(args[0])).intValue());
    }
}
