package com.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.Impl.UserServiceImpl;


public class RegisterAction extends ActionSupport 
{
	private String username;
	
	private String password;
	
	private String repassword;
	
	private int age;
	
	private String gender;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRepassword()
	{
		return repassword;
	}

	public void setRepassword(String repassword)
	{
		this.repassword = repassword;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public String saveUser()
	{
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setAge(age);
		user.setGender(gender);
		
		UserServiceImpl usi = new UserServiceImpl();
		
		String result = usi.saveOrUpdateUser(user);
		if(result.equals("exist"))
		{
			this.addActionError("用户名已存在！");
		}
		else if(result.equals("success"))
		{
			HttpSession session =  ServletActionContext.getRequest().getSession();
			session.setAttribute("username", this.username);
		}
		return result;
	}
	
	@Override
	public String execute() throws Exception
	{
		return this.saveUser();
	}
	
}
