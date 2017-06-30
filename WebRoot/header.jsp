<%@page import="com.ltmcp.comm.Comm"%>
<%@page import="com.ltmcp.entity.Admin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/basic.css"> 	
	<script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script> 
  	<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
      <div class="navbar-header header-left">              
          <a class="navbar-brand" href="javascript:void(0);"><b>中石油油罐车运输</br>管理云平台 </b> </a>                        
      </div>				           		 	                        	     
      <div class="navbar-custom-menu header-right">
        <ul class="nav navbar-nav">                
            <li class="dropdown notifications-menu">
               <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning"><s:property value="wayBillExcrecordCount" default="0"/></span>
               </a>
            </li>
            <li class="dropdown user user-menu">
               <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="padding-top:10px;">
                   <span class="fa fa-user fa-x"></span>&nbsp;<span style="font-size:14px;">系统管理员</span><span class="caret"></span>
               </a>
               <ul class="dropdown-menu pull-right" style="min-width:137px;">
                  <li class="text-center"><a href="updatepwd"><span class="text-primary"><i class="fa fa-edit fa-x"></i>&nbsp;账号设置</span></a></li>
		          <li class="text-center"><a href="javascript:void(0)"><span class="text-primary"><i class="fa fa-envelope fa-x"></i>&nbsp;消息设置</span></a></li>
		          <li class="divider"><a href="javascript:void(0)"></a></li>
		          <li class="text-center"><a href="exit"><span class="text-primary"><i class="fa fa-power-off fa-x"></i>&nbsp;退出登录</span></a></li>
               </ul>
            </li>
        </ul>
    </div>          
   </nav>