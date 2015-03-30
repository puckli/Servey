package com.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.service.Impl.UserServiceImpl;

public class DeleteUserAction extends ActionSupport
{
	private int id;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String execute() throws Exception
	{
		UserServiceImpl usi = new UserServiceImpl();
		String result = usi.deleteUser(this.id);
		
		HttpServletResponse res = ServletActionContext.getResponse();
		PrintWriter out = res.getWriter();
		res.setContentType("application/text;charset=utf-8");
		res.setHeader("cache-control", "no-cache");
		out.print(result);
		
		return null;
	}
}
