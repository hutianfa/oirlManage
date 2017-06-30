    /**
     * author：hutianfa
     * descibe：条件查询
     */
    //下一页
	$182("#next").live("click",function(){
		 curentPage = parseInt(curentPage)+1;
		 $("#curpage").text(curentPage); 
		 pageconment(curentPage,etotal);
		 jumpurl= jumpurl+curentPage;    			
		 jumpurl="empower_listPow?em.name="+name+"&currentPage="+curentPage+"";
		 empower(jumpurl);
	}); 
	//上一页
	$182("#pre").live("click",function(){
		curentPage = curentPage -1;  
		$("#curpage").text(curentPage);
		pageconment(curentPage,etotal);
		jumpurl= jumpurl+curentPage;
		jumpurl="empower_listPow?em.name="+name+"&currentPage="+curentPage+"";
		empower(jumpurl);
	});
	function pageconment(curentPage,totalPage){
		$(".table-bottom-page").empty();
		if(curentPage>1){
			 pagehtml="<a id='pre' >上一页 </a>&nbsp";
			 $(".table-bottom-page").append(pagehtml);
		}
		if(curentPage<totalPage){
			 pagehtml="<a id='next' >下一页 </a>&nbsp";
			 $(".table-bottom-page").append(pagehtml);
		 }
	} 
	//查询
	function searchempower(){
		name= $("#name").val();
		var url="empower_listPow?em.name="+name+"&currentPage=1";           			
		empower(url);
	}
	function empower(url){
    	$.ajax({
	    	type:"get",
	    	url:url,
	    	cache:true,
	        dataType:"json",
	        success:function (data){
	        	var datas = eval(data);
	        	$(".conment-code").empty();
	        	 $.each(datas,function(i,item){ 
	        	    var pichtml="<tr style='height:2.7em' class='info'>";
	        	        pichtml+= "<td >"+datas[i].name+"</td>";
	        	        pichtml+= "<td >********</td>" ; 
	        	        /*pichtml+= "<td class='xiugai'><input value='修改' class='emupdate' type='button' id='"+datas[i].id+"'/></td>";         	        	      
	        	        pichtml+= "<td ><input value='删除' class='emdel' type='button' id='"+datas[i].id+"'/></td>";*/  
	        	        pichtml+="<td class='xiugai'><button type='button' value='编 辑' id='"+datas[i].id+"' class='emupdate btn btn-xs btn-info' style='width:80px;height:28px;'>" +
	        	        				"<i class='fa fa-edit'> 编 辑</i></button></td>";
	        	        pichtml+="<td><button type='button' value='删 除' id='"+datas[i].id+"' class='emdel btn btn-xs btn-info' style='width:80px;height:28px;'>" +
	        	        		"<i class='fa fa-trash'> 删 除</i></button></td>";
	        	        pichtml+="</tr>";
	        	 $(".conment-code").append(pichtml);
	        	 etotal=datas[i].emTotal;
	        	});
	         pageconment(curentPage,etotal);	 
	       }
         });
	 }
	var name,curentPage=1,totalpage,jumpurl,etotal;
	name=$("#name").val();
	(name=="" || name==null) ? name="" :name=name;
	var tempurl="empower_listPow?em.name="+name+"&currentPage="+curentPage+"";
	window.onload=function(){empower(tempurl);}
	$(function(){            	
	function message(text,newdata) {
	   var newval= newdata;
       $("#spanmessage").text(text);
       $104("#message").dialog({
           title:"中石油油罐车运输云平台，提示您！",
           modal: true,
           buttons: {
               "确定": function(newdata) {
            	   if(newval ==1){
            		   $104(this).dialog("close");
            		   location.href="empower_Alllist";
            	   }else{
            		   $104(this).dialog("close");  
            	   }  
               }
           }
       });
   }            	
	/**
	 * author:hutianfa
	 * describe:修改数据操作
	 */
	var val1="",val2="",val3="", oldval2="",oldval3="", _this,isinput;
	$182("#updatab tbody tr td.xiugai .emupdate").live("click",function(){ 
		 $("#updatab tbody tr td").css("display","block");
		 $("#updatab tr th:eq(2)").show(); 
    	 oldval2=$(this).parents("td.xiugai").siblings("td:eq(0)").html();
    	 oldval3=$(this).parents("td.xiugai").siblings("td:eq(1)").html();
    	 $(this).parents("td.xiugai").prevAll().html(function(i,html){
                 return '<input type="text" value='+html+' />';
         });
    	 $(this).parents("td.xiugai").siblings("td:eq(1)").find("input").val("请输入新的授权码");
    	/* $(this).val("保存");
    	 $(this).attr("class","save");*/
    	 $(this).val("保存");$(this).attr("class","save btn btn-xs btn-info");
    	 $(this).children("i").attr("class","fa fa-save");
    	 $(this).children("i").text(" 保存");
    	 _this=$(this);	
    	 isinput=_this.parents("td.xiugai").children().get(0).tagName;
    	 if(isinput =="BUTTON"){
    		 $182("#updatab tbody tr td button.save").live("click",function(){
	        	 //val3=$(this).parents().find("td:eq(2)").find("input[type=text]").val();
	        	 val1=$(this).parents().find("td:eq(0)").find("input[type=text]").val();
	        	 val2=$(this).parents().find("td:eq(1)").find("input[type=text]").val();
	        	 var id=$(this).attr("id");
	        	 val2=="输入新的授权码" ? val2="" : val2;//"em.company":val2,
	        	 $.post("empower_UpdatePow",{"em.name":val1,"em.powerCode":val2,"em.Id":id},
		   			function(data){  
        	    	   if(data==1){
        	    		     $(this).parents().find("td:eq(0)").text(val1);
				        	 $(this).parents().find("td:eq(1)").text(val2);
			   				message("修改成功！",data);
			   			    $("#alltishi").html("");
			   			 }else{
			   				 $(this).parents().find("td:eq(0)").text(oldval2);
				        	 $(this).parents().find("td:eq(1)").text(oldval3);
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
     $(function () {
    	 $('[data-toggle="tooltip"]').tooltip()
     })
	  $("#addbtn,#close,#cancel").click(function(){	
		  $("#myModal").modal("show");
	  });
      $(function(){
    	 function message(text,newdata) {
    		   var newval= newdata;
    	       $("#spanmessage").text(text);
    	       $104("#message").dialog({
    	           title:"中石油油罐车运输云平台，提示您！",
    	           modal: true,
    	           buttons: {
		               "确定": function(newdata) {
		            	   if(newval ==1){
		            		   $104(this).dialog("close");
		            		   location.href="empower_Alllist";
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
     	      * describe:删除数据操作
     	      */
     	     $182(".conment-code tr td button.emdel").live("click",function(){
     	    	   var id=$(this).attr("id");	     	    	  
     	    	   $.fn.delid=function(id){
     	    		 var url="empower_delPow?poId="+id; 
	       		    $.get(url,function(data){
		       			  if(data==1){
		       				 message("删除成功！",data);
		       			  }else{
		       				 message("删除失败！",data);
		       			  }
            	     });};
     	    	 queren("确认删除吗？",function(){ 
     	    		 $182().delid(id);
     	    	 });
     	     });
       		 /**
       		  * describe：添加信息
       		  */
     	    $("#sureaddbtn").click(function(){  
     	  		  var uname = $("#uname").val();
     	  		  var emcode=$("#emcode").val();
     	  		  if( uname =="" || uname == null){
     	  			  $(".errortilp").html("用户名不能为空");
     	  		  }
     	  		  else if(emcode =="" ||emcode == null){
     	  			  $(".errortilp").html("授权码不能为空！");
     	  		  }
     	  		  else{
     	  			  $("#myModal").modal("hide");
     			      queren("确认要添加吗？", function(){ 
     			   		$.post("empower_addPow",$("#addformper").serialize(),
		   				 function(data){  
	        	    	   if(data==1){
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
	  