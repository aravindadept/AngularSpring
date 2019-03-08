package org.pace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {
	
	
	@RequestMapping( value = "/home" , method = RequestMethod.GET)
	public String home(ModelMap model) {
		
		model.addAttribute("title", "Angular");
		return "home";
	}				
	
	@RequestMapping( value = "/category" , method = RequestMethod.GET)
	public String category(ModelMap model) {
		
		model.addAttribute("title", "Angular");
		return "category";
	}
	
	@RequestMapping( value = "/item" , method = RequestMethod.GET)
	public String item(ModelMap model) {
		
		model.addAttribute("title", "Angular");
		return "item";
	}

}
