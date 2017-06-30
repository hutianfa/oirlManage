<%@page import="javax.sound.midi.SysexMessage"%>
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
	<meta http-equiv="description" content="活动签封记录">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
	<link rel="stylesheet" type="text/css" href="waybill/css/waybillmanage.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>	
	<!-- <link rel="stylesheet" type="text/css" href="common/css/commsearchmedia.css"/> -->
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
	     <div class="panel panel-info" >
		   <div class="panel-heading" style="text-align:left;float:left;width:50%;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;施解封记录		       	
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
	          <div class="box">
	              <span class="input-group-addon" style="display:inline;">车牌号</span>
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
                 <div class="box"> <span class="input-group-addon" style="display:inline;"><span >所属公司</span></span>
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
             
              <div class="box"> <span class="input-group-addon" style="display:inline;"><span >所属站点</span></span>
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
			 <div class="box"> <span class="input-group-addon" style="display:inline;"><span >操作人</span></span>
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
              
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >客户类型</span></span>
              <div tabindex="4" class="searchable-select" id="tag1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.tag==71?'外购客户':'自有加油站'}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		 <input type="text" class="searchable-select-input"> 
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="tag">
              				<div class="searchable-select-item hover ${condition.tag==70?'selected':''}" data-value="70">自有加油站</div>
              			    <div class="searchable-select-item hover ${condition.tag==71?'selected':''}" data-value="71">外购客户</div>
              			</div>
              		<div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div>
              </div>  
              
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >状态</span></span>
              <div tabindex="5" class="searchable-select" id="stutas1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.status==0?'已完成':'运输中'}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input"> 
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="status">
              			   <div class="searchable-select-item hover ${condition.status==0?'selected':''}" data-value="0">已完成</div>
              			   <div class="searchable-select-item hover ${condition.status==1?'selected':''}" data-value="1">运输中</div>
              			</div>
              		<div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div>
              </div>
              
              <div class="box"><span class="input-group-addon" style="display:inline;"><span >是否授权</span></span>
              <div tabindex="5" class="searchable-select" id="isemp1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.empowerType==true?'是':'否'}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input"> 
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="isemp"> 
              			   <div class="searchable-select-item hover ${condition.empowerType==true?'selected':''}" data-value="true">是</div>
              			   <div class="searchable-select-item hover ${condition.empowerType==false?'selected':''}" data-value="false">否</div>
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
			     <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="javascript:searchWaybillList()">
			         <i class="fa fa-search"> 查  询</i>
			    </button></span>          
			  </div>
			   <input id="locid" style="width:120px;" type="hidden" value="${condition.comId }" />
               <input id="posid" style="width:120px;" type="hidden" value="${condition.posId }" />
		   </div>					   
		 </div>		          
	      <input type="hidden" value="" id="saveval"/>
	        <!-- 新增表格 -->
             <div class="widget orange">
               <div class="widget-title">
                   <!-- <h4 style="margin:0 auto;line-height:36px;color:#fff"><i class="fa fa-reorder"></i> 施解封记录</h4> -->                   
                 <a href="javascript:;" class="a-btn" id="export">
                  <i class="fa fa-print"></i><span class="text" data-type="xls" id="tableExcelbtn">导出Excel</span>
                 </a>
               </div>
               <div class="widget-body" style="overflow:auto;height:64em;">
                 <table class="table table-striped table-bordered table-advance table-hover">
                  <thead>
                  <tr>
	               <th style="border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;width:140px;">车辆信息</th>
	               <th style="border-left:none"></th>
	               <th style="border-right:none;"></th>	        
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none;border-right:none;text-align:right;width:140px;">施封信息</th>	               
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>
	               <th style="border-right:none;"></th>
	               <th style="border-left:none;border-right:none;"></th> 
	               <th style="border-left:none;border-right:none;text-align:right;width:140px;">解封信息</th>
	               <th style="border-left:none;border-right:none;"></th>
	               <th style="border-left:none"></th>
	               <th style="vertical-align: bottom;padding-bottom: 0px;">运输时长</th>               
	               <th style="vertical-align: bottom;padding-bottom: 0px;">状态</th>
	               <th style="vertical-align: bottom;padding-bottom: 0px;">客户类型</th>
	               <th style="border-left:none;border-right:none;text-align:right;width:140px;">授权信息</th> 
	               <th style="border-left:none"></th>              
	              </tr>
	              <tr>
	               <th style="border-top:1px solid #ccc;">车牌号</th>
	               <th style="border-top:1px solid #ccc;">签封口数</th>
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
	               <th style=""></th>
	               <th style=""></th>
	               <th style=""></th>
	               <th style="border-top:1px solid #ccc;">授权人</th>
	               <th style="border-top:1px solid #ccc;">授权原因</th>
	               <!-- <th style="border-top:1px solid #ccc;">是否授权</th> -->
                 </tr>
                </thead>
               <tbody>                            
				<s:iterator value="list">
	             <tr style="height:30px;" class="info">
	                 <td><a href="waybill_detailed?id=${seaId}" style="font-size:15px;color:blue;cursor:pointer;display:block;"><s:property value="car.carFlapper"/></a></td>
	             	 <td><s:property value="car.CarUnFixFlag"/></td>
	                 <td><s:property value="company.comName"/></td>
	                 <td><s:property value="person.perTrueName"/></td>
	                 <td><s:property value="position.posName"/></td>
	             	 <td><s:property value="seaTime"/></td>
	                 <td><s:property value="dimensionalBarCode.freeze_content"/></td>
	                 <td><img id="seaphone" class="bigpic" src="<s:property value="seaPhoto"/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>
	                 <td><s:property value="freeze.person.perTrueName"/></td>
	                 <td><s:property value="freeze.position.posName"/></td>
	             	 <td><s:property value="freeze.freTime"/></td>    	
	                 <td><s:property value="dimensionalBarCode.unfreeze_content"/></td>
	                 <td><img id="freephone" class="bigpic" src="<s:property value="freeze.frePhoto"/>" onerror="this.src='common/img/error.jpg'" width="30" height="30"/></td>	                  
	                 <td><%
		             		DecimalFormat df = new DecimalFormat("0.00");
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					        if("0".equals(request.getAttribute("seaStatus").toString()) || "2".equals(request.getAttribute("seaStatus").toString())){
					    	 out.print(df.format((sdf.parse(request.getAttribute("freeze.freTime").toString()).getTime() - sdf.parse(request.getAttribute("seaTime").toString()).getTime())/(1000*60*60.0))+"小时"); 
					        }else{
					        	out.print("");
					        }
					    %>
					</td>					
	                <td>
	                     <s:if test="seaStatus==0">已完成</s:if>
                         <s:if test="seaStatus==1">运输中</s:if>
                     </td>
                     <td> <s:if test="tag==70">自有加油站</s:if>
                         <s:if test="tag==71">外购客户</s:if></td>
                     <td><s:property value="freeze.powCodName"/></td>
	                 <td>
	                 	 <s:if test="freeze.powerTips==72">误操作</s:if>
	                     <s:if test="freeze.powerTips==73">操作不合格</s:if>
	                 </td>	          	 
	             </tr>
             	</s:iterator>
              </tbody>
            </table>
      	  </div>
	    </div>
	    
        <div class="table-conment" style="height:auto;">              
          <!-- 分页 -->
           <div class="table-bottom" >
             <s:if test="pageBean.totalCount !=''">
              <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录,共有<a><s:property value="pageBean.totalPage"/></a> 页,当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
             </div> 
             </s:if>
             <div style="width::38%;float:right;">
	             <ul class="pager" style="margin:0px;">
					 <s:if test="pageBean.curentPage>1">
					   <li><a onclick="javascript:preJump()" style="padding:1px 14px;">上一页 </a></li>
					  </s:if>
					   <s:if test="pageBean.curentPage<pageBean.totalPage">
					  	<li><a onclick="javascript:nextJump()" style="padding:1px 14px;">下一页  </a></li>
					  </s:if>
					  <s:if test="pageBean.totalPage>1">
                		<li>&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;outline:none;text-align:center;border:1px solid #ccc;border-radius:10px;" id="jumpPage"/> 页&nbsp;<a onclick="jumpPage();" style="padding:1px 14px;">  跳转  </a>
               		    </li>
               		 </s:if>
				</ul>
             </div>
            <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
            <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
           </div>
          </div>  
      </div>
    </div>
    <!-- 右边部分完结-->
   </div>
    <div style="display:none;" id="winSelector"></div>
    <div id="bigView" style="display:none;position:absolute;"><img width="300" height="300" alt="" src="" onerror="this.src='common/img/error.jpg'"/></div>    
  </center>
  <!-- 分页查询 -->
     <script type="text/javascript" src="js/laydate.js"></script>
     <script type="text/javascript" src="js/Blob.js"></script>          
     <script type="text/javascript">            
        !function(){
            laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
            laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
          	laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
         }();	        
	 </script>
     <script type="text/javascript" src="js/commSearch.js"></script> 
     <script type="text/javascript" src="js/newcommpos.js"></script>
     <script type="text/javascript" src="js/findCarList.js"></script>        
     <script type="text/javascript" src="js/commbase/waybillsearch.js"></script>
	 <script type="text/javascript" src="js/bigpic.js"></script>
     <script type="text/javascript" src="js/searchsuosou.js"></script>
     <script type="text/javascript" src="js/boswer.js"></script>     
  </body>
</html>
