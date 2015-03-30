package com.service;

import java.util.List;

import com.bean.Serveys;

public interface ServeyService
{
	public List<Serveys> getServeys();
	
	public String createServeys(Serveys servey);
	
	public String updateServeys(Serveys servey);
	
	public Serveys querySingleServeyByName(String serveyName);
	
	public Serveys querySingleServeyById(int serveyId);
	
	public String deleteServeys(int id);
	
	public int getQuestionCount(int serveyId);
	
	public String checkfilled(int serveyId,String username);
}
