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
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    
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
    <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
  	<script type="text/javascript">
  	  var $104=$;
  	</script>
  	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>		
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;App升级记录		       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		    </div>	
           <div class="panel-body">	  	              	        
              <div class="box"><span class="input-group-addon" style="display:inline;">是否已升级:</span>
              <div tabindex="2" class="searchable-select" id="stutas1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.status=='NO'?'NO':'YES'}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">   
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="status">              				
              			   <div class="searchable-select-item hover ${condition.status=='YES'?'selected':''}" data-value="YES">YES</div>
              			   <div class="searchable-select-item hover ${condition.status=='NO'?'selected':''}" data-value="NO">NO</div> 
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
               <span style="width:80px;right:5%;float:left;">
               <input type="button" value="查询" id="searchbtn" class="search btn btn-primary" onclick="searchAppList()" style="width:75px;"/></span>              
              </div>
            </div>
          </div>
        <div class="widget orange">
            <div class="widget-title">
                <h4 style="margin:0 auto;line-height: 36px;"><i class="fa fa-reorder"></i>操作员信息记录</h4>                   
            </div>
            <div class="widget-body" style="overflow:auto;height:50em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel"> 
             <thead>
              <tr>
	               <th style="">所属站点</th>
	               <th style="">App版本</th>
	               <th style="">序列号</th>
	               <th style="">是否已升级</th>	             
              </tr>              
             </thead>
             <tbody>
              <s:iterator value="list">  
	            <tr style="height:30px;" class="info">
                 	<td><s:property value="pname"/></td>
                 	<td><s:property value="appNum"/></td>
                 	<td><s:property value="imei"/></td>
                 	<td>
                 	  <s:if test="appNum != null">
                 	    <s:property value="success"/>
                 	  </s:if>
                 	</td>              	
	            </tr>
               </s:iterator> 
             </tbody>             
           </table>  
          </div>
          </div>
          <!-- 分页开始 -->
          <div class="table-bottom" >
             <s:if test="pageBean.totalCount !=''">
              <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录,共有<a><s:property value="pageBean.totalPage"/></a> 页,当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
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
	<script type="text/javascript" src="js/commSearch.js"></script> 
    <script type="text/javascript" src="js/laydate.js"></script>
    <script type="text/javascript" src="js/defaultdate.js"></script>
    <script type="text/javascript"> 
      !function(){
          laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
          laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
          laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
       }(); 		
    </script>   
     <script type="text/javascript" src="js/commbase/appsearch.js"></script>
     <script type="text/javascript" src="js/searchsuosou.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>
  </body>
</html>
