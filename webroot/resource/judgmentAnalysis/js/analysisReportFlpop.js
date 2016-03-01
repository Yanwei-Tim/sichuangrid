$(function(){
	//省份自定义显示个数 默认10个
	var customSum = 10;
	var value = $('#switchContrastConditions input[name=dataComparison]:checked').val();
	$("#orgName").text($.getCurrentOrgName());
	var plateName='';
	var paramsTemp = {};
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
					refreshData();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		
	});
	
	var orgId = ''
	var dataInfo = {};
	 var orgNameTemp,orgIdTemp,hbyearMonth,tool_data,orgNameAfter,orgNameAfterValue ;
	 var arrYear = [];
	 var arrMonth = [];
	 $.ajax({
			async: false,
			url: PATH+"/stat/currentTime/getCurrentTimeForYear.action",
			success:function(responseData){
				arrYear = [];
				for(var i = 0;i<responseData.length;i++){
					arrYear.push("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>") 
				}
				$("#year").append(arrYear); 
				getmonth();
				$(".currentTime").html("（"+$('#year').val()+"年"+$('#month').val()+"月）");
				if( $('#switchContrastConditions')[0] && $('#switchDetailedAnalysis')[0] ){
			        init();
			    }
			}
		});
	// 各种初始化加载方法
	    function init(){
	    	var value = $('#switchContrastConditions input[name=dataComparison]:checked').val();
	    	changeDescription();
	    	
	        // 户籍人口趋势分析
	        switchContrastConditions(value); // 切换charts
	        //switchGrid(value);   // 切换grid
	        $('#switchContrastConditions').on('change','input',function(){
	            switchContrastConditions(this.value);
	            //switchGrid(this.value);
	        });

	        // 户籍人口细化分析
	        settingAnalysisConditions( $('#switchDetailedAnalysis input[name=peopleType]:checked').val() );

	        $('#switchDetailedAnalysis').on('change','input',function(){
	            settingAnalysisConditions(this.value);
	        });
	        
	    }
		function getmonth(){
			$.ajax({
				async: false,
				url: PATH+"/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#year").val(),
				success:function(responseData){
					arrMonth = [];
					for(var i = 0;i<responseData.length;i++){
						arrMonth.push("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
					}
					$("#month").append(arrMonth);
				}
			});
		}
		
		$("#year").change(function(){
			$("#month").empty();
			getmonth();
		});
		$("#search").click(function(){
			changeDescription();
			$(".currentTime").html("（"+$('#year').val()+"年"+$('#month').val()+"月）");
			refreshData();
		});
		//刷新页面 重新加载数据
		function refreshData(){
			$('#migrantsCustomDisplay').html('');
			changeDescription();
			switchContrastConditions(value) ;
			//switchGrid(value);
			settingAnalysisConditions( $('#switchDetailedAnalysis input[name=peopleType]:checked').val() )		
		}
		
		function getData(orgId){
			if(!orgId){
	    		var orgId = $("#currentOrgId").val()
	    	}
			$.ajax({
				url:PATH+'/judgmentAnalysis/floatingPopulation/getArrayHistoryCycles.action',
				type:'POST',
				data: {"orgId":orgId,"year":$("#year").val(),"month":$("#month").val()},
				async:true,
				success:function(data){
					if(data){
						dataInfo = data;
						orgNameTemp = dataInfo.orgName;
						orgIdTemp = dataInfo.orgId;
						var valueTemp = $('#switchContrastConditions input[name=dataComparison]:checked').val();
						switchContrastConditionsShow(valueTemp);
						switchGridShow(valueTemp);
						changeDescription();
					}
					
				}
			})
		}
		
	function switchContrastConditionsShow(value){
		var year=$('#year').val();
        var month=$('#month').val();
               
        if(month==1){
          hbyearMonth=(year-1)+'年12月';
        }else{
          hbyearMonth=year+'年'+(month-1)+'月';
        }
        if( value === 'hb'){
           tool_data=[hbyearMonth,year+'年'+month+'月','环比增长率%'];     
        }else{
           tool_data=[(year-1)+'年'+month+'月',year+'年'+month+'月','同比增长率%']; 
        } 
     /*   var maxSize = 65;
        if(screen.width <= 1024){
        	maxSize = 46;
        }*/
        var xAxis = [{type : 'category',data : dataInfo.orgName == null ? [''] : dataInfo.orgName } ];
       /* var orgNameCount = "";
        var nameStr = dataInfo.orgName;
        if(nameStr != null){
        	for(var i = 0 ; i < nameStr.length ; i ++){
        		orgNameCount += nameStr[i];
        	}
        	if(orgNameCount.length > maxSize) {
        		xAxis = [{type : 'category',axisLabel:{'interval':0,'rotate':-15,'margin':0},data : dataInfo.orgName == null ? [''] : dataInfo.orgName } ];
        	}
        }*/
        var params = {
            tooltip : {
                trigger: 'axis'
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: false},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : false,
            legend: {},
            dataZoom: {
                show: true,
                start : 0,
                end : 30
            },
            xAxis : xAxis,
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
                    name : '百分比',
                    axisLabel : {
                        formatter: '{value} %'
                    }
                }
            ],
            series : []
        };
        var chartData1 = getChartsData(value);
        var settings = $.extend({},params,chartData1);
        $('#populationTrendAnalysis').buildChart(settings,callback);
	}
    /*
    *   图表模式初始化
    * */
	
    function switchContrastConditions(value,orgId){
    	if(!orgId){
    		var orgId = $("#currentOrgId").val()
    	}
    	getData(orgId);
    }
    $('#goBack').on('click',function(){
    	var value = $('#switchContrastConditions input[name=dataComparison]:checked').val();
    	switchContrastConditions(value);
    	//switchGrid(value);  // 切换grid
    	$('#goBack').hide(200);
    });
    
    function switchGridShow(value){
    	var data = dataInfo.dataListHb;
        if( value === 'hb'){
            var baseColumns =[
                { text: '区域\\时间', datafield: 'AreaDate', width: 120 },
                { text: tool_data[0], datafield: 'October', width: 100 },
                { text: tool_data[1], datafield: 'November', width: 100 },
                { text: '环比增长率%', datafield: 'hbGrowthRate', minWidth: 80 }
            ];
        }else if( value === 'tb') {

            var baseColumns =[
                { text: '区域\\时间', datafield: 'AreaDate', width: 120 },
                { text: tool_data[0], datafield: 'October', width: 100 },
                { text: tool_data[1], datafield: 'November', width: 100 },
                { text: '同比增长率%', datafield: 'tbGrowthRate',minWidth:80}
            ];
        }
        buildGrid(data,baseColumns,value);
    }
   
    /*
    *   grid 初始化
    * */

    // 初始化化build参数
     function buildGrid(data,columns,value){
    	 if( value === 'hb'){
            var source ={
                datatype: "json",
                datafields: [
                    { name: 'AreaDate', type: 'string' },
                    { name: 'October', type: 'string' },
                    { name: 'November', type: 'string' },
                    { name: 'hbGrowthRate', type: 'string' },
                    { name: 'tbGrowthRate', type: 'string' },
                    { name: 'id', type: 'string' }
                ],
                localdata: eval(dataInfo.dataListHb)
            };
    	 }else if( value === 'tb'){
    		 var source ={
 	                datatype: "json",
 	                datafields: [
 	                    { name: 'AreaDate', type: 'string' },
 	                    { name: 'October', type: 'string' },
 	                    { name: 'November', type: 'string' },
 	                    { name: 'hbGrowthRate', type: 'string' },
 	                    { name: 'tbGrowthRate', type: 'string' },
 	                    { name: 'id', type: 'string' }
 	                ],
 	                localdata: eval(dataInfo.dataListTb)
 	            };
    	 }
        var dataAdapter = new $.jqx.dataAdapter(source);

        $("#gridPopulationTrendAnalysis").jqxGrid({
            width: 550,
            autoHeight: true,
            source: dataAdapter,
            columnsresize: true,
            columns:columns
        });

    }
    // 户籍人口细化分析
    function settingAnalysisConditions(value){
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
                    axisLabel:{'interval':0,'rotate':-45},
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
            $.extend(settings,params);
            $('#'+id).buildChart(settings);
        }
        // 生成饼状图公共方法
        function createAnnulusCharts(id,params,callback) {
        	var settings = {
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : false
        	};
        	$.extend(settings,params);
            $('#'+id).buildChart(settings,callback);
        }
        // 饼状图公用的样式
        var dataStyle = {
            normal: {
                label: {show: false},
                labelLine: {show: false}
            }
        };
        var placeHolderStyle = {
            normal: {
                color: 'rgba(0,0,0,0)',
                label: {show: false},
                labelLine: {show: false}
            },
            emphasis: {
                color: 'rgba(0,0,0,0)'
            }
        };

        function gainPieData(dimensionKeyName,dimensionName,dimensionValue){
        	//获取关键字代表模块
        	var keyName = getKeyName(dimensionKeyName);
        	var seriesData = [];
	       	if(dimensionName != null){
	       		for(var i = dimensionName.length-1 ; i >= 0  ; i -- ){
	        		var objData = {};
	        		objData.value = dimensionValue[i];
	        		objData.name = dimensionName[i];
	        		seriesData.push(objData);
	        	}
	       	}
            var pieData = {
                legend: {
                	x : 'center',
                    y : 'bottom',
                    data: dimensionName
                },
                series: [
                    {
                        name: ''+keyName+'',
                        type: 'pie',
                        radius : [30, 110],
                        center : ['45%', 120],
                        roseType : 'area',
                        x: '50%',  
                        max: 40,
                        sort : 'ascending', 
                        data: seriesData
                    }
                ]
            }
            return pieData;
        }
        // 捞取性别饼状图数据
        function getGenderPieData(callback){
        	var dimensionName=[];
            var dimensionValue=[];
//            var genderPie = "var genderPie = " ;
        	$.ajax({
        		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
        		type:'POST',
        		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"sex","year":$("#year").val(),"month":$("#month").val()},
        		async:true,
        		success:function(data){
        			if(data){
	        			/*genderPie = data.mosaic; 
	                    callback(eval("("+genderPie+")"));*/
        				dimensionName= data.dimensionName;
        				dimensionValue= data.dimensionValue;
        				var pieData = gainPieData("sex",dimensionName,dimensionValue);
        				callback(pieData);
        			}
        		}
        	})
         }
        function getAgePieData(callback){
        	 var agePie = " var agePie = ";
         	$.ajax({
         		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
         		type:'POST',
         		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"age","year":$("#year").val(),"month":$("#month").val()},
         		async:true,
         		success:function(data){
         			if(data){
	         			/*agePie = data.mosaic; 
	                    callback(eval("("+agePie+")"))*/
         				dimensionName= data.dimensionName;
        				dimensionValue= data.dimensionValue;
        				var pieData = gainPieData("age",dimensionName,dimensionValue);
        				callback(pieData);
         			}
         		}
         	})
        }
        function getDiplomaPieData(callback){
        	 var schoolPie = "var diplomaPie = ";
          	$.ajax({
          		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
          		type:'POST',
          		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"edu","year":$("#year").val(),"month":$("#month").val()},
          		async:true,
          		success:function(data){
          			if(data){
	          			/*schoolPie = data.mosaic; 
	                    callback(eval("("+schoolPie+")"))*/
          				dimensionName= data.dimensionName;
        				dimensionValue= data.dimensionValue;
        				var pieData = gainPieData("edu",dimensionName,dimensionValue);
        				callback(pieData);
          			}
          		}
          	})
        }
        function getMarriagePieData(callback){
        	 var marryPie = " var diplomaPie = ";
           	$.ajax({
           		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
           		type:'POST',
           		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"mar","year":$("#year").val(),"month":$("#month").val()},
           		async:true,
           		success:function(data){
           			if(data){
	           			/*marryPie = data.mosaic; 
	                    callback(eval("("+marryPie+")"))*/
           				dimensionName= data.dimensionName;
        				dimensionValue= data.dimensionValue;
        				var pieData = gainPieData("mar",dimensionName,dimensionValue);
        				callback(pieData);
           			}
           		}
           	})

        }
        
        function getFlpopPieData(callback){
       	 var flpopPie = " var flpopPie = ";
          	$.ajax({
          		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
          		type:'POST',
          		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"flo","year":$("#year").val(),"month":$("#month").val()},
          		async:true,
          		success:function(data){
          			if(data){
	          			/*flpopPie = data.mosaic; 
	                   callback(eval("("+flpopPie+")"))*/
          				dimensionName= data.dimensionName;
        				dimensionValue= data.dimensionValue;
        				var pieData = gainPieData("flo",dimensionName,dimensionValue);
        				callback(pieData);
          			}
          		}
          	})

       }
        
        // 城市请求饼状图数据
        function getProPieData(callback,option){
       	 var proPie = " var proPie = ";
          	$.ajax({
          		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
          		type:'POST',
          		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"pro","year":$("#year").val(),"month":$("#month").val()},
          		async:true,
          		success:function(data){
          			if(data){
	          		/*	proPie = data.mosaic; 
	                   callback(eval("("+proPie+")"))*/
          				dimensionName= data.dimensionName;
        				dimensionValue= data.dimensionValue;
        				var pieData = gainPieData("pro",dimensionName,dimensionValue);
        				callback(pieData);
          			}
          		}
          	})

       }
        function getCitPieData(callback){
       	 var citPie = " var citPie = ";
          	$.ajax({
          		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
          		type:'POST',
          		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"cit","year":$("#year").val(),"month":$("#month").val()},
          		async:true,
          		success:function(data){
          			if(data){
	          		/*	citPie = data.mosaic; 
	                   callback(eval("("+citPie+")"))*/
          				dimensionName= data.dimensionName;
        				dimensionValue= data.dimensionValue;
        				var pieData = gainPieData("cit",dimensionName,dimensionValue);
        				callback(pieData);
          			}
          		}
          	})
       }
        //获取关键字代表模块
        function getKeyName(key){
        	if(key == 'sex')return "性别";
        	else if(key == 'age')return "年龄";
        	else if(key == 'edu')return "文化程度";
        	else if(key == 'mar')return "婚姻状况";
        	else if(key == 'flo')return "流入原因";
        	else if(key == 'pro')return "按省份排名";
        	else if(key == 'cit')return "按城市排名";
        }
        var proDimensionName = [];
        var proDimensionValue = [];
        function gainBarData(dimensionKeyName,dimensionName,dimensionValue){
        	if(dimensionKeyName == 'pro'){
        		if(dimensionName != null && dimensionName.length > 0){
        			proDimensionValue = dimensionValue;
        			proDimensionName = dimensionName;
        			dimensionName = dimensionName.slice(0,customSum);
        			dimensionValue = dimensionValue.slice(0,customSum);
        			getCustomPro(dimensionName,proDimensionName,proDimensionValue);
        		}
        	}
	       	if(dimensionKeyName == 'cit'){
        		if(dimensionName != null && dimensionName.length > 0){
        			dimensionName = dimensionName.slice(0,customSum);
        			dimensionValue = dimensionValue.slice(0,customSum);
        		}
        	}
        	
        	var keyName = getKeyName(dimensionKeyName);
        	//设置横向名称倾斜度，性别、年龄较少，不用倾斜
        	var tilt = -45;
	       	if(dimensionKeyName == 'sex' || dimensionKeyName == 'mar') tilt = 0;
            var setting = {
                legend: {
                    data:[''+keyName+'']
                },
                xAxis : [{
                    type : 'category',
                    axisLabel:{'interval':0,'rotate':tilt,'margin':0},
                    data : dimensionName
                }],
                series : [{
                    name:''+keyName+'',
                    type:'bar',
                    barMaxWidth:'40',
                    data:dimensionValue
                }]
            }
            return setting;
        	
        }
        //******************省份自定义显示  start ********************************//
        //自定义显示
        function getCustomPro(dimensionName,proDimensionName,proDimensionValue){
    		var proStr = "";
    		for(var i = 0 ; i < proDimensionName.length ; i ++ ){
    			proStr+="<li><label><input type='checkbox' name='checkPro' orgName='"+proDimensionName[i]+"'  value='"+proDimensionValue[i]+"'>"+proDimensionName[i]+"</label></li>";
    		}
        	$('#migrantsCustomDisplay').html(proStr);
        	var checkPro=$("input[name='checkPro']");
	        if( checkPro!=null){	          
	        	for(var i=0;i<dimensionName.length;i++){
	   		       for(var j=0;j<checkPro.length;j++ ){
	   		           if(checkPro.eq(j).attr('orgName')==dimensionName[i]){  
	   		                checkPro[j].checked=true;  
	   		           }
	   		         }
	        	 }
	        }
        }
        // 自定显示流动人员省份
        var $migrantsCustomDisplay = $('#switchViewMode')
        $migrantsCustomDisplay.on('click','button',function(){
     	    var arr = "";
     	    var arrValue = "";
 			$('#migrantsCustomDisplay').find('input').each(function(index){
 				if(this.checked){
 					arr += $(this).attr('orgname')+",";
 					arrValue += this.value+",";
 				}
 			})
 			$("#proHideName").val(arr);//勾选的省份
 			$("#proHideNameValue").val(arrValue);//勾选的省份的值
 			
		  var selectedProArr = $("#proHideName").val().split(",");
		  if( selectedProArr.length-1 > customSum+2 ){
			  $.messageBox({message:"最多显示12个省份",level: "warn"});
	          return;
		  }
		  gainBarDataPro();
        })  
        //自定义省份 点击确定后 重新加载省份柱状图
        function gainBarDataPro(){
    	    var selectedProArr = $("#proHideName").val().split(",");
    	    var selectedProArrVal = $("#proHideNameValue").val().split(",");
    	    selectedProArr = selectedProArr.slice(0,selectedProArr.length-1);
    	    selectedProArrVal = selectedProArrVal.slice(0,selectedProArrVal.length-1);
            var setting = {
                legend: {
                    data:['按省份排名']
                },
                xAxis : [{
                    type : 'category',
                    axisLabel:{'interval':0,'rotate':-45,'margin':0},
                    data : selectedProArr
                }],
                series : [{
                    name:'按省份排名',
                    type:'bar',
                    barMaxWidth:'40',
                    data:selectedProArrVal
                }]
            }
            createBarCharts("chartsBarPro",setting);
            
        }
        //*******************省份自定义显示  end****************************//
        
     function getBarData(dimensionKeyName,keyNameLeft,keyNameRight,param,modulelId){
		var settingParam = {
            legend: {
                data:['']
            },
            xAxis : [{
                type : 'category',
                data : []
            }],
            series : [{
                name:'',
                type:'bar',
                data:[]
            }]
        }
		var dataParam;
    	if(param){
    		$('#migrantsCustomDisplay').html('');
    		gainBarDataPro();
    		dataParam = {"orgId":$("#currentOrgId").val(),"dimensionKeyName":dimensionKeyName, "keyNameLeft":keyNameLeft,"keyNameRight":keyNameRight,"dimensionName":param.name,"year":$("#year").val(),"month":$("#month").val()};
    	}else{
    		dataParam = {"orgId":$("#currentOrgId").val(),"dimensionKeyName":dimensionKeyName,"year":$("#year").val(),"month":$("#month").val()};
    	}
    	if(dimensionKeyName == 'pro'){
			$.extend(dataParam,{"proNameShow":$("#proHideName").val(),"proValueShow":$("#proHideNameValue").val()});
    	}
		$.ajax({
    		url:PATH+'/judgmentAnalysis/floatingPopulation/findDimensionCombinationInfo.action',
    		type:'POST',
    		data: dataParam,
    		async:true,
    		success:function(data){
    			if(data){
    				/*if(dimensionKeyName == 'pro'){
    					orgNameAfter = data.orgNameAfter;
    					orgNameAfterValue = data.orgNameAfterValue;
        				var liStr="";
	        			if(orgNameAfter == null || orgNameAfterValue == null ){
	        	            liStr="";
	        			}else{
	        				 for(var i=0;i<orgNameAfter.length;i++){
	        	                liStr+="<li><label><input type='checkbox' name='checkPro' orgName='"+orgNameAfter[i]+"'  value='"+orgNameAfterValue[i]+"'>"+orgNameAfter[i]+"</label></li>";
	        	             } 
	        			}
	        			$('#migrantsCustomDisplay').html(liStr);
	        			
	        			var hideVal = $("#hideVal").val();
	        			var proHideName = $("#proHideName").val();
	    				if(proHideName != ''){
	    				  var selectedProArr = $("#proHideName").val().split(",");
	    				  var hideValArr = $("#hideVal").val().split(",");
	    				  
    					  var checkPro=$("input[name='checkPro']");
	    		          if( checkPro!=null){	          
	    		        	 for(var i=0;i<selectedProArr.length-1;i++){
	    	    		         for(var j=0;j<checkPro.length;j++ ){
	    	    		             if(checkPro.eq(j).attr('orgName')==selectedProArr[i]){  
	    	    		                  checkPro[j].checked=true; 
	    	    		             }
	    	    		         }
	    		        	 }
	    		           }
	    				}
    				}
    				setting = data.mosaicBar;
    				setting = eval("("+setting+")");
    				if(setting == null){
    					setting = settingParam;
    				}*/
    				dimensionName= data.dimensionName;
    				dimensionValue= data.dimensionValue;
    				var setting = gainBarData(dimensionKeyName,dimensionName,dimensionValue);
    				createBarCharts(modulelId,setting);
    			}
    		}
    	})
	}
    
        
        /*
         *   性别数据
         * */
        if( value === 'sex'){
            $('#chartsBarGender').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
            getGenderPieData(function(genderPie){
                function resetGenderBar(params){
                	paramsTemp = params;
                	getBarData("age","sex","age",params,"chartsBarAge");	
                	getBarData("edu","sex","edu",params,"chartsBarDiploma");
                	getBarData("mar","sex","mar",params,"chartsBarMarriage");
                	getBarData("flo","sex","flo",params,"chartsBarFlpop");
                	getBarData("pro","sex","pro",params,"chartsBarPro");
                	getBarData("cit","sex","cit",params,"chartsBarCit");
                }
                createAnnulusCharts('chartsBarGender',genderPie,function(chart){
                	 var ecConfig = require('echarts/config');
	                 chart.on(ecConfig.EVENT.CLICK, resetGenderBar);
                 });
                resetGenderBar()
            })
        }
        
        /*
         *   年龄比例
         * */
        if( value === 'age'){
            $('#chartsBarAge').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
            getAgePieData(function(agePie){
                function resetAgeBar(params){
                	paramsTemp = params;
                	getBarData("sex","age","sex",params,"chartsBarGender");	
                 	getBarData("edu","age","edu",params,"chartsBarDiploma");
                 	getBarData("mar","age","mar",params,"chartsBarMarriage");
                 	getBarData("flo","age","flo",params,"chartsBarFlpop");
                 	getBarData("pro","age","pro",params,"chartsBarPro");
                 	getBarData("cit","age","cit",params,"chartsBarCit");
                }
                createAnnulusCharts('chartsBarAge',agePie,function(chart){
               	 var ecConfig = require('echarts/config');
	                 chart.on(ecConfig.EVENT.CLICK, resetAgeBar);
                });
                resetAgeBar()
            })

        }
        /*
         *   文化程度
         * */
        if( value === 'edu'){
            $('#chartsBarDiploma').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
            getDiplomaPieData(function(diplomaPie) {
                function resetDiplomaBar(params) {
                	 paramsTemp = params;
                	 getBarData("sex","edu","sex",params,"chartsBarGender");
                	 getBarData("age","edu","age",params,"chartsBarAge");	
                 	 getBarData("mar","edu","mar",params,"chartsBarMarriage");
                 	 getBarData("flo","edu","flo",params,"chartsBarFlpop");
                 	 getBarData("pro","edu","pro",params,"chartsBarPro");
                 	 getBarData("cit","edu","cit",params,"chartsBarCit");
                }
                createAnnulusCharts('chartsBarDiploma',diplomaPie,function(chart){
                  	 var ecConfig = require('echarts/config');
   	                 chart.on(ecConfig.EVENT.CLICK, resetDiplomaBar);
                   });
                resetDiplomaBar()
            })
        }
        /*
        *   婚姻状况
        * */
        if( value === 'mar'){
            $('#chartsBarMarriage').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
            getMarriagePieData(function(maritalStatusPie) {
                function resetMaritalStatusBar(params) {
                	paramsTemp = params;
                	 getBarData("sex","mar","sex",params,"chartsBarGender");
                	 getBarData("age","mar","age",params,"chartsBarAge");	
                 	 getBarData("edu","mar","edu",params,"chartsBarDiploma");
                 	 getBarData("flo","mar","flo",params,"chartsBarFlpop");
                 	 getBarData("pro","mar","pro",params,"chartsBarPro");
                 	 getBarData("cit","mar","cit",params,"chartsBarCit");
                }
                createAnnulusCharts('chartsBarMarriage',maritalStatusPie,function(chart){
                 	 var ecConfig = require('echarts/config');
  	                 chart.on(ecConfig.EVENT.CLICK, resetMaritalStatusBar);
                  });
                resetMaritalStatusBar()
            })
        }
        /*
         *   流入原因
         * */
         if( value === 'flo'){
             $('#chartsBarFlpop').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
             getFlpopPieData(function(flpopStatusPie) {
                 function resetFlpopStatusBar(params) {
                	 paramsTemp = params;
                	 getBarData("sex","flo","sex",params,"chartsBarGender");
                	 getBarData("age","flo","age",params,"chartsBarAge");	
                 	 getBarData("edu","flo","edu",params,"chartsBarDiploma");
                 	 getBarData("mar","flo","mar",params,"chartsBarMarriage");
                 	 getBarData("pro","flo","pro",params,"chartsBarPro");
                 	 getBarData("cit","flo","cit",params,"chartsBarCit");
                 }
                 createAnnulusCharts('chartsBarFlpop',flpopStatusPie,function(chart){
                 	 var ecConfig = require('echarts/config');
  	                 chart.on(ecConfig.EVENT.CLICK, resetFlpopStatusBar);
                  });
                 resetFlpopStatusBar()
             })
         }
         /*
          *   户籍情况（按省份排名）
          * */
          if( value === 'pro'){
              $('#chartsBarPro').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
              getProPieData(function(proStatusPie) {
                  function resetProStatusBar(params) {
                	  paramsTemp = params;
                	  getBarData("sex","pro","sex",params,"chartsBarGender");
                 	  getBarData("age","pro","age",params,"chartsBarAge");	
                  	  getBarData("edu","pro","edu",params,"chartsBarDiploma");
                  	  getBarData("mar","pro","mar",params,"chartsBarMarriage");
                  	  getBarData("flo","pro","flo",params,"chartsBarFlpop");
                  	  getBarData("cit","pro","cit",params,"chartsBarCit");
                  }
                  createAnnulusCharts('chartsBarPro',proStatusPie,function(chart){
                  	 var ecConfig = require('echarts/config');
   	                 chart.on(ecConfig.EVENT.CLICK, resetProStatusBar);
                   });
                  resetProStatusBar()
              })
          }
          /*
           *   户籍情况（按城市排名）
           * */
           if( value === 'cit'){
               $('#chartsBarCit').closest('.subModBox').addClass('pie').siblings().removeClass('pie')
               getCitPieData(function(citStatusPie) {
                   function resetCitStatusBar(params) {
                	   paramsTemp = params;
                	   getBarData("sex","cit","sex",params,"chartsBarGender");
                  	   getBarData("age","cit","age",params,"chartsBarAge");	
                   	   getBarData("edu","cit","edu",params,"chartsBarDiploma");
                   	   getBarData("mar","cit","mar",params,"chartsBarMarriage");
                   	   getBarData("flo","cit","flo",params,"chartsBarFlpop");
                   	   getBarData("pro","cit","pro",params,"chartsBarPro");
                   }
                   createAnnulusCharts('chartsBarCit',citStatusPie,function(chart){
                    	 var ecConfig = require('echarts/config');
     	                 chart.on(ecConfig.EVENT.CLICK, resetCitStatusBar);
                     });
                   resetCitStatusBar()
               })
           }
        /* if gender*/

       // 自定显示流动人员省份
     /*  var $migrantsCustomDisplay = $('#switchViewMode')
       $migrantsCustomDisplay.on('click','button',function(){
    	    var arr = "";
    	    var arrValue = "";
			$('#migrantsCustomDisplay').find('input').each(function(index){
				if(this.checked){
					arr += $(this).attr('orgname')+",";
					arrValue += this.value+",";
				}
			})
			$("#proHideName").val(arr);
			
			  var selectedProArr = $("#proHideName").val().split(",");
			  var hideValArr = $("#hideVal").val().split(",");
			  if( selectedProArr.length-1 > 2 ){
				  $.messageBox({message:"最多显示12个省份",level: "warn"});
				  var checkPro=$("input[name='checkPro']");
				  checkPro.removeAttr("checked");
				  
  		          if( checkPro!=null){	          
  		        	 for(var i=0;i<hideValArr.length-1;i++){
  	    		         for(var j=0;j<checkPro.length;j++ ){
  	    		             if(checkPro.eq(j).attr('orgName')==hideValArr[i]){  
  	    		                  checkPro[j].checked=true; 
  	    		             }
  	    		         }
  		        	 }
  		           }
  		          
  		          return;
			  }
				  
			
			
			$("#hideVal").val(arr);
			
			$("#proHideNameValue").val(arrValue);
			var typeValue = $('#switchDetailedAnalysis input[name=peopleType]:checked').val();
			if(typeValue == 'pro'){
				getProPieData(function(proStatusPie) {
				    createAnnulusCharts('chartsBarPro', proStatusPie );
			    },$("#proHideName").val())
			}else{
				getBarData("pro",typeValue,"pro",paramsTemp,"chartsBarPro");
			}
			
       })*/
    }


    function changeDescription(){
    	$.ajax({
    		url:PATH+'/judgmentAnalysis/businessDescriptionManage/getDescriptionForStatistics.action?orgId='+$.getCurrentOrgId()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&keyName=flpop',
    		async:true,
    		success:function(data){
    			
    			$(".mContIntro").text(data);
    		}
    	});
    }

    
	function getChartsData(value){
        if( value === 'hb'){
        	return {
                legend: {
                	 data:tool_data
                },
                series : [
                    {
                    	name:tool_data[0],
                        type:'bar',
                        barMaxWidth:46,
                        data:dataInfo.amountOldHb == null ? [] :　dataInfo.amountOldHb
                    },
                    {
                    	name:tool_data[1],
                        type:'bar',
                        barMaxWidth:46,
                        data:dataInfo.amountHb == null ? [] :　dataInfo.amountHb
                    },
                    {
                        name:'环比增长率%',
                        type:'line',
                        yAxisIndex: 1,
                        data:dataInfo.rateHb == null ? [] :　dataInfo.rateHb
                    }
                ]
            };
        }else if( value === 'tb'){
        	return {
                legend: {
                	data:tool_data
                },
                series : [
                    {
                    	name:tool_data[0],
                        type:'bar',
                        barMaxWidth:46,
                        data:dataInfo.amountOldTb == null ? [] :　dataInfo.amountOldTb
                    },
                    {
                    	name:tool_data[1],
                        type:'bar',
                        barMaxWidth:46,
                        data:dataInfo.amountTb == null ? [] :　dataInfo.amountTb
                    },
                    {
                        name:'同比增长率%',
                        type:'line',
                        yAxisIndex: 1,
                        data:dataInfo.rateTb == null ? [] :　dataInfo.rateTb
                    }
                ]
            };
        }
    }
	
	function callback(chart){
		var value = $('#switchContrastConditions input[name=dataComparison]:checked').val();
        // 根据点击事件重新捞取对应数据
    	function refreshCharts(item){
        	var _orgId = '';
        	if(orgIdTemp != undefined && orgNameTemp != undefined){
            	 orgId =  orgIdTemp ;
            	 orgName = orgNameTemp ;
    	    }
        	if(orgName != null && orgName.length != 0){
            	for(var i=0; i<orgName.length; i++){
            		if(orgName[i] == item.name){
            			_orgId= orgId[i]
            		}
            	}
        	}
        	if(_orgId != ''){
        		switchContrastConditions(value,_orgId);
        		//switchGrid(value,_orgId);  // 切换grid
        		$('#goBack').show(200)
        	}
        }
        var ecConfig = require('echarts/config');
        chart.on(ecConfig.EVENT.CLICK, refreshCharts);
    }
});