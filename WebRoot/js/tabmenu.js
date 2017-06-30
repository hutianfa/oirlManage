 /**
  * authr:hutianfa
  * describe:一级显示下拉
  * @type String
  *//*
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
  *//**
  * authr:hutianfa
  * describe:片区显示下拉
  * @type String
  *//*
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
  });*/


