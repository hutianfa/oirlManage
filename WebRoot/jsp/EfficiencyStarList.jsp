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
     <script type="text/javascript" src="Highcharts-4.1.5/js/highcharts.js"></script>
    <script type="text/javascript" src="Highcharts-4.1.5/js/modules/exporting.js"></script> 
    <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	<script type="text/javascript"  src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
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
	     <div class="panel panel-info" style="margin-bottom:0px">
		   <div class="panel-heading" style="text-align:left;">
		      <h3 class="panel-title">
		       	<small><i class="fa fa-tasks fa-2x"></i></small> 效率之星报表		       	
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
              			<span>所属公司：<input id="loc" style="width:120px;" type="text" value="${condition.comName }" onfocus="findcom();"/></span>
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
            	<span style="padding:0.5em" style="width:80px;right:5%;float:left;"><input type="button" value="生成报表" id="searchbtn" class="search btn btn-primary" onclick="javascript:searchsecuList()" style="width:80px;height:28px;"/></span>
                </div>
                <div class="box">
                <span id="export" style="width:80px;right:25%;float:left;"><input type="button" data-type="xls" value="导出Excel" id="tableExcelbtn" class="search btn btn-primary" style="width:80px;height:28px;"/></span>
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
	               <th style="vertical-align: bottom;border-bottom:none;">总运输次数</th>
	               <th style="vertical-align: bottom;border-bottom:none;">总运输时长（小时）</th>
	               <th style="vertical-align: bottom;border-bottom:none;">平均运输时长（小时）</th>
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
           <div id="efficicontainer" style="margin-top:10px;"></div> 
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
 				tableExport('tableExcel', '效率之星报表', e.target.getAttribute('data-type'));
 			}	
 		 }, false);
    </script>
     <!--获取数据  -->
     <script type="text/javascript">
        var updown=0,benefithtml;
        var cararr=[],totalnumarr=[],totaltimearr=[],tlarr=[];
         function benefitstar(url){
	    	 $.ajax({
	   	    	type:"get",
	   	    	url:url,
	   	    	"Content-Type":'text/json;charset=UTF-8',
	   	    	cache:true,
	   	        dataType:"json",   
	   	        success:function (data){
	   	        	 var datas = eval(data);
	   	        	 $("#benefitcoment tr").children().remove();
	   	        	 cararr=[],totalnumarr=[],totaltimearr=[],tlarr=[];
	   	        	 $.each(datas,function(i,item){
	   	        		if(updown==0 ){
	   	        			benefithtml="<tr class='info'><td>"+datas[i].car+"</td>";
			   	        	benefithtml+="<td>"+datas[i].com+"</td>";
			   	        	benefithtml+="<td>"+datas[i].totalWayNum+"</td>";
			   	        	benefithtml+="<td>"+(datas[i].totalWayTime).toFixed(3)+"</td>";	       
			   	        	benefithtml+="<td>"+(datas[i].tl).toFixed(3)+"</td>";
			   	        	benefithtml+="<td>"+(i+1)+"</td></tr>";
			   	        	$("#benefitcoment").append(benefithtml);
	   	        		}
	   	        		if(updown==1 ){
	   	        			benefithtml="<tr class='info'><td>"+datas[i].car+"</td>";
			   	        	benefithtml+="<td>"+datas[i].com+"</td>";
			   	        	benefithtml+="<td>"+datas[i].totalWayNum+"</td>";
			   	        	benefithtml+="<td>"+(datas[i].totalWayTime).toFixed(3)+"</td>";	       
			   	        	benefithtml+="<td>"+(datas[i].tl).toFixed(3)+"</td>";
			   	        	benefithtml+="<td>"+(datas.length-i)+"</td></tr>";
			   	        	$("#benefitcoment").append(benefithtml);
	   	        		}
	   	        		cararr.push(datas[i].car);
	   	        		totalnumarr.push(datas[i].totalWayNum);
	   	        		totaltimearr.push(datas[i].totalWayTime);
	   	        		tlarr.push(datas[i].tl);
	   	        	}); 
	   	       		 //图形报表	       		 
		   	        	 $182('#efficicontainer').highcharts({		   	        	
			                chart: {
			                    zoomType: 'xy'
			                },
			                title: {
			                	text: '效率之星图形报表',
			                    x: 0 //center
			                },
			                subtitle: {
			                	text: '中石油四川公司',
			                    x: 0
			                },
			                xAxis: [{
			                	title:{text:'车牌号'},
			                    categories: cararr,    	
			                }],
			                yAxis: [{ 
			                    labels: {
			                         formatter: function() {
			                        	var strVal = this.value + '';
			                        	return strVal;
			                        }, 
			                        style: {
			                            color: '#89A54E'
			                        }
			                    },
			                    tickPositions: [0,10,20,30,40,50,60,70],
			                    title: {
			                        text: '总运输次数(次)',
			                        style: {
			                            color: '#89A54E'
			                        }
			                    }
			                }, { 
			                    gridLineWidth: 0,
			                    title: {
			                        text: '总运输时长(小时)',
			                        style: {
			                            color: '#4572A7'
			                        }
			                    },
			                    tickPositions: [0,40,80,120,160,200,240,280],
			                    labels: {
			                         formatter: function() {
			                        	 var strVal = this.value + '';			                        
			                        	 return strVal+ '.00';
			                        }, 
			                        style: {
			                            color: '#4572A7'
			                        }
			                    },
			                    opposite: true

			                }, {
			                    gridLineWidth: 0,
			                    title: {
			                        text: '平均运输时长(小时)',
			                        style: {
			                            color: '#AA4643'
			                        }
			                    },
			                    tickPositions: [0,4,8,12,16,20,24,28],
			                    labels: {
			                        formatter: function() {
			                        	 var strVal = this.value + '';			                        	
			                        	 return strVal+'.00';
			                        }, 
			                        style: {
			                            color: '#AA4643'
			                        }
			                    },
			                    opposite: true
			                }],
			                tooltip: {
			                    shared: true,
			                    valueDecimals: 2,			                  
			                },
			                legend: {
			                    layout: 'vertical',
			                    align: 'right',
			                    verticalAlign: 'middle',
			                    borderWidth: 0
			                },
			                series: [ {
			                	name: '平均运输时长',				                 
			                    color: '#AA4643',
			                    type: 'column',
			                    data: tlarr,
			                    yAxis: 2,
			                    tooltip: {
			                        valueSuffix: ' 小时'
			                    }
			                },{
			                	name: '总运输时长',
			                    type: 'column',
			                    color: '#4572A7',
			                    yAxis: 1,
			                    data: totaltimearr,			                    
			                    tooltip: {
			                        valueSuffix: ' 小时'
			                    }
				               
			                }, {
			                	name: '总运输次数',				               
			                    color: '#89A54E',
			                    type: 'column',			                    
			                    data: totalnumarr,
			                    tooltip: {
			                        valueSuffix: ' 次'
			                    }

			                }]
			            }); 
	   	           }
	         });
         }
         $("#starttime").val(starDate);$("#endtime").val(endDate);
         var jumpurl="effic_Efficiency?sortT="+updown+"&condition.beginTime="+starDate+"&condition.endTime="+endDate+"";
         window.onload=function(){benefitstar(jumpurl);}
         var starttime,endtime, comname,locid,carid="";
         function searchsecuList(){
  			starttime=document.getElementById("starttime").value;
  			endtime = document.getElementById("endtime").value;
  			comname=$("#loc").val();locid=$("#locid").val();
    		var url;loccc = txtid;
    		carid=$("#carid .selected").attr("data-value");
    		carid=encodeURI(carid,"UTF-8"); 
    		(carid=="" || carid==null || carid=="undefined") ? carid="" : carid=carid;
    		(comname=="" || comname==null || comname=="undefined") ? comname="" : comname=comname;
  			if(loccc !="" && loccc != null){  				
      			url="effic_Efficiency?condition.carFlapper="+carid+"&condition.comName="+comname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.comId="+loccc+"&sortT="+updown+"";
  			} else{
  				
      			url="effic_Efficiency?condition.carFlapper="+carid+"&condition.comName="+comname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&sortT="+updown+"";
  			}
  			benefitstar(url);
  		}
        $(".upbene").click(function(){
        	starttime=document.getElementById("starttime").value;
  			endtime = document.getElementById("endtime").value;
        	$(".downbene").removeClass("defsty");
        	$(".upbene").addClass("defsty");
        	updown=$(this).attr("id");
        	var tempurl="effic_Efficiency?sortT="+updown+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"";
        	benefitstar(tempurl);
        });
        $(".downbene").click(function(){
        	starttime=document.getElementById("starttime").value;
  			endtime = document.getElementById("endtime").value;
        	$(".upbene").removeClass("defsty");
        	$(".downbene").addClass("defsty");
        	updown=$(this).attr("id");
        	console.log(endtime);
        	var tempurl="effic_Efficiency?sortT="+updown+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"";
        	benefitstar(tempurl);
        });
     </script>
      <script type="text/javascript" language="javascript" src="js/findCarList.js"></script> 
      <script type="text/javascript" src="js/searchsuosou.js"></script>
      <script type="text/javascript" src="js/boswer.js"></script>
  </body>
</html>
