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
	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
	<link rel="stylesheet" type="text/css" href="exception/css/excmanage.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="common/css/nocommsearchmedia.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	  <script type="text/javascript">
  	   var $182=$;
  	  </script>
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
	     <div class="panel panel-info" style="margin-bottom:0px;">
		   <div class="panel-heading" style="text-align:left;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;已处理异常		       	
		      </h3>		     
		   </div>							   
		  </div>
		  <div style="clear:both;"></div>
           <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
			   <div class="panel-heading" style="padding:5px;">
			      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
			   </div>	
		     <div class="panel-body">	  	              
	          <div class="box"><span class="input-group-addon" style="display:inline;"><span>车牌号</span></span>
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
              <div class="box"><span class="input-group-addon" style="display:inline;"><span>异常类型</span></span>
              <div tabindex="5" class="searchable-select" id="exc1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.excType==5?'外壳有损坏':condition.excType==10? '钢丝有损坏':condition.excType==13?'外壳钢丝都损坏':condition.excType==0?'非法施封异常':condition.excType==1?'非法解封异常':''}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="exctype">
              				<div class="searchable-select-item " data-value=""></div>
              			   <div class="searchable-select-item  ${condition.excType==5?'selected':''}" data-value="5">外壳有损坏</div>
              			   <div class="searchable-select-item  ${condition.excType==10?'selected':''}" data-value="10">钢丝有损坏</div>
              			   <div class="searchable-select-item  ${condition.excType==13?'selected':''}" data-value="13">外壳钢丝都损坏</div>
              			     <div class="searchable-select-item  ${condition.excType==0?'selected':''}" data-value="0">非法施封异常</div>
              			   <div class="searchable-select-item  ${condition.excType==1?'selected':''}" data-value="1">非法解封异常</div>
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
               <span style="width:80px;right:5%;float:left;"><input type="button" value="查询" id="searchbtn" class="search btn btn-primary" style="height:28px;width:80px;" onclick="javascript:searchhasExcRecord()"/></span>
              </div>
             </div>  
         </div>
         <script type="text/javascript" src="js/laydate.js"></script>
         <script type="text/javascript" src="js/Blob.js"></script>
         <script type="text/javascript">
          !function(){
            	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
            	laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
            	laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
            }();
	      </script>
          <div class="widget orange" id="divcontent3">
               <div class="widget-title">
                   <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 异常信息记录</h4>                   
               </div>
               <div class="widget-body" style="overflow:auto;height:64em;">
                 <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">
	          	 <thead>
              	<tr>
	               <th style="border-left:none;border-right:none;text-align:right;width:90px">车辆信息</th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;">施封信息</th>	        
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none;border-right:none;"></th> 
	               <th style="border-left:none;border-right:none;">解封信息</th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="vertical-align: bottom;padding-bottom: 0px;border-bottom:none;">处理意见</th>
	               <th style="vertical-align: bottom;padding-bottom: 0px;border-bottom:none;">状态</th>		  		                           
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
	               <th style="border-top:none;"></th>
	               <th style="border-top:none;"></th>
                </tr>
              </thead> 
	            <tbody>
	             <s:iterator value="list">
	             	<tr class="warning">
	             	     <td><a href="excRecord_detailed?id=<s:property value='excId'/> " style="color:blue;font-size:14px;" ><s:property value="sealed.freeze.car.carFlapper" /></a></td>
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
		                 <td><s:property value="excHandleMethod"/></td>
		                 <td>
		                 	<s:if test="excStatus==0">已处理</s:if>
		                 </td>
	             	</tr>
	             </s:iterator>
	            </tbody>
             </table>
             <div style="display:none;" id="winSelector"></div>
           	<div id="bigView" style="display:none;position:absolute;"><img width="350" height="350" alt="" src="" onerror="this.src='common/img/error.jpg'"/></div>
           </div>
           </div>
            <!-- 特殊异常状态 -->  
          <div class="widget orange" id="divcontent4" style="display:none;">
               <div class="widget-title">
                   <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 异常信息记录</h4>                   
               </div>
               <div class="widget-body" style="overflow:auto;height:64em;">
                 <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">
	            <thead id="seaddiv" style="display:none;">
	              <tr>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;">非法施封信息</th>	        
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none;border-right:none;"></th>	                	               
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>			                           
              </tr>
              <tr>
                   <th style="border-top:1px solid #ccc;">车牌号</th>
	               <th style="border-top:1px solid #ccc;">施封人</th>
		           <th style="border-top:1px solid #ccc;">施封点</th>
		           <th style="border-top:1px solid #ccc;">施封时间</th>
	               <th style="border-top:1px solid #ccc;">施封内码</th>
	               <th style="border-top:1px solid #ccc;">施封图片</th>
	               <th style="border-top:1px solid #ccc;">处理状态</th>
	               <th style="border-top:1px solid #ccc;">处理意见</th>
	               <th style="border-top:1px solid #ccc;">处理人</th>
                </tr>
              </thead> 
              <thead id="freediv" style="display:none;">
              <tr>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;">非法解封信息</th>	        
	               <th style="border-left:none;border-right:none;"></th>	               
	               <th style="border-left:none;border-right:none;"></th>	                	               
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>			                           
              </tr>
              <tr>
	               <th style="border-top:1px solid #ccc;">车牌号</th>
	               <th style="border-top:1px solid #ccc;">解封人</th>
		           <th style="border-top:1px solid #ccc;">解封点</th>
		           <th style="border-top:1px solid #ccc;">解封时间</th>
	               <th style="border-top:1px solid #ccc;">解封外码</th>
	               <th style="border-top:1px solid #ccc;">解封图片</th>
	               <th style="border-top:1px solid #ccc;">处理状态</th>
	               <th style="border-top:1px solid #ccc;">处理意见</th>
	               <th style="border-top:1px solid #ccc;">处理人</th>
                </tr>
              </thead> 
	          <tbody>
	              <s:iterator value="pageBean.list">
	             	<tr class="warning">
	             	     <td></td>
	             	     <td><s:property value="doName" /></td>
	             	     <td><s:property value="posid" /></td>
		                 <td><s:property value="time" /></td>   
		                 <td><s:property value="code"/> </td>
		                 <td id="seapic"><img id="seaphone" class="bigpic" src="<s:property value=""/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>
		                 <td id="freepic"><img id="freephone" class="bigpic" src="<s:property value=""/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>		                
		                 <td>
		                   <s:if test="status==0">已处理</s:if>
		                 </td>
	             		 <td><s:property value="opinion" /></td>	             	          
		                 <td><s:property value="detialName" /></td>
	             	</tr>
	             </s:iterator> 	          
	            </tbody>
             </table>             
           </div> 
           </div>
           <script type="text/javascript" src="js/newcommpos.js"></script> 
           <script type="text/javascript" src="js/commSearch.js"></script> 
           <script type="text/javascript" src="js/findCarList.js"></script>  
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
                	&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;" id="jumpPage"/> 页&nbsp;<a href="javascript:void(0);" onclick="javascript:jumpPage()">  跳转  </a></span>
                </s:if>
             </div>
           </div>           
          </div>
      </div>
    </div>
    <!-- 右边部分完结-->
   </div>
  </div>
  </center>
   <script type="text/javascript" src="js/commbase/hasexcsearch.js"></script>
   <script type="text/javascript" src="js/boswer.js"></script>
  </body>
</html>
