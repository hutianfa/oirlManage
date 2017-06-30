/**
 * author:hutianfa
 * describe:条件查询
 */
function nextJump(){
	var curentPage=document.getElementById("curentPage").value;
	curentPage=parseInt(curentPage)+1;
	commJump(curentPage);
}
function preJump(){
	var curentPage=document.getElementById("curentPage").value;
	curentPage=curentPage-1;
	commJump(curentPage);
}
function jumpPage(){
	var jumpPage=document.getElementById("jumpPage").value;
	var totalPage=document.getElementById("totalPage").value;
	if(parseInt(jumpPage)>parseInt(totalPage)){
		return;
	}
	if(parseInt(jumpPage)>0){
		commJump(jumpPage);
	}
}
function commJump(curentPage){
	var name=document.getElementById("name").value;
	var status=$("#stutas1 .selected").attr("data-value");
	var comname=$("#loc").val(); var locid=$("#locid").val();
	var loccc = txtid;
	if(loccc !="" && loccc != null){
		location.href="admin_listGeneralManager?condition.name="+name+"&condition.comName="+comname+"&pageBean.curentPage="+curentPage+"&condition.status="+status+"&condition.comId="+loccc+"";
	}else{
		location.href="admin_listGeneralManager?condition.name="+name+"&condition.comName="+comname+"&pageBean.curentPage="+curentPage+"&condition.comId="+locid+"&condition.status="+status+"";
	}
}
function searchPersons(){
	var name=document.getElementById("name").value;
	var status=$("#stutas1 .selected").attr("data-value");
	var comname=$("#loc").val(); var locid=$("#locid").val();
	var loccc = txtid;
	if(loccc !="" && loccc != null){
		location.href="admin_listGeneralManager?condition.name="+name+"&condition.comName="+comname+"&condition.status="+status+"&condition.comId="+loccc+"";
	} else{
		comname=="" ? locid="" : locid=locid;
		location.href="admin_listGeneralManager?condition.name="+name+"&condition.comName="+comname+"&condition.comId="+locid+"&condition.status="+status+"";
	}
}
/**
 * author:hutianfa
 * describe:删除操作
 */
$(function(){
	function message(text,newdata) {
	   var newval= newdata;
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
     $(".delbtnid").click(function(e) {
	    var id = $(e.target).attr('id');
	    queren("确认要删除吗？", function(){
   		   var url="del_GeneralManager?id="+id;
   		    $.get(url,function(data){
   			  if(data==0){
   				 message("删除成功！",data);
   			  }else{
   				 message("删除失败！",data);
   			  }
   		 });
   		return true;
      });   
    });
  });  
  $(function () {
	 $('[data-toggle="tooltip"]').tooltip()
  });
  $("#addbtn").click(function(){
	  $("#myModal").modal("show");
	  $(".errortilp").html("");
  });
  $("#close,#cancel").click(function(){
	  $("#myModal").modal("hide");
	  $(".errortilp").html("");
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
            	   if(newval ==0){
            		   $104(this).dialog("close");
            		   location.href="admin_listGeneralManager";
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
    	var name = $("#user").val();
    	var pwd = $("#pwd").val();
    	var phone1 = $("#phone").val();
    	var email1 = $("#email").val();
    	var lo = txtid;
    	var turename=$("#turename").val();
    	var sex=$("#selopt .selected").attr("data-value"); 
    	var re= /^1\d{10}$/;
    	var ree=/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;   
    	var loggex = /^[a-zA-Z]{1,}[0-9a-zA-Z_]{1,}$/;
		var rename=new RegExp(loggex);
     	if(name == "" || name == null || pwd == "" || pwd == null ||
     			!rename.test(name) || !rename.test(pwd)){
     	   $(".errortilp").html("(用户名和密码不能为空且以字母开头！)");
     	}else if(phone1 != "" && !re.test($("#phone").val())){
     		$(".errortilp").html("(手机号位数不正确！)"); 	     			
     	}else if(email1 !="" && !ree.test($("#email").val())){
     		$(".errortilp").html("(邮箱格式不正确！)");
     	}else if(lo == "" || lo == null){
     		$(".errortilp").html("(所属公司不能为空！)");
     	}else{
     	  $("#myModal").modal("hide");  
 		    //$("#add_GeneralManager").serialize()
 		  var datastr={"admin.admName":name,"admin.admPassword":pwd,"admin.admTrueName":turename,
 		    	         "admin.admPhone":phone1,"admin.admEmail":email1,"admin.admSex":sex,"condition.comId":lo}
 		  $.post("add_GeneralManager",datastr,function(data){  
	         if(data==0){
			   message("添加成功！",data);
			   $(".errortilp").html("");
   			 }else{
   				message("添加失败！",data);
   				$(".errortilp").html("");
   			 } 
 	      }); 
     	}
     });    	 
   });
   //下拉列表获取选中的值
   $("#selopt1").click(function(){
	   $(this).children().removeClass("searchable-select-hide");
	   $(this).children().show();
	   $("#selopt1").searchData();
   });
   $182("#selopt1 .searchable-select-item").live("click",function(){
	   $(this).addClass("selected").siblings().removeClass("selected");
	   etype=$("#selopt .selected").attr("data-value");
	   var val=$("#selopt div.selected").text();
	   $("#selopt1 .searchable-select-holder").text(val);
	   $("#selopt1 .searchable-select-dropdown").addClass("searchable-select-hide");
   });
   $("#stutas1").click(function(){
	   $(this).children().removeClass("searchable-select-hide");
	   $(this).children().show();
	  $("#stutas1").searchData();
   });
   $182("#status .searchable-select-item").live("click",function(){
	   $(this).addClass("selected").siblings().removeClass("selected");
	   status=$("#status .selected").attr("data-value");
	   idstutas=status;
	   var val=$("#status div.selected").text();
	   $("#stutas1 .searchable-select-holder").text(val);
	   $("#stutas1 .searchable-select-dropdown").addClass("searchable-select-hide");
   });
   $182(".searchable-select-item").live("mouseover",function(){
	   $(this).addClass("hover").siblings().removeClass("hover");
   });
   $182(".searchable-select,.searchable-select-dropdown").live("mouseleave",function(){
	   $(".searchable-select-dropdown").addClass("searchable-select-hide");
   });
   var idstutas=getQueryString("condition.status");
   function getQueryString(id) { 
   	 var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i");
   	 var r = window.location.search.substr(1).match(reg); 
   	 if (r != null) return decodeURIComponent(r[2]); return ""; 
   } 
  
  
  