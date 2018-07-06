package cn.gy.oa.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * 新增实体
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 删除实体
	 * @param entity
	 */
	void delete(T entity);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 根据id获取实体
	 * @param id
	 */
	T getById(Long id);
	
	/**
	 * 根据 get()的方法参数定义接口
	 * @param t
	 * @param id
	 * @return
	 */
	T getById(Class<T> t,Serializable id);
	
	
	/**
	 * 获取所有实体
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 获取多个实体
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);
	
	
	
}
