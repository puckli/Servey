package com.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.action.LoginAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminLoginedInterceptor extends AbstractInterceptor
{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		Map map = invocation.getInvocationContext().getSession();
		
		if(null == map.get("username"))
		{
			ActionSupport la= (ActionSupport) invocation.getAction();
			la.addActionError("您未登录或者登陆已过期，请重新登陆！");
			HttpServletResponse res =  ServletActionContext.getResponse();
			res.getWriter().print("noauthority");
			return null;
		}
		
		if(!"admin".equals(map.get("username")))
		{
			ActionSupport la= (ActionSupport) invocation.getAction();
			la.addActionError("非法操作，请以管理员身份重新登陆！");
			HttpServletResponse res =  ServletActionContext.getResponse();
			res.getWriter().print("noauthority");
			return null;
		}
		
		return invocation.invoke();
	}

}
