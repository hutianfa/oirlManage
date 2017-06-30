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
    <script type="text/javascript">
  	  var $182=$;
  	</script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts-3d.src.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/modules/exporting.js"></script>
    <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript">
  	  var $104=$;
  	</script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script> 
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript" src = "js/findCompanyList.js"></script>
     <style>
       .opaly{width:100%;height:265px;border:1px solid #ccc;display:none;margin-top:10px;}
       #personreport{width:100%;height:255px;}
       .openlay{-moz-opacity: 0.7;-webkit-opacity:0.7;-ms-opacity:0.7;-o-opacity:0.7;opacity:.70;filter: alpha(opacity=70);background:#000;}
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
           &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" />签封出入库月报
        </div>
      </div> 
      <div class="rig-down-all">
         <div class="table-title" style="line-height:4em;">
           <div class="consearch" style="width:82%;float:left;text-align:left;*margin-top:12px;vertical-align:middle;">
                <span><select id="conid">
                   <option value="1" selected="selected">站点</option>
                   <option value="2">操作人</option>
                   <option value="3">车辆</option>
                </select></span>
                <span></span>
            </div>
            <div class="extdiv" style="width:17%;float:right;text-align:left;*margin-top:1em;vertical-align:middle;">             
               <span ><input type="button" value="查询" id="searchbtn" class="search" style="height:1.8em;"/></span>
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
<script type="text/javascript">
$(function (){
	var chotype=$("#conid option:selected").val();
	var outnumarr= [],totalnumarr=[],surplusnumarr=[],stocknumarr=[],posarr=[],personarr=[],cararr=[];
	$("#conid").change(function(){
		chotype=$(this).find("option:selected").val();
		dayManage(url,chotype);
	});
	//总数据报表
   function dayManage(url,daytype){
	 if(daytype==1){
		 $.ajax({
		    	type:"get",
		    	url:url+daytype,
		    	cache:true,
		        dataType:"json",
		        success:function (data){
		        	var datas = eval(data);
		        	outnumarr=[],totalnumarr=[],surplusnumarr=[],stocknumarr=[],posarr=[];
		        	$.each(datas,function(i,item){ 
		        	  outnumarr.push(datas[i].outNum);
		        	  totalnumarr.push(datas[i].totalnum);
		        	  surplusnumarr.push(datas[i].surplusnum);
		        	  stocknumarr.push(datas[i].stocknum);
		        	  posarr.push(datas[i].position);
		            });	 
		        	//pageconment(curentPage,pontotal);
		        	$182('#exccontainer').highcharts({
		        		chart: {
		        	            type: 'column'
		        	    },
		                title: {
		                    text: '(站/库)数量',
		                    x: 0 //center
		                },
		                 subtitle: {
		                    text: '中石油四川公司',
		                    x: 0
		                }, 
		                xAxis: {
		                	title:{text:'作业点'},
		                    categories: posarr,    	                    
		                },
		                yAxis: {
		                    title: {
		                        text: '出库数量(K表示千)'
		                    },
		                    tickPositions: [0,1000,2300,3500,4400,5000,6300,7000,8000,12000,20000,25000]
		                },
		                plotOptions: {                                                             
		                    series: {                                                              
		                        lineWidth: 1,                                                      
		                        point: {                                                           
		                            events: { 	                   
		                            }                                                              
		                        }                                                                  
		                    }                                                                      
		                },          
		                tooltip: {
		                },
		                legend: {
		                    layout: 'vertical',
		                    align: 'right',
		                    verticalAlign: 'middle',
		                    borderWidth: 0
		                },
		                series: [{
		                    name: '总数',
		                    data: totalnumarr
		                }, {
		                    name: '使用数',
		                    data: outnumarr

		                }, {
		                    name: '剩余数',
		                    data: surplusnumarr
		                }, {
		                    name: '库存',
		                    data: stocknumarr
		                }]
		            });
		        }
		  });
	 }
	 if(daytype==2){
		 $.ajax({
		    	type:"get",
		    	url:url+daytype,
		    	cache:true,
		        dataType:"json",
		        success:function (data){
		        	var datas = eval(data);
		        	outnumarr=[],personarr=[];
		        	$.each(datas,function(i,item){ 
		        	  outnumarr.push(datas[i].outNum);
		        	  personarr.push(datas[i].person);
		            });	 
		        	//pageconment(curentPage,pontotal);
		        	$182('#exccontainer').highcharts({
		        		chart: {
		        	            type: 'column'
		        	    },
		                title: {
		                    text: '操作人使用数量',
		                    x: 0 //center
		                },
		                 subtitle: {
		                    text: '中石油四川公司',
		                    x: 0
		                }, 
		                xAxis: {
		                	title:{text:'操作人'},
		                    categories: personarr,    	                    
		                },
		                yAxis: {
		                    title: {
		                        text: '使用数量(K表示千)'
		                    },
		                    tickPositions: [0,1000,2300,3500,4400,5000,6300,7000,8000,12000,20000,25000]
		                },
		                plotOptions: {                                                             
		                    series: {                                                              
		                        lineWidth: 1,                                                      
		                        point: {                                                           
		                            events: { 	                   
		                            }                                                              
		                        }                                                                  
		                    }                                                                      
		                },          
		                tooltip: {
		                },
		                legend: {
		                    layout: 'vertical',
		                    align: 'right',
		                    verticalAlign: 'middle',
		                    borderWidth: 0
		                },
		                series: [{
		                    name: '使用数',
		                    data: outnumarr
		                }]
		            });
		        }
		  });
	 }
	 if(daytype==3){
		 $.ajax({
		    	type:"get",
		    	url:url+daytype,
		    	cache:true,
		        dataType:"json",
		        success:function (data){
		        	var datas = eval(data);
		        	outnumarr=[],cararr=[];
		        	$.each(datas,function(i,item){ 
		        	  outnumarr.push(datas[i].outNum);
		        	  posarr.push(datas[i].position);
		            });	 
		        	//pageconment(curentPage,pontotal);
		        	$182('#exccontainer').highcharts({
		        		chart: {
		        	            type: 'column'
		        	    },
		                title: {
		                    text: '车辆使用签封数量',
		                    x: 0 //center
		                },
		                 subtitle: {
		                    text: '中石油四川公司',
		                    x: 0
		                }, 
		                xAxis: {
		                	title:{text:'车牌号'},
		                    categories: cararr,    	                    
		                },
		                yAxis: {
		                    title: {
		                        text: '使用数量(K表示千)'
		                    },
		                    tickPositions: [0,1000,2300,3500,4400,5000,6300,7000,8000,12000,20000,25000]
		                },
		                plotOptions: {                                                             
		                    series: {                                                              
		                        lineWidth: 1,                                                      
		                        point: {                                                           
		                            events: { 	                   
		                            }                                                              
		                        }                                                                  
		                    }                                                                      
		                },          
		                tooltip: {
		                },
		                legend: {
		                    layout: 'vertical',
		                    align: 'right',
		                    verticalAlign: 'middle',
		                    borderWidth: 0
		                },
		                series: [{
		                    name: '使用数',
		                    data: outnumarr
		                }]
		            });
		        }
		  });
		  
	   }
	 
	}
	   //查询操作
       var jumpurl,curentPage=1,totalpage,pagehtml,name;
			 jumpurl="thing_thingList?type="; 
		 window.onload=function(){dayManage(jumpurl,chotype);}
		$("#searchbtn").click(function(){
			 var purl="thing_thingList?type=";
			dayManage(purl,chotype);
		});	
		//下一页
		$182("#next").live("click",function(){
			 comid=txtid;
			 curentPage = parseInt(curentPage)+1;
			 $("#curpage").text(curentPage); 
			// pageconment(curentPage,totalpage);
			 jumpurl= jumpurl+curentPage;			
			 jumpurl="thing_thingList?type=";
			 dayManage(jumpurl,chotype);
		}); 
		//上一页
		$182("#pre").live("click",function(){
			comid=txtid;
			curentPage = curentPage -1;  
			$("#curpage").text(curentPage);
			//pageconment(curentPage,totalpage);
			jumpurl= jumpurl+curentPage;
			jumpurl="thing_thingList?type=";
			dayManage(jumpurl,chotype);
		});
		/* function pageconment(curentPage,totalpage){
			$(".table-bottom-page").empty();
			if(curentPage>1){
				 pagehtml="<a id='pre' >上一页 </a>&nbsp";
				 $(".table-bottom-page").append(pagehtml);
			}
			if(curentPage<totalpage){
				 pagehtml="<a id='next' >下一页 </a>&nbsp";
				 $(".table-bottom-page").append(pagehtml);
			 }
		} */ 
	});
</script>
  </body>
</html>
