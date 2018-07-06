package cn.gy.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.gy.oa.domain.Forum;
import cn.gy.oa.service.ForumManageService;

@Service
@SuppressWarnings("unchecked")
public class ForumManageServiceImpl extends BaseServiceImpl<Forum> implements ForumManageService {

	@Override//上移动，最上面不能上移动
	public void moveUp(long id) {
		//获取要交换的两个forum
		Forum forum = getById(id);//当前操作的Forum
		Forum other = (Forum) getSession()
//				.createQuery("From Forum f where f.position < ? ORDER BY f.position DESC limit 1 ")
				.createQuery("From Forum f where f.position < ? ORDER BY f.position DESC ")//没有limit关键字呢
				.setParameter(0, forum.getPosition())//只能通过id来操作
				.setFirstResult(0)
				.setMaxResults(1)//代表只取第一条结果
				//.setFirstResult(0) .setMaxResults(1)相当于 limit 0,1
				.uniqueResult();//我上面的一个forum
		if(other == null) {
			return;
		}
		
		//方法一： 查询出所有的数据集合,或者集合中的数据
		//交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		//更新到数据库中
		//因为是持久化状态，所以不需要调用update方法
	}

	@Override//下移动，最下面的不能下移动
	public void moveDown(long id) {
		Forum forum = getById(id);
		Forum other = (Forum) getSession()
				.createQuery("From Forum f where f.position > ? ORDER BY f.position ASC ")
				.setParameter(0, forum.getPosition())
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
		if(other == null) {
			return;
		}
		
		//交换
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
	}

	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position ASC").list();
	}

	public void save2(Forum forum) {
		//保存到DB，会自动生成Id的值
		getSession().save(forum);
		//指定position的值为最大
		//将long型转化成int型==>参考number下的方法
		forum.setPosition(forum.getId().intValue());//SELECT MAX(f.position) from Forum f
		//保存到数据库
		//因为forum是持久化化对象,此时保存在session中,所以会自动更新到数据库中
		//因为是持久化状态,所以不需要调用update()方法
	}
	/**
	 * @param forum
	 */
	public void save(Forum forum) {
		//我的思路： 查询最大的position,然后加1
		int positionMax = (int) getSession().createQuery("SELECT MAX(f.position) position from Forum f").uniqueResult();
		forum.setPosition(positionMax + 1);
		getSession().save(forum);
	}
	
}
