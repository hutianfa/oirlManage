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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="common/css/bundlist.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>  
    <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript">
  	  var $104=$;
  	</script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
      var $182=$;
    </script>
    <script type="text/javascript" src="js/ajaxfileupload.js"></script> 

 </head>

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
        <div class="rig-top-title" style="float:left;width:12em">
          &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 数据绑定上传管理
        </div>
      </div> 
      <div class="rig-down-all">
         <div class="updata">
            <h2>上传文件</h2>

           <!--   <form id='inputform' name='inputform' action="" method="" enctype="multipart/form-data">  -->

				<div class="uploader blue">
				    <input type="text" class="filename" readonly/>
				    <input type="button" name="file" class="button" value="上传..."/>
				    <input type="file" size="30" id="fileField" name="filedName"/>
				</div>


			<!--  </form> -->

         </div>
      </div>  
    </div>
    <!-- 右边部分完结-->
   <script type="text/javascript">
	    $(function(){
	    	var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
    	     $(document.body).append(boardDiv); 
	    	function message(text,newdata) {
	    		   var newval= newdata;
	    	       $("#spanmessage").text(text);
	    	       $104("#message").dialog({
	    	           title:"中石油油罐车运输云平台，提示您！",
	    	           modal: true,
	    	           buttons: {
	    	               "确定": function(newdata) {
	    	            	   if(newval ==0){
	    	            		   $104(this).dialog("close");
	    	            	   }else{
	    	            		   $104(this).dialog("close");  
	    	            	   }  
	    	               }
	    	           }
	    	       });
	    	   }   
	       $182("input[type=file]").live("change",function() {
	    	   if(window.navigator.userAgent.indexOf("Firefox")>=1){
	    		   $(this).parents(".uploader").find(".filename").val($(this).val());
	    	   }else{
	    		   $(this).parents(".uploader").find(".filename").val($(this).val().substring(12,$(this).val().length)); 	    	   
	    	   }
			   var extStart=$(this).val().lastIndexOf(".");
			   var ext=$(this).val().substring(extStart,$(this).val().length).toUpperCase();
			   if(ext!=".XLS"&&ext!=".TXT"&&ext!=".DOC"){
			       alert("限于xls,txt,doc格式"); //检测允许的上传文件类型
			     return false;
			   }else{
		         $182.ajaxFileUpload({
	               url: 'position_importCodeByMysql', //用于文件上传的服务器端请求地址
	               type: 'post',
	               secureuri: false, //是否需要安全协议，一般设置为false
	               fileElementId: 'fileField', //文件上传域的ID
	               dataType: 'json', //返回值类型 一般设置为json
	               success: function ()//服务器成功响应处理函数
	               {
	            	   message("上传成功！",0);  
	               },
	               error: function ()//服务器响应失败处理函数
	               {
	            	   message("上传失败！",1);
	               } 
		        });	
			 }
	       }); 
	       $182("input[type=file]").each(function(){
				if($(this).val()==""){
					$(this).parents(".uploader").find(".filename").val("请选择文件...");
				}
		   });      
	   });
	   
    /* $(function(){
		 $("input[type=file]").change(function(){
			  $(this).parents(".uploader").find(".filename").val($(this).val().substring(12,$(this).val().length));
			 //$(this).parents(".uploader").find(".filename").val($(this).val()); 
			 var filename="";
			 if(window.navigator.userAgent.indexOf("Firefox")>=1){
				   filename=$(this).parents(".uploader").find(".filename").val($(this).val());
	    	   }else{
	    		   filename=$(this).parents(".uploader").find(".filename").val($(this).val().substring(12,$(this).val().length)); 	    	   
	    	   }
			   var extStart=$(this).val().lastIndexOf(".");
			   var ext=$(this).val().substring(extStart,$(this).val().length).toUpperCase();
			   if(ext!=".XLS"&&ext!=".TXT"&&ext!=".DOC"&&ext!=".JPG"&&ext!=".JPEG"){
			     alert("限于xls,txt,doc格式"); //检测允许的上传文件类型
			     return false;
			   }else{
				$.ajax({
					type:'post',
					url:'position_importCodeByMysql',
					dataType:'json',
					async:true,
				    success:function(){
				    	alert(1);
				    },
				    error:function(){
				    	alert(0);
				    }
				});
			 }
		});
		$("input[type=file]").each(function(){
			if($(this).val()==""){
				$(this).parents(".uploader").find(".filename").val("请选择文件...");
			}
		}); 
	}); */
	</script>
	
  </div>
</div>
</center>
  </body>
</html>
