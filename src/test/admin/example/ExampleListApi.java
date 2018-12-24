package test.admin.example;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import af.sql.AfSqlWhere;
import af.web.AfHttpReqParams;
import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;

public class ExampleListApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin=new Permissions(this).checkLogin();
		AfHttpReqParams params=new AfHttpReqParams(httpReq);
		//int start=params.getInt("start", 0);
		
		int course=jreq.getInt("course");//所属课程ID
		int chapter=0;
		if(jreq.has("chapter"))chapter=jreq.getInt("chapter");
		//构建两表联合查询的SQL语句
		AfSqlWhere where=new AfSqlWhere();
		if(course>0)where.add2("a.course", course);
		if(chapter>0)where.add2("a.chapter", chapter);
		//if(start>0)where.add("id<"+start);
		//每页放十条记录
		int limit=10;
		String sql="select a.id,a.chapter,a.course,rank,content,answer,b.title AS chapterTitle "
				+"from example a INNER JOIN chapter b "
				+"ON a.chapter=b.number AND a.course=b.course"
				+where
				+"ORDER BY a.chapter ASC,a.rank ASC ";//按章节号难度排序
		
		
		
		//查询出相关习题
		List<Map>rows=MyC3P0Factory.executeQuery2Map(sql);
		for(Map row:rows)
		{
			String content=(String)row.get("content");
			String contentHtml=text2Html(content);
			row.put("contentHtml", contentHtml);
			
			String answer=(String)row.get("answer");
			String answerHtml=text2Html(answer);
			row.put("answerHtml", answerHtml);
//			//计算出最后一条记录的id
//			int next=0;
//			if(rows.size()>=limit)
//			{
//				Map lastrow=(Map)rows.get(rows.size()-1);
//				next=(Integer)lastrow.get("id");
//							
//			}
//			row.put("next", next);
//			row.put("start", start);
			
		}
		
		//JSONArray jarray=MyC3P0Factory.executeQuery2JSON(sql);
		
		//return jarray;
		return new JSONArray(rows);
	}
	private String text2Html(String text)throws Exception
	{
		//换行 </br>
		//空格&nbsp
		//制表符&nbsp,&nbsp,&nbsp,&nbsp
		//进行格式转换,转换为HTML文件的格式
		text=text.replace("\n", "</br>")
				.replace(" ", "&nbsp;")
				.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return "<p>"+text+"</p>";
	}


}
