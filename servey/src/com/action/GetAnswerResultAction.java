package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bean.Question;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AnswerService;
import com.service.QuestionService;
import com.service.Impl.AnswerServiceImpl;
import com.service.Impl.QuestionServiceImpl;

public class GetAnswerResultAction extends ActionSupport
{
	private int serveyId;
	private String questionId;
	public int getServeyId()
	{
		return serveyId;
	}
	public void setServeyId(int serveyId)
	{
		this.serveyId = serveyId;
	}
	public String getQuestionId()
	{
		return questionId;
	}
	public void setQuestionId(String questionId)
	{
		this.questionId = questionId;
	}
	
	@Override
	public String execute() throws Exception
	{
		AnswerService as = new AnswerServiceImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();

		HttpServletResponse resp = ServletActionContext.getResponse();
		
		System.out.println(questionId);
		
		if(!questionId.equals("allquestionresult"))
		{
			map = as.getAnswerByQuestionId(Integer.parseInt(questionId));
			
			String jsonStr = gson.toJson(map);
			System.out.println(jsonStr);
			
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(jsonStr);
		}
		else{
			map = as.getAnswerByServeyId(serveyId);
			QuestionService qs = new QuestionServiceImpl();
			List<Question> list = qs.getAllQuestionByServeyId(serveyId);
			String jsonStr = "[";
			if(map.size() > 1){
				
				for(int i = 0;i<map.size(); i++)
				{
					int qid = list.get(i).getId();
					Map map2 = (Map) map.get("q" + qid);
					jsonStr += gson.toJson(map2) + ",";
				}
				jsonStr = jsonStr.substring(0,jsonStr.length()-1) + "]";
				System.out.println("1:" + jsonStr);
			}
			else{
				jsonStr = gson.toJson(map);
				System.out.println(jsonStr);
			}
			
			
			
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(jsonStr);
		}
		
		return null;
	}
}













