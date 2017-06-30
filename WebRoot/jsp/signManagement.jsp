<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,java.text.DecimalFormat" pageEncoding="UTF-8"%>
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
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
	<link rel="stylesheet" type="text/css" href="waybill/css/waybillmanage.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/nocommsearchmedia.css"/>
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
  	  var $182=$;
  	</script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts-3d.src.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/modules/exporting.js"></script>
    <script type="text/javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script><script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript">
  	  var $104=$;
  	</script>
  	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>		
    <style type="text/css">
      .box{float:left;padding:5px;} .input-group-addon{width:auto;display:inline;}
  	  .table tr th,tr td {word-break: keep-all;font-family:"微软雅黑";}
    </style>
</head>
  <body>
  <center>
    <div id="wrapper">
   <!--顶部标题-->
    <jsp:include page="/header.jsp" />
  <!-- 左侧树形菜单栏-->
     <jsp:include page="common1.jsp" />
    <!-- 右侧内容-->
      <div id="page-wrapper">
         <div id="page-inner">
	     <div class="panel panel-info" style="margin-bottom:0px;">
		   <div class="panel-heading" style="text-align:left;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;签封入库记录	       	
		      </h3>		     
		   </div>							   
		 </div>
       <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_listing'">
        <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		   </div>	
		   <div class="panel-body">	 
			  <div class="box"><span class="input-group-addon" style="display:inline;">所属公司:</span>
              <div tabindex="0" class="searchable-select" id="coma1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder"></div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="coma">              				
              			</div>
              		  <div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div> 
              </div>
              <div class="box"> <span class="input-group-addon" style="display:inline;">所属区域：</span>
              <div tabindex="1" class="searchable-select" id="area1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder"></div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input"> 
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="addarea">
              			</div>
              		<div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div>
              </div> 
              <div class="box"><span class="input-group-addon" style="display:inline;">所属站点</span>
              <div tabindex="2" class="searchable-select" id="pos1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder"></div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="posName">              				
              			</div>
              		<div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div> 
              </div>        
              <div class="box"> 
                <span class="input-group-addon" style="display:inline;"><span >开始时间</span></span>
              	<span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="starttime" value="${condition.beginTime}"></span>
			  </div>
			  <div class="box">
			    <span class="input-group-addon" style="display:inline;"><span >结束时间</span></span>
			    <span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="endtime" value="${condition.endTime}"></span>            					               
			  </div>
			  <div class="box">        
               <span style="width:80px;right:5%;float:left;"><input type="button" value="查询" id="searchbtn" class="search btn btn-primary" onclick="searchsignList()" style="width:75px;height:28px;"/></span>
               </div>
               <div class="box"> 
               <span style="width:80px;right:5%;float:left;"><input type="button" value="领用" id="addbtn" class="search btn btn-primary" style="width:75px;height:28px;" /></span>
              </div>
            </div>
            <span><input id="locid" style="width:120px;" type="hidden" value="" /></span>
          </div>
            <div class="widget orange">
               <div class="widget-title">
                   <h4 style="margin:0 auto;line-height: 36px;"><i class="fa fa-reorder"></i> 签封入库记录</h4>                   
               </div>
               <div class="widget-body" style="overflow:auto;height:54em;">
                 <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">
	             <thead>
	              <tr>
	               <th style="">所属站点</th>
	               <th style="">签封总数</th>
	               <th style="">签封使用数</th>
	               <th style="">签封剩余数</th>
	               <th>
            	          <div style="font-size:14px;">排名</div>
	            	      <a href="javascript:void(0)" id="0" class="upbene defsty"><img src="common/img/up.png" width="20" height="25"/></a>
	            	      <a href="javascript:void(0)" id="1" class="downbene"><img src="common/img/down.png" width="20" height="25"/></a>
	              </th>
	              </tr>              
	             </thead>
             <tbody id="benefitcoment">
              
             </tbody>             
           </table>  
          </div>
          </div>
             <!-- 分页开始 -->
           <div class="table-bottom">
             <div style="width:60%;float:left;">
               <span style="float:left;">当前是第<a id="curpage">1</a>页</span>
              </div> 
             <div style="width::38%;float:right;">
                <span class="table-bottom-page">
                </span>
            </div>
          </div>           
      </s:if>
      </s:iterator>
       <!--区域  -->
      <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_queryAreaThings'">
        <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		   </div>	
		   <div class="panel-body">	             
              <div class="box"><span class="input-group-addon" style="display:inline;">所属区域</span>
              <div tabindex="1" class="searchable-select" id="area1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder"></div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input"> 
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="addarea">
              			</div>
              		<div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div>
              </div>                  
               <div class="box"> 
                <span class="input-group-addon" style="display:inline;"><span >开始时间</span></span>
              	<span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="starttime" value="${condition.beginTime}"></span>
			  </div>
			  <div class="box">
			    <span class="input-group-addon" style="display:inline;"><span >结束时间</span></span>
			    <span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="endtime" value="${condition.endTime}"></span>            					               
			  </div>
			  <div class="box">    
               <span style="width:80px;right:5%;float:left;"><input type="button" value="查询" id="searchbtn" class="search btn btn-primary" onclick="searchsignList()" style="width:75px;height:28px;"/></span>
               </div>
               <div class="box"> 
               <span style="width:80px;right:5%;float:left;"><input type="button" value="领用" id="addbtn" class="search btn btn-primary" style="width:75px;height:28px;" /></span>
              </div>
            </div>
            <span><input id="locid" style="width:120px;" type="hidden" value="" /></span>
          </div>
           <div class="widget orange">
             <div class="widget-title">
                 <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 签封入库记录</h4>                   
             </div>
             <div class="widget-body" style="overflow:auto;height:64em;">
               <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">
               <thead>
                <tr>
	               <th style="">所属区域</th>
	               <th style="">签封总数</th>
	               <th style="">领用时间</th>
	               <th style="">领用人</th>	               	               
               </tr>              
              </thead>
             <tbody id="benefitcoment">
              
             </tbody>             
           </table>  
          </div>
             <!-- 分页开始 -->
           <div class="table-bottom">
             <div style="width:60%;float:left;">
               <span style="float:left;">当前是第<a id="curpage">1</a>页</span>
              </div> 
             <div style="width::38%;float:right;">
                <span class="table-bottom-page">
                </span>
            </div>
            </div>
          </div>            
      </s:if>
      </s:iterator>      
    </div>
    <!-- 右边部分完结-->
     <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_listing'">
          <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">添加入库信息</h4>
		      </div>
		      <div class="modal-body">
	         <form id="addformper" action="" method="" class="tabfrom-ys">
	            <div class="widget orange"> 
		            <div class="widget-body" style="overflow:auto;">
		              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">
		             <tr style="height:30px;">
	                   <td>
		                  <div class="box"><span class="input-group-addon" style="display:inline;">所属公司</span>
			              <div tabindex="0" class="searchable-select" id="newcoma1">
			              	<span class="searchable-select-caret"></span>
			              	<div class="searchable-select-holder"></div>
			              	<div class="searchable-select-dropdown searchable-select-hide newdown" >
			              		<input type="text" class="searchable-select-input">
			              		<div class="searchable-scroll">
			              		  <div class="searchable-has-privious searchable-select-hide">...</div>
			              			<div class="searchable-select-items" id="newcoma">              				
			              			</div>
			              		  <div class="searchable-has-next searchable-select-hide">...</div>
			              		</div>
			              	</div>
			              </div>
			              </div>  </td>
		               	<td><div class="box"><span class="input-group-addon" style="display:inline;">所属区域</span>
			              <div tabindex="1" class="searchable-select" id="newarea1">
			              	<span class="searchable-select-caret"></span>
			              	<div class="searchable-select-holder"></div>
			              	<div class="searchable-select-dropdown searchable-select-hide newdown" >
			              		<input type="text" class="searchable-select-input"> 
			              		<div class="searchable-scroll">
			              		  <div class="searchable-has-privious searchable-select-hide">...</div>
			              			<div class="searchable-select-items" id="newaddarea">
			              			</div>
			              		<div class="searchable-has-next searchable-select-hide">...</div>
			              		</div>
			              	</div>
			              </div> 
			              </div></td> 
		             </tr>               
	                 <tr style="height:30px;">
	                     <td><div class="box"><span class="input-group-addon" style="display:inline;">所属站点</span>
			              <div tabindex="2" class="searchable-select" id="newpos1">
			              	<span class="searchable-select-caret"></span>
			              	<div class="searchable-select-holder"></div>
			              	<div class="searchable-select-dropdown searchable-select-hide newdown">
			              		<input type="text" class="searchable-select-input">
			              		<div class="searchable-scroll">
			              		  <div class="searchable-has-privious searchable-select-hide">...</div>
			              			<div class="searchable-select-items" id="newposName">              				
			              			</div>
			              		<div class="searchable-has-next searchable-select-hide">...</div>
			              		</div>
			              	</div>
			              </div> 
			              </div></td>
	                 	<td><div class="box"><span class="input-group-addon" style="display:inline;">领用数量</span>
	                 	<input type="text" value="" class="comsty" id="innum" data-toggle="tooltip" data-placement="bottom" title="入库数量不能为空！" />
	                    </div></td>
	                 </tr>
	                 </table>
		            </div>
		         </div>
	         </form>
	         <span class="errortilp" style="color:red;text-align:center;"></span>
	       </div>
	       <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel" >关 闭</button>
	        <button type="button" class="btn btn-primary" id="surebtn">保 存</button>
	      </div>
	      </div>
	    </div>
	   </div>                      
         </s:if>
        </s:iterator>  
        <!-- 区域 -->
       <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_queryAreaThings'">		 
	      <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">添加入库信息</h4>
		      </div>
		      <div class="modal-body">
	         <form id="addformper" action="" method="" class="tabfrom-ys">
	            <div class="widget orange"> 
		            <div class="widget-body" style="overflow:auto;">
		              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">  
                    <tr style="height:30px;">
                    <td><div class="box"><span class="input-group-addon" style="display:inline;">所属公司</span>
		              <div tabindex="0" class="searchable-select" id="newcoma1">
		              	<span class="searchable-select-caret"></span>
		              	<div class="searchable-select-holder"></div>
		              	<div class="searchable-select-dropdown searchable-select-hide newdown" >
		              		<input type="text" class="searchable-select-input">
		              		<div class="searchable-scroll">
		              		  <div class="searchable-has-privious searchable-select-hide">...</div>
		              			<div class="searchable-select-items" id="newcoma">              				
		              			</div>
		              		  <div class="searchable-has-next searchable-select-hide">...</div>
		              		</div>
		              	</div>
		              </div>
		              </div></td>
                     <td>
                     <div class="box"><span class="input-group-addon" style="display:inline;">所属区域</span>
		              <div tabindex="1" class="searchable-select" id="newarea1">
		              	<span class="searchable-select-caret"></span>
		              	<div class="searchable-select-holder"></div>
		              	<div class="searchable-select-dropdown searchable-select-hide newdown" >
		              		<input type="text" class="searchable-select-input"> 
		              		<div class="searchable-scroll">
		              		  <div class="searchable-has-privious searchable-select-hide">...</div>
		              			<div class="searchable-select-items" id="newaddarea">
		              			</div>
		              		<div class="searchable-has-next searchable-select-hide">...</div>
		              		</div>
		              	</div>
		              </div> 
		             </div>                        
                    </td>
                 </tr>
                 <tr>
                 <td><div class="box"><span class="input-group-addon" style="display:inline;">领用数量</span>
                    <input id="innum" class="comsty" style="width:130px;" type="text" value="" data-toggle="tooltip" data-placement="bottom" title="入库数量不能为空！" />
                    </div>
                 </td>
                 <td></td>
                  </tr>
	                </table>
			            </div>
			         </div>
		         </form>
		         <span class="errortilp" style="color:red;text-align:center;"></span>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel" >关 闭</button>
		        <button type="button" class="btn btn-primary" id="surebtn">保 存</button>
		      </div>
		    </div>
		  </div>
		</div>    
        </s:if>
        </s:iterator>  
  </div>
</div>
</center> 
	<script type="text/javascript">
	$(function () {
  	  $('[data-toggle="tooltip"]').tooltip()
  	})
	$("#addbtn").click(function(){
		$("#myModal").modal("show");
		  var newareaid,newposid,tempid="",newcomid="";
   	      var newareaurl="position_areaById?id=" ,newposurl="position_posiById?id=";
   	       commcom();
	   	   function commcom(){
	       	var newcomurl="position_comaById";
	       	$.ajax({
	   	    	type:"get",
	   	    	url:newcomurl,
	   	    	cache:true,
	   	        dataType:"json",   
	   	        success:function(data){  
	   	        	var comdatas = eval(data);
	   	        	$("#newcoma div").remove();	        	 
	   	        	$.each(comdatas,function(i,item){
	   	        		var comhtml="<div class='searchable-select-item  hover selected' data-value='"+comdatas[i].comid+"'>"+comdatas[i].comName+"</div>";
	   	        		 $("#newcoma").append(comhtml);	        		
	   	        	});
	   	        	$("#newcoma1 .searchable-select-holder").text($("#newcoma .selected:first").text());
	   	        	newcomid=$("#newcoma .searchable-select-item").attr("data-value");
	   	    		commarea(newareaurl,newcomid);
	   	        }
	   		});	
	       }
	       function commarea(newareaurl,newcomid){
	       	var tempurl=newareaurl+newcomid;
	   		$.ajax({
	   	    	type:"get",
	   	    	url:tempurl,
	   	    	cache:true,
	   	        dataType:"json",   
	   	        success:function (data){
	   	        	var areadatas = eval(data);
	   	        	$("#newaddarea").children().remove(); 
	   	        	$.each(areadatas,function(i,item){	        			       			
	   	        		var  areahtml="<div class='searchable-select-item hover selected' data-value='"+areadatas[i].areaid+"' >"+areadatas[i].areaName+"</div>";
	   	        		  $("#newaddarea").append(areahtml);
	   	        	});
	   	        	$("#newarea1 .searchable-select-holder").text($("#newaddarea .selected:first").text());
	   	        	newareaid=$("#newaddarea .selected").attr("data-value");
	   	        	(newareaid=="" || newareaid==null) ? newareaid="" :newareaid=newareaid;
	   	    		commpos(newposurl,newareaid);   		    		
	   	        }
	   		});	
	       }
		   	function commpos(newposurl,newareaid){
		   		var tempurl=newposurl+newareaid;
		   		$.ajax({
		   	    	type:"get",
		   	    	url:tempurl,
		   	    	cache:true,
		   	        dataType:"json",      
		   	        success:function (data){
		   	        	var dataspos = eval(data);
		   	        	$("#newposName ").children().remove();
		   	        	$.each(dataspos,function(i,item){	        		
		   	        		var	poshtml="<div class='searchable-select-item hover selected' data-value='"+dataspos[i].posid+"'>"+dataspos[i].posName+"</div>";
		           			$("#newposName").append(poshtml);
		   	        	});
		   	        	$("#newpos1 .searchable-select-holder").text($("#newposName .selected:first").text());
		   	        	newposid=$("#newposName .selected").attr("data-value");
		   	        	(newposid=="" || newposid==null)? newposid="":newposid=newposid;
		   	        }
	   		});	
	    	}
		   	$("#newcoma1").click(function(){
		   	   $(this).children().removeClass("searchable-select-hide");
		   	   $(this).children().show();
		   	   $("#newcoma1").searchData();
		      });
		      $182("#newcoma .searchable-select-item").live("click",function(){
		   	   $(this).addClass("selected").siblings().removeClass("selected");
		   	   var newcomid=$("#newcoma .selected").attr("data-value");
		 	   commarea(newareaurl,newcomid);
		   	   var val=$("#newcoma div.selected").text();
		   	   $("#newcoma1 .searchable-select-holder").text(val);
		   	   $("#newcoma1 .searchable-select-dropdown").addClass("searchable-select-hide");
		      });
		      $("#newarea1").click(function(){
		     	   $(this).children().removeClass("searchable-select-hide");
		     	   $(this).children().show();
		     	  $("#newarea1").searchData();
		        });
		        $182("#newaddarea .searchable-select-item").live("click",function(){
		     	   $("#newpos1 .searchable-select-holder").text("");
		     	   $(this).addClass("selected").siblings().removeClass("selected");
		     	   var newareaid=$("#newaddarea .selected").attr("data-value");
		    		commpos(newposurl,newareaid); 
		     	   var val=$("#newaddarea div.selected").text();
		     	   $("#newarea1 .searchable-select-holder").text(val);
		     	   $("#newarea1 .searchable-select-dropdown").addClass("searchable-select-hide");
		        });
		        $("#newpos1").click(function(){
		     	   $(this).children().removeClass("searchable-select-hide");
		     	   $(this).children().show();
		     	  $("#newpos1").searchData();
		        });
		        $182("#newposName .searchable-select-item").live("click",function(){
		     	   $(this).addClass("selected").siblings().removeClass("selected");
		     	   var  newposid=$("#newposName .selected").attr("data-value");
		     	   var val=$("#newposName div.selected").text();
		     	   $("#newpos1 .searchable-select-holder").text(val);
		     	   $("#newpos1 .searchable-select-dropdown").addClass("searchable-select-hide");
		        });
	  });
	   $("#close").click(function(){
		   $("#myModal").modal("hide");
		 });
	  $("#cancel").click(function(){
		  $("#myModal").modal("hide");
	  });
	  </script>
	  <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_listing'">
		  <script type="text/javascript">
		  $(function(){
		    	 function message(text,newdata) {
		    		   var newval= newdata;
		    	       $("#spanmessage").text(text);
		    	       $104("#message").dialog({
		    	           title:"中石油油罐车运输云平台，提示您！",
		    	           modal: true,
		    	           buttons: {
		    	               "确定": function(newdata) {
		    	            	   if(newval ==1 ){
		    	            		   $104(this).dialog("close");
		    	            		   location.href="thing_Allthing";
		    	            	   }else{
		    	            		   $104(this).dialog("close");  
		    	            	   }  
		    	               }
		    	           }
		    	       });
		    	   }
		       	 var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
		     	     $(document.body).append(boardDiv); 
		     	    function queren(text, callback) {
		                $("#spanmessage").text(text);
		                $104("#message").dialog({
		                    title: "中石油油罐车运输云平台，提示您！",
		                    modal: true,
		                    resizable: false,
		                    buttons: {
		                        "是": function() {
		                            callback.call();//方法回调
		                            $104(this).dialog("close");
		                        },
		                       "否": function() {
		     		                 $104(this).dialog("close");
		                        }
		                    } 
		                });
		            }
		       		 //添加
		     	    $("#surebtn").click(function(){  
		     	  		  var innum =$("#innum").val();
		     	  		  var newcomid=$("#newcoma1 .selected").attr("data-value");
		     	  		  var newareaid=$("#newaddarea .selected").attr("data-value");
		     	  		  var newposid=$("#newposName .selected").attr("data-value");
		     	  		  if( innum =="" || innum == null){
		     	  			  $(".errortilp").html("入库数量不能为空");
		     	  		  }	     	  		 
		     	  		  else{  
		     	  			$("#myModal").modal("hide");
		     			        console.log(newposid,newcomid,newareaid);
		     			       queren("确认领用吗？", function(){
		     			   		$.post("thing_insertAreaThings",{"th.inNum":innum,"th.comId":newcomid,"th.areaId":newareaid,"th.position":newposid},function(data){  
		     			        	    	   if(data==1){
		     						   				message("领用成功！",data);
		     						   			    $(".errortilp").html("");
		     						   			 }else{
		     						   				message("领用失败！",data);
		     						   			    $(".errortilp").html("");
		     						   			 } 
		     			        	 }); 
		     			   	   $(".errortilp").html("");
		     			    });
		     	  		  }
		     	  	});  
			});     
		</script>
	</s:if>
	</s:iterator>  
	  <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_queryAreaThings'">
		  <script type="text/javascript">
		  $(function(){
		    	 function message(text,newdata) {
		    		   var newval= newdata;
		    	       $("#spanmessage").text(text);
		    	       $104("#message").dialog({
		    	           title:"中石油油罐车运输云平台，提示您！",
		    	           modal: true,
		    	           buttons: {
		    	               "确定": function(newdata) {
		    	            	   if(newval ==1 ){
		    	            		   $104(this).dialog("close");
		    	            		   location.href="thing_Allthing";
		    	            	   }else{
		    	            		   $104(this).dialog("close");  
		    	            	   }  
		    	               }
		    	           }
		    	       });
		    	   }
		       	 var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
		     	     $(document.body).append(boardDiv); 
		     	    function queren(text, callback) {
		                $("#spanmessage").text(text);
		                $104("#message").dialog({
		                    title: "中石油油罐车运输云平台，提示您！",
		                    modal: true,
		                    resizable: false,
		                    buttons: {
		                        "是": function() {
		                            callback.call();//方法回调
		                            $104(this).dialog("close");
		                        },
		                       "否": function() {
		     		                 $104(this).dialog("close");
		                        }
		                    } 
		                });
		            }
		       		 //添加
		     	    $("#surebtn").click(function(){  
		     	  		  var innum =$("#innum").val();
		     	  		  var newcomid=$("#newcoma1 .selected").attr("data-value");
		     	  		  var newareaid=$("#newaddarea .selected").attr("data-value");
		     	  		  if( innum =="" || innum == null){
		     	  			  $(".errortilp").html("入库数量不能为空");
		     	  		  }	     	  		 
		     	  		  else{  
		     	  			    $("#myModal").modal("hide");
		     			        console.log(innum,newcomid,newareaid);
		     			       queren("确认领用吗？", function(){
		     			   		$.post("thing_thingAdd",{"at.inNum":innum,"at.comId":newcomid,"at.areaId":newareaid},function(data){  
		     			        	    	   if(data==1){
		     						   				message("领用成功！",data);
		     						   			    $(".errortilp").html("");
		     						   			 }else{
		     						   				message("领用失败！",data);
		     						   			    $(".errortilp").html("");
		     						   			 } 
		     			        	 }); 
		     			   	   $(".errortilp").html("");
		     			    });
		     	  		  }
		     	  	});  
			});     
		</script>
	  </s:if>
	</s:iterator>      
    <script type="text/javascript" src="js/laydate.js"></script>
    <script type="text/javascript" src="js/Blob.js"></script>
    <script type="text/javascript" src="js/FileSaver.js"></script>
    <script type="text/javascript" src="js/tableExport.js"></script>
    <script type="text/javascript" src="js/jquery.base64.js"></script>
    <script type="text/javascript" src="js/defaultdate.js"></script>  
    <script type="text/javascript"> 
      !function(){
          laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
          laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
        	laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
       }(); 		
    </script>   
     <!--获取数据  -->
     <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_listing'">
	     <script type="text/javascript">
	        var updown=0,benefithtml,curentPage=1,pagetotal;
	         function signstar(url,curentPage){  
		    	 $.ajax({
		   	    	type:"get",
		   	    	url:url,
		   	    	cache:true,
		   	        dataType:"json",   
		   	        success:function (data){
		   	        	 var datas = eval(data);
		   	        	 $("#benefitcoment").children().remove();
		   	        	 $.each(datas,function(i,item){
		   	        		if(updown==0){
		   	        			benefithtml="<tr class='info'><td>"+datas[i].position+"</td>";
				   	        	benefithtml+="<td>"+datas[i].totalNum+"</td>";			   	        	
				   	        	benefithtml+="<td>"+datas[i].used+"</td>";
				   	        	benefithtml+="<td>"+datas[i].lossNum+"</td>";
				   	        	benefithtml+="<td>"+((i+1)+(curentPage-1)*13)+"</td></tr>";
				   	        	$("#benefitcoment").append(benefithtml);
		   	        		}
		   	        		if(updown==1){
		   	        			benefithtml="<tr class='info'><td>"+datas[i].position+"</td>";
				   	        	benefithtml+="<td>"+datas[i].totalNum+"</td>";
				   	        	benefithtml+="<td>"+datas[i].used+"</td>";
				   	        	benefithtml+="<td>"+datas[i].lossNum+"</td>";
				   	        	benefithtml+="<td>"+(datas[i].total-13*(curentPage-1)-i)+"</td></tr>";
				   	        	$("#benefitcoment").append(benefithtml);
		   	        		}
		   	        		pagetotal=datas[i].pageNum;
	       	        	 	pageconment(curentPage,pagetotal);
		   	        	}); 
		   	           }
		         });		   
	         }
	         $("#endtime").val(endDate);
	         var jumpurl="thing_listing?sortT="+updown+"&condition.endTime="+endDate+"&currentPage="+curentPage+"";
	         $(function(){ signstar(jumpurl,curentPage);});
	         var starttime,endtime,areaname="",comname="",posname="",tempurl;
	         function commcondition(){
	        	starttime=document.getElementById("starttime").value;
	  			endtime = document.getElementById("endtime").value;
	  			posname=$("#pos1 .selected").attr("data-value");
				areaname=$("#area1 .selected").attr("data-value");
				comname=$("#coma1 .selected").attr("data-value");
				(posname=="" || posname==null) ? posname="" :posname=posname;
				(comname=="" || comname==null) ? comname="" : comname=comname;
				(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	         }
	         function searchsignList(){
	  			commcondition();
				url="thing_listing?condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&sortT="+updown+"&currentPage="+curentPage+"";
	    		signstar(url,curentPage);
	  		}
	        $(".upbene").click(function(){	
	        	commcondition();
	        	$(".downbene").removeClass("defsty");$(".upbene").addClass("defsty");
	        	updown=$(this).attr("id"); 
	      		tempurl="thing_listing?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";	
	        	signstar(tempurl,curentPage);
	        });
	        $(".downbene").click(function(){
	        	commcondition();
	        	$(".upbene").removeClass("defsty"); $(".downbene").addClass("defsty");
	        	updown=$(this).attr("id");
	          	tempurl="thing_listing?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";	
	        	signstar(tempurl,curentPage);
	        });
	       //下一页
			$182("#next").live("click",function(){
				 commcondition();
				 curentPage = parseInt(curentPage)+1;
				 $("#curpage").text(curentPage); 
				 pageconment(curentPage,pagetotal);
				 jumpurl="thing_listing?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
				 signstar(jumpurl,curentPage);
			}); 
			//上一页
			$182("#pre").live("click",function(){
				commcondition();
				curentPage = curentPage -1;  
				$("#curpage").text(curentPage);
				pageconment(curentPage,pagetotal);
				jumpurl="thing_listing?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
				signstar(jumpurl,curentPage);
			});
			function pageconment(curentPage,totalPage){
				$(".table-bottom-page").empty();
				if(curentPage>1){
					 pagehtml="<a id='pre' >上一页 </a>&nbsp";
					 $(".table-bottom-page").append(pagehtml); 
				}
				if(curentPage<totalPage){
					 pagehtml="<a id='next' >下一页 </a>&nbsp";
					 $(".table-bottom-page").append(pagehtml);
				}
			} 
	     </script>
     	</s:if>
     </s:iterator>
     <!-- 区域 -->
     <s:iterator value="#session.ADMIN_POWER">
        <s:if test="poUrl=='thing_queryAreaThings'">
	     <script type="text/javascript">
	        var updown=0,benefithtml,curentPage=1,pagetotal;
	         function signstar(url,curentPage){     
		    	 $.ajax({
		   	    	type:"get",
		   	    	url:url,
		   	    	cache:true,
		   	        dataType:"json",   
		   	        success:function (data){
		   	        	 var datas = eval(data);
		   	        	 $("#benefitcoment").children().remove();
		   	        	 $.each(datas,function(i,item){		   	        		
		   	        		datas[i].detailName ==null ? datas[i].detailName ="":datas[i].detailName=datas[i].detailName;
		   	        		if(updown==0){
		   	        			benefithtml="<tr class='info'><td>"+datas[i].areaId+"</td>";
				   	        	benefithtml+="<td>"+datas[i].inNum+"</td>";			   	        	
				   	        	benefithtml+="<td>"+datas[i].time+"</td>";
				   	        	benefithtml+="<td>"+datas[i].detailName+"</td>";
				   	        	$("#benefitcoment").append(benefithtml);
		   	        		}
		   	        		if(updown==1){
		   	        			benefithtml="<tr class='info'><td>"+datas[i].areaId+"</td>";
				   	        	benefithtml+="<td>"+datas[i].inNum+"</td>";			   	        	
				   	        	benefithtml+="<td>"+datas[i].time+"</td>";
				   	        	benefithtml+="<td>"+datas[i].detailName+"</td>";
				   	        	$("#benefitcoment").append(benefithtml);
		   	        		}
		   	        		pagetotal=datas[i].pageNum;
	       	        	 	pageconment(curentPage,pagetotal);
		   	        	});    	        	
		   	           }
		         });
	         }
	         $("#endtime").val(endDate);
	         var jumpurl="thing_queryAreaThings?condition.endTime="+endDate+"&currentPage="+curentPage+"";
	         $(function(){ signstar(jumpurl,curentPage);});
	         var starttime,endtime,areaname="",comname="",posname="",tempurl;
	         function commcondition(){
	        	starttime=document.getElementById("starttime").value;
	  			endtime = document.getElementById("endtime").value;
	  			posname=$("#pos1 .selected").attr("data-value");
				areaname=$("#area1 .selected").attr("data-value");
				comname=$("#coma1 .selected").attr("data-value");
				(posname=="" || posname==null) ? posname="" :posname=posname;
				(comname=="" || comname==null) ? comname="" : comname=comname;
				(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	         }
	         function searchsignList(){
	  			commcondition();
				url="thing_queryAreaThings?condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
	    		signstar(url,curentPage);
	  		}
	        $(".upbene").click(function(){	
	        	commcondition();
	        	$(".downbene").removeClass("defsty");$(".upbene").addClass("defsty");
	        	updown=$(this).attr("id"); 
	      		tempurl="thing_queryAreaThings?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";	
	        	signstar(tempurl,curentPage);
	        });
	        $(".downbene").click(function(){
	        	commcondition();
	        	$(".upbene").removeClass("defsty"); $(".downbene").addClass("defsty");
	        	updown=$(this).attr("id");
	          	tempurl="thing_queryAreaThings?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";	
	        	signstar(tempurl,curentPage);
	        });
	       //下一页
			$182("#next").live("click",function(){
				 commcondition();
				 curentPage = parseInt(curentPage)+1;
				 $("#curpage").text(curentPage); 
				 pageconment(curentPage,pagetotal);
				 jumpurl="thing_queryAreaThings?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
				 signstar(jumpurl,curentPage);
			}); 
			//上一页
			$182("#pre").live("click",function(){
				commcondition();
				curentPage = curentPage -1;  
				$("#curpage").text(curentPage);
				pageconment(curentPage,pagetotal);
				jumpurl="thing_queryAreaThings?sortT="+updown+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
				signstar(jumpurl,curentPage);
			});
			function pageconment(curentPage,totalPage){
				$(".table-bottom-page").empty();
				if(curentPage>1){
					 pagehtml="<a id='pre' >上一页 </a>&nbsp";
					 $(".table-bottom-page").append(pagehtml); 
				}
				if(curentPage<totalPage){
					 pagehtml="<a id='next' >下一页 </a>&nbsp";
					 $(".table-bottom-page").append(pagehtml);
				}
			} 
	     </script>
       </s:if>
     </s:iterator>
     <script type="text/javascript" src="js/newcommpos.js"></script>  
     <script type="text/javascript" src="js/searchsuosou.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>
  </body>
</html>
