<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>中石油油罐车管理系统</title>
    <base href="<%=basePath%>"> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   	<link rel="stylesheet" type="text/css" href="positonmanage/css/posmanage.css"/>
  	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
  	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
  	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
  	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wUpkZW6ETCUz9OpVZUTEUu9w"></script>
  	<script type="text/javascript" src="js/convertor.js"></script>
    <style type="text/css">
      .warptab{height:auto;border:none;width:100%;float:left;box-shadow:none;}.warptab tr td{border:none;margin:auto;}
      .del input{border: 1px solid #364f86;border-radius: 3px;height: 1.5em; outline: medium none;
		          transition: all 0.3s ease-in-out 0s;margin-top:25px;}
      .detail-top{ width:100%;height:113px;background:#FFFFCC} .anchorBL{display:none;} 
	  #allmap{height:400px;width:100%;} #r-result{width:100%; font-size:14px;}
    </style> 
  </head>  
<body>
  <center>
   <div class="main">
   <!--顶部标题-->
    <jsp:include page="/header.jsp"/>
    <div class="down">
   <!-- 左侧树形菜单栏-->
     <jsp:include page="common1.jsp" />
    <!-- 右侧内容-->
    <div class="downrig">
      <div class="rig-top">
        <div class="rig-top-titletree" style="float:left;text-align:left;">
         <a href="position_list"> &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 站点信息 >></a>
         <a href="javascript:void(0);">&nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 详情信息</a>
        </div>
      </div> 
      <!-- 站点详情页 -->
      <center>
      <div class="rig-down-all">
        <div class="table-title">
           <div style="width:65%;float:left;text-align:left;">
             <span>详情信息</span>
          </div>
          </div>
          <div class="table-conment">
           <div style="height:50em;width:100%;overflow: auto;border-radius:3px;">
             <div class="detail-top">
	           <table class="bordered warptab">
	             <tr style="height:30px;">
	               <td>站点编号：<label><s:property value="position.posId"/> </label></td>
	               <td>所属公司：<label><s:property value="position.company.comName"/></label></td>
	               <td>状态：<label>${position.posStatus==0?"正常":"未使用"}</label></td>
	             </tr>
	             <tr>
	               <td>创建时间：<label><s:property value="position.posDate"/></label></td>	               
	             </tr> 
	           </table>
	           <div style="width:19%;float:right;" class="del">
                <input type="hidden" value="<s:property value="position.posId"/>" id="posId"/>
	            </div>
              </div>
         <%--     <script type="text/javascript">
			       $(function(){
			    	 function message(text,newdata) {
			    		   var newval= newdata;
			    	       $("#spanmessage").text(text);
			    	       $("#message").dialog({
			    	           title:"中石油油罐车运输云平台，提示您！",
			    	           modal: true,
			    	           buttons: {
			    	               "确定": function(newdata) {
			    	            	   if(newval ==0){
			    	            		   $(this).dialog("close");
			    	            		   location.href="position_list";
			    	            	   }else{
			    	            		   $(this).dialog("close");  
			    	            	   }  
			    	               }
			    	           }
			    	       });
			    	   }
			       	 var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
			     	     $(document.body).append(boardDiv);
			         function queren(text, callback) {
			             $("#spanmessage").text(text);
			             $("#message").dialog({
			                 title: "中石油油罐车运输云平台，提示您！",
			                 modal: true,
			                 resizable: false,
			                 buttons: {
			                     "是": function() {
			                         callback.call();//方法回调
			                         $(this).dialog("close");
			                     },
			                    "否": function() {
					                 $(this).dialog("close");
			                  }
			                 }
			             });
			         }
			         $("#delbtn").click(function(){
			            queren("确认要删除吗？", function(){
			            	var id=$("#posId").val();
			        		var url="position_del?id="+id;
			       		    $.get(url,function(data){
			       			  if(data==0){
			       				 $("#delbtn").css("display","none");
			       				 message("删除成功！",data);
			       			  }else{
			       				 message("删除失败！无操作权限或服务器错误！",data);
			       			  }
			       		 });
			       		return true;
			          });   
			      });
			  });         
			</script> --%>
              <%-- <div class="divapi">
                  <div class="apiall">
                       <p style="font-family: "微软雅黑";">经度纬度地图显示</p>
                         <div id="allmap"></div>
						   <div id="r-result">
							 <input id="longitude" type="hidden" value="${position.posLongitude}" style="width:100px; margin-right:10px;" />
							 <input id="latitude" type="hidden" value="${position.posLatitude}" style="width:100px; margin-right:10px;" />
						 </div>
						 <input type="hidden" onclick="$('#allmap div.anchorBL').hide();" value=""/>
                  </div>
               </div>  
			 <script type="text/javascript">
			 if(document.getElementById("longitude").value !=null && document.getElementById("longitude").value !="" ||
			    document.getElementById("latitude").value !=null && document.getElementById("latitude").value !=""){
				var gpsPoint = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
				//地图初始化
				var bm = new BMap.Map("allmap");
				bm.centerAndZoom(gpsPoint, 15);
				bm.addControl(new BMap.NavigationControl());
			    //坐标转换完之后的回调函数
				setTimeout(function(){
					BMap.Convertor.translate(gpsPoint,0,translateCallback);     //真实经纬度转成百度坐标
				}, 2000); 
				 	
		        translateCallback = function (point){
							bm.enableScrollWheelZoom(true);
							var marker = new BMap.Marker(point);  // 创建标注
							bm.addOverlay(marker);              // 将标注添加到地图中
							bm.centerAndZoom(point, 15);
							var opts = {
							  width : 100,     // 信息窗口宽度
							  height: 50,     // 信息窗口高度
							  title : "当前信息：" , // 信息窗口标题
							  enableMessage:true,//设置允许信息窗发送短息
							  message:""
							}
							var infoWindow = new BMap.InfoWindow("经度："+point.lng+",纬度："+point.lat+"", opts);  // 创建信息窗口对象 
							marker.addEventListener("click", function(){          
								bm.openInfoWindow(infoWindow,point); //开启信息窗口
							});
					}
			 }
	        </script> --%>
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
