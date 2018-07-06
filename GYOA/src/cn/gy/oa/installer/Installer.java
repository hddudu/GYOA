package cn.gy.oa.installer;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.gy.oa.domain.Privilege;
import cn.gy.oa.domain.User;

/**
 * @author dudu
 * 运行是使用javac -encoding UTF-8 Installer.java
 * ==>导致了无法javac编译
 * 去除包名之后:
 * 	F:\myFrame\GYOA\src\cn\gy\oa\installer>javac -encoding UTF-8 Installer.java
 *	F:\myFrame\GYOA\src\cn\gy\oa\installer>java Installer
 */
@Component//交给spring管理
public class Installer {
	
	@Resource//注入实例
	private SessionFactory sessionFactory;
	
	@Transactional//数据库操作需要事务
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		
		System.out.println(DigestUtils.md5Hex("123456"));
		//超级管理员信息
		User user = new User();
		user.setLoginName("admin");
		user.setRealName("超级管理员1");
		user.setPassword("123456");
		session.save(user);//这个保存跨数据库
		
		User user2 = new User();
		user2.setLoginName("hongdu");
		user2.setRealName("超级管理员2");
		user2.setPassword(DigestUtils.md5Hex("123456"));//使用MD5摘要
		session.save(user2);//这个保存跨数据库
		
		//二、权限信息:统一保存文件名
		Privilege menu,menu1,menu2,menu3,menu4,menu5;
		menu = new Privilege(null, null, "系统管理", "FUNC20082.gif");
		menu1 = new Privilege(menu, "roleAction_list", "岗位管理", null);
		menu2 = new Privilege(menu, "departmentAction_list", "部门管理", null);
		menu3 = new Privilege(menu, "userAction_list", "用户管理", null);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		//岗位权限
		session.save(new Privilege(menu1, "roleAction_list", "岗位列表", null));
		session.save(new Privilege(menu1, "roleAction_delete", "岗位删除", null));
		session.save(new Privilege(menu1, "roleAction_add", "岗位添加", null));
		session.save(new Privilege(menu1, "roleAction_edit", "岗位修改", null));
		//部门权限
		session.save(new Privilege(menu2, "departmentAction_list", "部门列表", null));
		session.save(new Privilege(menu2, "departmentAction_delete", "部门删除", null));
		session.save(new Privilege(menu2, "departmentAction_add", "部门添加", null));
		session.save(new Privilege(menu2, "departmentAction_edit", "部门修改", null));
		//用户权限
		session.save(new Privilege(menu3, "userAction_list", "用户列表", null));
		session.save(new Privilege(menu3, "userAction_delete", "用户删除", null));
		session.save(new Privilege(menu3, "userAction_add", "用户添加", null));
		session.save(new Privilege(menu3, "userAction_edit", "用户修改", null));
		session.save(new Privilege(menu3, "userAction_initPassword", "用户初始化密码", null));
//		menu1 = new Privilege(menu, "GYOA/roleAction_list.action", "岗位管理", null);
//		menu2 = new Privilege(menu, "GYOA/departmentAction_list.action", "部门管理", null);
//		menu3 = new Privilege(menu, "GYOA/userAction_list.action", "用户管理", null);
		
		
		//-----------------------------网上交流模块
		menu = new Privilege(null, null, "网上交流", "FUNC20064.gif");
		menu1 = new Privilege(menu, "forumAction_list", "论坛", null);
		menu2 = new Privilege(menu, "forumManageAction_list", "论坛管理", null);
		//链接先不设计
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		
		//-----------------------------审批流转模块
		menu = new Privilege(null, null, "审批流转", "FUNC20057.gif");
		menu1 = new Privilege(menu, "processDefinitionAction_list", "审批流程管理", null);
		menu2 = new Privilege(menu, "applicationTemplateAction_list", "申请模板管理", null);
		menu3 = new Privilege(menu, "flowAction_applicationTemplateActionList", "起草申请", null);
		menu4 = new Privilege(menu, "flowAction_myTaskList", "待我审批", null);
		menu5 = new Privilege(menu, "flowAction_myApplicationList", "我的申请查询", null);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
	}
	
	public static void main(String[] args) {
		System.out.println("正在执行安装...");
		//自动读取jar包名字
		//静态的无法代理
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取实体bean
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
		System.out.println("安装完毕");
	}
}
