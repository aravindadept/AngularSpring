package org.pace.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
 
        return "login";
    }
	
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String notfound(ModelMap model) {
 
        return "403";
    }
    
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
