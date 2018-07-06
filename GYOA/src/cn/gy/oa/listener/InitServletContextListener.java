package cn.gy.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.gy.oa.domain.Privilege;
import cn.gy.oa.service.PrivilegeService;

/**
 * 初始化servlet容器监听器
 * @author dudu
 *  这个监听器是没有在spring容器中的,所以不能交给spring管理，spring就没法帮你做事情,也不能帮你注入
 *   
 *  那么办法就是： 直接拿到容器对象来进行操作
 */
public class InitServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sec) {
		ServletContext application = sec.getServletContext();
		//准备所有顶级权限的集合(顶级菜单)
		//得到service的bean对象实例 : 应该是从容器中获取的，如果是自己弄的话没人帮你管理事务等信息
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		
		List<Privilege> topPrivilegeList = privilegeService.findTopAll();//已经有了查询顶级菜单的方法
		
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		
		System.out.println("--已准备好顶级权限的数据--");
		
		//准备所有权限的url的集合
		List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("--已准备好所有权限的url的数据--");
	}

}
