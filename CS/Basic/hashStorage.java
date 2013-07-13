import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.*;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class hashStorage{

    public static void main(String args[]){

	{//TEST CONCURRENT HASH MAP
	    ConcurrentHashMap<String,Integer> chm = new ConcurrentHashMap<String,Integer>();
	    chm.put("One",new Integer("69"));
	    chm.put("Two",new Integer("420"));
	    chm.put("Three",new Integer("69420"));
	    
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try{
		ObjectOutputStream oos=new ObjectOutputStream(baos);
		oos.writeObject(chm);
		oos.flush();
		oos.close();
	    }catch(IOException e){ System.out.println("ERROR: hashStorage: "+e.getMessage()); }

	    byte[] chmBytes = baos.toByteArray();
	    System.out.println("Size Of Concurrent Hash MAp: "+chmBytes.length+"\r\nConcurrentHashMap:");

	    BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(chmBytes)));
	    String temp;
	    try{
		while( (temp=br.readLine()) != null)
		    System.out.println(temp);
	    }catch(IOException e){System.out.println("hashStorage ERR0R IOE "+e.getMessage());}

	}

	{//TEST HASH TABLE
	    Hashtable<String,Integer> ht=new Hashtable<String,Integer>();
	    ht.put("One",new Integer("69"));
	    ht.put("Two",new Integer("420"));
	    ht.put("Three",new Integer("69420"));
	    
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try{
		ObjectOutputStream oos=new ObjectOutputStream(baos);
		oos.writeObject(ht);
		oos.flush();
		oos.close();
	    }catch(IOException e){ System.out.println("ERROR: hashStorage: "+e.getMessage()); }

	    byte[] htBytes = baos.toByteArray();
	    System.out.println("Size Of Hash Table: "+htBytes.length+"\r\nHashtable:");

	    BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(htBytes)));
	    String temp;
	    try{
		while( (temp=br.readLine()) != null)
		    System.out.println(temp);
	    }catch(IOException e){System.out.println("hashStorage ERR0R IOE "+e.getMessage());}
	}
	
    }

}
