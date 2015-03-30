package test;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CreateTable
{
	public static void main(String[] args)
	{
		SchemaExport s = new SchemaExport(new Configuration().configure());
		
		s.create(true, false);
	}
}
