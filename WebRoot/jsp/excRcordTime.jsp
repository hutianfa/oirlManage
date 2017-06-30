<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="waybill/css/waybillmanage.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
	<script type="text/javascript" src="js/jquery.min.js"></script>	
	<script type="text/javascript" language="javascript" src="js/jquery.autocomplete.min.js"></script> 
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript" src = "js/findCompanyList.js"></script>
</head>
  <body>
  <center>
    <div class="main">
   <!--顶部标题-->
    <jsp:include page="/header.jsp" />
  <div class="down">
  <!-- 左侧树形菜单栏-->
     <jsp:include page="common.jsp" />
    <!-- 右侧内容-->
      <div class="downrig">
      <div class="rig-top">
        <div class="rig-top-title" style="float:left;*height:35px;vertical-align:middle;width:12em">
          &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 超时异常信息
        </div> 
      </div> 
      <center>
      <div class="rig-down-all">
         <div class="table-title" style="line-height:4em;">
           <div class="consearch" style="width:82%;float:left;text-align:left;*margin-top:12px;vertical-align:middle;">
              <s:iterator value="#session.ADMIN_POWER">
              	  <s:if test="poUrl=='company_getListByName'">
	                  <span>所属公司：<input id="loc" style="width:150px;" type="text" onfocus="findcom();" value="${condition.comName }" name="comName"/></span>  
             	  </s:if>
               </s:iterator> 
               <input id="locid" style="width:150px;" type="hidden" value="${condition.comId }" />
              <%-- <span>账号：<input type="text" value="${condition.perName}" id="perName" /></span> --%>
            </div>
            <div class="extdiv" style="width:17%;float:right;text-align:left;*margin-top:1em;vertical-align:middle;">             
                <span ><input type="button" value="查询" id="searchbtn" class="search" onclick="javascript:searchWaybillList()"/></span>
                <span></span> 
            </div>
        </div>
        <div class="table-conment">
         <div id="allcoment" style="height:50em;width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
           <table class="bordered" id="tableExcel">
           <thead>
            <tr class="top-bordered-title" style="height:30px;"> 
                 <th>车牌号</th>
                 <th>司机姓名</th>
                 <th>所属公司</th>
                 <th>作业点</th>
                 <th>施封时间</th>                 
             </tr>
             </thead>
             <tbody>
             <s:iterator value="sealedList">
	             <tr style="height:30px;">
	                 <td><s:property value="car.carFlapper"/></td>
	                 <td><s:property value=""/></td> 
	                 <td><s:property value="company.comName"/></td>
	                 <td><s:property value="position.posName" /></td>
	                 <td><s:property value="seaTime"/></td> 
	             </tr>
             </s:iterator>
             </tbody>
           </table>   
          </div>         
          <!-- 分页 -->
           <div class="table-bottom" >
              <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录，当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
             </div> 
             <div style="width::38%;float:right;">
                <span class="table-bottom-page">
                <s:if test="pageBean.curentPage>1">
                	<a onclick="javascript:preJump()">上一页 </a>&nbsp;
                </s:if>
                <s:if test="pageBean.curentPage<pageBean.totalPage">
	                <a  onclick="javascript:nextJump()">下一页  </a>
                </s:if>
                <s:if test="pageBean.totalPage>1">
                	&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;" id="jumpPage"/> 页&nbsp;<a onclick="javascript:jumpPage()">  跳转  </a></span>
                </s:if>
             </div>
            <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
           <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
           </div>
          </div>  
      </div>
      </center>
    </div>
    <!-- 右边部分完结-->
  </div>
</div>
</center>
 <!-- 分页查询 -->
     <script type="text/javascript" src="js/commSearch.js"></script>
     <script type="text/javascript">
 			//var perName=document.getElementById("perName").value;
 			var companyId = txtid;
     		var jumpurl,locid,name;
     		locid=$("#locid").val();
     		name=$("#loc").val();
     		if(companyId !="" && companyId != null){
         		jumpurl="excRecord_queryTimeOut?condition.comId="+companyId+"&condition.comName="+name+"&pageBean.curentPage=";
     		}else{
         		jumpurl="excRecord_queryTimeOut?condition.comId="+locid+"&pageBean.curentPage=";
     		}
     		function nextJump(){ commnextJump(jumpurl);}
     		function preJump(){ commpreJump(jumpurl);}
     		function jumpPage(){commjumpPage(jumpurl);}
     		function searchWaybillList(){
     			var url;
     			//var perName=document.getElementById("perName").value;condition.perName="+perName+"&
     			var companyId = txtid;
       			if(companyId !="" && companyId != null){
       				url="excRecord_queryTimeOut?condition.comId="+companyId+"";
       			}else{
       				name=="" ? locid="" : locid=locid;
       				url="excRecord_queryTimeOut?condition.comId="+locid+"";
       			}
     			commSearch(url);
     		}
     </script>
  </body>
</html>
