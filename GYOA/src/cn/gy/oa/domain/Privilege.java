package cn.gy.oa.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体表 : 权限与角色是多对多的关系
 * 			 权限自关联： 一对多的关系，因为一个大权限包含多个小权限
 * @author dudu
 * 角色还是手动创建权限表
 *
 * 
 */
public class Privilege {
	private Long id;//主键
	private Set<Role> roles = new HashSet<Role>();//关联属性
	
	//自关联关系设置：父亲与孩子
	private Privilege parent;
	private Set<Privilege> childrens = new HashSet<>();
	
	//一般关系属性
	//创建时间和修改时间
	private Timestamp crtDate;
	private Timestamp updDate;
	//创建人和修改人
	private User crtUser;
	private User updUser;
	
	//特殊属性:与权限相关的所有属性:URL
	private String privUrl;//对应着功能:url不能直接暴露====>设计成无意义的名称显示给用户
	private String privName;//权限名字:保证唯一性
	private String privIcon;//url对应唯一图标路径:可以只写图标的名称，将图标放到统一的路径
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public Set<Privilege> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<Privilege> childrens) {
		this.childrens = childrens;
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
	public String getPrivUrl() {
		return privUrl;
	}
	public void setPrivUrl(String privUrl) {
		this.privUrl = privUrl;
	}
	public String getPrivName() {
		return privName;
	}
	public void setPrivName(String privName) {
		this.privName = privName;
	}
	public String getPrivIcon() {
		return privIcon;
	}
	public void setPrivIcon(String privIcon) {
		this.privIcon = privIcon;
	}
	public Privilege(Privilege parent, String privUrl, String privName,
			String privIcon) {
		super();
		this.parent = parent;
		this.privUrl = privUrl;
		this.privName = privName;
		this.privIcon = privIcon;
	}
	public Privilege() {
		super();
	}
	
	
}
