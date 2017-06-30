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
	var name=document.getElementById("username").value;
	var posname=$("#pos1 .selected").attr("data-value");
	var areaname=$("#area1 .selected").attr("data-value");
	var comname=$("#coma1 .selected").attr("data-value");
	var tureid=$("#per1 .selected").attr("data-value");
	var turename=$("#per1 .selected").text();
	(posname=="" || posname==null) ? posname="" :posname=posname;
	(comname=="" || comname==null) ? comname="" : comname=comname;
	(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	(tureid=="" || tureid==null) ? tureid="" : tureid=tureid;
	(turename=="" || turename==null) ? turename="" : turename=turename;
	location.href="person_list?condition.perUserName="+name+"&condition.perId="+tureid+"&condition.perTrueName="+turename+"&condition.areaId="+areaname+"&condition.posId="+posname+"&condition.comId="+comname+"&pageBean.curentPage="+curentPage+"";
}
function searchPersons(){
	var name=document.getElementById("username").value;
	var posname=$("#pos1 .selected").attr("data-value");
	var areaname=$("#area1 .selected").attr("data-value");
	var comname=$("#coma1 .selected").attr("data-value");
	var tureid=$("#per1 .selected").attr("data-value");
	var turename=$("#per1 .selected").text();
	(posname=="" || posname==null) ? posname="" :posname=posname;
	(comname=="" || comname==null) ? comname="" : comname=comname;
	(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	(tureid=="" || tureid==null) ? tureid="" : tureid=tureid;
	(turename=="" || turename==null) ? turename="" : turename=turename;
	location.href="person_list?condition.perUserName="+name+"&condition.perId="+tureid+"&condition.perTrueName="+turename+"&condition.areaId="+areaname+"&condition.posId="+posname+"&condition.comId="+comname+"";           			
}
$(function () {
  $('[data-toggle="tooltip"]').tooltip()
});
  /**
   * author:hutianfa
   * describe:弹出层
   * @type 
   */
  var pid;
  $("#addbtn").click(function(){ 		 
	  $("#myModal").modal("show");
	  $.ajax({    
	        type:'get',        
	        url:"position_getListByName",    
	        cache:false,    
	        dataType:'json',    
	        success:function(data){ 
	        	Actioncomplete(data);
	        }    
	    });   
       function  Actioncomplete(datas){
		   $104('#loccc').autocomplete(datas,{
		        max: 12,    //列表里的条目数
		        minChars:1,    //自动完成激活之前填入的最小字符
		        width: 120,     //提示的宽度，溢出隐藏
		        scrollHeight: 300,   //提示的高度，溢出显示滚动条
		        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
		        autoFill: false,    //自动填充
		        formatItem: function(row, i, max) {
		            return  "\ ["+row.posName+"]";
		        },
		        formatMatch: function(row, i, max) {
		            return row.posName + row.posId;
		        },
		        formatResult: function(row) {
		            return row.posName;
		        }
		    }).result(function(event, row, formatted){
		          pid = row.posId;
		    }); 
      }
       $("#worktype1").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();
       });
       $182("#worktype .searchable-select-item").live("click",function(){
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   etype=$("#worktype .selected").attr("data-value");
    	   var val=$("#worktype div.selected").text();
    	   $("#worktype1 .searchable-select-holder").text(val);
    	   $("#worktype1 .searchable-select-dropdown").addClass("searchable-select-hide");
       });
       $("#sex11").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();
       });
       $182("#sex1 .searchable-select-item").live("click",function(){
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   etype=$("#sex1 .selected").attr("data-value");
    	   var val=$("#sex1 div.selected").text();
    	   $("#sex11 .searchable-select-holder").text(val);
    	   $("#sex11 .searchable-select-dropdown").addClass("searchable-select-hide");
       });
       $("#qtype1").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();
       });
       $182("#qtype .searchable-select-item").live("click",function(){
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   etype=$("#qtype .selected").attr("data-value");
    	   var val=$("#qtype div.selected").text();
    	   $("#qtype1 .searchable-select-holder").text(val);
    	   $("#qtype1 .searchable-select-dropdown").addClass("searchable-select-hide");
       }); 
	});
    $("#close").click(function(){
	   $("#myModal").modal("hide");
	}); 
   /**
    * author:hutianfa
    * describe:删除信息
    * @param {} obj
    */
   function delper(obj){
       queren("确认要删除吗？", function(){
       		 var id=obj.id;
      		 var url="person_del?id="+id;
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
            		   location.href="person_list"; 
            	   }else{
            		   $104(this).dialog("close");  
            		   $("#myModal").modal("show");
            	   }  
               }
           }
       });
    }
    /**
     * author：hutianfa
     * describe：修改操作员信息
     * @type String
     */
    var val1="",val2="",val3="",val4="",val5="",val6="";
    var oldval1="", oldval2="",oldval3="",oldval4="",oldval5="",oldval6="";
    var _this,isinput;
    $182("#updatab tbody tr td.xiugai .upbtn").live("click",function(){ 
    	 oldval5=$(this).parents("td.xiugai").siblings("td:eq(4)").html();
    	 oldval6=$(this).parents("td.xiugai").siblings("td:eq(5)").html();
    	 $(this).parents("td.xiugai").prevAll(".iptd").html(function(i,html){
                 return '<input type="text" value='+html+' />';   
         }); 
    	 $(this).val("保存");$(this).attr("class","save btn btn-xs btn-info");
    	 $(this).children("i").attr("class","fa fa-save");
    	 $(this).children("i").text(" 保存");
    	 _this=$(this);	
    	 isinput=_this.parents("td.xiugai").children().get(0).tagName;
    	 if(isinput =="BUTTON"){
    		 $182("#updatab tbody tr td button.save").live("click",function(){
	        	 val5=$(this).parents().find("td:eq(4)").find("input[type=text]").val();
	        	 val6=$(this).parents().find("td:eq(5)").find("input[type=text]").val();
	        	 var id=$(this).attr("id");
	        	 console.log(val5);
	        	 $.post("person_update",{"id":id,"phoneNum":val5,"pwd":val6},
		   			function(data){  
        	    	   if(data==0){
				        	 $(this).parents().find("td:eq(4)").text(val5);
				        	 $(this).parents().find("td:eq(5)").text(val6);
			   				message1("修改成功！",data);
			   			    $("#alltishi").html("");
			   			 }else{
				        	 $(this).parents().find("td:eq(4)").text(oldval5);
				        	 $(this).parents().find("td:eq(5)").text(oldval6);
			   				message1("修改失败！",data);
			   			    $("#alltishi").html("");
			   			 } 
		        	 });  
	        	 $(this).prevAll().find("input[type=text]").remove();
	        	 _this.removeAttr("disabled");
	        });
    	 }
  });	
    function message1(text,newdata) {
	   var newval= newdata;
       $("#spanmessage").text(text);
       $104("#message").dialog({
           title:"中石油油罐车运输云平台，提示您！",
           modal: true,
           buttons: {
               "确定": function(newdata) {
            	   if(newval ==0){
            		   $104(this).dialog("close");
            		   location.href="person_list"; 
            	   }else{
            		   $104(this).dialog("close");  
            	   }  
               }
           }
       });
    }
  $(function(){
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
	  * describe:添加操作员信息
	  */
    $("#sureaddbtn").click(function(){
    	var name = $("#user").val(),pwd = $("#pwd").val(),
    	idbh =$("#userid").val(),phone1 = $("#phone").val(),
    	truename = $("#turename").val(),
    	locname = pid, 	  relox = /^1\d{10}$/;
    	email1=$("#qtype .selected").attr("data-value");
    	type=$("#worktype .selected").attr("data-value");
    	sex=$("#sex1 .selected").attr("data-value"); 
    	var loggex = /^[a-zA-Z]{1,}[0-9a-zA-Z_]{1,}$/;
		var re=new RegExp(loggex);
     	if(name == "" || name == null || pwd == "" || pwd == null ){
     		$(".errortilp").html("(用户名，密码都不能为空！)");
     	}
     	else if(!re.test(name) || !re.test(pwd)){
     		$(".errortilp").html("(用户名，密码必须以字母开头！)");
     	}
     	else if(truename =="" || truename == null){
     		$(".errortilp").html("姓名不能为空！");
     	}
     	else if(phone1 != "" && !relox.test(phone1)){
     		$(".errortilp").html("(手机号位数不正确！)"); 	
     	}
     	else if(locname =="" && locname == null){
     		$(".errortilp").html("位置不能为空！");
     	}	     	 
     	else{
     		$("#myModal").modal("hide");
 		     queren("确认要添加吗？", function(){ 
 		      var datastr={"addPerson.perName":name,"addPerson.perPassword":pwd,
                    "addPerson.perTrueName":truename,"addPerson.perCode":idbh,
                    "addPerson.perPhone":phone1,"addPerson.perEmail":email1,
                    "addPerson.basDict.dictId":type,"addPerson.perSex":sex,
	 		        "addPerson.position.posId":locname
	 		   };	 		      
	 		      $.post("person_add",datastr ,function(data){  
	 	    	       if(data==0){
			   				message("添加成功！",data);
			   			 }else if(data==-1){
			   				message("该用户已存在！",data);
			   			 }else{
			   				message("添加失败！",data);
			   			 } 
	 	    	       $(".errortilp").html("");
                 });	 		    
 		    }); 
     	  }
      }); 
   });	     
   $("#cancel").click(function(){
	   $("#myModal").modal("hide");  
   }); 