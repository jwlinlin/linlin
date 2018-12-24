package test.admin.chapter;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;

public class ChapterRemoveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		int chapter_id=jreq.getInt("chapter_id");
		String sql="DELETE FROM chapter WHERE number="+chapter_id;
		MyC3P0Factory.execute(sql);
		return null;
	}

}
