package com.brianthetall.mongo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
	String secret=null;

	try{
	    FileInputStream fis = new FileInputStream(new File("/home/ubuntu/client.secret"));
	    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	    secret=br.readLine();

	}catch(IOException e){
	    System.out.println("AppTest: IOError opening secrets file");
	}catch(Exception e){
	    System.out.println("AppTest: Error opening secrets file");
	}

	MongoIO mio = new Client(secret).getMongoIO();
	System.out.println(mio.lsDatabases());

	{//insert documents into Collections
	    mio.insertDocument("gludb","Two",mio);
	    mio.insertDocument("gludb","zero",mio);
	}


	{//pull/delete Document(s) from DB
	    System.out.println( "Pulled From Zero by ID:"+mio.getDocument("gludb","zero","5178b5a9e4b0fd93d3620523") );
	    System.out.println("Deleted By ID:"+mio.deleteDocument("gludb","zero","5178b5a9e4b0fd93d3620523"));
	}

	{//print Collections & Contents of each
	    ArrayList<MongoCollection> collections = mio.lsCollections("gludb");
	    Iterator<MongoCollection> it=collections.iterator();

	    System.out.println("AppTest: Collections:");
	    while(it.hasNext()){
		String collectionName=it.next().toString();
		System.out.println("\r\n"+collectionName+" Documents:" );
		
		String[] documentList = mio.lsDocuments("gludb",collectionName,null);//not passing args
		if(documentList.length==0)
		    System.out.println("Empty Collection :-(");
		for(int i=0;i<documentList.length;i++)
		    System.out.println(documentList[i]);
	    }
	}


        assertTrue( true );
    }
}
