<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<style>
#highcharts-18{
height: 315px;
}
</style>
<div id="taskListinfoColumn" class="SigmaReport" style="width:100%;overflow:auto!important;margin-left:50px;"></div>
<script type="text/javascript">

function commonStatisticsOnchange(){
var selectTypes;//所选择的任务清单上报类型
		var searchDateType;//查询类别(月、季度、年)
		var isSegin;//签收还是上报
		var year;
		var month;
		var quarter;
		var title;
		var url;
		 $('input:checkbox[name=selectTypes]:checked').each(function(i){
		 
	       if(0==i){
	        	selectTypes = $(this).val();
	       }else{
	        	selectTypes += (","+$(this).val());
	       }
      	});
		
		searchDateType = $("#timeColumn").val();
		if(searchDateType==0){
			year = $("#yearColumn").val();
		}
		if(searchDateType==1){
			year = $("#yearQuarterColumn").val();
		}
		if(searchDateType==2){
			year = $("#searchYearColumn").val();
		}
		month = $("#monthColumn").val();
		quarter = $("#quarterColumn").val();
		yearType = $("#yearType").val();
		
		if($('#report').attr('checked') && $('#sign').attr('checked')){
			if(selectTypes.split(",").length>1 || selectTypes=='pandect'){
				$.messageBox({level:'warn',message:"选择多个任务类别或总览无法同时选择任务状态！"});
				return;
			}
			isSegin = 2;
			title = "上报签收对比";
		}else if($('#report').attr('checked')){
			isSegin = 0;
			title = "上报统计";
		}else{
			isSegin = 1;
			title = "签收统计";
		}
		$("#taskListinfoColumn").columnChartOfTaskList({
			url: "${path}/plugin/statistics/generalSituationManage/getTaskListOfSubTypeColumn.action?taskListStatisticsVo.propertyDomainName=<@s.property value='#parameters.type'/>&taskListStatisticsVo.selectTypes="+selectTypes+"&taskListStatisticsVo.searchDateType="+searchDateType+"&taskListStatisticsVo.isSegin="+isSegin+"&taskListStatisticsVo.year="+year+"&taskListStatisticsVo.month="+month+"&taskListStatisticsVo.quarter="+quarter+"&taskListStatisticsVo.yearType="+yearType+"&taskListStatisticsVo.orgId="+getCurrentOrgId(),
			moduleName:title,
			textx:-150,
			height: document.documentElement.offsetHeight - ($.browser.msie ? 240 : 350),
			quantity:'条数'
		});
}
$(document).ready(function(){
	$("#content").show();
	$("#timeColumn").show();
	commonStatisticsOnchange();
	
	var sHeight = $(".ui-layout-center").height()-$("#nav:visible").height()-$(".taskSearchCondition").height()-$(".ui-tabs-nav").height()-136;
	$("#taskListinfoColumn,.highcharts-container").height(sHeight);
	
});



</script>