package cn.gy.oa.service;

import java.util.List;

import cn.gy.oa.domain.Department;

public interface DepartmentService extends BaseService<Department>{

	/**
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查询自部门列表
	 * @param parentId
	 * @return
	 */
	List<Department> findChildrenList(Long parentId);
	
	/**
	 * 查询部门列表
	 * @return
	 */
//	List<Department> findAll();
//
//	void deleteById(Long id);
//
//	void save(Department model);
//
//	Department findById(Long id);
//
//	void update(Department depart);
//	
	List<Department> findTreeAll();
}
