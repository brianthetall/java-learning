package com.brianthetall.frameworks.mvc;

import javax.validation.Valid;//how does this work? the javadoc was sparse.
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/start")
public class FirstController{

    //WHAT CALLS THIS? DISPATCHER_SERVLET???
    @RequestMapping(value="/", method=RequestMethod.GET)
	public String loadFormPage(Model m) {//WHERE DOES m COME FROM? DISPATCHER_SERVLET?
	//	m.addAttribute("subscriber", new Subscriber());//does this effect the returned View?
	return "view0";
    }

    //WHAT DOES @VALID DO?
    //what is BindingResult? Model?
    @RequestMapping(value="/", method=RequestMethod.POST)
	public String submitForm(/*@Valid Subscriber subscriber, */BindingResult result, Model m) {
	if(result.hasErrors()) {
	    return "view0";
	}
	return "view0";
    }
}

