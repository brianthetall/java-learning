/*takes an input file from commandline
generates an array of objects; stores that data in the file
outputs the toString() of the betterClass objects
*/
import java.io.*;
import java.util.*;//want arrayList
public class readDatabase{
    private static final int SIZE=64;
    private int size;
    private ArrayList<betterClass> dbase;
    private int filePosition;
    private File f;
    private FileReader fread;
    private BufferedReader bread;
    public readDatabase(){//this is not complete
	dbase=new ArrayList<betterClass>(SIZE);
    }
    public readDatabase(File f){
	size=getNumberOfLines(f);//determine number of lines in file
	//	System.out.println(size);
	this.f=f;
	try{
	    fread=new FileReader(f);
	    bread=new BufferedReader(fread);//buffered reader for file is ready
	    dbase=new ArrayList<betterClass>(size);//create arraylist accordingly
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
    }
    public int getSize(){return size;}
    public betterClass getObject(int index){
	return(dbase.get(index));
    }
    public void readFile(){//assumes the object has been initalized (with constructor)
	for(int i=0;i<size;i++)	//parse file line by line & populate arraylist
	    {
		//		System.out.println("FGETS:"+fgets());//fgets
		dbase.add(new betterClass(fgets()));
	    }
    }
    private String fgets(){
	String retval="";
	try{
	    retval=bread.readLine();
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
	return retval;
    }
    private int getNumberOfLines(File f){
	int i,temp,numLines=0;
	try{
	    FileReader fr=new FileReader(f);
	    while((temp=fr.read())!=-1){
		//	System.out.printf("%c",temp);
		if(temp=='\n')
		    numLines++;
	    }
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
	return numLines;
    }
    public static void main(String args[]){
	readDatabase rd=new readDatabase(new File(args[0]));
	rd.readFile();//have file read into arraylist
	//read it back to the terminal
	for(int i=0;i<rd.getSize();i++)
	    {
		System.out.println(rd.getObject(i).toString());
	    }
    }
}