    $('#main-menu').metisMenu();
  	var str=location.pathname;
  		defurl=str.substr(str.lastIndexOf("/")+1);      	
  	switch(defurl){
  	  case "waybill_list":
  		 $("#wyb ul ").addClass('collapse in');
         $("#wyb ul li:eq(0) a").addClass('active-menu');
  		 break;
  	  case "reportForm_list":
  		 $("#ord ul ").addClass('collapse in');
         $("#ord ul li:eq(0) a").addClass('active-menu');
  		 break;
  	  /*case "fixSea_list":
  		 $("#wyb ul ").addClass('collapse in');
         $("#wyb ul li:eq(1) a").addClass('active-menu');
 		 break;*/
  	  case "report_Benefitlist":      		
  		  $("#bbo ul ").addClass('collapse in');
          $("#bbo ul li:eq(0) a").addClass('active-menu');
  		  break;
  	  case "effic_efficlist":      		
  		$("#bbo ul ").addClass('collapse in');
        $("#bbo ul li:eq(1) a").addClass('active-menu');
 		  break;  
  	  case "safe_Seculist":      		
  		$("#bbo ul ").addClass('collapse in');
        $("#bbo ul li:eq(2) a").addClass('active-menu');
 		  break;     		 
  	  case "excRecord_list":      		
  		$("#wyb ul ").addClass('collapse in');
        $("#wyb ul li:eq(1) a").addClass('active-menu');
 		  break;
  	  case "excRecord_illegality":
  		$("#wyb ul ").addClass('collapse in');
        $("#wyb ul li:eq(1) a").addClass('active-menu');
  		  break;
  	  case "excRecord_allHasillegality":
  		$("#wyb ul ").addClass('collapse in');
        $("#wyb ul li:eq(1) a").addClass('active-menu');
		  break;      		
  	  case "excRecord_allHasHandle":      		
  		$("#wyb ul ").addClass('collapse in');
        $("#wyb ul li:eq(1) a").addClass('active-menu');
		  break;    		  
  	  case "thing_Allthing":      		
  		$("#thing ul ").addClass('collapse in');
        $("#thing ul li:eq(0) a").addClass('active-menu');
	     break;
  	  case "person_list":      		
  		$("#bas ul.inout-nav ").addClass('collapse in');
  		$("#bas ul li.pernav ul").addClass('collapse in');
        $("#bas ul li.pernav ul li:eq(0) a").addClass('active-menu');
	    break;   		
  	  case "empower_Alllist":      		
  		$("#bas ul.inout-nav").addClass('collapse in');
  		$("#bas ul li.empnav ul").addClass('collapse in');
        $("#bas ul li.empnav ul li:eq(0) a").addClass('active-menu');
	     break;  		
  	  case "car_list":      		
  		$("#bas ul.inout-nav").addClass('collapse in');
  		$("#bas ul li.carnav ul").addClass('collapse in');
        $("#bas ul li.carnav ul li:eq(0) a").addClass('active-menu');
	     break;
  	  case "position_list":      		
  		$("#bas ul.inout-nav ").addClass('collapse in');
  		$("#bas ul li.posnav ul").addClass('collapse in');
        $("#bas ul li.posnav ul li:eq(0) a").addClass('active-menu');
	     break;
  	  case "in_list":      		
  		$("#bas ul.inout-nav ").addClass('collapse in');
  		$("#bas ul li.innav ul").addClass('collapse in');
        $("#bas ul li.innav ul li:eq(0) a").addClass('active-menu');
	     break;
  	  case "appNum_num":      		
  		$("#bas ul.inout-nav ").addClass('collapse in');	
  		$("#bas ul li.appnav ul").addClass('collapse in');
        $("#bas ul li.appnav ul li:eq(0) a").addClass('active-menu');
	     break;
  	  case "admin_listGeneralManager":      		
  		$("#bas ul.inout-nav ").addClass('collapse in');
  		$("#bas ul li.pernav ul").addClass('collapse in');
        $("#bas ul li.pernav ul li:eq(1) a").addClass('active-menu');
	     break;
	 /*case "reportForm_detailed":      		
  		$("#sto ul.inout-nav ").addClass('collapse in');
  		$("#sto ul li.instro ul").addClass('collapse in');
        $("#sto ul li.instro ul li:eq(0) a").addClass('active-menu');
	    break;
	  case "reportForm_inarea":      		
  		$("#sto ul.inout-nav ").addClass('collapse in');
  		$("#sto ul li.instro ul").addClass('collapse in');
        $("#sto ul li.instro ul li:eq(1) a").addClass('active-menu');
	    break;
	  case "reportForm_inoil":      		
  		$("#sto ul.inout-nav ").addClass('collapse in');
  		$("#sto ul li.instro ul").addClass('collapse in');
        $("#sto ul li.instro ul li:eq(2) a").addClass('active-menu');
	    break;*/
	  case "reportForm_instation":      		
  		 $("#dat ul ").addClass('collapse in');
         $("#dat ul li:eq(1) a").addClass('active-menu');
	    break;
	 case "reportForm_orderlist":      		
        $("#sto ul.inout-nav ").addClass('collapse in');
  		$("#sto ul li.instro ul").addClass('collapse in');
        $("#sto ul li.instro ul li:eq(0) a").addClass('active-menu');
	    break;
	 /*case "reportForm_outarea":      		
  		$("#sto ul.inout-nav ").addClass('collapse in');
  		$("#sto ul li.outstro ul").addClass('collapse in');
        $("#sto ul li.outstro ul li:eq(1) a").addClass('active-menu');
	    break;
	 case "reportForm_outoil":      		
  		$("#sto ul.inout-nav ").addClass('collapse in');
  		$("#sto ul li.outstro ul").addClass('collapse in');
        $("#sto ul li.outstro ul li:eq(2) a").addClass('active-menu');
	    break;*/
	 case "reportForm_tjlist":
  		 $("#dat ul ").addClass('collapse in');
         $("#dat ul li:eq(0) a").addClass('active-menu');
  		 break;
	  case "reportForm_tonowlist":
  		 $("#sto ul.inout-nav ").addClass('collapse in');
  		 $("#sto ul li.outstro ul").addClass('collapse in');
         $("#sto ul li.outstro ul li:eq(0) a").addClass('active-menu');
  		 break;
  	}
  	 $("#fakeloader").fakeLoader({
          timeToHide:3000, 
          zIndex:999, 
          spinner:"spinner2",
          bgColor:"rgba(46,204,113,0.7)"
          
    }); 