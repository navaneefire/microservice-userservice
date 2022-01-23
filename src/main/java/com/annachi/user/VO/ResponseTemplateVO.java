package com.annachi.user.VO;

import com.annachi.user.entity.User;

public class ResponseTemplateVO 
{
	private User user = new User();
	private Department department = new Department();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
