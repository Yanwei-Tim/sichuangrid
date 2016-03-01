(function($){
	$.fn.extend({
		pieMapChart : function(option,pieChartOption){
			var bol='';
			Highcharts.theme = {
				colors: ['#0965b8', '#0D8ECF', '#009A61', '#2DB200', '#798E2F', '#FF8000', '#FF9900', '#FF6600', '#C926FF', '#6D00D9', '#D90000', '#8C0000','#8A8A8A']
			};
			Highcharts.setOptions(Highcharts.theme);
			
			var defaultOption = {
				url:option.url,
				moduleName:option.moduleName,
				renderTo: $(this).attr('id'),
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        msgInfo:"",
				onClick:function(event){},
				marginTop:50,
				marginBottom:30
			};

			$.extend(defaultOption, option);
			var self=$(this);
			var chart = '';
			var msg_ = option.msgInfo;
			var chartOptions = {
				chart: defaultOption,
			    title: {
			        text: '',
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
					//模拟数据
					var data = data;//[["Firefox",45.0],["IE",26.8],["Safari",8.5],{"name": "Chrome","y": 12.8,"sliced": true,"selected": true},["Opera",6.2],["Others",0.7]]  
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
})(jQuery)