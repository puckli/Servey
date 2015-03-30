package com.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoUtil
{
	private static SessionFactory sessionFactory;
	static
	{
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	public static Session openSession()
	{
		return sessionFactory.openSession();
	}
	public static void close(Session session)
	{
		if(null != session)
		{
			session.close();
		}
	}

}
