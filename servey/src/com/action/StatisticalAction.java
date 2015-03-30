package com.action;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bean.Question;
import com.bean.Serveys;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.service.QuestionService;
import com.service.ServeyService;
import com.service.Impl.QuestionServiceImpl;
import com.service.Impl.ServeyServiceImpl;

public class StatisticalAction extends ActionSupport
{
	private String radio;
	private int serveyId;
	private int questionId;

	public String getRadio()
	{
		return radio;
	}

	public void setRadio(String radio)
	{
		this.radio = radio;
	}

	public int getServeyId()
	{
		return serveyId;
	}

	public void setServeyId(int serveyId)
	{
		this.serveyId = serveyId;
	}

	public int getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}

	@Override
	public String execute() throws Exception
	{
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String initQuery() throws Exception
	{

		ServeyService ssi = new ServeyServiceImpl();
		List<Serveys> serveys = ssi.getServeys();
		String serveyResult = "";
		StringBuffer resultBuffer = new StringBuffer();
		String results[] = null;

		for (int i = 0; i < serveys.size(); i++)
		{
			Date beginDate = serveys.get(i).getBeginDate();
			Date endDate = serveys.get(i).getEndDate();
			Date nowDate = new Date();
			if (!(nowDate.before(endDate) && nowDate.after(beginDate)))
			{
				serveys.remove(i);
				i = i - 1;
			}
		}

		if (serveys.size() != 0)
		{
			for (int i = 0; i < serveys.size(); i++)
			{
				Serveys s = serveys.get(i);
				String str = "{'id':'" + s.getId() + "','name':'" + s.getName()
						+ "','description':'" + s.getDescription()
						+ "','gender':'" + s.getGender() + "','beginAge':'"
						+ s.getBeginAge() + "','endAge':'" + s.getEndAge()
						+ "','beginDate':'" + s.getBeginDate()
						+ "','endDate':'" + s.getEndDate() + "','checkLogin':'"
						+ s.getCheckLogin() + "'}";
				resultBuffer.append(str + ";");
			}
			results = resultBuffer.toString().split(";");
			if (results.length > 1)
			{
				serveyResult = "[";
				for (int i = 0; i < results.length; i++)
				{
					if (i != results.length - 1)
					{
						serveyResult = serveyResult + results[i] + ",";
					}
					else
					{
						serveyResult = serveyResult + results[i] + "]";
					}
				}
			}
			else if (results.length == 1)
			{
				serveyResult = results[0];
			}

		}

		if (!"".equals(serveyResult))
		{
			if (results.length > 1)
			{
				JSONArray str = new JSONArray(serveyResult);
				serveyResult = str.toString();
			}
			else
			{
				JSONObject str = new JSONObject(serveyResult);
				serveyResult = str.toString();
			}
		}

		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();

		resp.setContentType("application/json;charset=utf-8");
		resp.setHeader("catch-control", "no-cache");
		out.print(serveyResult);

//		System.out.println(serveyResult);
		return null;
	}

	public String getAllQuestionByServeyId() throws Exception
	{
		QuestionService qs = new QuestionServiceImpl();
		List<Question> list = qs.getAllQuestionByServeyId(serveyId);
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Type type = new TypeToken<List<Question>>(){}.getType();
		String json = "";
		try{
		 json = gson.toJson(list,type);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();

		resp.setContentType("application/json;charset=utf-8");
		resp.setHeader("catch-control", "no-cache");
		out.print(json);

//		System.out.println(json);
		return null;
	}

}
