<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>   
    <base href="<%=basePath%>">
    <title>中石油油罐车运输管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <link rel="icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	<link rel="stylesheet" type="text/css" href="common/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="css/barstyle.css" />
	<link rel="stylesheet" type="text/css" href="common/css/index.css" />
    <script type="text/javascript"  src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="Highcharts-4.1.5/js/highcharts.js"></script>
	<script type="text/javascript" src="Highcharts-4.1.5/js/highcharts-3d.src.js"></script>
	<script type="text/javascript" src="Highcharts-4.1.5/js/modules/exporting.js"></script>  
    <script type="text/javascript">
     $(function () {
      $('#exccontainer').highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: '异常信息统计'
        },
        colors:[  
				'#FD30FF',
				'#0000FF',
            ],
        tooltip:{
        	 pointFormat:'{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                      formatter: function() {
                        if (this.percentage > 0)
                        	{
                              return '<b>' + this.point.name + '</b>';
                        	}
                    },  
                  // format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '所占百分比',
            data:  [
                ['未处理异常',  Number(parseInt($("#excstart").val()).toFixed(2))],
                {
                    name: '已处理异常',
                    y:  Number(parseInt($("#excend").val()).toFixed(2)),
                    sliced: true,
                    selected: true
                }
            ] 
        }]
    });
    $('#waybillcontainer').highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: '运单记录统计'
        },
        colors:[
				'#FD30FF',
				'#0000FF',
				'#99CC66',
            ],
        tooltip: {
              pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '所占百分比',
            data: [
                ['已完成',    Number(parseInt($("#waybillend").val()).toFixed(2))],
                {
                    name: '运输中',
                    y:  Number(parseInt($("#waybillstart").val()).toFixed(2)),
                    sliced: true,
                    selected: true
                },
                ['异常',     Number(parseInt($("#waybillexc").val()).toFixed(2))]
            ]
        }]
    	});
     });
</script>
</head>
<body>
<center>
  <div  id="wrapper">
	<!--顶部标题-->
	<jsp:include page="header.jsp" />			
		<!-- 左侧树形菜单栏-->
		<jsp:include page="/jsp/common1.jsp" />
		<!-- 右侧内容-->
		<div id="page-wrapper">
        	<div id="page-inner">
        	 <div class="panel panel-primary" style="margin-bottom:10px;">
			   <div class="panel-heading" style="background-color:#78A7E4;border-color:#78A7E4;">
			      <h3 class="panel-title" style="margin:0 auto;"><i class="fa fa-tasks fa-x"></i>&nbsp;信息统计</h3>
			   </div>						   
		     </div>	
		  <div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 15px;">
		      <h3 class="panel-title" style="text-align:left;"><i class="fa fa-tasks fa-x"></i>&nbsp;运单统计</h3>
		   </div>
		   <div class="panel-body state-overview">
		   	<div class="row">				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">										
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="symbol terques">
                         <i class="fa fa-thumbs-o-up"></i>
                     </div>
                     <div class="value" >
                         <h1><s:property value="wayBillCompletedCount" default="0"/></h1>
                         <p>全时段已完成运单共计(单位:条)</p>
                     </div>
                 </section>			
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				 <section class="panel" style="background-color:rgba(240,240,240,1);">
                   <div class="symbol red">
                       <i class="fa fa-cubes"></i>
                   </div>
                   <div class="value">
                       <h1><s:property value="wayBillUnfinishedCount" default="0"/></h1>
                       <p>全时段运输中运单共计(单位:条)</p>
                   </div>
                 </section>		
				</div><!--/.col-->								
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">	
				  <section class="panel" style="background-color:rgba(240,240,240,1);">
                     <div class="symbol yellow">
                         <i class="fa fa-cloud-download"></i>
                     </div>
                     <div class="value">
                         <h1><s:property value="wayBillExcrecordCount" default="0"/></h1>
                         <p>全时段异常运单共计(单位:条)</p>
                     </div>
                  </section>		
				</div><!--/.col-->																
			</div>						    
		   </div>						   
		</div>	
		 <div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 15px;">
		      <h3 class="panel-title" style="text-align:left;"><i class="fa fa-tasks fa-x"></i>&nbsp;异常统计</h3>
		   </div>
		   <div class="panel-body state-overview">
		   	<div class="row">				
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<section class="panel" style="background-color:rgba(240,240,240,1);">
                          <div class="symbol blue">
                              <i class="fa fa-thumbs-o-down"></i>
                          </div>
                          <div class="value">
                              <h1><s:property value="excUntreatedCount" default="0"/></h1>
                              <p>全时段未处理异常共计(单位:条)</p>
                          </div>
                      </section>			
				</div><!--/.col-->								
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<section class="panel" style="background-color:rgba(240,240,240,1);">
                          <div class="symbol green">
                              <i class="fa fa-reddit"></i>
                          </div>
                          <div class="value">
                              <h1><s:property value="excTreatedCount" default="0"/></h1>
                              <p>全时段已处理异常共计(单位:条)</p>
                          </div>
                      </section>				
				</div><!--/.col-->																						
			 </div>						     
		   </div>						   
		</div>								              															
		<!-- 获取数据 -->
		 <input type="hidden" value="<s:property value='wayBillCompletedCount' default='0' />" id="waybillend" /> 
		 <input type="hidden" value="<s:property value='wayBillUnfinishedCount' default='0' />" id="waybillstart" />
		 <input type="hidden" value="<s:property value='wayBillExcrecordCount' default='0' />"  id="waybillexc"/>
		 <input type="hidden" value="<s:property value='excUntreatedCount' default='0' />" id="excstart"/>
		 <input type="hidden" value="<s:property value='excTreatedCount' default='0' />"  id="excend"/>
		 <div class="panel panel-primary" style="margin-bottom:10px;">
		   <div class="panel-heading" style="background-color:#78A7E4;padding: 5px 15px;">
		      <h3 class="panel-title" style="text-align:left;"><i class="fa fa-tasks fa-x"></i>&nbsp;报表统计</h3>
		   </div>
		   <div class="panel-body">
		    <div class="row">
			   <div class="col-sm-6 col-md-6">
			      <div class="thumbnail">
			        <div id="exccontainer" style="min-width:100%;height:300px"></div>
			         <div class="caption">
			            <h3>异常信息统计</h3>							   
			         </div>
			      </div>
			   </div>
			   <div class="col-sm-6 col-md-6">
			      <div class="thumbnail">
			         <div id="waybillcontainer" style="min-width:100%;height:300px"></div>
			         <div class="caption">
			            <h3>异常信息统计</h3>							   
			         </div>
			      </div>
			   </div>
			 </div>
		   </div>						   
		 </div>	
   		</div>	
   		</div>		
    </div>
	</center>
</body>
</html> 
