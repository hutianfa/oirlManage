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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;分公司库存记录	       	
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
		          <div class="box"><span class="input-group-addon" style="display:inline;">库存位置</span>
		             <input type="text" class="comsty" name="" id="instroname" value='' /> </span>
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
            </div>
            <div class="widget-body" style="overflow:auto;height:54em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
            <thead>
              <tr  style="height:30px;"> 
                 <th></th>             
                 <th>总数量</th>
                 <th>使用数量</th>
                 <th>剩余数量</th>
                 <th>库存地址</th> 
                 <th>截止时间</th>                           
              </tr>
            </thead>
            <tbody id="instocoment">
			  
            </tbody>
           </table>  
           </div> 
           </div>
         </div>
      </div>
     
    </div>  
     <!-- 右边部分完结-->     
     <script type="text/javascript" src="js/commbase/storagesearch.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>
  </div>
</div>
</center>
  </body>
</html>
