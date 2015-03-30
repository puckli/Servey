package com.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.bean.Question;
import com.opensymphony.xwork2.ActionSupport;
import com.service.FillServeyService;
import com.service.QuestionService;
import com.service.Impl.FillServeyServiceImpl;
import com.service.Impl.QuestionServiceImpl;

public class FillServeyAction2 extends ActionSupport
{
	/**
	 * questionId为页面显示给用户的第几题，该数字有页面js实现递增，此action获取后只需将其更新到session中即可
	 * answers为用户答题内容，单选格式为answer[x]，多选格式为多个answer[x]拼装以逗号隔开，问答即答案内容。
	 * 
	 * session中维护的变量有：serveyId，serveyName，questionCount（当前问卷总共的问题数目），questionId（第x题）,username
	 * questionIddb为当前问题在数据库中的id值。
	 */
	 
	private int questionId;
	private int questionType;
	private String answers;
	private int questionIddb;
	private int answerCount;
	
	HttpSession session = ServletActionContext.getRequest().getSession();
	
	public int getQuestionIddb()
	{
		return questionIddb;
	}

	public void setQuestionIddb(int questionIddb)
	{
		this.questionIddb = questionIddb;
	}
	
	public int getQuestionType()
	{
		return questionType;
	}

	public void setQuestionType(int questionType)
	{
		this.questionType = questionType;
	}

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
		
		session.setAttribute("questionId",questionId);
	}
	
	public int getAnswerCount()
	{
		return answerCount;
	}

	public void setAnswerCount(int answerCount)
	{
		this.answerCount = answerCount;
	}

	@Override
	public String execute() throws Exception
	{
		FillServeyService fss = new FillServeyServiceImpl();
		HttpSession session = ServletActionContext.getRequest().getSession();
		int serveyId = (Integer) session.getAttribute("serveyId");
		String username = (String)session.getAttribute("username");
		String result = fss.saveAnswer(answers,questionIddb,answerCount,questionType,serveyId,username);
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("application/json;charset=utf-8");
		resp.setHeader("cache-control", "no-cache");
		PrintWriter out = resp.getWriter();
		
		String jsonFirst = "{";
		String jsonLast = "}";
		String jsonResult = "";
		if("success".equals(result))
		{
			/*获得下个问题*/
			QuestionService qsi = new QuestionServiceImpl();
			Question question = qsi.getNextQuestionById(questionIddb);
			if(question != null)
			{
				if (question.getType() == 1 || question.getType() == 2)
				{
					String questionName = question.getQuestion();
					int qType = question.getType();
					int answerCount = question.getAnswerCount();
					questionIddb = question.getId();
					
					String answer1 = "";
					String answer2 = "";
					String answer3 = "";
					String answer4 = "";
					String answer5 = "";
					String answer6 = "";
					String answer7 = "";
					
					switch(answerCount)
					{
					case 2:answer1 = question.getAnswer1(); answer2 = question.getAnswer2();break;
					case 3:answer1 = question.getAnswer1(); answer2 = question.getAnswer2(); answer3 = question.getAnswer3();break;
					case 4:answer1 = question.getAnswer1(); answer2 = question.getAnswer2(); answer3 = question.getAnswer3(); answer4 = question.getAnswer4();break;
					case 5:answer1 = question.getAnswer1(); answer2 = question.getAnswer2(); answer3 = question.getAnswer3(); answer4 = question.getAnswer4(); answer5 = question.getAnswer5();break;
					case 6:answer1 = question.getAnswer1(); answer2 = question.getAnswer2(); answer3 = question.getAnswer3(); answer4 = question.getAnswer4(); answer5 = question.getAnswer5(); answer6 = question.getAnswer6();break;
					case 7:answer1 = question.getAnswer1(); answer2 = question.getAnswer2(); answer3 = question.getAnswer3(); answer4 = question.getAnswer4(); answer5 = question.getAnswer5(); answer6 = question.getAnswer6(); answer7 = question.getAnswer7();break;
					}
					
					jsonResult = jsonFirst + "question:" + questionName + ",questionType:" +qType +",answerCount:" +answerCount + ",questionIddb:" + questionIddb + ",result:success";
					
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
					out.print(jsonResult);
					
				}
				/*为文字作答题*/
				else
				{
					String questionName = question.getQuestion();
					int qType = question.getType();
					int answerCount = question.getAnswerCount();
					questionIddb = question.getId();
					
					jsonResult = jsonFirst + "question:" + questionName + ",questionType:" +qType +",answerCount:" +answerCount + ",questionIddb:" + questionIddb + ",result:success";
					jsonResult = jsonResult + jsonLast;
					JSONObject str = new JSONObject(jsonResult);
					jsonResult = str.toString();
					System.out.println(jsonResult);
					out.print(jsonResult);
					
				}
			}
			/*没有更多问题了*/
			else{
				jsonResult = jsonFirst + "result:noquestion" + jsonLast;
				JSONObject str = new JSONObject(jsonResult);
				jsonResult = str.toString();
				out.print(jsonResult);
			}
			
		}
		/*保存问题失败*/
		else{
			jsonResult = jsonFirst + "result:error" + jsonLast;
			JSONObject str = new JSONObject(jsonResult);
			jsonResult = str.toString();
			out.print(jsonResult);
		}
		return null;
	}
}
