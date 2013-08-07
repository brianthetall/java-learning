package com.brianthetall.frameworks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class BankAccount{
    
    private UserBean owner;
    private Account account;

    public BankAccount(){}
    public BankAccount(UserBean owner,Account account){
	this.owner=owner;
	this.account=account;
    }

    public UserBean getOwner(){return owner;}
    public Account getAccount(){return account;}

    @Autowired public void setOwner(UserBean owner){
	this.owner=owner;
    }

    @Autowired public void setAccount(Account account){
	this.account=account;
    }

}