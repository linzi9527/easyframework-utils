package org.easyframework.tag;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.easyframework.filter.user.Permission;

@SuppressWarnings("serial")
public class QueryControlTag extends TagSupport {

	/**
	 * 通过自定义权限控制标签，来控制页面相关操作的显示
	 * 查看按钮，列表
	 */
	private static final Logger log = Logger.getLogger(QueryControlTag.class);
	private List<Permission> userPermission;
	private String roleid;
	private String url;
	private String method;
	
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		boolean validate=false;//鉴权失败,隐藏标签内容
			// 这里进行鉴权 JSP引擎是否解析标签体的内容
			HttpSession session = super.pageContext.getSession();
			//String sessionId = session.getId();
			//log.info("Q-sessionId:"+sessionId);
			 
			roleid = (String)session.getAttribute("roleid");
			 userPermission=(List<Permission>) session.getAttribute("userPermission");
			 if(method==null){method="R";}
			 //log.info("page-url:"+url+"|page-method:"+method);
			 
			 if(roleid!=null&&userPermission!=null){
				//鉴权验证
				 for(Permission p:userPermission){
					if(p.getRoleId()==roleid&&p.getMenuUrl().contains(url)&&p.getPermission().contains(method)){
						log.info("url:"+p.getMenuUrl()+"|method:"+p.getPermission());
							validate=true;//鉴权成功,显示标签内容
							break;
					}
				 }
			
			 }

			return validate ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	

	public List<Permission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(List<Permission> userPermission) {
		this.userPermission = userPermission;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	
}
