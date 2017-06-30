  var seaname,tempposid; 
  function findpos(){
	  $.ajax({    
	        type:'get',        
	        url:"position_getListByName",    
	        cache:false,    
	        dataType:'json',    
	        success:function(data){ 
	        	Actioncomplete(data);
	         }    
	    });   
	   function  Actioncomplete(datas){
		   $104('#carid').autocomplete(datas,{
		        max: 12,    //列表里的条目数
		        minChars: 1,    //自动完成激活之前填入的最小字符
		        width: 150,     //提示的宽度，溢出隐藏
		        scrollHeight: 300,   //提示的高度，溢出显示滚动条
		        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
		        autoFill: false,    //自动填充
		        formatItem: function(row, i, max) {
		            return  "\ ["+row.posName+"]";
		        },
		        formatMatch: function(row, i, max) {
		            return row.posName+row.posId;
		        },
		        formatResult: function(row) {
		            return row.posName;
		        }
		    }).result(function(event, row, formatted){
		          seadname = row.posName;
		          tempposid=row.posId;
		    });
	   }
  }