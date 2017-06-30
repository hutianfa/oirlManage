/**
 * author：hutianfa
 * describe：条件查询
 */
var carFlapper=$("#carid .selected").attr("data-value");
	var name=$("#loc").val(),loccc = txtid;
	var jumpurl,locid;locid=$("#locid").val();           		
	(carFlapper=="" || carFlapper==null || carFlapper=="undefined") ? carFlapper="" : carFlapper=carFlapper;
	(name=="" || name==null || name=="undefined") ? name="" : name=name;
	if(loccc !="" && loccc != null){
		jumpurl="car_list?condition.carFlapper="+carFlapper+"&condition.comId="+loccc+"&condition.comName="+name+"&pageBean.curentPage=";
	}else{
		jumpurl="car_list?condition.carFlapper="+carFlapper+"&condition.comId="+locid+"&condition.comName="+locid+"&pageBean.curentPage=";
	}
	function nextJump(){commnextJump(jumpurl);}
	function preJump(){commpreJump(jumpurl);}
	function jumpPage(){commjumpPage(jumpurl);}  
	var url;
	function searchCars(){
		var loccc = txtid;var carFlapper;
		var name=$("#loc").val();
		carFlapper=$("#carid .selected").attr("data-value");
		(carFlapper=="" || carFlapper==null || carFlapper=="undefined") ? carFlapper="" : carFlapper=carFlapper;
		(name=="" || name==null || name=="undefined") ? name="" : name=name;
		if(loccc !="" && loccc != null){
			url="car_list?condition.carFlapper="+carFlapper+"&condition.comId="+loccc+"&condition.comName="+name;    
		} else{
			name=="" ? locid="" : locid=locid;
			url="car_list?condition.carFlapper="+carFlapper+"&condition.comId="+locid+"&condition.comName="+name;   
		}
		commSearch(url);
	}
	/**
	 * author：hutianfa
	 * describe：删除操作
	 * @param {} text
	 * @param {} newdata
	 */
	function message(text,newdata) {
	   var newval= newdata;
       $("#spanmessage").text(text);
       $104("#message").dialog({
           title:"中石油油罐车运输云平台，提示您！",
           modal: true,
           buttons: {
               "确定": function(newdata) {
            	   if(newval ==0){
            		   $104(this).dialog("close");
            		   location.href="car_list";
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
 function delcar(obj){
    queren("确认要删除吗？", function(){
    	var id=obj.id;
		var url="car_del?id="+id;
	    $.get(url,function(data){
		  if(data==0){
			 $("#delbtn").css("display","none");
			 message("删除成功！",data);
		  }else{
			 message("删除失败！",data);
   			  }
   		 });
   		return true;
      });   
  }        
  $(function () {
    $('[data-toggle="tooltip"]').tooltip()
  })
  $("#addbtn,#close,#cancel").click(function(){
    $("#myModal").modal("show");		 		  
  });	
  /**
   * author：hutianfa
   * describe：添加信息操作
   */
  $(function(){
    	 function message(text,newdata) {
    		   var newval= newdata;
    	       $("#spanmessage").text(text);
	       $104("#message").dialog({
	           title:"中石油油罐车运输云平台，提示您！",
	           modal: true,
	           buttons: {
	               "确定": function(newdata) {
	            	   if(newval ==0){
	            		   $104(this).dialog("close");
	            		   location.href="car_list";
	            	   }else{
	            		   $104(this).dialog("close");  
	            	   }  
	               }
	           }
	       });
	   }
   	 var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
 	     $(document.body).append(boardDiv);        
 	    $("#sureaddbtn").click(function(){  
 	  		  var carnumtext = $("#carnum").val();
 	  		  var re=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
 	  		  var activnum=$("#activenum").val();
 	  		  var nochangenum=$("#nochangenum").val();
 	  		  if( carnumtext =="" ||carnumtext == null){
 	  			  $(".errortilp").html("车牌号不能为空！");
 	  		  }
 	  		  else if(activnum =="" ||activnum == null){
 	  			  $(".errortilp").html("活动封签数不能为空！");
 	  		  }
 	  		  else if(nochangenum =="" ||nochangenum == null){
 	  			  $(".errortilp").html("固定封签数不能为空！");
 	  		  }
 	  		  else if(!re.test(carnumtext)){
 	  				 $(".errortilp").html("车牌号不正确！");
 	  			  }
 	  		  else{
 	  			    $("#myModal").modal("hide");
 			   		$.post("car_add",{"addCar.carFlapper":carnumtext,"addCar.CarUnFixFlag":activnum,"addCar.CarFixFlag":nochangenum},
		   				function(data){  
	        	    	   if(data==0){
				   				message("添加成功！",data);
				   			 }else if(data==-1){
				   				message("该车辆已存在！",data);
				   			 }else{
				   				message("添加失败！",data);
				   			 } 
		        	  }); 
 			     	$(".errortilp").html("");
 	  		    }	     	  		
 	  	  });
    });	
	