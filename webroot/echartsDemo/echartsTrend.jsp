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
		            'echarts/chart/line',
		            'echarts/chart/bar'
		        ],
		        function (ec) {
		            // 基于准备好的dom，初始化echarts图表
		        	var mapHeight = $(document).height()-120 ;
		        	$('#main').height(mapHeight);
		            myChart = ec.init($('#main')[0]); 
		            var option = {
		            		tooltip : {
		            	        trigger: 'axis'
		            	    },
		            	    legend: {
		            	        data:['任务清单签收数据',]
		            	    },
		            	    toolbox: {
		            	        show : true,
		            	        feature : {
		            	            magicType : {show: true, type: ['line', 'bar']},
		            	            saveAsImage : {show: true}
		            	        }
		            	    },
		            	    calculable : true,
		            	    xAxis : [
		            	        {
		            	            type : 'category',
		            	            boundaryGap : false,
		            	            data : ['2015-01','2015-02','2015-03','2015-04','2015-05','2015-06','2015-07','2015-08','2015-09','2015-10','2015-11','2015-12']
		            	        }
		            	    ],
		            	    yAxis : [
		            	        {
		            	            type : 'value'
		            	        }
		            	    ],
		            	    series : [
		            	        {
		            	            name:'任务清单签收数据',
		            	            type:'line',
		            	            stack: '总量',
		            	            data:[120, 132, 101, 134, 90, 230, 210,220,110,98,211,90]
		            	        }
		            	    ]
		            }
		            myChart.setOption(option);
		 });
		            
});
</script>