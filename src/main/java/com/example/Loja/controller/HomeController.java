package com.example.Loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	String index () {
		return "index";
	}
	
	@GetMapping("/login")
	String login() {
		return "login";
	}
}
