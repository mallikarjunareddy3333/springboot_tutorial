package com.springboot.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping(value = "/view-products")
	public String viewProducts() {
		return "view-products";
	}

	@RequestMapping(value = "/add-products")
	public String addProducts() {
		return "add-products";
	}
	
	@RequestMapping(value = "/websocket_example")
	public String websocketExample() {
		return "websocket_example";
	}
	
}
