package com.example.demoThymeleaf.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
@GetMapping("hello")
public String hello(ModelMap model) {
	model.addAttribute("message","<h3>Hello thymeLeaf</h3>");
	return "hello";
}
}
