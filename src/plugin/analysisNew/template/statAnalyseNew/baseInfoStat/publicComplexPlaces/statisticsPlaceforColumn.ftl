<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<input name="keyType" id = "keyType" value="<@s.property value="#parameters.keyTpe[0]"/>" type="hidden"></input>

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="cyear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;"  id="cmonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="csearch" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="place" class="SigmaReport"></div>

<script type="text/javascript">

$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
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
	onOrgChanged();
});

function onOrgChanged(){
	var keyType=$("#keyType").val();
	var chartCount = $("#place").columnChart({
		url: "${(path)!}/baseinfo/statAnalysePlace/findStatAnalysePlaceForHighchartColumnVo.action?orgId="+getCurrentOrgId()+"&keyType="+keyType+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		quantity:'个数'	
	});
	enableOrDisableColumn(2);
}

function getmonth(){
	$.ajax({
		async: false,
		url: "${(path)!}/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

</script>