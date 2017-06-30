<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>中石油油罐车运输管理系统</title>
    <base href="<%=basePath%>"> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="waybill/css/waybillmanage.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="css/lightbox.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.slideBox.css" />
  	<script type="text/javascript" src="js/jquery.min.js"></script>  
    <style type="text/css">     
      .del input{border: 1px solid #364f86;border-radius: 3px;height: 1.5em;outline: medium none;
		    transition: all 0.3s ease-in-out 0s;margin-top:25px;}      
      .imgdiv{cursor: pointer;display: inline-block;outline: medium none;
		    text-align: center;text-decoration: none; max-width:150px;height:144px;} 
	  .imgdiv img{border: 1px solid rgb(204, 204, 204);} #allmap{height:400px;width:100%;}#r-result{width:100%; font-size:14px;}
	  .anchorBL{display:none;}#allmapend{height:400px;width:100%;}#r-result-end{width:100%; font-size:14px;}
	  .slideBox,ul.items li,div.slideBox ul.items li a img{width:320px;}  
    </style> 
  </head>  
<body>
  <center>
  <div id="wrapper">
   <!--顶部标题-->
    <jsp:include page="/header.jsp"/>
   <!-- 左侧树形菜单栏-->
     <jsp:include page="common1.jsp" />
    <!-- 右侧内容-->
    <div id="page-wrapper">
        <div id="page-inner">
	     <div class="panel panel-info" >
		   <div class="panel-heading" style="text-align:left;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;活动封签记录/详细记录		       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
      <!-- 运单详情页 -->
      <center>
      <div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 5px;">
		      <h3 class="panel-title" style="text-align:left;"><small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;车辆信息</h3>
		   </div>
		   <div class="panel-body state-overview">
		   	<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>车牌号：<label>${sealed.car.carFlapper}</label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>所属公司：<label><s:property value="sealed.company.comName"/> </label></span>
                   </div>
                 </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <section class="panel" style="background-color:rgba(240,240,240,1);">       
                     <div class="value">
                        <span>签封口数：<label><s:property value="sealed.car.CarUnFixFlag"/> </label></span>
                     </div>
                  </section>		
				</div><!--/.col-->																
			</div>						    
		   </div>						   
		</div>	
         <div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 5px;">
		      <h3 class="panel-title" style="text-align:left;"><small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;施封信息</h3>
		   </div>
		   <div class="panel-body state-overview">
		   	<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>施封人：<label><s:property value="sealed.person.perTrueName"/></label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>施封时间：<label><s:property value="sealed.seaTime"/> </label></span>
                   </div>
                 </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <section class="panel" style="background-color:rgba(240,240,240,1);">       
                     <div class="value">
                        <span>施封内码:<label><s:property value="sealed.dimensionalBarCode.freeze_content"/> </label></span>
                     </div>
                  </section>		
				</div><!--/.col-->																
			</div>
			<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>施封点:<label><s:property value="sealed.position.posName"/></label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>客户类型:<s:if test="sealed.tag==70">自有加油站</s:if>
                         <s:if test="sealed.tag==71">外购客户</s:if></span>
                   </div>
                 </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <section class="panel" style="background-color:rgba(240,240,240,1);">       
                     <div class="value">
                        <span>客户类型:<s:if test="sealed.tag==70">自有加油站</s:if>
                         <s:if test="sealed.tag==71">外购客户</s:if></span>
                     </div>
                  </section>		
				</div><!--/.col-->																
			</div>	
			<label class="wayid" id="<s:property value="sealed.seaNumber" />" style="display:none;"><s:property value="sealed.seaNumber"/> </label>									    
		    <div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">
                    <div class="imgdiv" style="position:relative;">
	                 <div >
	                  <a class="example-image-link" href="${sealed.seaPhoto}"  data-lightbox="example-1" style="width:100%;height:190px;">
	                   <img  class="bigpic " id="seaphone" src="${sealed.seaPhoto}" onerror="this.src='common/img/error.jpg'" width="100%" height="190" alt="暂未上传照片"/></a>
	                 </div>
	                 <div style="width: 100%;height: 30px;line-height:30px;background-color:#000;overflow: hidden;position:absolute;opacity: 0.5;"><p style="padding-bottom: 2px;color:#fff;"> 施封图片</p></div>
	                 <!-- <div style="display:none;" id="winSelector"></div> -->
	              </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">
                   <div id="my52player" class="seaplayer" style="background-color: #fff;width:22%; height: 220px;float:left;margin:0px 25px 0px 25px;position:relative;"> 
					  <div style="">      
					  <a href="${sealed.seaImg}" download="${sealed.seaImg}" id="seaimgid" >
					  <img src="common/img/file-video-icon.png" width="100%" height="220"/></a>
					  </div>
					  <div style="">
						  <span style="bottom: 0px;text-align:center;width: 100%;height: 30px;line-height:30px;background-color:#000;overflow: hidden;position:absolute;opacity: 0.5;">
						    <strong style="color:#fff;font-size:16px;">下载观看</strong>
						  </span>
					  </div>
					</div>
                 </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <section class="panel" style="background-color:rgba(240,240,240,1);">       
                     <!--滚动轮播图  -->
					 <div id="demo3" class="slideBox" style="max-width:36%;height:220px;">
						  <ul class="items">						  						    
						  </ul>
					 </div> 
                  </section>		
				</div><!--/.col-->																
			</div>	
		   </div>						   
		</div>
		 <s:if test="sealed.seaStatus==0"> <!-- 判断为已完成情况 --> 	          
          <div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 5px;">
		      <h3 class="panel-title" style="text-align:left;"><small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;解封信息</h3>
		   </div>
		   <div class="panel-body state-overview">
		   	<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>解封人：<label><s:property value="sealed.freeze.person.perTrueName"/></label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>解封时间：<label><s:property value="sealed.freeze.freTime"/></label></span>
                   </div>
                 </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <section class="panel" style="background-color:rgba(240,240,240,1);">       
                     <div class="value">
                        <span>解封点：<label><s:property value="sealed.freeze.position.posName"/></label></span>
                     </div>
                  </section>		
				</div><!--/.col-->																
			</div>
			<div class="row">				
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>解封外码：<label><s:property value="sealed.freeze.dimensionalBarCode.unfreeze_content"/> </label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>运输时长：<%
		             		DecimalFormat df = new DecimalFormat("0.00");
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					        if("0".equals(request.getAttribute("sealed.seaStatus").toString()) || "2".equals(request.getAttribute("sealed.seaStatus").toString())){
					    	 out.print(df.format((sdf.parse(request.getAttribute("sealed.freeze.freTime").toString()).getTime() - sdf.parse(request.getAttribute("sealed.seaTime").toString()).getTime())/(1000*60*60.0))+"小时"); 
					        }else{
					        	out.print("");
					        }
					    %></span>
                   </div>
                 </section>		
				</div><!--/.col-->
				<label class="wayfreid" id="<s:property value="sealed.freeze.freNumber" />" style="display:none;"><s:property value="sealed.freeze.freNumber" /></label>																								
			</div>
			<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">										
				 <div class="imgdiv" style="position:relative;">
	                 <div><a class="example-image-link" href="${freeze.frePhoto}" data-lightbox="example-2" style="width:100%;height:190px;">
	                 <img class="bigpic example-image" id="freephone" src="${freeze.frePhoto}" onerror="this.src='common/img/error.jpg'" width="100%" height="190" alt="暂未上传照片"/></a>
	                 </div>
	                 <div style="width: 100%;height: 30px;line-height:30px;background-color:#000;overflow: hidden;position:absolute;opacity: 0.5;"><p style="padding-bottom: 2px;color:#fff;"> 解封图片</p></div>
	                 <!-- <div style="display:none;" id="winSelector"></div> -->
	              </div>
	              </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">
				   <div id="my52player" class="freplayer" style="background-color: #fff;width:22%; height: 220px;float:left;margin:0px 25px 0px 25px;position:relative;"> 
					  <div style="">
					  <a href="${freeze.freImg}" download="${freeze.freImg}"  id="freimgid">
					  <img src="common/img/file-video-icon.png" width="100%" height="220"/></a>
					  </div>
					  <div style="">
						  <span style="bottom: 0px;text-align:center;width: 100%;line-height:30px;height: 30px;background-color:#000;overflow: hidden;position:absolute;opacity: 0.5;">
						    <strong style="color:#fff;font-size:16px;">下载观看</strong>
						  </span>
					  </div>
					</div>
					</section>	
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <!--滚动轮播图  -->
				  <section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">
				  <div id="demo2" class="slideBox demo2" style="max-width:36%;height:220px;">
					  <ul class="items">						  						    
					  </ul>
				  </div>
				  </section>		
				</div><!--/.col-->																
			</div>			    
		   </div>						   
		</div>	
		</s:if>
		 <s:if test="sealed.seaStatus==2"> <!-- 判断为发生异常 --> 	          
          <div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 5px;">
		      <h3 class="panel-title" style="text-align:left;"><small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;异常解封信息</h3>
		   </div>
		   <div class="panel-body state-overview">
		   	<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>异常类型：<label><s:property value="basDict.dictValue"/></label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>异常状态：<label>${excStatus==0?"已处理":"未处理"}</label></span>
                   </div>
                 </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <section class="panel" style="background-color:rgba(240,240,240,1);">       
                     <div class="value">
                        <span>运输时长：<%
					             		DecimalFormat df = new DecimalFormat("0.00");
								        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
								        if("0".equals(request.getAttribute("sealed.seaStatus").toString()) || "2".equals(request.getAttribute("sealed.seaStatus").toString())){
								    	 out.print(df.format((sdf.parse(request.getAttribute("sealed.freeze.freTime").toString()).getTime() - sdf.parse(request.getAttribute("sealed.seaTime").toString()).getTime())/(1000*60*60.0))+"小时"); 
								        }else{
								        	out.print("");
								        }
					    			%></span>
                     </div>
                  </section>		
				</div><!--/.col-->																
			</div>
			<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>解封人：<label><s:property value="sealed.freeze.person.perTrueName"/> </label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>解封点：<label><s:property value="sealed.freeze.position.posName"/> </label></span>
                   </div>
                 </section>		
				</div><!--/.col-->
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>完成状态：<label>${sealed.seaStatus==0?"完成":sealed.seaStatus==1?"运输中":"异常"}</label></span>
                   </div>
                 </section>		
				</div><!--/.col-->												
			</div>
			<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				<section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">									
				 <div class="imgdiv" style="position:relative;">
	                 <div><a class="example-image-link" href="${freeze.frePhoto}" data-lightbox="example-3" style="width:100%;height:190px;">
	                 <img class="bigpic example-image" id="freephone" src="${freeze.frePhoto}" onerror="this.src='common/img/error.jpg'" width="100%" height="190" alt="暂未上传照片"/></a>
	                 </div>
	                 <div style="width: 100%;height: 30px;line-height:30px;background-color:#000;overflow: hidden;position:absolute;opacity: 0.5;"><p style="padding-bottom: 2px;color:#fff;"> 解封图片</p></div>
	                 <!-- <div style="display:none;" id="winSelector"></div> -->
	              </div>
	              </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">
				  <div id="my52player" class="freplayer" style="background-color: #fff;width:22%; height: 220px;float:left;margin:0px 25px 0px 25px;position:relative;"> 
					  <div style="">
					  <a href="${freeze.freImg}" download="${freeze.freImg}"  id="freimgid">
					  <img src="common/img/file-video-icon.png" width="100%" height="220"/></a>
					  </div>
					  <div style="">
						  <span style="bottom: 0px;text-align:center;width: 100%;line-height:30px;height: 30px;background-color:#000;overflow: hidden;position:absolute;opacity: 0.5;">
						    <strong style="color:#fff;font-size:16px;">下载观看</strong>
						  </span>
					  </div>
					</div>	
				  </section>
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <!--滚动轮播图  -->
				  <section class="panel" style="background-color:rgba(240,240,240,1);height:220px;">
				  <div id="demo4" class="slideBox demo2" style="max-width:36%;height:220px;">
					  <ul class="items">						  						    
					  </ul>
				  </div>
				  </section>		
				</div><!--/.col-->																
			</div>			    
		   </div>						   
		</div>	
		</s:if>
		<div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 5px;">
		      <h3 class="panel-title" style="text-align:left;"><small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;授权信息</h3>
		   </div>
		   <div class="panel-body state-overview">
		   	<div class="row">				
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="value" >
                       <span>授权人：<label><s:property value="sealed.freeze.powCodName"/></label></span>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="value">
                      <span>授权原因：<label> <s:if test="sealed.freeze.powerTips==72">误操作</s:if>
	                     					   <s:if test="sealed.freeze.powerTips==73">操作不合格</s:if></label></span>
                   </div>
                 </section>		
				</div><!--/.col-->																										
			</div>						    
		   </div>						   
		</div>	
      </div>
     <!-- 三个状态结束 -->     
     </div>
    <!-- 调用经纬度定位 -->   
      <script type="text/javascript">
	    var waynums=$(".wayid").attr("id");
		if(waynums!="" || waynums !=null){
	    	 $("#demo3 ul.items").load("mobile/comm_wayNumPic?wayNum="+waynums,function(data){
	            var datas=eval(data);
	            $("#demo3 ul.items").empty();
	            if(datas != "" || datas.length !=0){
		            $.each(datas,function(i,item){ 	          	
			            var htmlpics="<li><a class='example-image-link' href='"+datas[i].picpath+"' data-lightbox='example-ul1' ><img  class='example-image' src='"+datas[i].picpath+"' width='100%' height='220'></a></li>";
			            $("#demo3 ul.items").append(htmlpics);
		            });
	            }
	    	}); 
		} 
	/* 图片轮播 */
	setTimeout(function(){
		var lichild=$("#demo3 ul.items li").length;
		if(lichild > 0){
    		$('#demo3').slideBox({
				duration : 0.3,//滚动持续时间，单位：秒
				easing : 'linear',//swing,linear//滚动特效
				delay : 5,//滚动延迟时间，单位：秒
				hideClickBar : false,//不自动隐藏点选按键
				clickBarRadius : 10
			});
		}
	},1000); 
	setTimeout(function(){
    	var waynumf=$(".wayfreid").attr("id");
    	if(waynumf!="" || waynumf !=null){
	    	$(".demo2 ul.items").load("mobile/comm_wayNumPic?wayNum="+waynumf,function(data){
	            var dataf=eval(data);
	            $(".demo2 ul.items").empty();
	            if(dataf !="" || dataf.length !=0){				            
		            $.each(dataf,function(i,item){ 
		            	var htmlpicf="<li><a class='example-image-link' href='"+dataf[i].picpath+"' data-lightbox='example-ul2'><img class='example-image' src='"+dataf[i].picpath+"' width='100%' height='220'></a></li>";
		                $(".demo2 ul.items").append(htmlpicf);
		            });
	            }
	    	});
    	}		    	
	},500);
	setTimeout(function(){
		var lichild2=$(".demo2 ul.items li").length;
		if(lichild2 > 0){
    		$('.demo2').slideBox({  
				duration : 0.3,//滚动持续时间，单位：秒
				easing : 'linear',//swing,linear//滚动特效
				delay : 5,//滚动延迟时间，单位：秒
				hideClickBar : false,//不自动隐藏点选按键
				clickBarRadius : 10
			}); 
		}
	},1500);
	var defhref=$("#seaimgid").attr("href");
	var frehref=$("#freimgid").attr("href");
	var exchref=$("#excimgid").attr("href");
	if(defhref==""){$(".seaplayer").hide();}
	if(frehref ==""){$(".freplayer").hide();}
	if(exchref ==""){$(".excplayer").hide();}
    </script>
   <!-- 右边部分完结-->
</div>
    <script type="text/javascript" src="js/jquery.slideBox.js"></script>
    <script type="text/javascript" src="js/lightbox.js"></script>
</center>
</body>
</html>
