package cn.gy.oa.service;

import java.util.List;

import cn.gy.oa.domain.Role;

public interface RoleService extends BaseService<Role>{

	
	/**
	 * 查找所有的角色
	 * @return
	 */
//	public List<Role> findAll();
//
//	/**
//	 * 根据id删除
//	 * @param id
//	 */
//	public void delete(Long id);
//
//	/**
//	 * 新增、保存对象实体
//	 * @param role
//	 */
//	public void save(Role role);
//
//	/**
//	 * 修改实体信息
//	 * @param role
//	 */
//	public void udpate(Role role);
//
//	/**
//	 * 通过查询数据
//	 * @param id
//	 * @return
//	 */
//	public Role getById(Long id);

	/**
	 * 查找未授权部分的岗位
	 * @return
	 */
	List<Role> findNotAll(Long[] ids);
}
