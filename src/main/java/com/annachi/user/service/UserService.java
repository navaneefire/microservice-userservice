package com.annachi.user.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.annachi.user.VO.Department;
import com.annachi.user.VO.ResponseTemplateVO;
import com.annachi.user.entity.User;
import com.annachi.user.respository.UserRepository;

@Service
public class UserService 
{
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RestTemplate restTemplate;
	public User saveUser(User user) 
	{		
		LOGGER.log(Level.INFO, "save user called");
		return userRepo.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) 
	{		
		ResponseTemplateVO response =  new ResponseTemplateVO();
		User user = userRepo.findById(userId).orElse(null);
		Long departmentId = user.getDepartmentId();		
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+departmentId,Department.class);
		response.setUser(user);
		response.setDepartment(department);
		LOGGER.log(Level.INFO, "got department successfully");
		return response;
	}

	public User getUser(Long userId) 
	{		
		return userRepo.findById(userId).orElse(null);
	}
	
}
