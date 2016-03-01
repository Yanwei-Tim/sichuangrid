<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div>
			<label class="form-lbl">年份：</label>
			<select id="yearSelectOptions" style="width:100px;"></select> 
			<label class="form-lbl">月份：</label>
			<select id="monthSelectOptions" style="width:100px;"></select>
			<a id="statistic" href="javascript:void(0)"><span>统计</span></a>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="statementStatisticList"></table>
		<div id="statementStatisticListPager"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#statementStatisticList").jqGridFunction({
		datatype:"local",
		sortname:"orgId",
		sortorder:"asc",
		colModel:[
	    	{name:"id", index:"id", hidden:true},
	    	{name:"statementNo", index:"statementNo", label:"序号", width:50},
	    	{name:"organization.orgName", index:"orgInternalCode", width:120, label:"单位"},
	    	{name:"statementTotal", index:"statementTotal", width:60, label:"累计办件"},
	    	{name:"currentPeriodRegister", index:"currentPeriodRegister", width:60, label:"当期立案"},
	    	{name:"registerTotal", index:"registerTotal", width:60, label:"累计立案"},
	    	{name:"overdueNotAccepted", index:"overdueNotAccepted", width:65, label:"逾期未受理"},
	    	{name:"normalNotAccepted", index:"normalNotAccepted", width:65, label:"正常未受理"},
	    	{name:"sevenDaysInDeal", index:"sevenDaysInDeal", width:65, label:"七天在办"},
	    	{name:"overtakeSevenDaysInDeal", index:"overtakeSevenDaysInDeal", width:65, label:"超七天在办"},
	    	{name:"onScheduleHandle", index:"onScheduleHandle", width:60, label:"按期办结"},
	    	{name:"overdueHandle", index:"overdueHandle", width:60, label:"超期办结"},
	    	{name:"transferTotal", index:"transferTotal", width:60, label:"累计移交"},
	    	{name:"endTotal", index:"endTotal", width:65, label:"累计结案数"},
	    	{name:"onScheduleRateOfEnd", index:"onScheduleRateOfEnd", width:65, label:"按期结案率"},
	    	{name:"rateOfEnding", index:"rateOfEnding", width:60, label:"结案率"}
	  	]
	});

	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged();
		getYearsByOrgId();
	}

	$("#statistic").click(function(event){
		onOrgChanged();
	});

	$("#yearSelectOptions").change(function(){
		getMonthsByOrgId($("#yearSelectOptions").val());
	});
});

function onOrgChanged(){
	$("#statementStatisticList").setGridParam({
		url:'${path}/supervisionEvaluation/statementStatisticManage/findStatementStatisticsForPageByOrgInternalCode.action',
		datatype:'json'
	});
	$("#statementStatisticList").setPostData({
		"organization.id":getCurrentOrgId(),
		"year":$("#yearSelectOptions").val() == null || $("#yearSelectOptions").val() == "" ? 0 : $("#yearSelectOptions").val(),
		"month":$("#monthSelectOptions").val() == null || $("#monthSelectOptions").val() == "" ? 0 : $("#monthSelectOptions").val(),
		"page":1
	});
	$("#statementStatisticList").trigger("reloadGrid");
}

function getYearsByOrgId(){
	$.ajax({
		async:false,
		url:"${path}/supervisionEvaluation/statementStatisticManage/getYearsByOrgId.action",
		//data:{
		//	"organization.id":getCurrentOrgId()
		//},
		success:function(responseData){
			$("#yearSelectOptions option").remove();
			$("#yearSelectOptions").append('<option value=""></option>');
			$.each(responseData, function(i) { 
				$("#yearSelectOptions").append('<option value='+responseData[i]+'>'+responseData[i]+'</option>');
			}); 
		}
	});
}

function getMonthsByOrgId(year){
	$.ajax({
		async:false,
		url:"${path}/supervisionEvaluation/statementStatisticManage/getMonthsByOrgId.action",
		data:{
			"organization.id":getCurrentOrgId(),
			"year":year
		},
		success:function(responseData){
			$("#monthSelectOptions option").remove();
			$("#monthSelectOptions").append('<option value=""></option>');
			$.each(responseData, function(i) { 
				$("#monthSelectOptions").append('<option value='+responseData[i]+'>'+responseData[i]+'</option>');
			}); 
		}
	});
}
</script>