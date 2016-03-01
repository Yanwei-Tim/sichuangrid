<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="taskListinfoPie" class="SigmaReport" style="height:300px;width:100%;"></div>
<script type="text/javascript">

function commonStatisticsOnchangePie(){
		statusClick();
		var selectTypes;//所选择的任务清单上报类型
		var searchDateType;//查询类别(月、季度、年)
		var isSegin;//签收还是上报
		var year;
		var month;
		var quarter;
		var yearType;
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
			$.messageBox({level:'warn',message:"选择多个任务类别或总览无法同时选择任务状态！"});
			return;
		}else if($('#report').attr('checked')){
			isSegin = 0;
			title = "上报统计";
		}else{
			isSegin = 1;
			title = "签收统计";
		}
		$("#taskListinfoPie").pieChart({
		url: "${path}/plugin/statistics/generalSituationManage/getTaskListOfSubTypePie.action?taskListStatisticsVo.propertyDomainName=<@s.property value='#parameters.type'/>&taskListStatisticsVo.selectTypes="+selectTypes+"&taskListStatisticsVo.searchDateType="+searchDateType+"&taskListStatisticsVo.isSegin="+isSegin+"&taskListStatisticsVo.year="+year+"&taskListStatisticsVo.month="+month+"&taskListStatisticsVo.quarter="+quarter+"&taskListStatisticsVo.yearType="+yearType+"&taskListStatisticsVo.orgId="+getCurrentOrgId(),
		moduleName:"<@s.property value='#parameters.type'/>"
	});
}


$(document).ready(function(){
	$("#content").show();
	$("#timeColumn").show();
	commonStatisticsOnchangePie();
});
</script>