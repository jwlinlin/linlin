package test.admin.example;

import java.util.Date;

import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;
import test.admin.db.Example;

public class ExampleSaveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin=new Permissions(this).checkLogin();
		Example e=new Example();
		e.setCourse(jreq.getInt("course"));
		e.setChapter(jreq.getInt("chapter"));
		e.setAnswer(jreq.getString("answer"));
		e.setContent(jreq.getString("content"));
		e.setRank((byte)jreq.getInt("rank"));
		e.setTimeCreated(new Date());
		e.setTimeModifide(new Date());
		
		MyC3P0Factory.insert(e);
			
		
		return null;
	}
	
	
	

}
