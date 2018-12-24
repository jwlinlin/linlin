package test.admin.course;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.db.Course;

public class CourseListApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		String sql="SELECT * FROM course ORDER BY id ASC";
		List<Course>rows=MyC3P0Factory.executeQuery(sql, Course.class);
		return new JSONArray(rows);
	}

}
