package com.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.User;
import com.dao.UserDao;
import com.util.DaoUtil;

public class UserDaoImpl implements UserDao
{
	@Override
	public void saveUser(User user)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.save(user);
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			if (null != tx)
			{
				tx.rollback();
			}
		} finally
		{
			DaoUtil.close(session);
		}
	}
	
	@Override
	public User getSingleUser(String username)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		User user = null;
		try
		{
			user = (User)session.createQuery("from User where username='" + username + "'").uniqueResult();
			
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			if (null != tx)
			{
				tx.rollback();
			}
		} finally
		{
			DaoUtil.close(session);
		}
		return user;
	}

}
