package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.User;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.service.Impl.UserServiceImpl;

public class GetUserAction extends ActionSupport
{
	private int page;
	
	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	@Override
	public String execute() throws Exception
	{
		UserServiceImpl usi = new UserServiceImpl();
		List<User> users = usi.getUser(page);
		int n = usi.getUserCount() - 1;
		n = n/10 + 1;
		
		
		Gson gson = new Gson();
		String userResult = gson.toJson(users);
		
		userResult = "{\"total\":" + n +",\"data\":" + userResult + "}";
		System.out.println(userResult);
		
		HttpServletResponse res = ServletActionContext.getResponse();
		PrintWriter out = res.getWriter();
		res.setContentType("application/json;charset=utf-8");
		res.setHeader("cache-control", "no-cache");
		out.print(userResult);
		
		return null;
	}
}
