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
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
	<script type="text/javascript">
  	  var $104=$;
  	</script>	
  	<style type="text/css">
  	  .box{float:left;padding:5px;} .input-group-addon{width:auto;display:inline;}
  	  .table tr th,tr td {word-break: keep-all;}
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;固定签封记录	       	
		      </h3>		     
		   </div>			   					   
		 </div>    
        <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		   </div>	
		   <div class="panel-body">		     
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >车牌号</span></span>	
              <div tabindex="4" class="searchable-select" id="car1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder"></div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="carid">              				
              			</div>
              		  <div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div>
              </div> 
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >所属公司</span></span>	
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
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >所属区域</span></span>
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
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >所属站点</span></span>
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
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >操作人</span></span>
              <div tabindex="3" class="searchable-select" id="per1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder"></div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="pername">
              			</div>
              		<div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div> 
              </div> 
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >状态</span></span>
              <div tabindex="5" class="searchable-select" id="stutas1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.status==10?'已完成':'运输中'}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="status">
              			   <div class="searchable-select-item hover ${condition.status==10?'selected':''}" data-value="10">已完成</div>
              			   <div class="searchable-select-item hover ${condition.status==11?'selected':''}" data-value="11">运输中</div>
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
               <span style="width:80px;right:5%;float:left;"><input type="button" value="查询" id="searchbtn" class="search btn btn-primary" onclick="searchfixsign()" style="width:80px;height:28px;"/></span>
              </div>
            </div>
            <input id="locid" style="width:120px;" type="hidden" value="${condition.comId }" />
          </div>
          <script type="text/javascript" src="js/laydate.js"></script>
          <script type="text/javascript" src="js/Blob.js"></script>
          <script type="text/javascript" src="js/FileSaver.js"></script>
          <script type="text/javascript"> 
	        !function(){
	            laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	            laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
	          	laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
	         }();	         
	      </script>
	      <input type="hidden" value="" id="saveval"/>
         <div class="widget orange">
          <div class="widget-title">
              <h4 style="margin:0 auto;line-height: 36px;"><i class="fa fa-reorder"></i> 签封记录</h4>                   
          </div>
          <div class="widget-body" style="overflow:auto;height:64em;">
            <table class="table table-striped table-bordered table-advance table-hover">
             <thead>
              <tr>
	               <th style="border-left:none;border-right:none;text-align:right;width:100px;">车辆信息</th>
	               <th style="border-left:none;"></th>
	               <th style="border-right:none;"></th>	        
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;">施封信息</th>	               
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>
	               <th style="border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th> 
	               <th style="border-left:none;border-right:none;text-align:right;">解封信息</th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>              	               
              </tr>
              <tr>
	               <th style="border-top:1px solid #ccc;">车牌号</th>
	               <th style="border-top:1px solid #ccc;">所属公司</th>
	               <th style="border-top:1px solid #ccc;">施封人</th>
		           <th style="border-top:1px solid #ccc;">施封点</th>
		           <th style="border-top:1px solid #ccc;">施封时间</th>
	               <th style="border-top:1px solid #ccc;">施封内码</th>
	               <th style="border-top:1px solid #ccc;">施封原因</th>
	               <th style="border-top:1px solid #ccc;">施封签名</th>
	               <th style="border-top:1px solid #ccc;">解封人</th>
	               <th style="border-top:1px solid #ccc;">解封点</th>
	               <th style="border-top:1px solid #ccc;">解封时间</th>
	               <th style="border-top:1px solid #ccc;">解封外码</th>
	               <th style="border-top:1px solid #ccc;">解封签名</th>
                </tr>
              </thead>
             <tbody>
              <s:iterator value="list">
	             <tr style="height:30px;class="info"">
	                 <td><s:property value="plateNumber"/></td>
	                 <td><s:property value="company"/></td>
	             	 <td><s:property value="seaName"/></td>
	             	 <td><s:property value="seaPosi"/></td>
	                 <td><s:property value="seaTime"/></td>
	                 <td><s:property value="seaCode"/></td>
	                 <td><s:property value="seaTip"/></td>
	             	 <td><img src="<s:property value='seaSign'/>" width='30' height='30'/></td>
	                 <td><s:property value="freName"/></td>
	                 <td><s:property value="frePosi"/></td>
	                 <td><s:property value="freTime"/></td>
	             	 <td><s:property value="freCode"/></td>
	                 <td><img src="<s:property value='freSign'/>" width='30' height='30'/></td>	                              	 
	             </tr>
             </s:iterator>
             </tbody>
           </table>  
            <div style="display:none;" id="winSelector"></div>
           	<div id="bigView" style="display:none;position:absolute;"><img width="350" height="350" alt="" src="" onerror="this.src='common/img/error.jpg'"/></div>
          </div>         
          <!-- 分页 -->
           <div class="table-bottom" >
             <s:if test="pageBean.totalCount !=''">
              <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录，当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
             </div> 
             </s:if>
             <div style="width::38%;float:right;">
                <span class="table-bottom-page">
                <s:if test="pageBean.curentPage>1">
                	<a onclick="javascript:preJump()">上一页 </a>&nbsp;
                </s:if>
                <s:if test="pageBean.curentPage<pageBean.totalPage">
	                <a  onclick="javascript:nextJump()">下一页  </a>
                </s:if>
                <s:if test="pageBean.totalPage>1">
                	&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;" id="jumpPage"/> 页&nbsp;<a onclick="jumpPage();">  跳转  </a>
                </s:if>
                </span>
             </div>
            <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
            <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
           </div>
          </div>  
      </div>
 
    </div>
    <!-- 右边部分完结-->
  </div>
</div>
</center>
 	 <!-- 分页查询 -->         
     <script type="text/javascript">
		    var starttime,endtime,comname="",carid="",status="",pername="",perid="",jumpurl,posname="",areaname="";	            		
     		function nextJump(){ 
     			starttime=document.getElementById("starttime").value; 
     			endtime = document.getElementById("endtime").value;
     			posname=$("#pos1 .selected").attr("data-value");
    			areaname=$("#area1 .selected").attr("data-value");
    			comname=$("#coma1 .selected").attr("data-value");
    			perid=$("#per1 .selected").attr("data-value");
    			pername=$("#per1 .selected").text();
    			status=$("#stutas1 .selected").attr("data-value");
    			carid=$("#carid .selected").attr("data-value");
    			(posname=="" || posname==null) ? posname="" :posname=posname;
 				(comname=="" || comname==null) ? comname="" : comname=comname;
 				(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
 				(pername=="" || pername==null) ? pername="" : pername=pername;
 				(perid=="" || perid==null) ? perid="" : perid=perid;
 				(carid=="" || carid==null) ? carid="" : carid=carid;
    			jumpurl="fixSea_list?condition.perName="+pername+"&condition.perid="+perid+"&condition.comId="+comname+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posId="+posname+"&pageBean.curentPage=";
     			commnextJump(jumpurl);
     		}
     		function preJump(){ 
     			starttime=document.getElementById("starttime").value; 
     			endtime = document.getElementById("endtime").value;
     			posname=$("#pos1 .selected").attr("data-value");
    			areaname=$("#area1 .selected").attr("data-value");
    			comname=$("#coma1 .selected").attr("data-value");
    			perid=$("#per1 .selected").attr("data-value");
    			pername=$("#per1 .selected").text();
    			status=$("#stutas1 .selected").attr("data-value");
    			carid=$("#carid .selected").attr("data-value");
    			(posname=="" || posname==null) ? posname="" :posname=posname;
 				(comname=="" || comname==null) ? comname="" : comname=comname;
 				(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
 				(pername=="" || pername==null) ? pername="" : pername=pername;
 				(perid=="" || perid==null) ? perid="" : perid=perid;
 				(carid=="" || carid==null) ? carid="" : carid=carid;
    			jumpurl="fixSea_list?condition.perName="+pername+"&condition.perid="+perid+"&condition.comId="+comname+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posId="+posname+"&pageBean.curentPage=";
     			commpreJump(jumpurl);
     		}
     		function jumpPage(){
     			starttime=document.getElementById("starttime").value; 
     			endtime = document.getElementById("endtime").value;
     			posname=$("#pos1 .selected").attr("data-value");
    			areaname=$("#area1 .selected").attr("data-value");
    			comname=$("#coma1 .selected").attr("data-value");
    			perid=$("#per1 .selected").attr("data-value");
    			pername=$("#per1 .selected").text();
    			status=$("#stutas1 .selected").attr("data-value");
    			carid=$("#carid .selected").attr("data-value");
    			(perid=="" || perid==null) ? perid="" : perid=perid;
     			(posname=="" || posname==null) ? posname="" :posname=posname;
 				(comname=="" || comname==null) ? comname="" : comname=comname;
 				(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
 				(pername=="" || pername==null) ? pername="" : pername=pername; 
 				(carid=="" || carid==null) ? carid="" : carid=carid;
    			jumpurl="fixSea_list?condition.perName="+pername+"&condition.perid="+perid+"&condition.comId="+comname+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posId="+posname+"&pageBean.curentPage=";
     			commjumpPage(jumpurl);
     		}
     		function searchfixsign(){ 
     			starttime=document.getElementById("starttime").value; 
     			endtime = document.getElementById("endtime").value;
     			posname=$("#pos1 .selected").attr("data-value");
     			areaname=$("#area1 .selected").attr("data-value");
    			comname=$("#coma1 .selected").attr("data-value");
    			perid=$("#per1 .selected").attr("data-value");
    			pername=$("#per1 .selected").text();
    			status=$("#stutas1 .selected").attr("data-value");
    			carid=$("#carid .selected").attr("data-value");
    			(perid=="" || perid==null) ? perid="" : perid=perid;
    			(pername=="" || pername==null) ? pername="" : pername=pername;     			
    			(posname=="" || posname==null) ? posname="" :posname=posname;
 				(comname=="" || comname==null) ? comname="" : comname=comname;
 				(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
 				(carid=="" || carid==null) ? carid="" : carid=carid;
    			var url="fixSea_list?condition.perName="+pername+"&condition.perid="+perid+"&condition.comId="+comname+"&condition.carFlapper="+carid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posId="+posname+"&condition.areaid="+areaname;
     			commSearch(url);
     		}
     </script>
     <script type="text/javascript" src="js/commSearch.js"></script> 
     <script type="text/javascript" src="js/newcommpos.js"></script>  
     <script type="text/javascript" src="js/findCarList.js"></script>  
     <script type="text/javascript" src="js/bigpic.js"></script>
     <script type="text/javascript" src="js/searchsuosou.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>
  </body>
</html>
