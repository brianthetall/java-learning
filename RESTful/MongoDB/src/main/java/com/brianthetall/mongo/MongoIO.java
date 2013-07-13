package com.brianthetall.mongo;

import java.lang.NullPointerException;
import java.util.StringTokenizer;
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
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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

    /**
     * Return JSON string containing Databases
     * TESTED
     */
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
     * TESTED
     */
    public ArrayList<MongoCollection> lsCollections(String database){

	String jsonResult=null;
	try{
	    jsonResult = client.get("/databases/"+database+"/collections");
	    //	    System.out.println("MongoIO.jsonResult="+jsonResult);
	}catch(IOException e){
	    System.out.println("Error: lsCollections "+e.getMessage());
	    return null;
	}

	ArrayList<MongoCollection> retval = new ArrayList<MongoCollection>();
	JsonFactory jf = new JsonFactory();
	try{
	    
	    JsonParser jp = jf.createJsonParser(jsonResult);

	    JsonToken jt=jp.nextToken();
	    while(jt != null){
		//		System.out.println("MongoIO: TokenName="+jt.toString());
		if( jt == JsonToken.END_OBJECT ){
		    
		}else if( jt == JsonToken.START_ARRAY){
		    
		    String value=null;
		    while( ( value=jp.nextTextValue() ) != null){
		    
			retval.ensureCapacity(retval.size()+1);
			retval.add(new MongoCollection(value));
		    }
		    
		}
		jt=jp.nextToken();
	    }

	    jp.close();

	}catch(JsonParseException e){
	    System.out.println(e.getMessage());
	}catch(IOException e){
	    System.out.println(e.getMessage());
	}
	retval.trimToSize();
	return retval;
    }

    /**
     * List Documents within specified collection
     * @return String[] of JSON documents
     * TESTED: without using args! 
     */
    public String[] lsDocuments(String database,String collection,String[] args){

	ArrayList<String> buffer=new ArrayList<String>();
	String jsonResult=null;
	try{
	    jsonResult = client.get("/databases/"+database+"/collections/"+collection);

	}catch(IOException e){
	    System.out.println(e.getMessage());
	}

	//Find char-indexes of the commas that are surrounded by } {
	//delimit by "},{"
	int cursor=2;//skip START JSON symbol
	int indexOfInterest=0;

	//	System.out.println("Length="+jsonResult.length());
	while(true && jsonResult.length()>4){
	    
	    indexOfInterest = jsonResult.indexOf("} , {",cursor+5);
	    if(indexOfInterest!=-1){
		//		System.out.println("Cursor="+cursor+" IndexOfInterest="+indexOfInterest);
		//System.out.println(jsonResult.substring(cursor,indexOfInterest+1));
		buffer.ensureCapacity(buffer.size()+1);
		buffer.add(jsonResult.substring(cursor,indexOfInterest+1));
		cursor=indexOfInterest+4;
	    }else{

		indexOfInterest = jsonResult.indexOf("} ]");
		if( indexOfInterest!=-1 ){
		    //System.out.println(jsonResult.substring(cursor,indexOfInterest+1));
		    buffer.ensureCapacity(buffer.size()+1);
		    buffer.add(jsonResult.substring(cursor,indexOfInterest+1));
		    cursor=indexOfInterest;
		    if(cursor==indexOfInterest){
			//System.out.println("abouttoBreak");
			break;
		    }
		}

	    }
	}
	buffer.trimToSize();
	String[] retval=new String[buffer.size()];
	try{
	    buffer.toArray(retval);
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
	return retval;
    }

    /**
     * Pull DB document
     * @return JSON representation of Document
     * TESTED
     */
    public String getDocument(String database,String collection,String docId){

	try{
	    return client.get("/databases/"+database+"/collections/"+collection+"/"+docId );
	}catch(IOException e){
	    System.out.println("Error: lsCollections "+e.getMessage());
	    return null;
	}


    }

    /**
     * TESTED
     * Insert Object into the Database (after converted using Gson)
     */
    public String insertDocument(String database,String collection,Object object){
	Gson gson=new Gson();
	String payload=gson.toJson(object);
	try{
	    client.post("/databases/"+database+"/collections/"+collection,payload);
	}catch(IOException e){
	    System.out.println("MongoIO.insertDocument Error:"+e.getMessage());
	}
	return null;
		    /*
		    POST /databases/{database}/collections/{collection}
		    Content-Type: application/json
		    Body: <JSON data>

		    */
    }

    /**
     * DELETE /databases/{database}/collections/{collection}/{_id}
     * TESTED
     */
    public String deleteDocument(String database,String collection,String docId){
	try{
	    return client.delete("/databases/"+database+"/collections/"+collection+"/"+docId );
	}catch(IOException e){
	    System.out.println("Error: lsCollections "+e.getMessage());
	    return null;
	}
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
