package com.brianthetall.frameworks.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.lang.String;

@Controller
@RequestMapping("/A")
public class PersonalDataController{

    @RequestMapping(value="/userForm",method=RequestMethod.GET)
    public String loadForm(Model m){
	return "personalForm";
    }

    @RequestMapping(value="/userForm",method=RequestMethod.POST)
    public String rxForm(@RequestParam("first") String first,@RequestParam("last") String last, @RequestParam("passwd") String passwd, Model m){

	//verify all strings passed in are valid
	if(first==null||last==null||passwd==null)
	    return "redirect:userForm";
	if(first.length()==0||last.length()==0||passwd.length()==0)
	    return "redirect:userForm";

	m.addAttribute("first",first);
	m.addAttribute("last",last);
	m.addAttribute("passwd",passwd);

	return "welcome";
    }

}
