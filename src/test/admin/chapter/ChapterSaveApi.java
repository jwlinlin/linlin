package test.admin.chapter;



import org.json.JSONArray;
import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;
import test.admin.db.Chapter;


public class ChapterSaveApi extends AfRestfulApi
{
	public ChapterSaveApi()
	{
		this.enableErrorLog = true;
	}
	
	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin = new Permissions(this).checkLogin();
		
		int course = jreq.getInt("course"); // 当前课程ID
		
		// 课程下的章节列表
		JSONArray chapter_list = jreq.getJSONArray("chapter_list");
		
		// 先从chapter表中删除相关条目 
		if(true)
		{
			String sql = "delete from chapter where course=" + course;
			MyC3P0Factory.execute(sql);
		}
		
		// 再依次添加
		for(int i=0; i<chapter_list.length(); i++)
		{
			String title = chapter_list.getString(i);
			Chapter row = new Chapter();
			row.setTitle( title );
			row.setCourse( course);
			row.setNumber( i + 1); // 按顺序对章进行编号
			MyC3P0Factory.insert( row );
		}
		return null;
	}

}
