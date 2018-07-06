package cn.gy.oa.view.action;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.gy.oa.service.DepartmentService;
import cn.gy.oa.service.ForumManageService;
import cn.gy.oa.service.PrivilegeService;
import cn.gy.oa.service.RoleService;
import cn.gy.oa.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
getClass().getGenericSuperclass() 
返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type，然后将其转换ParameterizedType。
getActualTypeArguments() 
返回表示此类型实际类型参数的 Type 对象的数组。[0]就是这个数组中第一个了。简而言之就是获得超类的泛型参数的实际类型。
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource//注入实体<进行实例化
	protected UserService userService;
	
	//查询部门和岗位
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected DepartmentService departmentService;
	
	@Resource
	protected PrivilegeService privilegeService;
	
	@Resource
	protected ForumManageService forumManageService;
	
//	protected Role model = new Role();
	
	protected T model;//泛型不能实例化==》需要解决实例化的问题
	
	//new子类的时候，会调用父类的这个构造方法==>将泛型实例化
	public BaseAction() {
		try {
			//XXX:得到model的真实类型
			//FIXME:只会new子类，获取的是子类的类
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type，然后将其转换ParameterizedType
			Class clazz = (Class) pt.getActualTypeArguments()[0];
			//获得超类的泛型参数的实际类型
			
			//通过反射生成model的实例
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public T getModel() {
		return model;
	}
	//没有具体类型==》生成实例就只有反射了
	//放到一个初始化方法中，且只执行一次
	//一般放到构造方法中
}
