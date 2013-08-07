package com.brianthetall.frameworks;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import java.lang.String;
//import javax.inject.Inject;

@Import(Canned.class)
@Service public class Person implements UserBean{

    private String name;
    //  @Inject private Canned can;
    
    public Person(){
	//	name=new String("CannedString");
    }

    /*
    public Person(String name){
	this.name=name;
    }
    */

    @Autowired public Person setName(String name){
	this.name=name;
	return this;
    }

    public String getName(){return name;}
    public String toString(){
	return "Person: "+name;
    }
}
