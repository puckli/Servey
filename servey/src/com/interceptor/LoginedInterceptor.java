package com.interceptor;

import java.util.Map;

import com.action.LoginAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginedInterceptor extends AbstractInterceptor
{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		Map map = invocation.getInvocationContext().getSession();
		if(null == map.get("username"))
		{
			LoginAction la= (LoginAction)invocation.getAction();
			la.addActionError("您未登录或者登陆已过期，请重新登陆！");
			return Action.LOGIN;
		}
		
		return invocation.invoke();
	}

}
