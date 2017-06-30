<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	<title>中石油油罐车运输管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="positonmanage/css/auditmanage.css" />
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css" />
  	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
</head>
   <style type="text/css">
		warptab tr #more{width:100%;height:auto;}
		.warptab tr td input,select{height: 2em;}
		warptab tr td{ width:49%;}
      </style>
<body>
<center>
<div class="main">
	  <!--顶部标题-->
	 <jsp:include page="/header.jsp" />
	<div class="down">
	  <!-- 左侧树形菜单栏-->
	  <jsp:include page="common.jsp" />
		<!-- 右侧内容-->
		<div class="downrig">
			<div class="rig-top">
				<div class="rig-top-title" style="float:left;text-align:left;">
					&nbsp;&nbsp;<img src="common/img/tubiao1.jpg" />站点采集员信息
				</div>
			</div>
	    <center>
		  <div class="rig-down-all">
			<div class="table-title" style="line-height:4em;">
				<div style="width:65%;float:left;text-align:left;*margin-top:12px;vertical-align:middle;">
					<span>账户：<input type="text" name="Account" value="${condition.account}" id="name" /></span> 
				</div>
				<div style="width:31%;float:right;text-align:left;*margin-top:13px;vertical-align:middle;">
					<span><input type="button" value="查询" id="searchbtn"class="search" style="height:1.8em;"
						onclick="javascript:searchPositionExcSuc()" /></span>
				    <span></span>
				</div>
			</div>
			<div class="table-conment">
			  <div style="height:50em;width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
				<table class="bordered">
				   <thead>
						<tr class="top-bordered-title" style="height:30px;">
						    <th>账号</th>
						    <th>密码</th>
							<th>姓名</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list">
							<tr style="height:30px;">
							    <td><s:property value="positionAccount" /></td>
								<td><s:property value="positionPassword" /></td>
								<td><s:property value="positionName" /></td>
							</tr>
						</s:iterator>
					</tbody>
			  </table>
			</div>
			<!-- 分页开始 -->
			<div class="table-bottom">
				<div style="width:60%;float:left;">
					<span style="float:left;">共有<a><s:property value="pageBean.totalCount" /></a> 条记录，当前是第<a><s:property value="pageBean.curentPage" /></a>页</span>
				</div>
				<div style="width::38%;float:right;">
					<span class="table-bottom-page"> 
						<s:if test="pageBean.curentPage>1"><a onclick="javascript:preJump()">上一页 </a>&nbsp;</s:if> 
						<s:if test="pageBean.curentPage<pageBean.totalPage"><a onclick="javascript:nextJump()">下一页 </a></s:if> 
						<s:if test="pageBean.totalPage>1">&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;" id="jumpPage" /> 页&nbsp;<a href="javascript:void(0);" onclick="javascript:jumpPage()">跳转 </a>
						</s:if>
					</span>
				</div>
			</div>
			<input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
        	<input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
        	<script type="text/javascript" src="js/commSearch.js"></script>
			<script type="text/javascript">
				var Account = document.getElementById("name").value;
				var jumpurl="positionExamineUser_list?condition.account="+ Account+ "&pageBean.curentPage=";
				function nextJump(){commnextJump(jumpurl);}
				function preJump(){commpreJump(jumpurl);}
				function jumpPage(){commjumpPage(jumpurl);}  
				function searchPositionExcSuc() {
					var Account = document.getElementById("name").value;
					var url="positionExamineUser_list?condition.account="+Account+"";
					commSearch(url);						
				}
			</script>
			<!-- 分页结束 -->
			</div>
			</div>
		 </center>
		</div>
		<!-- 右边部分完结-->
		</div>
	</div>
</center>
</body>
</html>
