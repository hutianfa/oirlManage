(function(view){
	 view.commSearch = function(url){
		location.href = url;
	};
	var curentPage = $("#curentPage").val();
	view.commnextJump = function(jumpurl){
		 curentPage = parseInt(curentPage)+1;
		 location.href = jumpurl+curentPage;
	};
	view.commpreJump = function(jumpurl){
		curentPage = curentPage -1;
		 location.href = jumpurl+curentPage;
	};	
    view.commjumpPage = function(jumpurl){	
    	var jumpPage = $("#jumpPage").val();
    	var totalPage=$("#totalPage").val();
		if(parseInt(jumpPage) > parseInt(totalPage)){
			return;
		}
		 curentPage=jumpPage;
		 location.href = jumpurl+jumpPage;
	};
})(window);
