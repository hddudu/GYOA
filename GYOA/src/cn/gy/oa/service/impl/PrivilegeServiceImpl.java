package cn.gy.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.gy.oa.domain.Privilege;
import cn.gy.oa.service.PrivilegeService;

@Service//交给spriing进行管理==>进行实例化
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege> implements
		PrivilegeService<Privilege> {

	@Override
	public List<Privilege> findTopAll() {
//		return getSession().createQuery("FROM Privilege WHERE parentId is NULL").list();
		return getSession().createQuery("FROM Privilege p WHERE p.parent is NULL order by id").list();
	}

	@Override
	public List<Privilege> getSecondAll(Long id) {
		return getSession().createQuery("FROM Privilege p WHERE p.parent.id = ?")
				.setParameter(1, id)
				.list();
	}

	@Override
	public List<String> getAllPrivilegeUrls() {//查询所有权限的url集合: 不要空值和null
		return getSession().createQuery("SELECT DISTINCT p.privUrl FROM Privilege p WHERE p.privUrl IS NOT NULL ").list();
	}

}
