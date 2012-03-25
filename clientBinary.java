import java.net.*;
import java.io.*;
public class clientBinary{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private static File imageFile,packetFile;
    private static FileOutputStream packetFileStream;
    private static FileInputStream imageFileStream;

    public clientBinary(){
	try{
	    socket=new Socket();
	}catch(Exception e){System.out.println(e.getMessage());}
    }

    public clientBinary(String site,int port){
	try{
	    socket=new Socket(site,port);
	    int temp;//debug output the image for sanity
	    try{
		while((temp=imageFileStream.read())!=-1){
		    System.out.print(temp);
		}
	    }catch(IOException e){System.out.println(e.getMessage());}
	}
	catch(Exception e){System.out.println(e.getMessage());}
    }

    public String toString(){
	return (socket.toString());
    }

    public static void main(String args[]){
	if(args.length!=2){
	    System.out.println("clientBinary <ServerIP> <ImageFile>");
	    System.exit(-1);
	}
	imageFile=new File(args[1]);//open image file that will be sent to SERVER
	try{
	    imageFileStream=new FileInputStream(imageFile);
	}catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}
	clientBinary ss=new clientBinary(args[0],80);
	System.out.println(ss.toString());
    }
}