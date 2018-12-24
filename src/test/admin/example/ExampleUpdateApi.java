package test.admin.example;

import java.util.Date;

import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import af.web.restful.AfRestfulException;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;
import test.admin.db.Example;

public class ExampleUpdateApi extends AfRestfulApi
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
		int example_id=jreq.getInt("example_id");
		update(example_id, e);
		return null;
	}
	private void update(int example_id,Example e)throws Exception
	{
		AfSqlUpdate u=new AfSqlUpdate("example");
		u.add2("course", e.course);
		u.add2("chapter", e.chapter);
		u.add2("rank", (int)e.rank);
		u.add2("content", e.content);
		u.add2("answer", e.answer);
		AfSqlWhere w = new AfSqlWhere().add2("id", example_id);
		
		// 插入数据库
		String sql = "" + u + w;
		MyC3P0Factory.execute( sql );
		
		
		
		
	}

}
