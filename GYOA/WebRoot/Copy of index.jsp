<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主页测试案例</title>
    <!-- 1，引入js文件与css文件 -->
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css">
	 --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/echarts/echarts.js"></script>
	
  </head>
  
  <body>
    This is my JSP page. <br>
    <!-- 2，使用ul与li显示出数据 -->
    <!-- ol:sortList：有序列表 ul:unsortList： 无序列表: li:定义列表项目 -->
		<!-- <ul id="tree" class="filetree">整个是无序列表
			<li><span class="folder">系统管理</span>第一个li和第二个li是最外层列表的子项目
				<ul>又是子项目中的无序列表
					<li>
						<input type="checkbox">岗位管理
						<ul>
							第三层孩子的无序列表
							<li>岗位添加</li>
							<li>岗位删除</li>
						</ul>
					</li>
					<li>部门管理</li>
					<li>用户管理</li>
				</ul>
			</li>
			<li>网上交流</li>
		</ul> -->
		
		<!-- 还是先准备一个具备大小 的Dom -->
		<div id="main" style="width: 1000px;height:600px;"></div>
		<script type="text/javascript">
		//准备配置项和数据（在已经准备好的空间Dom上）
		var myChart = echarts.init(document.getElementById("main"));
		myChart.setOption({
    series : [
        {
            name: '访问来源',
            type: 'pie',
           	roseType: 'true',
            radius: '95%',
            data:[
                {value:235, name:'视频广告'},
                {value:274, name:'联盟广告'},
                {value:310, name:'邮件营销'},
                {value:335, name:'直接访问'},
                {value:400, name:'搜索引擎'}
            ]
        }
    ]
})
        
	</script>	
  </body>
</html>
