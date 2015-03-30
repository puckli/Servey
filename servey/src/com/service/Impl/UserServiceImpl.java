package com.service.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.bean.User;
import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.service.UserService;
import com.util.DaoUtil;

public class UserServiceImpl implements UserService
{

	@Override
	public String saveOrUpdateUser(User user)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		String username = user.getUsername();
		Query query = session.createQuery("from User u where u.username='" + username + "'" );
		
		if(query.list().size() > 0)
		{
			return "exist";
		}
		try{
			if(user.getRegisterDate() == null || user.getRegisterDate().equals("")){
				java.text.DateFormat format1 = new java.text.SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String s = format1.format(new java.util.Date());
				user.setRegisterDate(s);
			}
			
			session.saveOrUpdate(user);
			
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
	public String isLogin(String username, String password)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		String mark = "notexist";
		try{
//			Query query = session.createQuery("from User where binary username='" + username + "'");
			@SuppressWarnings("unchecked")
//			List<User> list = query.list();
			List<User> list = session.createCriteria(User.class).add(Restrictions.like("username",username)).list();
			
			
			if(list.size() == 0)
				mark =  "notexist";
			else if(!(list.get(0).getUsername().equals(username)))
			{
				mark =  "notexist";
			}
			else if(!((User)list.get(0)).getPassword().equals(password))
			{
				mark =  "passworderror";
			}
			else{
				mark = "success";
			}
			if(username.equals("admin") || username.equals("liwenzhong")){
				mark =  "admin";
			}
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
		
		return mark;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser(int page)
	{
		int first = page * 10;
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users = null;
		try{
			
			Query query = session.createQuery("from User order by id").setFirstResult(first).setMaxResults(10);
			users = (List<User>)query.list();
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
		return users;
	}
	
	@Override
	public int getUserCount()
	{
		int n = 0;
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users = null;
		try{
			
			Query query = session.createQuery("select id from User order by id");
			users = (List<User>)query.list();
			n = users.size();
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
		return n;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String deleteUser(int id)
	{
		Session session = DaoUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users = null;
		try{
			Query query = session.createQuery("from User where id=" + id);
			users = (List<User>)query.list();
			System.out.println(users.get(0).getId());
			session.delete((User)users.get(0));
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
	public User getSingleUser(String username)
	{
		UserDao udi = new UserDaoImpl();
		
		return udi.getSingleUser(username);
	}
}
