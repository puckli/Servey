package com.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.bean.Question;
import com.opensymphony.xwork2.ActionSupport;
import com.service.QuestionService;
import com.service.Impl.QuestionServiceImpl;

public class FillServeyAction extends ActionSupport
{
	/**
	 * 这里的questionId是呈现给用户的题目序列
	 * session中维护的变量有：serveyId，serveyName，questionCount（当前问卷总共的问题数目），questionId（第x题）
	 */
	private int questionId;
	private String answers;
	
	public String getAnswers()
	{
		return answers;
	}

	public void setAnswers(String answers)
	{
		this.answers = answers;
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
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("application/json;charset=utf-8");
		resp.setHeader("cache-control", "no-cache");
		PrintWriter out = resp.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession();
		int serveyId = (Integer) session.getAttribute("serveyId");

		Question fq = null;
		
		String jsonFirst = "{";
		String jsonLast = "}";
		String jsonResult = "";

		if (questionId == 1)
		{
			QuestionService qsi = new QuestionServiceImpl();
			fq = qsi.getFirstQuestion(serveyId);
		}
		int questionIddb = fq.getId();
		if (fq.getType() == 1 || fq.getType() == 2)
		{
			String question = fq.getQuestion();
			int qType = fq.getType();
			int answerCount = fq.getAnswerCount();
			
			String answer1 = "";
			String answer2 = "";
			String answer3 = "";
			String answer4 = "";
			String answer5 = "";
			String answer6 = "";
			String answer7 = "";
			
			switch(answerCount)
			{
			case 2:answer1 = fq.getAnswer1(); answer2 = fq.getAnswer2();break;
			case 3:answer1 = fq.getAnswer1(); answer2 = fq.getAnswer2(); answer3 = fq.getAnswer3();break;
			case 4:answer1 = fq.getAnswer1(); answer2 = fq.getAnswer2(); answer3 = fq.getAnswer3(); answer4 = fq.getAnswer4();break;
			case 5:answer1 = fq.getAnswer1(); answer2 = fq.getAnswer2(); answer3 = fq.getAnswer3(); answer4 = fq.getAnswer4(); answer5 = fq.getAnswer5();break;
			case 6:answer1 = fq.getAnswer1(); answer2 = fq.getAnswer2(); answer3 = fq.getAnswer3(); answer4 = fq.getAnswer4(); answer5 = fq.getAnswer5(); answer6 = fq.getAnswer6();break;
			case 7:answer1 = fq.getAnswer1(); answer2 = fq.getAnswer2(); answer3 = fq.getAnswer3(); answer4 = fq.getAnswer4(); answer5 = fq.getAnswer5(); answer6 = fq.getAnswer6(); answer7 = fq.getAnswer7();break;
			}
			
			jsonResult = jsonFirst + "question:" + question + ",questionType:" +qType +",answerCount:" +answerCount + ",questionIddb:" + questionIddb;
			
			switch(answerCount)
			{
			case 2:jsonResult = jsonResult + ",answer1:" + answer1 + ",answer2:" + answer2; break;
			case 3:jsonResult = jsonResult + ",answer1:" + answer1 + ",answer2:" + answer2 + ",answer3:" + answer3;break;
			case 4:jsonResult = jsonResult + ",answer1:" + answer1 + ",answer2:" + answer2 + ",answer3:" + answer3 + ",answer4:" + answer4;break;
			case 5:jsonResult = jsonResult + ",answer1:" + answer1 + ",answer2:" + answer2 + ",answer3:" + answer3 + ",answer4:" + answer4 + ",answer5:" + answer5;break;
			case 6:jsonResult = jsonResult + ",answer1:" + answer1 + ",answer2:" + answer2 + ",answer3:" + answer3 + ",answer4:" + answer4 + ",answer5:" + answer5 + ",answer6:" + answer6;break;
			case 7:jsonResult = jsonResult + ",answer1:" + answer1 + ",answer2:" + answer2 + ",answer3:" + answer3 + ",answer4:" + answer4 + ",answer5:" + answer5 + ",answer6:" + answer6 + ",answer7:" + answer7; break;
			}
			jsonResult = jsonResult + jsonLast;
			JSONObject str = new JSONObject(jsonResult);
			jsonResult = str.toString();
//			System.out.println("FillServeyAction:jsonResult: " + jsonResult + fq.getAnswer1());
			out.print(jsonResult);
			
		}
		/*为文字作答题*/
		else
		{
			String question = fq.getQuestion();
			int qType = fq.getType();
			int answerCount = fq.getAnswerCount();
			
			jsonResult = jsonFirst + "question:" + question + ",questionType:" +qType +",answerCount:" +answerCount + ",questionIddb:" + questionIddb;
			jsonResult = jsonResult + jsonLast;
			JSONObject str = new JSONObject(jsonResult);
			jsonResult = str.toString();
			System.out.println(jsonResult);
			out.print(jsonResult);
			
		}
		return null;
	}
}
