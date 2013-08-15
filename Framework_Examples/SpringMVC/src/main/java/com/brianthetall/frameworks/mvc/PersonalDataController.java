package com.brianthetall.frameworks.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/W")
public class PersonalDataController{

    @RequestMapping(value="/userForm",method=RequestMethod.GET)
    public String loadForm(Model m){
	return "personalForm";
    }

    @RequestMapping(value="/userForm",method=RequestMethod.POST)
    public String rxForm(Model m){
	//read the data out of the REQUEST
	//BUILD THE MODEL/MAP
	//HOW DOES m GET PASSED TO JSP?
	return "welcome";
    }

}
