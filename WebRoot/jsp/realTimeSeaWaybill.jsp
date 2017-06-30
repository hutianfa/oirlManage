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
	
	<link rel="stylesheet" type="text/css" href="common/css/indexreal.css">
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src = "js/findCompanyList.js"></script>
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript" language="javascript" src="js/jquery.autocomplete.min.js"></script>
</head>
  <style type="text/css">.seasty{color:red;}.freesty{color:blue;}</style>
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
        <div class="rig-top-title" style="float:left;*height:35px;vertical-align:middle;width:15em;">
          &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 实时运单信息管理
        </div>
      </div> 
      <center>
      <div class="rig-down-all">
         <div class="table-title" style="line-height:4em;">
           <div class="record_Top"><span><label id="companyname"></label><strong>实时运单信息</strong></span></div>
          <s:iterator value="#session.ADMIN_POWER">
              <s:if test="poUrl=='company_getListByName'">
					
           			<div class="record_left"><span>公司名称：<input type="text" value="" id="loc" onfocus="findcom();"/></span><span><input type="button" value="查询" style="width:50px" id="sss" onclick="recover();" /></span></span></div> 
           	  </s:if>
         </s:iterator> 
         
      
          
         </div>
      <div class="table-conment">
         <div id="allcoment" style="width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
            <div class="topRec_List">
		  		<dl>
		        	<dd>二维码</dd>
		        	<dd>操作员</dd>
		        	<dd>真实姓名</dd>
		        	<dd>公司名称</dd>
		        	<dd>时间</dd>
		        	<dd>车牌号</dd>
		        	<dd>状态</dd>
		        	<dd>位置</dd>
		        </dl>
		        <div class="maquee">
		            <ul></ul>
		        </div> 
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
 <script type="text/javascript"> 
 	var idd; 
	  function autoScroll(obj,id){ 
		  var urll;
		  if(id != null && id !=""){
			  d = id;
		  }else{
			  d = "";
		  }
		     $.ajax({
		    	type:"get",
		    	url:"waybill_seaJson?condition.comId="+d,
		    	cache:true,
		        dataType:"json",
		        success:function (data){
		        	var datas = eval(data);
		        	$(".maquee ul li").remove();
		        	$.each(datas,function(i,item){ 
		        		var pertruename="",posname="",stute="",pername="",time="";
		        		if(datas[i].seaStatus !=null || datas[i].seaStatus !=""){	
		        			if(datas[i].seaTime =="" || datas[i].seaTime == null){
		        				time="";
		        			}else{
		        				time=datas[i].seaTime;
		        			}
		        			if(datas[i].perName =="" || datas[i].perName==null){
		        				prename="";
		        			}else{
		        				pername=datas[i].perName;
		        			}
		        			if(datas[i].perTrueName =="" || datas[i].perTrueName== null){
			        			pertruename="";
			        		}else{
			        			pertruename=datas[i].perTrueName;
			        		}
		        			if(datas[i].posName =="" || datas[i].posName== null){
			        			posname="";
			        		}else{
			        			posname=datas[i].posName;
			        		}
		        			stute="施封";
		        			$(".maquee ul").append(
		        					 "<li>"+
		        					 "<div>"+datas[i].dimensionalBarCode+"</div>"+
		        					 "<div>"+pername+"</div>"+
		        					 "<div>"+pertruename+"</div>"+
		        					 "<div>"+datas[i].comName+"</div>"+
		        					 "<div>"+time+"</div>"+
		        					 "<div>"+datas[i].carFlapper+"</div>"+
		        					 "<div class='freesty seasty'>"+stute+"</div>"+
		        					 "<div>"+posname+"</div>"+
		        					 "</li>");
		        		}
		        		 if(datas[i].freStatus !=null || datas[i].freStatus !=""){
		        			if(datas[i].freTime =="" || datas[i].freTime == null){
		        				time="";
		        			}else{
		        				time=datas[i].freTime;
		        			}
		        			if(datas[i].frePerName =="" || datas[i].frePerName==null){
		        				prename="";
		        			}else{
		        				pername=datas[i].frePerName;
		        			}
		        			if(datas[i].freTrueName =="" || datas[i].freTrueName==null){
		        				pertruename="";
		        			}else{
		        				pertruename=datas[i].freTrueName;
		        			}
		        			if(datas[i].frePosName=="" || datas[i].frePosName==null){
		        				posname="";
		        			}else{
		        				posname=datas[i].frePosName;
		        			}
		        			stute="解封";
		        			if(posname == null || posname == ""){
		        				pertruename ="正在途中...";
		        				time ="正在途中...";
		        				posname ="正在途中...";
		        			}
		        			$(".maquee ul").append(
		        					 "<li>"+
		        					 "<div>"+datas[i].dimensionalBarCodeun+"</div>"+
		        					 "<div>"+pername+"</div>"+
		        					 "<div>"+pertruename+"</div>"+
		        					 "<div>"+datas[i].comName+"</div>"+
		        					 "<div>"+time+"</div>"+
		        					 "<div>"+datas[i].carFlapper+"</div>"+
		        					 "<div class='freesty seasty'>"+stute+"</div>"+
		        					 "<div>"+posname+"</div>"+
		        					 "</li>");
		        		} 
		        		
		        	});
		        	var arr = new Array();
		        	var divtxt=$(".maquee ul li div:nth-child(7)").text();	
		        	var result ="";
		        	for(var i=0,len=divtxt.length;i<len;i++){
		        	    result += divtxt[i];
		        	    if(i % 2 == 1) result += ',';
		        	}
		        	arr=result.split(",");
		        	for(var j=0;j<arr.length;j++){
		        		if(arr[j]=="施封"){
		        			$(".maquee ul li:eq("+j+") div:nth-child(7)").removeClass("freesty");	
		        		}else if(arr[j]=="解封"){
		        			$(".topRec_List li:eq("+j+") div:nth-child(7)").removeClass("seasty");
		        		}
		        	}
		        }
		    }); 
			$(obj).find("ul").animate({  
				marginTop : "-39px"  
			},400,function(){  
				$(this).css({marginTop : "0px"}).find("li:last-child").appendTo(this);  
			});  
		}  
		$(function(){  
			setInterval('autoScroll(".maquee",idd)',15000);
			autoScroll(".maquee",idd);
		});
		function recover(){
			idd = txtid;
			autoScroll(".maquee",idd);
		}
</script> 
  </body>
</html>
