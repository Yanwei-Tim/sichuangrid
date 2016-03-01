(function($){
	$.fn.extend({
		columnChart : function(option,columnOptions){
			Highcharts.theme = {
				colors: ["#514F78", "#42A07B", "#9B5E4A", "#72727F", "#1F949A", "#82914E", "#86777F", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee"]
			};
			Highcharts.setOptions(Highcharts.theme);
			var self=$(this);
			var selfHeight=self.height();
			var chart = '';
			var defaultOption = {
					url:'',
					quantity:'',
					moduleName:'',
					renderTo: self.attr('id'),
					defaultSeriesType: 'column',
					width:null,
					ajax:true,
					textx:'',
					events:{
						load:function(){
							$.sigmaReportLayout();
						}
					},
					margin:40,
					marginTop:60,
					marginBottom:60,
					options3d: {
		                enabled: true,
		                alpha: 0,
		                beta: 0,
		                depth: 120,
		                viewDistance:250
		            }
			};
			if(defaultOption.renderTo==undefined){
				return;
			}
			
			$.extend(defaultOption,option);
			var chartOptions = {
					chart:defaultOption,
					title: {
						align:'center',
						text: '',
						margin:30
					},
					style: {
						fontFamily: 'Arial'
					},
					xAxis: {
						categories:[]
					},
					yAxis: {
						allowDecimals: false,
						opposite: false,
						min: 0,
						title: {
							text: ''
						},
						labels: {
			                align: 'right',
			                x: 1
			            }
					},
					legend: {
						align: 'left',
						x: 70,
						verticalAlign: 'top',
						y: 30,
						floating: true,
						backgroundColor: '#FFFFFF',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.x +'</b><br/>'+
								 this.series.name +': '+ this.y +(option.isShowSum=='false'?'':'<br/>'+defaultOption.moduleName+'总数量: '+ this.point.stackTotal);
						}
					},
					plotOptions: {
						column: {
							stacking: 'normal',
							depth: 25
						}
					},
				    series: [],
				    credits: {
						enabled: true,
						text: '',
						href: 'javascript:;',
						position: {
							align: 'right',
							x: -10,
							verticalAlign: 'bottom',
							y: -5
						},
						style: {
							cursor: 'pointer',
							color: '#000000',
							fontSize: '10px'
						}
					},
					lang: {
						downloadPNG : "下载成 PNG 格式",
						downloadJPEG : "下载成 JPEG 格式",
						downloadPDF : "下载成 PDF 格式",
						downloadSVG : "",
						exportButtonTitle : "导出图片",
						printButtonTitle : "打印"
					},
					exporting:{
						buttons:{
							printButton:{
								enabled:false,
								onclick:function(){
									$(this.container).printArea();
								}
							},
							exportButton:{
								enabled:false,
								align:'right',
								x:-30,
								y:45
							}
						}
					}
			};
			
			
			if(defaultOption.ajax){
				$.ajax({
					async: false,
					url: defaultOption.url,
					success:function(responseData){
						var moduleName='';
						if(responseData==null || responseData.series==null){return false;}
						if(defaultOption.moduleName != ''){
							moduleName = defaultOption.moduleName;
						}else{
							moduleName = responseData.moduleName;
						}
						var origChartWidth = 90;
						if(responseData!=null && responseData.categories!=null && responseData.categories.length>13){
							var num = responseData.categories.length;
							chartOptions.chart.width = origChartWidth*num;
						}
						var sum = 0;
						var i,j;
						//alert("series:"+responseData.series.length+",categories:"+responseData.categories.length);
						var display="";
						if(responseData.series.length<=2){
							for(i=0;i<responseData.series.length;i++){
								for(j=0;j<responseData.categories.length;j++){
									if(display==""){
										display=responseData.series[i].stack;
									}
									if(display==responseData.series[i].stack){
									sum += responseData.series[i].data[j];
									}
								}
							}
						}else if(responseData.series.length==4 && responseData.categories.length==1){
							for(i=0;i<responseData.series.length;i++){
								for(j=0;j<responseData.categories.length;j++){
									if(display==""){
										display=responseData.series[i].stack;
									}
									if(display==responseData.series[i].stack){
									sum += responseData.series[i].data[j];
									}
								}
							}
						}else{
							for(i=0;i<responseData.series.length;i++){
								for(j=0;j<responseData.categories.length;j++){
									if(display==""){
										display=responseData.series[i].stack;
									}
									if(display==responseData.series[i].stack){
									sum += responseData.series[i].data[j];
									}
								}
							}
						}
						chartOptions.title.text = moduleName + "(总计: " + sum + "个)";
						chartOptions.yAxis.title.text = moduleName+ 'y轴（'+defaultOption.quantity+'）（1k=1000）';
						chartOptions.title.x =defaultOption.textx;
						if(defaultOption.legend != ''){
							$.extend(chartOptions.legend,defaultOption.legend);
						}
						chartOptions.xAxis.categories = responseData.categories ;
						chartOptions.series = responseData.series;
						$.extend(true,chartOptions,columnOptions);//深度copy传入的参数
						if(chartOptions.workbenchColum==true && responseData.categories.length>5){
							var label={
								labels:{
									rotation: -45,
				                    style: {
				                        fontSize: '11px'
				                    }
								}
							}
							$.extend(true,chartOptions.xAxis,label);
						}
						try
						{
							chart=new Highcharts.Chart(chartOptions);
						}
						catch(err)
						{
							$.messageBox({message:err,level:'error'});
							throw new Error(err);
						}
						
					}
				});
			}else{
				var visualizeOptions={
					title:{
						text:'',
						margin:15
					},
					plotOptions:{
						column:{
							stacking:null
						}
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.x +'</b><br/>'+
								 this.series.name +': '+ this.y;
						}
					},
					exporting:{
						buttons:{
							exportButton:{
							enabled:false,
								align:'right'
							},
							printButton:{
								enabled:false
							}
						}
					},
					legend:{
						align: 'left',
						x: 70,
						verticalAlign: 'top',
						y: -10,
						floating: true,
						backgroundColor: '#FFFFFF',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					}
				}
				$.extend(chartOptions,visualizeOptions);
				chart=Highcharts.visualize(defaultOption.table, chartOptions);
			}
			$("#"+defaultOption.renderTo).children(".highcharts-container").css({
				overflow:"auto",width:"100%","overflow-y":"auto"
			})
			self.data("chart",chart);
			return chart;
		}
	});
	
	$.fn.extend({
		columnChartOfTaskList : function(option,columnOptions){
			Highcharts.theme = {
				colors: ["#514F78", "#42A07B", "#9B5E4A", "#72727F", "#1F949A", "#82914E", "#86777F", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee"]
			};
			Highcharts.setOptions(Highcharts.theme);
			var self=$(this);
			var selfHeight=self.height();
			var chart = '';
			var defaultOption = {
					url:'',
					quantity:'',
					moduleName:'',
					renderTo: self.attr('id'),
					defaultSeriesType: 'column',
					width:null,
					ajax:true,
					textx:'',
					events:{
						load:function(){
							$.sigmaReportLayout();
						}
					},
					margin:40,
					marginTop:60,
					marginBottom:60,
					options3d: {
		                enabled: true,
		                alpha: 0,
		                beta: 0,
		                depth: 120,
		                viewDistance:250
		            }
			};
			if(defaultOption.renderTo==undefined){
				return;
			}
			
			$.extend(defaultOption,option);
			var chartOptions = {
					chart:defaultOption,
					title: {
						align:'center',
						text: '',
						margin:30
					},
					style: {
						fontFamily: 'Arial'
					},
					xAxis: {
						categories:[]
					},
					yAxis: {
						allowDecimals: false,
						opposite: false,
						min: 0,
						title: {
							text: ''
						},
						labels: {
			                align: 'right',
			                x: 1
			            }
					},
					legend: {
						align: 'left',
						x: 70,
						verticalAlign: 'top',
						y: 30,
						floating: true,
						backgroundColor: '#FFFFFF',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.x +'</b><br/>'+
								 this.series.name +': '+ this.y +(option.isShowSum=='false'?'':'<br/>');
						}
					},
					plotOptions: {
//						column: {
//							stacking: 'normal',
//							depth: 25
//						}
						column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
					},
				    series: [],
				    credits: {
						enabled: true,
						text: '',
						href: 'javascript:;',
						position: {
							align: 'right',
							x: -10,
							verticalAlign: 'bottom',
							y: -5
						},
						style: {
							cursor: 'pointer',
							color: '#000000',
							fontSize: '10px'
						}
					},
					lang: {
						downloadPNG : "下载成 PNG 格式",
						downloadJPEG : "下载成 JPEG 格式",
						downloadPDF : "下载成 PDF 格式",
						downloadSVG : "",
						exportButtonTitle : "导出图片",
						printButtonTitle : "打印"
					},
					exporting:{
						buttons:{
							printButton:{
								enabled:false,
								onclick:function(){
									$(this.container).printArea();
								}
							},
							exportButton:{
								enabled:false,
								align:'right',
								x:-30,
								y:45
							}
						}
					}
			};
			
			
			if(defaultOption.ajax){
				$.ajax({
					async: false,
					url: defaultOption.url,
					success:function(responseData){
						var moduleName='';
						if(responseData==null || responseData.series==null){return false;}
						if(defaultOption.moduleName != ''){
							moduleName = defaultOption.moduleName;
						}else{
							moduleName = responseData.moduleName;
						}
						var origChartWidth = 90;
						if(responseData!=null && responseData.categories!=null && responseData.categories.length>13){
							var num = responseData.categories.length;
							chartOptions.chart.width = origChartWidth*num;
						}
						var sum = 0;
						var i,j;
						//alert("series:"+responseData.series.length+",categories:"+responseData.categories.length);
						var display="";
						if(responseData.series.length<=2){
							for(i=0;i<responseData.series.length;i++){
								for(j=0;j<responseData.categories.length;j++){
									if(display==""){
										display=responseData.series[i].stack;
									}
									if(display==responseData.series[i].stack){
									sum += responseData.series[i].data[j];
									}
								}
							}
						}else if(responseData.series.length==4 && responseData.categories.length==1){
							for(i=0;i<responseData.series.length;i++){
								for(j=0;j<responseData.categories.length;j++){
									if(display==""){
										display=responseData.series[i].stack;
									}
									if(display==responseData.series[i].stack){
									sum += responseData.series[i].data[j];
									}
								}
							}
						}else{
							for(i=0;i<responseData.series.length;i++){
								for(j=0;j<responseData.categories.length;j++){
									if(display==""){
										display=responseData.series[i].stack;
									}
									if(display==responseData.series[i].stack){
									sum += responseData.series[i].data[j];
									}
								}
							}
						}
						chartOptions.title.text = moduleName;
						chartOptions.yAxis.title.text = moduleName+ 'y轴（'+defaultOption.quantity+'）（1k=1000）';
						chartOptions.title.x =defaultOption.textx;
						if(defaultOption.legend != ''){
							$.extend(chartOptions.legend,defaultOption.legend);
						}
						chartOptions.xAxis.categories = responseData.categories ;
						chartOptions.series = responseData.series;
						$.extend(true,chartOptions,columnOptions);//深度copy传入的参数
						if(chartOptions.workbenchColum==true && responseData.categories.length>5){
							var label={
								labels:{
									rotation: -45,
				                    style: {
				                        fontSize: '11px'
				                    }
								}
							}
							$.extend(true,chartOptions.xAxis,label);
						}
						try
						{
							chart=new Highcharts.Chart(chartOptions);
						}
						catch(err)
						{
							$.messageBox({message:err,level:'error'});
							throw new Error(err);
						}
						
					}
				});
			}else{
				var visualizeOptions={
					title:{
						text:'',
						margin:15
					},
					plotOptions:{
						column:{
							stacking:null
						}
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.x +'</b><br/>'+
								 this.series.name +': '+ this.y;
						}
					},
					exporting:{
						buttons:{
							exportButton:{
							enabled:false,
								align:'right'
							},
							printButton:{
								enabled:false
							}
						}
					},
					legend:{
						align: 'left',
						x: 70,
						verticalAlign: 'top',
						y: -10,
						floating: true,
						backgroundColor: '#FFFFFF',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					}
				}
				$.extend(chartOptions,visualizeOptions);
				chart=Highcharts.visualize(defaultOption.table, chartOptions);
			}
			$("#"+defaultOption.renderTo).children(".highcharts-container").css({
				overflow:"auto",width:"100%","overflow-y":"auto"
			})
			self.data("chart",chart);
			return chart;
		}
	});

	$.fn.extend({
		pieChart : function(option,pieChartOption){
			var bol='';
			Highcharts.theme = {
				colors: ["#514F78", "#42A07B", "#9B5E4A", "#72727F", "#1F949A", "#82914E", "#86777F", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee"]
			};
			Highcharts.setOptions(Highcharts.theme);
			
			var defaultOption = {
				url:"",
				moduleName:"",
				renderTo: $(this).attr('id'),
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        msgInfo:"",
				onClick:function(event){},
				margin:50,
				options3d: {
					enabled: true,
	                alpha: 45,
	                beta: 0
	            }
			};

			$.extend(defaultOption, option);
			var self=$(this);
			var chart = '';
			var msg_ = option.msgInfo;
			var chartOptions = {
				chart: defaultOption,
			    title: {
			        text: defaultOption.moduleName+'类型分布图',
			        margin:30
			    },
			    tooltip: {
			        formatter: function() {
			    		return '<b>'+ this.point.name +'</b>: '+ (this.y==0.001?'0':this.y) +' %';
			        }
			    },
			    plotOptions: {
			        pie: {
			    		allowPointSelect: true,
			            cursor: 'pointer',
		                depth: 35,
			            dataLabels: {
			            	enabled: true,
			            	color: '#000000',
			                connectorColor: '#000000',
			                formatter: function() {
			    	            if(this.y && ""==this.y.toString()){
			    	            	return '<b>'+ this.point.name +'</b>';
			    	            }else{
			    	            	return '<b>'+ this.point.name +'</b>: '+ (this.y==0.001?'0':this.y) +' %';
			    	            }
			                }
			             },
			             events: {
			                 click: defaultOption.onClick
			             }
			         }
			      },
			      series: [{
			    	  type: 'pie',
			    	  name: 'Browser share',
			          data: []
			      }],
			      credits: {
						enabled: true,
						text: '',
						href: 'javascript:;',
						position: {
							align: 'right',
							x: -10,
							verticalAlign: 'bottom',
							y: -5
						},
						style: {
							cursor: 'pointer',
							color: '#000000',
							fontSize: '10px'
						}
					},
					lang: {
						downloadPNG : "下载成 PNG 格式",
						downloadJPEG : "下载成 JPEG 格式",
						downloadPDF : "下载成 PDF 格式",
						downloadSVG : "",
						exportButtonTitle : "",
						printButtonTitle : "打印"
					},
					exporting:{
						buttons:{
							exportButton:{
								enabled:false
							},
							printButton:{
								enabled:false,
								onclick:function(){
									$(this.container).printArea();
								}
							}
						}
					}
			};
			$.ajax({
				async: false,
				url: defaultOption.url,
				success:function(data){
					if(data == null || data== "" || data.indexOf("errorCode")!=-1){
						$.messageBox({message:"查询的月份没有数据生成",level: "error"});
						return false;
					}else{
						for(var j=data.length-1;j>=0;j--){
							if( !data[j]['1'] ){
								data[j]['1'] = 0.001
							}
						}
						chartOptions.series[0].data=data;
						$.extend(true,chartOptions,pieChartOption);//深度copy传入的参数
						chart=new Highcharts.Chart(chartOptions);
					}
				}
			});
			self.data("chart",chart);
			return chart;
		}
	});
	
	
	
	$.fn.extend({
		lineChart : function(option,lineOptions){
			var defaultOption = {
					url:'',
					moduleName:'',
					ajax:true,
					options3d: {
						enabled: true
					},
					isReverse:true,
					renderTo: $(this).attr('id'),
					defaultSeriesType: 'spline',
					isIssue:false//是否是事件的趋势图,事件的趋势图允许为负数
			};
			var self=$(this);
			$.extend(defaultOption,option);
			var min=0;
			if(defaultOption.isIssue){
				min=null;
			}
			var line = '';
			var chartOptions = {
					chart:defaultOption,
					title: {
						text: ''
					},
					xAxis: {
						categories:[]
					},
					yAxis: {
						allowDecimals: false,
						min: min,
						title: {
							text: ''
						}
					},
				      tooltip: {
				          crosshairs: true,
				          shared: true
				       },
				       plotOptions: {
				          spline: {
				             marker: {
				                radius: 4,
				                lineColor: '#666666',
				                lineWidth: 1
				             }
				          }
				       },
					series:{},
					  credits: {
							enabled: true,
							text: '',
							href: 'javascript:;',
							position: {
								align: 'right',
								x: -10,
								verticalAlign: 'bottom',
								y: -5
							},
							style: {
								cursor: 'pointer',
								color: '#000000',
								fontSize: '10px'
							}
						},
						legend: {
							align: 'left',
							x: 70,
							verticalAlign: 'top',
							y: 18,
							floating: true,
							backgroundColor: '#FFFFFF',
							borderColor: '#CCC',
							borderWidth: 1,
							shadow: false
						},
						lang: {
							downloadPNG : "下载成 PNG 格式",
							downloadJPEG : "下载成 JPEG 格式",
							downloadPDF : "下载成 PDF 格式",
							downloadSVG : "",
							exportButtonTitle : "",
							printButtonTitle : "打印"
						},
						exporting:{
							exportButton:{
								align:'right',
								x:-30,
								y:45
							},
						    buttons:{
								exportButton:{
									enabled:false
								},
							    printButton:{
									enabled:false,
								    onclick:function(){
									    $(this.container).printArea();
								    }
							    }
						    }
						}

			};
			
			if(defaultOption.ajax){
				$.get(defaultOption.url, function(data) {
					var moduleName='';
					
					if(defaultOption.moduleName != ''){
						moduleName = defaultOption.moduleName;
					}else{
						moduleName = data.moduleName;
					}
					
					chartOptions.title.text = moduleName;
					chartOptions.yAxis.title.text = moduleName;

					chartOptions.xAxis.categories = data.categories;
					chartOptions.series = data.series;
					$.extend(true,chartOptions,lineOptions);//深度copy传入的参数
				    line=new Highcharts.Chart(chartOptions);
				});
			}else{
				var visualizeOptions = {
					isReverse:defaultOption.isReverse,
					legend: {
						align: 'left',
						x: 70,
						verticalAlign: 'top',
						y: -10,
						floating: true,
						backgroundColor: '#FFFFFF',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					}
				}
				$.extend(chartOptions,visualizeOptions);
				line = Highcharts.visualize(defaultOption.table, chartOptions);
			}
			self.data("chart",line);
			return line;
		}
	});
	
	/**
	 * Visualize an HTML table using Highcharts. The top (horizontal) header 
	 * is used for series names, and the left (vertical) header is used 
	 * for category names. This function is based on jQuery.
	 * @param {Object} table The reference to the HTML table to visualize
	 * @param {Object} options Highcharts options
	 */
	Highcharts.visualize = function(table, options) {
		
		var indexOfHeJi = -1;
		// the categories
		var tableId=$(table).attr("id").substring(5,$(table).attr("id").length);
		options.xAxis.categories = [];
		
		var arrayData = jQuery('#'+tableId+' tr.ui-row-ltr:visible').get();
		if(options.isReverse){
			arrayData.reverse();
		}
		
		$(arrayData).each( function(i) {
			var thisVal=$(this).find("td:visible").first().text();
			if(thisVal!=''){
				if(thisVal == '合计'){
					indexOfHeJi = i;
				}else{
					options.xAxis.categories.push(thisVal);
					indexOfHeJi = -1;
				}
			}
		});
		
		
		// the data series
		options.series = [];
		
		jQuery("#"+$(table).attr("id")+' .ui-jqgrid-htable .ui-th-ltr:visible').each( function(i) {
			var thisVal=$(this).find(".ui-jqgrid-sortable:visible").first().text();
			options.series[i-1] = { 
				name: thisVal,
				data: []
			};
		});
		
		$(arrayData).each( function(i) {
			var tr = this;
			if(indexOfHeJi != i){
				jQuery('td:visible',tr).each( function(j) {
					if (j > 0) { // skip first column
						options.series[j - 1].data.push(parseFloat(this.innerHTML));
					}
				});
			}
		});
		var origChartWidth = 120;//柱状图横坐标间隔
		if(options.xAxis.categories.length>13){
			var num = options.xAxis.categories.length;
			options.chart.width = origChartWidth*num;
		}
		var chart = new Highcharts.Chart(options);
		return chart;
	}
})(jQuery)