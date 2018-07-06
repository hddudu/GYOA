<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>js存在强依赖关系</title>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
  <script type="text/javascript">
  	//页面元素dom加载完之后执行初始化方法
  	$(function(){
  		//$("#root").tree();
  		$("#root").treeview();
  	});
  </script>
  </head>
  <body>
    <!-- 树状结构表现形式：
    	①ul套li
    	②div套div -->
   	<!-- 纯表示树框架的原生html==需要别人做好的jqueryTree来美化我们的html -->
   	<!-- .表示class的意思 -->
    <ul id="root" class="filetree">
    	<li><span class="folder">系统管理</span>
    		<ul>
    			<li><input type="checkbox"/>岗位管理
    				<ul>
    					<li>岗位添加</li>
    					<li>岗位删除</li>
    				</ul>
    			</li>
    			<li>部门管理</li>
    			<li>用户管理</li>
    		</ul>
    	</li>
    	<li>网上交流</li>
    </ul>
    
    
  </body>
</html>
