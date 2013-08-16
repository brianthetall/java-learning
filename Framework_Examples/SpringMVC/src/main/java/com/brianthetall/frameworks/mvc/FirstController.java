package com.brianthetall.frameworks.mvc;

import javax.validation.Valid;//how does this work? the javadoc was sparse.
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/A")
public class FirstController{

    @RequestMapping(method=RequestMethod.GET)
	public String loadFormPage(Model m) {
	m.addAttribute("parameterTest","BAaaaaaaaaaM!");
	return "view0";
    }

}
