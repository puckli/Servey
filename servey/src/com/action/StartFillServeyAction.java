package com.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Serveys;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ServeyService;
import com.service.Impl.ServeyServiceImpl;

public class StartFillServeyAction extends ActionSupport
{
	private String serveyName;
	
	private int serveyId;

	public int getServeyId()
	{
		return serveyId;
	}

	public void setServeyId(int serveyId)
	{
		this.serveyId = serveyId;
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("serveyId", serveyId);
		System.out.println("serveyId in StartFillServeyAction:" + serveyId );
	}

	public String getServeyName()
	{
		return serveyName;
	}

	public void setServeyName(String serveyName)
	{
		this.serveyName = serveyName;
	}
	
	@Override
	public String execute() throws Exception
	{
		ServeyService ssi = new ServeyServiceImpl();
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		Serveys servey = ssi.querySingleServeyById(serveyId);
		
		if(servey.getCheckLogin().equals("yes"))
		{
			String username = (String) session.getAttribute("username");
			String mark = ssi.checkfilled(serveyId,username);
			if(username == null)
			{
				mark = "yes";
			}
			if(mark.equals("yes"))
			{
				return ERROR;
			}
		}
		
		int questionCount = ssi.getQuestionCount(serveyId);
		
		session.setAttribute("serveyName", servey.getName());
		session.setAttribute("questionId",1);
		session.setAttribute("questionCount",questionCount);
		
		return "success";
	}
	
	public String isFilled()throws Exception
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/text;charset=utf-8");
		resp.setHeader("cache-control", "no-cache");
		PrintWriter out = resp.getWriter();
		
		int serveyId = (Integer) session.getAttribute("serveyId");
		ServeyService ssi = new ServeyServiceImpl();
		Serveys servey = ssi.querySingleServeyById(serveyId);
		
		if(servey.getCheckLogin().equals("yes"))
		{
			String username = (String) session.getAttribute("username");
			String mark = ssi.checkfilled(serveyId,username);
			if(mark.equals("yes"))
			{
				out.print("filled");
			}
		}
		
		return null;
	}
}
