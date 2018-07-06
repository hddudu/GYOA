package cn.gy.oa.listener;

import cn.gy.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 只是拦截请求： 可以就继承这个抽象的拦截器，不用实现拦截器了
 * 	权限分类控制：
 * 	①登陆功能，未登陆时也可以使用
 *  ②不需要控制的功能，只要登陆就可以使用，不需要控制
 *  相当于一个鸡蛋： 蛋黄我们就进行控制；但是蛋白呢，我们就不进行控制
 * @author dudu
 * 
 * invocation.invoke() 就是通知struts2接着干下面的事情
比如 调用下一个拦截器 或 执行下一个Action
就等于退出了你自己编写的这个interceptor了

如何使用struts2拦截器，或者自定义拦截器。特别注意，在使用拦截器的时候，
在Action里面必须最后一定要引用struts2自带的拦截器缺省堆栈defaultStack,
如下(这里我是引用了struts2自带的checkbox拦截器)：

<interceptor-ref name="checkbox">
  <param name="uncheckedValue">0</param>
</interceptor-ref>
<interceptor-ref name="defaultStack"/>
(必须加，否则出错)
 *
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7115888044406029471L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		System.out.println("------> 之前");
//		//放行
//		String result = invocation.invoke();
//		System.out.println("------> 之后");
//		return result;
		
		/*
		 * 如果未登陆用户
		 *  如果不是去登陆，就转到登陆页面
		 *  如果是正在使用登陆功能，就放行
		 * 	转到登陆页面
		 * 
		 * 如果已登陆用户
		 * 	判断权限，如果有权限，就放行
		 *  如果没有权限，就转到提示页面
		 */
		//获取当前用户的URL,并去掉当前应用的前缀 : 也就是 namespace + ActionName
		String actionName = invocation.getProxy().getActionName();
		String nameSpace = invocation.getProxy().getNamespace();
		String url = null;
		if(nameSpace.endsWith("/")) {
			url = nameSpace + actionName;
		} else {
			url = nameSpace + "/" + actionName; 
		}
		//要去掉开头的 /
		if(url.startsWith("/")) {
			url = url.substring(1);
		}
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user == null) {
			//如果不是去登陆： 怎么判断是不是去登陆呢
			if(url.startsWith("userAction_login")) {//只能从url的特征去判断: userAction_login userAction_loginUI
				return invocation.invoke();
			} else {
				return "loginUI";
			}
		} else {
			//如果已经登陆用户，如果有权限就放行
			if(user.hasPrivilegeByUrl(url)) {
				return invocation.invoke();
			} else {
				return "noPrivilegeError";
			}
		}
	}

}
