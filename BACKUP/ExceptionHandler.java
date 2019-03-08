package org.pace.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandler implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(ModelMap error) {
    	
    	error.addAttribute("title","error");
    	
        return "error1";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    
}