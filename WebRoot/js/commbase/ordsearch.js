  /**
   * describe:ajax解析列表数据
   **/
  var benefithtml,curentPage=1,pagetotal;
  var jumpurl="order_list";
  $(function(){ orderlist(jumpurl,curentPage);});
  function orderlist(url,curentPage){  
   $.ajax({
	 type:"get",
	 url:url,
	 cache:true,
     dataType:"json",   
     success:function (data){
    	 var datas = eval(data);
    	 $("#ordercoment").children().remove();
    	 $.each(datas,function(i,item){
    		if(datas[i].order_number == null){
    			datas[i].order_number="";
    		}
    		benefithtml="<tr><td>"+datas[i].id+"</td>";
        	benefithtml+="<td>"+datas[i].shouhuo_person+"</td>";			   	        	
        	benefithtml+="<td class='tdnum'>"+datas[i].fahuo_number+"</td>";
        	benefithtml+="<td>"+datas[i].shouhuo_address+"</td>";
        	benefithtml+="<td>"+datas[i].shouhuo_phone+"</td>";
        	benefithtml+="<td>"+datas[i].xiadan_time+"</td>";
    		if(datas[i].status == 0){
				datas[i].status="待发货";
				benefithtml+="<td style='color:red;'>"+datas[i].status+"</td>";
				/*benefithtml+="<td style='color:blue;'></td>";
	        	benefithtml+="<td></td>";*/
				benefithtml+="<td class='revise'><button type='button' value='编 辑' id='"+datas[i].id+"' class='btn btn-xs btn-info edute' style='width:80px;height:28px;'>" +
				 	"<i class='fa fa-edit'> 编 辑</i></button></td>"; 	   	        	
	        	$("#ordercoment").append(benefithtml);  
    		}else if(datas[i].status ==1){
    			datas[i].status="待收货";
    			benefithtml+="<td style='color:red;'>"+datas[i].status+"</td>";
    			benefithtml+="<td class='revise'><button type='button' value='编 辑' id='"+datas[i].id+"' disabled='disabled' class='btn btn-xs btn-info edute' style='width:80px;height:28px;'>" +
			 	"<i class='fa fa-edit'> 编 辑</i></button></td>";
    			/*benefithtml+="<td style='color:blue;'>待核实</td>";
   	        	benefithtml+="<td></td>";
                benefithtml+="<td class='revise'><button type='button' value='查 看' id='"+datas[i].id+"' class='btn btn-xs btn-info' onclick='javascript:lookSearch(this.id);' style='width:80px;height:28px;'>" +
                 	"<i class='fa fa-search-plus'> 查 看</i></button> | " +
                 	"<button type='button' value='审 核' id='checkord'  class='btn btn-xs btn-info' style='width:80px;height:28px;'>" +
                 	"<i class='fa fa-check'> 审 核</i></button></td>";*/	   	        	
   	        	$("#ordercoment").append(benefithtml);  
    		 }else{
    			datas[i].status="已完成";
    			benefithtml+="<td style='color:red;'>"+datas[i].status+"</td>";
    			benefithtml+="<td class='revise'><button type='button' value='编 辑' id='"+datas[i].id+"' disabled='disabled' class='btn btn-xs btn-info edute' style='width:80px;height:28px;'>" +
			 	"<i class='fa fa-edit'> 编 辑</i></button></td>";
    			/*benefithtml+="<td style='color:blue;'>已核实</td>";
   	        	benefithtml+="<td>王一</td>";
   	            benefithtml+="<td class='revise'><button type='button' value='查 看' id='"+datas[i].id+"' class='btn btn-xs btn-info' onclick='javascript:lookSearch(this.id);' style='width:80px;height:28px;'>" +
                 	"<i class='fa fa-search-plus'> 查 看</i></button> | " +
                 	"<button type='button' value='审 核' id='checkord'  class='btn btn-xs btn-default btn-lg' disabled='disabled' style='width:80px;height:28px;'>" +
                 	"<i class='fa fa-check'> 审 核</i></button></td>"; */  	        	
        	    $("#ordercoment").append(benefithtml);  
    		 }        		      		
    		/*pagetotal=datas[i].pageNum;
    	 	pageconment(curentPage,pagetotal);*/
    	}); 
       }
     });		   
   }
    function commcondition(){
    	var ordid=$("#ordid").val();
    }
   //下一页
	$182("#next").live("click",function(){
		 commcondition();
		 curentPage = parseInt(curentPage)+1;
		 $("#curpage").text(curentPage); 
		 pageconment(curentPage,pagetotal);
		 jumpurl="reportForm_list?currentPage="+curentPage+"";
		 signstar(jumpurl,curentPage);
	}); 
	//上一页
	$182("#pre").live("click",function(){
		commcondition();
		curentPage = curentPage -1;  
		$("#curpage").text(curentPage);
		pageconment(curentPage,pagetotal); 
		jumpurl="reportForm_list?currentPage="+curentPage+"";
		signstar(jumpurl,curentPage);
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
	/**
	 * author:hutianfa
	 * describe:条件查询
	 */
	function searchOrder(){	
		commcondition();
		location.href="reportForm_list?orderid="+ordid;    
	}	
   /**
    * author:hutianfa
    * describe:弹出层
    * @type 
    */
    $("#addbtn").click(function(){ 		 
	   $("#myModal").modal("show");
    });
    $("#close,#cancel").click(function(){
	   $("#myModal").modal("hide");
	});
    $(":input").focus(function(){
  	  $('[data-toggle="tooltip"]').tooltip();
  	});
    /**
     * author：hutianfa
     * describe：新增订单信息
     * @type String
     */
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
             		   $("#myModal").modal("show");
             	   }  
                }
            }
         });
       }
	  /**
	  * author:hutianfa
	  * describe:添加订单信息
	  */
      $("#sureaddbtn").click(function(){
    	  var odrnum=$("#odrnum").val();	     
          var odrname=$("#odrname").val();
          var telphone=$("#telphone").val();
          var adres=$("#adrs .selected").text();
          var re = /^[0-9]*[0-9]$/i;
        if(re.test(odrnum) && odrnum%500!==0) {
	        $(".errortilp").html("订单数量必须是500的倍数！");
	        return;
        }else if(odrname =="" || odrname ==null){
        	$(".errortilp").html("订单人不能为空！");
 	        return;
        }else if(telphone =="" || telphone==null){
        	$(".errortilp").html("联系电话不能为空！");
 	        return;
        }else if(adres =="" || adres ==null){
        	$(".errortilp").html("所属地址不能为空！");
 	        return;
        }else{
 		  $("#myModal").modal("hide");
	      queren("确认要新增订单吗？", function(){
	        var datastr={"num":odrnum,"name":odrname,"phone":telphone,"address":adres };
 		    $.post("order_add",datastr ,function(data){ 
    	       if(data==0){
	   				message("下单成功,请确认！",data);
	   			 }else{
	   				message("下单失败,请重新提交订单！",data);
	   			 } 
    	       $(".errortilp").html("");
            });	 		    
	     });
        }
      });
      /**
       *author:hutianfa 
       * describe:修改订单信息
       */
      var val1="",oldval1="";var _this,isinput;
      $182("#updatab tbody tr td.revise .edute").live("click",function(){ 
    	 oldval1=$(this).parents("td.revise").siblings("td:eq(2)").text();    	 
    	 $(this).parents("td.revise").prevAll(".tdnum").html(function(i,html){
               return '<input type="text" value='+html+' />';   
         }); 
    	 $(this).val("保存");$(this).attr("class","save btn btn-xs btn-info");
    	 $(this).children("i").attr("class","fa fa-save");
    	 $(this).children("i").text(" 保存");
    	 _this=$(this);	
    	 isinput=_this.parents("td.revise").children().get(0).tagName;    	 
    	 if(isinput =="BUTTON"){
    		 $182("#updatab tbody tr td button.save").live("click",function(){
    			val1=$(this).parents().find("td:eq(2)").find("input[type=text]").val();
	        	var id=$(this).attr("id");
	        	$.post("order_orderModify",{"id":id,"num":val1},function(data){  
    	    	   if(data==0){
		        	  $(this).parents().find("td:eq(2)").text(val1);
		        	  $(this).val("编辑");$(this).attr("class","btn btn-xs btn-info");
			    	  $(this).children("i").attr("class","fa fa-edit");
			    	  $(this).children("i").text(" 编辑");
		   			  message("修改成功！",data);
		   			  $("#alltishi").html("");
		   			}else{
			          $(this).parents().find("td:eq(2)").text(oldval1);
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
  /**
   * describe:查看订单信息
   * @param {} id
   */
  function lookSearch(id){  
  	$("#detailtab").children(".itemdiv1").remove();
  	$("#delivery").children().remove();
  	var lookid=id.valueOf();
    var thistd=$("#"+lookid).parent("td").prevAll();
    var prithtml="",arr=["","","","","联系电话","所属地址","订单数量","订 单  人","订单号"]				     
    for(var i=thistd.length-1;i>=4;i--){  
    	prithtml ="<div class='itemdiv1 dialogdiv'><div class='user1'>";
        prithtml +="<span>"+arr[i]+"</span></div>";     
        prithtml +="<div class='body'><div class='name'>" +
        		"<a href='javascript:void(0)'>"+thistd[i].innerText+"</a></div>" +
        				"</div></div>"; 
        $("#detailtab").append(prithtml);
    }
    var delivehtml="";arrd=["快递编号","快递名称","运单状态","到达时间","描述"];
    var arrda=["0082131","顺丰快递","运输中","","你的包裹正在发往攀枝花途中"];
    for(var j=0;j<arrd.length;j++){
    	delivehtml ="<div class='user'>";
    	delivehtml +="<span>"+arrd[j]+"</span></div>";
    	delivehtml +="<div class='body'><div class='name'>";
    	delivehtml +="<a href='javascript:void(0)'>"+arrda[j]+"</a>" +
    			   "</div></div>";  
       $("#delivery").append(delivehtml);
    }
    $("#lookmyModal").modal("show");      
  }
  /**
   * author:hutianfa
   * describe:导出订单数据
   * @param {} obj
   */
  $("#printbtn").click(function(){
    $("#updatab").table2excel({
	  // 导出的Excel文档的名称
	  name: "xlsx",
	  // Excel文件的名称
	  filename: "订单信息记录"
	});
  });
  /**
   * describe:地址选择操作
   */
   $("#addres").click(function(){
	   $(this).children().removeClass("searchable-select-hide");
	   $(this).children().show();
	   $("#addres").searchData(); 
   });
   $182("#adrs .searchable-select-item").live("click",function(){
	   $(this).addClass("selected").siblings().removeClass("selected");
	   var etype=$("#adrs .selected").attr("data-value");
	   var val=$("#adrs div.selected").text();
	   $("#addres .searchable-select-holder").text(val);
	   $("#addres .searchable-select-dropdown").addClass("searchable-select-hide");
   }); 
  
  
  
  
  
  
  
  
