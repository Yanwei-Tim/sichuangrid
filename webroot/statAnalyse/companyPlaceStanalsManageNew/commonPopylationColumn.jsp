<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	request.setAttribute("moduleType",request.getParameter("moduleType"));
%>
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
	$("#csearch").click(function(){
		onOrgChanged();
	});
	$("#cyear").change(function(){
		$("#cmonth").empty();
		getmonth();
	});
	
	$.gridboxHeight();
	onOrgChanged();
	
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
		url: "/baseInfoStat/companyPlaceStanalsManageNew/getStatisticsCompanyPlaceColumn.action?moduleType=${moduleType}&orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		moduleName:document.title,
		textx:-150,
		quantity:'个数',
		isShowSum:'false'
	});
	enableOrDisableColumn(1);
}

</script>