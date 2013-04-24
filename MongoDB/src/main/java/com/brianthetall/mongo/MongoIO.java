package com.brianthetall.mongo;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.JsonParseException;

/** REST-API Interface
 * Used for communicating with the MongoLab DB-service
 */
public class MongoIO{

    private Client client;

    /**
     * Called from Client-Class; after Client is setup
     */    
    MongoIO(Client c){
	if(c != null){
	    this.client=c;
	}else
	    System.err.println("Null Client used to make MongoIO");
    }


    public String lsDatabases(){
	try{
	    return client.get("/databases");
	}catch(IOException e){
	    System.out.println("Error: lsDatabases "+e.getMessage());
	    return null;
	}
    }

    /**
     * List tables in this DB
     */
    /*
    public String lsCollections(String database){

	try{
	    return client.get("/databases/"+database+"/collections");
	}catch(IOException e){
	    System.out.println("Error: lsCollections "+e.getMessage());
	    return null;
	}

    }
    */

    /**
     * List tables in this DB
     */
    public ArrayList<MongoCollection> lsCollections(String database){

	String jsonResult=null;
	try{
	    jsonResult = client.get("/databases/"+database+"/collections");
	    System.out.println("MongoIO.jsonResult="+jsonResult);
	}catch(IOException e){
	    System.out.println("Error: lsCollections "+e.getMessage());
	    return null;
	}

	ArrayList<MongoCollection> retval = new ArrayList<MongoCollection>();
	JsonFactory jf = new JsonFactory();
	try{
	    
	    JsonParser jp = jf.createJsonParser(jsonResult);
	    while(jp.nextToken() !=null){
		String temp=jp.nextValue().toString();

		if( temp.equals("END_ARRAY") ){

		    System.out.println("MongoIO:"+temp);

		}else if( temp.equals("VALUE_STRING") ){

		    System.out.println("MongoIO:"+temp);
		    jp.nextToken();
		    retval.ensureCapacity(retval.size()+1);//stupid
		    retval.add(new MongoCollection(jp.nextTextValue()));

		}
	    }
	}catch(JsonParseException e){
	    System.out.println(e.getMessage());
	}catch(IOException e){
	    System.out.println(e.getMessage());
	}
	
	return retval;
    }

    /**
     * collection is a JSON string to be added to mongoDB
     * SQL=Create Table
     */
    public String insertCollection(String collection){
	return null;
    }

    public String lsDocuments(String database,String collection,String[] args){
	
	return null;
    }

    /**
     * Pull DB document
     * @return JSON representation of Document
     */
    public String getDocument(String database,String collection){

	try{
	    return client.get("/"+database+"/collections/"+collection );
	}catch(IOException e){
	    System.out.println("Error: lsCollections "+e.getMessage());
	    return null;
	}


    }

    /**
     * DELETE /databases/{database}/collections/{collection}/{_id}
     *
     */
    public String deleteDocument(String docId){
	return null;
    }

    /**
     * POST /databases/{database}/runCommand
     * getLastError,getPrevError,ping,profile,repairDatabase,resetError
     * whatsmyuri,convertToCapped,distinct,findAndModify,geoNear
     * reIndex,collStats,dbStats
     */
    public String runCommand(String command){
    return null;
    }

}
