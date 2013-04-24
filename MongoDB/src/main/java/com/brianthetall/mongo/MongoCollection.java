package com.brianthetall.mongo;

public class MongoCollection{

    private String name;

    public MongoCollection(String name){
	this.name=name;
    }

    public String toString(){
	return name;
    }

}
