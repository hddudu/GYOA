package cn.gy.oa.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class User implements Serializable {
	private Long id;
	private String loginName;//登陆名
	//关联关系
	private Department department;
	private Set<Role> roles=new HashSet<Role>();
	
	private String password;
	private String realName;
	private String gender;
	private String email;
	private String phoneNumber;
	private String userDesc;//说明
	private String status;//用户状态：是否可用
	
	//创建时间和修改时间
	private Timestamp crtDate;
	private Timestamp updDate;
	//创建人和修改人
	//private Long crtId;
	//private Long updId;
	private User crtUser;
	private User updUser;
	
	/**
	 * 判断本用户是否有指定名称的权限
	 * @param privName
	 * @return
	 */
	public boolean hasPrivilegeByName(String privName) {
		//超级管理员有所有的权限
		if(isAdmin()) {
			return true;
		}
		
		//一般用户要是有权限才返回true
			//如果是需要控制的功能，则有权限才可以使用
			for(Role role : roles) {
				for(Privilege privilege : role.getPrivileges()) {
					if(privilege.getPrivName().equals(privName)) {
						return true;
					}
				}
			}
		return false;
	}
	
	/**
	 * 判断本用户是否有指定url的权限
	 * @param privUrl
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
		//超级管理员有所有的权限
		if(isAdmin()) {
			return true;
		}
		
		//如果以UI后缀结尾，就去掉UI后缀，以得到对应的权限，
		if(privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}
		
		//一般用户要是有权限才返回true
		//在启动的时候就进行查询所有的URL
		List<String> allPrivilegeUrls = (List<String>) ActionContext.getContext().getSession().get("allPrivilegeUrls");
		if(allPrivilegeUrls.contains(privUrl)) { 
			//如果是不需要控制的功能，则所有用户都可以使用
			return true;
		} else {
			
			for(Role role : roles) {
				for(Privilege privilege : role.getPrivileges()) {
					if(privUrl.equals(privilege.getPrivUrl())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断是否是管理员
	 * @return
	 */
	private boolean isAdmin() {
		return "hongdu".equals(loginName) || "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getPassword() {
		if(this.password == null)
			return null;
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		if(this.realName == null)
			return null;
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGender() {
		if(this.gender == null)
			return null;
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		if(this.email == null)
			return null;
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		if(this.phoneNumber == null)
			return null;
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public User(Long id, String loginName, Department department,
			Set<Role> roles, String password, String realName, String gender,
			String email, String phoneNumber, String userDesc, String status,
			Timestamp crtDate, Timestamp updDate, User crtUser, User updUser) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.department = department;
		this.roles = roles;
		this.password = password;
		this.realName = realName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userDesc = userDesc;
		this.status = status;
		this.crtDate = crtDate;
		this.updDate = updDate;
		this.crtUser = crtUser;
		this.updUser = updUser;
	}
	public User(Long id, String loginName, Department department,
			Set<Role> roles, String password, String realName, String gender,
			String email, String phoneNumber, String userDesc, String status) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.department = department;
		this.roles = roles;
		this.password = password;
		this.realName = realName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userDesc = userDesc;
		this.status = status;
	}
	public User() {
		super();
	} 
	
}
