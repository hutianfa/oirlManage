<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>">
    
    <title>中石油油罐车运输管理系统</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="stylesheet" type="text/css" href="log/css/login.css" /> 	
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="iealert/style.css" />
    <link rel="stylesheet" type="text/css" href="common/css/mediaall.css">	
	<link rel="icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon"/>
	<link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.css" media="screen">  	
  </head>  
<body >
<center>
 <div class="box">
   <!-- 标题 -->
   <h1 class="text-center text-white" style="margin:0 auto;font-size:48px;padding-top:10px;">
     <span class="glyphicon glyphicon-cloud-upload"> </span>
      	中石油油罐车运输管理系统    
   </h1>
   <!-- 登录面板 -->
   <div class="login-box">
        <div class="login-title text-center">
            <h1><small>登录(Login)</small></h1>
        </div>
        <div class="login-content ">
            <div class="form">
                <form action="doLogin" method="post" class="divfor" id="formdiv">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" id="username" name="admin.admName" class="form-control" placeholder="用户名" data-toggle="tooltip" data-placement="auto bottom" title="用户名是字母开头,且不能为空！" >
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" id="password" name="admin.admPassword" class="form-control" data-toggle="tooltip" data-placement="auto bottom" title="密码不能为空！" placeholder="密码">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-question-sign"></span></span>
                                <input type="text" id="authcode" name="code" class="form-control" data-toggle="tooltip" data-placement="auto bottom" title="验证码不能为空！" placeholder="验证码">                               
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="input-group" style="height:30px;">                                
                                <img id="123" src="image.jsp" onclick="this.src='/Ltmcp/image.jsp?abc='+Math.random()" alt="图片看不清？点击重新得到验证码" style="cursor:hand;width:90px;"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-4 col-xs-offset-4 " style="margin: 0 37%;">
                            <input class="btn btn-sm btn-info " type="submit" value="登录"  id="logbtn"><span class="glyphicon glyphicon-off" style="left:-23;top:2px"></span>
                        </div>
                    </div>                   
                </form>  
                 <div class="form-group">
                 <div class="col-xs-12  ">
                     <div class="input-group" style="color:red;">                        		  			   
						   <%
			  				String msg=(String)session.getAttribute("log_msg");
			  				if(!"".equals(msg)&&null!=msg){
			  					out.print(msg);
			  					session.removeAttribute("log_msg");
			  				}
			  			    %>							
                     </div>
                 </div>
                </div>
                           
            </div>            
        </div>
    </div>
 </div>
  </center> 
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
	 var $182=$;
	</script>
	<script type="text/javascript" src="iealert.js"></script>	
	<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>	
	<script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	  	$(document).ready(function(){
			$182("body").iealert();
		});
    </script>
    <script type="text/javascript">
    <!-- 登录验证    退出登录-->
    $(function () {
		$("[data-toggle='tooltip']").tooltip(); 
			
	}); 
    $("body").css({
    	 "background-image":"url(common/img/bg-login.png)",    	
    	 "background-repeat": "no-repeat",
    	 "background-size":"cover",
    	 "-ms-behavior": "url(log/backgroundsize.min.htc)",
         behavior: "url(log/backgroundsize.min.htc)"
    });
    cover(); 
    $(window).resize(function(){ //浏览器窗口变化 
        cover(); 
    }); 
    function cover(){ 
	    var win_width = $(window).width(); 
	    var win_height = $(window).height(); 
	    $("body").attr({width:win_width,height:win_height}); 
	    $("body").css({
	    	 "background-image":"url(common/img/bg-login.png)",	    	 
	    	 "background-repeat":"no-repeat",
	    	 "background-size":"cover",
	    	 "-ms-behavior": "url(log/backgroundsize.min.htc)",
	         behavior: "url(log/backgroundsize.min.htc)"
	    });	    
	} 
  function checkusername() {
		var uname = $("#username").val();
		//var reg = "^[a-zA-Z]\w{5,17}$";
		if (uname == "" || uname == null || uname =="英文字母,数字组成") {
			$("#uuu").html("请输入用户名");
			return false;
		} else {
			$("#uuu").html("");
			return true;
		}
	}
	function checkpwd() {
		var ppwd = $("#pwd").val();
		if (ppwd == "" || ppwd == null) {
			$("#ppp").html("请输入密码");
			return false;
		} else {
			$("#ppp").html("");
			return true;
		}
	}
	function checkCode() {
		var code = $("#authcode").val();
		if (code == "" || code == null) {
			$("#ccc").html("请输入验证码");
			return false;
		} else {
			$("#ccc").html("");
			return true;
		}
	}
	function checkAll() {
		return checkusername() && checkpwd();
	}
	
	function toLoginOut() {
		var isLoginOut = window.confirm("您确定要退出");
		if (isLoginOut) {
			window.location = "/supplier/dologout";
		}
	} 
  </script>
  <script type="text/javascript" src="js/boswer.js"></script> 	 
 </body>
 
</html>
    