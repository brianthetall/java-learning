package com.brianthetall.frameworks.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/A")
public class PersonalDataController{

    @RequestMapping(value="/userForm",method=RequestMethod.GET)
    public String loadForm(Model m){
	return "personalForm";
    }

    @RequestMapping(value="/userForm",method=RequestMethod.POST)
    public String rxForm(Model m){

	m.addAttribute("first","Bob");
	m.addAttribute("last","Dole");
	m.addAttribute("passwd","organicDildos");

	/*
	  m.addAttribute("first",request.getParameter(""));
	  m.addAttribute("last",request.getParameter(""));
	  m.addAttribute("passwd",request.getParameter(""));
	*/

	return "welcome";
    }

}
