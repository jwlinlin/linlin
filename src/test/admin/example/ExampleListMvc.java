package test.admin.example;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import af.web.mvc.AfSimpleMVC;
import test.admin.MyC3P0Factory;
import test.admin.db.Chapter;
import test.admin.db.Course;
@WebServlet("/example.do")
public class ExampleListMvc extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		
		//取得所有课程
		
		String sql="SELECT * FROM course ORDER BY id ASC";
		List<Course>course_list=MyC3P0Factory.executeQuery(sql, Course.class);
		model.put("course_listJ", new JSONArray(course_list));
		//取得所有章节
		String sql1="SELECT * FROM chapter ORDER BY course ASC,number ASC";
		List<Chapter>chapter_list=MyC3P0Factory.executeQuery(sql1, Chapter.class);
		model.put("chapter_listJ", new JSONArray(chapter_list));
		
		return "/example.jhtml";
	}

}
