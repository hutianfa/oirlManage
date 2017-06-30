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
  <link rel="stylesheet" type="text/css" href="car/css/carmanage.css"/>
  <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
  <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
  <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript">
  	  var $104=$;
  	</script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
  	<script type="text/javascript" src = "js/findCompanyList.js"></script>
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript" language="javascript" src="js/jquery.autocomplete.min.js"></script> 
  	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
      var $182=$;
    </script> 
  </head>
  <style>
   .openlay{-moz-opacity: 0.7;-webkit-opacity:0.7;-ms-opacity:0.7;-o-opacity:0.7;opacity:.70;filter: alpha(opacity=70);background:#000;}
   .thingupdate,.save{
	    border: 1px solid #364f86;
		border-radius: 3px;
		outline: medium none;
		transition: all 0.3s ease-in-out 0s;
		color: blue;
       }
  </style>
  <body>
  <center>
  <div class="main">
   <!--顶部标题-->
    <jsp:include page="/header.jsp" />
  <div class="down">
  <!-- 左侧树形菜单栏-->
     <jsp:include page="common1.jsp" />
    <!-- 右侧内容-->
     <div class="downrig">
      <div class="rig-top">
        <div class="rig-top-title" style="float:left;width:20em;text-align:left;">
          &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 签封领用信息
        </div>
      </div> 
      <div class="rig-down-all">
          <div class="table-title" style="line-height: 4em;">
          <div style="float:left;text-align:left;*margin-top:12px;vertical-align:middle;" class="seachleft">
          		 <span class="demo1">作业点：<input class="laydate-icon" id="posname" value="${condition.posName}"> </span>
                 <s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='company_getListByName'">
              			<span>所属公司：<input id="loc" style="width:150px;" type="text" value="${condition.comName }" onfocus="findcom();"/></span>
              		</s:if>
              	 </s:iterator> 
              	 <input id="locid" style="width:150px;" type="hidden" value="${condition.comId }" />
              	 <span class="demo1">开始时间：<input class="laydate-icon" id="starttime" value="${condition.beginTime}"> </span>
                <span class="demo1">结束时间：<input class="laydate-icon" id="endtime" value="${condition.endTime}"> </span>
             </div>
             <div style="float:right;text-align:center;*margin-top:13px;vertical-align:middle;" class="seachrig">
              <span ><input type="button" value="查询" id="searchbtn" class="search" style="height:1.8em;" onclick="searchoutof()"/></span>
              <span ><input type="button" value="领用" id="addbtn" class=" search" style="height:1.8em;" /></span>
              <span></span>
              </div>          
              <input type="hidden" value="${condition.posId}" id="loid"/>
          </div>
          
          <div class="table-conment">
           <div style="height:50em;width:100%;overflow: auto;border:1px solid #aaa;border-radius:3px;">
           <table class="bordered" id="updatab">
            <thead>
	           <tr class="top-bordered-title" style="height:30px;">                  
                 <th>作业点</th>
                 <th>总数</th>
				 <th>姓名</th>
                 <th>领用时间</th>
             </tr>
             </thead>
             <tbody class="conment-code">
            
             </tbody>
           </table>
           </div>
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
            <script type="text/javascript">
            //下一页
    		$("#next").live("click",function(){
    			 curentPage = parseInt(curentPage)+1;
    			 $("#curpage").text(curentPage); 
    			 pageconment(curentPage,etotal);
    			 jumpurl= jumpurl+curentPage;    			
    			 jumpurl="thing_thingList?em.name="+name+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
    			 outofmanage(jumpurl);
    		}); 
    		//上一页
    		$("#pre").live("click",function(){
    			curentPage = curentPage -1;  
    			$("#curpage").text(curentPage);
    			pageconment(curentPage,etotal);
    			jumpurl= jumpurl+curentPage;
    			jumpurl="thing_thingList?em.name="+name+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
    			outofmanage(jumpurl);
    		});
    		function pageconment(curentPage,totalPage){
    			$(".table-bottom-page").empty();
    			if(curentPage>1){
    				 pagehtml="<a id='pre' >上一页 </a>&nbsp";
    				 $(".table-bottom-page").append(pagehtml);
    			}
    			if(curentPage<totalPage){
    				 pagehtml="<a id='next' >下一页 </a>&nbsp";
    				 $(".table-bottom-page").append(pagehtml);
    			 }
    		} 
    		//查询
        	function searchempower(){
       			name= $("#name").val();
       			var url="thing_thingList?em.name="+name+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage=1";           			
       			outofmanage(url);
       		}
    	  var name,starttime,endtime;
          var tempurl="thing_thingList?condition.beginTime="+starttime+"&condition.endTime="+endtime+"&currentPage="+curentPage+"";
       	  window.onload=function(){outofmanage(tempurl);}
       	  function outofmanage(url){
            	$.ajax({
        	    	type:"get",
        	    	url:url,
        	    	cache:true,
        	        dataType:"json",
        	        success:function (data){    
        	        	var datas = eval(data);
        	        	 $.each(datas,function(i,item){ 
        	        	    var pichtml="<tr style='height:30px;'>";
        	        	        pichtml+= "<td >"+datas[i].position+"</td>";
        	        	        pichtml+= "<td >"+datas[i].inNum+"</td>" ; 
        	        	        pichtml+= "<td >"+datas[i].perName+"</td>";
        	        	        pichtml+= "<td >"+datas[i].time+"</td>" ; 
        	        	        pichtml+="</tr>";
        	        	 $(".conment-code").append(pichtml);
        	        	 var pagetotal=datas[i].pageNum;
        	        	 pageconment(curentPage,pagetotal);
        	        	}); 
        	       }
               });
            }
           </script>
           <!-- 分页结束 -->
          </div>
      </div>
    </div> 
        <!-- 弹出层界面-->
     <div class="openlay"></div>
          <div id="win" class="win" style="">
           <div class="title">
                <div class="title_left">
                   <div class="laytitle" style="text-align:left;padding-left:10px">添加车辆信息</div>
                </div>
                <div class="title_right"><span id="close">×</span></div>
            </div>
            <div class="content">
            <form id="tabform" action="" method="" class="tabfrom-ys">
              <table class="bordered warptab">
                 <tr style="height:30px;">
                    <td>作业点：<input type="text" value="${em.position}" id="locc" /><span id="carnumtishi" style="color:red;"></span></td>
                 	<td> 领用数量：<input type="text" value="${em.inNum}" id="innum" /><span id="actnumtishi" style="color:red;"></span></td>
                 </tr>                 
           </table>
              <div class="btndiv" style="margin-top:1em;" >
              <span>
                <input type="button" value="确定" id="surebtn" class="search" style="height:2em;margin-right:2em;" />
                <input type="button" value="取消" id="cancel" class="search" style="height:2em;"/></span><br/>
                <span id="alltishi" style="color:red"></span>
              </div>
           </form>
           </div>
        </div>     
     <script type="text/javascript">
     $("#addbtn").click(function(){
		  $(".openlay").css({height:$(window).height(),'min-width':'1160px'});
		  if(navigator.userAgent.indexOf("MSIE") >=0){
			    $("#win").css( { _position : 'absolute','_top' : '30%' , '_left' : '40%','min-width':'300px'} ); 
			    $("#win").css("display","block");  
				$(".openlay").css("display","block");
		  }else{
				var top = ($(window).height() - $("#win").height())/2;   
				var left = ($(window).width() - $("#win").width())/2; 
				$("#win").css( { position : 'absolute','top' : top , left : left,'min-width':'300px'} );
				$("#win").css("display","block");  
				$(".openlay").css("display","block");
		  }
	  });
	   $("#close").click(function(){
		  $(".openlay").css("display","none");
		  $("#win").css("display","none");
		 });
	  $("#cancel").click(function(){
		  $(".openlay").css("display","none");
		  $("#win").css("display","none");
	  });
      var oid;
	  $.ajax({    
	        type:'get',        
	        url:"position_getListByName",    
	        cache:false,    
	        dataType:'json',    
	        success:function(data){ 
	        	Actioncomplete(data);
	         }    
       }); 
      function  Actioncomplete(datas){
		   $104('#locc').autocomplete(datas,{
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
		            return row.posName+row.posId;
		        },
		        formatResult: function(row) {
		            return row.posName;
		        }
		    }).result(function(event, row, formatted){
		          //seadname = row.posName;
		          oid=row.posId;
		          $("#loid").val(oid);
		    });
         }
	  $(function(){
	    	 function message(text,newdata) {
	    		   var newval= newdata;
	    	       $("#spanmessage").text(text);
	    	       $104("#message").dialog({
	    	           title:"物流运输云平台，提示您！",
	    	           modal: true,
	    	           buttons: {
	    	               "确定": function(newdata) {
	    	            	   if(newval ==1 ){
	    	            		   $104(this).dialog("close");
	    	            		   location.href="thing_Allthing";
	    	            	   }else{
	    	            		   $104(this).dialog("close");  
	    	            	   }  
	    	               }
	    	           }
	    	       });
	    	   }
	       	 var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
	     	     $(document.body).append(boardDiv);   
	       		 //添加
	     	    $("#surebtn").click(function(){  
	     	  		  var innum =$("#innum").val();
	     	  		  var locid=$("#loid").val();
	     	  		  if( innum =="" || innum == null){
	     	  			  $("#alltishi").html("入库数量不能为空");
	     	  		  }
	     	  		  else if(locid =="" ||locid == null){
	     	  			  $("#alltishi").html("作业点不能为空！");
	     	  		  }
	     	  		  else{  
		     	  			$(".openlay").css("display","none");
	     			        $("#win").css("display","none"); 
	     			   		$.post("thing_thingAdd",{"th.inNum":innum,"th.position":locid},function(data){  
	     			        	    	   if(data==1){
	     						   				message("领用成功！",data);
	     						   			    $("#alltishi").html("");
	     						   			 }else{
	     						   				message("领用失败！",data);
	     						   			    $("#alltishi").html("");
	     						   			 } 
	     			        	 }); 
	     			   	$("#alltishi").html("");
	     	  		  }
	     	  	});
		});
    </script>
  </div>
</div>
</center>
  </body>
</html>
