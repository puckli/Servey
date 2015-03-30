package com.service.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Answer;
import com.bean.Serveys;
import com.service.ServeyService;
import com.util.DaoUtil;

public class ServeyServiceImpl implements ServeyService
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Serveys> getServeys()
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<Serveys> serveys = null;
		try{
			Query query = session.createQuery("from Serveys order by id");
			serveys = (List<Serveys>)query.list();
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
		return serveys;
	}
	
	@Override
	public String createServeys(Serveys servey)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(servey);
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			return "error";
		}
		finally{
			DaoUtil.close(session);
		}
		return "success";
	}
	
	public String updateServeys(Serveys servey)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(servey);
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			return "error";
		}
		finally{
			DaoUtil.close(session);
		}
		return "success";
	}
	
	@Override
	public Serveys querySingleServeyByName(String serveyName)
	{
		
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		Serveys servey = null;
		try{
			servey = (Serveys)session.createQuery("from Serveys where name='" + serveyName + "'").uniqueResult();
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("ServeyServiceImpl.class querySingleServeyByName error");
		}
		finally{
			DaoUtil.close(session);
		}
		return servey;
	}
	
	@Override
	public Serveys querySingleServeyById(int serveyId)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		Serveys servey = null;
		try{
			servey = (Serveys)session.createQuery("from Serveys where id='" + serveyId + "'").uniqueResult();
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("ServeyServiceImpl.class querySingleServeyByName error");
		}
		finally{
			servey.getGender(); 
			DaoUtil.close(session);
		}
		return servey;
	}
	
	@Override
	public String deleteServeys(int id)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Serveys servey = (Serveys)session.createQuery("from Serveys where id='" + id + "'").uniqueResult();
			System.out.println("ServeyServiceImpl.class deleteServey" + id + " serveyName:" + servey.getName());
			session.delete(servey);
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("ServeyServiceImpl.class deleteServey error;serveyId:" + id);
			return "error";
		}
		finally{
			DaoUtil.close(session);
		}
		return "success";
	}
	
	@Override
	public int getQuestionCount(int serveyId)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		int questionCount = 0;
		try{
			Query query = session.createQuery("select count(*) from Question where servey_id=" + serveyId);
			
			questionCount = Integer.parseInt(query.uniqueResult().toString());
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("ServeyServiceImpl.class getQuestionCount error");
		}
		finally{
			DaoUtil.close(session);
		}
		return questionCount;
	}
	
	@Override
	public String checkfilled(int serveyId, String username)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		String mark = "no";
		try{
			Query query = session.createQuery("from Answer where serveyId=" + serveyId + " and username='" + username +"'");
			
			@SuppressWarnings("unchecked")
			List<Answer> list = query.list();
			if(list.size()>0)
				mark="yes";
			
			tx.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(tx != null)
				tx.rollback();
			System.out.println("ServeyServiceImpl.class getQuestionCount error");
		}
		finally{
			DaoUtil.close(session);
		}
		return mark;
	}
}
