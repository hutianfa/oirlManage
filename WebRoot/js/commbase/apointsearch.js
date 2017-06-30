/**
 * author：hutianfa
 * describe：条件查询
 */
	function nextJump(){commnextJump(jumpurl);}
	function preJump(){commpreJump(jumpurl);}
	function jumpPage(){commjumpPage(jumpurl);}  
	var url;
	function searchCars(){		
		commSearch(url);
	}
  $(function () {
    $('[data-toggle="tooltip"]').tooltip()
  });
  $("#edutbtn").click(function(){
    $("#myModal").modal("show");		 		  
  });
  $(".close,#cancel").click(function(){
    $("#myModal").modal("hide");
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
            		   location.href="";
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
 			   		$.post("car_add",
 			   			{"addCar.carFlapper":carnumtext,
 			   			"addCar.CarUnFixFlag":activnum,
 			   			"addCar.CarFixFlag":nochangenum},
		   				function(data){  
	        	    	   if(data==0){
				   				message("添加成功！",data);
				   			 }else{
				   				message("添加失败！",data);
				   			 } 
		        	  }); 
 			     	$(".errortilp").html("");
 	  		    }	     	  		
 	  	  });
    });	
	