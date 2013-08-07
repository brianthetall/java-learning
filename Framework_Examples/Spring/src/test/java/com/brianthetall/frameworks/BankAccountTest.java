package com.brianthetall.frameworks;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Assert;
import org.springframework.beans.factory.BeanFactory;//?
import org.springframework.context.ApplicationContext;//?
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BankAccountTest{

    private BankAccount account;
    //    private static BeanFactory factory;
    private static ApplicationContext factory;

    @BeforeClass public static void init(){
	//	factory = new ClassPathXmlApplicationContext("spring.cfg.xml");
	//	factory = context;
	factory=new AnnotationConfigApplicationContext(BankAccount.class);

	/*
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	MyService myService = ctx.getBean(MyService.class);
	myService.doStuff();
	*/

    }

    @Before public void setup(){
	account=(BankAccount)factory.getBean("BankAccount");
    }
    
    @Test public void credit(){

    }

    @Test public void debit(){

    }

}
