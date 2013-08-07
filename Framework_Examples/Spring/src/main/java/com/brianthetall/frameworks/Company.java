package com.brianthetall.frameworks;

public class Company implements UserBean{

    private String name;
    private String owner;
    private int employeeSize;
    public Company setName(String name){
	this.name=name;
	return this;
    }
    public Company setOwner(String owner){
	this.owner=owner;
	return this;
    }
    public Company setSize(int size){
	employeeSize=size;
	return this;
    }
    public String getName(){return name;}
    public String getOwner(){return owner;}
    public int getSize(){return employeeSize;}
    public String toString(){
	return new String("Company Name:"+name+" Owner:"+owner+" Employees:"+employeeSize);
    }
}
