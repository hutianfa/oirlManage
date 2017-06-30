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
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="exception/css/excmanage.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/searchmedia.css" />
    <link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" language="javascript" src="js/jquery.autocomplete.min.js"></script> 
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
        <div class="rig-top-title" style="float:left;*height:35px;vertical-align:middle;">
           &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 时间异常信息
        </div>
      </div> 
      <center>
      <div class="rig-down-all">
          <div class="table-title" style="line-height:4em;">
            <div style="float:left;text-align:left;*margin-top:12px;vertical-align:middle;" class="seachleft">            
            	<%-- <s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='company_getListByName'"> --%>
	                  <span>公司信息：<input id="loc" style="" type="text" value="${condition.comName }" onfocus="findcom();" name=""/></span>  
             	<%--   </s:if>
                </s:iterator>  --%>
                  <input id="locid" style="width:150px;" type="hidden" value="${condition.comId }"  name=""/>
                  <span>施封作业点：<input type="text" id="seadname" value="${condition.dictName}"/></span>
                  <span>解封作业点：<input type="text" id="freename" value="${condition.staName }"/></span>
             </div>
             <div style="float:right;text-align:left;*margin-top:1em;vertical-align:middle;" class="seachrig">
               <span ><input type="button" value="查询" id="searchbtn" class="search" style="height:1.8em;" onclick="javascript:searchExcRecord()"/></span>
               <span></span>
             </div>
         </div>
          <script type="text/javascript" src="js/Blob.js"></script>
          <div class="table-conment">
           <div style="height:50em;width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
           <table class="bordered" id="tableExcel">
	           <thead>
	            <tr class="top-bordered-title" style="height:30px;">
		             <th>姓名</th>
		             <th>账号</th>
	                 <th>所属公司</th>
	                 <th>车牌号</th>
	                 <th>施封时间</th>
	                 <th>解封时间</th>
	                 <th>施封作业点</th>
	                 <th>解封作业点</th>
	                 <th>平均时间</th> 
	                 <th>实际时间</th>
	                 <th>超出百分比</th>
	             </tr>
	            </thead>
	            <tbody>
	             <s:iterator value="sealedList">
	             	<tr>
	             	 <td><s:property value="person.perTrueName"/></td>
	                 <td><s:property value="person.perName"/></td>
	                 <td><s:property value="person.company.comName"/></td>
	                 <td><s:property value="car.carFlapper"/></td>
	                 <td><s:property value="seaTime"/></td>
	                 <td><s:property value="freeze.freTime" /></td>
	                 <td><s:property value="position.posName" /></td>
	                 <td><s:property value="freeze.position.posName" /></td>
	                 <td><s:property value="avgTime"/>小时</td>
	                 <td><s:property value="actualTime" />小时</td>
	                 <td><s:property value="parcentTime" />%</td>
	             	</tr>
	             </s:iterator>
	            </tbody>
             </table>
           </div>
            <script type="text/javascript">
            //获取施封位置
            var seadname="";
            //获取解封位置
            var freename="";
             $.ajax({    
			        type:'get',        
			        url:"position_getListByName",    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){ 
			        	console.log(data);
			        	Actioncomplete(data);
			         }    
		        });   
		       function  Actioncomplete(datas){
				   $('#seadname').autocomplete(datas,{
				        max: 12,    //列表里的条目数
				        minChars: 0,    //自动完成激活之前填入的最小字符
				        width: 150,     //提示的宽度，溢出隐藏
				        scrollHeight: 300,   //提示的高度，溢出显示滚动条
				        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
				        autoFill: false,    //自动填充
				        formatItem: function(row, i, max) {
				            return  row.posId+"\" ["+row.posName+"]";
				        },
				        formatMatch: function(row, i, max) {
				            return row.posName;
				        },
				        formatResult: function(row) {
				            return row.posName;
				        }
				    }).result(function(event, row, formatted){
				          seadname = row.posName;
				    });
				$('#freename').autocomplete(datas,{
				        max: 12,    //列表里的条目数
				        minChars: 0,    //自动完成激活之前填入的最小字符
				        width: 150,     //提示的宽度，溢出隐藏
				        scrollHeight: 300,   //提示的高度，溢出显示滚动条
				        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
				        autoFill: false,    //自动填充
				        formatItem: function(row, i, max) {
				            return  row.posId+"\" ["+row.posName+"]";
				        },
				        formatMatch: function(row, i, max) {
				            return row.posName;
				        },
				        formatResult: function(row) {
				            return row.posName;
				        }
				    }).result(function(event, row, formatted){
				          freename = row.posName;
				    }); 
		        }
           		function nextJump(){
           			var curentPage=document.getElementById("curentPage").value;
           			curentPage=parseInt(curentPage)+1;
      				commJump(curentPage);
           		}
           		function preJump(){
           			var curentPage=document.getElementById("curentPage").value;
           			curentPage=curentPage-1;
           			commJump(curentPage);
           		}
           		function jumpPage(){
           			var jumpPage=document.getElementById("jumpPage").value;
           			var totalPage=document.getElementById("totalPage").value;
           			if(jumpPage>totalPage){
           				return;
           			}
           			commJump(jumpPage);
           		}
           		function commJump(curentPage){
           			var seadnameval =seadname;
           			var freenameval=freename;
           			var name=$("#loc").val();
           			var locid=$("#locid").val();
           			var companyId = txtid;
           			if(companyId !="" && companyId != null){
           				location.href="excRecord_queryExcParcent?condition.dictName="+seadnameval+"&condition.staName="+freenameval+"&condition.comName="+name+"&pageBean.curentPage="+curentPage+"&condition.comId="+companyId+"";         		
           			}else{
           			   location.href="excRecord_queryExcParcent?condition.dictName="+seadnameval+"&condition.staName="+freenameval+"&condition.comName="+name+"&condition.comId="+locid+"&pageBean.curentPage="+curentPage+"";         		
           			}
           		}
           		function searchExcRecord(){
           			var seadnameval =seadname;
           			var freenameval=freename;
           			var name=$("#loc").val();
           			var locid=$("#locid").val();
           			var companyId = txtid;
           			if(companyId !="" && companyId != null){
           				location.href="excRecord_queryExcParcent?condition.dictName="+seadnameval+"&condition.staName="+freenameval+"&condition.comName="+name+"&condition.comId="+companyId+"";
           			}else if(companyId == null && seadnameval=="" && freenameval==""  ){
           				location.href="excRecord_queryExcParcent";
           			}else{
           				name=="" ? locid="" : locid=locid;
           				location.href="excRecord_queryExcParcent?condition.dictName="+seadnameval+"&condition.staName="+freenameval+"&condition.comId="+locid+"&condition.comName="+name+"";
           			}

           	    }      		
           </script>
           <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
           <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
           <div class="table-bottom">
           <s:if test="pageBean.totalCount !=null ">
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
      </center>
    </div>
    <!-- 右边部分完结-->
  </div>
</div>
</center>
  </body>
</html>
