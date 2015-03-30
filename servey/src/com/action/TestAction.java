package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.bean.Serveys;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ServeyService;
import com.service.Impl.ServeyServiceImpl;

public class TestAction extends ActionSupport
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
	
	public String getServeyInfo() throws Exception
	{
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf8");
		ServeyService ssi = new ServeyServiceImpl();
		Serveys servey = ssi.querySingleServeyById(serveyid);
		
		java.text.DateFormat format1 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String bd = format1.format(servey.getBeginDate());
		String ed = format1.format(servey.getEndDate());
		
		String jsonstr = "{'name':'"+servey.getName()+"','description':'"+servey.getDescription()+"','gender':'"+servey.getGender()
				+"','checklogin':'"+servey.getCheckLogin()+"','beginage':'"+servey.getBeginAge()+"','endage':'"+servey.getEndAge()
				+"','begindate':'"+servey.getBeginDate()+"','enddate':'"+servey.getEndDate() +"','byear':'"+bd.substring(0,4)
				+"','bmonth':'"+bd.substring(5,7)+"','bdate':'"+bd.substring(8,10)+"','eyear':'"+ed.substring(0,4)
				+"','emonth':'"+ed.substring(5,7)+"','edate':'"+ed.substring(8,10)+"'}";
		
		System.out.println("test:"+jsonstr);
		
		req.setAttribute("name","你好hello");
		out.print(jsonstr);
		
		return SUCCESS;
	}
	
	public String alterServey() throws Exception
	{
		
		return super.execute();
	}
}
