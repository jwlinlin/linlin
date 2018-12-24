package test.admin.example;

import org.json.JSONObject;

import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;

public class ExampleRemoveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		int example_id=jreq.getInt("example_id");
		
		String sql="DELETE FROM example WHERE id="+example_id;
		MyC3P0Factory.execute(sql);
		return null;
	}

}
