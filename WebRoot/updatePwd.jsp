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
    <meta http-equiv="X-UA-Compatible" content="IE=edg"/>
	<link rel="stylesheet" type="text/css" href="common/css/index.css" />
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css" />
	<link rel="stylesheet" type="text/css" href="log/css/updatepwd.css" /> 
    <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <style type="text/css">
      .box{float:left;padding:5px;}   .input-group{padding:20px 20px 10px 20px;}
      .input-group-addon{width:auto;display:inline;*padding:4px 12px;_padding:4px 12px;}   
    </style>
</head>
<body>
	<div id="wrapper">
		<!--顶部标题-->
		<jsp:include page="header.jsp" />
			<!-- 左侧树形菜单栏-->
			<jsp:include page="/jsp/common1.jsp" />
			<!-- 右侧内容-->
			 <div id="page-wrapper">
		       <div id="page-inner" style="height:69em;">
			     <div class="panel panel-info" style="margin-bottom:0px;">
				   <div class="panel-heading" style="text-align:left;">
				      <h3 class="panel-title">
				       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;修改密码		       	
				      </h3>		     
				   </div>							   
				 </div>
				 <div style="clear:both;"></div>
				 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
				   <div class="panel-body" style="height:50em;">
				      <form action="" method="post" class="formpwd" id="formdiv" style="position:relative;top:20%;margin:0 auto;">
	                    <div class="form-group">
	                        <div class="col-xs-12  ">
	                            <div class="input-group">
	                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
	                                <input type="password" name="pwd" id="pwd" class="form-control" placeholder="原始密码" data-toggle="tooltip" data-placement="auto bottom" title="原始密码不能为空！" >
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-xs-12  ">
	                            <div class="input-group">
	                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
	                                <input type="password" name="newPwd" id="newPwd" class="form-control" data-toggle="tooltip" data-placement="auto bottom" title="新密码不能为空！" placeholder="新密码">
	                            </div>
	                        </div>
	                    </div>
	                   <div class="form-group">
	                        <div class="col-xs-12  ">
	                            <div class="input-group">
	                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
	                                <input type="password" name="confirmPwd" id="confirmPwd" class="form-control" data-toggle="tooltip" data-placement="auto bottom" title="确认密码不能为空！" placeholder="确认密码">
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group form-actions">
	                        <div class="col-xs-4 col-xs-offset-4 " style="margin: 0 37%;padding:10px;">
	                            <input class="btn btn-sm btn-info " type="submit" value="提交" id="surebtn" name="surebtn" />
	                        </div>
	                    </div> 
	                   <div class="form-group">
	                        <div class="col-xs-12  ">
	                            <div class="input-group">
	                                <span class="errortilp" style="color:red;text-align:center;"></span>
	                            </div>
	                        </div>
	                    </div>                 
	                </form> 
				   </div>
				 </div>
			</div>
		   </div>
		</div>					
	<script type="text/javascript">  
	 $("#surepwd").blur(function(){
		var newpwd=$("#newpwd").val();
		var surepwd=$("#surepwd").val();
		if(newpwd != surepwd){
			$(".errortilp").html("新密码两次输入不一致！");
		}
	 });
	$(function(){
   	 function message(text,newdata) {
   		   var newval= newdata;
   	       $("#spanmessage").text(text);
   	       $("#message").dialog({
   	           title:"中石油油罐车运输云平台，提示您！",
   	           modal: true,
   	           buttons: {
   	               "确定": function(newdata) {
   	            	   if(newval ==0){
   	            		   $(this).dialog("close");
   	            		   location.href="login";
   	            	   }else{
   	            		   $(this).dialog("close");  
   	            	   }  
   	               }
   	           }
   	       });
   	   }
      	 var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
    	     $(document.body).append(boardDiv);        
    	    $("#surebtn").click(function(){  
    	    	   var oldpwd=$("#pwd").val();
    			   var newpwd=$("#newPwd").val();
    			   var surepwd=$("#confirmPwd").val();
    	  		  if(oldpwd =="" ||oldpwd == null || newpwd =="" ||
    	  				  newpwd ==null || surepwd =="" || surepwd ==null){
    	  			  $(".errortilp").html("密码不能为空！");
    	  		   }
    	  		   else if(newpwd == oldpwd ){
    	  			  $(".errortilp").html("新密码不能和旧密码一致！");
    	  		   }
    	  		   else if(newpwd != surepwd){
    	  			  $(".errortilp").html("新密码两次输入不一致！");
    	  		   }
    	  		   else{
    			   		$.post("updatePwd",{"pwd":oldpwd,"newPwd":newpwd,"confirmPwd":surepwd},
    			   				function(data){  
   			        	    	   if(data==0){
   						   				message("修改成功！",data);
   						   			    $(".errortilp").html("");
   						   			 }else{
   						   				message("修改失败！",data);
   						   			    $(".errortilp").html("");
   						   			 } 
    			        	 }); 
    	  		    }
    	  	     });
	});
	</script>
</body>
</html> 
