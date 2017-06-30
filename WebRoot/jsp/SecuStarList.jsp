<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,java.text.DecimalFormat" pageEncoding="UTF-8"%>
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
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">     
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
	<link rel="stylesheet" type="text/css" href="waybill/css/waybillmanage.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
	<link rel="stylesheet" type="text/css" href="common/css/starcomm.css">
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
  	  var $182=$;
  	</script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
    
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts-3d.src.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/modules/exporting.js"></script>
    <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	
  	<script type="text/javascript">
  	  var $104=$;
  	</script>		
  	<link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css">
  	<script type="text/javascript"  src="js/jquery.autocomplete.min.js"></script> 
    <script type="text/javascript" src = "js/findCompanyList.js"></script>
   
</head>
  <body>
  <center>
    <div id="wrapper">
   <!--顶部标题-->
    <jsp:include page="/header.jsp" />
  <!-- 左侧树形菜单栏-->
     <jsp:include page="common1.jsp" />
    <!-- 右侧内容-->
      <div id="page-wrapper">
         <div id="page-inner">
	     <div class="panel panel-info" style="margin-bottom:0px;">
		   <div class="panel-heading" style="text-align:left;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small> 安全之星报表		       	
		      </h3>		     
		   </div>			  						   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		   </div>	
		   <div class="panel-body">	  	              
	          <div class="box"><span class="input-group-addon" style="display:inline;"><span >车牌号</span></span>	
              <div tabindex="4" class="searchable-select" id="car1">
              	<span class="searchable-select-caret"></span>
              	<div class="searchable-select-holder"></div>
              	<div class="searchable-select-dropdown searchable-select-hide">
              		<input type="text" class="searchable-select-input"> 
              		<div class="searchable-scroll">
              		  <div class="searchable-has-privious searchable-select-hide">...</div>
              			<div class="searchable-select-items" id="carid">              				
              			</div>
              		  <div class="searchable-has-next searchable-select-hide">...</div>
              		</div>
              	</div>
              </div> 
              </div>
             	<s:iterator value="#session.ADMIN_POWER">
              		<s:if test="poUrl=='company_getListByName'">
              			<span>所属公司：<input id="loc" style="width:120px;" type="text" value="${condition.comName}" onfocus="findcom();"/></span>
              		</s:if>
            	 </s:iterator>
               <input id="locid" style="width:120px;" type="hidden" value="${condition.comId }" />
                <div class="box"> 
                <span class="input-group-addon" style="display:inline;"><span >开始时间</span></span>
              	<span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="starttime" value="${condition.beginTime}"></span>
			  </div>
			  <div class="box">
			    <span class="input-group-addon" style="display:inline;"><span >结束时间</span></span>
			    <span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="endtime" value="${condition.endTime}"></span>            					               
			  </div>
			   <div class="box">
               <span style="width:80px;right:5%;float:left;"><input type="button" value="生成报表" id="searchbtn" class="search btn btn-primary" onclick="javascript:searchsecuList()" style="width:80px;height:28px;"/></span>
              </div>
              <div class="box">
               <span id="export" style="width:80px;right:25%;float:left;"><input type="button" class="search btn btn-primary" data-type="xls" value="导出Excel" id="tableExcelbtn" class="search" style="width:80px;height:28px;"/></span>
              </div>         
            </div>
          </div>
         <div class="widget orange">
          <div class="widget-title">
              <h4 style="margin:0 auto;line-height: 36px;"><i class="fa fa-reorder"></i> 报表信息记录</h4>                   
          </div>
          <div class="widget-body" style="overflow:auto;height:64em;">
            <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">
           <thead>
              <tr>
	               <th style="border-left:none;border-right:none;text-align:right;">车辆信息</th>
	               <th style="border-left:none"></th>
	               <th style="vertical-align: bottom;border-bottom:none;">解封数</th>
	               <th style="vertical-align: bottom;border-bottom:none;">异常数</th>
	               <th style="vertical-align: bottom;border-bottom:none;">异常数/解封数（比例）</th>
	               <th style="border-bottom:none;">
	            	          <div style="font-size:14px;">排名</div>
		            	      <a href="javascript:void(0)" id="0" class="upbene defsty"><img src="common/img/up.png" width="20" height="25"/></a>
		            	      <a href="javascript:void(0)" id="1" class="downbene"><img src="common/img/down.png" width="20" height="25"/></a>
	              </th>
              </tr>
              <tr>
               <th style="border-top:1px solid #ccc;">车牌号</th>
               <th style="border-top:1px solid #ccc;">所属公司</th>
               <th style="border-top:none;"></th>
               <th style="border-top:none;"></th>
               <th style="border-top:none;"></th>
               <th style="border-top:none;"></th>
              </tr>
             </thead>
             <tbody id="benefitcoment">
             </tbody>             
           </table>  
          </div>
          <!--图形报表  -->
          <!-- <div id="secucontainer" style="margin-top:10px;"></div> --> 
          </div>  
      </div>
    </div>
    <!-- 右边部分完结-->
  </div>
</div>
</center>        
    <script type="text/javascript" src="js/laydate.js"></script>
    <script type="text/javascript" src="js/Blob.js"></script>
    <script type="text/javascript" src="js/FileSaver.js"></script>
    <script type="text/javascript" src="js/tableExport.js"></script>
    <script type="text/javascript" src="js/jquery.base64.js"></script>
    <script type="text/javascript" src="js/defaultdate.js"></script>
    <script type="text/javascript"> 
      !function(){
          laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
          laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
        	laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
       }();
        var $exportLink = document.getElementById('export');
 		  $exportLink.addEventListener('click', function(e){
 			e.preventDefault();
 			if(e.target.nodeName === "INPUT"){
 				tableExport('tableExcel', '安全之星报表', e.target.getAttribute('data-type'));
 			}	
 		 }, false); 
    </script>
     <!--获取数据  -->
     <script type="text/javascript">
        var updown=0,benefithtml;
        var cararr=[],freTotalarr=[],exrTotalarr=[],tlarr=[];
         function benefitstar(url){
	    	 $.ajax({
	   	    	type:"get",
	   	    	url:url,
	   	    	"Content-Type":'text/json;charset=UTF-8',
	   	    	cache:true,
	   	        dataType:"json",   
	   	        success:function (data){
	   	        	 var datas = eval(data);
	   	        	 $("#benefitcoment tr").remove();
	   	        	 cararr=[],freTotalarr=[],exrTotalarr=[],tlarr=[];
	   	        	 $.each(datas,function(i,item){
	   	        		if(updown==0){
	   	        			benefithtml="<tr class='info'><td>"+datas[i].car+"</td>";
			   	        	benefithtml+="<td>"+datas[i].com+"</td>";
			   	        	benefithtml+="<td>"+datas[i].freTotal+"</td>";
			   	        	benefithtml+="<td>"+datas[i].exrTotal+"</td>";	       
			   	        	benefithtml+="<td>"+(datas[i].tl).toFixed(2)+"%"+"</td>";
			   	        	benefithtml+="<td>"+(i+1)+"</td></tr>";
			   	        	$("#benefitcoment").append(benefithtml);
	   	        		}
	   	        		if(updown==1){
	   	        			benefithtml="<tr class='info'><td>"+datas[i].car+"</td>";
			   	        	benefithtml+="<td>"+datas[i].com+"</td>";
			   	        	benefithtml+="<td>"+datas[i].freTotal+"</td>";
			   	        	benefithtml+="<td>"+datas[i].exrTotal+"</td>";	       
			   	        	benefithtml+="<td>"+(datas[i].tl).toFixed(2)+"%"+"</td>";
			   	        	benefithtml+="<td>"+(datas.length-i)+"</td></tr>";
			   	        	$("#benefitcoment").append(benefithtml);
	   	        		}
	   	        		cararr.push(datas[i].car);
	   	        		freTotalarr.push(datas[i].freTotal);
	   	        		exrTotalarr.push(datas[i].exrTotal);
	   	        		tlarr.push(datas[i].tl);
	   	        	}); 
	   	        	//图形报表
		   	        	/* $182('#secucontainer').highcharts({
		   	        	 	chart: {
			                    zoomType: 'xy'
			                },
			                title: {
			                    text: '安全之星图形报表',
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
			                yAxis: [{ 
			                    labels: {
			                        formatter: function() {
			                            return this.value;
			                        },
			                        style: {
			                            color: '#89A54E'
			                        }
			                    },
			                    tickPositions: [0,10,20,30,40,50,60,70,80,90] ,
			                    title: {
			                        text: '施封数(个)',
			                        style: {
			                            color: '#89A54E'
			                        }
			                    }
			                    
			                }, { 
			                    gridLineWidth: 0,
			                    title: {
			                        text: '异常数(个)',
			                        style: {
			                            color: '#4572A7'
			                        }
			                    },
			                    tickPositions:[0,10,20,30,40,50,60,70,80,90] ,
			                    labels: {
			                        formatter: function() {
			                            return this.value;
			                        },
			                        style: {
			                            color: '#4572A7'
			                        }
			                    },
			                    opposite: true

			                }, {
			                    gridLineWidth: 0,
			                    title: {
			                        text: '异常数/施封数',
			                        style: {
			                            color: '#AA4643'
			                        }
			                    },
			                    tickPositions: [0,1,2,3,4,5,6,7,8,9],
			                    labels: {
			                        formatter: function() {
			                            return this.value/10 +'%';
			                        },
			                        style: {
			                            color: '#AA4643'
			                        }
			                    },
			                    opposite: true
			                }],
			                tooltip: {
			                    shared: true,
			                    valueDecimals: 2
			                },
			                
			                legend: {
			                    layout: 'vertical',
			                    align: 'right',
			                    verticalAlign: 'middle',
			                    borderWidth: 0
			                },
			                series: [ {
			                	name: '异常数/施封数（比例）',
			                    data: tlarr,				                 
			                    color: '#AA4643',
			                    type: 'column',
			                    yAxis: 2,
			                    data: tlarr,
			                    tooltip: {
			                        valueSuffix: ' %'
			                    }
			                }, {
			                	 name: '异常数',
				                 data: exrTotalarr, 
			                    type: 'column',
			                    color: '#4572A7',
			                    yAxis: 1,		                    
			                    tooltip: {
			                        valueSuffix: ' 个'
			                    }
				               
			                },{
			                	 name: '施封数',
				                 data: freTotalarr,				               
			                    color: '#89A54E',
			                    type: 'column',			                     
			                    tooltip: {
			                        valueSuffix: ' 个'
			                    }

			                }]
			                
			            });	 */
	   	           }
	         });
         }
         $("#starttime").val(starDate);$("#endtime").val(endDate);
         var jumpurl="safe_safe?sortT="+updown+"&condition.beginTime="+starDate+"&condition.endTime="+endDate+"";
         window.onload=function(){benefitstar(jumpurl);}
         var starttime,endtime, comname,locid,carid="";
         function searchsecuList(){
  			starttime=document.getElementById("starttime").value;
  			endtime = document.getElementById("endtime").value;
  			carid=$("#carid .selected").attr("data-value");
  			carid=encodeURI(carid,"UTF-8"); 
    		(carid=="" || carid==null || carid=="undefined") ? carid="" : carid=carid;
  			comname=$("#loc").val();locid=$("#locid").val();
    		var url;loccc = txtid;
    		(comname=="" || comname==null || comname=="undefined") ? comname="" : comname=comname;
  			if(loccc !="" && loccc != null){
      			url="safe_safe?condition.carFlapper="+carid+"&condition.comName="+comname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.comId="+loccc+"&sortT="+updown+"";
  			} else{
      			url="safe_safe?condition.carFlapper="+carid+"&condition.comName="+comname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&sortT="+updown+"";
  			}
  			benefitstar(url);
  		}
        $(".upbene").click(function(){
        	starttime=document.getElementById("starttime").value;
  			endtime = document.getElementById("endtime").value;
        	$(".downbene").removeClass("defsty");
        	$(".upbene").addClass("defsty");
        	updown=$(this).attr("id");
        	var tempurl="safe_safe?sortT="+updown+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"";
        	benefitstar(tempurl);
        });
        $(".downbene").click(function(){
        	starttime=document.getElementById("starttime").value;
  			endtime = document.getElementById("endtime").value;
        	$(".upbene").removeClass("defsty");
        	$(".downbene").addClass("defsty");
        	updown=$(this).attr("id");
        	var tempurl="safe_safe?sortT="+updown+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"";
        	benefitstar(tempurl);
        });
     </script>
      <script type="text/javascript" src="js/findCarList.js"></script> 
      <script type="text/javascript" src="js/searchsuosou.js"></script>
      <script type="text/javascript" src="js/boswer.js"></script>
  </body>
</html>
