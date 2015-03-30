package com.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import jxl.Workbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.bean.Question;
import com.bean.Serveys;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AnswerService;
import com.service.QuestionService;
import com.service.ServeyService;
import com.service.Impl.AnswerServiceImpl;
import com.service.Impl.QuestionServiceImpl;
import com.service.Impl.ServeyServiceImpl;

public class ToExcelAction extends ActionSupport
{
	private int serveyId;

	public int getServeyId()
	{
		return serveyId;
	}

	public void setServeyId(int serveyId)
	{
		this.serveyId = serveyId;
	}

	@Override
	public String execute() throws Exception
	{
		AnswerService as = new AnswerServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map = as.getAnswerByServeyId(serveyId);
		QuestionService qs = new QuestionServiceImpl();
		List<Question> list = qs.getAllQuestionByServeyId(serveyId);
		ServeyService ss = new ServeyServiceImpl();
		Serveys servey = ss.querySingleServeyById(serveyId);
		
		
		boolean mark = writeExcelBo(servey.getName(), "d:\\"+servey.getName()+".xls", map, list);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.getWriter().print(mark);
		return null;
	}

	public static boolean writeExcelBo(String name, String fos, Map map2, java.util.List ve)
	{
		jxl.write.WritableWorkbook wwb;
		try
		{
			wwb = Workbook.createWorkbook(new File(fos));
			jxl.write.WritableSheet ws = wwb.createSheet("统计结果", 10);
			ws.addCell(new jxl.write.Label(0, 1, "答案1"));
			ws.addCell(new jxl.write.Label(1, 1, "答案2"));
			ws.addCell(new jxl.write.Label(2, 1, "答案3"));
			ws.addCell(new jxl.write.Label(3, 1, "答案4"));
			ws.addCell(new jxl.write.Label(4, 1, "答案5"));
			ws.addCell(new jxl.write.Label(5, 1, "答案6"));
			ws.addCell(new jxl.write.Label(6, 1, "答案7"));
			int listSize = ve.size();
			Question q = new Question();
			for (int i = 0; i < listSize; i++)
			{

				q = (Question) ve.get(i);
				int qid = q.getId();
				Map map = (Map) map2.get("q" + qid);
				
				ws.addCell(new jxl.write.Label(0, 4 * i + 2, i+1+"." + q.getQuestion()));
					
				
				ws.addCell(new jxl.write.Label(0, 4 * i + 3, "" + q.getAnswer1()));
				ws.addCell(new jxl.write.Label(0, 4 * i + 4, "" + ((int[]) map.get("acount"))[0] + " / " + map.get("answer" + 0 + "b")));

				ws.addCell(new jxl.write.Label(1, 4 * i + 3, "" + q.getAnswer2()));
				ws.addCell(new jxl.write.Label(1, 4 * i + 4, "" + ((int[]) map.get("acount"))[1] + " / " + map.get("answer" + 1 + "b")));
				
				if(q.getAnswerCount()>2 )
				{
					ws.addCell(new jxl.write.Label(2, 4 * i + 3, "" + q.getAnswer3()));
					ws.addCell(new jxl.write.Label(2, 4 * i + 4, "" + ((int[]) map.get("acount"))[2] + " / " + map.get("answer" + 2 + "b")));
				}
				
				if(q.getAnswerCount()>3 )
				{
					ws.addCell(new jxl.write.Label(3, 4 * i + 3, "" + q.getAnswer4()));
					ws.addCell(new jxl.write.Label(3, 4 * i + 4, "" + ((int[]) map.get("acount"))[3] + " / " + map.get("answer" + 3 + "b")));
				}
				
				if(q.getAnswerCount()>4 )
				{
					ws.addCell(new jxl.write.Label(4, 4 * i + 3, "" + q.getAnswer5()));
					ws.addCell(new jxl.write.Label(4, 4 * i + 4, "" + ((int[]) map.get("acount"))[4] + " / " + map.get("answer" + 4 + "b")));
				}
				
				if(q.getAnswerCount()>5 )
				{
					ws.addCell(new jxl.write.Label(5, 4 * i + 3, "" + q.getAnswer6()));
					ws.addCell(new jxl.write.Label(5, 4 * i + 4, "" + ((int[]) map.get("acount"))[5] + " / " + map.get("answer" + 5 + "b")));
				}
				
				if(q.getAnswerCount()>6 )
				{
					ws.addCell(new jxl.write.Label(6, 4 * i + 3, "" + q.getAnswer7()));
					ws.addCell(new jxl.write.Label(6, 4 * i + 4, "" + ((int[]) map.get("acount"))[6] + " / " + map.get("answer" + 6 + "b")));
				}

			}
			ws.addCell(new jxl.write.Label(0, 0, name));
			wwb.write();
			// 关闭Excel工作薄对象
			wwb.close();
			return true;
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		} catch (RowsExceededException e)
		{
			e.printStackTrace();
			return false;
		} catch (WriteException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
