package com.springboot.tutorial.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.springboot.tutorial.entity.HelloMessage;

@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public String greeting(HelloMessage message) throws InterruptedException {
		Thread.sleep(1000);
		
		return "{\"content\":\"" + String.format("Hello, %s !", message.getName()) + "\"}";
	}

}
