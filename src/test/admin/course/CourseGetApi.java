package test.admin.course;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;
import test.admin.db.Course;

public class CourseGetApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin=new Permissions(this).checkLogin();
		int id=jreq.getInt("id");
		String sql="SELECT * FROM course WHERE id="+id;
		Course rows=(Course)MyC3P0Factory.get(sql, Course.class);
		return new JSONObject(rows);
	}

}
