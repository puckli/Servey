package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bean.Serveys;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ServeyService;
import com.service.Impl.ServeyServiceImpl;

public class GetQuestionnaireAction extends ActionSupport
{
	@Override
	public String execute() throws Exception
	{
		ServeyService ssi = new ServeyServiceImpl();
		List<Serveys> serveys = ssi.getServeys();
		String serveyResult = "";

		if (serveys.size() == 1)
		{
			Serveys s = serveys.get(0);
			serveyResult = "{'id':'" + s.getId() + "','name':'" + s.getName()
					+ "','description':'" + s.getDescription() + "','gender':'"
					+ s.getGender() + "','beginAge':'" + s.getBeginAge()
					+ "','endAge':'" + s.getEndAge() + "','beginDate':'"
					+ s.getBeginDate() + "','endDate':'" + s.getEndDate()
					+ "','checkLogin':'" + s.getCheckLogin() + "'}";
		}
		else if (serveys.size() > 1)
		{
			String str = "";
			serveyResult = "[";
			for (int i = 0; i < serveys.size(); i++)
			{
				Serveys s = serveys.get(i);
				str = "{'id':'" + s.getId() + "','name':'" + s.getName()
						+ "','description':'" + s.getDescription()
						+ "','gender':'" + s.getGender() + "','beginAge':'"
						+ s.getBeginAge() + "','endAge':'" + s.getEndAge()
						+ "','beginDate':'" + s.getBeginDate()
						+ "','endDate':'" + s.getEndDate() + "','checkLogin':'"
						+ s.getCheckLogin() + "'}";
				if (i != serveys.size() - 1)
				{
					serveyResult = serveyResult + str + ",";
				}
				else
				{
					serveyResult = serveyResult + str + "]";
				}
			}
		}
		else
		{

		}
		if (serveys.size() > 1)
		{
			JSONArray str = new JSONArray(serveyResult);
			serveyResult = str.toString();
		}
		else
		{
			JSONObject str = new JSONObject(serveyResult);
			serveyResult = str.toString();
		}
		System.out.println(serveyResult);

		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		res.setContentType("application/json;charset=utf-8");
		res.setHeader("cache-control", "no-cache");
		out.print(serveyResult);

		return null;
	}
}
