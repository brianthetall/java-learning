package com.brianthetall.frameworks;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.*;
import org.springframework.beans.annotation.*;
import org.springframework.context.annotation.*;

//@Component
@Service 
public class BankAccount{
    
    private UserBean owner;
    private Account account;

    public BankAccount(){}

    public String toString(){
	return owner.toString()+","+account.toString();
    }

    public UserBean getOwner(){return owner;}
    public Account getAccount(){return account;}

    /**
     * can setters return 'this'; builder pattern
     */
    @Autowired public void setOwner(UserBean owner){
	this.owner=owner;
    }

    @Autowired public void setAccount(Account account){
	this.account=account;
    }

}