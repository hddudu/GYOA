package cn.gy.oa.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色表
 * @author dudu
 *
 */
@SuppressWarnings("serial")
public class Role implements Serializable{
	private Long id;
	private String roleName;
	private String roleDesc;
	public Role() {
		super();
	}
	//岗位与用户
	Set<User> users=new HashSet<User>();
	
	//岗位与权限
	Set<Privilege> privileges = new HashSet<Privilege>();
	
	//创建时间和修改时间
	private Timestamp crtDate;
	private Timestamp updDate;
	//创建人和修改人
//	private Long crtId;
//	private Long updId;
	private User crtUser;
	private User updUser;
	
	public Role(Long id, String roleName, String roleDesc) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
	}

	public Role(Long id, String roleName, String roleDesc, Timestamp crtDate,
			Timestamp updDate) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.crtDate = crtDate;
		this.updDate = updDate;
	}

	public Role(Long id, String roleName, String roleDesc, Set<User> users) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Timestamp getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Timestamp crtDate) {
		this.crtDate = crtDate;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public User getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(User crtUser) {
		this.crtUser = crtUser;
	}

	public User getUpdUser() {
		return updUser;
	}

	public void setUpdUser(User updUser) {
		this.updUser = updUser;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}	
