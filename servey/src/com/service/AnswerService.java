package com.service;

import java.util.Map;

public interface AnswerService
{
	public Map<String,Object> getAnswerByQuestionId(int questionId);
	
	public Map getAnswerByServeyId(int serveyId);
}
