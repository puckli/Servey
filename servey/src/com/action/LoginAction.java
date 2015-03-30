package com.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;
import com.service.Impl.UserServiceImpl;

public class LoginAction extends ActionSupport
{
	private String username;
	
	private String password;

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
	
	@Override
	public void validate()
	{
		super.validate();
	}
	
	public String isLogin()
	{
		UserService usi = new UserServiceImpl();
		
		String result = usi.isLogin(username, password);
		
		if("notexist".equals(result))
		{
			this.addActionError("用户不存在！");
		}
		else if("passworderror".equals(result))
		{
			this.addActionError("密码输入错误！");
		}
		else if("admin".equals(result))
		{
			HttpSession session =  ServletActionContext.getRequest().getSession();
			session.setAttribute("username", "admin");
		}
		else if("success".equals(result))
		{
			HttpSession session =  ServletActionContext.getRequest().getSession();
			session.setAttribute("username", username);
		}
		
		return result;
	}
	
	@Override
	public String execute() throws Exception
	{
		return this.isLogin();
	}
}
