<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<link rel="stylesheet" type="text/css" href="positonmanage/css/posmanage.css" />
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css" />
	<link rel="stylesheet" type="text/css" href="common/css/searchmedia.css" />
	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
    <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
     <script type="text/javascript">
      var $104=$;
    </script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>  
    <script type="text/javascript" src = "js/findCompanyList.js"></script>      	
  	<script type="text/javascript"  src="js/jquery.autocomplete.min.js"></script> 
  	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
      var $182=$;
    </script> 
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script> 
    <style type="text/css">
      .delbtn{border: 1px solid #364f86;border-radius: 3px;outline: medium none;transition: all 0.3s ease-in-out 0s;color: blue;height: 1.5em;}
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;站点信息		       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		    </div>	
	           <div class="panel-body">						
	           <div class="box"><span class="input-group-addon" style="display:inline;">所属公司</span>
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
               <div class="box"><span class="input-group-addon" style="display:inline;">所属区域</span>
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
               <div class="box"><span class="input-group-addon" style="display:inline;">所属站点</span>
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
				<div class="box"><span style="width:80px;right:5%;float:left;"> 					
				 <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="javascript:searchPosition()">
		           <i class="fa fa-search"> 查  询</i>
		    	 </button></span>
				</div>
					<%-- <s:iterator value="#session.ADMIN_POWER">
	              	    <s:if test="poUrl=='position_add'">
							<div class="box"><span style="width:80px;right:5%;float:left;">
							  <input type="button" value="添加" id="addbtn" class="search btn btn-primary" style="width:80px;height:28px;" /></span>
							</div>
                    	</s:if>
              		</s:iterator> --%>
              	  </div>
				</div>				
			</div>
		   <div class="widget orange">
            <div class="widget-title">
               <!--  <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 站点信息记录</h4> -->                   
              <s:iterator value="#session.ADMIN_POWER">
                <s:if test="poUrl=='position_add'">
				 <a href="javascript:;" class="a-btn" id="addbtn">
                   <i class="fa fa-pencil"></i><span class="text">新增站点</span>
                 </a>
                </s:if>
              </s:iterator>
            </div>
             <div class="widget-body" style="overflow:auto;height:49em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatatab"> 
					<thead>
						<tr  style="height:30px;">
							<th>所属公司</th>
							<th>所属区域</th>
							<th>所属站点</th>
							<th>物理地址 </th>
							<th>芯片编号</th>
							<th>创建时间</th>
							<s:iterator value="#session.ADMIN_POWER">
		              	    	<s:if test="poUrl=='position_add'">
								    <th>操作</th>							    
	                    		</s:if>
	              			</s:iterator>
	              			<s:iterator value="#session.ADMIN_POWER">
					           	  <s:if test="poUrl=='position_del'">
					           	    <th>操作</th>
					           	  </s:if>
				               </s:iterator>
						</tr>
					</thead>
					<tbody>
					  <s:iterator value="list">
						<tr style="height:30px;" class="info">
							<td><s:property value="company.comName" /></td>
							<td><s:property value="area.area_name" /></td>
							<td><s:property value="posName" /></td>
							<td><s:property value="phoneMac" /></td>
							<td><s:property value="posCardNumber" /></td>
							<td><s:property value="posDate" /></td>
							<s:iterator value="#session.ADMIN_POWER">
              	    		  <s:if test="poUrl=='position_add'">
								<td class="xiugai">
								 <button type="button" value="编 辑" id="<s:property value="posId" />" class="upbtn btn btn-xs btn-info" style="width:80px;height:28px;">
						            <i class="fa fa-edit"> 编 辑</i>
						         </button><input id="posid" type="hidden" value="" /></td>										
							  </s:if>
             					</s:iterator>
             					<s:iterator value="#session.ADMIN_POWER">
				           	  <s:if test="poUrl=='position_del'&&posStatus==0">
				           	  	 <td>
				           	  	  <button type="button" value="删 除" id="<s:property value="posId"/>" onclick="javascript:delpos(this);" class="delbtn btn btn-xs btn-info" style="width:80px;height:28px;">
						           <i class="fa fa-trash"> 删 除</i>
						          </button></td>				           	  	 
				           	  </s:if>
			               </s:iterator>												      
						</tr>
					   </s:iterator>
					 </tbody>
					</table>
				  </div>
				</div>				
				<!-- 分页开始 -->
				<div class="table-bottom">
					<div style="width:60%;float:left;">
						<span style="float:left;">共有<a><s:property value="pageBean.totalCount" /></a> 条记录，当前是第<a><s:property value="pageBean.curentPage" /></a>页
						</span>
					</div>
					<div style="width::38%;float:right;">
						<span class="table-bottom-page">
						<s:if test="pageBean.curentPage>1"><a onclick="javascript:preJump()">上一页 </a>&nbsp;</s:if> 
                        <s:if test="pageBean.curentPage<pageBean.totalPage"><a onclick="javascript:nextJump()">下一页 </a></s:if>
						<s:if test="pageBean.totalPage>1">&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>'
									style="width:3em;" id="jumpPage" /> 页&nbsp;<a href="javascript:void(0);" onclick="javascript:jumpPage()">跳转 </a></span>
						</s:if>
					</div>
				</div>
				<input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
        		<input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
      			<script type="text/javascript" src="js/newcommpos.js"></script>        			
      			<script type="text/javascript" src="js/commSearch.js"></script>   
      			<script type="text/javascript" src="js/searchsuosou.js"></script>     			
				<!-- 分页结束 -->
				</div>
			  </div>
	
		  </div>
		  <!-- 右边部分完结-->
		  <!-- 添加弹出层 -->
          <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	              <span aria-hidden="true"><i class="fa fa-remove fa-2x" style="color:red;"></i></span>
	            </button>
		        <h4 class="modal-title" id="myModalLabel">添加站点信息</h4>
		      </div>
		      <div class="modal-body">
		         <form id="addformper" action="" method="" class="tabfrom-ys">
	             <div class="widget orange"> 
		            <div class="widget-body" style="overflow:auto;">
		              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">
		              <tr style="height:30px;">
	                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">所属区域</span>
	                        <input class="comsty" type="text" value="" id="newarea"/>
	                      </div>
	                     </td>
	                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">所属站点</span>
	                      <input type="text" value="" id="newposname" class="comsty" "/>
	                      </div>
	                     </td>
		              </tr>
			          <tr style="height:30px;">		                                       
		                    <td>
		                      <div class="box"><span class="input-group-addon" style="display:inline;">站点类型</span>
				             <div tabindex="5" class="searchable-select" id="positiontype1">
				              	<span class="searchable-select-caret"></span>
				              	<div class="searchable-select-holder">油库</div>
				              	<div class="searchable-select-dropdown searchable-select-hide">
				              		<!-- <input type="text" class="searchable-select-input"> -->
				              		<div class="searchable-scroll">
				              		  <div class="searchable-has-privious searchable-select-hide">...</div>
				              			<div class="searchable-select-items"  id="positiontype" >
				              			   <div class="searchable-select-item  selected" name="dictId" data-value="2">油库</div>
				              			   <div class="searchable-select-item  "  name="dictId" data-value="1">油站</div>
				              			</div>
				              		  <div class="searchable-has-next searchable-select-hide">...</div>
				              		</div>
				              	</div>
				              </div>
				              </div>    
		                    </td>
		                    <td><div class="box"><span class="input-group-addon" style="display:inline;">芯片编号</span>
		                     <input class="comsty" id="xpid" type="text" value="" name="cardNumber" data-toggle="tooltip" data-placement="bottom" title="芯片编号不能为空！" />
							 </div>
						  </td> 
			            </tr>   
			            <tr>
	                   <td><div class="box"><span class="input-group-addon" style="display:inline;">物理地址</span>
	                      <input class="comsty" id="phonemac" type="text"  value="" name="phonemac" />
	                      </div>
	                   </td>
	                   <td></td>
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
	     <script type="text/javascript" src="js/commbase/possearch.js"></script>
         <script type="text/javascript" src="js/boswer.js"></script>
		</div>
	</div>
</center>
</body>
</html>
