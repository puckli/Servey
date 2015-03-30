package com.service.Impl;

import java.util.List;

import com.bean.Question;
import com.dao.QuestionDao;
import com.daoImpl.QuestionDaoImpl;
import com.service.QuestionService;

public class QuestionServiceImpl implements QuestionService
{
	private QuestionDao questionDao = new QuestionDaoImpl();
	@Override
	public String saveQuestion(Question question)
	{
		return questionDao.saveQuestion(question);
	}
	
	@Override
	public Question lastQuestion(int serveyId)
	{
		return questionDao.lastQuestion(serveyId);
	}
	
	@Override
	public void updateQuestion(Question question)
	{
		questionDao.updateQuestion(question);
	}
	
	@Override
	public Question getFirstQuestion(int serveyId)
	{
		return questionDao.getFirstQuestion(serveyId);
	}
	
	@Override
	public Question getSingleQuestionById(int questionId)
	{
		return questionDao.getSingleQuestionById(questionId);
	}
	
	@Override
	public Question getNextQuestionById(int questionIddb)
	{
		return questionDao.getNextQuestionById(questionIddb);
	}
	
	@Override
	public List<Question> getAllQuestionByServeyId(int serveyId)
	{
		return questionDao.getAllQuestionByServeyId(serveyId);
	}
}
