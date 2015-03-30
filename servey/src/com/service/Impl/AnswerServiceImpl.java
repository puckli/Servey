package com.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bean.Question;
import com.dao.AnswerDao;
import com.daoImpl.AnswerDaoImpl;
import com.service.AnswerService;
import com.service.QuestionService;

public class AnswerServiceImpl implements AnswerService
{
	private AnswerDao ado = new AnswerDaoImpl();
	private QuestionService qser = new QuestionServiceImpl();
	
	public Map<String,Object> getAnswerByQuestionId(int questionId)
	{
		Question question = qser.getSingleQuestionById(questionId);
		int mark = question.getAnswerCount();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		int[] a = new int[7];
		
		a[0] = ado.getAnswerCount(questionId,"answer1");
		map.put("answer1",question.getAnswer1());
		a[1] = ado.getAnswerCount(questionId,"answer2");
		map.put("answer2",question.getAnswer2());
		if(mark >= 3)
		{
			a[2] = ado.getAnswerCount(questionId,"answer3");
			map.put("answer3",question.getAnswer3());
		}
		if(mark >= 4)
		{
			a[3] = ado.getAnswerCount(questionId,"answer4");
			map.put("answer4",question.getAnswer4());
		}
		if(mark >= 5)
		{
			a[4] = ado.getAnswerCount(questionId,"answer5");
			map.put("answer5",question.getAnswer5());
		}
		if(mark >= 6)
		{
			a[5] = ado.getAnswerCount(questionId,"answer6");
			map.put("answer6",question.getAnswer6());
		}
		if(mark >= 7)
		{
			a[6] = ado.getAnswerCount(questionId,"answer7");
			map.put("answer7",question.getAnswer7());
		}
		
		float sum = 0;
		for(int i = 0; i < mark; i++){
			sum += a[i];
		}
		for(int i = 0; i < mark; i++){
			
			float b = a[i];
			double bl = (b/sum);
			bl = bl *100;
			String str = new Double(bl).toString();
			if(str.length() > 4)
				str = str.substring(0,4);
			if(sum == 0)str = "0.0";
			map.put("answer" + i + "b" ,str + "%");
		}
		
		map.put("acount",a);
		map.put("answercount",mark);
		map.put("questionname",question.getQuestion());
		map.put("questiondescription",question.getDescription());
		
		return map;
	}
	
	@Override
	public Map getAnswerByServeyId(int serveyId)
	{
		QuestionService qs = new QuestionServiceImpl();
		List<Question> list = qs.getAllQuestionByServeyId(serveyId);
		Map maps = new HashMap();
		
		for(int i = 0; i < list.size(); i++)
		{
			int questionid = list.get(i).getId();
			Map map = new HashMap();
			map = this.getAnswerByQuestionId(questionid);
			maps.put("q" + questionid,map);
		}
		
		
		return maps;
	}
}
