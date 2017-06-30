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
   	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
  	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
  	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
  	<link rel="stylesheet" type="text/css" href="common/css/ace.min.css" />
  	<link rel="stylesheet"  href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>    
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
  <body style="padding-right:0px;">
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;订单记录		       	
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
		          <div class="box"><span class="input-group-addon" style="display:inline;">订单号</span>
		             <input type="text" class="comsty" name="" id="ordername" value='' /> </span>
		          </div>                
              <div class="box">
                <span style="width:80px;right:5%;float:left;">                
                <button type="button" value="查  询" id="edute" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="searchOrder();">
			         <i class="fa fa-search"> 查  询</i>
			    </button></span>
              </div>       		  
          </div>
          </div> --%>
          <div class="widget orange">
            <div class="widget-title">
               <a href="javascript:;" class="a-btn" id="addbtn">
                  <i class="fa fa-pencil"></i><span class="text">新增订单</span>
               </a>
                <a href="javascript:;" class="a-btn" id="printbtn">
                  <i class="fa fa-print"></i><span class="text">导出订单</span>
               </a>
            </div>
            <div class="widget-body" style="overflow:auto;height:54em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
             <thead>
              <tr  style="height:30px;"> 
                 <th>订 单 号</th>
                 <th>订 单 人</th>
                 <th>订单数量</th>
                 <th>所属地址</th>
                 <th>联系电话</th>
                 <th>订单时间</th> 
                 <th>订单状态</th>
                 <!-- <th>是否核实</th>
                 <th>核实人</th> -->
                 <th class="revise">操作</th>                 
              </tr>
           </thead>
            <tbody id="ordercoment">			   
            </tbody>
           </table>  
           </div> 
           </div>
           <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
           <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
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
         </div>
       </div>
     </div>  
     <!-- 新增订单界面--弹出层-->
      <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	         <span aria-hidden="true"><i class="fa fa-remove fa-2x" style="color:red;"></i></span>
	        </button>
	        <h4 class="modal-title" id="myModalLabel">新增订单信息</h4>
	      </div>
	      <div class="modal-body">
	         <form id="addformper" action="" method="" class="tabfrom-ys">
	           <div class="widget orange"> 
		         <div class="widget-body" style="overflow:auto;height:150px;">
		           <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
		             <tr style="height:30px;">		                    
		                <td><div class="box"><span class="input-group-addon" style="display:inline;">订单数量</span>
		                      <input id="odrnum" type="text" name="num" class="comsty" value=""  data-toggle="tooltip" data-placement="bottom" title="订单数量必须是500的倍数！"/>
		                    </div>
		                </td>
		                <td>
	                       <div class="box"><span class="input-group-addon" style="display:inline;">所属地址</span>	                        
	                        <div tabindex="4" class="searchable-select" id="addres">
				              	<span class="searchable-select-caret"></span>
				              	<div class="searchable-select-holder">销售分公司</div>
				              	<div class="searchable-select-dropdown searchable-select-hide">
				              		<input type="text" class="searchable-select-input"> 
				              		<div class="searchable-scroll">
				              		  <div class="searchable-has-privious searchable-select-hide">...</div>
				              			<div class="searchable-select-items" id="adrs" style="height:60px;">
				              				<div class="searchable-select-item selected" name="address" data-value="0">销售分公司</div>
				              			   <!--  <div class="searchable-select-item " name="address" data-value="1">金江油库</div>
				              			    <div class="searchable-select-item " name="address" data-value="2">江南片区</div>
				              			    <div class="searchable-select-item " name="address" data-value="3">西城片区</div> -->
				              			</div>
				              		  <div class="searchable-has-next searchable-select-hide">...</div>
				              		</div>
				              	</div>
				              </div>
	                       </div>                       
		                 </td>
		             </tr>
		             <tr style="height:30px;">
		                 <td><div class="box"><span class="input-group-addon" style="display:inline;">订单人</span>
		                       <input id="odrname" class="comsty" name="name" type="text" name="name"  value="" data-toggle="tooltip" data-placement="bottom" title="必须填写订单人！"/>
		                      </div>
		                 </td>
		                 <td><div class="box"><span class="input-group-addon" style="display:inline;">联系电话</span>
		                      <input id="telphone" type="text" class="comsty" name="phone" value="" data-toggle="tooltip" data-placement="bottom" title="联系电话不能为空！"/>
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
	        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel" >取消下单</button>
	        <button type="button" class="btn btn-primary" id="sureaddbtn">提交订单</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!--订单详细信息界面  ---弹出层  -->
	<div class="modal fade bs-example-modal-lg" id="lookmyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	         <span aria-hidden="true"><i class="fa fa-remove fa-2x" style="color:red;"></i></span>
	        </button>
	        <h4 class="modal-title" id="myModalLabel">订单详细信息</h4>
	      </div>
	      <div class="modal-body">	         
	         <div class="dialogs" style="width: auto;" id="detailtab">
	           <%-- <div class="itemdiv1 dialogdiv">
					<div class="user1">
						<span>订单号</span>
					</div>
					<div class="body">							
						<div class="name">
							<a href="javascript:void(0)">1231255</a>
						</div>							
					</div>
				</div>
				<div class="itemdiv1 dialogdiv">
					<div class="user1">
						<span>订单人</span>
					</div>
					<div class="body">							
						<div class="name">
							<a href="javascript:void(0)">鲁经理</a>
						</div>
					</div>
				</div>
				<div class="itemdiv1 dialogdiv">
					<div class="user1">
						<span>订单数量</span>
					</div>
					<div class="body">							
						<div class="name">
							<a href="javascript:void(0)">1000</a>
						</div>														
					</div>
				</div>
				<div class="itemdiv1 dialogdiv">
					<div class="user1">
						<span>联系电话</span>
					</div>
					<div class="body">						
						<div class="name">
							<a href="javascript:void(0)">13547312602</a>								
						</div>				
					</div>
				</div>
				<div class="itemdiv1 dialogdiv">
					<div class="user1">
						<span>所属地址</span>
					</div>
					<div class="body">							
						<div class="name">
							<a href="javascript:void(0)">攀枝花市江南片区</a>
						</div>							
					</div>
				</div> --%> 
				<div class="itemdiv dialogdiv">
				    <div class="panel panel-default" >
					  <div class="panel-body" style="padding:0px 0px 0px 10px;text-align:left;color:red;">
					              运输信息
					  </div>
					   <div class="panel-footer">
					     <div class="itemdiv dialogdiv" id="delivery">
							<%-- <div class="user">
								<span>快递编号</span>
							</div>
							<div class="body">							
								<div class="name">
									<a href="javascript:void(0)"></a>
								</div>							
							</div> --%>
						</div>
					  </div>
					  <%--<div class="panel-footer">
					     <div class="itemdiv dialogdiv">
							<div class="user">
								<span>快递名称</span>
							</div>
							<div class="body">							
								<div class="name">
									<a href="javascript:void(0)"></a>
								</div>							
							</div>
						</div>
					  </div>
					  <div class="panel-footer">
					     <div class="itemdiv dialogdiv">
							<div class="user">
								<span>订单状态</span>
							</div>
							<div class="body">							
								<div class="name">
									<a href="javascript:void(0)"></a>
								</div>							
							</div>
						</div>
					  </div>						  
					  <div class="panel-footer">
					     <div class="itemdiv dialogdiv">
							<div class="user">
								<span>到达时间</span>
							</div>
							<div class="body">							
								<div class="name">
									<a href="javascript:void(0)"></a>
								</div>							
							</div>
						</div>
					  </div>
					  <div class="panel-footer">
					     <div class="itemdiv dialogdiv">
							<div class="user">
								<span>描述</span>
							</div>
							<div class="body">							
								<div class="name">
									<a href="javascript:void(0)"></a>
								</div>							
							</div>
						</div>
					  </div> --%>
					</div>
				</div>							
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal" id="canclose" >关闭</button>
	      </div>
	    </div>
	  </div>
	</div>
	<script type="text/javascript" src="js/commbase/ordsearch.js"></script>
	<script type="text/javascript" src="js/commbase/jquery.table2excel.min.js"></script>
	<script type="text/javascript" src="js/searchsuosou.js"></script>
    <script type="text/javascript" src="js/boswer.js"></script>
</center>
  </body>
</html>
