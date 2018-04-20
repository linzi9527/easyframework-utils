package org.easyframework.filter.user;

import com.summaryday.framework.a.Colum;
import com.summaryday.framework.a.Key;
import com.summaryday.framework.a.Table;
import com.summaryday.framework.a.Colum.ObjTypes;


@Table(name="t_permission")
public class Permission {

	@Key(isPrimary=true)
	@Colum(columName="id",isNUll=false,type=ObjTypes.STRING)
	private String id;
	
	@Colum(columName="role_id",isNUll=false,type=ObjTypes.STRING)
	private String roleId;
	
	@Colum(columName="menu_name",isNUll=false,type=ObjTypes.STRING)
	private String menuName;
	
	@Colum(columName="menu_url",isNUll=false,type=ObjTypes.STRING)
	private String menuUrl;
	
	@Colum(columName="menu_order",isNUll=false,type=ObjTypes.INT)
	private int menuOrder;
	
	@Colum(columName="permission",isNUll=false,type=ObjTypes.STRING)
	private String permission;
	
	@Colum(columName="root_id",isNUll=false,type=ObjTypes.STRING)
	private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	
}
