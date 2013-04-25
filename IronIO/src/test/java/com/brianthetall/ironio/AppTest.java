package com.brianthetall.ironio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import io.iron.ironmq.Client;
import io.iron.ironmq.Queue;
import io.iron.ironmq.Message;

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
	String projectId=null;
	//	"/ho
	try{

	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/home/ubuntu/ironIO.client.secret"))));
	    secret = br.readLine();
	    projectId = br.readLine();
	    System.out.println("Secret:"+secret+" ProjectID="+projectId);
	}catch(Exception e){
	    System.out.println("Error opening secrets file");
	}

       	IronMQ q = new IronMQ(new Client(projectId,secret),"VirginQueue");
	q.push("MessageApeShit");
	
	Message m;
	while( (m=q.pop()) != null){
	    System.out.println("ID:"+m.getId()+" Message: "+m.toString());
	    q.delete(m.getId());
	}

        assertTrue( true );
    }
}
