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
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">   
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="car/css/carmanage.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>  
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css"> 
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
    <script type="text/javascript"  src="js/jquery.autocomplete.min.js"></script> 
  	<script type="text/javascript" src = "js/findCompanyList.js"></script>
  	<script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap-modal.js"></script>
  	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
      var $182=$;
    </script> 
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>  	
  </head>
  <style>
  .emupdate,.emdel,.save{border: 1px solid #364f86;border-radius: 3px;outline: medium none;transition: all 0.3s ease-in-out 0s;color: blue;}
  </style>
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;授权记录		       	
		      </h3>		     
		   </div>						   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		   </div>	
		   <div class="panel-body">	  	              
                <div class="box"><span class="input-group-addon" style="display:inline;">授权人</span>
                  <input type="text" class="comsty" value="${em.name}" id="name" /></span>  
                </div>
                <div class="box"><span style="width:80px;right:5%;float:left;">
              	 <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="javascript:searchempower()">
			         <i class="fa fa-search"> 查  询</i>
			     </button></span>
              	</div>
                <%-- <s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='empower_addPow'">
              			<div class="box"><span style="width:80px;right:15%;float:left;">
              			 <input type="button" value="添加" id="addbtn" class="search btn btn-primary" style="height:2.3em;width:80px;" /></span>
              		    </div>
              		</s:if>
              	</s:iterator> --%>
               </div>
             </div>
          </div>          
           <div class="widget orange">
            <div class="widget-title">
                <!-- <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 授权信息记录</h4> -->                   
                <s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='empower_addPow'">
              		 <a href="javascript:;" class="a-btn" id="addbtn">
                      <i class="fa fa-pencil"></i><span class="text">新增授权人</span>
                     </a>
              		</s:if>
              	</s:iterator>
            </div>
            <div class="widget-body" style="overflow:auto;height:64em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatab"> 
              <thead>
	           <tr> 
                 <th>授权人</th>
                 <!-- <th>所属公司</th> -->
                 <th>授权码</th>
                 <th>操作</th>
                 <th>操作</th>
             </tr>
             </thead>
             <tbody class="conment-code">
            
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
           <!-- 分页结束 -->
          </div>
      </div>
    </div>
    <!-- 右边部分完结-->
      <!-- 弹出层-->
        <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">添加授权信息</h4>
		      </div>
		      <div class="modal-body">
		         <form id="addformper" action="" method="" class="tabfrom-ys">
		            <div class="widget orange"> 
			            <div class="widget-body" style="overflow:auto;">
			              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
			                  <tr style="height:30px;">
			                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">授权人</span>
			                        <input id="uname" type="text" value="" class="comsty" name="em.name" data-toggle="tooltip" data-placement="bottom" title="授权人不能为空！"/>
			                      </div>
			                     </td>
			                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">授权码</span>
			                      <input id="emcode" type="text" value="" class="comsty" name="em.powerCode" data-toggle="tooltip" data-placement="bottom" title="授权码不能为空！"/>
			                      </div>
			                     </td>
			                  </tr>			                 
			              </table>
			            </div>
			         </div>
		         </form>
		         <span class="errortilp" style="color:red;text-align:center;"></span>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel" >关 闭</button>
		        <button type="button" class="btn btn-primary" id="sureaddbtn">保 存</button>
		      </div>
		    </div>
		  </div>
		</div>
    <script type="text/javascript" src="js/commbase/empsearch.js"></script>
    <script type="text/javascript" src="js/boswer.js"></script>
  </div>
</div>
</center>
  </body>
</html>
