package com.brianthetall.mongo;

import java.io.BufferedReader;
import java.io.IOException;

/** REST-API Interface
 * Used for communicating with the MongoLab DB-service
 */
public class MongoIO{

    private Client client;
    
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
	    System.out.println("Error: lsDatabases"+e.getMessage());
	    return null;
	}
    }

    public String lsCollections(String database){
	    return null;
    }

    /**
     * collection is a JSON string to be added to mongoDB
     */
    public String insertCollection(String collection){
    return null;
    }

    /**
     * Pull DB document
     * @return JSON representation of Document
     */
    public String getDocument(String database,String collection){
    return null;
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
