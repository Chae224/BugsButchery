package com.bugsButchery.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	BugsButcheryService mainS;
	
	@RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
		
		
		
        return "index";
    }
}
