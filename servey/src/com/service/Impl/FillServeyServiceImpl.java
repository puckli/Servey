package com.service.Impl;

import com.bean.Answer;
import com.bean.Question;
import com.dao.AnswerDao;
import com.dao.QuestionDao;
import com.daoImpl.AnswerDaoImpl;
import com.daoImpl.QuestionDaoImpl;
import com.service.FillServeyService;

public class FillServeyServiceImpl implements FillServeyService
{
	AnswerDao adi = new AnswerDaoImpl();
	@Override
	public String saveAnswer(String answers,int questionIddb,int answerCount,int questionType,int serveyId,String username)
	{
		Answer answer = new Answer();
		QuestionDao qdi = new QuestionDaoImpl();
		Question question = qdi.getSingleQuestionById(questionIddb);
		answer.setQuestion(question);
		
		answer.setQuestionType(questionType);
		answer.setAnswerCount(answerCount);
		answer.setServeyId(serveyId);
		answer.setUsername(username);
		
		if(questionType == 3)
		{
			answer.setAnswer(answers);
		}
		else if(questionType == 1 || questionType == 2)
		{
			if(answers.indexOf("answer1") != -1)
			{
				answer.setAnswer1("yes");
			}
			if(answers.indexOf("answer2") != -1)
			{
				answer.setAnswer2("yes");
			}
			if(answers.indexOf("answer3") != -1)
			{
				answer.setAnswer3("yes");
			}
			if(answers.indexOf("answer4") != -1)
			{
				answer.setAnswer4("yes");
			}
			if(answers.indexOf("answer5") != -1)
			{
				answer.setAnswer5("yes");
			}
			if(answers.indexOf("answer6") != -1)
			{
				answer.setAnswer6("yes");
			}
			if(answers.indexOf("answer7") != -1)
			{
				answer.setAnswer7("yes");
			}
		}
		
		String result = adi.saveAnswer(answer);
		
		return result;
	}
}
