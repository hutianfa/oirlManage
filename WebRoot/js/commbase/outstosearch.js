	/**
	 * author:hutianfa
	 * describe:出库公共条件查询
	 */
	function nextJump(){
		var curentPage=document.getElementById("curentPage").value;
		curentPage=parseInt(curentPage)+1;
		commJump(curentPage);
	}
	function preJump(){
		var curentPage=document.getElementById("curentPage").value;
		curentPage=curentPage-1;
		commJump(curentPage);
	}
	function jumpPage(){
		var jumpPage=document.getElementById("jumpPage").value;
		var totalPage=document.getElementById("totalPage").value;
		if(parseInt(jumpPage)>parseInt(totalPage)){
			return;
		}
		if(parseInt(jumpPage)>0){
			commJump(jumpPage);
		}
	}
	function commJump(curentPage){
		
	}
	function searchOrder(){
		
	}	
     

  
  
  
  
  
  
  
  
