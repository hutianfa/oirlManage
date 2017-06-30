<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.ltmcp.comm.Comm"%>
<%@page import="com.ltmcp.entity.Admin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="common/css/163css.css"/>
    <link rel="icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="common/css/menu.css"/>
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/basic.css"> 
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/font-awesome.css">  
    <link rel="stylesheet" type="text/css" href="css/fakeLoader.css"/> 
    <script type="text/javascript" src="js/defaultdate.js"></script>
    <script type="text/javascript" src="bootstrap-3.3.6-dist/js/jquery.metisMenu.js"></script>
    <script type="text/javascript" src="js/fakeLoader.js"></script>         
    <script type="text/javascript">	   
       var endDate=GetDatee();
       var starDate=GetDates();    
       function jumpwaybill(){
			url="waybill_list?condition.status="+0+"&condition.empowerType=="+false+"&condition.tag="+70+"&condition.beginTime="+starDate+"&condition.endTime="+endDate+"";			
			location.href=url;	
	   }
       function jumpexcpt(){
			var excurl="excRecord_list?condition.beginTime="+starDate+"&condition.endTime="+endDate+"";  
			location.href=excurl;        
	   }
       /* function jumphasexc(){
			var hasexcurl="excRecord_allHasHandle?condition.beginTime="+starDate+"&condition.endTime="+endDate+"";
			location.href=hasexcurl;
	   } */
       function jumpfixlist(){
    	   var fixurl="fixSea_list?condition.beginTime="+starDate+"&condition.endTime="+endDate+"&condition.status=10";
    	   location.href=fixurl;
       }
       /* function jumpapp(){
			url="appNum_num?condition.status="+'NO'+"&condition.beginTime="+starDate+"&condition.endTime="+endDate+"";			
			location.href=url;	
	   } */
    </script>     
  </head>
     <div id="fakeloader"></div>
     <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
               <li>
                  <div class="user-img-div">
                      <span style="color:#fff;"><img src="common/img/admin.jpg" style="border:3px solid #000333;-o-border-radius: 50%;
											-moz-border-radius: 50%;-webkit-border-radius: 50%;border-radius: 50%;"/></span>
                      <div class="inner-text">
                         <%
				       		Admin admin=(Admin)session.getAttribute(Comm.CURRENT_ADMIN);
				       		if(null!=admin){
				       			out.print(admin.getAdmName());
				       		}else{
				       			out.print("您好!");
				       		}
				        %> 欢迎您！
                      <br>
                          <small>登录时间: <%
                           Date date=new Date();
                           DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                           String time=format.format(date);
                          	out.print(time);  
                          %></small>
                      </div>
                  </div>
               </li>
                <li>
                    <a class="active-menu" href="main"><i class="fa fa-home fa-x"></i>首页</a>
                </li>
                <li id='ord'>
                   <a href="#"><i class="fa fa-inbox "></i>订单信息管理 <span class="fa arrow"></span></a>
                   <ul class="nav nav-second-level collapse">
                    <li>
                       <a href="reportForm_list">订单记录</a>
                    </li>
                   </ul>
                </li>
                <li id='sto'>
                  <a href="#"><i class="fa fa-paste "></i>库存信息管理 <span class="fa arrow"></span></a>
                  <ul class="nav nav-second-level collapse inout-nav">
                    <%-- <li class="instro">
                        <a href="#"><i class="fa fa-outdent fa-x"></i>分公司分发<span class="fa  arrow"></span></a>
                        <ul class="nav nav-third-level collapse">
                            <li>
                                <a href="reportForm_detailed" >入库记录</a>	                                
                            </li>  
                            <li>
                                <a href="reportForm_inarea" style="padding-left:60px;">片区入库记录</a>	                                
                            </li>  
                            <li>
                                <a href="reportForm_inoil" style="padding-left:60px;">油库入库记录</a>	                                
                            </li> 
                            <li>
                                <a href="reportForm_instation" style="padding-left:60px;">油站入库记录</a>
                            </li>  
                            <li>
                                <a href="reportForm_orderlist" >分发记录</a>	                               
                            </li>                                                
                        </ul>
                    </li>  --%>
                    <!--库存记录 -->
                    <li class="outstro">
                      <a href="#"><i class="fa fa-indent fa-x"></i>分公司库存<span class="fa  arrow"></span></a>
                        <ul class="nav nav-third-level collapse"> 
                            <li>
                                <a href="reportForm_tonowlist">库存记录</a>
                            </li>   
                            <!-- <li>
                                <a href="reportForm_outarea" style="padding-left:60px;">片区出库记录</a>
                            </li>
                            <li>
                                <a href="reportForm_outoil" style="padding-left:60px;">油库出库记录</a>
                            </li> -->                          
                        </ul>
                    </li>
                   </ul>
                </li>               
                <li id="wyb">
                   <a href="#"><i class="fa fa-desktop "></i>日常信息管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                       <li>
                           <a href="javascript:void(0)" onclick="jumpwaybill();">施解封记录</a>
                       </li>
                       <!-- <li>
                           <a href="javascript:void(0)" onclick="jumpfixlist()">固定签封</a>
                       </li>  -->
                       <li>
                            <a href="javascript:void(0)" onclick="jumpexcpt();">异常记录 </a>
                       </li>
                   </ul>
                </li>
                <li id="dat">
                  <a href="#"><i class="fa fa-cloud "></i>分析统计管理<span class="fa arrow"></span></a>
                     <ul class="nav nav-second-level collapse">                          
                        <li>
                            <a href="reportForm_tjlist">异常统计分析</a>
                        </li>
                       <!--  <li>
                            <a href="reportForm_instation">设备统计记录</a>
                        </li> -->                     
                    </ul>
                </li>
                <%-- <li id="exc">
                    <a href="#"><i class="fa fa-briefcase "></i>异常信息管理 <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">                           
                         <li>
                            <a href="javascript:void(0)" onclick="jumpexcpt();">异常记录 </a>
                        </li>
                         <!-- <li>
                            <a href="javascript:void(0)" onclick="jumphasexc();">已处理异常</a>
                        </li> -->        
                    </ul>
                </li> --%>
                <li id="bbo">
                    <a href="#"><i class="fa fa-bar-chart"></i>报表信息管理<span class="fa arrow"></span></a>
                     <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="report_Benefitlist">效益之星报表</a>
                        </li>
                        <li>
                            <a href="effic_efficlist">效率之星报表</a>
                        </li>
                         <li>
                            <a href="safe_Seculist">安全之星报表</a>
                        </li>
                    </ul>
                 </li>                 
                <%--<li id="thing">
                    <a href="#"><i class="fa fa-credit-card "></i>签封入库管理<span class="fa arrow"></span></a>
                     <ul class="nav nav-second-level collapse">                          
                         <li>
                            <a href="thing_Allthing">签封入库记录 </a>
                        </li>                           
                    </ul>
                </li> --%>
                <li id="bas">
                    <a href="#"><i class="fa fa-cogs "></i>基础数据管理<span class="fa arrow"></span></a>
                     <ul class="nav nav-second-level collapse inout-nav">
                        <li class="pernav">
                            <a href="#"><i class="fa fa-user fa-x"></i>员工管理<span class="fa  arrow"></span></a>
                            <ul class="nav nav-third-level collapse">
                                <li>
                                    <a href="person_list">操作员信息</a>
                                </li>
                                <s:iterator value="#session.ADMIN_POWER">
           		             		<s:if test="poUrl=='admin_listGeneralManager'">
	                                 <li>
	                                     <a href="admin_listGeneralManager?condition.status=0">Web管理员信息 </a>
	                                 </li>
									</s:if>
								</s:iterator>
                            </ul>
                        </li>
                        <%-- <s:iterator value="#session.CURRENT_ADMIN">
          	    		 <s:if test="admName=='tr'">
                         <li class="appnav">
                             <a href="#"><i class="fa fa-tablet"></i>App升级<span class="fa arrow"></span></a>
                             <ul class="nav nav-third-level collapse">
                                 <li>
                                     <a href="javascript:void(0)" onclick="jumpapp();">App升级记录</a>
                                 </li>                                   
                             </ul>
                         </li>
                          </s:if>
                        </s:iterator>
                        <s:iterator value="#session.CURRENT_ADMIN">
          	    			<s:if test="admName=='tr'">
	                         <li class="innav">
	                             <a href="#"><i class="fa fa-paste"></i>录入管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-third-level collapse">
	                                 <li>
	                                     <a href="in_list">油损录入</a>
	                                 </li>                                  
	                             </ul>
	                         </li>
                       	 </s:if>
                        </s:iterator> --%>
                        <s:iterator value="#session.ADMIN_POWER">
          	    			<s:if test="poUrl=='empower_Alllist'">
	                         <li class="empnav">
	                             <a href="#"><i class="fa fa-lock"></i>授权管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-third-level collapse">
	                                 <li>
	                                     <a href="empower_Alllist">授权信息</a>
	                                 </li>                                  
	                             </ul>
	                         </li>
	                        </s:if>
                        </s:iterator>
                        <li class="carnav">
                            <a href="#"><i class="fa fa-truck"></i>车辆管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level collapse">
                                <li>
                                    <a href="car_list">车辆信息</a>
                                </li>
                            </ul>
                        </li>
                         <li class="posnav">
                            <a href="#"><i class="fa fa-inbox"></i>站点管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level collapse">
                                <li>
                                    <a href="position_list">站点信息</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>                 
            </ul>
        </div>
        </nav>  
       <script type="text/javascript" src="js/menudef.js"></script>   			