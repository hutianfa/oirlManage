  /**
   * author：hutianfa
   * describe：非法异常和异常模块切换
   */
   $(function(){
   	 //获取浏览器地址参数
     function getQueryString(id) { 
  	   	 var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i");
  	   	 var r = window.location.search.substr(1).match(reg); 
  	   	 if (r != null) return decodeURIComponent(r[2]); return ""; 
     }
     var idtype=getQueryString("condition.excType"); 
  	 $("#divcontent1,#divcontent2").hide();
  	 if(idtype=="5" || idtype=="10" || idtype=="13"){//正常情况三种异常
  		 $("#divcontent1").show();$("#divcontent2").remove();
  	 }else if(idtype=="1" || idtype=="2"){//解封码的异常		      		 
  		$("#divcontent2,#freediv,#freepic").show();$("#divcontent1,#seadiv,#seapic,#carbx").remove();
  	 }else if(idtype=="0"){
  		$("#divcontent2,#seaddiv,#seapic").show();$("#divcontent1,#freediv,#freepic,#carbx").remove();	
  	 }else{
  		 $("#divcontent1").show();$("#divcontent2").remove();
  	 }
   });
    /**
     * author：hutianfa
     * describe：条件查询
     * @type 
     */
    var starttime,endtime,comname="",posname="",areaname="",carid="",jumpurl,pername="",exctype,opval=1;
   function comcondition(){
    	starttime= $("#starttime").val();endtime = $("#endtime").val();
		posname=$("#pos1 .selected").attr("data-value");
		areaname=$("#area1 .selected").attr("data-value");
		comname=$("#coma1 .selected").attr("data-value");
		pername=$("#per1 .selected").attr("data-value");
		exctype=$("#exc1 .selected").attr("data-value");
		carid=$("#carid .selected").attr("data-value");
		(carid=="" || carid==null) ? carid="" : carid=carid;
		(posname=="" || posname==null) ? posname="" :posname=posname;
		(comname=="" || comname==null) ? comname="" : comname=comname;
		(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
		(pername=="" || pername==null) ? pername="" : pername=pername;
		(exctype=="" || exctype==null) ? exctype="" : exctype=exctype;
    }
	function nextJump(){ 
		comcondition();
		if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_list?condition.perId="+pername+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.posid="+posname+"&condition.comId="+comname+"&pageBean.curentPage=";
 		}
		else if(exctype=="0" || exctype=="1" || exctype=="2"){
	    	jumpurl="excRecord_illegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.excStatus="+1+"&condition.posid="+posname+"&pageBean.curentPage=";
		}	
		commnextJump(jumpurl);
	}
	function preJump(){ 
		comcondition();
		if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_list?condition.perId="+pername+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.posid="+posname+"&condition.comId="+comname+"&pageBean.curentPage=";
		}
		else if(exctype=="0" || exctype=="1" || exctype=="2"){
	    	jumpurl="excRecord_illegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.excStatus="+1+"&condition.posid="+posname+"&pageBean.curentPage=";
		}
		commpreJump(jumpurl);
	}
	function jumpPage(){
		comcondition();
		if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_list?condition.perId="+pername+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.posid="+posname+"&condition.comId="+comname+"&pageBean.curentPage=";
		}
		else if(exctype=="0" || exctype=="1" || exctype=="2"){
	    	jumpurl="excRecord_illegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.excStatus="+1+"&condition.posid="+posname+"&pageBean.curentPage=";
		}
		commjumpPage(jumpurl);
	}
	//查询方法
	function searchExcRecord(){
		comcondition();
	    if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_list?condition.perId="+pername+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.posid="+posname+"&condition.comId="+comname+"&pageBean.curentPage=";           	        	
	    }
	    else if(exctype=="0" || exctype=="1" || exctype=="2"){
	    	jumpurl="excRecord_illegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&condition.excStatus="+1+"&pageBean.curentPage=";
	    }
		commSearch(jumpurl);
	}
	/**
	 * author：hutianfa
	 * describe：添加操作
	 * @type 
	 */	
   var temp,newtemp;
   function delillegality(newobj){	  
   	   $("#myModal1").modal("show");
	  newtemp=newobj.id;
   }
   /* 正常状态下的处理操作 */
  function delexc(obj){	 
	 /* if(navigator.userAgent.indexOf("MSIE") >=0){
			// $("#example").css({ _position : 'absolute','_top' : '30%' , '_left' : '40%','min-width':'450px',display:'block'});
	  }else{   
			var left = ($(window).width() - $("#example").width())/2; 	
			var scrollY = document.documentElement.scrollTop || document.body.scrollTop;    //滚动条解决办法
			var top = (window.screen.height / 4) + scrollY - 120;  //滚动条解决办法
			 $("#example").css({ position : 'absolute','top' : top , left : left,'background-color':'#fff',
				 				'width':'450px',display:'block',height:'300px'});
			 $('#example').modal('show'); $("#example").removeClass('hide');
	  }
	  $(window).resize(function() {
		  var scrollY = document.documentElement.scrollTop || document.body.scrollTop;    //滚动条解决办法
		  var top = (window.screen.height / 4) + scrollY - 120;  //滚动条解决办法   
		  var left = ($(window).width() - $("#example").width())/2; 
		  $("#example").css( { position : 'absolute','top' : top , left : left,'min-width':'450px'} );
		  //$("#example").css({height:$(window).height()});
	  });*/
  	  $("#myModal").modal("show");
	  temp=obj.id;
   }
   $("#close,#newclose").click(function(){
   	$("#myModal").modal("hide"); $("#myModal1").modal("hide");
   });
   $("#cancel,#newcancel").click(function(){
	  $("#myModal1").modal("hide"); $("#myModal1").modal("hide");
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
            		   location.reload();
            	   }else{
            		   $104(this).dialog("close");  
            	   }  
               }
           }
       });
     }  
	 function newmessage(text,newdata) {
	   var newval=newdata;
       $("#spanmessage").text(text);
       $104("#message").dialog({
           title:"中石油油罐车运输云平台，提示您！",
           modal: true,
           buttons: {
               "确定": function() {
            	   if(newval ==0){
            		   $104(this).dialog("close");	  	            		   
            		   location.reload();
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
 /**
  * author：hutianfa
  * describe：异常处理数据
  */
 $("#surebtn").click(function(){
   	/* $(window).resize(function() {
		  var top = ($(window).height() - $("#example").height())/2;   
		  var left = ($(window).width() - $("#example").width())/2; 
		  $("#example").css( { position : 'absolute','top' : top , left : left,'min-width':'450px'} );
		  $("#example").css({height:$(window).height()});
	  });
      $('#example').modal('hide'); $("#example").addClass('hide');*/
 	 $("#myModal").modal("hide");
     queren("确认要提交吗？", function(){
	   	var id=temp;
	   	var excText=$("#txtconment").val();
		var url="excRecord_addExcHandleMethod?id="+id+"&excText="+excText;
		 $("#myModal").modal("hide");
		    $.get(url,function(data){
			  if(data==0){
				 $(".delbtn").val("已处理");
				 message("处理成功！",data);
			  }else{
				 message("失败！无该操作权限或系统错误!",data);
				  $("#myModal").modal("show");
	  		 }
	     });
  		return true;
      });   
    });
    /***
     * author：hutianfa
     * describe：非法异常添加操作
     */
    $("#newsurebtn").click(function(){
     $("#myModal1").modal("show");
     queren("确认要提交吗？", function(){
   	    var newid=newtemp;
    	var newexcText=$("#newtxtconment").val();
	    var newurl="excRecord_illegalitychange?newid="+newid+"&newexcText="+newexcText;
	     $("#myModal1").modal("hide");
	    $.get(newurl,function(data){
		  if(data==0){
			 $(".newdelbtn").val("已处理");
			 newmessage("处理成功！",data);
		  }else{
			 newmessage("处理失败!",data);
      	  }
      	});
      	return true;
      });   
    });
 });         
	