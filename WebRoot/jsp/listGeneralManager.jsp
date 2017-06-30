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
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   	<link rel="stylesheet" type="text/css" href="admin/css/employmanament.css"/>
   	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
  	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
  	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
  	<link rel="stylesheet" type="text/css" href="common/css/searchmedia.css" />
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script type="text/javascript">
      var $104=$;
    </script>
    <script type="text/javascript" src = "js/findCompanyList.js"></script>
    <script type="text/javascript" language="javascript" src="js/jquery.autocomplete.min.js"></script>   	
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;web管理员信息		       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		    </div>	
	           <div class="panel-body">	  	              
	              <s:iterator value="#session.ADMIN_POWER">
	              		<s:if test="poUrl=='company_getListByName'">
	              			<div class="box"><span class="input-group-addon" style="display:inline;">
	              			   所属公司</span><input id="loc" class="comsty"  type="text" value="${condition.comName }" onfocus="findcom();"/>
	              		    </div>
	              		</s:if>
	              </s:iterator>
	             <input id="locid" style="width:150px;" type="hidden" value="${condition.comId }" />
	            <div class="box"><span class="input-group-addon" style="display:inline;">账号</span>
	               <input type="text" class="comsty" name="condition.name" id="name" value='<s:property value="condition.name"/>'/>
	            </div>
	            <div class="box"><span class="input-group-addon" style="display:inline;">状态</span>
              <div tabindex="5" class="searchable-select" id="stutas1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder">${condition.status==1?'流失':'正常'}</div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input">
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="status">
              			   <div class="searchable-select-item hover ${condition.status==0?'selected':''}" data-value="0">正常</div>
              			   <div class="searchable-select-item hover ${condition.status==1?'selected':''}" data-value="1">流失</div>
              			</div>
              		<div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div>
              </div>
	       		<div class="box">
                 <span style="width:80px;right:5%;float:left;">
                  <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="javascript:searchPersons();">
			         <i class="fa fa-search"> 查  询</i>
			    </button></span>
              	</div>
              	<%-- <s:iterator value="#session.ADMIN_POWER" var="p">
              		<s:if test="poUrl=='add_GeneralManager'">
	             	 	<div class="box"><span style="width:80px;right:5%;float:left;">
	             	 	  <input type="button" value="添  加" id="addbtn" class="search btn btn-primary" style="width:80px;height:28px;" /></span>              		   
              		</s:if>
              	</s:iterator> --%>
	         </div>
          </div>
          </div>
          <div class="widget orange">
            <div class="widget-title">
                <!-- <h4 style="margin:0 auto;line-height: 36px;"><i class="fa fa-reorder"></i>Web管理员信息记录</h4> -->                   
                <s:iterator value="#session.ADMIN_POWER" var="p">
              		<s:if test="poUrl=='add_GeneralManager'">
              		  <a href="javascript:;" class="a-btn" id="addbtn">
	                    <i class="fa fa-pencil"></i><span class="text">新增管理员</span>
	                  </a>	             	 	
              		</s:if>
              	</s:iterator>
            </div>
            <div class="widget-body" style="overflow:auto;height:48em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatab"> 
             <thead>
              <tr style="height:30px;"> 
                 <th>账号</th>
                 <th>姓名</th>
                 <th>所属公司</th>
                 <th>邮箱</th>
                 <th>手机号码</th>
                 <th>状态</th>
                 <s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='del_GeneralManager'">
	                 	<th>操作</th>
                 	</s:if>
                 </s:iterator>
             </tr>
             </thead>
             <tbody>
				 <s:iterator value="generalManagerlist">  
	             	<tr style="height:30px;" class="info">
	                 	<td><s:property value="admName"/></td>
	                 	<td><s:property value="admTrueName"/></td>
	                 	<td><s:property value="company.comName"/></td>
	                 	<td><s:property value="admEmail"/></td>
	                 	<td><s:property value="admPhone"/></td>
	                 	<td>${admStatus==0?"正常":"已删除"}</td>
	                 	<s:iterator value="#session.ADMIN_POWER">
              				<s:if test="poUrl=='del_GeneralManager'">
              					<s:if test="admStatus==0">
	                 			<td>
		                 		   <button type="button" value="删 除" id="<s:property value="admId" />" class="delbtnid btn btn-xs btn-info" style="width:80px;height:28px;">
							         <i class="fa fa-trash"> 删 除</i>
							       </button>
	                 			</td>
              					</s:if>
              					<s:else>
	                 				<td style="color:#ccc;cursor:pointer;display:block;font-size:13;">无操作</td>
              					</s:else>
	                 		</s:if>
                 		</s:iterator>
	            	</tr>
               </s:iterator> 
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
      </center>
    </div>     
    <!-- 右边部分完结-->
    <!-- 弹出层-->
       <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	             <span aria-hidden="true"><i class="fa fa-remove fa-2x" style="color:red;"></i></span>
	            </button>
		        <h4 class="modal-title" id="myModalLabel">添加Web员信息</h4>
		      </div>
		      <div class="modal-body">
	           <form id="addformper" action="" method="" class="tabfrom-ys">
	            <div class="widget orange"> 
		            <div class="widget-body" style="overflow:auto;">
		              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">  
		              <tr style="height:30px;">
	                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">账号</span>
	                        <input class="comsty" id="user" type="text" value="" name="admin.admName" data-toggle="tooltip" data-placement="bottom" title="用户名不能为空，且以字母开头！"/>
	                      </div>
	                     </td>
	                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">密码</span>
	                      <input id="pwd" type="password" value="" name="admin.admPassword" class="comsty" data-toggle="tooltip" data-placement="bottom" title="密码不能为空，且以字母开头！"/>
	                      </div>
	                     </td>
	                  </tr>
	                   <tr style="height:30px;">
	                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">姓名</span>
	                        <input class="comsty" id="turename" type="text" value="" name="admin.admTrueName" />
	                      </div>
	                     </td>
	                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">手机号</span>
	                      <input id="phone" type="text" value="" name="admin.admPhone" class="comsty" data-toggle="tooltip" data-placement="bottom" title="手机号不能为空！"/>
	                      </div>
	                     </td>
	                  </tr>
	                   <tr style="height:30px;">
	                    <td><div class="box"><span class="input-group-addon" style="display:inline;">邮箱</span>
	                     <input class="comsty" id="email" type="text" value="" name="admin.admEmail" data-toggle="tooltip" data-placement="bottom" title="邮箱不能为空！"/>
	                     </div>
	                    </td>
	                    <td><div class="box"><span class="input-group-addon" style="display:inline;">性别</span>
			              <div tabindex="5" class="searchable-select" id="selopt1">
			              	<span class="searchable-select-caret"></span>
			              	<div class="searchable-select-holder">男</div>
			              	<div class="searchable-select-dropdown searchable-select-hide">
			              		<!-- <input type="text" class="searchable-select-input"> -->
			              		<div class="searchable-scroll">
			              		  <div class="searchable-has-privious searchable-select-hide">...</div>
			              			<div class="searchable-select-items"  id="selopt">
			              			   <div class="searchable-select-item  selected" data-value="1" name="admin.admSex">男</div>
			              			   <div class="searchable-select-item  " data-value="0" name="admin.admSex">女</div>
			              			</div>
			              		<div class="searchable-has-next searchable-select-hide">...</div>
			              		</div>
			              	</div>
			              </div>
			              </div> 
						</td>
	                 </tr>
	                 <tr>
	                   <td><div class="box"><span class="input-group-addon" style="display:inline;">所属公司</span>
	                      <input class="comsty" id="locc"  type="text" value="" onfocus="findcom();"/>
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
    <input id="l" type="hidden" value="condition.comId" name="condition.comId"/> 
    <script type="text/javascript" src="js/searchsuosou.js"></script> 
    <script type="text/javascript" src="js/commbase/genersearch.js"></script>       
    <script type="text/javascript" src="js/boswer.js"></script>
  </div>
</div>
</center>
  </body>
</html>
