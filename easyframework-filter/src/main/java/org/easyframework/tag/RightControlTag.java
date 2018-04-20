package org.easyframework.tag;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.easyframework.filter.user.Permission;

@SuppressWarnings("serial")
public class RightControlTag extends TagSupport {

	/**
	 * 通过自定义权限控制标签，来控制页面相关操作的显示
	 */
	private static final Logger log = Logger.getLogger(RightControlTag.class);
	private boolean validate = true;
	private Permission permission;
	private List<Permission> userPermission;
	private String roleid;
	private String url;
	private String method;
	
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		if (!this.validate) {
			return TagSupport.SKIP_BODY;
		} else {
			// 这里进行鉴权 JSP引擎是否解析标签体的内容
			HttpSession session = super.pageContext.getSession();
			//String sessionId = session.getId();
			//log.info("reight-sid:"+sessionId);
			roleid = (String)session.getAttribute("roleid");
			 userPermission=(List<Permission>) session.getAttribute("userPermission");
			 if(roleid!=null&&userPermission!=null){
				//鉴权验证
				 for(Permission p:userPermission){
					if(p.getRoleId()==roleid){
						log.info("url:"+p.getMenuUrl()+"|method:"+p.getPermission());
						if(p.getMenuUrl().contains(url)&&p.getPermission().contains(method)){
							log.info("page-url:"+url+"|page-method:"+method);
							validate=true;//鉴权成功,显示标签内容
							break;
						}
					}
				 }
			 }else{
				 validate=false;//鉴权失败,隐藏标签内容
			 }
			

			return validate ? EVAL_BODY_INCLUDE : SKIP_BODY;
		}
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
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
