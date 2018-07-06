<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
<html>
<head>
<title>导航菜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/blue/menu.css" />
<script type="text/javascript">
	function menuClick(menuDiv) {
		//$(".MenuLevel2").not($(menuDiv).next()).hide();//排除自己本身: 适合很多菜单的情况
		$(menuDiv).next().toggle();
	}
	
</script>

</head>
<body style="margin: 0">
<div id="Menu">
<%--<s:property value="%{#session.user}"/>cn.gy.oa.domain.User@2c59be0b --%>

    <ul id="MenuUl">
    	<%--顶级菜单 --%>
    	<%-- 程序启动时，使用监听器保存菜单数据: 监听最大的作用域 application--%>
    	<s:iterator value="#application.topPrivilegeList">
    	<%-- <s:property value="%{#session.user.hasPrivilegeByName(privName)}"/> --%>
    		<s:if test="#session.user.hasPrivilegeByName(privName)">
	        <li class="level1">
	            <div onClick="menuClick(this);" class="level1Style">
	            	<img src="${pageContext.request.contextPath }/style/images/MenuIcon/${privIcon}" class="Icon" />
	            	 <!-- 系统管理 -->
	            	 ${privName }
	            </div>
	            <ul style="display:none" class="MenuLevel2">
	            	<%-- 二级菜单 --%>
	            	<s:iterator value="childrens">
	            		<s:if test="#session.user.hasPrivilegeByName(privName)"> 
		                <li class="level2">
		                    <div class="level2Style">
		                    	<img src="${pageContext.request.contextPath }/style/images/MenuIcon/menu_arrow_single.gif" /> 
		                    	<a target="right" href="${pageContext.request.contextPath }/${privUrl}.action"> 
		                    		<!-- 岗位管理 -->
		                    		${privName }
		                    	</a>
		                    </div>
		                </li>
		                </s:if>
	            	</s:iterator>
	            </ul>
	        </li>
	        </s:if>
        </s:iterator>
    </ul>
</div>

</body>
</html>
