package cn.gy.oa.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.gy.oa.service.BaseService;

/**
 * @author dudu
 *  去掉了DAO层
 *
 * @param <T>
 */
@Transactional//添加事务管理:这个注解对所有其子类也有效果
public class BaseServiceImpl<T> implements BaseService<T> {
	@Resource//注入实例
	private SessionFactory sessionFactory;
	
	protected Class<T> clazz;//这是一个问题
	
	public BaseServiceImpl() {
		//通过反射得到真是类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];//BaseDaoImpl<T,A,E,F>
		System.out.println("真实类:"+this.clazz.getSimpleName());
	}

	@Override
	public void save(T entity) {
//		this.getSession().save(entity);
//		Session session = sessionFactory.getCurrentSession();
		getSession().save(entity);
	}

	@Override
	public void update(T entity) {
//		this.getSession().update(entity);
		getSession().update(entity);
	}

	@Override
	public void delete(T entity) {
//		this.getSession().delete(entity);
		getSession().delete(entity);
	}

	@Override
	public T getById(Long id) {
//		Session session = this.getSession();
//		String hql = "";
//		Query query = session.createQuery(hql);
//		return (T) query.uniqueResult();
		if(id == null) {
			return null;
		}
		T t = (T) getSession().get(clazz, id);
		return t;
	}

	/*
	 * @see cn.gy.oa.dao.BaseDao#findAll()
	 */
	@Override
	public List<T> findAll() {
		List<T> list =  getSession().createQuery("FROM " + clazz.getSimpleName()).list();
		return list;
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		List<T> list = null;
		/*if(ids.length > 0){
			list = new ArrayList<T>();
			for(Long id : ids){
				T t = (T) getSession().get(clazz, id);
				list.add(t);
			}
		}*/
		//如果ids为空的话 select * from oa where id in()
		if(ids == null || ids.length == 0){
			return Collections.EMPTY_LIST;//返回一个空集合
		}
		return getSession().createQuery("FROM " + clazz.getSimpleName() + " where id IN(:ids)")
			.setParameterList("ids", ids).list();
		
	}
	
	/**
	 * 获取当前可用的session对象
	 * 为了子类能用,使用protected.
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/* 
	 * 同样的获取单个实体的方法
	 * @see cn.gy.oa.dao.BaseDao#getById(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public T getById(Class<T> t, Serializable id) {
		return (T) getSession().get(t, id);
	}

	@Override
	public void delete(Long id) {
		Object obj=getById(id);
		getSession().delete(obj);
	}
}
