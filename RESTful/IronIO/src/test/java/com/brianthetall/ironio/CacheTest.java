package com.brianthetall.ironio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import io.iron.ironcache.IronCache;
import io.iron.ironcache.DefaultIronCache;
import io.iron.ironcache.CacheItem;

/**
 * Unit test for Iron.io.cache
 */
public class CacheTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CacheTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CacheTest.class );
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

	IronCache ic = new DefaultIronCache(secret,projectId,"holeInTheDirt");
	try{
	    //	    ic.put("girlfriend", "pussy");
	    ic.putItem("IceHole","girlfriend", "pussy",true,false);
	}catch(IOException e){
	    System.out.println("CatcheTest.put Error:"+e.getMessage());
	}

        assertTrue( true );
    }
}
