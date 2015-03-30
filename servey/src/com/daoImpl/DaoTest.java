package com.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Question;
import com.util.DaoUtil;

public class DaoTest
{
	public static void main(String[] args)
	{
		
			Session session = DaoUtil.openSession();
			Transaction tx = session.beginTransaction();
			Question question = null;
			try{
				
//				Query query = session.createQuery("from Question where servey_id=" + 7 + " and type_=" + 3);
				
				question = (Question)session.get(Question.class,34);
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
			System.out.println(question.getAnswer1());
			else
			System.out.println("else");
		
	}
}
