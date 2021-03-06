package com.service;

import java.util.List;

import com.bean.Question;

public interface QuestionService
{
	public String saveQuestion(Question question);
	
	public Question lastQuestion(int serveyId);
	
	public void updateQuestion(Question question);
	
	public Question getFirstQuestion(int serveyId);
	
	public Question getSingleQuestionById(int questionId);
	
	public Question getNextQuestionById(int questionIddb);
	
	public List<Question> getAllQuestionByServeyId(int serveyId);
}
