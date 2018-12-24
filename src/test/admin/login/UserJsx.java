package test.admin.login;

import org.json.JSONObject;

import af.web.jsx.AfJsxCreator;
import test.admin.MyC3P0Factory;
import test.admin.db.Admin;

public class UserJsx extends AfJsxCreator
{

	@Override
	public Object execute() throws Exception
	{
		String sql="SELECT * FROM admin";
		Admin row=(Admin)MyC3P0Factory.get(sql, Admin.class);
		return new JSONObject(row);
	}

}
