<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>中石油油罐车运输管理系统</title>
    <base href="<%=basePath%>"> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   	<link rel="stylesheet" type="text/css" href="positonmanage/css/posmanage.css"/>
  	<link rel="stylesheet" type="text/css" href="common/css/comm.css" />
  	<link rel="stylesheet" type="text/css" href="common/css/styles.css"/>
  	<script type="text/javascript" src="js/jquery.min.js"></script>
  	<link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	<script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>

    <style type="text/css">
      .openlay{-moz-opacity: 0.7;-webkit-opacity:0.7;-ms-opacity:0.7;-o-opacity:0.7;opacity:.70;filter: alpha(opacity=70);background:#000;}
      .warptab{height:auto;border:none; width:78%;float:left;box-shadow:none;}
      .warptab tr td{border:none; margin:auto;}
      .del input{ border: 1px solid #364f86;border-radius: 3px;height: 1.5em;outline: medium none;
		    transition: all 0.3s ease-in-out 0s; margin-top:25px;}
      .detail-top{ width:100%;background:#FFFFCC}
      #spanmessage{text-align:center;}#message{ text-align:center;}
       @media screen and (min-width: 768px) and (max-width: 1260px) { 
	     #win{width:25%;}#win .content input{width:140px;}
       }
	   @media screen and (min-width:1260px) and (max-width: 1600px){
	     #win{width:25%;}#win .content input{width:140px;}
	   }
	   @media screen and (min-width:1600px) and (max-width: 1920px) { 
	     #win{width:25%;}
	   }
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
        <div class="rig-top-titletree" style="float:left;text-align: left;">
          <a href="excRecord_allHasHandle">&nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 已处理异常 >></a>
          <a href="javascript:void(0)">&nbsp;&nbsp;<img src="common/img/tubiao1.jpg" /> 异常详细记录</a>
        </div>
      </div> 
      <!-- 异常详情页 -->
      <center>
      <div class="rig-down-all">
          <div class="table-conment">
           <fieldset style="border: 1px solid #ccc;text-align:left;font-size:15px;font-family: '微软雅黑';border-radius:3px;">
           <legend>异常详细记录:</legend>
	           <div style="height:6em;width:100%;background:#FFFFCC;overflow: auto;border-radius:3px;display:block;margin-top:5px;">
	             <div class="detail-top" style="height:auto;">             
	                <h3 style="text-align:left;padding-left:1em;margin-top:5px;color: #035392;">车辆信息</h3>
		           <table class="bordered warptab" style="width:100%;">
		             <tr style="height:30px;">
		               <td>车牌号：<label>${excRecord.sealed.car.carFlapper}</label></td>
		               <td>所属公司：<label><s:property value="excRecord.sealed.company.comName"/> </label></td>
		             </tr>              
		           </table>	           
	              </div>
			   </div>
              <div style="height:14em;width:100%;background:#FFFFCC;overflow: auto;border-radius:3px;display:block;margin-top:5px">
               <div class="detail-top">             
                <h3 style="text-align:left;padding-left:1em;margin-top:5px;color: #035392;">施封信息</h3>
	           <table class="bordered warptab"> 
	             <tr>
	               <td>施封人:<label><s:property value="excRecord.sealed.person.perTrueName"/> </label></td>
	               <td>施封时间:<label><s:property value="excRecord.sealed.seaTime"/> </label></td>
	             </tr>
	             <tr>
	               <td>施封点:<label><s:property value="excRecord.sealed.position.posName"/> </label></td>
	               <td>施封内码:<label><s:property value="excRecord.sealed.dimensionalBarCode.freeze_content"/> </label></td>
	             </tr>
	           </table>
	           <div style="width:19%;float:right;" class="del">
	             <div class="imgdiv" style="">
	                <p style="padding-bottom: 2px;"> 施封图片</p><img class="bigpic" id="seaphone" src="${excRecord.sealed.seaPhoto}" onerror="this.src='common/img/error.jpg'" width="150" height="120" alt="暂未上传照片"/> 
	             </div>	             
	           </div>
               </div>
              </div>
            <div style="height:17em;background:#FFFFCC;width:100%;overflow: auto;border-radius:3px;margin-top:5px">
             <div class="detail-top">
               <h3 style="text-align:left;padding-left:1em;margin-top:5px;color: #035392;">解封信息</h3>
	           <table class="bordered warptab">
	             <tr style="height:30px;">
	               <td>解封人:<label><s:property value="excRecord.sealed.freeze.person.perTrueName"/> </label></td>
	               <td>解封时间:<label><s:property value="excRecord.sealed.freeze.freTime"/> </label></td>
	             </tr>
	             <tr>	             
	             <tr>
	               <td>解封点:<label><s:property value="excRecord.sealed.freeze.position.posName"/> </label></td>
	               <td>状态：<label id="exc_status">已处理</label></td>
	             </tr>
	             <tr>
	               <td>异常类型：<label><s:property value="excRecord.basDict.dictValue"/></label></td>
	               <td>解封外码:<label><s:property value="excRecord.sealed.freeze.dimensionalBarCode.unfreeze_content"/> </label></td>
	             </tr>
	           </table>	          
	            <div style="width:19%;float:right;" class="del">
	             <div class="imgdiv" style="">
	              	<p style="padding-bottom: 2px;margin-top:1em">解封图片</p><img class="bigpic" id="freephone" src="${excRecord.sealed.freeze.frePhoto}" onerror="this.src='common/img/error.jpg'" width="150" height="120" alt="暂未上传照片"/>
	             </div>
	              <div style="display:none;" id="winSelector"></div>
	              <div id="bigView" style="display:none;position:absolute;"><img width="300" height="300" alt="" src="" onerror="this.src='common/img/error.jpg'"/></div>
	           </div>
              </div>
            </div>
            <div style="height:6em;background:#FFFFCC;width:100%;overflow: auto;border-radius:3px;margin-top:5px">
             <div class="detail-top">
               <h3 style="text-align:left;padding-left:1em;margin-top:5px;color: #035392;">处理人信息</h3>
	           <table class="bordered warptab">	             
	             <tr> 
	                <td>处理人:<label><s:property value="excRecord.comName"/> </label></td>
	                <td>处理意见：<label><s:property value="excRecord.excHandleMethod"/></label></td>
	             </tr>
	           </table>	          	            
              </div>
            </div>
           </fieldset>
      </div>
      </div>
      </center>
    </div>
   <!-- 右边部分完结-->
  </div>
</div>
</center>
	<script type="text/javascript">
	$(document).ready(function(){
	    $.fn.decorateIframe = function(options) {
	            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);
	            $(this).each(function() {
	                var $myThis = $(this);
	                //创建一个IFRAME
	                var divIframe = $("<iframe />");
	                divIframe.attr("id", opts.iframeId);
	                divIframe.css({"position":"absolute",
	                               "display":"none",
	                               "display":"block",
	                               "z-index":opts.iframeZIndex,
	                               "border":"0",
	                               "top":"0",
	                               "left":"0"
	                });
	                if (opts.width == 0) {
	                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");
	                }
	                if (opts.height == 0) {
	                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");
	                }
	                divIframe.css("filter", "mask(color=#fff)");
	                $myThis.append(divIframe);
	            });
	    }
	    $.fn.decorateIframe.defaults = {
	        iframeId: "decorateIframe1",
	        iframeZIndex: -1,
	        width: 0,
	        height: 0
	    }
	    //放大镜视窗
	    $("#bigView").decorateIframe();
	    //大视窗看图
	    function mouseover(e) {
	        if ($("#winSelector").css("display") == "none") {
	            $("#winSelector,#bigView").show();
	            $("#bigView img").attr("src",this.src);
	        }
	        $("#winSelector").css(fixedPosition(e));
	        e.stopPropagation();
	    }
	    function mouseOut(e) {
	        if ($("#winSelector").css("display") != "none") {
	            $("#winSelector,#bigView").hide();
	            $("#bigView img").attr("src","");
	        }
	        e.stopPropagation();
	    }
	    $("#seaphone").mouseover(mouseover); //中图事件
	    $("#seaphone,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件
	    
	    /* $("#seaimg").mouseover(mouseover); //中图事件
	    $("#seaimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件 */
	    
	    $("#freephone").mouseover(mouseover); //中图事件
	    $("#freephone,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件
	    
	    /* $("#freimg").mouseover(mouseover); //中图事件
	    $("#freimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件 */

	    var $divWidth = $("#winSelector").width(); //选择器宽度
	    var $divHeight = $("#winSelector").height(); //选择器高度
	    var $imgWidth = $(".bigpic").width(); //中图宽度
	    var $imgHeight = $(".bigpic").height(); //中图高度
	    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

	    $("#bigView").scrollLeft(0).scrollTop(0);
	    function fixedPosition(e) {
	        if (e == null) {
	            return;
	        }
	        var $imgLeft = $(".bigpic").offset().left; //中图左边距
	        var $imgTop = $(".bigpic").offset().top; //中图上边距
	        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
	        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
	        X = X < 0 ? 0 : X;
	        Y = Y < 0 ? 0 : Y;
	        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
	        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

	        if ($viewImgWidth == null) {
	            $viewImgWidth = $("#bigView img").outerWidth();
	            $viewImgHeight = $("#bigView img").height();
	            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
	                $viewImgWidth = $viewImgHeight = 800;
	            }
	            $height = $divHeight * $viewImgHeight / $imgHeight;
	            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
	            $("#bigView").height($height);
	        }
	        var top = ($(document).height() - $("#bigView img").height())/2;   
			var left = ($(document).width() - $("#bigView img").width()/2)/2; 
	        var scrollX = X * $viewImgWidth / $imgWidth;
	        var scrollY = Y * $viewImgHeight / $imgHeight;
	        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 ,"border":"3px solid #ccc"});
	        $("#bigView").css({ "top": top, "left":left });

	        return { left: X, top: Y };
	    }
	});
	</script>
</body>
</html>
