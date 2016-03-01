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
		            'echarts/chart/pie'
		        ],
		        function (ec) {
		            // 基于准备好的dom，初始化echarts图表
		        	var mapHeight = $(document).height()-120 ;
		        	$('#main').height(mapHeight);
		            myChart = ec.init($('#main')[0]); 
		            var option = {
		            		title : {
		            	        text: '任务清单研判统计',
		            	        subtext: '发现治安隐患',
		            	        x:'center'
		            	    },
		            	    tooltip : {
		            	        trigger: 'item',
		            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		            	    },
		            	    legend: {
		            	        orient : 'vertical',
		            	        x : 'left',
		            	        data:['涉黄','涉毒','涉赌','暴力事件','纠纷事件']
		            	    },
		            	    toolbox: {
		            	        show : true,
		            	        feature : {
		            	            saveAsImage : {show: true}
		            	        }
		            	    },
		            	    calculable : true,
		            	    series : [
		            	        {
		            	            name:'治安隐患类别',
		            	            type:'pie',
		            	            radius : '55%',
		            	            center: ['50%', '60%'],
		            	            data:[
		            	                {value:1, name:'涉黄'},
		            	                {value:2, name:'涉毒'},
		            	                {value:3, name:'涉赌'},
		            	                {value:4, name:'暴力事件'},
		            	                {value:5, name:'纠纷事件'}
		            	            ]
		            	        }
		            	    ]
		            }
		            myChart.setOption(option);
		 });
		            
});
</script>