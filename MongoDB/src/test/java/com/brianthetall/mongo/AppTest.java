package com.brianthetall.mongo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

	MongoIO mio = new Client("A3M-u8otI1GJrTfv6pO49c93E8c1ORTZ").getMongoIO();
	System.out.println(mio.lsDatabases());

        assertTrue( true );
    }
}
