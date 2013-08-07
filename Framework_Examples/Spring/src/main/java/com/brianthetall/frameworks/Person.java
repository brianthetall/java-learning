package com.brianthetall.frameworks;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import java.lang.String;

@Import(Canned.class)
@Service public class Person implements UserBean{

    private String name;
    public Person(){
	//	name=new String("CannedString");
    }
        
    @Autowired public void setName(String name){
	this.name=name;
    }

    public String getName(){return name;}
    public String toString(){
	return "Person: "+name;
    }
}
