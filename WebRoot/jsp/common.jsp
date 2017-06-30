<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="common/css/163css.css"/>
    <link rel="icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery-ui.css"/>
<!--     <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>  -->
    <script type="text/javascript">
	    engine=null;
	   if(window.navigator.appName=="Microsoft Internet Explorer")
	    {
	       if(document.documentMode)//IE8
			    engine=document.documentMode;
	       else//IE 
	      {
			   engine=5; 
		       if(document.compatMode)
		       {
		         if(document.compatMode=="CSS1Compat")
		           engine=7;
		       }
	      }
	    }
	   function GetDatee()  
       {  
      	   var d = new Date();
       	   var year = d.getFullYear();
       	   var mon=d.getMonth()+1;
       	   var day=d.getDate()+1;     
       	   s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day);            
         return s;     
       }  
       function GetDates()  
       {  
      	 var n = 7;
     	    var d = new Date();
     	    var year = d.getFullYear();
     	    var mon=d.getMonth()+1;
     	    var day=d.getDate();
     	    if(day <= n){
     	            if(mon>1) {
     	               mon=mon-1;
     	            }
     	           else {
     	             year = year-1;
     	             mon = 12;
     	             }
     	           }
     	          d.setDate(d.getDate()-n);
     	          year = d.getFullYear();
     	          mon=d.getMonth()+1;
     	          day=d.getDate();
     	     s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day);
     	     return s;    
       }   
       var endDate=GetDatee();
       var starDate=GetDates();
       function jumpwaybill(){
			var url="waybill_list?condition.status="+0+"&condition.beginTime="+starDate+"&condition.endTime="+endDate+"";
			location.href=url;
		}
       function jumpexcpt(){
			var excurl="excRecord_list?condition.beginTime="+starDate+"&condition.endTime="+endDate+"";
			location.href=excurl;
		}
       function jumphasexc(){
			var hasexcurl="excRecord_allHasHandle?condition.beginTime="+starDate+"&condition.endTime="+endDate+"";
			location.href=hasexcurl;
		}
       function jumpfixlist(){
    	   var fixurl="fixSea_list?condition.endTime="+endDate+"";
    	   location.href=fixurl;
       }
    </script>
    <style type="text/css">
    	.open{display:block;}
        .container{width:100%;height:auto;margin:auto;}
		/*left*/
		.leftsidebar_box{width:auto;height:auto !important;overflow:visible !important;height:96.2% !important;background-color:#035392;}
		.line{height:2px;width:100%;background-image:url(common/img/left/line_bg.png);background-repeat:repeat-x;}
		.leftsidebar_box dt{padding-left:40px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#f5f5f5;font-size:14px;position:relative;line-height:48px;*line-height:48px;cursor:pointer;}
		.leftsidebar_box dd{background-color:#07599D;padding-left:40px;display:none;}
		.leftsidebar_box dd a{color:#f5f5f5;line-height:20px;*line-height:20px;font-size:14px;}
		.leftsidebar_box dd a:hover{color:#ccc;}
		.leftsidebar_box dt img{position:absolute;right:10px;*top:1px;top:20px;}
		.system_log dt{background-image:url(common/img/left/custom.png)}
		.custom dt{background-image:url(common/img/left/system.png)}
		.channel dt{background-image:url(common/img/left/channel.png)}
		.app dt{background-image:url(common/img/left/syetem_management.png)}
		.cloud dt{background-image:url(common/img/left/cloud.png)}
		.soucebund dt{background-image:url(common/img/left/source.png)}
		.statistics dt{background-image:url(common/img/left/statistics.png)}
		.home dt{background-image:url(common/img/left/app.png)}
		.leftsidebar_box dl dd:last-child{padding-bottom:10px;}   
		a:hover{ text-decoration:none;}
		.down-left{background-color:#075AE4;}
    </style> 
  </head>
    <div class="down-left">
      <div class="left-title">中石油运输管理<span>&nbsp;&nbsp;&nbsp;&nbsp;</span></div>
        <div class="warpmenu">
           <div class="container">
			<div class="leftsidebar_box">
				<div class="line"></div>
				<dl class="custom">
					<dt ><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;签封管理<span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd" id="menu6">
		             <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                 <li class="subitem1" style="height:30px;"><a href="javaScript:void(0);" onclick="jumpwaybill();">活动签封记录 <span></span></a></li>
<%-- 		             <li class="subitem1" style="height:30px;"><a href="waybill_tonowlist">实时运单 <span></span></a></li>
 --%>		             <%-- <s:iterator value="#session.ADMIN_POWER">
              		      <s:if test="poUrl=='photo_photolist'">
		                    <li class="subitem1" style="height:30px;"><a href="photo_photolist">图片审核<span></span></a></li>
		                  </s:if>
              		     </s:iterator> --%>              		     
              		      <li class="subitem1" style="height:30px;"><a href="javaScrip:jumpfixlist();" onclick="">固定签封记录 <span></span></a></li> 
		             </ul>
		            </dd>
				</dl>  
				<dl class="statistics">
					<dt><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;报表管理 <span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd" id="menu8">
		               <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                    <li class="subitem1" style="height:30px;"><a href="report_Benefitlist">效益之星报表<span></span></a></li>
		                    <li class="subitem1" style="height:30px;"><a href="effic_efficlist">效率之星报表<span></span></a></li>
		                    <li class="subitem1" style="height:30px;"><a href="safe_Seculist">安全之星报表 <span></span></a></li>
		                    <%-- <li class="subitem1" style="height:30px;"><a href="photo_reportList">审图信息报表 <span></span></a></li> --%>		                    
		                    <li class="subitem1" style="height:30px;"><a href="reportForm_list">数据统计信息报表 <span></span></a></li>
		                </ul>
		            </dd>
				</dl>
					
				<dl class="cloud">
					<dt><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;异常管理 <span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd" id="menu7">
		               <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                    <li class="subitem1" style="height:30px;"><a href="javascript:void(0)" onclick="jumpexcpt();">未处理异常<span></span></a></li>
<%-- 		                    <li class="subitem1" style="height:30px;"><a href="excRecord_queryExcParcent">时间异常信息<span></span></a></li>  
 --%>    				        <%-- <li class="subitem1" style="height:30px;"><a href="excRecord_queryTimeOut">超时异常信息<span></span></a></li> --%>
    				        <li class="subitem1" style="height:30px;"><a href="javascript:void(0)" onclick="jumphasexc();">已处理异常<span></span></a></li>
    				 </ul>
		            </dd>
				</dl>
				<dl class="app">
					<dt onClick="changeImage()"><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;站点管理<span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd" id="menu4">
		              <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                <li class="subitem1" style="height:30px;"><a href="position_list">站点信息 <span></span></a></li>
		                <%-- <li class="subitem1" style="height:30px;"><a href="positionExamine_list">位置审核信息 <span></span></a></li>  --%>
		                <%-- <s:iterator value="#session.ADMIN_POWER">
              		      	<s:if test="poUrl=='position_addCardNumber'">
		                		<li class="subitem1"><a href="position_addCardNumber">绑定上传数据<span></span></a></li>
		              		</s:if>
              		    </s:iterator> --%>
		              </ul>
		            </dd>
				</dl> 	
				<dl class="channel">
					<dt><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;车辆管理<span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd" id="menu5">
		             <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                 <li class="subitem1"><a href="car_list">车辆信息 <span></span></a></li>
		              </ul>
		            </dd>
				</dl>
				<s:iterator value="#session.ADMIN_POWER">
              	  <s:if test="poUrl=='empower_Alllist'">
				<dl class="soucebund" >
					<dt><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;授权管理<span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd" id="menu2">
		             <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                 <li class="subitem1"><a href="empower_Alllist">授权信息 <span></span></a></li>
		              </ul>
		            </dd>
				</dl>
				 </s:if>
               </s:iterator>  
               <s:iterator value="#session.ADMIN_POWER">
              	  <s:if test="poUrl=='thing_Allthing'">
					<dl class="home" >
						<dt><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;出入库管理<span></span></a><img src="common/img/left/select_xl01.png"></dt>
						<dd class="first_dd" id="menu3">
			             <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
			                 <li class="subitem1" style="height:30px;"><a href="thing_Allthing">签封出入库记录 <span></span></a></li>			                 
			              </ul>
			            </dd>
					</dl>
				</s:if>
               </s:iterator>    
				<dl class="system_log">
					<dt><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;员工管理 <span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd" id="menu1">
		             <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                 <li class="subitem1" style="height:30px;" ><a href="person_list">操作员管理 <span></span></a></li>
		                 <s:iterator value="#session.ADMIN_POWER">
              		      <s:if test="poUrl=='admin_listGeneralManager'">
		                   <li class="subitem1" style="height:30px;"><a href="admin_listGeneralManager">Web管理员管理 <span></span></a></li>
		                 </s:if>
              			</s:iterator>  
		             </ul>
		            </dd>
				</dl>
				
				 <!-- <dl class="soucebund">
					<dt><a href="javascript:void(0)" style="*width:79px;">&nbsp;&nbsp;数据管理 <span></span></a><img src="common/img/left/select_xl01.png"></dt>
					<dd class="first_dd">
		               <ul class="no" style="list-style:initial;list-style-type:inline;color:#fff">
		                    <li class="subitem1"><a href="position_addCardNumber">绑定上传数据<span></span></a></li>
		                </ul>
		            </dd>
				</dl>  -->
				
			</div>
		</div>
       </div>
      </div>
      <%-- <input type="hidden" class="menuval" value="${condition.xunhao }" /> --%>
      <script type="text/javascript">
		$(".leftsidebar_box dt").css({"background-color":"#035392"});
		$(".leftsidebar_box dt img").attr("src","common/img/left/select_xl01.png");
		 $(function(){
			$(".leftsidebar_box dt").click(function(){
				$(".leftsidebar_box dd").hide();
				$(".leftsidebar_box dt").css({"background-color":"#035392"});
				$(this).css({"background-color": "#07599D"});
				$(this).parent().find('dd').removeClass("menu_chioce");
				$(".leftsidebar_box dt img").attr("src","common/img/left/select_xl01.png");
				$(this).parent().find('img').attr("src","common/img/left/select_xl.png");
				$(".menu_chioce").slideUp(); 
				$(this).parent().find('dd').slideToggle();
				$(this).parent().find('dd').addClass("menu_chioce");				
			});	
			
		}); 
		
        /* var menu="menu"+$(".menuval").val();
   	    console.log($(".menuval").val());
    	menu=="menu1" ? $("#menu1").css({"display":"block"}):""; */
      </script>