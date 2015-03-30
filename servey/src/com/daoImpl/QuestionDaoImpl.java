package com.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Question;
import com.dao.QuestionDao;
import com.util.DaoUtil;

public class QuestionDaoImpl implements QuestionDao
{

	@Override
	public String saveQuestion(Question question)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		
		try{
			session.save(question);
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
		}
		finally{
			DaoUtil.close(session);
		}
		return "success";
	}
	
	@Override
	public Question lastQuestion(int serveyId)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		Question question = null;
		try{
			Query query = session.createQuery("from Question where servey_id=" + serveyId + " order by id desc limit 1");
			@SuppressWarnings("unchecked")
			List<Question> list = query.list();
			if(list.size() != 0)
				question = list.get(0);
			else{
				question = null;
			}
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("QuestionDaoImpl.class lastQuestion error");
		}
		finally{
			DaoUtil.close(session);
		}
		return question;
	}
	
	@Override
	public void updateQuestion(Question question)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(question);
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("QuestionDaoImpl.class updateQuestion error");
		}
		finally{
			DaoUtil.close(session);
		}
	}
	
	@Override
	public Question getFirstQuestion(int serveyId)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		Question question = null;
		
		try{
			Query query = session.createQuery("from Question where servey_id=" + serveyId + " order by id asc limit 1");
			@SuppressWarnings("unchecked")
			List<Question> list = query.list();
			if(list.size() != 0)
			{
				question = list.get(0);
			}
			else{
				System.out.println("QuestionDaoImpl.class getFirstQuestion no firstquestion error");
				return null;
			}
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("QuestionDaoImpl.class getFirstQuestion error");
		}
		finally{
			DaoUtil.close(session);
		}
		return question;
	}
	@Override
	public Question getSingleQuestionById(int questionId)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		Question question = null;
		try{
			question = (Question)session.get(Question.class,questionId);
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("error in:QuestionDaoImpl getSingleQuestionById!");
			if(tx != null)
			{
				tx.rollback();
			}
		}
		finally{
			DaoUtil.close(session);
		}
		return question;
	}
	
	@Override
	public Question getNextQuestionById(int questionIddb)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		Question question = null;
		try{
			question = (Question)session.get(Question.class,questionIddb);
			question = question.getChildQuestion();
			if(question != null)
			question.getType();
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
		}
		finally{
			DaoUtil.close(session);
		}
		if(question != null)
		return question;
		else
		return null;
	}
	
	@Override
	public List<Question> getAllQuestionByServeyId(int serveyId)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<Question> list = new ArrayList<Question>();
		
		try{
			Query query = session.createQuery("from Question where servey_id=" + serveyId + " order by id asc");
			list = query.list();
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
		}
		finally{
			DaoUtil.close(session);
		}
		
		System.out.println(list.size());
		
		return list;
	}
}
