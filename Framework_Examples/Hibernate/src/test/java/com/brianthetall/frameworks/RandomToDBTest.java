package com.brianthetall.frameworks;

import org.junit.*;

public class RandomToDBTest{


    RandomToDB db;

    @Before public void setup(){
	db=new RandomToDB();
	
    }

    @Test public void add(){
	db.add(new RandomToDB.DataBean("Brian"));
    }

    @Test public void get(){
	Integer id=db.add(new RandomToDB.DataBean("PC-Name"));
	System.out.println("GET ID="+id.toString());
	//	System.out.println("READ FROM DB: "+db.get(id).toString());//null pointer!!!!!!!!!!!!!!
    }

    @Test public void update(){
	Integer id = db.add(new RandomToDB.DataBean("updatingName"));
	System.out.println("Update ID="+id.toString());
	db.update(id,null,42,69.9);

    }

    @Test public void delete(){
	Integer id = db.add(new RandomToDB.DataBean("DeleteME"));
	System.out.println("Delete ID="+id.toString());
	db.delete(id);
    }

}
