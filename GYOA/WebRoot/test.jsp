<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'test.jsp' starting page</title>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
  <script type="text/javascript">
  /*
  	function itemClick(itemDiv) {//itemDiv是什么呢?this表示整个标签
  		//选择当前元素的直接兄弟元素(或者下一个)
  		//$(itemDiv).next().hide();
  		$(itemDiv).next().toggle();
  	}
  	*/
  	
  </script>
  </head>
  <body>
    Struts2添加成功!<br/>
    Struts2与Spring整合成功！<br/>
    
    <!-- 树状结构表现形式：
    	①ul套li
    	②div套div -->
    	<!-- 注释我们自己写的 -->
<!--    <li><div style="border:1px solid red;width:50px" onclick="itemClick(this)">岗位管理</div> -->
    <ul id="root">
    	<li>系统管理
    		<ul>
    			<li><div style="border:1px solid red;width:50px" onclick="itemClick(this)">岗位管理</div>
    				<ol>
    					<li>岗位添加</li>
    					<li>岗位删除</li>
    				</ol>
    			</li>
    			<li>部门管理</li>
    			<li>用户管理</li>
    		</ul>
    	</li>
    	<li>网上交流</li>
    </ul>
    
    
  </body>
</html>
