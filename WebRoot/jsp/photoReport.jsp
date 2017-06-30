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
	<link rel="stylesheet" type="text/css" href="waybill/css/waybillmanage.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts-3d.src.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/modules/exporting.js"></script>
    
    
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript" src = "js/findCompanyList.js"></script>
     <style>
       .opaly{width:100%;height:265px;border:1px solid #ccc;display:none;margin-top:10px;}
       #personreport{width:100%;height:255px;}
     </style>
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
           &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 操作次数报表
        </div>
      </div> 
      <div class="rig-down-all">
         <div class="table-title" style="line-height:4em;">
           <div class="consearch" style="width:82%;float:left;text-align:left;*margin-top:12px;vertical-align:middle;">
              <span class="demo1">开始时间：<input class="laydate-icon" id="startDate" value="${condition.startDate}"> </span>
              <span class="demo1">结束时间：<input class="laydate-icon" id="endDate" value="${condition.endDate}"> </span> 
             
             <s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='company_getListByName'">
	                  <span>所属公司：<input id="loc" style="width:150px;" type="text"  onfocus="findcom();" value="${condition.comName }"/></span>  
             	  </s:if>
              </s:iterator> 
               <input id="locid" style="width:150px;" type="hidden"   value="${condition.comId }"/>
            </div>
            <div class="extdiv" style="width:17%;float:right;text-align:left;*margin-top:1em;vertical-align:middle;">             
                <span ><input type="button" value="查询" id="searchbtn" class="search" /></span>
                <span></span>
            </div>
          </div>
          <!-- 报表数据区 -->
         <div class="all-pic-content" style="padding-top:5px;width:98%">
              <div id="exccontainer" style=""></div>
              <div class="opaly"><div id="personreport"></div></div>
         </div>
         <!-- 分页查询 -->
         <div class="table-bottom" style="width:98%">
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
    <!-- 右边部分完结-->
  </div>
  <input type="hidden" value="" class="chuanzhi"/>
</div>
</center>
<script type="text/javascript" src="js/laydate.js"></script>
<script type="text/javascript" src="js/Blob.js"></script>
<script type="text/javascript" src="js/them.js"></script>
<script type="text/javascript"> 
 !function(){
      laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
      laydate({elem: '#startDate'});//绑定元素
      laydate({elem:'#endDate'});
   }();
</script>
<script type="text/javascript">
	var allarr=[];
	var ids,ptype,company; 
$(function () {
	var namearr= [],allpointarr=[],allbadarr=[];
	var pontotal;
	//总数据报表
   function photoaudit(url){
	 $.ajax({
	    	type:"get",
	    	url:url,
	    	cache:true,
	        dataType:"json",
	        success:function (data){
	        	var datas = eval(data);
	        	namearr=[],allpointarr=[],allbadarr=[],allarr=[];
	        	$.each(datas,function(i,item){ 
	        	   namearr.push(datas[i].perTrueName);
	        	   allpointarr.push(datas[i].allTotal);
	        	   allbadarr.push(datas[i].allBadTotal);
	        	   allarr.push({"name":datas[i].perTrueName,"type":datas[i].personType,"id":datas[i].id,"company":datas[i].company});
	        	   pontotal=datas[i].pageNum;
	            });	  
	        	pageconment(curentPage,pontotal);
	        	$('#exccontainer').highcharts({
	                title: {
	                    text: '个人操作总数与失误数统计',
	                    x: 0 //center
	                },
	                 subtitle: {
	                    text: '中石油四川公司',
	                    x: 0
	                }, 
	                xAxis: {
	                	title:{text:'操作人'},
	                    categories: namearr,    	                    
	                },
	                yAxis: {
	                    title: {
	                        text: '操作次数'
	                    },
	                    tickPositions: [0,6,7,8,9,10,11,12,13,14,15,17,20]
	                },
	                plotOptions: {                                                             
	                    series: {                                                              
	                        lineWidth: 1,                                                      
	                        point: {                                                           
	                            events: { 	                  
	                                'mouseOver': function(event) {
                                        var xval=this.category;
                                        for(var j=0;j<allarr.length;j++){
                                        	if(allarr[j].name==xval){
                                        		ids=allarr[j].id;
                                        		ptype=allarr[j].type;
                                        		company =allarr[j].company;
                                        		$(".opaly").css("display","block"); 
          	                                    personalreport(ids,ptype,xval,company);
          	                                    return;
                                        	}
                                        }
	                                  
	                                },
	                                'mouseOut':function(){
	                                	$(".opaly").css("display","block"); 
	                                }
	                            }                                                              
	                        }                                                                  
	                    }                                                                      
	                },          
	                tooltip: {
	                    /* valueSuffix: 'xx', */
	                    /* pointFormatter: function() {
	                        return this.series.name+':<b>'+this.y+'<br/></b><span >最近6个月详情如下图</span>';

	                    } */
	                },
	                legend: {
	                    layout: 'vertical',
	                    align: 'right',
	                    verticalAlign: 'middle',
	                    borderWidth: 0
	                },
	                series: [{
	                    name: '总操作次数',
	                    data: allpointarr
	                }, {
	                    name: '失误操作次数',
	                    data: allbadarr
	                }]
	            });
	        }
	  });
	 
	}
	 var timearr=[];
	 //单个操作人数据报表
	 function personalreport(objid,objtype,xname,company){
		 $.ajax({
		    	type:"get",
		    	url:"photo_photoPersonReport?person.perId="+objid+"&person.basDict.dictId="+objtype,
		    	cache:true,
		        dataType:"json",
		        success:function (data){
		        	var datas = eval(data);
		        	timearr=[],allpointarr=[],allbadarr=[];
		        	$.each(datas,function(i,item){ 
		        	   timearr.push(datas[i].monthNum);
		        	   allpointarr.push(datas[i].allTotal);
		        	   allbadarr.push(datas[i].allBadTotal);  
		            });
		        	$('#personreport').highcharts({
		                title: {
		                    text: xname+'的详细记录',
		                    x: 0 //center
		                },
		                 subtitle: {
		                    text: company,
		                    x: 0
		                }, 
		                xAxis: {
		                	title:{text:'时间'},
		                    categories: timearr,    
		                },
		                yAxis: {
		                    title: {
		                        text: '操作次数'
		                    },
		                    tickPositions: [0,6,7,8,9,10,11,12,13]
		                },
		                plotOptions: {                                                             
		                    series: {                                                              
		                        lineWidth: 1,                                                      
		                        point: {                                                           
		                            events: { 	                  
		                                'mouseOver': function(event) {
		                                },
		                                'mouseOut':function(){	
		                                }
		                            }                                                              
		                        }                                                                  
		                    }                                                                      
		                },          
		                tooltip: {
		                    valueSuffix: ''		                    
		                },
		                legend: {
		                    layout: 'vertical',
		                    align: 'right',
		                    verticalAlign: 'middle',
		                    borderWidth: 0
		                },
		                series: [{
		                    name: '总操作次数',
		                    data: allpointarr
		                }, {
		                    name: '失误操作次数',
		                    data: allbadarr
		                }]
		            });
		        }
		 
		});
	 }	
	   //查询操作
       var starttime,endtime,comid,jumpurl,curentPage=1,totalpage,pagehtml,name,locid;
   	     starttime= $("#startDate").val();
		 endtime = $("#endDate").val();
		 name=$("#loc").val();
		 locid=$("#locid").val();
		 comid=txtid;
		 if(comid !="" && comid != null){
			 jumpurl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+comid+"&condition.currentPage=";
		 }else{
		     jumpurl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+locid+"&condition.currentPage=";
		 } 
		 window.onload=function(){photoaudit(jumpurl);}
		$("#searchbtn").click(function(){
			 var purl;
			 starttime= $("#startDate").val();
			 endtime = $("#endDate").val();
			 comid=txtid;
			if(comid !="" && comid != null){
				 purl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+comid+"&condition.currentPage=";
			 }else{
			    purl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+locid+"&condition.currentPage=";
			 }
			photoaudit(purl);
		});	
		//下一页
		$("#next").live("click",function(){
			 comid=txtid;
			 curentPage = parseInt(curentPage)+1;
			 $("#curpage").text(curentPage); 
			 pageconment(curentPage,totalpage);
			 jumpurl= jumpurl+curentPage;
			if(comid !="" && comid != null){
				 jumpurl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+comid+"&condition.currentPage=";
			 }else{
			     jumpurl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+locid+"&condition.currentPage=";
			 }
			 photoaudit(jumpurl);
		}); 
		//上一页
		$("#pre").live("click",function(){
			comid=txtid;
			curentPage = curentPage -1;  
			$("#curpage").text(curentPage);
			pageconment(curentPage,totalpage);
			jumpurl= jumpurl+curentPage;
			if(comid !="" && comid != null){
				 jumpurl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+comid+"&condition.currentPage=";
			 }else{
			    jumpurl="photo_photoReport?condition.startDate="+starttime+"&condition.endDate="+endtime+"&condition.comName="+name+"&condition.comId="+locid+"&condition.currentPage=";
			 }
			photoaudit(jumpurl);
		});
		function pageconment(curentPage,totalpage){
			$(".table-bottom-page").empty();
			if(curentPage>1){
				 pagehtml="<a id='pre' >上一页 </a>&nbsp";
				 $(".table-bottom-page").append(pagehtml);
			}
			if(curentPage<totalpage){
				 pagehtml="<a id='next' >下一页 </a>&nbsp";
				 $(".table-bottom-page").append(pagehtml);
			 }
		} 
	});
</script>
  </body>
</html>
