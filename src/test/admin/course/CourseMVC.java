package test.admin.course;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import af.web.mvc.AfSimpleMVC;
import test.admin.MyC3P0Factory;
import test.admin.db.Course;



@WebServlet("/course.do")
public class CourseMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		String sql = "select * from course order by id ASC";
		List<Course> rows = MyC3P0Factory.executeQuery(sql, Course.class);
		model.put("course_list", rows);
		
		return "/course.jhtml";
	}
}
