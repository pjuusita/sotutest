package com.sotuexample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

	@RequestMapping("/") 
	public String getDefault() {
		return "Hello Default";
	}
	
	@RequestMapping("/hello") 
	public String getGreeting() {
		return "Hello World!";
	}	
	
	
	
}
