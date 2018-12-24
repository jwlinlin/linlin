package test.admin.chapter;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import af.sql.AfSqlWhere;
import af.web.mvc.AfSimpleMVC;
import test.admin.MyC3P0Factory;
import test.admin.db.Chapter;
import test.admin.db.Course;

@WebServlet("/chapter.do")
public class ChapterMvc extends AfSimpleMVC
{
	// 在url里带上 id=nnn 参数

		@Override
		protected String execute(HttpServletRequest req, 
				HttpServletResponse resp, 
				HashMap<String, Object> model)	throws Exception
		{
			int id=Integer.valueOf(req.getParameter("id"));
			String sql="SELECT * FROM course WHERE id="+id;
			Course rows=(Course)MyC3P0Factory.get(sql, Course.class);

			model.put("course", rows);
			model.put("courseJ", new JSONObject(rows));
			if(true)
			{

				String s = "select * from chapter WHERE course="+id 
						+ " ORDER BY number ASC"; // 按章节编号升序
				//查询出数据库中的最大
				List chapter=MyC3P0Factory.executeQuery(s, Chapter.class);
				List<Chapter>rowss=MyC3P0Factory.executeQuery(s, Chapter.class);
				//model.put("chapter_list", rowss);
				model.put("chapterJ", new JSONArray(chapter));
			}
			return "/chapter.jhtml";
		}

}
