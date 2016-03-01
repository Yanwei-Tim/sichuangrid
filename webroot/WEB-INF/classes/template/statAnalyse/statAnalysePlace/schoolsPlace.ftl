<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />


<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
$(document).ready(function() {
	onOrgChanged();
});

function onOrgChanged(){
	$.ajax({
		async: false,
		url: "${path}/baseinfo/statAnalysePlace/findStatAnalysePlaceForHighchartColumnVo.action?orgId="+getCurrentOrgId(),
		type:"json",
		success:function(data){
			new Highcharts.Chart(initData(data));
		}
	});
}

function initData(data){
	return {
			chart: {
			renderTo: 'container',
			defaultSeriesType: 'column'
		},
		title: {
			text: data.moduleName+'柱状图'
		},
		xAxis: {
			categories:data.categories
		},
		yAxis: {
			min: 0,
			title: {
				text: data.moduleName
			}
		},
		legend: {
			align: 'right',
			x: -100,
			verticalAlign: 'top',
			y: 20,
			floating: true,
			backgroundColor: '#FFFFFF',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			formatter: function() {
				return '<b>'+ this.x +'</b><br/>'+
					 this.series.name +': '+ this.y +'<br/>'+
					 data.moduleName+'总数量: '+ this.point.stackTotal;
			}
		},
		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},
	    series: data.series
	}
}
</script>