package com.brianthetall.mongo;

import java.lang.StringBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * The Client class provides access to the MongoLab service
 */

public class Client {

    static final private boolean DEBUG=true;
    static final private String apiVersion = "1";
    static final private String initPath = "/api/";
    static final Random rand = new Random();
    private String token;
    private Cloud cloud;
    static {//prevent old SSL from being used
        System.setProperty("https.protocols", "TLSv1");
    }

    /**
     * @param token OAuth token.
     */
    public Client(String token) {
        this(token, Cloud.ml);
    }

    /**
     * @param token An OAuth token.
     * @param cloud The cloud to use.
     */
    public Client(String token, Cloud cloud) {
        this.token = token;
        this.cloud = cloud;
    }

    /**
     * Returns a MongoIO
     * @param name The name of the Queue to create.
     */
    public MongoIO getMongoIO() {
        return new MongoIO(this);
    }

    String delete(String endpoint) throws IOException {
        return request("DELETE", endpoint, null);
    }

    String get(String endpoint) throws IOException {
        return request("GET", endpoint, null);
    }

    String post(String endpoint, String body) throws IOException {
        return request("POST", endpoint, body);
    }

    String put(String endpoint, String body) throws IOException {
        return request("PUT", endpoint, body);
    }

    private String request(String method, String endpoint, String body) throws IOException {

        String path = initPath + apiVersion + endpoint + "?apiKey="+token;//pass token here on POST? yes
	if(DEBUG){System.out.println("PATH="+path);}
        URL url = new URL(cloud.scheme, cloud.host, cloud.port, path);

        final int maxRetries = 5;
        int retries = 0;
        while (true) {
            try {
                return singleRequest(method, url, body);
            } catch (HttpException e) {
                // ELB sometimes returns this when load is increasing.
                // We retry with exponential backoff.
                if (e.getStatusCode() != 503 || retries >= maxRetries) {
                    throw e;
                }
                retries++;
                // random delay between 0 and 4^tries*100 milliseconds
                int pow = (1 << (2*retries))*100;
                int delay = rand.nextInt(pow);
                try {//implement delay similar to Ethernet standard of exp-backoff
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static private class Error implements Serializable {
        String msg;
    }

    private String singleRequest(String method, URL url, String body) throws IOException {

	//	System.out.println("DEBUG:"+method+" "+url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);

        if (body != null) {
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
        }

        conn.connect();

        if (body != null) {
	    if(DEBUG){System.out.println("Client Writing:"+body);}
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(body);
	    out.flush();
            out.close();
        }

        int status = conn.getResponseCode();
	System.out.println("Client: STATUS="+status);
        if (status != 200) {

            String msg;

            if (conn.getContentLength() > 0 && conn.getContentType().equals("application/json")) {

                InputStreamReader reader = null;
                try {
                    reader = new InputStreamReader(conn.getErrorStream());
                    Gson gson = new Gson();
                    Error error = gson.fromJson(reader, Error.class);
                    msg = error.msg;
                } catch (JsonSyntaxException e) {
                    msg = "Server's response contained invalid JSON";
                } finally {
                    if (reader != null)
                        reader.close();
                }

            } else {
                msg = "Empty or non-JSON response";
            }

            throw new HttpException(status, msg);
        }

	BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	StringBuffer sb=new StringBuffer();
	String temp;
	try{
	    while( (temp=br.readLine()) != null){
		//		System.out.println("Client"+temp);
		sb.append(temp);
	    }
	}catch(IOException e){
	    System.out.println(e.getMessage() + " Client-reading-reply");
	}
	String retval = sb.toString();
	//	System.out.println("Client.retval="+retval);
        return retval;
    }
}
