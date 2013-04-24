package com.brianthetall.mongo;

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

    }

    public String lsCollections(String database){

    }

    /**
     * collection is a JSON string to be added to mongoDB
     */
    public String insertCollection(String collection){

    }

    /**
     * Pull DB document
     * @return JSON representation of Document
     */
    public String getDocument(String database,String collection){

    }

    /**
     * DELETE /databases/{database}/collections/{collection}/{_id}
     *
     */
    public String deleteDocument(String docId){

    }

    /**
     * POST /databases/{database}/runCommand
     * getLastError,getPrevError,ping,profile,repairDatabase,resetError
     * whatsmyuri,convertToCapped,distinct,findAndModify,geoNear
     * reIndex,collStats,dbStats
     */
    public String runCommand(String command){

    }

    public static void main(String args[]){

	if(args.length!=2){
	    System.out.println("MongoIO <ServerURL> <OAuthKey>");
	    System.exit(-1);
	}
	new MongoIO(args[0],args[1]);
    }
}
