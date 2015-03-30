package com.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bean.Serveys;
import com.bean.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ServeyService;
import com.service.UserService;
import com.service.Impl.ServeyServiceImpl;
import com.service.Impl.UserServiceImpl;

public class IndexLoginAction extends ActionSupport
{
	@Override
	public String execute() throws Exception
	{
		ServeyService ssi = new ServeyServiceImpl();
		List<Serveys> serveys = ssi.getServeys();

		String serveyResult = "";
		StringBuffer resultBuffer = new StringBuffer();
		String results[] = null;

		HttpSession session = ServletActionContext.getRequest().getSession();
		String username = (String) session.getAttribute("username");

		UserService usi = new UserServiceImpl();
		User user = usi.getSingleUser(username);
		
		for (int i = 0; i < serveys.size(); i++)
		{
			if(serveys.get(i).getCheckLogin().equals("no"))continue;
			if (!(serveys.get(i).getBeginAge() < user.getAge()
					&& serveys.get(i).getEndAge() > user.getAge() && (serveys
					.get(i).getGender().equals(user.getGender()) || serveys
					.get(i).getGender().trim().equals("A"))))
			{
				serveys.remove(i);
				i = i-1;
			}
			else
			{
				Date beginDate = serveys.get(i).getBeginDate();
				Date endDate = serveys.get(i).getEndDate();
				Date nowDate = new Date();
				if (!(nowDate.before(endDate) && nowDate.after(beginDate)))
				{
					serveys.remove(i);
					i = i-1;
				}
			}
		}
		
		if (serveys.size() != 0)
		{
			for (int i = 0; i < serveys.size(); i++)
			{
//				String checkLogin = serveys.get(i).getCheckLogin().trim();
//				if ("yes".equals(checkLogin))
//				{
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
//				}
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
		}
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setHeader("catch-control", "no-cache");
		out.print(serveyResult);

		System.out.println(serveyResult);
		return null;
	}
}
