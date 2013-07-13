package com.brianthetall.ironio;

import java.lang.String;
import java.io.IOException;
import io.iron.ironmq.Client;
import io.iron.ironmq.Queue;
import io.iron.ironmq.EmptyQueueException;
import io.iron.ironmq.Message;

public class IronMQ{
    
    private Queue q;

    public IronMQ(Client c,String qName){
	if(c!=null)
	    q=c.queue(qName);
    }

    public Queue getQueue(){
	return q;
    }

    /**
     *
     * TESTED
     */
    public String push(String data){
	try{
	    return q.push(data);
	}catch(IOException e){
	    System.out.println("IronMQ.push Error:"+e.getMessage());
	}
	return null;
    }


    /**
     * @return - Message off the stack; else NULL if the stack is clear
     * TESTED
     */
    public Message pop(){
	try{
	    return q.get();
	}catch(EmptyQueueException e){
	    System.out.println("IronMQ.pop EQE:"+e.getMessage());
	}catch(IOException e){
	    System.out.println("IronMQ.pop Error:"+e.getMessage());
	}
	return null;
    }

    /**
     *
     * TESTED
     */
    public void delete(String id){
	try{
	    q.deleteMessage(id);
	}catch(IOException e){
	    System.out.println("IronMQ.delete Error:"+e.getMessage());
	}
    }

}
