<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>中石油油罐车运输管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="waybill/css/waybillmanage.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/mediaall.css"/>
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
  </head>
	<style type="text/css">
		warptab tr #more{width:100%;height:auto;}
		.warptab tr td input,select{height: 2em;}
		.warptab tr td{width:49%;}
		textarea{risize:none;width: 600px;height: 70px;max-width: 600px;max-height: 70px;}
		.openlay{-moz-opacity: 0.7;-webkit-opacity:0.7;-ms-opacity:0.7;-o-opacity:0.7;opacity:.70;filter: alpha(opacity=70);background:#000;}
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
        <div class="rig-top-title" style="float:left;*height:35px;vertical-align:middle;">
          &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 运单图片管理
        </div>
        <!--<div class="rig-top-title" style="float:right;*height:35px;width:17%;text-align:left;vertical-align:middle;">
           <span><select id="tabledata">
             <option value="1" ${condition.info==1?"selected":""}>当前数据</option>
             <option value="2" ${condition.info==2?"selected":""}>所有数据</option>
           </select></span>
           <span id="export"><input type="button" data-type="xls" value="导出" id="tableExcelbtn" class="search"/></span>
        </div>-->
      </div> 
      <center>
      <div class="rig-down-all">
         <div class="table-title" style="line-height:4em;">
          </div>
        <div class="table-conment">
         <div id="allcoment" style="height:50em;width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
              <div class="pic_conment">
              </div>
              <div class="selectpic" style="display:none;">
                 <img src="common/img/sys_item_selected.gif" />
              </div>
          </div>      
          <!-- 分页 -->
           <div class="table-bottom" >
              <div style="width:60%;float:left;">
               <span style="float:left;">共有<a id="page">0</a> 条记录</span>
             </div> 
             <div style="width::38%;float:right;">
                <span class="table-bottom-page"></span>
             </div>
            <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
            <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
           </div>
          </div>  
      </div>
      </center>
    </div>
    <!-- 右边部分完结-->
    <!-- 弹出层-->
     <div class="openlay" ></div>
        <div id="win" class="win">
           <div class="title">
                <div class="title_left">
                   <div class="laytitle" style="text-align:left;padding-left:10px;">审核意见</div>
                </div>
                <div class="title_right"><span id="close">×</span></div>
            </div>
            <div class="content">
            <form id="addformper" action="" method="post" class="tabfrom-ys">
              <table class="bordered warptab">
                 <tr style="height:30px;">
                    <td>施封审核信息选择：  <select id="rigauditid">
                              <option value="1">完整</option>
                              <option value="2">不完整</option>
                              <%-- <option value="1">其他</option>--%></select> 
                    </td>
                 </tr>
                 <tr style="height:30px;">
                    <td>施封审核信息描述：
                      <textarea rows="4" cols="90" id="rigauditarea" placeholder="ceshi"></textarea>
                    </td>
                 </tr>
                 <tr style="height:30px;">
                    <td>解封审核信息选择：  <select id="lefauditid">
                              <option value="1">完整</option>
                              <option value="2">不完整</option>
                              <!-- <option value="1">其他</option> --></select>
                    </td>
                 </tr>
                 <tr style="height:30px;">
                    <td>解封审核信息描述：
                      <textarea rows="4" cols="90" id="lefauditarea" placeholder="ceshi"></textarea>
                    </td>
                 </tr>
           </table>
           <!-- 审核意见 -->
           <div class="btndiv" style="margin-top:1em;">
                <span>
                  <input type="button" value="确定" id="sureaddbtn" class="search" style="height:2em;margin-right:2em;" />
                  <input type="button" value="取消" id="cancel" class="search" style="height:2em;"/></span> <br/>
                  <span id="alltishi" style="color:red"></span>
              </div>
           </form>
            </div>
        </div>
  </div>
</div>
</center>
<script type="text/javascript">
$(function(){
	//循环添加图片
	var pagenum;
	 $.ajax({
	    	type:"get",
	    	url:"photo_seaPhoto",
	    	cache:true,
	        dataType:"json",
	        success:function (data){
	        	var datas = eval(data);
	        	//console.log(data);
	        	if(datas.length==0){
	        		var errorhtml="<div class='pic_end'>";
		        		errorhtml+= "<span class='imgdiv' ><img src='common/img/error.jpg' border='none' id=''/>";
		        		errorhtml+= "</span>";
		        		errorhtml+= "<i></i>";
		        		errorhtml+="</div>";
	   	        	 $(".pic_conment").append(errorhtml);
	   	        	 $(".pic_end").unbind("click");
	        	}else{
	        	 $.each(datas,function(i,item){ 
	        	    var pichtml="<div class='pic_end'>";
	        	        pichtml+= "<span class='imgdiv' id='firstid'><img src='"+datas[i].SeaSrc+"' border='none' id='"+datas[i].seaId+"'/>";
	        	        pichtml+= "</span>";
	        	        pichtml+= "<span class='imgdiv' id='twoid'><img src='"+datas[i].freSrc+"' border='none' id='"+datas[i].freId+"'/>";
	        	        pichtml+= "</span>";
	        	        pichtml+="<i></i>";	       
	        	        pichtml+="</div>";
	        	 $(".pic_conment").append(pichtml);
	        	 pagenum=datas[i].pageTotal;
	        	}); 
	    	    $("#page").text(pagenum);
	           }
	        }
     });
	  
	 if(navigator.userAgent.indexOf("MSIE") >=0 ){
	    $("#win").css( { _position : 'absolute','_top' : '30%' , '_left' : '40%','min-width':'550px'} ); 
	  }else{
		var top = ($(window).height() - $("#win").height())/2;   
		var left = ($(window).width() - $("#win").width())/2; 
		$("#win").css( { position : 'absolute','top' : top , left : left,'min-width':'550px'} );	
	  }
	 function message(text,newdata){
		   var newval= newdata;
	       $("#spanmessage").text(text);
	       $104("#message").dialog({
	           title:"中石油油罐车运输云平台，提示您！",
	           modal: true,
	           buttons: {
	               "确定": function(newdata) {
	            	   if(newval ==2){
	            		   $104(this).dialog("close");
	            		   location.href="photo_photolist"; 
	            	   }else{
	            		   $104(this).dialog("close");  
	            		   $("#win").css("display","block");
	            		   $(".openlay").css("display","block");
	            	   }  
	               }
	           }
	       });
	   }
	    var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
	        $(document.body).append(boardDiv);
	    $182(".pic_end").live("click",function(){
	  		  $(this).find("i").css("display","block");
	  		  setTimeout(function () { 
	  			  $(".openlay").css({height:$(window).height(),'min-width':'1160px'});
	  	 		  $("#win").css("display","block");  
	  	 		  $(".openlay").css("display","block");
	  		    }, 500);
	  		var seaid=$(this).find("span:first").find("img").attr("id");
	  		var freid=$(this).find("span:last").find("img").attr("id");
	  		$("#sureaddbtn").click(function(){
	  			var seaStatus=$("#rigauditid option:selected").val();
	  			var freStatus=$("#lefauditid option:selected").val();
	  			var seaTip=$("#rigauditarea").val();
	  			var freTip=$("#lefauditarea").val();
	  			datas={"condition.seaTip":seaTip,"condition.seaStatus":seaStatus,"condition.freStatus":freStatus,
	  					"condition.freTip":freTip,"condition.seaId":seaid,"condition.freId":freid};
	  			$.ajax({
	  				type:"post",
	  		    	url:"photo_photoAdvice",
	  		    	cache:true,
	  		        dataType:"json",
	  		        data:datas,
	  		        success:function (data){
	  		        	if(data==2){
	  		   				message("审核成功！",data);
	  		   				$("#alltishi").html("");
	  		   			 }else{
	  		   				message("审核失败！",data);
	  		   				$("#alltishi").html("");
	  		   			 } 
	  		        	$(".openlay").css("display","none");
	  				    $("#win").css("display","none");  
	  				    $(".pic_end i").css("display","none");
	  		        }
	  			});
	  		});
	  		
	       });  	    
	});
	 $("#cancel").click(function(){
		   $(".openlay").css("display","none");
		   $("#win").css("display","none");  
		   $(".pic_end i").css("display","none");
	 });
	 $("#close").click(function(){
		   $(".openlay").css("display","none");
		   $("#win").css("display","none");  
		   $(".pic_end i").css("display","none");
	 });
</script>
  </body>
</html>
