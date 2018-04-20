package org.easyframework.filter.user;

import com.summaryday.framework.a.Colum;
import com.summaryday.framework.a.Colum.ObjTypes;
import com.summaryday.framework.a.Key;
import com.summaryday.framework.a.Table;


@Table(name="t_menu")
public class Menu {

	@Key(isPrimary=true)
	@Colum(columName="id",isNUll=false,type=ObjTypes.STRING)
	private String id;
	
	@Colum(columName="menuname",isNUll=false,type=ObjTypes.STRING)
	private String menuName;
	
	@Colum(columName="menuurl",isNUll=false,type=ObjTypes.STRING)
	private String menuUrl;
	
	@Colum(columName="menuorder",isNUll=false,type=ObjTypes.INT)
	private int menuOrder;
	
	@Colum(columName="isopen",isNUll=false,type=ObjTypes.INT)
	private int isOpen;
	
	@Colum(columName="parentid",isNUll=false,type=ObjTypes.STRING)
	private String parentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
	
}
