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
    <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script> 
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>  
    <script type="text/javascript">
  	  var $104=$;
  	</script>
  	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
  	<script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap-modal.js"></script>
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;库存记录	       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		    </div>	
	        <div class="panel-body">	  	              
	           <div class="box">
	              <span class="input-group-addon" style="display:inline;">所属地址</span>
	              <span style="">
	                <input type="text" class="comsty" name="" id="instroname" value='' />  
	              </span>
	           </div> 
	           <div class="box">
	               <span class="input-group-addon" style="display:inline;">截止时间</span>
			       <span class="demo1" style="margin-left:-5px;">
			         <input style="height:28px;line-hieght:28px;" class="laydate-icon comsty" id="endtime" value="">
			       </span>
	           </div>               
               <div class="box">
                   <span style="width:80px;right:5%;float:left;">
	                <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="">
				        <i class="fa fa-search"> 查  询</i>
				    </button>
			       </span>
               </div>
          </div>
          </div>
          <div class="widget orange">
            <div class="widget-title">                  
               <a href="javascript:;" class="a-btn" id="pritbtn">
                  <i class="fa fa-print"></i><span class="text">导出Excel</span>
               </a>
            </div>
            <div class="widget-body" style="overflow:auto;height:54em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
            <thead>
              <tr  style="height:30px;">
              	 <th>产品名称</th> 
				 <th>总数量</th>
				 <th>使用数量</th>
                 <th>库存数量</th>
                 <th>所属地址</th>
                 <th>统计时间</th>                            
              </tr>
            </thead>
            <tbody id="storagedata">
			   <tr>
			     <td>二维码签封</td>
			     <td>200</td>
			     <td>150</td>
			     <td>50</td>    
			     <td>炳草岗加油站</td>
			     <td>2016-12-13 9:22:45</td>
			   </tr>
			   <tr>
			   	 <td>二维码签封</td>
			   	 <td>300</td>
			   	 <td>180</td>
			     <td>120</td>
			     <td>老街加油站</td>
			     <td>2016-12-25 08:12:45</td>
			   </tr>
			   <tr>
			   	 <td>二维码签封</td>
			   	 <td>1800</td>
			   	 <td>1750</td>
			     <td>50</td>
			     <td>金江油库</td>
			     <td>2016-12-25 08:12:45</td>
			   </tr>
			    <tr>
			   	 <td>二维码签封</td>
			   	 <td>100</td>
			   	 <td>80</td>
			     <td>20</td>
			     <td>西城渔门加油站</td>
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
     <script type="text/javascript" src="js/commbase/jquery.table2excel.min.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>        
     <script type="text/javascript">
       $("#pritbtn").click(function(){
    	    $("#updatab").table2excel({
    		  // 导出的Excel文档的名称
    		  name: "Excel Document Name of storage",
    		  // Excel文件的名称
    		  filename: "库存信息"
    		});   
       });
       !function(){
           laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
           laydate({elem:'#endtime',format:'YYYY-MM-DD'});
        }();
     </script>
  </div>
</div>
</center>
  </body>
</html>
