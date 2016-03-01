<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="taskListinfoTrend" class="SigmaReport" ></div>
<script type="text/javascript">
function getTaskListColumnTrend(){
		var selectTypes;//所选择的任务清单上报类型
		var searchDateType=0;//查询类别(月、季度、年)
		var isSegin;//签收还是上报
		var year;
		var month;
		var title;
		 $('input:checkbox[name=checkNameColumn]:checked').each(function(i){
		   if($(this).val() == "pandect"){
		   		selectTypes = "a_floating";
		   		return;
		   }
	       if(0==i){
	        	selectTypes = $(this).val();
	       }else{
	        	selectTypes += (","+$(this).val());
	       }
      	});
		
		year = $("#yearColumn").val();
		month = $("#monthColumn").val();
		
		if($('#report').attr('checked') && $('#sign').attr('checked')){
			if(selectTypes.split(",").length>1 || selectTypes=='pandect'){
				$.messageBox({level:'warn',message:"选择多个任务类别或总览无法同时选择任务状态！"});
				return;
			}
			isSegin = 2;
			title = "任务清单上报签收对比";
		}else if($('#report').attr('checked')){
			isSegin = 0;
			title = "任务清单上报统计";
		}else{
			isSegin = 1;
			title = "任务清单签收统计";
		}
		$("#taskListinfoTrend").lineChart({
		url: "${(path)!}/plugin/statistics/generalSituationManage/getTaskListOfTrend.action?taskListStatisticsVo.situationType=1&taskListStatisticsVo.selectTypes="+selectTypes+"&taskListStatisticsVo.searchDateType="+searchDateType+"&taskListStatisticsVo.isSegin="+isSegin+"&taskListStatisticsVo.year="+year+"&taskListStatisticsVo.month="+month+"&taskListStatisticsVo.orgId="+getCurrentOrgId(),
		moduleName:"流动人口线性统计"
		});
}

$(document).ready(function(){
	$("#content").show();
	$("#timeColumn").hide();
	if($("#timeColumn").val()!=0){
		$("#timeColumn option[value=0]").attr("selected",true);
		$("#monthColumn").empty();
		getYear();
		$("#yearAndMonthColumn").show();
		$("#yearAndQuarterColumn").hide();
		$("#yearAndyearTypeColumn").hide();
	}
	getTaskListColumnTrend();
});

</script>