 
   $(function () {
     $('[data-toggle="tooltip"]').tooltip()
   })
   $("#addbtn").click(function(){
       $("#myModal").modal("show");
	   $("#positiontype1").click(function(){
		   $(this).children().removeClass("searchable-select-hide");
		   $(this).children().show();
	   });
	   $182("#positiontype .searchable-select-item").live("click",function(){
		   $(this).addClass("selected").siblings().removeClass("selected");
		   etype=$("#positiontype .selected").attr("data-value");
		   var val=$("#positiontype div.selected").text();
		   $("#positiontype1 .searchable-select-holder").text(val);
		   $("#positiontype1 .searchable-select-dropdown").addClass("searchable-select-hide");
	   });
   });
   $("#close,#cancel").click(function(){
      $("#myModal").modal("hide");
   });
   /**
    * author：hutianfa
    * describe：条件查询
    * @type 
    */
   var jumpurl,posname="",areaname="",comname="";												
	function nextJump(){
		posname=$("#pos1 .selected").attr("data-value");
		areaname=$("#area1 .selected").attr("data-value");
		comname=$("#coma1 .selected").attr("data-value");
		(comname=="" || comname==null ) ? comname="" : comname=comname;
		(areaname=="" || areaname==null ) ? areaname="" : areaname=areaname;
		(posname=="" || posname==null ) ? posname="" : posname=posname;
		jumpurl="position_list?condition.posid="+posname+"&condition.areaid="+areaname+"&condition.comId="+ comname+"&pageBean.curentPage=";
		commnextJump(jumpurl);
	}
	function preJump(){
		posname=$("#pos1 .selected").attr("data-value");
		areaname=$("#area1 .selected").attr("data-value");
		comname=$("#coma1 .selected").attr("data-value"); 
		(posname=="" || posname==null ) ? posname="" : posname=posname;
		(comname=="" || comname==null ) ? comname="" : comname=comname;
		(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
		jumpurl="position_list?condition.posId="+posname+"&condition.areaId="+areaname+"&condition.comId="+ comname+"&pageBean.curentPage=";
		commpreJump(jumpurl);
	}
	function jumpPage(){
		posname=$("#pos1 .selected").attr("data-value");
		areaname=$("#area1 .selected").attr("data-value");
		comname=$("#coma1 .selected").attr("data-value"); 
		(posname=="" || posname==null ) ? posname="" : posname=posname;
		(comname=="" || comname==null ) ? comname="" : comname=comname;
		(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
		jumpurl="position_list?condition.posid="+posname+"&condition.areaid="+areaname+"&condition.comId="+ comname+"&pageBean.curentPage=";
		commjumpPage(jumpurl);
	}
	
	function searchPosition() {							
		var posname="",areaname="",comname="",url;
		posname=$("#pos1 .selected").attr("data-value");
		areaname=$("#area1 .selected").attr("data-value");
		comname=$("#coma1 .selected").attr("data-value");
		console.log(posname);
		(posname=="" || posname==null ) ? posname="" : posname=posname;
		(comname=="" || comname==null ) ? comname="" : comname=comname;
		(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
		url = "position_list?condition.posid="+posname+"&condition.areaid="+areaname+"&condition.comId="+comname+"";
		console.log(url);
		commSearch(url);					    
	}
   /**
    * author:hutianfa
    * describe：删除信息
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
	            		   location.href="position_list";
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
   function delpos(obj){			    	   
        queren("确认要删除吗？", function(){
        	var id=obj.id;
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
  }
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
     * author:hutianfa
     * describe:添加信息
     * */
     $("#sureaddbtn").click(function(){  
    	var posname=$("#newposname").val();
    	var cardNumber=$("#xpid").val();
    	var areaname=$("#newarea").val();
    	var mac=$("#phonemac").val();
    	//var dictId=$("#positiontype option:selected").val();
    	var dictId=$("#positiontype .selected").attr("data-value"); 
        if(cardNumber =="" || cardNumber ==null || dictId =="" || dictId == null){
        	$(".errortilp").html("芯片编号和站点类型不能为空！");
        }else{
           $("#myModal").modal("hide");
	       queren("确认要添加吗？", function(){	
	   		$.post("position_addNewPosition",{"posName":posname,"phoneMac":mac,"cardNumber":cardNumber,"dictId":dictId,"area.area_name":areaname},
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
  	  /**
  	   * author:hutianfa
  	   * describe:修改列表数据
  	   */
    var val1="",val2="",val3="",val4="",val5="";
    var oldval1="",oldval2="",oldval3="",oldval4="",oldval5="";
    var _this,isinput;
    $182("#updatatab tbody tr td.xiugai .upbtn").live("click",function(){ 
        	 oldval1=$(this).parents("td.xiugai").siblings("td:eq(0)").html();
        	 oldval2=$(this).parents("td.xiugai").siblings("td:eq(1)").html();
        	 oldval3=$(this).parents("td.xiugai").siblings("td:eq(2)").html();
        	 oldval4=$(this).parents("td.xiugai").siblings("td:eq(3)").html();
        	 oldval5=$(this).parents("td.xiugai").siblings("td:eq(4)").html();
        	 $(this).parents("td.xiugai").prevAll().html(function(i,html){
                     return '<input type="text" value='+html+' />';
             });
        	 $(this).val("保存");$(this).attr("class","save btn btn-xs btn-info");
    	 	 $(this).children("i").attr("class","fa fa-save");
    	 	 $(this).children("i").text(" 保存");
        	 $(this).parents("td.xiugai").siblings("td:eq(5)").find("input[type=text]").attr("disabled","disabled");
        	 _this=$(this);	
        	 isinput=_this.parents("td.xiugai").children().get(0).tagName;
        	 console.log(isinput);
        	 if(isinput =="BUTTON"){
        		 $182("#updatatab tbody tr td.xiugai button.save").live("click",function(){
		        	 val1=$(this).parents().siblings("td:eq(0)").find("input[type=text]").val();
		        	 val2=$(this).parents().siblings("td:eq(1)").find("input[type=text]").val();
		        	 val3=$(this).parents().siblings("td:eq(2)").find("input[type=text]").val();
		        	 val4=$(this).parents().siblings("td:eq(3)").find("input[type=text]").val();
		        	 val5=$(this).parents().siblings("td:eq(4)").find("input[type=text]").val();
		        	 var id=$(this).attr("id");
		        	 $.post("position_updateNewPos",{"posName":val3,"cardNumber":val5,"id":id,"area.area_name":val2,"phoneMac":val4},
   			   			function(data){  
	        	    	   if(data==0){
	        	    		     $(this).parents().find("td:eq(0)").text(val1);
					        	 $(this).parents().find("td:eq(1)").text(val2);
					        	 $(this).parents().find("td:eq(2)").text(val3);
					        	 $(this).parents().find("td:eq(3)").text(val4);
					        	 $(this).parents().find("td:eq(4)").text(val5);
				   				 message("修改成功！",data);
				   			    $("#alltishi").html("");
				   			 }else{
				   				 $(this).parents().find("td:eq(0)").text(oldval1);
					        	 $(this).parents().find("td:eq(1)").text(oldval2);
					        	 $(this).parents().find("td:eq(2)").text(oldval3);
					        	 $(this).parents().find("td:eq(3)").text(oldval4);
					        	 $(this).parents().find("td:eq(4)").text(oldval5);
				   				message("修改失败！",data);
				   			    $("#alltishi").html("");
				   			 } 
   			        	 });  
		        	 $(this).prevAll().find("input[type=text]").remove();
		        	 _this.removeAttr("disabled");
			        });
	        	 }
	    });	   
   });