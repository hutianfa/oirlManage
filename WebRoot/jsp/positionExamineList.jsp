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
	warptab tr #more{ width:100%;height:auto;}
	.warptab tr td input,select{height: 2em; }
	.warptab tr td{width:49%;}
	.openlay{-moz-opacity: 0.7;-webkit-opacity:0.7;-ms-opacity:0.7;-o-opacity:0.7;opacity:.70;filter: alpha(opacity=70);background:#000;}
    @media screen and (min-width: 768px) and (max-width: 1260px) { 
      #name{width:100px;}
      #win{width:36%;}
     #win .content input{width:120px;}
    }
   @media screen and (min-width:1260px) and (max-width: 1600px){
     #name{width:100px;}
     #win{width:36%;}
     #win .content input{width:120px;}
   }
   @media screen and (min-width:1600px) and (max-width: 1920px) { 
     #name{width:140px;}
     #win{width:28%;}
   }
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
					&nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 站点审核信息
				</div>
			</div>
	    <center>
		  <div class="rig-down-all">
			<div class="table-title" style="line-height:4em;">
				<div style="width:65%;float:left;text-align:left;*margin-top:12px;vertical-align:middle;">
					<span>站点名称：<input type="text" name="posName" value="${condition.name}" id="name" /></span> 
					<span>状态：
					     <select name="condition.state" id="state">
			               <option value="" ></option>
			               <option value="0" ${condition.state==0?"selected":""}>已审核</option>
			               <option value="1" ${condition.state==1?"selected":""}>未审核</option>
			               <option value="2" ${condition.state==2?"selected":""}>无效信息</option>
			             </select>
					</span>
				</div>
				<div style="width:31%;float:right;text-align:left;*margin-top:13px;vertical-align:middle;">
					<span><input type="button" value="查询" id="searchbtn"class="search" style="height:1.8em;"
						onclick="javascript:searchPosition()" /></span>		   
              		<s:iterator value="#session.ADMIN_POWER">
              	      <s:if test="poUrl=='positionExamineUser_add'"> 
                       <span><input type="button" value="添加" id="addbtn" class="search" style="height:1.8em;" /></span>
				      </s:if>
				    </s:iterator>
				    <span></span>
				</div>
			</div>
			<div class="table-conment">
			  <div style="height:50em;width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
				<table class="bordered">
				   <thead>
						<tr class="top-bordered-title" style="height:30px;">
						    <th>账号</th>
							<th>站点名称</th>
							<th>经度</th>
							<th>纬度</th>
							<th>时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list">
							<tr style="height:30px;">
							    <td><s:property value="positionExamineUser.positionAccount" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="longitude" /></td>
								<td><s:property value="latitude" /></td>
								<td><s:property value="time" /></td>
								<td><s:if test="state==0">已审核</s:if><s:elseif test="state==1">未审核</s:elseif><s:else>无效信息</s:else></td>
								<td><a href="positionExamine_detail?id=${id}" style="color:blue;cursor:pointer;display:block;">详细</a></td>
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
				var Name = document.getElementById("name").value;
				var state = document.getElementById("state").value;
				var jumpurl="positionExamine_list?condition.name="+ encodeURI(Name)+"&condition.state="+state+ "&pageBean.curentPage=";
				function nextJump(){commnextJump(jumpurl);}
				function preJump(){commpreJump(jumpurl);}
				function jumpPage(){commjumpPage(jumpurl);}      
				function searchPosition() {
					var Name = document.getElementById("name").value;
					var state = document.getElementById("state").value;
					var url="positionExamine_list?condition.name="+encodeURI(Name)+"&condition.state="+state+"";
					commSearch(url);
				}
			</script>
			<!-- 分页结束 -->
			</div>
			</div>
		 </center>
		</div>
		<!-- 右边部分完结-->
		<!-- 添加审核人员 -->
		<div class="openlay"></div>
        <div id="win" class="win" >
           <div class="title">
                <div class="title_left">
                   <div class="laytitle" style="text-align:left;padding-left:10px;">添加位置采集员信息</div>
                </div>
                <div class="title_right"><span id="close">×</span></div>
            </div>
            <div class="content">
             <form id="addformper" action="" method="post" class="tabfrom-ys">
              <table class="bordered warptab">
                 <tr style="height:30px;">
                    <td>账号：<input id="user" type="text" autocomplete="off" value="" name="user.positionAccount" /><input style="display:none" /><span id="usertishi" style="color:red;"></span></td>
                    <td>密码：<input id="pwd" type="password" autocomplete="off" value="" name="user.positionPassword" /><input style="display:none" /><span id="pwdtishi" style="color:red;"></span></td>
                 </tr>
                 <tr style="height:30px;">
                    <td>姓名：<input id="turename" type="text" value="" name="user.positionName"/><span id="turenametishi" style="color:red"></span></td>
                    <td></td>
                 </tr>
              </table>
              <!-- 位置处理 -->
              <div class="btndiv" style="margin-top:1em;">
                <span>
                  <input type="button" value="确定" id="sureaddbtn" class="search" style="height:2em;margin-right:2em;" />
                  <input type="button" value="取消" id="cancel" class="search" style="height:2em;"/></span> <br/>
                  <span id="alltishi" style="color:red"></span>
              </div>
           </form> 
            </div>
        </div>
        <script type="text/javascript">
         $("#addbtn").click(function(){ 
  		    $(".openlay").css({height:$(window).height(),'min-width':'1160px'});
			$("#win").css("display","block");  
 			$(".openlay").css("display","block");
  	    });
  	  $("#close").click(function(){
  		  $(".openlay").css("display","none");
  		  $("#win").css("display","none");
  		}); 
	   $(function(){
		  if(navigator.userAgent.indexOf("MSIE") >=0 ){
 			    $("#win").css( { _position : 'absolute','_top' : '30%' , '_left' : '40%','min-width':'460px'}); 
 		  }else{
 				var top = ($(window).height() - $("#win").height())/2;   
 				var left = ($(window).width() - $("#win").width())/2; 
 				$("#win").css( { position : 'absolute','top' : top , left : left,'min-width':'460px'});
 		  }
	  	 function message(text,newdata) {
	  		   var newval= newdata;
	  	       $("#spanmessage").text(text);
	  	       $("#message").dialog({
	  	           title:"物流运输云平台，提示您！",
	  	           modal: true,
	  	           buttons: {
	  	               "确定": function(newdata) {
	  	            	   if(newval ==0){
	  	            		   $(this).dialog("close");
	   	            		   location.href="positionExamineUser_list"; 
	  	            	   }else{
	  	            		   $(this).dialog("close");  
	  	            		   $("#win").css("display","block");
	  	            		   $(".openlay").css("display","block");
	  	            	   }  
	  	               }
	  	           }
	  	       });
	  	   }
	  	    var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
	  	        $(document.body).append(boardDiv); 
	  	       
	          $("#sureaddbtn").click(function(){
	  	    	var name = $("#user").val();
	  	    	var pwd = $("#pwd").val();
	  	    	var truename = $("#turename").val();
	  	     	if(name == "" || name == null || pwd == "" || pwd == null){
	  	     		$("#alltishi").html("(用户名，密码都不能为空！)");
	  	     	}
	  	     	else if(truename =="" || truename == null){
	  	     		$("#alltishi").html("真实姓名不能为空！");
	  	     	}
	  	     	else{
	  	     	      $(".openlay").css("display","none");
	  	 		      $("#win").css("display","none");
	  	 		      $.post("positionExamineUser_add", {
	  	 		    	                    "user.positionAccount":name,
	  	 		    	                    "user.positionPassword":pwd,
	  	 		    	                    "user.positionName":truename
	  						 		       },function(data){  
	  						 	    	       if(data==0){
	  								   				message("添加成功！",data);
	  								   				$("#alltishi").html("");
	  								   			 }else{
	  								   				message("添加失败！",data);
	  								   				$("#alltishi").html("");
	  								   			 } 
	   	                                }); 
	  	     	   }
	  	     }); 
	     	 
	  	  }); 
	  	   $("#cancel").click(function(){
	  		   $(".openlay").css("display","none");
	  		   $("#win").css("display","none");  
	  	   }); 
        </script>
		</div>
	</div>
</center>
</body>
</html>
