	var areaid="",posid="",tempid="",comid="";
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
	        	$("#coma option").remove();	        	 
	        	$.each(comdatas,function(i,item){
	        		var comhtml="<option value="+comdatas[i].comid+">"+comdatas[i].comName+"</option>";	        		  	
	        		 $("#coma").append(comhtml);	        		
	        	});	        	
	        	comid=$("#coma option:selected").val();
	    		commarea(areaurl,comid);
	        }
		});	
    }
    $182("#coma").on("change",function(){
		comid=$("#coma option:selected").val();
		commarea(areaurl,comid);
	});
    function commarea(areaurl,comid){
    	var tempurl=areaurl+comid;
		$.ajax({
	    	type:"get",
	    	url:tempurl,
	    	cache:true,
	        dataType:"json",   
	        success:function (data){
	        	var areadatas = eval(data);
	        	$("#addarea option").remove();
	        	var areahtml="<option value=''></option>";
        		$("#addarea").append(areahtml); 
	        	$.each(areadatas,function(i,item){	        			       			
	        		  areahtml="<option value="+areadatas[i].areaid+" "+(ida==areadatas[i].areaid? 'selected':'')+">"+areadatas[i].areaName+"</option>";
	        		  $("#addarea").append(areahtml);
	        	});
	        	areaid=$("#addarea option:selected").val();
	    		commpos(posurl,areaid);   		    		
	        }
		});	
    }
	$182("#addarea").live("change",function(){
		areaid=$("#addarea option:selected").val();
		commpos(posurl,areaid);
	});
	function commpos(posurl,areaid){
		var tempurl=posurl+areaid;
		$.ajax({
	    	type:"get",
	    	url:tempurl,
	    	cache:true,
	        dataType:"json",      
	        success:function (data){
	        	var dataspos = eval(data);
	        	$("#posName option").remove();
	        	var poshtml="<option value=''></option>";
    			$("#posName").append(poshtml);
	        	$.each(dataspos,function(i,item){	        		
        			poshtml="<option value="+dataspos[i].posid+" "+(idp==dataspos[i].posid? 'selected':'')+" >"+dataspos[i].posName+"</option>";
        		    $("#posName").append(poshtml);
	        	});
	        	posid=$("#posName option:selected").val();
	        	commpername(perurl,posid);
	        }
		});	
	}
	$182("#posName").live("change",function(){
		posid=$("#posName option:selected").val();
		commpername(perurl,posid);
	});
	function commpername(perurl,posid){
		var tempurl=perurl+posid;
		$.ajax({
	    	type:"get",
	    	url:tempurl,
	    	cache:true,
	        dataType:"json",      
	        success:function (data){
	        	var datasper = eval(data);
	        	$("#pername option").remove();
	        	var perhtml="<option selected></option>";
	        	$("#pername").append(perhtml);
	        	$.each(datasper,function(i,item){
	        		perhtml="<option value="+datasper[i].perid+" "+(idper==datasper[i].perid? 'selected':'')+">"+datasper[i].perName+"</option>";
	        		$("#pername").append(perhtml);
	        	});
	        	perid=$("#pername option:selected").val();
	        	$('select.sect').searchableSelect();
	        }		 
		});			
	}	 
    function getQueryString(id) { 
	   	 var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i");
	   	 var r = window.location.search.substr(1).match(reg); 
	   	 if (r != null) return decodeURIComponent(r[2]); return ""; 
   	 }   
     var idp= getQueryString("condition.posId");
     var ida= getQueryString("condition.areaId");
     var idper=getQueryString("condition.perId");  