package com.brianthetall.frameworks;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Assert;
import org.springframework.beans.factory.BeanFactory;//?
import org.springframework.context.ApplicationContext;//?
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankAccountTest{

    private BankAccount account;
    //    private static BeanFactory factory;
    private static ApplicationContext factory;

    @BeforeClass public static void init(){
	factory = new ClassPathXmlApplicationContext("spring.cfg.xml");
	//	factory = context;

    }

    @Before public void setup(){
	account=(BankAccount)factory.getBean("BankAccount");
    }
    
    @Test public void credit(){

    }

    @Test public void debit(){

    }

}
