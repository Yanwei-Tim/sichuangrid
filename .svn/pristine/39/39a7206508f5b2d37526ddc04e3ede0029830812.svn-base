
 $(function(){
	   
	   var maxTitleSize=70;       //标题字数多大倾斜
	   if(screen.width<=1024){    		   
		   maxTitleSize=46;
	   }
 	 
   $.resetHeight();
      
   $("#orgName").text($("#currentOrgId").attr("text"));
   var plateName='';
	$("#orgLevel").click(function(){
	
		$("#globalOrgBox").createDialog({
			url:'/sysadmin/orgManage/orgSelectComponent.action?id='+$.getCurrentOrgId()+"&plateName="+plateName,
			width:550,
			height:'auto',
			title:'请选择',
			buttons: {
				"确定" : function(event){
					var selectInput=$("#orgSelectInput");
					
					$("#currentOrgId").attr({
						"orgLevelInternalId":selectInput.attr("orgLevelInternalId"),
						text:selectInput.attr("text"),
						value:selectInput.val()
					});
					
					$("#orgName").text(selectInput.attr("text"))
					$(this).dialog("close");
					 
					  init_();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		
	});
  
    var dateYear = new Date().getFullYear();
    var dateMonth = new Date().getMonth();        
    for(var i=dateYear;i>(dateYear-3);i--){
	   
	   $('#year').append("<option value='"+i+"' selected>"+i+"</option>");	       
    }
    for(var i=1;i<=dateMonth;i++){
           
	    if(i<10){
	    	$('#month').append("<option value='"+i+"'>0"+i+"</option>");
	    }else{
	    	$('#month').append("<option value='"+i+"'>"+i+"</option>");
	    }     
    }
 
     $('#year').val(dateYear);
     $('#month').val(dateMonth);
     
     $('#year').change(function(){
           $('#month').empty();
	      for(var i=1;i<=12;i++){
	      
            if($('#year').val()==dateYear && i==(dateMonth+1)) return;
                            
		    if(i<10){
		    	$('#month').append("<option value='"+i+"'>0"+i+"</option>");
		    }else{
		    	$('#month').append("<option value='"+i+"'>"+i+"</option>");
		    }     
	     }
    
     });
     
  $(".currentTime").html("（"+$('#year').val()+"年"+$('#month').val()+"月）");
 
  $("#search").click(function(){	 
     $(".currentTime").html("（"+$('#year').val()+"年"+$('#month').val()+"月）");
      init_();
  });
 

  
  var emphasisProOrder_data; //特殊人员省份排序原数据
  var emphasisProOrder_select_data; //特殊人员省份排序显示数据
    $('#orderBtn').click(function(){
    
		var selectedProArr = [];
		$("input[name='checkPro']").each(function(index){
			if(this.checked){
				selectedProArr.push(this.value)
			}
		})
 		  
		
		if(emphasisProOrder_data!=null){ 		
			dataJson=ProOrderbyasc(selectedProArr);
	 		  		 
			var dimensionType=$('#switchDetailedAnalysis_ input[name=peopleType]:checked').val();
				 
			 if(dimensionType=="emphasisProOrder"){//是饼图
			 
				   $('#chartsBarEmphasisProOrder_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
	               
	                getEmphasisOrderPieData(function(emphasisStatusPie) {
	           
		                  function resetMaritalStatusBar(params) {
		                  
		                        postLine("emphasisProOrder",params);
		                  }
		                  
		                  createAnnulusCharts('chartsBarEmphasisProOrder_', emphasisStatusPie, function (param) {
			                    console.log(param);
			                    resetMaritalStatusBar(param.seriesIndex)
			              });
	            
	                  },dataJson);          
			 }else{
			 
			    createBarCharts('chartsBarEmphasisProOrder_',getEmphasisOrderData(true,dataJson)); 			 
			 }

       }
 
     })
  
  //勾选排名
  function selectedPro(dataJson_){
     	            
     $('#migrantsCustomDisplay').empty();
     $('#migrantsCustomDisplay').html("<li>获取全国省份失败!</li>");
    	    
      if(dataJson_==null || dataJson_=="查询失败!") {    	  
    	  return dataJson_;
      }
      
      emphasisProOrder_data=dataJson_;
      emphasisProOrder_select_data=[];
  
      var liStr="";
      for(var i=0;i<dataJson_.length;i++){
    	 
         if(i<10){	         
        	 emphasisProOrder_select_data[i]=dataJson_[i];		         
         }else{
        	 liStr+="<li><label><input type='checkbox' name='checkPro'  value='"+dataJson_[i].dimensionName1+"'>"+dataJson_[i].dimensionName1+"</label></li>";
         }
      }
      $('#migrantsCustomDisplay').html(liStr);
      
         return emphasisProOrder_select_data;
   }
  
    function ProOrderbyasc(selectedProArr){
             
	    var arryIndex=[];
 	    		  		        
	     for(var i=0;i<selectedProArr.length;i++){
	        
            for(var j=0;j<emphasisProOrder_data.length;j++){
                 if( emphasisProOrder_data[j].dimensionName1==selectedProArr[i] ){
                    arryIndex[i]=j;
                 }	            
            }	      
	      }
	     
 	     if( arryIndex.length > 2 ){
 	    	  	    	  	    	  
 	    	$.messageBox({message:"最多显示12个省份",level: "warn"});
 	    	
 			var checkPro=$("input[name='checkPro']");
 	    	checkPro.removeAttr("checked");
 			for(var i=0;i<checkPro.length;i++){
 				for(var j=0;j<emphasisProOrder_select_data.length;j++){
 					if(emphasisProOrder_select_data[j].dimensionName1==checkPro[i].value){
 						 checkPro[i].checked=true;
 					}
 				}
 			} 	    	 
 	    	 return emphasisProOrder_select_data;
 	     }
 	     
 	     arryIndex.sort(function(a,b){return a>b?1:-1});
 	      
 	     emphasisProOrder_select_data=emphasisProOrder_select_data.slice(0,10);
 	  
 	     for(var i=0;i<arryIndex.length;i++){
 	    	 emphasisProOrder_select_data[10+i]=emphasisProOrder_data[arryIndex[i]];	    	  	        
 	     }    
 	     
 	    
           return emphasisProOrder_select_data;
    }
  
    function trimArray10(dataJson_){
    	
    	if(dataJson_!=null && dataJson_!="查询失败!"){
    		var dataJson=[];
    		for(var i=0;i<dataJson_.length;i++){
    			if(i==10) break;
    			dataJson[i]=dataJson_[i];    			
    		}
    		return dataJson;
    	}   	
    	  return dataJson_;	    	 
    }
    
    var trend_dataJson;
    function switchContrastConditions_(value,dataJson){
        
        var xAxis_data=[];
        var firstMonth_data=[]; 
        var secondMonth_data=[];
        var rate_data=[];   
        var titleSize=0;  
        var titleRotate=false;
        
         if(dataJson!=null && dataJson!="查询失败!" ){
        	 
        	 dataJson=generateGrowthRate(dataJson);                    
              for(var i=0;i<dataJson.length;i++){
                 xAxis_data.push(dataJson[i].orgName);
                 firstMonth_data.push(dataJson[i].first_amount);
                 secondMonth_data.push(dataJson[i].second_amount);
                 rate_data.push(dataJson[i].growth_rate);
                 
                 titleSize+=dataJson[i].orgName.length;
                 if((dataJson[i].orgName.length)*(dataJson.length)>maxTitleSize){
                	 titleRotate=true;
                 }
 
              } 
              
         } 
          
         var year=$('#year').val();
         var month=$('#month').val();
        
         var hbyearMonth;         
         if(month==1){
           hbyearMonth=(year-1)+'年12月';
         }else{
           hbyearMonth=year+'年'+(month-1)+'月';
         }
         
         var  tool_data
         if( value === 'true'){
         
            tool_data=[hbyearMonth,year+'年'+month+'月','环比增长率(‰)'];     
         }else{
            tool_data=[(year-1)+'年'+month+'月',year+'年'+month+'月','同比增长率(‰)']; 
         }               
            
        var params = {
            tooltip : {
                trigger: 'axis'
            },
            toolbox: {
                show : true,
                feature : {                    
                    dataView : {show: true, readOnly: false},                  
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : false,
            legend: {},
            xAxis : [
                {
                    type : 'category',                  
                    data : xAxis_data
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    name : '人数',
                    axisLabel : {
                        formatter: '{value} 人'
                    }
                },
                {
                    type : 'value',
                    name : '千分比',
                    axisLabel : {
                        formatter: '{value} ‰'
                    }
                }
            ],
            series : []
        };
         
         if( titleSize > maxTitleSize || titleRotate){
        	params.xAxis[0].axisLabel={'interval':0,'rotate':-15,'margin':0};
         } 
        
        var hbParams = {
            legend: {
                data:tool_data
            },
            series : [
                {
                    name:tool_data[0],
                    type:'bar',
                    barMaxWidth:46,
                    data:firstMonth_data
                },
                {
                    name:tool_data[1],
                    type:'bar',
                    barMaxWidth:46,
                    data:secondMonth_data
                },
                {
                    name:tool_data[2],
                    type:'line',
                    yAxisIndex: 1,
                    data:rate_data
                }
            ]
        };
        
     // callback方法为了获取chart对象，从而绑定click事件
        function callback(chart){
            // 根据点击事件重新捞取对应数据
            function refreshCharts(item){
             
            	var orgId ;             	 
            	if(trend_dataJson != null){
                	for(var i=0; i<trend_dataJson.length; i++){
                		if(trend_dataJson[i].orgName == item.name){
                			orgId= trend_dataJson[i].orgId
                		}
                	}
            	}            	 
            	if(orgId!=null){
            		var value = $('#switchContrastConditions_ input[name=dataComparison]:checked').val(); 
                	postGrid(value,orgId);  
                	changeDescription();
                    $('#goBack').show(200)
            	}                
            }
            var ecConfig = require('echarts/config');
            chart.on(ecConfig.EVENT.CLICK, refreshCharts);
        }
        
        var settings = $.extend({},params,hbParams);
               
        $('#populationTrendAnalysis_').buildChart(settings,callback);
       
        $('#goBack').on('click',function(){
        	var value = $('#switchContrastConditions_ input[name=dataComparison]:checked').val(); 
        	postGrid(value,$("#currentOrgId").val());
            $('#goBack').hide(200)
        })
        
    }

    /*
    *   grid 初始化
    * */

    // 初始化化build参数
    function switchGrid_(value,dataJson){
    
        function buildGrid(data,columns){
            var source ={
                datatype: "json",
                datafields: [
                    { name: 'orgName', type: 'string' },
                    { name: 'first_amount', type: 'string' },
                    { name: 'second_amount', type: 'string' },
                    { name: 'growth_rate', type: 'string' }                    
                ],
                localdata: data
            };
            var dataAdapter = new $.jqx.dataAdapter(source);

            $("#gridPopulationTrendAnalysis_").jqxGrid({
                width: 550,
                autoHeight: true,
                source: dataAdapter,
                columnsresize: true,
                columns:columns
            });

        }
        
	         var year=$('#year').val();
	         var month=$('#month').val();
	        
	         var hbyearMonth;
	         if(month==1){
	           hbyearMonth=(year-1)+'年12月';
	         }else{
	           hbyearMonth=year+'年'+(month-1)+'月';
	         }
             
            var baseColumns =[
                { text: '区域\\时间', datafield: 'orgName', width: 120 },
                { text: year+'年'+month+'月', datafield: 'first_amount', width: 100 },
                { text: year+'年'+month+'月', datafield: 'second_amount', width: 100 },
                { text: '环比增长率(‰)', datafield: 'growth_rate', minWidth: 80 }
            ];
            
            if(value === 'true'){
               baseColumns[1].text=hbyearMonth;
               baseColumns[3].text='环比增长率(‰)';
            }else{
               baseColumns[1].text=(year-1)+'年'+month+'月'
               baseColumns[3].text='同比增长率(‰)';
            }
                 
           var data = [];

           if(dataJson!=null && dataJson!="查询失败!"){
              data=generateGrowthRate(dataJson);
           }
                     
           var sumFirstAmount=0;
           var sumSecondAmount=0;
           var sumGrowthRate=0;
           for(var i=0;i<data.length;i++){
        	   if(data[i].first_amount!=null && !isNaN(data[i].first_amount)){
        		   sumFirstAmount+=data[i].first_amount;        		  
        	   }
        	   if(data[i].second_amount!=null && !isNaN(data[i].second_amount) ){
        		   sumSecondAmount+=data[i].second_amount;
        	   }
        	   if(data[i].growth_rate!=null && !isNaN(data[i].growth_rate)){         		           		   
        		   sumGrowthRate+=data[i].growth_rate;
        		   data[i].growth_rate=data[i].growth_rate+'‰';
        	   }

           }
           sumGrowthRate=parseFloat(parseFloat(sumGrowthRate).toFixed(2))+'‰';
           data.push({'orgName':'合计','first_amount':sumFirstAmount,'second_amount':sumSecondAmount,'growth_rate':sumGrowthRate});
        
        buildGrid(data,baseColumns)
    }
 
   
     // 生成柱状图公共方法
    function createBarCharts(id,params){
            var settings = {
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: true, readOnly: false},                       
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [{
                    type : 'category',                     
                    data : []
                }],
                yAxis : [{
                    type : 'value',
                    name : '',
                    axisLabel : {
                        formatter: '{value}'
                    }
                }]
            };
            $.extend(true,settings,params);
            $('#'+id).buildChart(settings);
        }

    // 生成饼状图公共方法
   function createAnnulusCharts(id,params,callback) {
 	   
          var  settings = {
            	    title : {
            	        text: '',
            	        subtext: '',
            	        x:'center'
            	    },
            	    tooltip : {
            	        trigger: 'item',
            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
            	    },
            	    legend: {
            	        x : 'center',
            	        y : 'bottom',
            	        data:[]
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {            	            
            	            dataView : {show: true, readOnly: false},           	            
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    calculable : false,
            	    series : [           	        
            	        {
            	            name:'面积模式',
            	            type:'pie',
            	            radius : [30, 110],
            	            center : ['50%', '50%'],
            	            roseType : 'area',
            	            x: '50%',                
            	            max: 40,               
            	            sort : 'ascending',      
            	            data:[]
            	        }
            	    ]
            	};
            
            $.extend(true,settings,params);
            $('#'+id).buildChart(settings,callback);
        }
  

   // 请求性别柱状图数据
   function getGenderData(param,dataJson_){
         
          var xAxis_data=[];
          var series_data=[];
          
          if(dataJson_!=null && dataJson_!="查询失败!"){
                xAxis_data=[];
                series_data=[];
              for(var i=0;i<dataJson_.length;i++){
		           xAxis_data[i]=dataJson_[i].dimensionName1;
		           series_data[i]=dataJson_[i].amount;		         		         
		         }		          
          }
   
            var setting = {
                legend: {
                    data:['性别']
                },
                xAxis : [{
                    type : 'category',
                    data : xAxis_data 
                }],
                series : [{
                    name:'性别',
                    type:'bar',
                    barMaxWidth:16,
                    data: series_data
                }]
            }
            if(param){
                // 根据参数捞取数据
                //var setting ={}
            }else{
                // 默认捞取数据
                //var setting ={}
            }
            return setting;
    }
  
   
   // 捞取性别饼状图数据
   function getGenderPieData(callback,dataJson){
	   
            var tool_data=[];
	        var series_data=[];
 	                     
	            if(dataJson!=null && dataJson!="查询失败!" ){ 
 	            
	             for(var i=dataJson.length-1;i>=0;i--){
	        
			         var objData= {};
			         
			             tool_data[i]=dataJson[i].dimensionName1;	
			             
			             objData.name=dataJson[i].dimensionName1;		             
			             objData.value=dataJson[i].amount;
 			              
			             series_data.push(objData);
			        }	                 	                     
	            }         
 		 	       	                           
            var genderPie = {               
                legend: {      
		            	orient: 'horizontal',
		           	         x:"center",
		           	         y:"bottom",                
                          data: tool_data
                },
                series:[{name:'性别',
            	         radius : ['10%', '60%'],
   	                     center : ['50%', '42%'],
   	                     data:series_data }]
            };
            
            callback(genderPie)
                         
    }

    // 请求年龄柱状图数据
   function getAgeData(param,dataJson_){
        
          var xAxis_data=[];
          var series_data=[];
 					     	                
            if(dataJson_!=null && dataJson_!="查询失败!" ){
	             xAxis_data=[];
		         series_data=[];
		         for(var i=0;i<dataJson_.length;i++){
		           xAxis_data[i]=dataJson_[i].dimensionName1;
		           series_data[i]=dataJson_[i].amount;		         		         
		         }
            }		  
	            var setting = {
	                legend: {
	                    data:['年龄数据']
	                },
	                xAxis : [{
	                    type : 'category',
	                    axisLabel:{'interval':0,'rotate':-45,'margin':0}, 
	                    data :xAxis_data 
	                }
	                ],
	                series : [{
	                    name:'年龄数据',
	                    type:'bar',
	                    barMaxWidth:16,
	                    data:series_data
	                }
	                ]
	            }

	            if(param){
	                // 根据参数捞取数据
	                //var setting ={}
	            }else{
	                // 默认捞取数据
	                //var setting ={}
	            }	            
	            return setting;                 
    }
                
   function getAgePieData(callback,dataJson){
        
          var tool_data=[];
	      var series_data=[];
 	        
	        if(dataJson!=null && dataJson!="查询失败!" ){
 	             
	             for(var i=dataJson.length-1;i>=0;i--){
	        
			         var objData={};
			         
			             tool_data[i]=dataJson[i].dimensionName1;
			             
			             objData.name=dataJson[i].dimensionName1;		             
			             objData.value=dataJson[i].amount;
			             
			             series_data.push(objData);
				 }
	        }
            
            var agePie = {
                    legend: {      
		            	 orient: 'horizontal',
		      	              x:"center",
		      	              y:"bottom",                   
                           data: tool_data
                    },
                    series:[{ name:'年龄',
	           	              radius : ['10%', '60%'],
		                      center : ['50%', '42%'],
		                      data:series_data }]
                };
            
            callback(agePie)
     }
   var textStyle= {fontSize: 13,
		           fontStyle: 'normal',		          
		          }
    // 请求文化程度柱状图数据
    function getDiplomaData(param,dataJson_){
        
          var xAxis_data=[];
          var series_data=[];
          
          if(dataJson_!=null && dataJson_!="查询失败!"){
                  xAxis_data=[];
	              series_data=[];
               for(var i=0;i<dataJson_.length;i++){	                 
		           xAxis_data[i]={value:dataJson_[i].dimensionName1 ,textStyle:textStyle};
		           series_data[i]=dataJson_[i].amount;		         		         
		        }	
          }
         
            var setting = {
                legend: {
                    data:['文化程度']
                },
                xAxis : [{
                    type : 'category',
                    axisLabel:{'interval':0,'rotate':-45,'margin':0},                    
                    data : xAxis_data
                }],
                series : [{
                    name:'文化程度',
                    type:'bar',                    
                    barMaxWidth:16,
                    data: series_data
                }]
            }

            if(param){
                // 根据参数捞取数据
                //var setting ={}
            }else{
                // 默认捞取数据
                //var setting ={}
            }
            return setting;
    }
        
        
   function getDiplomaPieData(callback,dataJson){
        
          var tool_data=[];
	      var series_data=[];
 	                     	        
	        if(dataJson!=null && dataJson!="查询失败!" ){
 	        
	             for(var i=dataJson.length-1;i>=0;i--){
		        
				         var objData= {};
				         
				             tool_data[i]=dataJson[i].dimensionName1;	
				             
				             objData.name=dataJson[i].dimensionName1;		             
				             objData.value=dataJson[i].amount;
				           
				             series_data.push(objData);
				  }
	        }
            
            var diplomaPie = {
                    legend: {      
            	      orient: 'horizontal',
	                       x:"center",
	                       y:"bottom",                                                 
                        data: tool_data
                    },
                    series:[{ name:'文化程度',
	         	              radius : ['10%', '60%'],
		                      center : ['50%', '42%'],
		                      data:series_data }]
                };
            
            callback(diplomaPie)
        }
   
    // 请求婚姻状况柱状图数据
   function getMarriageData(param,dataJson_){
        
          var xAxis_data=[];
          var series_data=[];
          
          if(dataJson_!=null && dataJson_!="查询失败!" ){
                xAxis_data=[];
                series_data=[];
             for(var i=0;i<dataJson_.length;i++){
		           xAxis_data[i]=dataJson_[i].dimensionName1;
		           series_data[i]=dataJson_[i].amount;		         		         
		      }
          }
           
            var setting = {
                legend: {
                    data:['婚姻状况']
                },
                xAxis : [{
                    type : 'category',
                    data : xAxis_data
                }],
                series : [{
                    name:'婚姻状况',
                    type:'bar',
                    barMaxWidth:16,
                    data:series_data
                }]
            }

            if(param){
                // 根据参数捞取数据
                //var setting ={}
            }else{
                // 默认捞取数据
                //var setting ={}
            }
            return setting;
   }
        
        
   function getMarriagePieData(callback,dataJson){
        
          var tool_data=[];
	      var series_data=[];
	   
         if(dataJson!=null && dataJson!="查询失败!" ){
 	             
             for(var i=0;i<dataJson.length;i++){
        
		         var objData={};
		         
		             tool_data[i]=dataJson[i].dimensionName1;	
		             
		             objData.name=dataJson[i].dimensionName1;		             
		             objData.value=dataJson[i].amount;
 
		             series_data.push(objData);
		        }        
         }
             
            var maritalStatusPie = {
                    legend: { orient: 'horizontal',
			                       x:"center",
			                       y:"bottom",                  
                              data: tool_data
                    },
                    series:[{ name:'婚姻状况',
		       	              radius : ['10%', '60%'],
		                      center : ['50%', '42%'],
		                      data:series_data }]
             };
          
            callback(maritalStatusPie)
    }

  
   function getEmphasisPieData(callback,dataJson){
        
          var tool_data=[];
	      var series_data=[];
         
         if(dataJson!=null && dataJson!="查询失败!" ){
 	             
             for(var i=dataJson.length-1;i>=0;i--){
        
		         var objCopy= {};
		         
		             tool_data[i]=dataJson[i].dimensionName1;	
		             
		             objCopy.name=dataJson[i].dimensionName1;		             
		             objCopy.value=dataJson[i].amount;
		            		            
		             series_data.push(objCopy);
		        }        
         }
          
            var emphasisStatusPie = {
                    
                    legend: {      
                    	 orient: 'horizontal',
                    	 x:"center",
                    	 y:"bottom",
                                   
                       data: tool_data
                    },
                    series:[{ name:'特殊人群类型',
		              	      radius : ['10%', '40%'],
		   	                  center : ['50%', '42%'],
		   	                  data:series_data }]
             };
          
            callback(emphasisStatusPie)
    }


   function getEmphasisData(param,dataJson_){
        
          var xAxis_data=[];
          var series_data=[];
          
          if(dataJson_!=null && dataJson_!="查询失败!"){
                xAxis_data=[];
                series_data=[];
             for(var i=0;i<dataJson_.length;i++){
		           xAxis_data[i]=dataJson_[i].dimensionName1;
		           series_data[i]=dataJson_[i].amount;		         		         
		      }
          }
           
            var setting = {
                legend: {
                    data:['特殊人员类型']
                },
                xAxis : [{
                    type : 'category',
                    axisLabel:{'interval':0,'rotate':-45,'margin':0},
                    data : xAxis_data
                }],
                series : [{
                    name:'特殊人员类型',
                    type:'bar',
                    barMaxWidth:16,
                    data:series_data
                }]
            }

            if(param){
                // 根据参数捞取数据
                //var setting ={}
            }else{
                // 默认捞取数据
                //var setting ={}
            }
            return setting;
    }

    function getEmphasisOrderPieData(callback,dataJson){
        
          var tool_data=[];
	      var series_data=[];
         
         if(dataJson!=null && dataJson!="查询失败!"){
 	             
             for(var i=dataJson.length-1;i>=0;i--){
        
		         var objData= {};
		         
		             tool_data[i]=dataJson[i].dimensionName1;
		             
		             objData.name=dataJson[i].dimensionName1;		             
		             objData.value=dataJson[i].amount;
		             
		             series_data.push(objData);
		     }        
          }
            
            var emphasisOrderPie = {
                    legend: {      
			            	orient: 'horizontal',
				                 x:"center",
				                 y:"bottom",                
                              data: tool_data
                    },                   
                    series:[{  name:'排序情况',
	              	        radius : ['10%', '60%'],
	   	                    center : ['50%', '42%'],
                    	       data: series_data }]
             };
          
            callback(emphasisOrderPie)
    }
 
   function getEmphasisOrderData(param,dataJson_){
        
          var xAxis_data=[];
          var series_data=[];
          
          if(dataJson_!=null && dataJson_!="查询失败!" ){
                xAxis_data=[];
                series_data=[];
             for(var i=0;i<dataJson_.length;i++){
		           xAxis_data[i]=dataJson_[i].dimensionName1;
		           series_data[i]=dataJson_[i].amount;		         		         
		      }
          }
           
            var setting = {
                legend: {
                    data:['排名情况']                    
                },
                xAxis : [{
                    type : 'category',
                    axisLabel:{'interval':0,'rotate':-45,'margin':0},
                    data : xAxis_data
                }],
                series : [{
                    name:'排名情况',
                    type:'bar',
                    barMaxWidth:16,
                    data:series_data
                }]
            }

            if(param){
                // 根据参数捞取数据
                //var setting ={}
            }else{
                // 默认捞取数据
                //var setting ={}
            }
            return setting;
   }
       

  function settingAnalysisConditions_(value){
        /*
         *   性别数据
         * */
        if( value === 'sex'){
            $('#chartsBarGender_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
          
	          var year=$('#year').val();
	          var month=$('#month').val();
	          var orgId=$("#currentOrgId").val();
              var postParams={"orgId":orgId,"dimension1":"sex","modelKeyName":"重点人员","year":year,"month":month};         		    
	        
	         $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action',postParams,function(dataJson){   

                getGenderPieData(function(pieData){
	                function resetChartBar(params){	               	 
	                 	 postLine('sex',params);
	                }
	                createAnnulusCharts('chartsBarGender_',pieData,function(chart){
	                    var ecConfig = require('echarts/config');
	                    chart.on(ecConfig.EVENT.CLICK, resetChartBar);
	                });
	                resetChartBar()
	            },dataJson)
            
             });
        }
        /*
         *   年龄比例
         * */
        if( value === 'age'){
            $('#chartsBarAge_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
            
             var year=$('#year').val();
	         var month=$('#month').val();
	         var orgId=$("#currentOrgId").val();
             var postParams={"orgId":orgId,"dimension1":"age","modelKeyName":"重点人员","year":year,"month":month};         		    
	        
	         $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action',postParams,function(dataJson){  
	            
                getAgePieData(function(pieData){
	                function resetChartBar(params){
	                 	 postLine('age',params);
	                }
	                createAnnulusCharts('chartsBarAge_',pieData,function(chart){
	                    var ecConfig = require('echarts/config');
	                    chart.on(ecConfig.EVENT.CLICK, resetChartBar);
	                });
	                resetChartBar()
	            },dataJson)

              });
        }
        /*
         *   文化程度
         * */
        if( value === 'edu'){
            $('#chartsBarDiploma_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
            
             var year=$('#year').val();
	         var month=$('#month').val();
	         var orgId=$("#currentOrgId").val();
             var postParams={"orgId":orgId,"dimension1":"edu","modelKeyName":"重点人员","year":year,"month":month};         		    
	        
	         $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action',
	                 postParams,function(dataJson){  
	                 	                
		                getDiplomaPieData(function(diplomaPie) {
		            
		                function resetChartBar(params) {
		                
		                   postLine('edu',params);			                                    		                    
		                }
		
		                createAnnulusCharts('chartsBarDiploma_',diplomaPie,function(chart){
		                    var ecConfig = require('echarts/config');
		                    chart.on(ecConfig.EVENT.CLICK, resetChartBar);
		                });
		                
		                resetChartBar()
		                
		            },dataJson)
		            
		    });         
        }
        /*
        *   婚姻状况
        * */
        if( value === 'mar'){
        
            $('#chartsBarMarriage_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
            
            var year=$('#year').val();
	        var month=$('#month').val();
	        var orgId=$("#currentOrgId").val();
           
            var postParams={"orgId":orgId,"dimension1":"mar","modelKeyName":"重点人员","year":year,"month":month};         		    
	         $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action',
	                 postParams,function(dataJson){
	              	              
	                getMarriagePieData(function(maritalStatusPie) {
	            
	                function resetChartBar(params) {
	                
	                       postLine('mar',params);	                                
	                }
	
	                createAnnulusCharts('chartsBarMarriage_',maritalStatusPie,function(chart){
	                    var ecConfig = require('echarts/config');
	                    chart.on(ecConfig.EVENT.CLICK, resetChartBar);
	                });
 	                
	                resetChartBar();
	                
	            },dataJson);
	            
	      });      
        }
        
        /*
        *   重点人员类型
        * */
        if(value==="emphasis"){
        
           $('#chartsBarEmphasis_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
           
            var year=$('#year').val();
	        var month=$('#month').val();
	        var orgId=$("#currentOrgId").val();
            var postParams={"orgId":orgId,"dimension1":"emphasis","modelKeyName":"重点人员","year":year,"month":month};         		    
	        
	         $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action',
	                 postParams,function(dataJson){
	                 	             
                getEmphasisPieData(function(emphasisStatusPie) {
           
                  function resetChartBar(params) {
 
	                     postLine('emphasis',params);		                    
                  }
                  
                  createAnnulusCharts('chartsBarEmphasis_',emphasisStatusPie,function(chart){
	                    var ecConfig = require('echarts/config');
	                    chart.on(ecConfig.EVENT.CLICK, resetChartBar);
	              });
  	                
                  resetChartBar();
           
           },dataJson);
               
          });
       }
        
       /*
        *   重点人员省级排名类型
        * */
     if(value==="emphasisProOrder"){
        
           $('#chartsBarEmphasisProOrder_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
           
            var year=$('#year').val();
	        var month=$('#month').val();
	        var orgId=$("#currentOrgId").val();
            var postParams={"orgId":orgId,"dimension1":"pro","modelKeyName":"重点人员","year":year,"month":month};         		    
	       
	         $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action',
	                 postParams,function(dataJson){
	            
                  getEmphasisOrderPieData(function(emphasisOrderPie) {
           
                  function resetChartBar(params) {
                  
                        postLine("pro",params);
                  }
                  
                  createAnnulusCharts('chartsBarEmphasisProOrder_',emphasisOrderPie,function(chart){
	                    var ecConfig = require('echarts/config');
	                    chart.on(ecConfig.EVENT.CLICK, resetChartBar);
	              });
 	                
                  resetChartBar();
           
           },selectedPro(dataJson));
               
          });
       }
    
      /*
        *   重点人员市级排名类型
        * */
        
        if(value==="emphasisOrder"){
        
           $('#chartsBarEmphasisOrder_').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
           
            var year=$('#year').val();
	        var month=$('#month').val();
	        var orgId=$("#currentOrgId").val();
            var postParams={"orgId":orgId,"dimension1":"cit","modelKeyName":"重点人员","year":year,"month":month};         		    
	       
	         $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action',
	                 postParams,function(dataJson){
	        	 
	        	    dataJson=trimArray10(dataJson); //只要前10个    	              
 	                 
                  getEmphasisOrderPieData(function(emphasisStatusPie) {
           
                  function resetChartBar(params) {
                  
                        postLine("cit",params);
                  }
                  
                  createAnnulusCharts('chartsBarEmphasisOrder_',emphasisStatusPie,function(chart){
	                    var ecConfig = require('echarts/config');
	                   // chart.on(ecConfig.EVENT.CLICK, resetChartBar);
	              });
  	                
                  resetChartBar();
           
           },dataJson);
               
          });
       }
           
   }
            
    function postLine(dimension2,params){
    	
     if(params!=null && params.name=='invisible') return;
    	
      var year=$('#year').val();
      var month=$('#month').val();
      var orgId=$("#currentOrgId").val();  

       var postParams={"orgId":orgId,"modelKeyName":"重点人员","year":year,"month":month,
                       "dimension2":dimension2                                               
                       };	     
      
               
       var postURL='/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisSingleDimension.action';	                   
  
        if( (dimension2!="emphasisOrder" && dimension2!="emphasisProOrder") && params!=null ){
          postParams.dimensionName1=params.name;
          postURL='/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisMoreDimension.action';	                                 
        }

         if(dimension2!="age"){   
             postParams.dimension1="age";                  
             postParams.dimensionKeyName="age"; 	                                                	    
            $.getJSON(postURL,postParams,function(dataJson_){   
               	                                                                         	                    
                createBarCharts('chartsBarAge_',getAgeData(true,dataJson_));  	                    
            });
          } 
          
         if(dimension2!="sex"){  
             postParams.dimension1="sex";                  
             postParams.dimensionKeyName="sex"; 
            $.getJSON(postURL,postParams,function(dataJson_){  
                                                    	                    		                     
                createBarCharts('chartsBarGender_',getGenderData(true,dataJson_));   	                    
            });
         }
         
         if(dimension2!="edu"){
             postParams.dimension1="edu";                  
             postParams.dimensionKeyName="edu"; 	                                                	    
           $.getJSON(postURL,postParams,function(dataJson_){
                             	                    	                         
                 createBarCharts('chartsBarDiploma_',getDiplomaData(true,dataJson_)); 	                    
            });         
     	 }  
     	 
     	 if(dimension2!="mar"){                               	                   
             postParams.dimension1="mar";                  
             postParams.dimensionKeyName="mar"; 	                                                	    
           $.getJSON(postURL,postParams,function(dataJson_){ 
                	                    	                         	                   
                  createBarCharts('chartsBarMarriage_',getMarriageData(true,dataJson_)); 	                    
            }); 
         }  
         
         if(dimension2!="emphasis"){
             postParams.dimension1="emphasis";                  
             postParams.dimensionKeyName="emphasis"; 	                                                	    
           $.getJSON(postURL,postParams,function(dataJson_){ 
                	                    	                         	                   
                  createBarCharts('chartsBarEmphasis_',getEmphasisData(true,dataJson_)); 	                    
            });     
         }
         
         if(dimension2!="cit"){
             postParams.dimension1="cit";                  
             postParams.dimensionKeyName="cit"; 
                                         	    
           $.getJSON(postURL,postParams,function(dataJson_){  
        	      dataJson_=trimArray10(dataJson_); //只要前10个    	               	                         	                   
                  createBarCharts('chartsBarEmphasisOrder_',getEmphasisOrderData(true,dataJson_)); 	                    
            }); 
             
         }
         
         if(dimension2!="pro"){
             postParams.dimension1="pro";                  
             postParams.dimensionKeyName="pro"; 
              	                                                	    
           $.getJSON(postURL,postParams,function(dataJson_){  
             	                	                         	                   
                  createBarCharts('chartsBarEmphasisProOrder_',getEmphasisOrderData(true,selectedPro(dataJson_))); 	                    
            });
              
         }
   
     }
 

/*服务落实情况图*/
    function showServiceLine(dataJson){
        
        var xAxis_data=[];
        var series_isHelp=[];
        var series_noHelp=[];
        var series_isDone=[];
        var series_noDone=[];
        var series_helpRate=[];
        var series_doneRate=[];
        var titleSize=0;     
        var titleRotate=false;  
      
        if(dataJson!=null && dataJson!="查询失败!"){
        
              for(var i=0;i<dataJson.length;i++){
            	  
                xAxis_data[i]=dataJson[i].orgName;
                series_isHelp[i]=dataJson[i].isHelp_amount;
                series_noHelp[i]=dataJson[i].noHelp_amount;
                series_isDone[i]=dataJson[i].isDone_amount;
                series_noDone[i]=dataJson[i].noDone_amount;
                series_helpRate[i]=dataJson[i].help_rate;
                series_doneRate[i]=dataJson[i].done_rate;
                
                titleSize+=dataJson[i].orgName.length; 
                if( (dataJson[i].orgName.length)*(dataJson.length)>maxTitleSize ){
                	 
                	titleRotate=true;
                }
              }
        
        }
        
          option5 = {
                    tooltip : {
                        show: true,
                        trigger: 'axis'
                    },
                    legend: {                   	 
                        data:['已走访','未走访','走访率']
                    },
                    toolbox: {
                        show : false
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',                           
                            data : xAxis_data
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            name : '人数',
                            axisLabel : {
                                formatter: '{value}'
                            }
                        },
                        {
                            type : 'value',
                            name : '千分比',
                            axisLabel : {
                                formatter: '{value} ‰'
                            }
                        }
                    ],
                    series : [
                        {
                            name:'已走访',
                            type:'bar',
                            barMaxWidth:16,
                            stack: '走访',
                            itemStyle: {
                                normal:{		                        	 
		                            color : '#00C5CD',
		                   barBorderRadius:[0, 0, 0, 0],
                                    label : {
                                        show : false,
                                        textStyle : {
                                            fontSize : '12',
                                            fontFamily : '微软雅黑',
                                            fontWeight : 'bold'
                                        }
                                    }
                                }
                            },
                            data: series_isHelp
                        },
                        {
                            name:'未走访',
                            type:'bar',
                            barMaxWidth:16,
                            stack: '走访',
                            itemStyle: {
                                normal: {
                           barBorderRadius:[0, 0, 0, 0],
                        	        color : '#97DDFF', 
                                    label : {
                                        show : false,
                                        textStyle : {
                                            fontSize : '12',
                                            fontFamily : '微软雅黑',
                                            fontWeight : 'bold'
                                        }
                                    }
                                }
                            },
                            data: series_noHelp
                        },{
                            name:'走访率',
                            type:'line',
                            yAxisIndex: 1,
                            itemStyle: {
                                normal: {
                        	        color : '#00C5CD',
                                    label : {
                                        show : false,
                                        textStyle : {
                                            fontSize : '12',
                                            fontFamily : '微软雅黑',
                                            fontWeight : 'bold'
                                        }
                                    }
                                }
                            },
                            data: series_helpRate
                        }                                               
                    ]
                };

                   
           if( titleSize>maxTitleSize || titleRotate){
            	option5.xAxis[0].axisLabel={'interval':0,'rotate':-15,'margin':0};
           } 
          
          $('#serviceImplementation').buildChart(option5);
    
    }





    // 各种初始化加载方法
    function init_(){
         
    	  changeDescription();
          var value = $('#switchContrastConditions_ input[name=dataComparison]:checked').val();       
          postGrid(value,$("#currentOrgId").val()); // 特殊人员趋势分析                 
       
        $('#switchContrastConditions_').on('change','input',function(){        
                postGrid(this.value,$("#currentOrgId").val());   
        });
     
          var postParams={"parentOrgId":$("#currentOrgId").val(),"year":$("#year").val(),"month":$("#month").val()};
        $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisIsHelpHistorySummary.action',
                postParams,function(dataJson){
             
                 showServiceLine(generateHelpAndDoneRate(dataJson)); //服务落实情况图                 
          });
          
         // 特殊人员细化分析
         settingAnalysisConditions_( $('#switchDetailedAnalysis_ input[name=peopleType]:checked').val() )

        $('#switchDetailedAnalysis_').on('change','input',function(){            
            settingAnalysisConditions_(this.value)
        });
       
    }
    
    if( $('#switchContrastConditions_')[0] && $('#switchDetailedAnalysis_')[0] ){    
         init_()
    }


   function postGrid(value,orgId){
  
        var postParams={"compared":value,"parentOrgId":orgId,"year":$("#year").val(),"month":$("#month").val()};
        $.getJSON('/judgmentAnalysis/BusinessModelRealDataManage/findEmphasisHistorySummary.action',
                postParams,function(dataJson){ 
        	       trend_dataJson=dataJson;                   
                   switchContrastConditions_(value,dataJson) // 切换charts
	 	           switchGrid_(value,dataJson)   // 切换grid
                                                   	 	        
           });
          
    }

              
   function changeDescription(){
		$.ajax({
			url:'/judgmentAnalysis/businessDescriptionManage/getDescriptionForStatistics.action?orgId='+$.getCurrentOrgId()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&keyName=special',
			async:false,
			success:function(data){
				
				$(".mContIntro").text(data);
			}
		});
	}
   
   function generateGrowthRate(dataJson){
	  
	   for(var i=0;i<dataJson.length;i++){
		   
		   if(dataJson[i].second_amount==0){
			   dataJson[i].growth_rate=0;
		   }else if(dataJson[i].first_amount!=0){
			   var decimal =(dataJson[i].second_amount-dataJson[i].first_amount)/dataJson[i].first_amount;
			  
				 dataJson[i].growth_rate=dealDecimal_tow(decimal);			  			   		 
		   }
	   }
	   return dataJson;
   }
   
  function generateHelpAndDoneRate(dataJson){
	   
	   for(var i=0;i<dataJson.length;i++){
		   
		   if(dataJson[i].amount!=0 && dataJson[i].isHelp_amount!=0){
			   var decimal =(dataJson[i].isHelp_amount)/dataJson[i].amount;
			       dataJson[i].help_rate=dealDecimal_tow(decimal);		 
		   } 
		   if(dataJson[i].amount!=0 && dataJson[i].isDone_amount!=0){
			   var decimal =(dataJson[i].isDone_amount)/dataJson[i].amount;
			       dataJson[i].done_rate=dealDecimal_tow(decimal);		 
		   } 
	   }
	   return dataJson;
   }
   
   function dealDecimal(decimal){
	   decimal=decimal*1000;
  	 if(decimal!=null && decimal!=0){
  		if(decimal<0.01){
 			  return "<0.01‰";
 		 }
  	   return 	parseFloat(parseFloat(decimal).toFixed(2))+"‰";
	 }
   	 return '0‰';
   }

   function dealDecimal_tow(decimal){
	   decimal=decimal*1000;
	  	 if(decimal!=null && decimal!=0){
	  		if(decimal<0.01){
	 			  return 0.01;
	 		 }
	  	   return 	parseFloat(parseFloat(decimal).toFixed(2));
		 }
	   	 return 0;
   }

   
   
   
});


 