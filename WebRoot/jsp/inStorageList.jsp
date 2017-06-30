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
  	<%-- <script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap-table-expandable.js"></script> --%>
  	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
      var $182=$;
    </script> 
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>   
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;入库记录	       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		    </div>	
	           <div class="panel-body">	  	              
		          <div class="box"><span class="input-group-addon" style="display:inline;">库存地点</span>
		             <input type="text" class="comsty" name="" id="instroname" value='' /> </span>
		          </div>                
              <div class="box">
                <span style="width:80px;right:5%;float:left;">
                <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="">
			         <i class="fa fa-search"> 查  询</i>
			    </button></span>
              </div>
          </div>
          </div>
          <div class="widget orange">
            <div class="widget-title">
                <!-- <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 入库信息记录</h4>  -->                  
            </div>
            <div class="widget-body" style="overflow:auto;height:54em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
            <thead>
              <tr  style="height:30px;"> 
                 <th></th>             
                 <th>收货人</th>
                 <th>入库箱数</th>
                 <th>入库袋数</th>
                 <th>入库个数</th>
                 <th>入库地址</th>
                 <th>入库时间</th>
                 <th>状态</th>                             
              </tr>
            </thead>
            <tbody id="instocoment">
			  <!--  <tr data-toggle="collapse" data-parent="#accordion" 
				   href=".collapseOne" class="success" style="cursor:pointer">
				 <td style="text-align:center;"><i class="fa fa-plus" style="color:blue;"></i></td>
			     <td>王一</td>
			     <td>5</td>
			     <td>100</td>
			     <td>5000</td>
			     <td>销售分公司</td>
			     <td>2016-11-12 09:22:45</td>
			     <td style="color:red;">已入库</td>
			   </tr>
			   <tr class="panel-collapse collapse collapseOne warning" data-toggle="collapse" data-parent="#accordion" 
				   href=".jstacoll" style="cursor:pointer">
				 <td style="text-align:right;"><i class="fa fa-plus" style="color:blue;"></i></td>
			     <td>鲁经理</td>
			     <td>2</td>
			     <td>40</td>
			     <td>2000</td>
			     <td>江南炳草岗</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">已入库</td>
			   </tr>
			   <tr class="panel-collapse collapse jstacoll">
			     <td ></td>
			     <td>朱佳</td>
			     <td>1</td>
			     <td>20</td>
			     <td>1000</td>
			     <td>瓜子坪加油站</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">待入库</td>
			   </tr>
			   <tr class="panel-collapse collapse jstacoll">
			     <td ></td>
			     <td>陈一龙</td>
			     <td>/</td>
			     <td>8</td>
			     <td>400</td>
			     <td>龙井加油站</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">待入库</td>
			   </tr>
			   <tr class="panel-collapse collapse jstacoll">
			     <td ></td>
			     <td>许勇</td>
			     <td>/</td>
			     <td>12</td>
			     <td>600</td>
			     <td>倮果桥加油站</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">已入库</td>
			   </tr>
			   <tr class="panel-collapse collapse collapseOne danger" data-toggle="collapse" data-parent="#accordion" 
				   href=".rstacoll" style="cursor:pointer">
			      <td style="text-align:right;"><i class="fa fa-plus" style="color:blue;"></i></td>
			     <td>李经理</td>
			     <td>1</td>
			     <td>20</td>
			     <td>1000</td>
			     <td>仁和老街</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">已入库</td>
			   </tr>
			   <tr class="panel-collapse collapse rstacoll">
			     <td ></td>
			     <td>杜云霞</td>
			     <td>/</td>
			     <td>10</td>
			     <td>500</td>
			     <td>南山加油站</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">待入库</td>
			   </tr>
			   <tr class="panel-collapse collapse rstacoll">
			     <td ></td>
			     <td>杨朝丽</td>
			     <td>/</td>
			     <td>10</td>
			     <td>500</td>
			     <td>巴斯箐加油站</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">待入库</td>
			   </tr>
			   <tr class="panel-collapse collapse collapseOne info">
			     <td></td>
			     <td>伍华(油库)</td>
			     <td>2</td>
			     <td>40</td>
			     <td>2000</td>
			     <td>金江油库</td>
			     <td>2016-11-19 10:10:34</td>
			     <td style="color:red;">已入库</td>
			   </tr> -->
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
     <script type="text/javascript" src="js/commbase/instoragesearch.js"></script>
     <script type="text/javascript" src="js/tabmenu.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>
  </div>
</div>
</center>
  </body>
</html>
