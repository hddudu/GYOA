package cn.gy.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.gy.oa.domain.Department;

public class DepartmentUtils {
	
	/**
	 * 遍历部门树，得到所有的部门列表,并修改了名称
	 * @param topList
	 * @return
	 */
	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> allDept = new ArrayList<>();
		walkDeptTrees(topList, "┣", allDept);
		return allDept;
	}
	
	/**
	 * 遍历部门树，把遍历出来的部门都放到指定的集合中
	 * @param topList
	 */
	private static void walkDeptTrees(Collection<Department> topList, String prefix , List<Department> list) {
		//顶点
		for(Department top : topList) {
			//子树
//			top.setDeptName(prefix + top.getDeptName());//现在修改的是session中的对象，是持久化状态
			Department copy = new Department();
			copy.setId(top.getId());
			copy.setDeptName(prefix + top.getDeptName());
			list.add(copy);//元对象是在session中的对象，只是为了显示，可以清空session或者使用副本
//			System.out.println(top.getDeptName());
			//往同一个集合中加入参数
			walkDeptTrees(top.getDepartments(), "　　" + prefix ,list);
		}
	}
	
}
