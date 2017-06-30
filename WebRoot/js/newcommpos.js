	var areaid="",posid="",tempid="",comid="",perid="",tag="",status="",etype="";
    var areaurl="position_areaById?id=" ,posurl="position_posiById?id=",perurl="position_personByPosid?id=" ;    
    window.onload= function(){
    	commcom();     	
	};
    function commcom(){
    	var comurl="position_comaById";
    	$.ajax({
	    	type:"get",
	    	url:comurl,
	    	cache:true,
	        dataType:"json",   
	        success:function(data){
	        	var comdatas = eval(data);
	        	$("#coma").children().remove();	        	 
	        	$.each(comdatas,function(i,item){
	        		var comhtml="<div class='searchable-select-item "+(idc==comdatas[i].comid? 'selected':'')+"' data-value='"+comdatas[i].comid+"'>"+comdatas[i].comName+"</div>";
	        		 $("#coma").append(comhtml);	        		
	        	});	        	
	        	$("#coma1 .searchable-select-holder").text($("#coma .selected").text());
	        	comid=$("#coma .selected").attr("data-value");
	        	(comid=="" || comid==null) ? comid="" :comid=comid;
	    		commarea(areaurl,comid);
	        }
		});	
    }
    function commarea(areaurl,comid){
    	var tempurl=areaurl+comid;
		$.ajax({
	    	type:"get",
	    	url:tempurl,
	    	cache:true,
	        dataType:"json",   
	        success:function (data){
	        	var areadatas = eval(data);
	        	$("#addarea").children().remove(); 
	        	$.each(areadatas,function(i,item){	        			       			
	        		var  areahtml="<div class='searchable-select-item "+(ida==areadatas[i].areaid? 'selected':'')+"' data-value='"+areadatas[i].areaid+"' >"+areadatas[i].areaName+"</div>";
	        		  $("#addarea").append(areahtml);
	        	});
	        	$("#area1 .searchable-select-holder").text($("#addarea .selected").text());
	        	areaid=$("#addarea .selected").attr("data-value");
	        	(areaid=="" || areaid==null) ? areaid="" :areaid=areaid;
	    		commpos(posurl,areaid);   		    		
	        }
		});	
    }
	function commpos(posurl,areaid){
		var tempurl=posurl+areaid;
		$.ajax({
	    	type:"get",
	    	url:tempurl,
	    	cache:true,
	        dataType:"json",      
	        success:function (data){
	        	var dataspos = eval(data);
	        	$("#posName ").children().remove();       	
	        	$.each(dataspos,function(i,item){	        		
	        		var	poshtml="<div class='searchable-select-item  "+(idp==dataspos[i].posid? 'selected':'')+"' data-value='"+dataspos[i].posid+"'>"+dataspos[i].posName+"</div>";
        			$("#posName").append(poshtml);
	        	});
	        	$("#pos1 .searchable-select-holder").text($("#posName .selected").text());
	        	posid=$("#posName .selected").attr("data-value");
	        	(posid=="" || posid==null)? posid="":posid=posid;
	        	commpername(perurl,posid);
	        }
		});	
	}
	function commpername(perurl,posid){
		var tempurl=perurl+posid;
		$.ajax({
	    	type:"get",
	    	url:tempurl,
	    	cache:true,
	        dataType:"json",      
	        success:function (data){
	        	var datasper = eval(data);
	        	$("#pername ").children().remove();
	        	$.each(datasper,function(i,item){
	        		var	perhtml="<div class='searchable-select-item  "+(idper==datasper[i].perid? 'selected':'')+"' data-value='"+datasper[i].perid+"'>"+datasper[i].perName+"</div>";
	        		$("#pername").append(perhtml);
	        	});
	        	$("#per1 .searchable-select-holder").text($("#pername .selected").text());
	        	perid=$("#pername .searchable-select-item").attr("data-value");	        	
	        }		 
		});			
	}
    function getQueryString(id) { 
	   	 var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i");
	   	 var r = window.location.search.substr(1).match(reg); 
	   	 if (r != null) return decodeURIComponent(r[2]); return ""; 
   	 }
     var idc= getQueryString("condition.comId"); 
     var idp= getQueryString("condition.posId");
     var ida= getQueryString("condition.areaId");
     var idper=getQueryString("condition.perId"); 
     var idtag=getQueryString("condition.tag");
     var idstutas=getQueryString("condition.status");
     var idtype=getQueryString("condition.excType");
     //事件
   $("#coma1").click(function(){
  	   $(this).children().removeClass("searchable-select-hide");
  	   $(this).children().show();
  	   $("#coma1").searchData();
   });
     $182("#coma .searchable-select-item").live("click",function(){
       $("#area1 .searchable-select-holder").text(""); 
       $("#pos1 .searchable-select-holder").text("");
  	   $("#per1 .searchable-select-holder").text("");
  	   $(this).addClass("selected").siblings().removeClass("selected");
  	   comid=$("#coma .selected").attr("data-value");
  	   idc=comid;
	   commarea(areaurl,comid);
  	   var val=$("#coma div.selected").text();
  	   $("#coma1 .searchable-select-holder").text(val);
  	   $("#coma1 .searchable-select-dropdown").addClass("searchable-select-hide");
     });
     $182(".searchable-select-item").live("mouseover",function(){
    	   $(this).addClass("hover").siblings().removeClass("hover");
     });
     $("#area1").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();    	   
    	   $("#area1").searchData();
       });
       $182("#addarea .searchable-select-item").live("click",function(){
    	   $("#pos1 .searchable-select-holder").text("");
    	   $("#per1 .searchable-select-holder").text("");
    	   idper="";
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   areaid=$("#addarea .selected").attr("data-value");
    	   ida=areaid;
   		   commpos(posurl,areaid); 
    	   var val=$("#addarea div.selected").text();
    	   $("#area1 .searchable-select-holder").text(val);
    	   $("#area1 .searchable-select-dropdown").addClass("searchable-select-hide");
       });
       
       $("#pos1").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();    	   
    	   $("#pos1").searchData();
       });
       $182("#posName .searchable-select-item").live("click",function(){
    	   $("#per1 .searchable-select-holder").text("");
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   posid=$("#posName .selected").attr("data-value");
    	   idp=posid;
           commpername(perurl,posid);
    	   var val=$("#posName div.selected").text();
    	   $("#pos1 .searchable-select-holder").text(val);
    	   $("#pos1 .searchable-select-dropdown").addClass("searchable-select-hide");
       });
       $("#per1").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();
    	   $("#per1").searchData();
       });
       $182("#pername .searchable-select-item").live("click",function(){
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   perid=$("#pername .selected").attr("data-value");
    	   idper=perid;
    	   var val=$("#pername div.selected").text();
    	   $("#per1 .searchable-select-holder").text(val);
    	   $("#per1 .searchable-select-dropdown").addClass("searchable-select-hide");
       });
       $182(".searchable-select,.searchable-select-dropdown").live("mouseleave",function(){
    	   $(".searchable-select-dropdown").addClass("searchable-select-hide");
       });
      //类型
      $("#tag1").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();
    	   $("#tag1").searchData();
       });
       $182("#tag .searchable-select-item").live("click",function(){
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   tag=$("#tag .selected").attr("data-value");
    	   idtag=tag;
    	   var val=$("#tag div.selected").text();
    	   $("#tag1 .searchable-select-holder").text(val);
    	   $("#tag1 .searchable-select-dropdown").addClass("searchable-select-hide");
       });
       //状态
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
       
       $("#exc1").click(function(){
    	   $(this).children().removeClass("searchable-select-hide");
    	   $(this).children().show();
    	   $("#exc1").searchData();
       });
       $182("#exctype .searchable-select-item").live("click",function(){
    	   $(this).addClass("selected").siblings().removeClass("selected");
    	   etype=$("#exctype .selected").attr("data-value");
    	   idtype=etype;
    	   var val=$("#exctype div.selected").text();
    	   $("#exc1 .searchable-select-holder").text(val);
    	   $("#exc1 .searchable-select-dropdown").addClass("searchable-select-hide");
       });  
      
       
       
       