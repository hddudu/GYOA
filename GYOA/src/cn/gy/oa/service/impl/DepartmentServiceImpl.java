package cn.gy.oa.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.gy.oa.domain.Department;
import cn.gy.oa.service.DepartmentService;

@Service
//@Transactional//因为注解是可以继承的，所以如果父类中没有写的时候，父类中的方法是没有开启事务的
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{

	@Override
	public List<Department> findTopList() {
		return getSession().createQuery(
				"FROM Department d WHERE d.parent is NULL")
				.list();
	}

	
	@Override
	public List<Department> findChildrenList(Long parentId) {
		return getSession().createQuery(
				"FROM Department d WHERE d.parent.id=?")
				.setParameter(0, parentId)
//				.setParameter("id", parentId)
				.list();
	}


	/* 
	 * 查出树形列表
	 * @see cn.gy.oa.service.impl.BaseServiceImpl#findAll()
	 */
	@Override
	public List<Department> findTreeAll() {
//		List<Department> list =  getSession().createQuery("FROM " + clazz.getSimpleName()).list();
		List<Department> list =  null;
		//首先应该查出顶级部门
		List<Department> topDepartmentList = findTopList();
		//循环遍历往儿子部门前面添加特殊符号 ┣
		list = addCharForDepartment(topDepartmentList, "┣");
		return list;
	}
	
	/**
	 * 修改部门名字格式
	 * 	ArrayList的复制:
	 * 	①new一个list
	 *  ②复制原数据==>效率低
	 * @param depList
	 * @param prefix
	 */
	private List<Department> addCharForDepartment(Collection<Department> topList, String prefix) {
		List<Department> tempList = new ArrayList<>();
		tempList = (List<Department>) topList;
		for(Department top : topList) {
			//先修改
			top.setDeptName(prefix + top.getDeptName());
			//递归下一个修改名字
			List<Department> childrenList = findChildrenList(top.getId()); 
			if(childrenList != null && childrenList.size() > 0) {
				addCharForDepartment(childrenList, "  " +prefix);
			}
		}
		return (List<Department>) topList;
	}
	
	//显示一棵树的信息
	private void showTreeDept(Department dept) {
		System.out.println(dept.getDeptName());
		for(Department d : findChildrenList(dept.getId())) {
			showTreeDept(d);
		}
	}
	//设置一棵树的信息
	
	//设置多棵树的信息
	
}
