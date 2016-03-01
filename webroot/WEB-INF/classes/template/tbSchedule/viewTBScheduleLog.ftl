<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
	<div class="grid_4 lable-right"><label class="form-lbl">任务名： </label></div>
	<div class="grid_20"><@s.property value="tbSchedule.jobName"/></div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right"><label class="form-lbl">描述： </label></div>
	<div class="grid_20"><@s.property value="tbSchedule.remark"/></div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right"><label class="form-lbl">开始时间： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.startTime"/></div>
	<div class="grid_4 lable-right"><label class="form-lbl">结束时间： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.endTime"/></div>
	<!-- <div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right"><label class="form-lbl">运行所在服务器： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.appName"/></div>
	<div class="grid_4 lable-right"><label class="form-lbl">任务参数： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.taskParameter"/></div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right"><label class="form-lbl">任务项总个数： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.taskItemNum"/></div>
	<div class="grid_4 lable-right"><label class="form-lbl">分配到任务项： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.taskItems"/></div> -->
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right"><label class="form-lbl">本次执行记录数： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.listSize"/></div>
	<div class="grid_4 lable-right"><label class="form-lbl">每次获取数据量： </label></div>
	<div class="grid_8"><@s.property value="tbSchedule.eachFetchDataNum"/></div>
	<div class='clearLine'>&nbsp;</div>
</div>	
<div style="width: 100%;">
	<table id="exceptionList"></table>
	<div id="exceptionListPager"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#exceptionList").jqGridFunction({
		url:PATH + '/tbScheduleLog/exceptionList.action?tbSchedule.id=${tbSchedule.id}',
		datatype: "json",
		height:'320',
	   	colModel:[
		       {name:'errorTime', label:"异常时间", width: 120, align: 'center'},
		       {name:'errorMsg', label:"异常信息", width: 630, align: 'lefte'}
	   	]
	});
});
</script>