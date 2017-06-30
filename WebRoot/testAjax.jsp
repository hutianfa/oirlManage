<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testAjax.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>

  </head>
  
  <body>
    <script type="text/javascript">
    	$(function(){
			 $.post("waybill_list_json",function(data){
				$("#t").val(data);
				var obj=$.parseJSON(data);
				var curentPage=obj["curentPage"];
				var pageIsEnd=obj["pageIsEnd"];
				var pageIsFirst=obj["pageIsFirst"];
				var pageSize=obj["pageSize"];
				var totalCount=obj["totalCount"];
				var totalPage=obj["totalPage"];
				
				var list=obj["list"];
				$.each(list,function(i){
					var seaId=list[i]["seaId"];
					var seaLatitude=list[i]["seaLatitude"];
					var seaLockCode=list[i]["seaLockCode"];
					var seaLongitude=list[i]["seaLongitude"];
					var seaPhoto=list[i]["seaPhoto"];
					var seaStatus=list[i]["seaStatus"];
					var time=new Date(list[i]["seaTime"]["year"],list[i]["seaTime"]["month"],list[i]["seaTime"]["day"],list[i]["seaTime"]["hours"],list[i]["seaTime"]["minutes"]);
					var seaTime=(time.getUTCFullYea)+"年"+(time.getMonth()+1)+"月"+time.getDay()+"日"+"  "+(time.getHours()+1)+"时"+(time.getMinutes()+1)+"分";
					alert("seaId:"+seaId+",seaLatitude"+seaLatitude+",seaLockCode"+seaLockCode+",seaLongitude"+seaLongitude+",seaPhoto"+seaPhoto+",seaStatus"+seaStatus+",seaTime"+seaTime);
				});
			 });		
    	});
    </script>
    <textarea rows="50" cols="200" id="t"></textarea>
  <body>
</html>
