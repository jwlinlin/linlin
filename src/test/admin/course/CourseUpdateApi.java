package test.admin.course;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;

public class CourseUpdateApi extends AfRestfulApi
{
	static Logger logger=Logger.getLogger(CourseUpdateApi.class);
	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin=new Permissions(this).checkLogin();
		String title=jreq.getString("title");
		int id=jreq.getInt("id");
		AfSqlUpdate u=new AfSqlUpdate("course");
		u.add2("title", title);
		AfSqlWhere where=new AfSqlWhere().add2("id", id);
		String sql=u.toString()+where;
		logger.debug("更新Course:SQL"+sql);
		MyC3P0Factory.execute(sql);
		return null;
	}

}
