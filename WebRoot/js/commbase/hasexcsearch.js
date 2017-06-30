   /**
    * author：hutianfa
    * describe：获取浏览器地址url参数
    * @param {} id
    * @return {String}
    */
   function getQueryString(id) { 
	 var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i");
	 var r = window.location.search.substr(1).match(reg); 
	 if (r != null) return decodeURIComponent(r[2]); return ""; 
	}
	 var idtype=getQueryString("condition.excType"); 
	  $("#divcontent3,#divcontent4").hide();
	 if(idtype=="5" || idtype=="10" || idtype=="13"){//正常情况三种异常
		 $("#divcontent3").show();$("#divcontent4").remove();
	 }else if(idtype=="1"){//非法施封的异常		      		 
		$("#divcontent4,#freediv,#freepic").show();$("#divcontent3,#seadiv,#seapic").remove();
	 }else if(idtype=="0"){
		$("#divcontent4,#seaddiv,#seapic").show();$("#divcontent3,#freediv,#freepic").remove();	
	 }else{
		 $("#divcontent3").show();$("#divcontent4").remove();
	 }
	/**
	 * describe：条件查询操作
	 * @type 
	 */
	var starttime,endtime,posname="",comname="",areaname="",carid="",jumpurl,exctype;
	function comcondition(){
		starttime= $("#starttime").val();endtime = $("#endtime").val();
		posname=$("#pos1 .selected").attr("data-value");
		areaname=$("#area1 .selected").attr("data-value");
		comname=$("#coma1 .selected").attr("data-value");
		pername=$("#per1 .selected").attr("data-value");
		exctype=$("#exc1 .selected").attr("data-value");
		carid=$("#carid .selected").attr("data-value");
		(carid=="" || carid==null) ? carid="" : carid=carid;
		(posname=="" || posname==null) ? posname="" :posname=posname;
		(comname=="" || comname==null) ? comname="" : comname=comname;
		(areaname=="" || areaname==null) ? areaname="" : areaname=areaname;
		(exctype=="" || exctype==null) ? exctype="" : exctype=exctype;
	}
	function nextJump(){ 
		comcondition();
		if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_allHasHandle?condition.comId="+comname+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&pageBean.curentPage=";
		}else if(exctype=="0" || exctype=="1"){
			jumpurl="excRecord_allHasillegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&condition.excStatus="+0+"&pageBean.curentPage=";
		}
		commnextJump(jumpurl);
	}
	function preJump(){ 
		comcondition();
		if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_allHasHandle?condition.comId="+comname+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&pageBean.curentPage=";
		}else if(exctype=="0" || exctype=="1"){
	    	jumpurl="excRecord_allHasillegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&condition.excStatus="+0+"&pageBean.curentPage=";
		}
		commpreJump(jumpurl);
	}
	function jumpPage(){
		comcondition();
		if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_allHasHandle?condition.comId="+comname+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&pageBean.curentPage=";
		}else if(exctype=="0" || exctype=="1"){
	    	jumpurl="excRecord_allHasillegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&condition.excStatus="+0+"&pageBean.curentPage=";
		}
		commjumpPage(jumpurl);
	}
	function searchhasExcRecord(){
		 comcondition();
		 if(exctype=="5" || exctype=="13" || exctype=="10" || exctype==""){
			jumpurl="excRecord_allHasHandle?condition.comId="+comname+"&condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.areaid="+areaname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&pageBean.curentPage=";
		 }else if(exctype=="0" || exctype=="1"){
	    	jumpurl="excRecord_allHasillegality?condition.excType="+exctype+"&condition.carFlapper="+carid+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.posid="+posname+"&condition.excStatus="+0+"&pageBean.curentPage=";
		 }
	     commSearch(jumpurl);
	}
	
	
	
	