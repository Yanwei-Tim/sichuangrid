<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="cyear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;"  id="cmonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="csearch" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="SimplyColumn" class="SigmaReport"></div>

<script type="text/javascript">


$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#cyear").val());
	$("#isNowMonth").val($("#cmonth").val());
	onOrgChanged();
	$("#csearch").click(function(){
		onOrgChanged();
	});
	$("#cyear").change(function(){
		$("#cmonth").empty();
		getmonth();
	});
	
	$.gridboxHeight();
	
});

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
function onOrgChanged(){
	var chartCount=$("#SimplyColumn").columnChart({
		url: "/baseInfoStat/statisticsPopulationNew/getStatisticsPopulationColumn.action?populationType=<@s.property value='#parameters.populationType[0]'/>&orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		moduleName:document.title,
		height: $.browser.msie ? document.documentElement.offsetHeight - 240 : null,
		quantity:'个数',
		isShowSum:isShowSum("<@s.property value='#parameters.populationType[0]'/>")
	});
	if('<@s.property value='#parameters.populationType[0]'/>'=='' || 'householdStaff'=='<@s.property value='#parameters.populationType[0]'/>')
		enableOrDisableColumn(1);
	else
		enableOrDisableColumn(2);
}

function isShowSum(populationType){
	if(populationType=='' || populationType=='householdStaff' || populationType == 'floatingPopulation' || populationType=='unsettledPopulation' || populationType=='overseaStaff'){
		return 'false';
	}else{
		return 'true';
	}
}
</script>