package com.study.my.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.my.service.IMyService;

@Controller
public class MyController {

	@Inject
	IMyService myService;
	
	@RequestMapping("/myHome.wow")
	public String myHome(Model model) {
		
		int dual= myService.getDual();
		model.addAttribute("dual",dual);
		
		return "myHome";
	}
}
