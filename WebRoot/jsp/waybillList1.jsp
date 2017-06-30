<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>	
  		<script type="text/javascript">
  	  var $182=$;
  	</script>
	<script type="text/javascript" src="Highcharts-4.1.5/js/highcharts.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/modules/exporting.js"></script> 
    <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript">
  	  var $104=$;
  	</script>	
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript" language="javascript" src="js/jquery.autocomplete.min.js"></script> 
    <script type="text/javascript" src = "js/findCompanyList.js"></script>
    <script type="text/javascript" src = "js/findPostionList.js"></script>
    <style type="text/css">
     /*  @media screen and (min-width: 750px) and (max-width: 900px) { 
	     .rig-top-title{width:22%;}
	     .consearch span input,select{width:100px;}
	     .extdiv {width:15%;}
       }
      @media screen and (min-width: 900px) and (max-width: 1260px) { 
	     .rig-top-title{width:22%;}
	     .consearch span input,select{width:100px;}
	     .extdiv {width:15%;}
       }
	   @media screen and (min-width:1260px) and (max-width: 1600px){
	     .rig-top-title{width:17%;}
	     .consearch span input{width:120px;}
	     .extdiv {width:17%;}
	   }
	   @media screen and (min-width:1600px) and (max-width: 1920px) { 
	     .rig-top-title{width:17%;}
	     .consearch span input{width:120px;}
	     .extdiv {width:17%;}
	   } */
	    @media screen and (min-width: 480px) and (max-width: 800px) { 
	     .rig-top-title{width:15%;}
	     .consearch span input{width:100px;} 
	      #status{width:100px;} .extdiv {width:16%;}
	     table{width:135%;} .main{width:160%;}
       }
      @media screen and (min-width: 800px) and (max-width: 900px) { 
	     .rig-top-title{width:14%;}
	     .consearch span input{width:100px;} 
	      #status{width:100px;} .extdiv {width:15%;}
	     table{width:120%;} /* .main{width:150%;} */
       }
      @media screen and (min-width: 900px) and (max-width: 1260px) { 
	     .rig-top-title{width:18%;}
	     .consearch span input,select{width:110px;}
	      #status{width:110px;}.extdiv {width:15%;}
	     table{width:100%;}  .main{width:105%;} 
       }
        @media screen and (min-width:1260px) and (max-width: 1400px){
	     .rig-top-title{width:16%;}
	     .consearch span input{width:110px;}
	     .extdiv {width:14%;}  table{width:100%;}    .main{width:105%;} 
	   }
	   @media screen and (min-width:1400px) and (max-width: 1600px){
	     .rig-top-title{width:16%;}
	     .consearch span input{width:115px;}
	     .extdiv {width:14%;}  table{width:100%;}   /* .main{width:125%;} */
	   }
	   @media screen and (min-width:1600px) and (max-width: 1920px) { 
	     .rig-top-title{width:14%;}
	     .consearch span input{width:120px;}
	     .extdiv {width:15%;}  table{width:100%;} 
	   }
    </style>
</head>
  <body>
  <center>
    <div class="main">
   <!--顶部标题-->
    <jsp:include page="/header.jsp" />
  <div class="down">
  <!-- 左侧树形菜单栏-->
     <jsp:include page="common1.jsp" />
    <!-- 右侧内容-->
      <div class="downrig" style="height:auto;">
      <div class="rig-top" >
        <div class="rig-top-title" style="float:left;*height:35px;vertical-align:middle;">
          &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 活动签封记录
        </div>
        <div class="rig-top-title" style="float:right;*height:35px;text-align:left;vertical-align:middle;">
           <span id="export" style="margin-top:5px;display:inline-block;"><input type="button" data-type="xls" value="导出Excel" id="tableExcelbtn" class="search" style="width:72px;height:26px"/></span>
           <span></span>
      	</div>
      </div>  
      <center>
      <div class="rig-down-all">
          <div class="table-title" style="line-height:3em;height:auto;">
           <div class="consearch" style="width:98%;text-align:left;*margin-top:12px;vertical-align:middle;padding: 5px;">              
              <fieldset style="border: 1px solid #ccc;font-size: 14px;font-family: '微软雅黑';border-radius:3px;">
               <legend>按条件查询:</legend>
              <s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='company_getListByName'">
              			<span>所属公司：<input id="loc" type="text" value="${condition.comName }" onfocus="findcom();"/></span>
              		</s:if>
               </s:iterator>  
              <span>车牌号：<input type="text" value="${condition.carNum}" id="carid" /></span>                                         
              <span>站点：<input type="text" value="${condition.posName}" id="posName" onfocus="findpos();" /></span>                                             
              <span class="demo1">开始时间：<input class="laydate-icon" id="starttime" value="${condition.beginTime}"> </span>
              <span class="demo1">结束时间：<input class="laydate-icon" id="endtime" value="${condition.endTime}"> </span>             	
			  <span>客户类型：<select id="tag">
	                <option value="70" ${condition.tag==70?"selected":""}>自有加油站</option>
	                <option value="71" ${condition.tag==71?"selected":""}>外购客户</option>
	                </select> 
                </span>
                <span>状态：<select id="status">
	                <option value="0" ${condition.status==0?"selected":""}>已完成</option>
	                <option value="1" ${condition.status==1?"selected":""}>运输中</option>
	                </select> 
                </span>
			   <span style="width:80px;padding:0.5em;right:5%"><input type="button" value="查询" id="searchbtn" class="search" onclick="javascript:searchWaybillList()" style="width:80px;"/></span>
            </fieldset>
            </div>
            <%-- <div class="extdiv" style="float:right;text-align:left;*margin-top:1em;height: 8em;line-height: 8em;vertical-align:middle;">             
                <span ><input type="button" value="查询" id="searchbtn" class="search" onclick="javascript:searchWaybillList()"/></span>
                <span></span>               
            </div> --%>
            <input id="locid" style="width:120px;" type="hidden" value="${condition.comId }" />
            <input id="posid" style="width:120px;" type="hidden" value="${condition.posId }" />
          </div>
          <script type="text/javascript" src="js/laydate.js"></script>
          <script type="text/javascript" src="js/Blob.js"></script>
          <script type="text/javascript" src="js/FileSaver.js"></script>
          <script type="text/javascript" src="js/tableExport.js"></script>
          <script type="text/javascript" src="js/jquery.base64.js"></script>
          <script type="text/javascript"> 
	        !function(){
	            laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	            laydate({elem: '#starttime',istime: true});//绑定元素
	          	laydate({elem:'#endtime',istime: true});
	         }();
	          /* var $exportLink = document.getElementById('export');
	   		 $exportLink.addEventListener('click', function(e){
	   			e.preventDefault();
	   			if(e.target.nodeName === "INPUT"){
	   				tableExport('tableExcel', '活动签封记录', e.target.getAttribute('data-type'));
	   			}	
	   		 }, false);  */
	      </script>
	      <input type="hidden" value="" id="saveval"/>
        <div class="table-conment">
         <div id="allcoment" style="height:50em;width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
           <table class="bordered" id="tableExcel">
           <thead>
           <tr>
	               <th style="border-left:none"></th>
	               <th style="border-left:none;text-align:right;width:100px;">车辆信息</th>
	               <th style="border-left:none"></th>
	               <th style=""></th>	        
	               <th style="border-left:none;text-align:right;width:100px;">施封信息</th>	               
	               <th style="border-left:none"></th>
	               <th style=""></th>
	               <th style="border-left:none;text-align:right;width:100px;">解封信息</th>
	               <th style="border-left:none"></th>             
	               <th style="vertical-align: bottom;padding-bottom: 0px;">状态</th>
	               <th style="vertical-align: bottom;padding-bottom: 0px;">操作</th>           
              </tr>
            <tr class="top-bordered-title" style="height:30px;"> 
            	 <th style="border-top:1px solid #ccc;">车牌号</th>
                 <th style="border-top:1px solid #ccc;">签封口数</th>
                 <th style="border-top:1px solid #ccc;">所属公司</th>
                 <th style="border-top:1px solid #ccc;">施封人</th>
                 <th style="border-top:1px solid #ccc;">施封点</th>
                 <th style="border-top:1px solid #ccc;">施封时间</th>
                 <th style="border-top:1px solid #ccc;">解封人</th>
                 <th style="border-top:1px solid #ccc;">解封点</th>
                 <th style="border-top:1px solid #ccc;">解封时间</th>
                 <th></th>
                 <th></th>
             </tr>
             </thead>
             <tbody>
              <s:iterator value="list">
	             <tr style="height:30px;">
	             	 <td><s:property value="car.carFlapper"/></td>
	                 <td><s:property value="car.CarUnFixFlag"/></td>
	                 <td><s:property value="company.comName" /></td>
	             	 <td><s:property value="person.perTrueName"/></td>
	                 <td><s:property value="position.posName"/></td>
	                 <td><s:property value="seaTime"/></td>
	                 <td><s:property value="freeze.person.perTrueName"/></td>
	                 <td><s:property value="freeze.position.posName" /></td>
	                 <td><s:property value="freeze.freTime"/></td>
	                 <%-- <td><%
		             		DecimalFormat df = new DecimalFormat("0.00");
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					        if("0".equals(request.getAttribute("seaStatus").toString()) || "2".equals(request.getAttribute("seaStatus").toString())){
					    	 out.print(df.format((sdf.parse(request.getAttribute("freeze.freTime").toString()).getTime() - sdf.parse(request.getAttribute("seaTime").toString()).getTime())/(1000*60*60.0))+"小时"); 
					        }else{
					        	out.print("");
					        }
					    %>
					</td> --%>
	                <td>
	                     <s:if test="seaStatus==0">已完成</s:if>
                         <s:if test="seaStatus==1">运输中</s:if>
                         <%-- <s:if test="seaStatus==2">异常</s:if> --%>
                     </td>
	                 <td><a href="waybill_detailed?id=${seaId}" style="color:blue;cursor:pointer;display:block;">详细</a></td>
	             </tr>
             </s:iterator>
             </tbody>
           </table>   
          </div>         
          <!-- 分页 -->
           <div class="table-bottom" >
             <s:if test="pageBean.totalCount !=''">
              <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录，当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
             </div> 
             </s:if>
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
     var starttime,endtime,status,jumpurl,tag,carid,posid, opval=1,comname,locid,posname;
     /*   $("#tabledata").change(function(){
      	   opval=$("#tabledata option:selected").val();
      	  if(opval ==1){ 
      		  searchWaybillList();
      	   }else if(opval ==2){
      		  location.href="waybill_allList?condition.info="+opval+"";
      	  } 
       });  */
      comname=$("#loc").val();locid=$("#locid").val();
      carid=$("#carid").val();starttime=$("#starttime").val();	        
		endtime = $("#endtime").val();status=$("#status").val();
		posid=$("#posid").val();tag=$("#tag").val();
		posname=$("#posName").val();
		var loccc = txtid,  tposid=tempposid;
		if(loccc !="" && loccc != null ){
   		jumpurl="waybill_list?condition.posName="+posname+"&condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+posid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.comId="+loccc+"&pageBean.curentPage=";
		}else if(tposid !="" && tposid != null){
			url="waybill_list?condition.posName="+posname+"&condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+tposid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.info="+opval+"&condition.comId="+locid+"";
		}else{
			posname==""? posid="" :posid=posid;
			comname=="" ? locid="" : locid=locid;
   		jumpurl="waybill_list?condition.posName="+posname+"&condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+posid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.comId="+locid+"&condition.endTime="+endtime+"&pageBean.curentPage=";
		}
		function nextJump(){ commnextJump(jumpurl);}
		function preJump(){ commpreJump(jumpurl);}
		function jumpPage(){commjumpPage(jumpurl);}
		function searchWaybillList(){     			
			starttime=document.getElementById("starttime").value;
			endtime = document.getElementById("endtime").value;
			comname=$("#loc").val();locid=$("#locid").val();
			posname=$("#posName").val();
			status=document.getElementById("status").value;
			carid=$("#carid").val();tag=$("#tag").val();
 			var url;
 			loccc = txtid;tposid=tempposid;
 			if(loccc !="" && loccc != null ){
   			url="waybill_list?condition.posName="+posname+"&condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+posid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.info="+opval+"&condition.comId="+loccc+"";
			} else if(tposid !="" && tposid != null){
				url="waybill_list?condition.posName="+posname+"&condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+tposid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.info="+opval+"&condition.comId="+locid+"";
			}else{
				comname=="" ? locid="" : locid=locid;
				posname==""? posid="" :posid=posid;
   			url="waybill_list?condition.posName="+posname+"&condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+posid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.comId="+locid+"&condition.info="+opval+"";
			}
			commSearch(url);
		}
		// 导出数据
		$("#tableExcelbtn").click(function(){
			comname=$("#loc").val();locid=$("#locid").val();
	        carid=$("#carid").val();starttime=$("#starttime").val();	        
			endtime = $("#endtime").val();status=$("#status").val();
			posid=$("#posid").val();tag=$("#tag").val();
			var loccc = txtid,  tposid=tempposid;
			var expurl;
			if(loccc !="" && loccc != null ){
				expurl="waybill_allList?condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+posid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.info="+2+"&condition.comId="+loccc+"";
			} else if(tposid !="" && tposid != null){
				url="waybill_list?condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+tposid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.info="+opval+"&condition.comId="+locid+"";
			}else{
				posname==""? posid="" :posid=posid;
				comname=="" ? locid="" : locid=locid;
				expurl="waybill_allList?condition.comName="+comname+"&condition.tag="+tag+"&condition.carNum="+carid+"&condition.posId="+posid+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.comId="+2+"&condition.info="+opval+"";
			}
			location.href=expurl;
		});
     </script>
  </body>
</html>
