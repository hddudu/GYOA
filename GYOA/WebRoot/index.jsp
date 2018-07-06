<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 重定向到我的主页面 -->
<%
	response.sendRedirect(request.getContextPath() + "/homeAction_index.action");
%>