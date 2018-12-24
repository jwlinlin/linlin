package test.admin.profile;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import af.web.mvc.AfSimpleMVC;
import test.admin.MyC3P0Factory;
import test.admin.db.Admin;
@WebServlet("/profile.do")
public class ProfileMvc extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse arg1, HashMap<String, Object> model)
			throws Exception
	{
		String sql="SELECT * FROM admin";
		Admin row=(Admin)MyC3P0Factory.get(sql, Admin.class);
		
		model.put("rows", row);
		
		
		
		return "/profile.jhtml";
	}

}
