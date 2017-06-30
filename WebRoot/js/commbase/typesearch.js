/**
 * author:hutianfa
 * describe:统计数据加载
 */
  var url="inventory_statisticalUnlockNotSealedRow";
  var stationhtml="";
  $(function(){loadStation(url);});
  function loadStation(url){
	  $.ajax({    
		 type:"get",
		 url:url,
		 cache:true,
	     dataType:"json", 
	     beforeSend:function(){
	    	 setTimeout("comloadlay()","3100");
		 },
	     success:function (data){   
	    	 var datas = eval(data);
	    	 $("#poscont").children().remove();
	    	 $.each(datas,function(i,item){	  		   
	    		 stationhtml="<tr><td>"+datas[i].positionName+"</td>";
	    		 stationhtml+= "<td>"+datas[i].counts+"</td>";
	    		 stationhtml +="<td>"+datas[i].time+"</td></tr>";
   				 $("#poscont").append(stationhtml);	
			}); 
	     },
	   	  complete:function(XMLHttpRequest,textStatus){ 
	   		   clearloadlay(); 
	       }, 
	       error:function(XMLHttpRequest,textStatus,errorThrown){ 
	    	   clearloadlay();
	      } 
	 });   
  }
  /**
   * 点击加载中效果
   */
  function comloadlay(){
  	$("#loadlay").html("<img src='common/loadlaynew.jpg' style='margin-top:5%;'/>");
	   	$("#loadlay").css({'background-color':'rgba(255, 255, 255, 0.9)','height':'100%'});
  }
  /**
   * 清除加载效果
   */
  function clearloadlay(){
  	$("#loadlay").empty();
  	$("#loadlay").css({'background-color':'none','height':'auto'});
  }
 /*$("#aptype").click(function(){
     $(this).children().removeClass("searchable-select-hide");
	 $(this).children().show();
 });
 $182("#apointtype .searchable-select-item").live("click",function(){
    $(this).addClass("selected").siblings().removeClass("selected");
    //aptype=$("#apointtype .selected").attr("data-value");
    var val=$("#apointtype div.selected").text();
    $("#aptype .searchable-select-holder").text(val);
    $("#aptype .searchable-select-dropdown").addClass("searchable-select-hide");
    console.log($("#areatab").css("display","none"));
    $(this).attr("data-value") == 2 ? $("#cartab").show() : $(this).attr("data-value") == 0 ? 
    $("#areatab").css("display","block") && $("#cartab").css("display","none") : 
    $("#areatab").css("display","block") && $("#cartab").css("display","none");
 }); */
 
	