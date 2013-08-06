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

}
