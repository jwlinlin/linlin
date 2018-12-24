package test.admin.profile;

import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import af.web.restful.AfRestfulException;
import test.admin.MyC3P0Factory;
import test.admin.db.Admin;

public class PasswordModifyApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		String username=jreq.getString("username");
		String new_password=jreq.getString("new_password");
		String old_password=jreq.getString("old_password");
		AfSqlWhere where=new AfSqlWhere().add2("username", username);
		String s1="SELECT * FROM admin"+where;
		Admin row=(Admin)MyC3P0Factory.get(s1, Admin.class);
		if(username.equals(row.username)&&old_password.equals(row.password))
		{
			update(new_password, row);
		}
		else
		{
			throw new AfRestfulException(500, "用户名或密码不正确,请重新输入");
		}
		
		
		return null;
	}
	private void update(String new_password,Admin row)throws Exception
	{
		AfSqlUpdate u=new AfSqlUpdate("admin");
		u.add2("password", new_password);
		AfSqlWhere where=new AfSqlWhere().add2("username", row.username);
		
		String s2=""+u+where;
		System.out.println("SQL:"+s2);
		MyC3P0Factory.execute(s2);
	}

}
