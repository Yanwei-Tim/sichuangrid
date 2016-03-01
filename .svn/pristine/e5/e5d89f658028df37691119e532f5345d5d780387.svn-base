<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content">
	<div class="ui-corner-all groupNav" id="nav">
		<a id="reloadTBScheduleLog" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="tbScheduleLogList"></table>
		<div id="tbScheduleLogListPager"></div>
	</div>
	<div id="tbScheduleLogErrorDialog"></div>
</div>
<script type="text/javascript">
function reloadTBScheduleLog(){
	$("#tbScheduleLogList").setGridParam({
		url:"${path}/tbScheduleLog/list.action",
		datatype: "json",
		page:1
	});
	$("#tbScheduleLogList").setPostData({
   	});
	$("#tbScheduleLogList").trigger("reloadGrid");
}
$(document).ready(function(){
	$("#tbScheduleLogList").jqGridFunction({
		datatype: "local",
		sortname: 'starttime',
		sotorder: 'desc',
	   	colModel:[
		       {name:"id", index: 'id', hidden: true},
		       {label:"操作", width: 60, align: 'center', formatter: function(val, evn, rows){ return '<a href="javascript:viewTBScheduleLogError('+rows.id+')">详情</a>';}},
		       {name:"jobName", label:"job名称", width: 280},
		       {name:'startTime', label:"开始时间", width: 120, align: 'center'},
		       {name:'endTime', label:"结束时间", width: 120, align: 'center'},
		       {label:"耗时", width: 80, align: 'center', formatter: useTimeFormatter},
		       {name:'remark', label:"描述", width: 200},
		       {name:'exceptionNum', label:"异常数", width: 60, align: 'center'},
		       {name:'listSize', label:"执行数", width: 60, align: 'center'},
		       {name:'appName', label:"服务器", width: 50, align: 'center'},
		       {name:'taskParameter', label:"参数", width: 100, align: 'center'},
		       //{name:'ownSign', label:"所属者", width: 60, align: 'center'},
		       {name:'taskItemNum', label:"任务项总个数", width: 80, align: 'center'},
		       {name:'taskItems', label:"任务项", width: 120, align: 'center'},
		       {name:'eachFetchDataNum', label:"获取量", width: 80, align: 'center'}
	   	],
		height:$(".ui-layout-center").height()-170
	});
	reloadTBScheduleLog();
	$("#reloadTBScheduleLog").click(function(){
		reloadTBScheduleLog();
	});
});

function useTimeFormatter(val, evn, rows){
	console.log(getDateTimeFromStr(rows.startTime));
	return rows.endTime == '' || rows.endTime == null ? '' : dealUseTime(getDateTimeFromStr(rows.endTime) - getDateTimeFromStr(rows.startTime));
}

function dealUseTime(useTime){
	useTime = useTime / 1000;
	if(useTime < 60){
		return useTime.toFixed(0) + ' 秒';
	} else if(useTime < 60 * 60){
		return parseInt(useTime/60) + ' 分 ' + ((useTime%60).toFixed(0) == 0 ? '' : (useTime%60).toFixed(0) + ' 秒');
	} else if(useTime < 60 * 60 * 24){
		return parseInt(useTime / (60 * 60)) + ' 小时 ' + ((useTime % (60 * 60) / 60).toFixed(0) == 0 ? '' : (useTime % (60 * 60) / 60).toFixed(0) + ' 分');
	} else if(useTime >= 60 * 60 * 24){
		return parseInt(useTime / (60 * 60 * 24)) + ' 天 ' + ((useTime % (60 * 60 * 24) / 60 / 60 ).toFixed(0) == 0 ? '' : (useTime % (60 * 60 * 24) / 60 / 60).toFixed(0) + ' 小时');
	} else{
		return '超时';
	}
}

function getDateTimeFromStr(dateStr){
	var dateTime = dateStr.split(' ');
	var date = dateTime[0].split('-');
	var time = dateTime[1].split(':');
	var dt = new Date();
	dt.setYear(date[0]);
	dt.setMonth(date[1]);
	dt.setDate(date[2]);
	dt.setHours(time[0]);
	dt.setMinutes(time[1]);
	dt.setSeconds(time[2]);
	return dt.getTime();
}

function viewTBScheduleLogError(id){
	$("#tbScheduleLogErrorDialog").createDialog({
		width: 800,
		height: 600,
		title:'任务错误信息 ',
		url:'${path}/tbScheduleLog/view.action?tbSchedule.id='+ id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
</script>