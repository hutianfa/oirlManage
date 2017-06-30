<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">   
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/jquery.searchableSelect.css"/>
		<link rel="stylesheet" type="text/css" href="exception/css/excmanage.css"/>
	  <link rel="stylesheet" type="text/css" href="common/css/comm.css" />
	  <link rel="stylesheet" type="text/css" href="common/css/styles.css"/>  
	  <link type="text/css" rel="stylesheet" href="css/jquery.autocomplete-new.css"> 
	  <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css"/>
  	 <script type="text/javascript" src="bootstrap-3.3.6-dist/js/bootstrap-modal.js"></script>
  	 <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	 <script type="text/javascript">
  	   var $182=$;
  	 </script> 
  	 <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  	 <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
  	 <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/development-bundle/ui/minified/jquery.ui.dialog.min.js"></script>
  	 <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  	 <script type="text/javascript" language="javascript" src="jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
	 <script type="text/javascript">
  	   var $104=$;
  	 </script>
	 <script type="text/javascript" language="javascript" src="js/jquery.autocomplete.min.js"></script>
	 <script type="text/javascript" src = "js/findCompanyList.js"></script>
	 <style type="text/css">
      .box{float:left;padding:5px;} .input-group-addon{width:auto;display:inline;}
  	  .table tr th,tr td {word-break: keep-all;}
    </style>   
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
		       	<small><i class="fa fa-tasks fa-2x"></i></small>&nbsp;油损记录		       	
		      </h3>		     
		   </div>							   
		 </div>
		 <div style="clear:both;"></div>
		 <div class="panel panel-info" style="margin-top:5px;margin-bottom:5px;">
		   <div class="panel-heading" style="padding:5px;">
		      <h4 class="panel-title" style="text-align:left;">按条件查询：</h4>
		    </div>	
	           <div class="panel-body">	
              	  <div class="box"><span class="input-group-addon" style="display:inline;">车牌号</span>
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
                  <div class="box"> 
                <span class="input-group-addon" style="display:inline;"><span >开始时间</span></span>
              	<span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="starttime" value="${condition.beginTime}"></span>
			  </div>
			  <div class="box">
			    <span class="input-group-addon" style="display:inline;"><span >结束时间</span></span>
			    <span class="demo1" style="margin-left:-5px;"><input style="height:28px;line-hieght:28px;" class="laydate-icon" id="endtime" value="${condition.endTime}"></span>            					               
			  </div>
			   <div class="box">              	               	  
                  <span style="width:80px;right:5%;float:left;">
                  <input type="button" value="查询" id="searchbtn" class="search btn btn-primary" style="height:2.3em;width:80px;" onclick="searchglibRecord()"/></span>
               </div>
               <div class="box">              	               	  
                  <span style="width:80px;right:5%;float:left;">
                  <input type="button" value="添加" id="addbtn" class=" search btn btn-primary" style="height:2.3em;width:80px;" /></span>
               </div>
             </div>
         </div>
          <script type="text/javascript" src="js/laydate.js"></script>
          <script type="text/javascript" src="js/Blob.js"></script>
          <script type="text/javascript" src="js/FileSaver.js"></script>
          <script type="text/javascript">
          !function(){
            	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
            	laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
            	laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});            	
            }();
	      </script>
         <div class="widget orange">
            <div class="widget-title">
                <h4 style="margin:0 auto;line-height: 36px;"><i class="fa fa-reorder"></i>油损信息记录</h4>                   
            </div>
            <div class="widget-body" style="overflow:auto;height:54em;">
              <table class="table table-striped table-bordered table-advance table-hover" id="tableExcel">  
             <thead>
              <tr>
	               <th style="width:90px;">车辆号</th>
	               <th style="">所属公司</th>
	               <th style="">总运输容量</th>
	               <th style="">油品</th> 
	               <th style="">总油损容量</th>	               	               
	               <th style="">运输时间</th>	               		                           
              </tr>              
              </thead> 
	          <tbody>	            
	             <s:iterator value="li">
	             	<tr class="info">
	             	     <td><s:property value="carFlaper" /></td>
	             	     <td><s:property value="comName" /></td>	             	     
		                 <td><s:property value="petrol_total" /></td>
		                 <td><s:property value="sea_oilpin" /></td> 
		                 <td><s:property value="petrol_loss"/> </td>
		                 <td><s:property value="time"/> </td>	             		 	                
	             	</tr>
	             </s:iterator>
	            </tbody>
             </table>             
           </div>
           </div>                                 
           <div class="table-bottom">
            <s:if test="pageBean.totalCount !=''">
             <div style="width:60%;float:left;">
               <span style="float:left;">共有<a><s:property value="pageBean.totalCount"/></a> 条记录，当前是第<a><s:property value="pageBean.curentPage"/></a>页</span>
             </div>
            </s:if>
             <div style="width::38%;float:right;">
                <span class="table-bottom-page">
                <s:if test="pageBean.curentPage>1">
                	<a  onclick="javascript:preJump()">上一页 </a>&nbsp;
                </s:if>
                <s:if test="pageBean.curentPage<pageBean.totalPage">
	                <a  onclick="javascript:nextJump()">下一页  </a>
                </s:if>
                <s:if test="pageBean.totalPage>1">
                	&nbsp;跳转到 <input type="text" value='<s:property value="pageBean.curentPage"/>' style="width:3em;" id="jumpPage"/> 页&nbsp;<a href="javascript:void(0);" onclick="jumpPage();">  跳转  </a></span>
                </s:if>
             </div>
           </div>            
           <input type="hidden" value="<s:property value="pageBean.curentPage"/>" id="curentPage">
           <input type="hidden" value="<s:property value="pageBean.totalPage"/>" id="totalPage">
          </div>
      </div>
    </div>
    <script type="text/javascript" src="js/commSearch.js"></script>
    <script type="text/javascript"  src="js/findCarList.js"></script>
    <script type="text/javascript" src="js/searchsuosou.js"></script>
      <script type="text/javascript">
         var starttime,endtime,carid;  	       
     	 starttime= $("#starttime").val();endtime = $("#endtime").val();
     	 carid=$("#carid .selected").attr("data-value");
		(carid=="" || carid==null) ? carid="" : carid=carid;
  		 var jumpurl="in_list?condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.carFlapper="+carid+"&pageBean.curentPage=";
   		function nextJump(){ commnextJump(jumpurl);}
   		function preJump(){ commpreJump(jumpurl);}
   		function jumpPage(){commjumpPage(jumpurl);}
     	function searchglibRecord(){
   			starttime= $("#starttime").val();endtime = $("#endtime").val();
   			carid=$("#carid .selected").attr("data-value");
    		(carid=="" || carid==null) ? carid="" : carid=carid;
   			var url="in_list?condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.carFlapper="+carid;
   			commSearch(url);
     	}
     </script>
    <!-- 右边部分完结-->
    <!-- 弹出层界面-->
      <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加油损信息</h4>
	      </div>
	      <div class="modal-body">
	         <form id="addformper" action="" method="" class="tabfrom-ys">
	            <div class="widget orange"> 
		            <div class="widget-body" style="overflow:auto;">
		              <table class="table table-striped table-bordered table-advance table-hover" id="updatab"> 
		          		<tr style="height:30px;">
	                    <td><div class="box"><span class="input-group-addon" style="display:inline;">车牌号</span>
	                      <input type="text" value="" id="carflag" class="comsty"  data-toggle="tooltip" data-placement="bottom" title="车牌号不能为空！"/>
	                      </div>
	                      </td>
	                    <td>
	                     <div class="box"><span class="input-group-addon" style="display:inline;">油品</span>
			              <div tabindex="5" class="searchable-select" id="olisec1">
			              	 <span class="searchable-select-caret"></span>
			              	 <div class="searchable-select-holder">93号汽油</div>
			              	 <div class="searchable-select-dropdown searchable-select-hide">
			              		<!-- <input type="text" class="searchable-select-input"> -->
			              		<div class="searchable-scroll">
			              		  <div class="searchable-has-privious searchable-select-hide">...</div>
			              			<div class="searchable-select-items"  id="olisec" >
			              			   <div class="searchable-select-item  selected"  data-value="93号汽油">93号汽油</div>
			              			   <div class="searchable-select-item  "  data-value="97号汽油">97号汽油</div>
			              			   <div class="searchable-select-item  "  data-value="0号柴油">0号柴油</div>
			              			</div>
			              		  <div class="searchable-has-next searchable-select-hide">...</div>
			              		</div>
			              	  </div>
			                 </div>
			              </div>                        
	                    </td>
	                 </tr> 
	                <tr>
	                   <td><div class="box"><span class="input-group-addon" style="display:inline;">总运输容量</span>
	                       <input type="text" value="" id="pertoltotal" class="comsty" data-toggle="tooltip" data-placement="bottom" title="总运输容量不能为空！"  />
	                      </div>
	                   </td>
	                   <td><div class="box"><span class="input-group-addon" style="display:inline;">总油损容量</span>
	                      <input class="comsty" style="width:130px;" type="text" value="" id="pertolloss" data-toggle="tooltip" data-placement="bottom" title="总油损容量不能为空！"/>
	                      </div>
	                   </td>
	                 </tr> 
	              </table>
	            </div>
	         </div>
	         </form>
	         <span class="errortilp" style="color:red;text-align:center;"></span>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel" >关 闭</button>
	        <button type="button" class="btn btn-primary" id="surebtn">保 存</button>
	      </div>
	    </div>
	  </div>
	</div>   
      <script type="text/javascript">
       $(function () {
    	  $('[data-toggle="tooltip"]').tooltip()
    	});
          var temp;
          $("#addbtn").click(function(){
        	  $("#myModal").modal("show");
			  $("#olisec1").click(function(){
		    	   $(this).children().removeClass("searchable-select-hide");
		    	   $(this).children().show();
		       });
		       $182("#olisec .searchable-select-item").live("click",function(){
		    	   $(this).addClass("selected").siblings().removeClass("selected");
		    	   etype=$("#olisec .selected").attr("data-value");
		    	   var val=$("#olisec div.selected").text();
		    	   $("#olisec1 .searchable-select-holder").text(val);
		    	   $("#olisec1 .searchable-select-dropdown").addClass("searchable-select-hide");
		       });
          });
		  $("#close").click(function(){
			  $("#myModal").modal("hide");
		  });
		  $("#cancel").click(function(){
			  $("#myModal").modal("hide");
		  });
       $(function(){
    	 function message(text,newdata) {
    		   var newval=newdata;
    	       $("#spanmessage").text(text);
    	       $104("#message").dialog({
    	           title:"中石油油罐车运输云平台，提示您！",
    	           modal: true,
    	           buttons: {
    	               "确定": function() {
    	            	   if(newval ==0){
    	            		   $104(this).dialog("close");
    	            		   location.href="in_list";
    	            	   }else{
    	            		   $104(this).dialog("close");  
    	            	   }  
    	               }
    	           }
    	       });
    	   }
       	 var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
     	     $(document.body).append(boardDiv);
     	    function queren(text, callback) {
                $("#spanmessage").text(text);
                $104("#message").dialog({
                    title: "中石油油罐车运输云平台，提示您！",
                    modal: true,
                    resizable: false,
                    buttons: {
                        "是": function() {
                            callback.call();//方法回调
                            $104(this).dialog("close");
                        },
                       "否": function() {
     		                 $104(this).dialog("close");
                        }
                    } 
                });
            }
         $("#surebtn").click(function(){
        	  var carflag = $("#carflag").val();
	  		  var pertoltotal=$("#pertoltotal").val();
	  		  var pertolloss=$("#pertolloss").val();
	  		  var olival=$("#olisec .selected").attr("data-value");
	  		  if(carflag =="" ||carflag == null){
	  			  $(".errortilp").html("车牌号不能为空！");
	  		  }	  		  
	  		  else if(pertoltotal =="" ||pertoltotal == null){
	  			  $(".errortilp").html("总运输容量不能为空！");
	  		  }
	  		  else if(pertolloss =="" ||pertolloss == null){
	  			  $(".errortilp").html("总油损容量不能为空！");
	  		  }
	  		  else{
	       	      $("#myModal").modal("hide");
			      queren("确认要添加吗？", function(){ 
				  	  $.post("in_inputPet",{"petrol.carFlaper":carflag,"petrol.petrol_total":pertoltotal,"petrol.petrol_loss":pertolloss,"petrol.sea_oilpin":olival},
		   				function(data){  
		        	    	   if(data==0){
					   				message("添加成功！",data);
					   			    $(".errortilp").html("");
					   			 }else{
					   				message("添加失败！",data);
					   			    $(".errortilp").html("");
					   			 } 
				      }); 
			      }); 
      		 } 
      });
   });         
  </script>
  <script type="text/javascript" src="js/boswer.js"></script>
  </div>
</div>
</center>
  </body>
</html>
