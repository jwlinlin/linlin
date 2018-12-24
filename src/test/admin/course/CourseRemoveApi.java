package test.admin.course;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.Permissions;
import test.admin.db.Admin;
import test.admin.db.Example;

public class CourseRemoveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin=new Permissions(this).checkLogin();
		int id=jreq.getInt("id");
		String sql="DELETE a,b,c FROM course a INNER JOIN chapter b"
				+ " ON a.id=b.course INNER JOIN `example` c ON a.id=c.`course`"
				+ " WHERE a.id="+id;
		String sql2="SELECT * FROM example WHERE course="+id;
		Example e=(Example)MyC3P0Factory.get(sql2, Example.class);
		if(e!=null)
		{
			MyC3P0Factory.execute(sql);
		}
		else
		{
			String sql3="DELETE a,b FROM course a INNER JOIN chapter b"
					+ " ON a.id=b.course "
					+ " WHERE a.id="+id;
			MyC3P0Factory.execute(sql3);
		}
		
		
		return null;
		
	}

}
