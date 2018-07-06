package cn.gy.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 仅仅是转发等功能
 * @author dudu
 *
 */
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport{
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String index() throws Exception {
		return "index";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String top() throws Exception {
		return "top";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bottom() throws Exception {
		return "bottom";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String left() throws Exception {
		return "left";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String right() throws Exception {
		return "right";
	}
}
