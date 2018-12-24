package test.admin.example;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.db.Example;

public class ExampleLoadApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		int example_id=jreq.getInt("example_id");
		String sql="select * from example where id="+example_id;
		Example row=(Example)MyC3P0Factory.get(sql, Example.class);
		
		return new JSONObject(row);
	}

}
