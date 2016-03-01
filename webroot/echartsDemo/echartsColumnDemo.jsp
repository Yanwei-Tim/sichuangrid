<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<jsp:include page="/common/orgSelectedComponent.jsp"/>   
<style type="text/css"> 
div.panel,p.flip
{
margin:0px;
padding:5px;
text-align:center;
background:#e5eecc;
border:solid 1px #c3c3c3;
}
div.panel
{
height:120px;
display:none;
}
</style>
<input type="button" value="条件设置" class="flip"/>
<div class="panel">
  <div class="dateSelect">
			<span class="taskTit">时间：</span>
			<select  id="timeColumn" class="mySelect">
				<option value="0">月</option>
				<option value="1">季度</option>
				<option value="2">年</option>
			</select>
			<span id="yearAndMonthColumn">
				<select id="yearColumn"  class="mySelect"></select> 
				<em class="taskTit">年 </em>
				<select id="monthColumn" class="mySelect" ></select>
			</span>
			<span id="yearAndQuarterColumn">
				<select id="yearQuarterColumn" class="mySelect">
				</select> 年 
				<select id="quarterColumn" class="mySelect"></select> 
			</span>
			<span id="yearAndyearTypeColumn">
				<select id="searchYearColumn"  class="mySelect">
				</select> 年 
				<select id="yearType" class="mySelect">
					<option value="0">上半年</option>
					<option value="1">下半年</option>
					<option value="2">全年</option>
				</select>
			</span>
		</div>
</div>
<div id="main" style="height: 500px; border: 1px solid #ccc; padding: 10px;"></div>
<script>
$(document).ready(function(){
	
	$(".flip").click(function(){
	    $(".panel").slideToggle("slow");
	  });
	
	 require.config({
	        paths: {
	            echarts: '/resource/judgmentAnalysis/js/echarts/build/dist'
	        }
	    });
	    
	    // 使用
	    require(
	        [
	            'echarts',
	            'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
	            'echarts/chart/bar'
	        ],
	        function (ec) {
	            // 基于准备好的dom，初始化echarts图表
	        	var mapHeight = $(document).height()-120 ;
	        	$('#main').height(mapHeight);
	            myChart = ec.init($('#main')[0]); 
	            myChart.setOption({
	        		title : {
	        	        text: '任务清单研判分析',
	        	        subtext: '流动人口'
	        	    },
	        	    tooltip : {
	        	        trigger: 'axis'
	        	    },
	        	    legend: {
	        	        data:['签收数','上报数']
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
	        	            data : ['成都市','广安市','德阳市','遂宁市','广元市','乐山市','雅安市','自贡市','眉山市','峨眉山市','巴中市','资阳市','阿坝彝族']
	        	        }
	        	    ],
	        	    yAxis : [
	        	        {
	        	            type : 'value'
	        	        }
	        	    ],
	        	    series : [
	        	        {
	        	            name:'签收数',
	        	            type:'bar',
	        	            data:[12, 23, 11, 22, 13, 56, 23, 13, 54, 21, 23, 23,12],
	        	            markPoint : {
	        	                data : [
	        	                    {type : 'max', name: '最大值'},
	        	                    {type : 'min', name: '最小值'}
	        	                ]
	        	            },
	        	            markLine : {
	        	                data : [
	        	                    {type : 'average', name: '平均值'}
	        	                ]
	        	            }
	        	        },
	        	        {
	        	            name:'上报数',
	        	            type:'bar',
	        	            data:[12, 22, 13, 23, 12, 44, 34, 23, 43, 23, 12, 43,21],
	        	            markPoint : {
	        	            	 data : [
	 	        	                    {type : 'max', name: '最大值'},
	 	        	                    {type : 'min', name: '最小值'}
	 	        	                ]
	        	            },
	        	            markLine : {
	        	                data : [
	        	                    {type : 'average', name : '平均值'}
	        	                ]
	        	            }
	        	        }
	        	    ]
	        		
	        	});
	            
	        });
});
</script>