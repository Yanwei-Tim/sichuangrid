<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select name="iqueryYear" id="iqueryYear"  style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="iqueryMonth" id="iqueryMonth">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="isearch" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="idleyouthPie1" class="SigmaReport" style="height:400px;width:100%;"></div>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#iqueryYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#iqueryYear").val());
	$("#isNowMonth").val($("#iqueryMonth").val());
	onOrgChanged();
	$("#isearch").click(function(){
		onOrgChanged();
	});
	$("#iqueryYear").change(function(){
		$("#iqueryYear").empty();
		getmonth();
	});
	$.gridboxHeight();
});


function onOrgChanged(){
	$("#idleyouthPie1").pieChart({
		url:"${path}/baseInfo/statisticManageNew/getStatisticPie.action?orgId="+getCurrentOrgId()+"&year="+$("#iqueryYear").val()+"&month="+$("#iqueryMonth").val()+"&type=<@s.property value='#parameters.type'/>&domainName=政治面貌",
		moduleName:document.title
	});
}
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#iqueryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#iqueryMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
</script>