package com.brianthetall.frameworks;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Assert;
import org.springframework.beans.factory.BeanFactory;//?
import org.springframework.context.ApplicationContext;//?
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.*;

public class BankAccountTest{

    private BankAccount account;
    //    private static BeanFactory factory;
    //    private static ApplicationContext factory;
    private static AnnotationConfigApplicationContext factory;

    @BeforeClass public static void init(){
	factory=new AnnotationConfigApplicationContext("com.brianthetall.frameworks");
    }

    @Before public void setup(){
	account=(BankAccount)factory.getBean("bankAccount");
	System.out.println("SETUP:"+account.toString());
    }
    
    @Test public void credit(){

    }

    @Test public void debit(){

    }

}
