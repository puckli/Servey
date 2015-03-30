package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Serveys
{
	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private String description;
	
	/*性别要求：F女 M男 A所有,默认所有 */
	@Expose
	private String gender;
	
	/*年龄要求：默认所有*/
	@Expose
	private int beginAge;
	@Expose
	private int endAge;
	
	/*问卷有效日期：必填*/
	@Expose
	private Date beginDate;
	@Expose
	private Date endDate;
	/*用户是否需要登陆，值yes和no*/
	@Expose
	private String checkLogin;

	private Set<Question> questions = new HashSet<Question>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	public Date getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	
	public String getCheckLogin()
	{
		return checkLogin;
	}

	public void setCheckLogin(String checkLogin)
	{
		this.checkLogin = checkLogin;
	}

	public Set<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(Set<Question> questions)
	{
		this.questions = questions;
	}
}
