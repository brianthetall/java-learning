package com.brianthetall.frameworks;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.lang.String;
import java.lang.Double;

@Configuration public class Canned{

    /*
    @Bean public Account account(){
	return new CheckingAccount().setBalance(doubleValue());
    }
    */

    /*
    @Bean public UserBean user(){
	return new Person().setName(stringValue());
    }
    */

    @Bean(name="stringBean") public String stringValue(){
	return (new String("Canned-String-Dependency"));
    }

    @Bean public Double doubleValue(){
	return (new Double(69.9));
    }
}
