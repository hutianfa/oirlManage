function myBrowser(){
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var ua=navigator.userAgent.toLowerCase(),
         	rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
         	isIE=rMsie.exec(ua)!=null; 
        if (userAgent.indexOf("Opera") > -1) {
           // return "Opera"
        }; //判断是否Opera浏览器
        if (userAgent.indexOf("Firefox") > -1) {
        	$(".input-group-addon").addClass("browsercss");
        	$("table .box .input-group-addon").css("font-size","15px");
        } //判断是否Firefox浏览器
        if (userAgent.indexOf("Chrome") > -1){
             $("table .box .input-group-addon").css("font-size","12px");
          //  return "Chrome";
        }
        if(navigator.userAgent.indexOf("360SE")>0){
        	$("table .box .input-group-addon").css("font-size","12px");
        }
        if (userAgent.indexOf("Safari") > -1) {
          //  return "Safari";
        } //判断是否Safari浏览器   		
		if(isIE){
		   $(".input-group-addon").addClass("browsercss");
		   $("table .box .input-group-addon").css("font-size","15px");		   
		}//判断是否ie浏览器
} 
 myBrowser();           