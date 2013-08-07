package com.brianthetall.frameworks;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BankAccountTest{

    private BankAccount account;
    //    private static AnnotationConfigApplicationContext factory;
    private AnnotationConfigApplicationContext factory;

    /* when the Context is static, it returns the same object every time
    @BeforeClass public static void init(){
	factory=new AnnotationConfigApplicationContext("com.brianthetall.frameworks");
    }

    @AfterClass public static void cleanup(){
	factory=null;
    }
    */

    @Before public void setup(){
	factory=new AnnotationConfigApplicationContext("com.brianthetall.frameworks");
	account=(BankAccount)factory.getBean("bankAccount");
	System.out.println("SETUP:"+account.toString());
    }

    @After public void unsetup(){
	account=null;
	factory=null;
    }
    
    @Test public void credit(){
	System.out.println("Credit Test");
	double initial=account.getAccount().getBalance().doubleValue();
	account.getAccount().setBalance(initial+4.02);
	assert(account.getAccount().getBalance().doubleValue() == initial+4.02);
    }

    @Test public void debit(){
	System.out.println("Debit Test");
	double initial=account.getAccount().getBalance().doubleValue();
	account.getAccount().setBalance(initial-4.02);
	assert(account.getAccount().getBalance().doubleValue() == initial-4.02);	
    }

}
