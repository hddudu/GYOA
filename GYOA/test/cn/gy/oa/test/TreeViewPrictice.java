package cn.gy.oa.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ognl.Ognl;
import ognl.OgnlException;

import org.junit.Test;

import cn.gy.oa.domain.Department;
import cn.gy.oa.domain.User;

/**
 * @author dudu
 *	
 *	说明： 不能使用多层循环的方式，因为需要支持任意层
 */
public class TreeViewPrictice {
	/**
	 * 练习一：打印所有顶层部门及其子孙部门的信息（名称） 提示：假设有一个 打印部门树 的信息 的方法
	 * 
	 * 要求打印如下效果：
	 * 
	 * <pre>
	 * 市场部
	 * 宣传部
	 * 业务部
	 * 业务一部
	 * 业务二部
	 * 开发部
	 * 开发一部
	 * 开发二部
	 * </pre>
	 */
	@Test
	public void printAllDepts_1() {
		List<Department> topList = findTopLevelDepartment();
		//方式一:没有格式，遍历所有
//		for(Department top : topList) {
//			showTree(top);
//		}
//		市场部
//		宣传部
//		业务部
//		业务部一部
//		业务部二部
//		开发部
//		开发一部
//		开发二部

		
		//方式二
		showTreeList(topList);
//		市场部
//		宣传部
//		业务部
//		业务部一部
//		业务部二部
//		开发部
//		开发一部
//		开发二部
		
	}
	
	/**
	 * 练习二：打印所有顶层部门及其子孙部门的信息（名称），用不同的缩进表示层次（使用全角空格）。<br>
	 * 子部门的名称前比上级部门多一个空格，最顶层部门的名字前没有空格。 提示：假设有一个打印部门集合中所有部门信息的方法
	 * 
	 * 要求打印如下效果：
	 * 
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 */
	@Test
	public void printAllDepts_2() {
		List<Department> topList = findTopLevelDepartment();
		
		showTreeList_2(topList, "┝");
	}
	
	/**
	 * @param topList
	 * @param string
	 */
	private void showTreeList_2(Collection<Department> topList, String prefix) {
		for(Department top : topList) {
			//顶点
			System.out.println(prefix + top.getDeptName());//底部的时候： "   业务一部"
			//子树
			showTreeList_2(top.getDepartments(), "　" + prefix);
		}
	}

	//显示一棵树的信息
	private void showTree(Department top) {
		//顶点
		System.out.println(top.getDeptName());
		//子树
		for(Department child : top.getDepartments()) {
			showTree(child);
		}
	}
	
	//显示多棵树的信息
	private void showTreeList(Collection<Department> topList) {
		for(Department top : topList) {
			//顶点
			System.out.println(top.getDeptName());
			//子树
			showTreeList(top.getDepartments());
		}
	}
	
	//
	/**
	 * 结构如下：
	 * 
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 * 
	 * @return 所有最顶层的部门的列表
	 */
	public static List<Department> findTopLevelDepartment() {
		Department dept_1_1 = new Department();
		dept_1_1.setId(new Long(11));
		dept_1_1.setDeptName("宣传部");
		
		Department dept_1_2 = new Department();
		dept_1_2.setId(new Long(12));
		dept_1_2.setDeptName("业务部");
		
		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(new Long(121));
		dept_1_2_1.setDeptName("业务部一部");
		
		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(new Long(122));
		dept_1_2_2.setDeptName("业务部二部");
		
		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		
		Set<Department> children_0 = new LinkedHashSet<Department>();
		children_0.add(dept_1_2_1);
		children_0.add(dept_1_2_2);
		dept_1_2.setDepartments(children_0);
		
		//===============================
		Department dept_1 = new Department();
		dept_1.setId(new Long(1));
		dept_1.setDeptName("市场部");
		
		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> children_1 = new LinkedHashSet<Department>();
		children_1.add(dept_1_1);
		children_1.add(dept_1_2);
		dept_1.setDepartments(children_1);
		
		// ---
		
		Department dept_2_1 = new Department();
		dept_2_1.setId(new Long(21));
		dept_2_1.setDeptName("开发一部");

		Department dept_2_2 = new Department();
		dept_2_2.setId((new Long(22)));
		dept_2_2.setDeptName("开发二部");

		Department dept_2 = new Department();
		dept_2.setId(new Long(2));
		dept_2.setDeptName("开发部");

		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> children_2 = new LinkedHashSet<Department>();
		children_2.add(dept_2_1);
		children_2.add(dept_2_2);
		dept_2.setDepartments(children_2);

		// ---

		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		return depts;
		
	}
	
	/**
	 * 二分搜索:
	 * 	前提是有序数组
	 * @param a : 搜索数组
	 * @param num : 目标数字
	 * @param left : 区间左边指针
	 * @param right ： 区间右边指针
	 * @param mid ： 区间中间基准
	 */
	private static int search(int[] a, int num, int left, int right, int mid) {
		if(a[mid] == num) {
			//找到了，返回
			return mid;
		}
		if(left >= right) {//找完了还没找到
			return -1;
		}
		
		if(a[mid] >= num) {//往左边找
			right = mid - 1;
			mid = left + (right - left ) / 2;
			return search(a, num, left, right, mid);
		}
		
		if(a[mid] <= num) {//往右边找
			left = mid + 1;
			mid = left + (right - left ) / 2;
			return search(a, num, left, right, mid);
		}
		return -1;
	}
	
	@Test
	public void testSearch() {
//		int[] a = {1,2,5,3,2,6,7,8,10};
		int[] a = {1,2,3,4,5,6,7,8,10};
		int des = 5;
		System.out.println(search(a, des, 0, a.length - 1, a.length / 2));
	}
	@Test
	public void test00() throws OgnlException {
		User user = new User();
		Ognl.getValue("loginName='hongdu'", new HashMap(), user);
		System.out.println(user.getLoginName());
		String name = (String) Ognl.getValue("getLoginName()", new HashMap(), user);
		System.out.println(name);
		//创建map集合
		/*
		 * 格式: #{'key' : 'value','key' : 'value'}
		 */
		Map map = (Map) Ognl.getValue("#{'loginName' : 'hongdu','realName' : 'hongsssssdu'}", new HashMap(), user);
		System.out.println(map.get("realName"));
	}
	/**
	 * 主要测试： struts2和ognl表达式的结合
	 * ognl表达式：
	 * 	①ognl的表达式的取值范围只能在某context和root中：
	 * 	格式为： ognl.getValue(expression,context,root);
	 * 						(String, Map, Object)三种类型;
	 * 	//
	 * struts2和ognl结合之后呢：
	 * ①OGNL的上下文context 就是 struts2 中的actionContext
	 * ②OGNL的root（根对象）就是struts2 中的valueStack
	 * 那么ActionContext和ValueStack的关系是什么呢？
	 * 	ActionContext本质也是一个Map;
	 * 		①以键值对的形式存储对象
	 * request、session、application这种我们熟知的作用域，注意是作用域，而不是对象
	 * 		②parameters 是表单提交的参数，全部都会放到map中，也就是ActionContext中
	 * 		③attr(attributes) ： 三个作用域所有的属性都会放到该map下，如果有重复的，以request中的为准，
	 * 			请求范围： 从
	 * 		④VALUE_STACK： 值栈，存放着valuestack对象，也就是说ActionContext能够获取到valueStack
	 */
	@Test
	public void testOgnlStruts2() {
		
	}
	
}
