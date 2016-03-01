<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<jsp:include page="/common/orgSelectedComponent.jsp"/>   

  
<div id="main" style="height: 500px; border: 1px solid #ccc; padding: 10px;"></div>

<script>
$(document).ready(function(){
	require.config({
        paths: {
            echarts: '/resource/judgmentAnalysis/js/echarts/build/dist'
        }
    });
	 require(
		        [
		            'echarts',
		            'echarts/chart/bar'
		        ],
		        function (ec) {
		            // 基于准备好的dom，初始化echarts图表
		        	var mapHeight = $(document).height()-120 ;
		        	$('#main').height(mapHeight);
		            myChart = ec.init($('#main')[0]); 
		            var option = {
				            	title : {
				                    text: '任务清单签收上报对比',
				                    subtext: '流动人口'
				                },
				                tooltip : {
				                    trigger: 'axis',
				                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				                    },
				                    formatter: function (params){
				                        return params[0].name + '<br/>'
				                               + params[0].seriesName + ' : ' + params[0].value + '<br/>'
				                               + params[1].seriesName + ' : ' + (params[1].value + params[0].value);
				                    }
				                },
				                legend: {
				                    selectedMode:false,
				                    data:['上报数', '未签收数']
				                },
				                toolbox: {
				                    show : true,
				                    feature : {
				                        saveAsImage : {show: true}
				                    }
				                },
				                calculable : true,
				                xAxis : [
				                    {
				                        type : 'category',
				                        data : ['成都市','广安市','广元市','乐山市','眉山市','自贡市']
				                    }
				                ],
				                yAxis : [
				                    {
				                        type : 'value',
				                        boundaryGap: [0, 0.1]
				                    }
				                ],
				                series : [
				                    {
				                        name:'上报数',
				                        type:'bar',
				                        stack: 'sum',
				                        barCategoryGap: '50%',
				                        itemStyle: {
				                            normal: {
				                                color: 'tomato',
				                                barBorderColor: 'tomato',
				                                barBorderWidth: 6,
				                                barBorderRadius:0,
				                                label : {
				                                    show: true, position: 'insideTop'
				                                }
				                            }
				                        },
				                        data:[260, 200, 220, 120, 100, 80]
				                    },
				                    {
				                        name:'未签收数',
				                        type:'bar',
				                        stack: 'sum',
				                        itemStyle: {
				                            normal: {
				                                color: '#fff',
				                                barBorderColor: 'tomato',
				                                barBorderWidth: 6,
				                                barBorderRadius:0,
				                                label : {
				                                    show: true, 
				                                    position: 'top',
				                                    formatter: function (params) {
				                                        for (var i = 0, l = option.xAxis[0].data.length; i < l; i++) {
				                                            if (option.xAxis[0].data[i] == params.name) {
				                                                return option.series[0].data[i] + params.value;
				                                            }
				                                        }
				                                    },
				                                    textStyle: {
				                                        color: 'tomato'
				                                    }
				                                }
				                            }
				                        },
				                        data:[40, 80, 50, 80,80, 70]
				                    }
				                ]
		            }
		            myChart.setOption(option);
		 });
		            
});
</script>