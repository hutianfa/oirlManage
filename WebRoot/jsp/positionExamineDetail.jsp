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
   	<link rel="stylesheet" type="text/css" href="positonmanage/css/auditmanage.css"/>
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
      .warptab{height:auto;border:none;width:80%;float:left;box-shadow:none;}
      .warptab tr td{border:none;margin:auto;}
      .del input{border: 1px solid #364f86;border-radius: 3px;
                 height: 1.5em;outline: medium none;transition: all 0.3s ease-in-out 0s;margin-top:25px;}
      .detail-top{width:100%;height:135px;background:#FFFFCC}
	  .anchorBL{display:none;} 
	  #allmap{height:400px;width:100%;}
	  #r-result{width:100%; font-size:14px;}
    </style> 
  </head>
  
<body>
  <center>
   <div class="main">
   <!--顶部标题-->
    <jsp:include page="/header.jsp"/>
    <div class="down">
   <!-- 左侧树形菜单栏-->
     <jsp:include page="common.jsp" />
    <!-- 右侧内容-->
    <div class="downrig">
      <div class="rig-top">
        <div class="rig-top-titletree" style="float:left;text-align:left;font-family:'微软雅黑';">
         <a href="positionExamine_list"> &nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 站点审核信息 >></a>
         <a href="javascript:void(0);">&nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 审核详情信息</a>
        </div>
      </div> 
      <!-- 位置详情页 -->
      <center>
      <div class="rig-down-all">
        <div class="table-title">
           <div style="width:65%;float:left;text-align:left;font-family:'微软雅黑';">
             <span>审核详情信息</span>
          </div>
          </div>
          <div class="table-conment">
           <div style="height:50em;width:100%;overflow: auto;border-radius:3px;">
             <div class="detail-top">
	           <table class="bordered warptab">
	             <tr style="height:30px;">
	               <td>站点编号：<label><s:property value="positionExamine.id"/> </label></td>
	               <td>账号：<label><s:property value="positionExamine.positionExamineUser.positionAccount"/></label></td>
	             </tr>
	             <tr>
	               <td>时间：<label><s:property value="positionExamine.time"/></label></td>
	               <td>站点名称：<label><s:property value="positionExamine.name"/></label></td>
	             </tr>
	             <tr>
	               <td>经度：<label><s:property value="positionExamine.longitude"/></label></td>
	               <td>纬度：<label><s:property value="positionExamine.latitude"/></label></td>
	             </tr>
	           </table>
	           <div style="width:19%;float:right;" class="del">
	             <s:if test="positionExamine.state==1">
	               <input type="button" value="通过" id="passthrough" class="search" style="height:1.8em;"/>
	               <input type="button" value="不通过" id="nopassthrough" class="search" style="height:1.8em;"/>
	             </s:if>
	             <!--数据传值  -->
	               <input type="hidden" value="<s:property value="positionExamine.id"/>" id="Id"/>
	               <input type="hidden" value="<s:property value="positionExamine.positionExamineUser.positionAccount"/>" id="positionAccount"/>
	               <input type="hidden" value="<s:property value="positionExamine.time"/>" id="time"/>
	               <input type="hidden" value="<s:property value="positionExamine.name"/>" id="name"/>
	               <input type="hidden" value="<s:property value="positionExamine.longitude"/>" id="longitude"/>
	               <input type="hidden" value="<s:property value="positionExamine.latitude"/>" id="latitude"/>
	               <input type="hidden" value="<s:property value="positionExamine.positionExamineUser.id"/>" id="user_id" /> 
                   <input type="hidden" value="<s:property value="positionExamine.cardNumber"/>" id="carNumber" /> 
                   <input type="hidden" value="<s:property value="positionExamine.company.comId"/>" id="comId" /> 
                   <input type="hidden" value="<s:property value="positionExamine.state"/>" id="state" />
               </div> 
              </div>
             <script type="text/javascript">
 			     $(function(){
 			    	 var positionAccount=$("#positionAccount").val();
 			    	 var time=$("#time").val();
 			    	 var name=$("#name").val();
 			    	 var longitude=$("#longitude").val();
 			    	 var latitude=$("#longitude").val();
 			    	 var user_id =$("#user_id").val();
 			    	 var carNumber =$("#carNumber").val();
 			    	 var comId=$("#comId").val();
 			    	 var state=$("#state").val();
			    	 function message(text,newdata,num) {
			    		   var newval= newdata;
			    	       $("#spanmessage").text(text);
			    	       $("#message").dialog({
			    	           title:"物流运输云平台，提示您！",
			    	           modal: true,
			    	           buttons: {
			    	               "确定": function(newdata) {
			    	            	   if(newval ==0){
			    	            		   if(num==1){
			    	            			   $(this).dialog("close");
			    	            			   location.href="position_list";
			    	            		   }else if(num==2){
			    	            			   $(this).dialog("close");
				    	            		   location.href="positionExamine_list";
			    	            		   }
			    	            		  
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
			                 title: "物流运输云平台，提示您！",
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
			         var id=$("#Id").val();
			         $("#passthrough").click(function(){
			            queren("确认要审核通过吗？", function(){
			        		/* var url="positionExamine_updateState?id="+id+"positionAccount="+positionAccount+"&condition."; */
			       		    $.post("positionExamine_updateState",{id:id,positionAccount:positionAccount,
	            				   time:time,name:name,longitude:longitude,latitude:latitude,user_id:user_id,
	            				   carNumber:carNumber,comId:comId,state:state},function(data){
			       			  if(data==0){
			       				 message("审核通过！",data,1);
			       			  }else{
			       				 message("通过失败！",data,1);
			       			  }
			       		 });
			       		return true;
			          });   
			      });
			      $("#nopassthrough").click(function(){
				            queren("确认要审核不通过吗？", function(){
				        		var url="positionExamine_del?id="+id;
				       		    $.get(url,function(data){
				       			  if(data==0){
				       				 message("处理成功！",data,2);
				       			  }else{
				       				 message("处理失败！",data,2);
				       			  }
				       		 });
				       		return true;
				          });   
				    });
			  });         
			</script>
              <div class="divapi">
                  <div class="apiall">
                       <p style="font-size:14px;font-family:'微软雅黑';padding-bottom:1em;">经度纬度地图显示</p>
                         <div id="allmap"></div>
						   <div id="r-result">
							 <input id="longitude" type="hidden" value="${positionExamine.longitude}" style="width:100px; margin-right:10px;" />
							 <input id="latitude" type="hidden" value="${positionExamine.latitude}" style="width:100px; margin-right:10px;" />
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
			</script>
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
