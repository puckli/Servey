package com.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Serveys;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ServeyService;
import com.service.Impl.ServeyServiceImpl;

public class CreateServeyAction extends ActionSupport
{
	private int serveyid;
	private String serveyName;
	private String serveyDescription;
	private String gender;
	private int beginAge;
	private int endAge;
	private String checkLogin;
	private String beginDate;
	private String endDate;
	
	public int getServeyid()
	{
		return serveyid;
	}

	public void setServeyid(int serveyid)
	{
		this.serveyid = serveyid;
	}

	public String getServeyName()
	{
		return serveyName;
	}

	public void setServeyName(String serveyName)
	{
		this.serveyName = serveyName;
	}

	public String getServeyDescription()
	{
		return serveyDescription;
	}

	public void setServeyDescription(String serveyDescription)
	{
		this.serveyDescription = serveyDescription;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public int getBeginAge()
	{
		return beginAge;
	}

	public void setBeginAge(int beginAge)
	{
		this.beginAge = beginAge;
	}

	public int getEndAge()
	{
		return endAge;
	}

	public void setEndAge(int endAge)
	{
		this.endAge = endAge;
	}

	public String getCheckLogin()
	{
		return checkLogin;
	}

	public void setCheckLogin(String checkLogin)
	{
		this.checkLogin = checkLogin;
	}

	public String getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(String beginDate)
	{
		this.beginDate = beginDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String structureServey() throws Exception
	{
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate2 = s.parse(beginDate);
		java.util.Date endDate2 = s.parse(endDate);
		
		Serveys servey = new Serveys();
		servey.setName(serveyName);
		servey.setDescription(serveyDescription);
		servey.setBeginAge(beginAge);
		servey.setEndAge(endAge);
		servey.setBeginDate(beginDate2);
		servey.setEndDate(endDate2);
		servey.setGender(gender);
		servey.setCheckLogin(checkLogin);
		
		
		ServeyService ssi = new ServeyServiceImpl();
		String result = ssi.createServeys(servey);
		
		Serveys singleservey = ssi.querySingleServeyByName(serveyName);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("serveyId", singleservey.getId());
		session.setMaxInactiveInterval(3600);
		
		return result;
	}
	
	@Override
	public String execute() throws Exception
	{
		String result = this.structureServey();
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out = resp.getWriter();
		resp.setContentType("html/text;charset=utf-8");
		resp.setHeader("cache-control", "no-cache");
		out.print(result);
		
		return null;
	}
	
}
