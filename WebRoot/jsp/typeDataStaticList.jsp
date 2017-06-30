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
	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
   	<link rel="stylesheet" type="text/css" href="admin/css/employmanament.css"/>   	
  	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
  	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
  	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>    
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script> 
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>  
    <script type="text/javascript">
  	  var $104=$;
  	</script>
  	<script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap-modal.js"></script>
  	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
      var $182=$;
    </script> 
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script> 
    <style>
    .widget-title a.a-btn {
	    line-height: 36px;
	    color: #fff;
	    text-align: left;
	    display: inline-block;
	    height: 34px;
	    float: left;
	    padding: 0px 5px;
	    font-family: "微软雅黑";
	    text-decoration: none;
	    margin-right: 5px;
	}
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
	     <div class="panel panel-info" style="margin-bottom:0px;">
		   <div class="panel-heading" style="text-align:left;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;异常统计分析	       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <!-- 查询 -->
		 <%-- <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		   </div>	
	         <div class="panel-body">	  	              
		       <div class="box"><span class="input-group-addon" style="display:inline;"><span >类型</span></span>
	             <div tabindex="5" class="searchable-select" id="aptype">
	             	<span class="searchable-select-caret"></span>
	             	<div class="searchable-select-holder">片区</div>
	             	<div class="searchable-select-dropdown searchable-select-hide">
	             		<input type="text" class="searchable-select-input"> 
	             		<div class="searchable-scroll">
	             		  <div class="searchable-has-privious searchable-select-hide">...</div>
	             			<div class="searchable-select-items" id="apointtype">
	             			   <div class="searchable-select-item"   data-value="0">站点</div>
	             			   <div class="searchable-select-item"   data-value="1">车牌号</div>
	             			   <div class="searchable-select-item"   data-value="2">操作人</div>
	             			</div>
	             		<div class="searchable-has-next searchable-select-hide">...</div>
	             		</div>
	             	</div>
	             </div>
	           </div>                 
               <div class="box">
                <span style="width:80px;right:5%;float:left;">
                <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="">
			         <i class="fa fa-search"> 查  询</i>
			    </button></span>
               </div>
            </div>
          </div> --%>
          <div class="widget orange" style="position:relative;">
            <div id="loadlay" style="position:absolute;opacity:0.6;width:100%;height:auto;"></div> 
            <div class="widget-title">
               <!-- <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 日常数据统计记录</h4> -->
               <a href="javascript:;" class="a-btn" id="pritbtn">
                  <i class="fa fa-print"></i><span class="text">导出Excel</span>
               </a>                   
            </div>
            <div class="widget-body" style="overflow:auto;height:54em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="areatab">          
	            <thead>
	              <tr  style="height:30px;">
	              	 <th>所属位置</th> 
	                 <th>未施封数量</th>
	                 <th>统计时间</th>                            
	              </tr>
	            </thead>
	            <tbody id="poscont">
				    
	            </tbody>
             </table>
             <table class="table table-striped table-bordered table-advance table-hover" id="cartab" style="display:none;">          
	            <thead>
	              <tr  style="height:30px;">
	              	 <th>车牌号</th> 
	                 <th>使用数量</th>
	                 <th>异常数量</th>
	                 <th>统计时间</th>                             
	              </tr>
	            </thead>
	            <tbody>
				   <tr>
				     <td>川AJ5255</td>
				     <td>100</td>    
				     <td>1</td>
				     <td>2016-12-13 9:22:45</td>
				   </tr>
				   <tr>
				   	 <td>川D26626</td>
				     <td>200</td>
				     <td>4</td>
				     <td>2016-12-25 08:12:45</td>
				   </tr>
				    <tr>
				   	 <td>川T26572</td>
				     <td>500</td>
				     <td>2</td>
				     <td>2016-12-25 08:12:45</td>
				   </tr>
	            </tbody>
             </table>    
           </div> 
           </div>
           <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
           <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
           <div class="table-bottom">
             <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录，当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
             </div>
             <div style="width::38%;float:right;">
                <span class="table-bottom-page">
                <s:if test="pageBean.curentPage>1">
                	<a  onclick="javascript:preJump()">上一页 </a>&nbsp;
                </s:if>
                <s:if test="pageBean.curentPage<pageBean.totalPage">
	                <a  onclick="javascript:nextJump()">下一页  </a>
                </s:if>
                <s:if test="pageBean.totalPage>1">
                 &nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;" id="jumpPage"/> 页&nbsp;<a href="javascript:void(0);" onclick="javascript:jumpPage()">  跳转  </a></span>
                </s:if>
             </div>
          </div>
         </div>
      </div>
    </div>  
     <!-- 右边部分完结-->
     <script type="text/javascript" src="js/commbase/typesearch.js"></script>
     <script type="text/javascript" src="js/commbase/jquery.table2excel.min.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>
     <script type="text/javascript">
     $("#pritbtn").click(function(){
   	    $("#areatab").table2excel({
   		  // 导出的Excel文档的名称
   		  name: "Excel Document Name",
   		  // Excel文件的名称
   		  filename: "日常数据统计信息"
   		});   
     });
     </script>
  </div>
</div>
</center>
  </body>
</html>
