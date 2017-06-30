  /**
   * author:hutianfa
   * 库存数据统计
   * describe:ajax返回json数据拼接
  */
  var benefithtml,curentPage=1,pagetotal;
  var jumpurl="inventory_queryTheCompany";
  var stohtml="";
  $(function(){ inStolist(jumpurl);}); 
  function inStolist(url){	   
	   $.ajax({
		 type:"get",
		 url:url,
		 cache:true,
	     dataType:"json",   
	     success:function (data){   
	    	 var comdatas = eval(data);
	    	 $("#instocoment").children().remove();
	    	 $.each(comdatas,function(i,item){	  		   
	    		 stohtml="<tr data-toggle='' data-parent=''" +
	 		 		"href='.collapseOne' onclick='onarea(this)' class='success success' style='cursor:pointer'>" +
	 		 		"<td style='text-align:center;'><i class='fa fa-sort-down' style='color:blue;'></i></td>"; 	    	   
	    		 stohtml +="<td>"+comdatas[i].countsCompany+"</td>";
	    		 stohtml +="<td>"+comdatas[i].alreadyDistributed+"</td>";
				 stohtml +="<td>"+comdatas[i].companyInventory+"</td>";				
				 stohtml +="<td>"+comdatas[i].companyAddress+"</td>";
				 stohtml +="<td>"+comdatas[i].time+"</td>";
				$("#instocoment").append(stohtml);	
			}); 
	     }
	   });   
   }  
   function onarea(obj){   	 
    	var areatype=obj.getAttribute('href');
    	var newarea =areatype.split('.')[1];
       $.ajax({
   		 type:"get",
   		 url:"inventory_queryTheInventory",
   		 cache:true,
   	     dataType:"json", 
   	     beforeSend:function(){
   	    	comloadlay();
		 },
   	     success:function (data){
   	    	 var areadatas = eval(data);
   	    	 $("#instocoment tr:not(:first)").remove();
   	    	 $.each(areadatas,function(i,item){	  		   
   	    		 stohtml="<tr data-toggle='' data-parent=''" +
   	 		 		"href='.areacoll"+areadatas[i].areaId+"' onclick='onpos(this)' id='"+areadatas[i].areaId+"' " +
   	 		 				"class='panel-collapse   warning "+newarea+" areacoll"+areadatas[i].areaId+"' style='cursor:pointer'>" +
   	 		 		"<td style='text-align:center;'><i class='fa fa-sort-down' style='color:blue;'></i></td>"; 	    	   
   	    		 stohtml +="<td>"+areadatas[i].totalAreaGainByCompany+"</td>";
   	    		 stohtml +="<td>"+areadatas[i].totalSiteGainByArea+"</td>";
   				 stohtml +="<td>"+areadatas[i].areaInventory+"</td>";				
   				 stohtml +="<td>"+areadatas[i].name+"</td>";
   				 stohtml +="<td>"+areadatas[i].time+"</td>";
   				$("#instocoment").append(stohtml);	
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
    function onpos(obj){
    	var id=obj.id;
    	var areapos="areacoll"+id;
    	 $.ajax({
       		 type:"get",
       		 url:"inventory_queryThePositionInventory?areaid="+id,
       		 cache:true,
       	     dataType:"json", 
       	     beforeSend:function(){
       	    	comloadlay();
  		     },
       	     success:function (data){
       	    	var posdatas = eval(data);
       	    	$("."+areapos).siblings(".posarr").remove();
      	        $.each(posdatas,function(i,item){	
      	        	stohtml="<tr class='panel-collapse posarr'><td ></td>";   
	   	   			 stohtml +="<td>"+posdatas[i].positionCount+"</td>";
	   				 stohtml +="<td>"+posdatas[i].alreadyInUsedPositionCount+"</td>";
	   				 stohtml +="<td>"+posdatas[i].positionInventory+"</td>";
	   				 stohtml +="<td>"+posdatas[i].positionName+"</td>";
	   				 stohtml +="<td>"+posdatas[i].time+"</td>";
	   				$("."+areapos).after(stohtml);
      	        })
       	     },
	   	   	  complete:function(XMLHttpRequest,textStatus){ 
	 	         clearloadlay();
	 	      }, 
	 	      error:function(XMLHttpRequest,textStatus,errorThrown){ 
	 	    	 clearloadlay(); 
	 	      } 
    	 })
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
   /**  
    * author：hutianfa
    * describe：条件查询
   */
	 
  $(function () {
    $('[data-toggle="tooltip"]').tooltip()
  });