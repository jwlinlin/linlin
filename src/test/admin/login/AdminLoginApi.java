package test.admin.login;

import org.json.JSONObject;

import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import test.admin.MyC3P0Factory;
import test.admin.db.Admin;

public class AdminLoginApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		String username=jreq.getString("username");
		String password=jreq.getString("password");
		AfSqlWhere where=new AfSqlWhere().add2("username", username);
		String sql="SELECT * FROM admin"+where;
		Admin row=(Admin)MyC3P0Factory.get(sql, Admin.class);
		if(row.username==null)
			throw new Exception("用户不存在或密码错误");
		if(!row.password.equals(password))
			throw new Exception("用户不存在或密码错误");
		//把登录信息传到会话里面
		httpReq.getSession().setAttribute("admin", row);
		return null;
	}

}
