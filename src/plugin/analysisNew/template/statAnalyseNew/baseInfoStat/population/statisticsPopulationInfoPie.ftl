<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="queryYear"  style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="queryMonth">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="positiveinfoPie" class="SigmaReport" style="height:400px;width:100%;"></div>

<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#queryYear").val());
	$("#isNowMonth").val($("#queryMonth").val());
	onOrgChanged();
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#queryYear").change(function(){
		$("#queryMonth").empty();
		getmonth();
	});
	$.gridboxHeight();
});


function onOrgChanged(){
	$("#positiveinfoPie").pieChart({
		url:"${path}/baseInfoStat/statisticsPopulationNew/getStatisticsPopulationPie.action?orgId="+getCurrentOrgId()+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&populationType=<@s.property value='#parameters.populationType'/>",
		moduleName:document.title
		/*,
		onClick:function(event){
			setOptionsWhenShowInfo(event.point.name,getCurrentOrgId());
			showInfo(url, title, width, height,$("#queryYear").val(),$("#queryMonth").val());
		}*/
	});
	enableOrDisableColumn(1);
}
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#queryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
</script>