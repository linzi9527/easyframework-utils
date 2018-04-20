package org.easyframework.filter.user;

import com.summaryday.framework.a.Colum;
import com.summaryday.framework.a.Key;
import com.summaryday.framework.a.Table;
import com.summaryday.framework.a.Colum.ObjTypes;


@Table(name="t_role")
public class Role {

	
	@Key(isPrimary=true)
	@Colum(columName="id",isNUll=false,type=ObjTypes.VARCHAR)
	private String id;

	@Colum(columName="rolename",isNUll=false,type=ObjTypes.VARCHAR)
	private String rolename;
	
	@Colum(columName="remarker",isNUll=false,type=ObjTypes.VARCHAR)
	private String remarker;
	
	
	@Colum(columName="status",isNUll=false,type=ObjTypes.VARCHAR)
	private String status;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRolename() {
		return rolename;
	}


	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public String getRemarker() {
		return remarker;
	}


	public void setRemarker(String remarker) {
		this.remarker = remarker;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
