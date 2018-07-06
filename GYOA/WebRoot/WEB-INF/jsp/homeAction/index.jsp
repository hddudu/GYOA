<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
<html>
<head>
<title>HONGDUOA</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
</head>
<!-- 
noresize 属性规定用户无法调整框架的大小。

默认地，可以通过拖动框架之间的“墙壁”来改变框架的大小，该属性可以锁定框架的大小
 -->
<frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
    <frame src="${pageContext.request.contextPath}/homeAction_top.action" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="${pageContext.request.contextPath}/homeAction_left.action" scrolling="yes" />
        <frame noresize name="right" src="${pageContext.request.contextPath}/homeAction_right.action" scrolling="yes" />
    </frameset>
    <frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/homeAction_bottom.action" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
