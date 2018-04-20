package org.easyframework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 跨域
 */
public class crossDomain implements Filter {
	
	private static final Logger log = Logger.getLogger(crossDomain.class);
	
	private String[] crossUrls=null;
	private String IsOpen=null;
	
	public void init(FilterConfig conf) throws ServletException {
		 try {
			IsOpen=conf.getInitParameter("IsOpen");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("没有找到IsOpen参数，默认不开启跨域！");
		}
		String Urls=null;
		try {
			Urls = conf.getInitParameter("crossUrls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("没有找到crossUrls参数，默认不开启跨域！");
		}
	
		if(Urls!=null&&Urls.length()>0){
			Urls=Urls.replaceAll(" ", "");
			Urls=Urls.replaceAll("\n", "");
			Urls=Urls.replaceAll("\t", "");
			Urls=Urls.trim();
			if(Urls.equals("/*")){
				this.crossUrls=null;
				IsOpen="all";
			}else{
				this.crossUrls=Urls.split(",");
			}
		}
		
		log.info("\n============监控跨域 开启 !====================\n");
	}
	
	public boolean isResourceUrl(String uri,String[] urls){
		boolean key=false;//需要验证
		if(urls!=null&&urls.length>0){
			for(int i=0;i<urls.length;i++){
				
				if(uri.endsWith(urls[i])){
					key=true;//完全相等，放行
					break;
				}
				else
				if(uri.indexOf(urls[i])!=-1){
					  key=true;//包含匹配，放行
					  break;
				}
			}
		}
		return key;
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest   req=(HttpServletRequest) request;
		    //String path=req.getContextPath();
			String uri=req.getRequestURI();
          //开启跨域过滤 
		 if(null!=IsOpen&&("true".equals(IsOpen.toLowerCase())||"1".equals(IsOpen.toLowerCase()))){
				if(null!=crossUrls&&crossUrls.length>0){
					//指定可以跨域
				   if(uri!=null&&isResourceUrl(uri,crossUrls)){
					   res.setContentType("text/html;charset=UTF-8");
					   res.setHeader("Access-Control-Allow-Origin", "*");
					   res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
					   res.setHeader("Access-Control-Max-Age", "0");
					   res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
					   res.setHeader("Access-Control-Allow-Credentials", "true");
					   res.setHeader("XDomainRequestAllowed","1");
					   chain.doFilter(request, response);
				   }else{
					   log.info("监控到非法跨域地址，被拦截"+uri);
				   }
				}
          }else 
           if(null!=IsOpen&&"all".equals(IsOpen.toLowerCase())){
  				//默认全部可以跨域
  			   res.setContentType("text/html;charset=UTF-8");
  			   res.setHeader("Access-Control-Allow-Origin", "*");
  			   res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
  			   res.setHeader("Access-Control-Max-Age", "0");
  			   res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
  			   res.setHeader("Access-Control-Allow-Credentials", "true");
  			   res.setHeader("XDomainRequestAllowed","1");
  			 chain.doFilter(request, response);
  		   } 
           else{
        	  log.info("您访问的地址，需要注册跨域地址，已被拦截"+uri);
        	  chain.doFilter(request, response);
          }
		 
		 
/*			if(null!=crossUrls&&crossUrls.length>0){
				//指定可以跨域
			   if(uri!=null&&isResourceUrl(uri,crossUrls)){
				   res.setContentType("text/html;charset=UTF-8");
				   res.setHeader("Access-Control-Allow-Origin", "*");
				   res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
				   res.setHeader("Access-Control-Max-Age", "0");
				   res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
				   res.setHeader("Access-Control-Allow-Credentials", "true");
				   res.setHeader("XDomainRequestAllowed","1");
				   chain.doFilter(request, response);
			   }else{
				   log.info("监控到跨域地址，被拦截"+uri);
			   }
			}else{
				//默认全部可以跨域
				   res.setContentType("text/html;charset=UTF-8");
				   res.setHeader("Access-Control-Allow-Origin", "*");
				   res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
				   res.setHeader("Access-Control-Max-Age", "0");
				   res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
				   res.setHeader("Access-Control-Allow-Credentials", "true");
				   res.setHeader("XDomainRequestAllowed","1");
				   chain.doFilter(request, response);
			}*/
		  
	}

	public void destroy() {
		// TODO Auto-generated method stub
		log.info("系统关停，默认销毁跨域！");
	}



}
