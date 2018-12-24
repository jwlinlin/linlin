package test.admin.example;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import af.web.mvc.AfSimpleMVC;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;
import test.admin.db.Chapter;
import test.admin.db.Course;
@WebServlet("/example_edit.do")
public class ExampleEditMvc extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		
		//取得课程信息
		int courseId=Integer.valueOf(req.getParameter("course"));
		String sql="SELECT * FROM course WHERE id="+courseId;
		Course row=(Course)MyC3P0Factory.get(sql, Course.class);
		if(row==null)
			throw new Exception("无此课程:"+courseId);
		model.put("course", row);
		
		//取得章节信息
		String sql1 = "select * from chapter  "
				+ " where course=" + courseId
				+ " ORDER BY course ASC, number ASC";
		List<Chapter>chapter_list=MyC3P0Factory.executeQuery(sql1, Chapter.class);
		model.put("chapter_list", chapter_list);
		return "/example_edit.jhtml";
	}

}
