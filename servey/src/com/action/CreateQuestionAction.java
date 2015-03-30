package com.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Question;
import com.bean.Serveys;
import com.opensymphony.xwork2.ActionSupport;
import com.service.QuestionService;
import com.service.ServeyService;
import com.service.Impl.QuestionServiceImpl;
import com.service.Impl.ServeyServiceImpl;

public class CreateQuestionAction extends ActionSupport
{
	private int serveyId;
	private String questionName;
	private int questiontype;
	private String questionDescription;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	private String answer7;
	private int answerCount;

	public int getServeyId()
	{
		return serveyId;
	}

	public void setServeyId(int serveyId)
	{
		this.serveyId = serveyId;
	}

	public String getQuestionName()
	{
		return questionName;
	}

	public void setQuestionName(String questionName)
	{
		this.questionName = questionName;
	}

	public int getQuestiontype()
	{
		return questiontype;
	}

	public void setQuestiontype(int questiontype)
	{
		this.questiontype = questiontype;
	}

	public String getQuestionDescription()
	{
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription)
	{
		this.questionDescription = questionDescription;
	}

	public String getAnswer1()
	{
		return answer1;
	}

	public void setAnswer1(String answer1)
	{
		this.answer1 = answer1;
	}

	public String getAnswer2()
	{
		return answer2;
	}

	public void setAnswer2(String answer2)
	{
		this.answer2 = answer2;
	}

	public String getAnswer3()
	{
		return answer3;
	}

	public void setAnswer3(String answer3)
	{
		this.answer3 = answer3;
	}

	public String getAnswer4()
	{
		return answer4;
	}

	public void setAnswer4(String answer4)
	{
		this.answer4 = answer4;
	}

	public String getAnswer5()
	{
		return answer5;
	}

	public void setAnswer5(String answer5)
	{
		this.answer5 = answer5;
	}

	public String getAnswer6()
	{
		return answer6;
	}

	public void setAnswer6(String answer6)
	{
		this.answer6 = answer6;
	}

	public String getAnswer7()
	{
		return answer7;
	}

	public void setAnswer7(String answer7)
	{
		this.answer7 = answer7;
	}

	public int getAnswerCount()
	{
		return answerCount;
	}

	public void setAnswerCount(int answerCount)
	{
		this.answerCount = answerCount;
	}

	public String saveQuestion()
	{
		QuestionService qsi = new QuestionServiceImpl();
		String result = "";
		Question question = new Question();
		question.setQuestion(questionName);
		question.setType(questiontype);
		question.setDescription(questionDescription);
		question.setAnswer1(answer1);
		question.setAnswer2(answer2);
		question.setAnswer3(answer3);
		question.setAnswer4(answer4);
		question.setAnswer5(answer5);
		question.setAnswer6(answer6);
		question.setAnswer7(answer7);
		question.setAnswerCount(answerCount);

		HttpSession session = ServletActionContext.getRequest().getSession();

		if (session.getAttribute("serveyId") == null || session.getAttribute("serveyId") == "")
			return "noServey";

		this.setServeyId((Integer) session.getAttribute("serveyId"));

		ServeyService ssi = new ServeyServiceImpl();
		Serveys servey = ssi.querySingleServeyById(serveyId);
		// HttpSession session = ServletActionContext.getRequest().getSession();
		// Integer str = (Integer)session.getAttribute("serveyId");
		// System.out.println("serveyId: " + str);
		// System.out.println("serveyName: " + servey.getName());
		question.setServey(servey);

		Question lastQuestion = qsi.lastQuestion(serveyId);
		if (lastQuestion == null)
		{
			result = qsi.saveQuestion(question);
		}
		else
		{
			question.setParentQuestion(lastQuestion);
			result = qsi.saveQuestion(question);
			lastQuestion.setChildQuestion(question);
			qsi.updateQuestion(lastQuestion);
		}

		return result;
	}

	@Override
	public String execute() throws Exception
	{
		// System.out.println(serveyId);
		String result = saveQuestion();

		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("html/text;charset=utf-8");
		resp.setHeader("cache-control", "no-cache");
		PrintWriter out = resp.getWriter();
		out.print(result);

		return null;
	}

}
