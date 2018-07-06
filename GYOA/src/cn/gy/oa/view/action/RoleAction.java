package cn.gy.oa.view.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.gy.oa.domain.Privilege;
import cn.gy.oa.domain.Role;
import cn.gy.oa.domain.User;
import cn.gy.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 岗位管理:6个请求 + 4个页面 一条请求过来，首先准备值栈对象参数： ValueStack==(Context（Map） 还有一个栈)
 * request、session、application、attrribute、parameters==》被封装到一个栈中去 ==》然后转到Action
 * ==》 ==》
 * 
 * @author dudu
 * 
 */
@Controller
// 配置action的注解直接可以在struts2中找到这个bean
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1383452389379836124L;

//	@Resource
//	private RoleService roleService;

	// 通过属性做的封装==>必须有下面的属性才能获取
	//以下的属性才是在值栈中的，设置了以下的属性就能够在页面直接操作
//	private Long id;
//	private String roleName;
//	private String roleDesc;
//	private Timestamp crtDate;
//	private Timestamp updDate;
	
//	private Role model = new Role();
//	
//	@Override
//	public Role getModel() {
//		return model;
//	}


	private Long[] privilegeIds;
	
	/**
	 * 列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		if (roleList != null && roleList.size() > 0)
			ActionContext.getContext().put("roleList", roleList);
		else
			System.out.println("查出的值为空,不会吧???问题在哪里");// 查出的值为空,不会吧???问题在哪里
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 得到参数，封装成对象  当使用实体为model时，也可以直接使用model

//		Role role = new Role();
//		role.setRoleName(roleName);
//		role.setRoleDesc(roleDesc);
		Date date = new Date();
		Timestamp curDate1 = new Timestamp(date.getTime());
//		role.setCrtDate(curDate1);
		model.setCrtDate(curDate1);
		// 保存到数据库中
		roleService.save(model);
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		return "addUI";
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// 得到参数，封装成对象
//		Role role = new Role();
//		role.setId(id);
		//获取数据库中 的原对象
		Role role = roleService.getById(model.getId());
		//设置修改属性
//		role.setRoleName(roleName);
//		role.setRoleDesc(roleDesc);
		
		Date date = new Date();
		Timestamp updDate1 = new Timestamp(date.getTime());
//		role.setUpdDate(updDate1);
		role.setRoleName(model.getRoleName());
		role.setRoleDesc(model.getRoleDesc());
		role.setUpdDate(updDate1);
		// 更新到数据库中
		roleService.update(model);
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
//		Role role = new Role();
//		role = roleService.getById(id);
//		this.roleName = role.getRoleName();
//		this.roleDesc = role.getRoleDesc();
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);//将新获取的值放到栈顶
		return "editUI";
	}
	
	/**
	 * 设置权限
	 * @return
	 * @throws Exception
	 */
	public String setPrivilege() throws Exception {
		//从数据库中取出原来对象
		Role role = roleService.getById(model.getId());
		
		//设置要修改的属性
		Date date = new Date();
		Timestamp updDate1 = new Timestamp(date.getTime());
		role.setUpdDate(updDate1);
//		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<>((List<Privilege>)privilegeService.getByIds(privilegeIds)));
		//更新到数据库中
		roleService.update(role);
		return "toList";
	}

	/**
	 * 设置权限页面
	 * @return
	 * @throws Exception
	 */
	public String setPrivilegeUI() throws Exception {
		//准备数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		
		List<Privilege> topPrivilegeList = privilegeService.findTopAll();
//		for(int i = 0; i < topPrivilegeList.size(); i++) {
//			Privilege pv = topPrivilegeList.get(i);
//			List<Privilege> secondPrivilegeList = privilegeService.getSecondAll(pv.getId());
//		}
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
		//准备回显的数据
		//那么就是要查出中间表的数据角色权限表==》显示出来==>其实就是给privilegeIds赋值
		//就是根据角色id查出关联的权限id集合
		//因为数据表都转化成了对象，那么就是根据对象查出关联对象的集合
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for(Privilege priv : role.getPrivileges()) {
			privilegeIds[index++] = priv.getId();
		}
		return "setPrivilegeUI";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getRoleName() {
//		return roleName;
//	}
//
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
//
//	public String getRoleDesc() {
//		return roleDesc;
//	}
//
//	public void setRoleDesc(String roleDesc) {
//		this.roleDesc = roleDesc;
//	}
//
//	public Timestamp getCrtDate() {
//		return crtDate;
//	}
//
//	public void setCrtDate(Timestamp crtDate) {
//		this.crtDate = crtDate;
//	}
//
//	public Timestamp getUpdDate() {
//		return updDate;
//	}
//
//	public void setUpdDate(Timestamp updDate) {
//		this.updDate = updDate;
//	}

}
