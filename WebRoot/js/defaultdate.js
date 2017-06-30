        function GetDatee()  
         {  
	        	   var d = new Date();
	         	   var year = d.getFullYear();
	         	   var mon=d.getMonth()+1;
	         	   var day=d.getDate();  
		           var h = d.getHours(); 
		           var m = d.getMinutes(); 
		           var se = d.getSeconds();
	         	   s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day)+" "+((h<10 ? "0"+ h : h))+":"+(m<10 ? "0" + m : m)+":"+(se<10 ? "0" +se : se);            
         	   return s;     
         }  
         function GetDates()  
         {  
        	var n = 7;
       	    var d = new Date();
       	    var year = d.getFullYear();
       	    var mon=d.getMonth()+1;
       	    var day=d.getDate();
       	    var h = d.getHours(); 
       	    var m = d.getMinutes(); 
       	    var se = d.getSeconds();
       	    if(day <= n){
       	            if(mon>1) {
       	               mon=mon-1;
       	            }
       	           else {
       	             year = year-1;
       	             mon = 12;
       	             }
       	           }
       	          d.setDate(d.getDate()-n);
       	          year = d.getFullYear();
       	          mon=d.getMonth()+1;
       	          day=d.getDate();
       	     s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day)+" "+((h<10 ? "0"+ h :h))+":"+(m<10 ? "0" + m : m)+":"+(se<10 ? "0" +se : se);
       	     return s;    
         }   