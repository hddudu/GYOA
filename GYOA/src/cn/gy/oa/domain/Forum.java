package cn.gy.oa.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dudu
 *
 */
public class Forum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private int position;//排序用的位置号
	//显示时按position排序
	//position的值不能重复，--》在添加时要指定一个位置号
	//要是当前最大值加1
	//上移动或者下移动就是与上面或者下面交换position的值
	
	private Long topicCount;
	private Long articleCount;
	
	//创建时间和修改时间
	private Timestamp crtDate;
	private Timestamp updDate;
		
	public Timestamp getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Timestamp crtDate) {
		this.crtDate = crtDate;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Forum(Long id, String name, String description, int position) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.position = position;
	}
	public Forum() {
		super();
	}
	public Long getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(Long topicCount) {
		this.topicCount = topicCount;
	}
	public Long getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Long articleCount) {
		this.articleCount = articleCount;
	}
}
