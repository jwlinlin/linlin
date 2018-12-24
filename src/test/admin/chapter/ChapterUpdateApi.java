package test.admin.chapter;

import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;

public class ChapterUpdateApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		int id=jreq.getInt("id");
		String title=jreq.getString("title");
		AfSqlUpdate u=new AfSqlUpdate("chapter");
		u.add2("title", title);
		AfSqlWhere w=new AfSqlWhere().add2("id", id);
		String sql=u+""+w;
		MyC3P0Factory.execute(sql);
		
		return null;
	}

}
