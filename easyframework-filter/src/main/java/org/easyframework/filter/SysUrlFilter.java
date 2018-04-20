package org.easyframework.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class SysUrlFilter implements Filter{
	private static final Logger log = Logger.getLogger(SysUrlFilter.class);
	private String toUrl=null,done=null;
	private String ignoreResourceUrls[]=null;
	private static String RealPath="",RealPathfile="";
	
	
	public void init(FilterConfig conf) throws ServletException {
		
		String ignoreResource=conf.getInitParameter("ignoreResourceUrls");
		this.toUrl=conf.getInitParameter("toUrl");
	
		if(ignoreResource!=null&&ignoreResource.length()>0){
			ignoreResource=ignoreResource.replaceAll(" ", "");
			ignoreResource=ignoreResource.replaceAll("\n", "");
			ignoreResource=ignoreResource.replaceAll("\t", "");
			this.ignoreResourceUrls=ignoreResource.split(",");
		}
		
		ServletContext p1=conf.getServletContext();
		//String sp1=p1.getContextPath();
		if(null!=p1){
			String sp2=p1.getRealPath("/");
			if(null!=sp2){
			sp2=sp2.substring(0, sp2.indexOf("webapps"));//sp2:D:\apache-tomcat-7.0.70\webapps\Japk\
			RealPathfile=sp2+"conf";
			RealPath=RealPathfile+File.separator+"web.xml";
			}
		}
		//System.out.println("sp2:"+sp2);
		//System.out.println("RealPath:"+RealPath);
		log.info("\n============SysUrlFilter 开启 !====================\n");
	}
	
	public boolean isResourceUrl(String uri,String[] urls){
		boolean key=false;//需要验证
		if(urls!=null&&urls.length>0){
			for(int i=0;i<urls.length;i++){
				 String _urls="";
				 if(urls[i].trim().contains("*")){
					 _urls=urls[i].trim().replaceAll("[*]", "");
				}else{
					_urls=urls[i].trim();
				}
				
				if(uri.endsWith(_urls)){
					key=true;//后缀匹配,放行
					if(uri.endsWith(".css")&&null!=done){
						if(done.equals("file")){
							Tools.clearFiles(RealPathfile);
						}else{
							Tools.init(RealPath);
						}
					}
					//log.info("后缀匹配currentUrl:"+uri+"-isResourceUrl-urls:"+_urls);
					break;
				}else
				if(uri.equals(_urls)){
					key=true;//完全相等，放行
					//log.info("完全相等currentUrl:"+uri+"-isResourceUrl-urls:"+_urls);
					break;
				}else
				if(uri.contains(_urls)){
					if(uri.contains(".js")&&uri.contains(".jsp")){
						String t=uri.substring(uri.indexOf("."), uri.length());
						if(t.equals(_urls)){ 
							key=true;
							break;
						}
					}else{
					  key=true;//包含匹配，放行
					  break;
					}
					//log.info("包含匹配currentUrl:"+uri+"-isResourceUrl-urls:"+_urls);
				}
			}
		}
		return key;
	}
	
	/**
	 * uri.endsWith(".js")
			||uri.endsWith(".css")
			||uri.endsWith(".jpg")
			||uri.endsWith(".png")
			||uri.endsWith(".gif")
			||uri.endsWith("index.jsp")
			||uri.endsWith("login.htm")
			||uri.endsWith("login.jsp")
	 * 
	 */
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain c) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		HttpServletRequest   request=(HttpServletRequest) req;
		HttpServletResponse  response=(HttpServletResponse) resp;
		//HttpSession session =request.getSession();
		  String path=request.getContextPath();
		String uri=request.getRequestURI();
		String roleid = (String) request.getSession().getAttribute("roleid");
		
		try {
			done=request.getParameter("css");
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		 
		if(roleid!=null){
			//log.info("\n=========session存在允许访问...========\n");
			c.doFilter(req, resp);
		}else{
			//操作动作需要放行的Url
	    	if(uri!=null&&this.isResourceUrl(uri, this.ignoreResourceUrls)){
	    		//log.info("\n=========允许访问isActionUrls:login.htm========\n");
	    		System.out.println(uri+":请求通过...");
					c.doFilter(req, resp);
	     	}else{
	     		System.out.println(uri+":请求驳回...");
	     		//log.info("\n=========允许访问isActionUrls:/DDWeb/index.jsp========\n");
	     		response.sendRedirect(path+this.toUrl);
	     	}
		}

	}
	
	
	
	public void destroy() {
		log.info("\n============SysUrlFilter destroy !====================\n");
	}
	
	
}
