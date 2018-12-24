package test.admin.chapter;

import org.json.JSONObject;

import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.db.Chapter;

public class ChapterGetApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		int chapter_id=jreq.getInt("chapter_id");
		int course_id=jreq.getInt("course_id");
		AfSqlWhere where=new AfSqlWhere();
		where.add2("number", chapter_id);
		where.add2("course", course_id);
		String sql="SELECT * FROM chapter"+where;//查询章序号
		Chapter row=(Chapter)MyC3P0Factory.get(sql, Chapter.class);
		
		return new JSONObject(row);
	}

}
