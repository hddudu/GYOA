package cn.gy.oa.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Department implements Serializable{
	private Long id;
	private Department parent;
	private Set<User> users=new HashSet<User>();
	private Set<Department> departments=new HashSet<Department>();
	
	private String deptName;
	private String deptDesc;
	
	//创建时间和修改时间
	private Timestamp crtDate;
	private Timestamp updDate;
	//创建人和修改人
	//private Long crtId;
	//private Long updId;
	private User crtUser;
	private User updUser; 
	
	public Department() {
	}
	public Department(Long id, Department parent, Set<User> users,
			Set<Department> departments, String deptName, String deptDesc) {
		super();
		this.id = id;
		this.parent = parent;
		this.users = users;
		this.departments = departments;
		this.deptName = deptName;
		this.deptDesc = deptDesc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public Date getCrtDate() {
		return crtDate;
	}
	
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	public void setCrtDate(Timestamp crtDate) {
		this.crtDate = crtDate;
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
	
}
