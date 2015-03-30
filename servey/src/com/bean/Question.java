package com.bean;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

@SuppressWarnings("rawtypes")
public class Question
{
	@Expose
	private int id;
	/* 问题的题目 */
	@Expose
	private String question;
	/* 问题类型，默认1为单选择题，类型2为多选择题[，类型3为文字作答题 ]*/
	@Expose
	private Integer type;

	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	private String answer7;
	private int answerCount;
	
	private String description;

	private Serveys servey;
	
	private Question parentQuestion;
	
	private Question childQuestion;

	private Set answers = new HashSet();

	public Set getAnswers()
	{
		return answers;
	}

	public void setAnswers(Set answers)
	{
		this.answers = answers;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Serveys getServey()
	{
		return servey;
	}

	public void setServey(Serveys servey)
	{
		this.servey = servey;
	}

	public Question getParentQuestion()
	{
		return parentQuestion;
	}

	public void setParentQuestion(Question parentQuestion)
	{
		this.parentQuestion = parentQuestion;
	}

	public Question getChildQuestion()
	{
		return childQuestion;
	}

	public void setChildQuestion(Question childQuestion)
	{
		this.childQuestion = childQuestion;
	}
}
