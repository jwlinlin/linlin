package test.admin.course;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;
import test.admin.db.Course;

public class CourseAddApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin=new Permissions(this).checkLogin();
		String title=jreq.getString("title");
		Course c=new Course();
		c.setTitle(title);
		MyC3P0Factory.insert(c);
		JSONObject row=new JSONObject(c);
		return row;
	}

}
