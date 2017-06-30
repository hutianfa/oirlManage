  /**
   * describe:ajax返回json数据拼接
  */
 /* var benefithtml,curentPage=1,pagetotal;
  var jumpurl="inSto_list";
  $(function(){ inStolist(jumpurl,curentPage);}); 
  function inStolist(url,curentPage){  
   $.ajax({
	 type:"get",
	 url:url,
	 cache:true,
     dataType:"json",   
     success:function (data){
    	 var datas = eval(data);
    	 $("#instocoment").children().remove();
    	 $.each(datas,function(i,item){*/
		 $("#instocoment").children().remove();
		 var arr = {"分公司":[{"name":"王一","box":"5","ga":"100","num":"5000","com":"销售分公司","date":"2016-11-12 09:22:45","state":"已入库"}],
				    "片区":[{"name":"江南经理","box":"1","ga":"20","num":"1000","com":"江南片区","date":"2016-11-12 09:22:45","state":"待入库"},{"name":"仁和经理","box":"2","ga":"40","num":"2000","com":"仁和片区","date":"2016-11-14 09:22:45","state":"已入库"}],
		            "油站":[{"name":"江南-刘站长","box":"/","ga":"2","num":"100","com":"炳草岗加油站","date":"2016-11-13 12:22:45","state":"已入库"},{"name":"仁和-王站长","box":"/","ga":"2","num":"100","com":"仁和老街加油站","date":"2016-11-13 12:22:45","state":"已入库"}],
		            "油库":[{"name":"伍华","box":"1","ga":"20","num":"1000","com":"金江油库","date":"2016-11-13 12:22:45","state":"已入库"}]
		           };
		 /*var arr = {"main1":[{"分公司":[{"name":"王一","box":"5","ga":"100","num":"5000","com":"销售分公司","date":"2016-11-12 09:22:45","state":"已入库"}],
				    "片区":[{"name":"江南经理","box":"1","ga":"20","num":"1000","com":"江南片区","date":"2016-11-12 09:22:45","state":"待入库"},{"name":"仁和经理","box":"2","ga":"40","num":"2000","com":"仁和片区","date":"2016-11-14 09:22:45","state":"已入库"}],
		            "油站":[{"name":"江南-刘站长","box":"/","ga":"2","num":"100","com":"炳草岗加油站","date":"2016-11-13 12:22:45","state":"已入库"},{"name":"王站长","box":"/","ga":"2","num":"100","com":"仁和老街加油站","date":"2016-11-13 12:22:45","state":"已入库"}],
		            "油库":[{"name":"伍华","box":"1","ga":"20","num":"1000","com":"金江油库","date":"2016-11-13 12:22:45","state":"已入库"}]
		 			}],
		 			"main2":[{"分公司":[{"name":"王一","box":"5","ga":"100","num":"5000","com":"销售分公司","date":"2016-11-12 09:22:45","state":"已入库"}],
					    "片区":[{"name":"江南经理","box":"1","ga":"20","num":"1000","com":"江南片区","date":"2016-11-12 09:22:45","state":"待入库"},{"name":"仁和经理","box":"2","ga":"40","num":"2000","com":"仁和片区","date":"2016-11-14 09:22:45","state":"已入库"}],
			            "油站":[{"name":"刘站长","box":"/","ga":"2","num":"100","com":"炳草岗加油站","date":"2016-11-13 12:22:45","state":"已入库"},{"name":"王站长","box":"/","ga":"2","num":"100","com":"仁和老街加油站","date":"2016-11-13 12:22:45","state":"已入库"}],
			            "油库":[{"name":"伍华","box":"1","ga":"20","num":"1000","com":"金江油库","date":"2016-11-13 12:22:45","state":"已入库"}]
			 		}]
		 };	*/	 
		 var stohtml="";
		 $.each(arr["分公司"],function(i,item){		  		   
    		 stohtml="<tr data-toggle='collapse' data-parent='#accordion'" +
 		 		"href='.collapseOne' class='success success' style='cursor:pointer'>" +
 		 		"<td style='text-align:center;'><i class='fa fa-plus' style='color:blue;'></i></td>"; 	    	   
    		 stohtml +="<td>"+arr["分公司"][i].name+"</td>";
			 stohtml +="<td>"+arr["分公司"][i].box+"</td>";
			 stohtml +="<td>"+arr["分公司"][i].ga+"</td>";
			 stohtml +="<td>"+arr["分公司"][i].num+"</td>";
			 stohtml +="<td>"+arr["分公司"][i].com+"</td>";
			 stohtml +="<td>"+arr["分公司"][i].date+"</td>";
			 stohtml +="<td style='color:red;'>"+arr["分公司"][i].state+"</td></tr>";	
			$("#instocoment").append(stohtml);	
		});
		$.each(arr["片区"],function(j,item){
    		 if(arr["片区"][j].name=="江南经理"){
    		 stohtml="<tr data-toggle='collapse' data-parent='#accordion'" +
		 		"href='.jstacoll' class='panel-collapse collapse warning collapseOne' style='cursor:pointer'>" +
		 		"<td style='text-align:right;'><i class='fa fa-plus' style='color:blue;'></i></td>";
    		 }
    		 if(arr["片区"][j].name=="仁和经理"){
    			 stohtml="<tr data-toggle='collapse' data-parent='#accordion'" +
			 		"href='.rstacoll' class='panel-collapse collapse danger collapseOne' style='cursor:pointer'>" +
			 		"<td style='text-align:right;'><i class='fa fa-plus' style='color:blue;'></i></td>";  
    		 }	
    		 stohtml +="<td>"+arr["片区"][j].name+"</td>";
			 stohtml +="<td>"+arr["片区"][j].box+"</td>";
			 stohtml +="<td>"+arr["片区"][j].ga+"</td>";
			 stohtml +="<td>"+arr["片区"][j].num+"</td>";
			 stohtml +="<td>"+arr["片区"][j].com+"</td>";
			 stohtml +="<td>"+arr["片区"][j].date+"</td>";
			 stohtml +="<td style='color:red;'>"+arr["片区"][j].state+"</td></tr>";	
			$("#instocoment").append(stohtml);	
		});
		$.each(arr["油站"],function(q,item){
	   		 if((arr["油站"][q].name).split("-")[0]=="江南"){
	   			 stohtml="<tr class='panel-collapse collapse jstacoll'><td ></td>";   
	   			 stohtml +="<td>"+arr["油站"][q].name+"</td>";
				 stohtml +="<td>"+arr["油站"][q].box+"</td>";
				 stohtml +="<td>"+arr["油站"][q].ga+"</td>";
				 stohtml +="<td>"+arr["油站"][q].num+"</td>";
				 stohtml +="<td>"+arr["油站"][q].com+"</td>";
				 stohtml +="<td>"+arr["油站"][q].date+"</td>";
				 stohtml +="<td style='color:red;'>"+arr["油站"][q].state+"</td></tr>";	
				$(".warning").after(stohtml);
	   		 }
	   		 if((arr["油站"][q].name).split("-")[0]=="仁和"){
	       		 stohtml="<tr class='panel-collapse collapse rstacoll'><td ></td>";   
	       		 stohtml +="<td>"+arr["油站"][q].name+"</td>";
				 stohtml +="<td>"+arr["油站"][q].box+"</td>";
				 stohtml +="<td>"+arr["油站"][q].ga+"</td>";
				 stohtml +="<td>"+arr["油站"][q].num+"</td>";
				 stohtml +="<td>"+arr["油站"][q].com+"</td>";
				 stohtml +="<td>"+arr["油站"][q].date+"</td>";
				 stohtml +="<td style='color:red;'>"+arr["油站"][q].state+"</td></tr>";	
				$(".danger").after(stohtml);
	         } 
				
		});
		$.each(arr["油库"],function(k,item){
		    stohtml="<tr class='panel-collapse collapse collapseOne info'><td ></td>"; 
		    stohtml +="<td>"+arr["油库"][k].name+"</td>";
			 stohtml +="<td>"+arr["油库"][k].box+"</td>";
			 stohtml +="<td>"+arr["油库"][k].ga+"</td>";
			 stohtml +="<td>"+arr["油库"][k].num+"</td>";
			 stohtml +="<td>"+arr["油库"][k].com+"</td>";
			 stohtml +="<td>"+arr["油库"][k].date+"</td>";
			 stohtml +="<td style='color:red;'>"+arr["油库"][k].state+"</td></tr>";	
			$("#instocoment").append(stohtml);	
		});
   	   	
		  /**
		   * authr:hutianfa
		   * describe:一级显示下拉
		   * @type String
		   */
		   var istrue="false";
		   $("table tbody tr.success").click(function(){
		 	  istrue=$("table tbody tr.collapseOne").attr("aria-expanded");
		 	  if(istrue == "true"){
		 	      $("table tbody tr.success td:eq(0)").children("i").attr("class","fa fa-plus");
		 	      $("table tbody tr.jstacoll,tr.rstacoll").attr("aria-expanded","false");
		 	      $("table tbody tr.jstacoll,tr.rstacoll").removeClass("in");
		 	      $("table tbody tr.warning td:eq(0),tr.danger td:eq(0)").children("i").attr("class","fa fa-plus");
		 	  }else{
		 	 	 $("table tbody tr.success td:eq(0)").children("i").attr("class","fa fa-minus");
		 	  }
		   });
		   /**
		   * authr:hutianfa
		   * describe:片区显示下拉
		   * @type String
		   */
		   var twoistrue="false";
		   $("table tbody tr.warning").click(function(){
		 	  twoistrue=$("table tbody tr.jstacoll").attr("aria-expanded");
		 	  if(twoistrue == "true"){
		 	      $("table tbody tr.warning td:eq(0)").children("i").attr("class","fa fa-plus");
		 	  }else{
		 	 	 $("table tbody tr.warning td:eq(0)").children("i").attr("class","fa fa-minus");
		 	  }
		   });
		   var thristrue="false";
		   $("table tbody tr.danger").click(function(){
		 	  thristrue=$("table tbody tr.rstacoll").attr("aria-expanded");
		 	  if(thristrue == "true"){
		 	      $("table tbody tr.danger td:eq(0)").children("i").attr("class","fa fa-plus");
		 	  }else{
		 	 	 $("table tbody tr.danger td:eq(0)").children("i").attr("class","fa fa-minus");
		 	  }
		   });
    	/*}); 
       }
     });		   
   }   */
   /**  
    * author：hutianfa
    * describe：条件查询
   */
	function nextJump(){commnextJump(jumpurl);}
	function preJump(){commpreJump(jumpurl);}
	function jumpPage(){commjumpPage(jumpurl);}  
	var url;
	function searchinSto(){		
		commSearch(url);
	}
  $(function () {
    $('[data-toggle="tooltip"]').tooltip()
  });