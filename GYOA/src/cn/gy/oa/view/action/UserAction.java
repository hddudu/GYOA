package cn.gy.oa.view.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.gy.oa.domain.Department;
import cn.gy.oa.domain.Role;
import cn.gy.oa.domain.User;
import cn.gy.oa.service.DepartmentService;
import cn.gy.oa.service.RoleService;
import cn.gy.oa.service.UserService;
import cn.gy.oa.utils.DepartmentUtils;
import cn.gy.oa.utils.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author dudu
 *
 */

@Controller
@Scope(value="prototype")//这两个不能继承==>只有那个事务才能继承
public class UserAction extends BaseAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long departmentId;
	
	private Long[] roleIds;
	
	/**列表: 目前没有分页**/
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		if(userList != null && userList.size() > 0)
			ActionContext.getContext().put("userList", userList);//将其放入到map中，在页面可以获取
		else 
			System.out.println("查出的值为空,不会吧???问题在哪里");
		return "list";
	}
	/**删除**/
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}
	/**新增**/
	public String add() throws Exception {
		//1：新建对象，并设置属性（也可以使用model）
		//部门与岗位未进行处理
//		if(departmentId != null)//如果经常返回null呢?
		model.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(roleIds);
//		Set<Role> roles = new HashSet<Role>(roleList);
//		roles.addAll(roleList);
		model.setRoles(new HashSet<>(roleService.getByIds(roleIds)));
//		if (roleIds != null && !"".equals(roleIds)) {
//			model.setRoles(roles);
//		}
		//2：保存到数据库当中，
		Date date = new Date();
		Timestamp curDate1 = new Timestamp(date.getTime());
		model.setCrtDate(curDate1);
		String passwordMD5 = DigestUtils.md5Hex("1234");
		model.setPassword(passwordMD5);// 默认密码为1234，应使用MD5摘要
		userService.save(model);
		return "toList";
	}
	/**新增页面:转到新增页面==》需要部门数据 + 岗位数据**/
	public String addUI() throws Exception {
		//FIXME:准备数据: department + role
		//TODO:应该是显示树状结构，目前是查询所有的部门
//		List<Department> departmentList = departmentService.findAll();//部门还要求是树状结构呢
		List<Department> topList = departmentService.findTopList();//寻找顶级部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);//将其放入到map中，在页面可以获取
		//准备数据 roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);//将其放入到map中，在页面可以获取
		return "addUI";
	}
	/**修改**/
	public String edit() throws Exception {
		User user = userService.getById(model.getId());
		Date date = new Date();
		Timestamp updDate1 = new Timestamp(date.getTime());
		user.setUpdDate(updDate1);
		user.setLoginName(StringUtils.removeNullPointer(model.getLoginName()));
		if(model.getDepartment() != null)
			user.setDepartment(model.getDepartment());
		user.setEmail(StringUtils.removeNullPointer(model.getEmail()));
		user.setGender(StringUtils.removeNullPointer(model.getGender()));
		user.setPassword(StringUtils.removeNullPointer(model.getPassword()));
		user.setPhoneNumber(StringUtils.removeNullPointer(model.getPhoneNumber()));
		//修改关联部门
		Department department = departmentService.getById(departmentId);
		user.setDepartment(department);
		
		//修改关联角色岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<>(roleList));
		userService.update(user);
		return "toList";
	}
	/**修改页面**/
	public String editUI() throws Exception {
		//准备部门的数据: 显示为树状结构
		List<Department> topList = departmentService.findTopList();//寻找顶级部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);//将其放入到map中，在页面可以获取
		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);//
		if(user.getRoles().size() > 0) {
			roleIds = new Long[user.getRoles().size()];//在右边已经选择的地方显示
			int index = 0;
			for(Role role : user.getRoles()) {
				roleIds[index] = role.getId();
				index++;
			}
		}
		//准备数据u:roleList:显示所有的角色
		List<Role> roleList = roleService.findNotAll(roleIds);
		ActionContext.getContext().put("roleList", roleList);
		//已经赋值的岗位
		List<Role> roleList2 = roleService.getByIds(roleIds);
		ActionContext.getContext().put("roleList2", roleList2);
		
		if(user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		return "editUI";
	}
	/**初始化密码**/
	public String initPassword() throws Exception {
		User user = userService.getById(model.getId());
		user.setPassword(DigestUtils.md5Hex("123456"));
		//为什么没有修改成功呢？是因为没有修改到数据库中啊
		userService.update(user);
		return "toList";
	}
	
	/**登陆页面**/
	public String loginUI() throws Exception {
		//不用准备数据
		return "loginUI";
	}
	/**登陆**/
	public String login() throws Exception {
		//数据准备+验证
		User user = userService.getByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if(user == null) {
			//用户名或密码不正确==>最好提示错误
			addFieldError("login", "用户名或密码不正确!");//国际化==》不同的地区显示不同的信息
			
			return "loginUI";
		} else {
			//登陆成功:并用session记录用户信息
			ActionContext.getContext().getSession().put("user", user);
			
			return "toIndex";//重定向到主页
		}
		
	}
	/**注销**/
	public String logout() throws Exception {
		//销毁登陆的信息
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
