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
		<meta http-equiv="expires" content="0"> 
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
		<link rel="stylesheet" type="text/css" href="exception/css/excmanage.css"/>
	  <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	  <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
	  <!-- <link rel="stylesheet" type="text/css" href="common/css/nocommsearchmedia.css"/>  --> 
	  <link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css"> 	  
	  <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	  <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	  <script type="text/javascript">
  	   var $182=$;
  	  </script>
  	  <script type="text/javascript"  src="bootstrap-3.3.6-dist/js/bootstrap-modal.js"></script>
  	  <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	  <script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	  <script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
  	  <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	  <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
	  <script type="text/javascript">
	    var $104=$;
  	  </script> 	 
  </head>  
  <body>
  <center>
    <div  id="wrapper">
   <!--顶部标题-->
    <jsp:include page="/header.jsp" />
  <!-- 左侧树形菜单栏-->
     <jsp:include page="common1.jsp" />
    <!-- 右侧内容-->
     <div id="page-wrapper">
         <div id="page-inner">
	     <div class="panel panel-info" >
		   <div class="panel-heading" style="text-align:left;float:left;width:50%;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;异常记录		       	
		      </h3>		     
		   </div>	
		   <div class="panel-heading" style="text-align:right;float:right;width:50%;height:42px;">		     
		      <h3 class="panel-title">
		        <%-- <span id="export" style="display:inline-block;"><input type="button" class="btn btn-primary" data-type="xls" value="导出Excel" id="tableExcelbtn"/></span> --%>
		      </h3>
		   </div>						   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		   </div>	
		   <div class="panel-body">	  	              
	          <div class="box" id="carbx"><span class="input-group-addon" style="display:inline;"><span>车牌号</span></span>
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
    
             <div class="box"><span class="input-group-addon" style="display:inline;"><span>所属公司</span></span>
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
              <div class="box"><span class="input-group-addon" style="display:inline;"><span>所属区域</span></span>
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
              <div class="box"><span class="input-group-addon" style="display:inline;"><span>所属站点</span></span>
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
               <div class="box"><span class="input-group-addon" style="display:inline;"><span>操作人</span></span>
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
              <div class="box"><span class="input-group-addon" style="display:inline;"><span>异常类型:</span></span>
              <div tabindex="5" class="searchable-select" id="exc1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.excType==5?'外壳有损坏':condition.excType==10? '钢丝有损坏':condition.excType==13?'外壳钢丝都损坏':condition.excType==0?'施封码未注册':condition.excType==1?'解封码未注册':condition.excType==2?'签封未施封':''}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input"> 
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="exctype">
              				<div class="searchable-select-item hover" data-value=""></div>
              			   <div class="searchable-select-item  ${condition.excType==5?'selected':''}" data-value="5">外壳有损坏</div>
              			   <div class="searchable-select-item  ${condition.excType==10?'selected':''}" data-value="10">钢丝有损坏</div>
              			   <div class="searchable-select-item  ${condition.excType==13?'selected':''}" data-value="13">外壳钢丝都损坏</div>
              			   <div class="searchable-select-item  ${condition.excType==0?'selected':''}" data-value="0">施封码未注册</div>
              			   <div class="searchable-select-item  ${condition.excType==1?'selected':''}" data-value="1">解封码未注册</div>
				   		   <div class="searchable-select-item  ${condition.excType==2?'selected':''}" data-value="2">签封未施封</div>
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
                  <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="javascript:searchExcRecord()">
			         <i class="fa fa-search"> 查  询</i>
			      </button></span>
             </div>              
             </div>
         </div>
          
          <!-- 新增表格 -->
             <div class="widget orange" id="divcontent1">
               <div class="widget-title">
                   <!-- <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 未处理记录</h4> -->                   
                 <a href="javascript:;" class="a-btn" id="export">
                  <i class="fa fa-print"></i><span class="text" data-type="xls" id="tableExcelbtn" style="border:none;">导出Excel</span>
                 </a>
               </div>
               <div class="widget-body" style="overflow:auto;height:64em;">
                 <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">
	            <thead>
	              <tr>
	               <th style="border-left:none;border-right:none;text-align:right;width:90px">车辆信息</th>
	               <th style="border-left:none"></th>
	               <th style="border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;">施封信息</th>	        
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none"></th>
	               <th style="border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none;border-right:none;"></th> 
	               <th style="border-left:none;border-right:none;">解封信息</th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>
	               <s:iterator value="#session.ADMIN_POWER">
		              <s:if test="poUrl=='excRecord_change'">
	               <th style="vertical-align: bottom;padding-bottom: 0px;border-bottom:none;">操作</th>	
	                  </s:if> 
	               </s:iterator>			                           
              </tr>
              <tr>
	               <th style="border-top:1px solid #ccc;">车牌号</th>
	               <th style="border-top:1px solid #ccc;">所属公司</th>
	               <th style="border-top:1px solid #ccc;">施封人</th>
		           <th style="border-top:1px solid #ccc;">施封点</th>
		           <th style="border-top:1px solid #ccc;">施封时间</th>
	               <th style="border-top:1px solid #ccc;">施封内码</th>
	               <th style="border-top:1px solid #ccc;">施封图片</th>
	               <th style="border-top:1px solid #ccc;">解封人</th>
	               <th style="border-top:1px solid #ccc;">解封点</th>
	               <th style="border-top:1px solid #ccc;">解封时间</th>
	               <th style="border-top:1px solid #ccc;">解封外码</th>
	               <th style="border-top:1px solid #ccc;">解封图片</th>
	               <th style="border-top:1px solid #ccc;">异常类型</th>
	               <s:iterator value="#session.ADMIN_POWER">
		              <s:if test="poUrl=='excRecord_change'">
	               		<th style="border-top:none;"></th>
	                </s:if> 
	               </s:iterator>
                </tr>
              </thead> 
	          <tbody>
	             <s:iterator value="list">
	             	<tr class="info">
	             	     <td><s:property value="sealed.freeze.car.carFlapper" /></td>
	             	     <td><s:property value="company.comName" /></td>
		                 <td><s:property value="sealed.person.perTrueName" /></td>
		                 <td><s:property value="sealed.position.posName"/> </td>
		                 <td><s:property value="sealed.seaTime"/> </td>
	             		 <td><s:property value="sealed.dimensionalBarCode.freeze_content" /></td>
	             	     <td><img id="seaphone" class="bigpic" src="<s:property value="sealed.seaPhoto"/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>
		                 <td><s:property value="sealed.freeze.person.perTrueName" /></td>
		                 <td><s:property value="sealed.freeze.position.posName"/> </td>
		                 <td><s:property value="sealed.freeze.freTime"/> </td>
	             		 <td><s:property value="sealed.freeze.dimensionalBarCode.unfreeze_content" /></td>
	             	     <td><img id="freephone" class="bigpic" src="<s:property value="sealed.freeze.frePhoto"/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>
		                 <td><s:property value="basDict.dictValue" /></td>  
		                 <s:if test="excStatus!=0">
			           	 <s:iterator value="#session.ADMIN_POWER">
		              		<s:if test="poUrl=='excRecord_change'">        
		                	 <td>
		                	 <button type="button" value="处 理" id="<s:property value="excId"/>" onclick="delexc(this);" class="excbtn btn btn-xs btn-info" style="width:80px;height:28px;">
			           		   <i class="fa fa-edit"> 处 理</i>
			          		 </button></td>
			             	</s:if>
			              </s:iterator>
			             </s:if>
	             	</tr>
	             </s:iterator>
	            </tbody>
             </table>              
           </div> 
           </div>
            <div style="display:none;" id="winSelector"></div>
           	<div id="bigView" style="display:none;position:absolute;"><img width="350" height="350" alt="" src="" onerror="this.src='common/img/error.jpg'"/></div>                                 	
           <!-- 特殊异常状态 -->  
           <div class="widget orange" id="divcontent2" style="display:block;">
               <div class="widget-title">
                   <h4 style="margin:0 auto;line-height: 36px;color:#fff; "><i class="fa fa-reorder"></i> 施解封异常记录</h4>                   
               </div>
               <div class="widget-body" style="overflow:auto;height:64em;">
                 <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">
	            <thead id="seaddiv" style="display:none;">
	              <tr>
              	   <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;">施封码异常信息</th>	        
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>	                	               
	               <s:iterator value="#session.ADMIN_POWER">
		              <s:if test="poUrl=='excRecord_illegalitychange'">
	               		<th style="vertical-align: bottom;padding-bottom: 0px;border-bottom:none;">操作</th>	
	                  </s:if> 
	               </s:iterator>			                           
              </tr>
              <tr>
              	   <th style="border-top:1px solid #ccc;">车牌号 </th>
	               <th style="border-top:1px solid #ccc;">施封人</th>
		           <th style="border-top:1px solid #ccc;">施封点</th>
		           <th style="border-top:1px solid #ccc;">施封时间</th>
	               <th style="border-top:1px solid #ccc;">施封内码</th>
	               <th style="border-top:1px solid #ccc;">施封图片</th>
	               <th style="border-top:1px solid #ccc;">处理状态</th>
	                <s:iterator value="#session.ADMIN_POWER">
		              <s:if test="poUrl=='excRecord_illegalitychange'">
	               		<th style="border-top:none;"></th>
	                </s:if> 
	               </s:iterator>
                </tr>
              </thead> 
              <thead id="freediv" style="display:none;">
              <tr>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;">解封码异常信息</th>	        
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none;border-right:none;"></th>	
	               <th style="border-left:none"></th>                	               
	               <s:iterator value="#session.ADMIN_POWER">
		              <s:if test="poUrl=='excRecord_illegalitychange'">
	               		<th style="vertical-align: bottom;padding-bottom: 0px;border-bottom:none;">操作</th>	
	                  </s:if> 
	               </s:iterator>			                           
              </tr>
              <tr>
              	   <th style="border-top:1px solid #ccc;">车牌号</th>
	               <th style="border-top:1px solid #ccc;">解封人</th>
		           <th style="border-top:1px solid #ccc;">解封点</th>
		           <th style="border-top:1px solid #ccc;">解封时间</th>
	               <th style="border-top:1px solid #ccc;">解封外码</th>
	               <th style="border-top:1px solid #ccc;">解封图片</th>
	               <th style="border-top:1px solid #ccc;">处理状态</th>
	                <s:iterator value="#session.ADMIN_POWER">
		              <s:if test="poUrl=='excRecord_illegalitychange'">
	               		<th style="border-top:none;"></th>
	                </s:if> 
	               </s:iterator>
                </tr>
              </thead> 
	          <tbody>
	              <s:iterator value="pageBean.list">
	             	<tr class="warning">
	             		 <td></td>
	             	     <td><s:property value="doName" /></td>
	             	     <td><s:property value="posiName" /></td>
		                 <td><s:property value="time" /></td>
		                 <td><s:property value="code"/> </td>
		                 <td id="seapic"><img id="seaphone" class="bigpic" src="<s:property value=""/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>
		                 <td id="freepic"><img id="freephone" class="bigpic" src="<s:property value=""/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>
		                 <td>
		                   <s:if test="status==1">未处理</s:if>
		                   <s:if test="status==0">已处理</s:if>
		                 </td>
		                 <s:if test="status != 0">
			           	 <s:iterator value="#session.ADMIN_POWER">
		              		<s:if test="poUrl=='excRecord_illegalitychange'">        
		                   <td>
		                     <button type="button" value="处 理" id="<s:property value="id"/>" onclick="delillegality(this);" class="excbtn btn btn-xs btn-info" style="width:80px;height:28px;">
			           		   <i class="fa fa-edit"> 处 理</i>
			          		 </button></td>
			             	</s:if>
			              </s:iterator>
			             </s:if>
	             	</tr>
	             </s:iterator> 	          
	            </tbody>
             </table>             
           </div>
           </div>
          <script type="text/javascript" src="js/laydate.js"></script>
          <script type="text/javascript" src="js/commbase/jquery.table2excel.min.js"></script>
          <script type="text/javascript">
          !function(){
            	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
            	laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
            	laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
            }();
   			$("#export").on('click', function(e){
	   			$("#tableExcel").table2excel({
	   			  // 导出的Excel文档的名称
	   			  name: "Excel Document Name",
	   			  // Excel文件的名称
	   			  filename: "异常记录"
	   			});
   			});
	      </script> 
           <script type="text/javascript" src="js/commSearch.js"></script>
           <script type="text/javascript" src="js/findCarList.js"></script>
           <script type="text/javascript" src="js/newcommpos.js"></script>  
           <script type="text/javascript" src="js/bigpic.js"></script>
           <script type="text/javascript" src="js/searchsuosou.js"></script>          
           <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
           <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
           <div class="table-bottom">
            <s:if test="pageBean.totalCount !=''">
             <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录，当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
             </div>
            </s:if>
             <div style="width::38%;float:right;">
                <span class="table-bottom-page">
                <s:if test="pageBean.curentPage>1">
                	<a  onclick="javascript:preJump()">上一页 </a>&nbsp;
                </s:if>
                <s:if test="pageBean.curentPage<pageBean.totalPage">
	                <a  onclick="javascript:nextJump()">下一页  </a>
                </s:if>
                <s:if test="pageBean.totalPage>1">
                	&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;" id="jumpPage"/> 页&nbsp;<a href="javascript:void(0);" onclick="jumpPage();">  跳转  </a></span>
                </s:if>
             </div>
           </div>
           
          </div>
      </div>
    </div>
    <!-- 右边部分完结-->
    <!-- 弹出层界面-->
	 <div  class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	         <span aria-hidden="true"><i class="fa fa-remove fa-2x" style="color:red;"></i></span>
	        </button>
	        <h4 class="modal-title" id="myModalLabel">异常信息处理</h4>
	      </div>
	      <div class="modal-body">
	         <form id="addformper" action="" method="" class="tabfrom-ys">
	            <div class="widget orange"> 
		            <div class="widget-body" style="overflow:auto;">
		              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
		                  <tr style="height:30px;">
		                    <td><div class="box">
		                         <div class="laytitle">请输入处理意见:</div>
                                 <textarea id="txtconment" value="" cols="145" rows="5" placeholder="字数在200字以内" name="addCar.carFlapper" style="margin-left: 2px;width:100%"></textarea>
                                 <span id="carnumtishi" style="color:red;"></span>
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
	        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel" >取消</button>
	        <button type="button" class="btn btn-primary" id="surebtn">提交</button>
	      </div>
	    </div>
	  </div>
	</div>	
	 </div>
     <!-- 特殊弹出层界面-->
      <div id="newexample" class="modal hide fade in" style="display: none; ">
		<div class="modal-header" style="padding:0px;">
			<a class="close" data-dismiss="modal">×</a>
			<h3>非法异常处理</h3>
		</div>
		<div class="modal-body">
		<h4 style="margin-top:0px;">
		  <form id="tabform" action="" method="" class="tabfrom-ys">
            <table class="bordered warptab">
               <tr style="height:30px;">
                  <strong class="laytitle">请输入处理意见</strong>
                  <td><textarea id="txtconment" value="" cols="3" rows="5" placeholder="字数在200字以内" name="addCar.carFlapper" style="margin-left: 2px;width:100%"></textarea><span id="carnumtishi" style="color:red;"></span></td>
               </tr>
            </table>
         </form></h4>	        
		</div>
		<div class="modal-footer" style="padding:2px;">
			<a href="#" class="btn btn-success" id="newsurebtn">提交</a>
			<a href="#" class="btn" data-dismiss="modal" id="newcancel">取消</a>
		</div>
	 </div> 
	 <div  class="modal fade bs-example-modal-lg" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="newclose">
	         <span aria-hidden="true"><i class="fa fa-remove fa-2x" style="color:red;"></i></span>
	        </button>
	        <h4 class="modal-title" id="myModalLabel">非法异常信息处理</h4>
	      </div>
	      <div class="modal-body">
	         <form id="addformper" action="" method="" class="tabfrom-ys">
	            <div class="widget orange"> 
		            <div class="widget-body" style="overflow:auto;">
		              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
		                  <tr style="height:30px;">
		                    <td><div class="box">
		                         <div class="laytitle">请输入处理意见:</div>
                                 <textarea id="txtconment" value="" cols="145" rows="5" placeholder="字数在200字以内" name="addCar.carFlapper" style="margin-left: 2px;width:100%"></textarea>
                                 <span id="carnumtishi" style="color:red;"></span>
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
	        <button type="button"c class="btn btn-default" data-dismiss="modal" id="newcancel" >取消</button>
	        <button type="button" class="btn btn-primary" id="newsurebtn">提交</button>
	      </div>
	    </div>
	  </div>
	</div>    
      <script type="text/javascript" src="js/commbase/excsearch.js"></script>
      <script type="text/javascript" src="js/boswer.js"></script>
  </div>
</div>
</center>
  </body>
</html>
