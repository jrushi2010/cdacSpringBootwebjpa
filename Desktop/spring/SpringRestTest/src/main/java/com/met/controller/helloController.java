package com.met.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class helloController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("welcomeMsg","Welcome to spring MVC");
		mv.setViewName("hello");
		return mv;
	}
	
	@RequestMapping(value="requestParam",method=RequestMethod.GET)
	public ModelAndView requestParam(@RequestParam(required=false,name="userName")String name, @RequestParam(defaultValue = "28")int age)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("welcomeMsg","Welcome to spring MVC user : " + name + " with age " + age);
		mv.setViewName("hello");
		return mv;
	}
	
	@GetMapping(value="pathVar/{id}")
	public ModelAndView pathVariable(@PathVariable int id)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("welcomeMsg","Welcome to spring MVC user with id :" + id);
		mv.setViewName("hello");
		return mv;
	}
}
