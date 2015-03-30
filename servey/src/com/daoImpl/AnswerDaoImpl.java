package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Answer;
import com.dao.AnswerDao;
import com.util.DaoUtil;

public class AnswerDaoImpl implements AnswerDao
{
	@Override
	public String saveAnswer(Answer answer)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		
		try{
			session.save(answer);
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("error in AnswerDaoImpl saveAnswer!");
			if(tx != null)
			{
				tx.rollback();
			}
			return "error";
		}
		finally{
			if(session != null)
			{
				DaoUtil.close(session);
			}
		}
		
		return "success";
	}
	
	@Override
	public List<Answer> getAnswerByQuestionId(int questionId)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<Answer> list = null;
				
		try{
			Query query =  session.createQuery("from Answer where question='" + questionId + "'");
			list = query.list();
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("error in AnswerDaoImpl getAnswerByQuestionId!");
			if(tx != null)
			{
				tx.rollback();
			}
		}
		finally{
			if(session != null)
			{
				DaoUtil.close(session);
			}
		}
		return list;
	}
	
	@Override
	public List<Answer> getAnswerByServeyId(int serveyId)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getAnswerCount(int questionId, String answer)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<Answer> list = null;
				
		try{
			Query query =  session.createQuery("from Answer where question='" + questionId + "'" + " and " + answer +"='yes'" );
			list = query.list();
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("error in AnswerDaoImpl getAnswerByQuestionId!");
			if(tx != null)
			{
				tx.rollback();
			}
		}
		finally{
			if(session != null)
			{
				DaoUtil.close(session);
			}
		}
		return list.size();
	}
}
