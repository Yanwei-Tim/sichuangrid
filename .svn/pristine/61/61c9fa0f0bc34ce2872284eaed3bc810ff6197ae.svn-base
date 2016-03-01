<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="${path}/resource/taskListDemoJs/codemirror.js"></script>
    <script src="${path}/resource/taskListDemoJs/javascript.js"></script>
</head>
<body>
	<div id="main" class="main" style="height: 450px;"></div>
    <script src="${path}/resource/taskListDemoJs/bootstrap.min.js"></script>
	 <script src="${path}/resource/taskListDemoJs/echarts.js"></script>
</body>
<script type="text/javascript">
	require.config({  
		paths: {  
			echarts:'${path}/resource/taskListDemoJs/js'  
		}  
	});  
	 
	require(  
		[  
			'echarts',  
			'echarts/chart/map'  
		],
		function (ec) {  
            var myChart=ec.init(document.getElementById('main'));
			var ecConfig = require('echarts/config');
			var mapType = [
			    '四川'
			];
			option = {
			    tooltip : {
			        trigger: 'item',
			        formatter: '{b}<br/>{c}'
			    },
			  
			    dataRange: {
			        min: 0,
			        max: 2100,
			        color:['#FF0000','#FF6600','#FFCC00','#CCCCFF','#66FF66'],
			        text:['高','低'],           // 文本，默认为数值文本
			        calculable : true
			    },
			    series : [
			        {
			            name: '',
			            type: 'map',
			            mapType: '四川',
			            selectedMode : 'single',
			            itemStyle:{
			                normal:{label:{show:true}},
			                emphasis:{label:{show:true}}
			            },
			            data:[
			                {name: '甘孜藏族自治州',value: 100},
			                {name: '阿坝藏族羌族自治州',value:200},
			                {name: '凉山彝族自治州',value: 300},
			                {name: '绵阳市',value: 400},
			                {name: '达州市',value: 500},
			                {name: '广元市',value: 600},
			                {name: '雅安市',value: 700},
			                {name: '宜宾市',value: 800},
			                {name: '乐山市',value: 900},
			                {name: '南充市',value: 1000},
			                {name: '巴中市',value: 1100},
			                {name: '泸州市',value: 1200},
			                {name: '成都市',value: 1300},
			                {name: '资阳市',value: 1400},
			                {name: '攀枝花市',value: 1500},
			                {name: '眉山市',value: 1600},
			                {name: '广安市',value: 1700},
			                {name: '德阳市',value: 1800},
			                {name: '内江市',value: 1900},
			                {name: '遂宁市',value: 2000},
			                {name: '自贡市',value: 2100}
			            ]
			        }
			    ]
			};
			myChart.setOption(option);  
		}
	);

	//任务类别点击事件
	function clickPropertyDicts(){
		$("[name='selectTypesByMap']").click(function(){
			require(  
					[  
						'echarts',  
						'echarts/chart/map'  
					],
					function (ec) {  
			            var myChart=ec.init(document.getElementById('main'));
						var ecConfig = require('echarts/config');
						var mapType = [
						    '四川'
						];
						option = {
						    tooltip : {
						        trigger: 'item',
						        formatter: '{b}<br/>{c}'
						    },
						  
						    dataRange: {
						        min: 0,
						        max: 2100,
						        color:['#FF0000','#FF6600','#FFCC00','#CCCCFF','#66FF66'],
						        text:['高','低'],           // 文本，默认为数值文本
						        calculable : true
						    },
						    series : [
						        {
						            name: '',
						            type: 'map',
						            mapType: '四川',
						            selectedMode : 'single',
						            itemStyle:{
						                normal:{label:{show:true}},
						                emphasis:{label:{show:true}}
						            },
						            
			data:[
							                {name: '甘孜藏族自治州',value: Math.round(Math.random()*2100)},
							                {name: '阿坝藏族羌族自治州',value:Math.round(Math.random()*2100)},
							                {name: '凉山彝族自治州',value: Math.round(Math.random()*2100)},
							                {name: '绵阳市',value: Math.round(Math.random()*2100)},
							                {name: '达州市',value: Math.round(Math.random()*2100)},
							                {name: '广元市',value: Math.round(Math.random()*2100)},
							                {name: '雅安市',value: Math.round(Math.random()*2100)},
							                {name: '宜宾市',value: Math.round(Math.random()*2100)},
							                {name: '乐山市',value: Math.round(Math.random()*2100)},
							                {name: '南充市',value: Math.round(Math.random()*2100)},
							                {name: '巴中市',value: Math.round(Math.random()*2100)},
							                {name: '泸州市',value: Math.round(Math.random()*2100)},
							                {name: '成都市',value: Math.round(Math.random()*2100)},
							                {name: '资阳市',value: Math.round(Math.random()*2100)},
							                {name: '攀枝花市',value: Math.round(Math.random()*2100)},
							                {name: '眉山市',value: Math.round(Math.random()*2100)},
							                {name: '广安市',value: Math.round(Math.random()*2100)},
							                {name: '德阳市',value: Math.round(Math.random()*2100)},
							                {name: '内江市',value: Math.round(Math.random()*2100)},
							                {name: '遂宁市',value: Math.round(Math.random()*2100)},
							                {name: '自贡市',value: Math.round(Math.random()*2100)}
							            ]
						        }
						    ]
						};
						myChart.setOption(option);  
					}
				);



		});
	}

	//加载治安隐患任务类别
	function dealPropertyDicts(){
		$.ajax({
			async: false,
			type: "GET",
			url: "${path}/sysadmin/propertyManage/findPropertyDictByDomainName.action",
			data:{"propertyDomain.domainName":"治安隐患异常类型"},
			success:function(responseData){
				var bo = true;//判断循环第一次
				var checkValue = "";
				for(var i = 0;i<responseData.length;i++){
					if(bo){
						bo = false;
						checkValue += "<input name='selectTypesByMap' id='"+responseData[i].id+"'  value='"+responseData[i].id+"' type='radio' style='margin-left:12px;' checked='checked'/> "+responseData[i].displayName+"";
					}else{
						checkValue += "<input name='selectTypesByMap' id='"+responseData[i].id+"'  value='"+responseData[i].id+"' type='radio' class='myCheckBox'/> "+responseData[i].displayName+"";
					}
				}
				$("#taskTypeMap").append(checkValue); 
				clickPropertyDicts();
			}
		});
	}	
	$(document).ready(function(){
		dealPropertyDicts();
	});
	
	
</script>


</html>