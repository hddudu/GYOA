package cn.gy.oa.service;

import java.util.List;

import cn.gy.oa.domain.Privilege;

public interface PrivilegeService<T> extends BaseService<T> {

	/**
	 * 查询所有顶级权限的集合
	 * @return
	 */
	List<Privilege> findTopAll();

	/**
	 * 查出所有二级孩子的集合
	 * @param id
	 * @return
	 */
	List<Privilege> getSecondAll(Long id);

	List<String> getAllPrivilegeUrls();

}
