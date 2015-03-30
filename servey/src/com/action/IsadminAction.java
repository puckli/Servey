package com.action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class IsadminAction extends ActionSupport
{
	@Override
	public String execute() throws Exception
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		String name = (String) session.getAttribute("username");

		if (name != "admin")
		{
			HttpServletResponse res = ServletActionContext.getResponse();
			res.setContentType("text/text;charset=utf-8");
			res.setHeader("cache-control", "no-cache");
			res.getWriter().print("noauthority");
		}
		return null;
	}
}
