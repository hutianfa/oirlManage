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
	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
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
    <script type="text/javascript"  src="js/jquery.autocomplete.min.js"></script> 
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript" src = "js/findCompanyList.js"></script>
  	<script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap-modal.js"></script>
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;操作员信息		       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		    </div>	
	           <div class="panel-body">	  	              
		          <div class="box"><span class="input-group-addon" style="display:inline;">账号</span>
		             <input type="text" class="comsty" name="condition.perName" id="username" value='${condition.perUserName}' /> </span>
		          </div>
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
              <div class="box"><span class="input-group-addon" style="display:inline;">操作人</span>
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
              <div class="box">
                <span style="width:80px;right:5%;float:left;">
                <button type="button" value="查  询" id="searchbtn" class="btn btn-xs btn-primary" style="width:80px;height:28px;" onclick="javascript:searchPersons()">
			         <i class="fa fa-search"> 查  询</i>
			    </button></span>
              </div>
              <%-- <s:iterator value="#session.ADMIN_POWER">
              	<s:if test="poUrl=='person_add'">
              		<div class="box">
             		 <span style="width:80px;right:5%;float:left;">
             		 <input type="button" value="添  加" id="addbtn" class="search btn btn-primary" style="width:80px;height:28px;" /></span>
              	    </div>
              	</s:if>
              </s:iterator> --%>
          </div>
          </div>
          <div class="widget orange">
            <div class="widget-title">
                <!-- <h4 style="margin:0 auto;line-height: 36px;color:#fff;"><i class="fa fa-reorder"></i> 操作员信息记录</h4> -->                   
              <s:iterator value="#session.ADMIN_POWER">
              	<s:if test="poUrl=='person_add'">
                  <a href="javascript:;" class="a-btn" id="addbtn">
                   <i class="fa fa-pencil"></i><span class="text">新增操作员</span>
                 </a>
               </s:if>
              </s:iterator>
            </div>
            <div class="widget-body" style="overflow:auto;height:54em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
             <thead>
              <tr  style="height:30px;"> 
                 <th>账号  </th>
                 <th>操作人</th>
                 <th>所属公司</th>
                 <th>作业点</th>
                 <th>电话号码</th>
                 <th>密码</th>
                 <th>操作</th> 
                 <s:iterator value="#session.ADMIN_POWER">
			       <s:if test="poUrl=='person_del'">
                     <th>操作</th>
                   </s:if>
	           	 </s:iterator>
              </tr>
           </thead>
            <tbody>
			<s:iterator value="list">
             	<tr style="height:30px;" class="info">
                 	<td><a href="person_detailed?id=<s:property value="perId"/> " style="color:blue;cursor:pointer;display:block;font-size:15px;"><s:property value="perName"/></a></td>
                 	<td><s:property value="perTrueName"/></td>
                 	<td><s:property value="company.comName"/></td>
                 	<td><s:property value="position.posName"/></td>
                 	<td class="iptd"><s:property value="perPhone"/></td>
                 	<td class="iptd">******</td>
                 	<td class="xiugai">
                 	  <button type="button" value="编 辑" id="<s:property value="perId" />" class="upbtn btn btn-xs btn-info" style="width:80px;height:28px;">
			            <i class="fa fa-edit"> 编 辑</i>
			          </button><input id="posid" type="hidden" value="" /></td>                	
                 	 <s:iterator value="#session.ADMIN_POWER">
			           	 <s:if test="poUrl=='person_del'&&perStatus==0">
			            	<td>
			            	   <button type="button" value="删 除" id="<s:property value="perId"/>" onclick="javascript:delper(this);" class="delbtn btn btn-xs btn-info" style="width:80px;height:28px;">
						         <i class="fa fa-trash"> 删 除</i>
						       </button>
			            	<%-- <input type="button" value="删除" class="delbtn" id="<s:property value="perId"/>" onclick="javascript:delper(this);" class="search" style="height:1.8em;"/> --%></td>
			           	 </s:if>
	           	     </s:iterator>
            	</tr>
           	</s:iterator>
            </tbody>
           </table>  
           </div> 
           </div>
           <script type="text/javascript" src="js/newcommpos.js"></script> 
           <script type="text/javascript" src="js/searchsuosou.js"></script>
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
    <!-- 弹出层-->
        <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	             <span aria-hidden="true"><i class="fa fa-remove fa-2x" style="color:red;"></i></span>
	            </button>
		        <h4 class="modal-title" id="myModalLabel">添加操作员信息</h4>
		      </div>
		      <div class="modal-body">
		         <form id="addformper" action="" method="" class="tabfrom-ys">
		            <div class="widget orange"> 
			            <div class="widget-body" style="overflow:auto;">
			              <table class="table table-striped table-bordered table-advance table-hover" id="updatab">          
			                  <tr style="height:30px;">
			                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">账号</span>
			                        <input id="user" class="comsty" type="text"  value="" name="addPerson.perName" data-toggle="tooltip" data-placement="bottom" title="用户名不能为空，且以字母开头！"/>
			                      </div>
			                     </td>
			                    <td> <div class="box"><span class="input-group-addon" style="display:inline;">密码</span>
			                      <input id="pwd" type="password" class="comsty" value="" name="addPerson.perPassword" data-toggle="tooltip" data-placement="bottom" title="密码不能为空，且以字母开头！"/>
			                      </div>
			                     </td>
			                  </tr>
			                  <tr style="height:30px;">
			                    <td><div class="box"><span class="input-group-addon" style="display:inline;">姓名</span>
			                      <input id="turename" type="text" class="comsty" value="" name="addPerson.perTrueName" data-toggle="tooltip" data-placement="bottom" title="姓名不能为空！"/>
			                      </div>
			                      </td>
			                    <td>
			                     <div class="box"><span class="input-group-addon" style="display:inline;">操作人</span>
					              <div tabindex="5" class="searchable-select" id="worktype1">
					              	<span class="searchable-select-caret"></span>
					              	<div class="searchable-select-holder">施封人员</div>
					              	<div class="searchable-select-dropdown searchable-select-hide">
					              		<!-- <input type="text" class="searchable-select-input"> -->
					              		<div class="searchable-scroll">
					              		  <div class="searchable-has-privious searchable-select-hide">...</div>
					              			<div class="searchable-select-items"  id="worktype">
					              			   <div class="searchable-select-item  selected" data-value="1" name="addPerson.basDict.dictId">施封人员</div>
					              			   <div class="searchable-select-item  " data-value="0" name="addPerson.basDict.dictId">解封人员</div>
					              			   <div class="searchable-select-item  " data-value="1" name="addPerson.basDict.dictId">施解封人员</div>
					              			   <div class="searchable-select-item  " data-value="0" name="addPerson.basDict.dictId">解封人员</div>
					              			   <div class="searchable-select-item  " data-value="1" name="addPerson.basDict.dictId">固定封签解封人员</div>
					              			   <div class="searchable-select-item  " data-value="0" name="addPerson.basDict.dictId">App管理员</div>
					              			</div>
					              		<div class="searchable-has-next searchable-select-hide">...</div>
					              		</div>
					              	</div>
					              </div>
					              </div>                        
			                    </td>
			                 </tr>
			                 <tr style="height:30px;">
			                    <td><div class="box"><span class="input-group-addon" style="display:inline;">员工编号</span>
			                     <input id="userid" class="comsty" type="text" value="" name="addPerson.perCode" />
								 </div>
								</td>                    
			                    <td>
			                      <div class="box"><span class="input-group-addon" style="display:inline;">性别</span>
					              <div tabindex="5" class="searchable-select" id="sex11">
					              	<span class="searchable-select-caret"></span>
					              	<div class="searchable-select-holder">男</div>
					              	<div class="searchable-select-dropdown searchable-select-hide">
					              		<!-- <input type="text" class="searchable-select-input"> -->
					              		<div class="searchable-scroll">
					              		  <div class="searchable-has-privious searchable-select-hide">...</div>
					              			<div class="searchable-select-items"  id="sex1">
					              			   <div class="searchable-select-item  selected" data-value="1" name="addPerson.perSex">男</div>
					              			   <div class="searchable-select-item  " data-value="0" name="addPerson.perSex">女</div>
					              			</div>
					              		<div class="searchable-has-next searchable-select-hide">...</div>
					              		</div>
					              	</div>
					              </div>
					              </div>    
			                    </td>
			                 </tr>
			                 <tr style="height:30px;">
			                    <td><div class="box"><span class="input-group-addon" style="display:inline;">电话号码</span>
			                     <input id="phone" class="comsty" type="text" value="" name="addPerson.perPhone" data-toggle="tooltip" data-placement="bottom" title="电话号码不能为空！"/>
			                     </div>
			                     </td>
			                    <td><div class="box"><span class="input-group-addon" style="display:inline;">权限类别</span>
					              <div tabindex="5" class="searchable-select" id="qtype1">
					              	<span class="searchable-select-caret"></span>
					              	<div class="searchable-select-holder">操作</div>
					              	<div class="searchable-select-dropdown searchable-select-hide">
					              		<!-- <input type="text" class="searchable-select-input"> -->
					              		<div class="searchable-scroll">
					              		  <div class="searchable-has-privious searchable-select-hide">...</div>
					              			<div class="searchable-select-items"  id="qtype">
					              			   <div class="searchable-select-item  selected" data-value="0" name="addPerson.perEmail">操作</div>
					              			   <div class="searchable-select-item  " data-value="1" name="addPerson.perEmail">管理员</div>
					              			</div>
					              		<div class="searchable-has-next searchable-select-hide">...</div>
					              		</div>
					              	</div>
					              </div>
					              </div>   
								</td>
			                 </tr>                 
			                  <tr>
			                   <td><div class="box"><span class="input-group-addon" style="display:inline;">作业点</span>
			                      <input id="loccc" class="comsty" style="width:130px;" type="text" value="" name="addPerson.position.posId"/>
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
    <script type="text/javascript" src="js/commbase/persearch.js"></script>
    <script type="text/javascript" src="js/boswer.js"></script>
  </div>
</div>
</center>
  </body>
</html>
