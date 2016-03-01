<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="taskListinfoTrend" class="SigmaReport" style="height:300px;width:100%;"></div>
<script type="text/javascript">
var parametersType = "<@s.property value='#parameters.type'/>";
function commonStatisticsOnchangeTrend(){
		var selectTypes;//所选择的任务清单上报类型
		var searchDateType=0;//查询类别(月、季度、年)
		var isSegin;//签收还是上报
		var year;
		var month;
		var title;
		var url;
		var situationType = 2;//默认是流动人口下的详细类别
		 $('input:checkbox[name=selectTypes]:checked').each(function(i){
		   if($(this).val() == "pandect"){
		 		if(parametersType == "治安隐患异常类型"){
					selectTypes = "f_hiddendanger";
				}else if(parametersType == "民警带领下工作内容"){
					selectTypes = "b_workingSituation";
				}else{
					selectTypes = "c_exceptionalSituationRecord";
				}
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
				$.messageBox({level:'warn',message:"任务状态只能选择一个"});
				return;
		}else if($('#report').attr('checked')){
			isSegin = 0;
			title = "上报统计";
		}else{
			isSegin = 1;
			title = "签收统计";
		}
		$("#taskListinfoTrend").lineChart({
		url: "${(path)!}/plugin/statistics/generalSituationManage/getTaskListOfTrend.action?taskListStatisticsVo.situationType="+situationType+"&taskListStatisticsVo.propertyDomainName=<@s.property value='#parameters.type'/>&taskListStatisticsVo.selectTypes="+selectTypes+"&taskListStatisticsVo.searchDateType="+searchDateType+"&taskListStatisticsVo.isSegin="+isSegin+"&taskListStatisticsVo.year="+year+"&taskListStatisticsVo.month="+month+"&taskListStatisticsVo.orgId="+getCurrentOrgId(),
		moduleName:"<@s.property value='#parameters.type'/>线性统计"
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
	commonStatisticsOnchangeTrend();
});
</script>