   /**
    * author：hutianfa
    * describe：初始化加载数据
    * @type Number
    */
   var updown=0,benefithtml;
    var cararr=[],totalarr=[],lossarr=[],tlarr=[];
     function benefitstar(url){
    	 $.ajax({
   	    	type:"get",
   	    	url:url,
   	    	"Content-Type":'text/json;charset=UTF-8',
   	    	cache:true,
   	        dataType:"json",   
   	        success:function (data){	   	      
   	        	 var datas = eval(data);
   	        	 $("#benefitcoment").children().remove();
   	        	 cararr=[],totalarr=[],lossarr=[],tlarr=[];
   	        	 $.each(datas,function(i,item){
   	        		if(updown==0){
   	        			benefithtml="<tr class='info'><td >"+datas[i].car+"</td>";
		   	        	benefithtml+="<td>"+datas[i].com+"</td>";
		   	        	benefithtml+="<td>"+datas[i].total+"</td>";
		   	        	benefithtml+="<td>"+datas[i].loss+"</td>";	       
		   	        	benefithtml+="<td>"+((datas[i].tl)*1000).toFixed(2)+"‰"+"</td>";
		   	        	benefithtml+="<td>"+(i+1)+"</td></tr>";
		   	        	$("#benefitcoment").append(benefithtml);
   	        		}
   	        		if(updown==1){
   	        			benefithtml="<tr class='info'><td>"+datas[i].car+"</td>";
		   	        	benefithtml+="<td>"+datas[i].com+"</td>";
		   	        	benefithtml+="<td>"+datas[i].total+"</td>";
		   	        	benefithtml+="<td>"+datas[i].loss+"</td>";	       
		   	        	benefithtml+="<td>"+((datas[i].tl)*1000).toFixed(2)+"‰"+"</td>";
		   	        	benefithtml+="<td>"+(datas.length-i)+"</td></tr>";
		   	        	$("#benefitcoment").append(benefithtml);
   	        		}
   	        		cararr.push(datas[i].car);
   	        		totalarr.push(datas[i].total);
   	        		lossarr.push(datas[i].loss);
   	        		tlarr.push((datas[i].tl)*1000);
   	        	});
   	        	/**
   	        	 * author：hutianfa
   	        	 * describe：图形报表统计
   	        	 */
   	        	/* $182('#benefitcontainer').highcharts({
   	        		chart: {
	                    zoomType: 'xy'
	                },
	                title: {
	                	text: '效益之星图形报表',
	                    x: 0 //center
	                },
	                subtitle: {
	                	text: '中石油四川公司',
	                    x: 0
	                },
	                xAxis: [{
	                	title:{text:'车牌号'},
	                    categories: cararr,    	
	                }],
	                yAxis: [{ 
	                    labels: {
	                        formatter: function() {
	                            return this.value;
	                        },
	                        style: {
	                            color: '#89A54E'
	                        }
	                    },
	                    tickPositions: [0,20000,40000,60000,80000,100000,120000,140000],
	                    title: {
	                        text: '总运输容量(升)',
	                        style: {
	                            color: '#89A54E'
	                        }
	                    }		                   

	                }, { 
	                    gridLineWidth: 0,
	                    title: {
	                        text: '总油损容量(升)',
	                        style: {
	                            color: '#4572A7'
	                        }
	                    },
	                    tickPositions: [0,20,40,60,80,100,120,140],
	                    labels: {
	                        formatter: function() {
	                            return this.value ;
	                        },
	                        style: {
	                            color: '#4572A7'
	                        }
	                    },
	                    opposite: true
	                },{
	                    gridLineWidth: 0,
	                    title: {
	                        text: '油损',
	                        style: {
	                            color: '#AA4643'
	                        }
	                    },
	                    tickPositions: [0,2,4,6,8,10,12,14],
	                    labels: {
	                        formatter: function() {
	                            return this.value +'‰';
	                        },
	                        style: {
	                            color: '#AA4643'
	                        }
	                    },
	                    opposite: true
	                }],
	                tooltip: {
	                    shared: true,
	                    valueDecimals: 2
	                },
	                legend: {
	                    layout: 'vertical',
	                    align: 'right',
	                    verticalAlign: 'middle',
	                    borderWidth: 0
	                },
	                series: [{
	                	 name: '油损',				                 
		                    color: '#AA4643',
		                    type: 'column',
		                    data: tlarr,
		                    yAxis: 2,
		                    tooltip: {
		                        valueSuffix: '‰'
		                    }
		                },{
	                	name: '总油损容量',
		                    type: 'column',
		                    color: '#4572A7',
		                    yAxis:1,
		                    data: lossarr,			                    
		                    tooltip: {
		                        valueSuffix: ' 升'
		                    }
	               
                	},{
		               name: '总运输容量',				               
		                    color: '#89A54E',
		                    type: 'column',
		                    data: totalarr,
		                    tooltip: {
		                        valueSuffix: '升'
		                    }
		              	  }]
	             }); */
   	           }
         });	    	 
     }         
     $("#starttime").val(starDate);$("#endtime").val(endDate);
     var jumpurl="report_petrolReport?sortT="+updown+"&condition.beginTime="+starDate+"&condition.endTime="+endDate+"";
     window.onload=function(){benefitstar(jumpurl);}
     /**
      * author：hutianfa
      * describe：条件查询
      * @type 
      */
     var starttime,endtime, comname,locid,carid="";
     function searchbenefitList(){
		starttime=document.getElementById("starttime").value;
		endtime = document.getElementById("endtime").value; 
		comname=$("#loc").val();locid=$("#locid").val();
		var url;loccc = txtid;
		carid=$("#carid .selected").attr("data-value"); 
		carid=encodeURI(carid,"UTF-8"); 
		(carid=="" || carid==null || carid=="undefined") ? carid="" : carid=carid;
		(comname=="" || comname==null || comname=="undefined") ? comname="" : comname=comname;
		if(loccc !="" && loccc != null){
  			url="report_petrolReport?condition.carFlapper="+carid+"&condition.comName="+comname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&condition.comId="+loccc+"&sortT="+updown+"";
		} else{
			url="report_petrolReport?condition.carFlapper="+carid+"&condition.comName="+comname+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"&sortT="+updown+"";
		}
		benefitstar(url);
	}
	/**
	 * describe:顺序排名
	 */
    $(".upbene").click(function(){
    	starttime=document.getElementById("starttime").value;
		endtime = document.getElementById("endtime").value;
    	$(".downbene").removeClass("defsty");
    	$(".upbene").addClass("defsty");
    	updown=$(this).attr("id");
    	var tempurl="report_petrolReport?sortT="+updown+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"";
    	benefitstar(tempurl);
    });
    /**
     * describe:倒序排名
     */
    $(".downbene").click(function(){
    	starttime=document.getElementById("starttime").value;
		endtime = document.getElementById("endtime").value;
    	$(".upbene").removeClass("defsty");
    	$(".downbene").addClass("defsty");
    	updown=$(this).attr("id");
    	var tempurl="report_petrolReport?sortT="+updown+"&condition.beginTime="+starttime+"&condition.endTime="+endtime+"";
    	benefitstar(tempurl);
    });
    /**
     * describe:数据导出和时间控件
     */
    !function(){
      laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
      laydate({elem: '#starttime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});//绑定元素
      laydate({elem:'#endtime',istime:true,format:'YYYY-MM-DD hh:mm:ss'});
    }(); 
   var $exportLink = document.getElementById('export');
	 $exportLink.addEventListener('click', function(e){
		e.preventDefault();
		console.log(e.target.nodeName);
		if(e.target.nodeName === "INPUT"){
			tableExport('tableExcel', '效益之星报表', e.target.getAttribute('data-type'));
		}	
	 }, false);	
    
    