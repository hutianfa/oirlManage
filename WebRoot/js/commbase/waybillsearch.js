var starttime,endtime,status="",empType="",jumpurl,tag="",carid="",opval=1,comname="",posname="",areaname="",pername="";			    
function nextJump(){ 
	starttime=$("#starttime").val();endtime = $("#endtime").val();
	posname=$("#pos1 .selected").attr("data-value");
	areaname=$("#area1 .selected").attr("data-value");
	comname=$("#coma1 .selected").attr("data-value");
	pername=$("#per1 .selected").attr("data-value");
	tag=$("#tag1 .selected").attr("data-value");
	status=$("#stutas1 .selected").attr("data-value");
	carid=$("#carid .selected").attr("data-value");
	empType=$("#isemp1 .selected").attr("data-value");    			
	(posname=="" || posname==null) ? posname="" :posname=posname;
	(comname=="" || comname==null) ? comname="" : comname=comname;
	(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	(pername=="" || pername==null) ? pername="" : pername=pername;
	(carid=="" || carid==null) ? carid="" : carid=carid;
	(empType=="" || empType==null) ? empType="" : empType=empType;
	jumpurl="waybill_list?condition.perId="+pername+"&condition.empowerType="+empType+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.tag="+tag+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&pageBean.curentPage=";
	commnextJump(jumpurl);
}
function preJump(){ 
	starttime=$("#starttime").val();endtime = $("#endtime").val();
	posname=$("#pos1 .selected").attr("data-value");
	areaname=$("#area1 .selected").attr("data-value");
	comname=$("#coma1 .selected").attr("data-value");
	pername=$("#per1 .selected").attr("data-value");
	tag=$("#tag1 .selected").attr("data-value");
	status=$("#stutas1 .selected").attr("data-value");
	carid=$("#carid .selected").attr("data-value");
	empType=$("#isemp1 .selected").attr("data-value");
	(posname=="" || posname==null) ? posname="" : posname=posname;
	(comname=="" || comname==null) ? comname="" : comname=comname;
	(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	(pername=="" || pername==null) ? pername="" : pername=pername;
	(carid=="" || carid==null) ? carid="" : carid=carid;
	(empType=="" || empType==null) ? empType="" : empType=empType;
	jumpurl="waybill_list?condition.perId="+pername+"&condition.empowerType="+empType+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.tag="+tag+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&pageBean.curentPage=";
	commpreJump(jumpurl);
}
function jumpPage(){
	starttime=$("#starttime").val();endtime = $("#endtime").val();
	posname=$("#pos1 .selected").attr("data-value");
	areaname=$("#area1 .selected").attr("data-value");
	comname=$("#coma1 .selected").attr("data-value");
	pername=$("#per1 .selected").attr("data-value");
	tag=$("#tag1 .selected").attr("data-value");
	status=$("#stutas1 .selected").attr("data-value");
	carid=$("#carid .selected").attr("data-value");
	empType=$("#isemp1 .selected").attr("data-value");
	(pername=="" || pername==null) ? pername="" : pername=pername;
	(posname=="" || posname==null) ? posname="" :posname=posname;
	(comname=="" || comname==null) ? comname="" : comname=comname;
	(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	(carid=="" || carid==null) ? carid="" : carid=carid;
	(empType=="" || empType==null) ? empType="" : empType=empType;
	jumpurl="waybill_list?condition.perId="+pername+"&condition.empowerType="+empType+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.tag="+tag+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&pageBean.curentPage=";
	commjumpPage(jumpurl);
}
function searchWaybillList(){     			
	starttime=document.getElementById("starttime").value;
	endtime = document.getElementById("endtime").value;
	posname=$("#pos1 .selected").attr("data-value");
	areaname=$("#area1 .selected").attr("data-value");
	comname=$("#coma1 .selected").attr("data-value");
	pername=$("#per1 .selected").attr("data-value");
	tag=$("#tag1 .selected").attr("data-value");
	status=$("#stutas1 .selected").attr("data-value");
	carid=$("#carid .selected").attr("data-value");
	empType=$("#isemp1 .selected").attr("data-value");
	(posname=="" || posname==null) ? posname="" :posname=posname;
	(comname=="" || comname==null) ? comname="" : comname=comname;
	(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	(pername=="" || pername==null) ? pername="" : pername=pername;
	(carid=="" || carid==null) ? carid="" : carid=carid;
	(empType=="" || empType==null) ? empType="" : empType=empType;
	jumpurl="waybill_list?condition.perId="+pername+"&condition.empowerType="+empType+"&condition.posId="+posname+"&condition.comId="+comname+"&condition.tag="+tag+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.info="+opval+"";
	commSearch(jumpurl);
}
// 导出数据
$("#tableExcelbtn").click(function(){
    starttime=$("#starttime").val();endtime = $("#endtime").val();
    posname=$("#pos1 .selected").attr("data-value");
	areaname=$("#area1 .selected").attr("data-value");
	comname=$("#coma1 .selected").attr("data-value");
	pername=$("#per1 .selected").attr("data-value");
	tag=$("#tag1 .selected").attr("data-value");
	status=$("#stutas1 .selected").attr("data-value");
	carid=$("#carid .selected").attr("data-value"); 
	empType=$("#isemp1 .selected").attr("data-value");
	var expurl;     			
	(posname=="" || posname==null) ? posname="" :posname=posname;
	(comname=="" || comname==null) ? comname="" : comname=comname;
	(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
	(pername=="" || pername==null) ? pername="" : pername=pername;
	(carid=="" || carid==null) ? carid="" : carid=carid;
	(empType=="" || empType==null) ? empType="" : empType=empType;
	expurl="waybill_allList?condition.perId="+pername+"&condition.empowerType="+empType+"&condition.comId="+comname+"&condition.tag="+tag+"&condition.carFlapper="+carid+"&condition.posId="+posname+"&condition.status="+status+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.info="+opval+"&condition.areaid="+areaname+"";
		location.href=expurl;
	});     	
   $("#isemp1").click(function(){
	   $(this).children().removeClass("searchable-select-hide");
	   $(this).children().show();
	   $("#isemp1").searchData(); 
   });
   $182("#isemp .searchable-select-item").live("click",function(){
	   $(this).addClass("selected").siblings().removeClass("selected");
	   var etype=$("#isemp .selected").attr("data-value");
	   var val=$("#isemp div.selected").text();
	   $("#isemp1 .searchable-select-holder").text(val);
	   $("#isemp1 .searchable-select-dropdown").addClass("searchable-select-hide");
   }); 