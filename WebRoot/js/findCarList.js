	var carurl="car_getCarList";    
	commcar(carurl);
   var carid="";
   function commcar(carurl){
	   $.ajax({
	    	type:"get",
	    	url:carurl,
	    	cache:true,
	        dataType:"json",   
	        success:function(data){
	        	var cardatas = eval(data);
	        	$("#carid div").remove();
	        	var carhtml="<div class='searchable-select-item hover' data-value=''></div>";
   			    $("#carid").append(carhtml);
	        	$.each(cardatas,function(i,item){	        		  	
	        		carhtml="<div class='searchable-select-item "+(idpc==cardatas[i].carFlpper ? 'selected':'')+"' data-value='"+cardatas[i].carFlpper+"' >"+cardatas[i].carFlpper+"</div>"; 
	        		$("#carid").append(carhtml);	        		
	        	});	 
	        	$("#car1 .searchable-select-holder").text(idpc);	        	
	        	carid=$("#carid .selected").attr("data-value");
	        }
		});	
   }
  
   
   function getQueryString(id) { 
	   	 var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i"); 
	   	 var r = window.location.search.substr(1).match(reg); 
	   	 if (r != null) return decodeURIComponent(r[2]); return ""; 
 	 } 
   var idpc= getQueryString("condition.carFlapper");
  $182(".searchable-select,.searchable-select-dropdown").live("mouseleave",function(){
	   $(".searchable-select-dropdown").addClass("searchable-select-hide");
   });
   $182(".searchable-select-item").live("mouseover",function(){
	   $(this).addClass("hover").siblings().removeClass("hover");
  });
   $("#car1").click(function(){
  	   $(this).children().removeClass("searchable-select-hide");
  	   $(this).children().show();
  	   $("#car1").searchData();
   });
     $182("#carid .searchable-select-item").live("click",function(){
  	   $(this).addClass("selected").siblings().removeClass("selected");
  	   carid=$("#carid .selected").attr("data-value");
  	   idc=carid;
  	   var val=$("#carid div.selected").text();
  	   $("#car1 .searchable-select-holder").text(val);
  	   $("#car1 .searchable-select-dropdown").addClass("searchable-select-hide");
     });