package com.brianthetall.frameworks;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import java.lang.Double;

@Service public class CheckingAccount implements Account{
    private Double balance;

    public CheckingAccount(){
	//	balance=new Double(-123.45);
    }

    @Autowired public CheckingAccount setBalance(Double balance){
	this.balance=balance;
	return this;
    }

    public Double getBalance(){return balance;}

    public String toString(){
	return "Checking Account Balance: "+balance;
    }
}
