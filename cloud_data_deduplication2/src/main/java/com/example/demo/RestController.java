package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;








@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private userservice userservice1;
	
	@RequestMapping("/hi")
    public String hello()
    {
    	return "hello how are you";
    }
	

	@GetMapping("/save-user")
	public String saveuser(@RequestParam  String username,@RequestParam String firstname,@RequestParam String lastname,@RequestParam String password,@RequestParam String email,@RequestParam String role)
	{
		user user1=new user(username,firstname,lastname,password,email,role);
		userservice1.savemyuser(user1);
		return "user saved";
	}
	
}
