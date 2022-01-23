package com.annachi.user.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.annachi.user.VO.ResponseTemplateVO;
import com.annachi.user.entity.User;
import com.annachi.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController 
{
	public static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	@Autowired
	private UserService userService;
	
	@PostMapping(name="/",produces={"application/json"})
	public User saveuser(@RequestBody User user)
	{
		LOGGER.log(Level.INFO,"save user called");
		return userService.saveUser(user);
	}
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long userid)
	{
		return userService.getUser(userid);
	}
	
	
	@GetMapping("/userwithdepartment/{id}")
	@CircuitBreaker(name="USER-SERVICE",fallbackMethod = "departmentDown")
	public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId)
	{
	 return userService.getUserWithDepartment(userId);
	}
//	@ResponseBody
//	public ResponseTemplateVO departmentDown(Long userId,Exception exception)
//	{
//		return new ResponseTemplateVO();
//	}	
	public ResponseTemplateVO departmentDown(Long userId,Exception e)
	{
		return new ResponseTemplateVO();
	}
	
}
