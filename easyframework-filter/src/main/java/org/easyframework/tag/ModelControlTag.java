package org.easyframework.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.easyframework.filter.user.Permission;

@SuppressWarnings("serial")
public class ModelControlTag extends TagSupport {

	/**
	 * 通过自定义模块菜单权限控制标签，来控制页面菜单相关操作的显示
	 * 模块中包含各项菜单
	 */
	private static final Logger log = Logger.getLogger(ModelControlTag.class);
	private List<Permission> userPermission;
	private String roleid;
	private String path;
	private String parentId;

	private String cssClass;
	private String targetId;
	
	@Override
	public int doStartTag() throws JspException {

		log.info("********* Model doStartTag()........\n");

		return EVAL_BODY_INCLUDE;
	}

	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		log.info("*********Model  doEndTag()........\n");
		StringBuffer sb = new StringBuffer();
		// 这里进行鉴权 JSP引擎是否解析标签体的菜单内容
		HttpSession session = super.pageContext.getSession();
		//String sessionId = session.getId();
		//log.info("模块SID:"+sessionId);
		roleid = (String) session.getAttribute("roleid");
		userPermission = (List<Permission>) session
				.getAttribute("userPermission");
		if (roleid != null && userPermission != null) {
			if(parentId==null){parentId="root";}
			// 鉴权验证
			for (Permission p : userPermission) {
				if (p.getRoleId()==roleid&&p.getParentId()==parentId) {
					// 鉴权成功,显示标签内容
					sb.append("<li class='"+cssClass+"'><a target='"+targetId+"' href='"
							+ this.path
							+ p.getMenuUrl()
							+ "'>"
							+ p.getMenuName() + "</a></li>");
				}
			}
		}

		JspWriter out = this.pageContext.getOut();

		try {
			out.println(sb.toString());
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("模块标签异常："+e.getMessage());
		}
		return super.doEndTag();
	}


	public List<Permission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(List<Permission> userPermission) {
		this.userPermission = userPermission;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	
}
