package test.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
@WebServlet("*.jhtml")
public class JHtmlSupport extends HttpServlet
{
	  protected	Configuration frmkConfig;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//客户端要访问的URL路径
		String servlertPath=req.getServletPath();
		//首次运行时,加载HTML页面时,解析预处理得到Template对象,存到Configuration内部的Template Cache里
		//再次运行时直接从Template Cache 里面获取Template对象
		Template tp=null;
		try
		{
			tp=frmkConfig.getTemplate(servlertPath);
			
		} catch (Exception e)
		{
			resp.sendError(404,"File not exist"+servlertPath);
			return;//目标文件不存在,直接返回404
		}
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		try
		{
			Map<String, Object>model=new HashMap<String, Object>();
			model.put("basePath", req.getContextPath()+"/");//添加basePath变量
			tp.process(model, resp.getWriter());
		} catch (Exception e)
		{
			e.printStackTrace();
			resp.sendError(500,e.getMessage());
			
		}
	}
	
	@Override
	public void init() throws ServletException
	{
		//取得APP所在目录
		File appDir=new File(getServletContext().getRealPath("/"));
		try
		{
			frmkConfig=new Configuration(Configuration.VERSION_2_3_28);
			frmkConfig.setDirectoryForTemplateLoading(appDir);
			frmkConfig.setDefaultEncoding("utf-8");
			frmkConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			frmkConfig.setLogTemplateExceptions(false);
		} catch (Exception e)
		{
			System.out.println("This should not happend");
		}
	}

}
