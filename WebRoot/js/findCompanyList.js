    var txtid,labname;
   function findcom(){	   
	   $.ajax({    
	        type:'get',        
			url:"company_getListByName",    
			cache:false,    
			dataType:'json',    
			success:function(data){ 
				console.log(data);
	    	Actioncomplete(data);
	    }    
	});   
	   function  Actioncomplete(datas){
		   $104('#loc,#locc').autocomplete(datas,{
	        max: 12,     //列表里的条目数
	        minChars: 0,   //自动完成激活之前填入的最小字符
	        width: 150,     //提示的宽度，溢出隐藏
	        scrollHeight: 300,   //提示的高度，溢出显示滚动条
	        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	        autoFill: false,    //自动填充
	        formatItem: function(row, i, max) {
	            return  row.comid+"\" ["+row.comName+"]";
		        },
		        formatMatch: function(row, i, max) {
		            return row.comName + row.comid;
		        },
		        formatResult: function(row) {
		            return row.comName;
		        }
		    }).result(function(event, row, formatted){
		          txtid = row.comid;
		          labname=row.comName;
		          $("#l").val(txtid);
		    }); 
	   } 
   }
