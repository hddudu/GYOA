package cn.gy.oa.dao;

import cn.gy.oa.domain.Role;

/**
 * 角色接口
 * @author dudu
 *
 */
public interface RoleDao extends BaseDao<Role>{

	/**
	 * 根据id删除
	 * @param id
	 */
	void delete(Long id);

}
