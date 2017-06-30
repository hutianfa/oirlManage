<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>物流运输管理云平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="jsp/404/404.css" rel="stylesheet" type="text/css">
<style type="text/css">
.img404 {
	background: url(jsp/404/p1.jpg) center center no-repeat;
	width: 745px;
	height: 258px;
	margin: 50px auto;
	position: relative;
}

.back {
	position: absolute;
	bottom: 0px;
	right: 180px;
}

.imgCenter {
	margin: 0 auto;
	width: 970px;
	text-align: center;
}

.imgCenterborder {
	border: 1px #eeeeee solid;
	padding: 6px;
}

a:link {
	text-decoration: none;
	color: #000;
}

a:visited {
	text-decoration: none;
	color: #000;
}

a:hover {
	text-decoration: none;
	color: #F90;
}

a:active {
	text-decoration: none;
	color: #000;
}
</style>
</head>

<body>
	<!-- img -->
	<div class="img404">
		<a href="login" class="back"><img
			src="jsp/404/back.jpg" width="177" height="104" border="0" alt=""></a>
	</div>

	<div id="Copyright">
		<p>
			CopyRight&#169;2004年-2010年 <strong>yelangsem.com NULL 天润信息部</strong> All
			Rights Reserved<br> 备案编号:京ICP证030173号
		</p>
		<p></p>
	</div>


</body>
</html>
