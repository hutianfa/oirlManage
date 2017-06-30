<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
    <link rel="stylesheet" type="text/css" href="statement/css/statmanage.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
    <!-- <script type="text/javascript" src="js/jquery.min.js"></script> -->
    <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
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
	            data:  [
	                ['未处理异常',  Number(parseInt($("#excstart").val()).toFixed(2))],
	                {
	                    name: '已处理异常',
	                    y: Number(parseInt($("#excend").val()).toFixed(2)),
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
	            text: '运单信息统计'
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
	                ['已完成',   Number(parseInt($("#waybillend").val()).toFixed(2))],
	                {
	                    name: '运输中',
	                    y: Number(parseInt($("#waybillstart").val()).toFixed(2)),
	                    sliced: true,
	                    selected: true
	                },
	                ['异常',    Number(parseInt($("#waybillexc").val()).toFixed(2))]
	            ]
	        }]
	    }); 
	 });
</script>
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
        <div class="rig-top-title" style="float:left;width: 12em;text-align: left;">
          &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 数据统计报表
        </div>
      </div> 
      <div class="rig-down-all">
         <div class="all-pic-content">
           <div class="pic-content-left">
              <div id="exccontainer" style="min-width:100%;height:450px"></div>
           </div>
           <div class="pic-content-rig">
              <div id="waybillcontainer" style="min-width:100%;height:450px"></div>
           </div>
         	 <input type="hidden" value="<s:property value='wayBillCompletedCount' default='0' />" id="waybillend" /> 
			 <input type="hidden" value="<s:property value='wayBillUnfinishedCount' default='0' />" id="waybillstart" />
			 <input type="hidden" value="<s:property value='wayBillExcrecordCount' default='0' />"  id="waybillexc"/>
			 <input type="hidden" value="<s:property value='excUntreatedCount' default='0' />" id="excstart"/>
			 <input type="hidden" value="<s:property value='excTreatedCount' default='0' />"  id="excend"/>
         </div>
      </div>
    </div>
    <!-- 右边部分完结-->
  </div>
</div>
</center>
  </body>
</html>
