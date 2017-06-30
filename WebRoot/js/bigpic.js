//查看大图
	$(document).ready(function(){
	    $.fn.decorateIframe = function(options) {
	            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);
	            $(this).each(function() {
	                var $myThis = $(this);
	                //创建一个IFRAME
	                var divIframe = $("<iframe />");  
	                divIframe.attr("id", opts.iframeId);
	                divIframe.css({"position":"absolute",
	                               "display":"none",
	                               "display":"block",
	                               "z-index":opts.iframeZIndex,
	                               "border":"0",
	                               "top":"0",
	                               "left":"0"
	                });
	                if (opts.width == 0) {
	                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");
	                }
	                if (opts.height == 0) {
	                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");
	                }
	                divIframe.css("filter", "mask(color=#fff)");
	                $myThis.append(divIframe);
	            });
	    }
	    $.fn.decorateIframe.defaults = {
	        iframeId: "decorateIframe1",
	        iframeZIndex: -1,
	        width: 0,
	        height: 0
	    }
	    //放大镜视窗
	    $("#bigView").decorateIframe();
	    //大视窗看图
	    function mouseover(e) {                	           
		        if ($("#winSelector").css("display") == "none") {
		            $("#winSelector,#bigView").show();
		            $("#bigView img").attr("src",this.src);
		        }
		        $("#winSelector").css(fixedPosition(e));
		        e.stopPropagation();
	    }
	    function mouseOut(e) {
	        if ($("#winSelector").css("display") != "none") {
	            $("#winSelector,#bigView").hide();
	            $("#bigView img").attr("src","");
	        }
	        e.stopPropagation();
	    }
	    $("#seaphone").mouseover(mouseover); //中图事件
	    $("#seaphone,#winSelector").mouseover(mouseover).mouseout(mouseOut); //选择器事件
	    
	    $("#freephone").mouseover(mouseover); //中图事件
	    $("#freephone,#winSelector").mouseover(mouseover).mouseout(mouseOut); //选择器事件

	    var $divWidth = $("#winSelector").width(); //选择器宽度
	    var $divHeight = $("#winSelector").height(); //选择器高度
	    var $imgWidth = $(".bigpic").width(); //中图宽度
	    var $imgHeight = $(".bigpic").height(); //中图高度
	    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

	    $("#bigView").scrollLeft(0).scrollTop(0);
	    function fixedPosition(e) {
	        if (e == null) {
	            return;    
	        }
	        var $imgLeft = $(".bigpic").offset().left; //中图左边距
	        var $imgTop = $(".bigpic").offset().top; //中图上边距
	        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
	        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
	        X = X < 0 ? 0 : X;
	        Y = Y < 0 ? 0 : Y;
	        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
	        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

	        if ($viewImgWidth == null) {
	            $viewImgWidth = $("#bigView img").outerWidth();
	            $viewImgHeight = $("#bigView img").height();
	            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
	                $viewImgWidth = $viewImgHeight = 800;
	            }
	            $height = $divHeight * $viewImgHeight / $imgHeight;
	            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
	            $("#bigView").height($height);
	        }      
	        var top = ($(window).height() - $("#bigView img").height())/2 +$(document).scrollTop();  
			var left = ($(window).width() - $("#bigView img").width())/4; 
	        var scrollX = X * $viewImgWidth / $imgWidth;
	        var scrollY = Y * $viewImgHeight / $imgHeight;
	        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 ,"border":"3px solid #ccc"});
	        $("#bigView").css({ "top": top+'px' , "left":left+'px' });
	        $(window).resize(function() {   	        	  
	        	left = ($(window).width() - $("#bigView img").width())/4;  
	        	top = ($(window).height() - $("#bigView img").height())/2 +$(document).scrollTop();  
	        	$("#bigView").css({left: left+'px', top: top + 'px'});   
	        });      
        	$(window).scroll(function() {   
        		left = ($(window).width() - $("#bigView img").width())/4;  
	        	top = ($(window).height() - $("#bigView img").height())/2 +$(document).scrollTop();  
	        	$("#bigView").css({left: left+'px', top: top + 'px'});   
        	});   
            console.log(top,left,$("#bigView img").width());
	        return { left: X, top: Y };
	    }
	});