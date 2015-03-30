package com.dao;

import java.util.List;

import com.bean.Answer;

public interface AnswerDao
{
	public String saveAnswer(Answer answer);
	
	public List<Answer> getAnswerByQuestionId(int questionId);
	
	public List<Answer> getAnswerByServeyId(int serveyId);
	
	public int getAnswerCount(int questionId,String answer);
}
