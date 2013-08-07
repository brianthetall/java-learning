package com.brianthetall.frameworks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class BankAccount{
    
    private UserBean owner;
    private Account account;

    public BankAccount(){}

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