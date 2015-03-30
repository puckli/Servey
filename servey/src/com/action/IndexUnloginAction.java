package com.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bean.Serveys;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ServeyService;
import com.service.Impl.ServeyServiceImpl;

public class IndexUnloginAction extends ActionSupport
{
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String execute() throws Exception
	{
		ServeyService ssi = new ServeyServiceImpl();
		List<Serveys> serveys = ssi.getServeys();

		String serveyResult = "";
		String serveyResult1 = "";
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
				String checkLogin = serveys.get(i).getCheckLogin().trim();
				if ("no".equals(checkLogin))
				{

					Serveys s = serveys.get(i);
					String str = "{'id':'" + s.getId() + "','name':'"
							+ s.getName() + "','description':'"
							+ s.getDescription() + "','gender':'"
							+ s.getGender() + "','beginAge':'"
							+ s.getBeginAge() + "','endAge':'" + s.getEndAge()
							+ "','beginDate':'" + s.getBeginDate()
							+ "','endDate':'" + s.getEndDate()
							+ "','checkLogin':'" + s.getCheckLogin() + "'}";
					resultBuffer.append(str + ";");
				}
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
				for (int i = 0; i < str.length(); i++)
				{
					serveyResult1 = serveyResult1 + ","
							+ str.getJSONObject(i).getString("name");
				}
				this.name = str.getJSONObject(0).getString("name");
			}
			else
			{
				JSONObject str = new JSONObject(serveyResult);
				serveyResult = str.toString();
				serveyResult1 = str.getString("name");
				this.name = str.getString("name");
			}
		}


		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();

		resp.setContentType("application/json;charset=utf-8");
		resp.setHeader("catch-control", "no-cache");
		out.print(serveyResult);


		System.out.println(serveyResult);
		return null;
	}
}
