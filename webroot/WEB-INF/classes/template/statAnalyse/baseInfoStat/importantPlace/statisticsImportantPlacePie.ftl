<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="month" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="importantPlacePie" class="SigmaReport" style="height:400px;width:100%;"></div>
<script type="text/javascript">

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#year").val());
	$("#isNowMonth").val($("#month").val());
	onOrgChanged();
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$.gridboxHeight();
});

function onOrgChanged(){
	$("#importantPlacePie").pieChart({
		url:"${path}/baseInfoStat/statisticsPlace/getStatisticsImportantPlacePie.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&typeTableName=IMPORTANTPLACE",
		moduleName:"重点场所"/*,
		onClick:function(event){
			setOptionsWhenShowInfo(event.point.name,getCurrentOrgId());
			showInfo(url, title, 900, 600,$("#year").val(),$("#month").val());
		}*/
	});
	enableOrDisableColumn(1);
}


</script>