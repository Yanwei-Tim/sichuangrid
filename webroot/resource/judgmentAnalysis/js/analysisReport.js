$(function(){
	$("#orgName").text($.getCurrentOrgName());
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
					$("#orgName").text(selectInput.attr("text"));
					$(this).dialog("close");
					refreshData();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		
	});
	$.resetHeight()
	var orgId = '';
	var dataInfo = {};
	var orgNameTemp,orgIdTemp,hbyearMonth,tool_data ;
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
			
			 if( $('#switchContrastConditions')[0] && $('#switchDetailedAnalysis')[0] ){
		        init()
		    }
		}
	});
	 // 各种初始化加载方法
	    function init(){
	    	var value = $('#switchContrastConditions input[name=dataComparison]:checked').val();
	    	changeDescription();
	    	
	        // 户籍人口趋势分析
	        switchContrastConditions(value) // 切换charts
//	        switchGrid(value)   // 切换grid
	        $('#switchContrastConditions').on('change','input',function(){
	            switchContrastConditions(this.value)
//	            switchGrid(this.value)
	        });

	        // 户籍人口细化分析
	        settingAnalysisConditions( $('#switchDetailedAnalysis input[name=peopleType]:checked').val() )

	        $('#switchDetailedAnalysis').on('change','input',function(){
	            settingAnalysisConditions(this.value)
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
	
	$(".currentTime").html("（"+$('#year').val()+"年"+$('#month').val()+"月）");
	
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
		var value = $('#switchContrastConditions input[name=dataComparison]:checked').val();
		switchContrastConditions(value) ;
		changeDescription();
//		switchGrid(value);
		settingAnalysisConditions( $('#switchDetailedAnalysis input[name=peopleType]:checked').val() )		
	}
	function getData(orgId){
		if(!orgId){
    		var orgId = $("#currentOrgId").val()
    	}
		$.ajax({
			url:PATH+'/judgmentAnalysis/houseHoldStaff/getArrayHistoryCycles.action',
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
           tool_data=[hbyearMonth,year+'年'+month+'月','环比增长率‰'];     
        }else{
           tool_data=[(year-1)+'年'+month+'月',year+'年'+month+'月','同比增长率‰']; 
        } 
       /* var maxSize = 65;
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
                    name : '千分比',
                    axisLabel : {
                        formatter: '{value} ‰'
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
//        	switchGrid(value);  // 切换grid
    	$('#goBack').hide(200)
    })
    function switchGridShow(value){
    	var data = dataInfo.dataListHb;
        if( value === 'hb'){
            var baseColumns =[
                { text: '区域\\时间', datafield: 'AreaDate', width: 120 },
                { text: tool_data[0], datafield: 'October', width: 100 },
                { text: tool_data[1], datafield: 'November', width: 100 },
                { text: '环比增长率‰', datafield: 'hbGrowthRate', minWidth: 80 }
            ];
        }else if( value === 'tb') {

            var baseColumns =[
                { text: '区域\\时间', datafield: 'AreaDate', width: 120 },
                { text: tool_data[0], datafield: 'October', width: 100 },
                { text: tool_data[1], datafield: 'November', width: 100 },
                { text: '同比增长率‰', datafield: 'tbGrowthRate',minWidth:80}
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
                        mark : {show: false},
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
            var genderPie = "var genderPie = " ;
        	$.ajax({
        		url:PATH+'/judgmentAnalysis/houseHoldStaff/findDimensionCombinationInfo.action',
        		type:'post',
        		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"sex","year":$("#year").val(),"month":$("#month").val()},
        		async:false,
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
         		url:PATH+'/judgmentAnalysis/houseHoldStaff/findDimensionCombinationInfo.action',
         		type:'POST',
         		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"age","year":$("#year").val(),"month":$("#month").val()},
         		async:false,
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
          		url:PATH+'/judgmentAnalysis/houseHoldStaff/findDimensionCombinationInfo.action',
          		type:'POST',
          		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"edu","year":$("#year").val(),"month":$("#month").val()},
          		async:false,
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
           		url:PATH+'/judgmentAnalysis/houseHoldStaff/findDimensionCombinationInfo.action',
           		type:'POST',
           		data: {"orgId":$("#currentOrgId").val(),"dimensionKeyName":"mar","year":$("#year").val(),"month":$("#month").val()},
           		async:false,
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
        //获取关键字代表模块
        function getKeyName(key){
        	if(key == 'sex')return "性别";
        	else if(key == 'age')return "年龄";
        	else if(key == 'edu')return "文化程度";
        	else if(key == 'mar')return "婚姻状况";
        }
        function gainBarData(dimensionKeyName,dimensionName,dimensionValue){
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
    		var dimensionName=[];
            var dimensionValue=[];
    		var dataParam;
        	if(param){
        		dataParam = {"orgId":$("#currentOrgId").val(),"dimensionKeyName":dimensionKeyName, "keyNameLeft":keyNameLeft,"keyNameRight":keyNameRight,"dimensionName":param.name,"year":$("#year").val(),"month":$("#month").val()};
        	}else{
        		dataParam = {"orgId":$("#currentOrgId").val(),"dimensionKeyName":dimensionKeyName,"year":$("#year").val(),"month":$("#month").val()};
        	}
    		$.ajax({
        		url:PATH+'/judgmentAnalysis/houseHoldStaff/findDimensionCombinationInfo.action',
        		type:'POST',
        		data: dataParam,
        		async:true,
        		success:function(data){
        			if(data){
        				/*setting = data.mosaicBar;
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
                }
                createAnnulusCharts('chartsBarMarriage',maritalStatusPie,function(chart){
                 	 var ecConfig = require('echarts/config');
  	                 chart.on(ecConfig.EVENT.CLICK, resetMaritalStatusBar);
                  });
                resetMaritalStatusBar()
            })
        }

        /* if gender*/
    }


    

    function changeDescription(){
    	$.ajax({
    		url:PATH+'/judgmentAnalysis/businessDescriptionManage/getDescriptionForStatistics.action?orgId='+$.getCurrentOrgId()+'&year='+$("#year").val()+'&month='+$("#month").val()+'&keyName=hstaff',
    		async:false,
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
                        name:'环比增长率‰',
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
                        name:'同比增长率‰',
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
