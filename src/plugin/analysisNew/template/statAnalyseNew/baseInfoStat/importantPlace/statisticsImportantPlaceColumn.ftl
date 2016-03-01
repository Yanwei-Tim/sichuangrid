<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="cyear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;"  id="cmonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="csearch" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="importantPlaceColumn" class="SigmaReport"></div>
<script type="text/javascript">
var url = '';
var title = '';
var width = 900;
var height = 500;

$(function() {
	$.ajax({
		async: false,
		url: "/stat/currentTime/getCurrentTimeForYear.action",
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
	$.loadingComp("close");
});

function onOrgChanged(){
	var chartCount=$("#importantPlaceColumn").columnChart({
		url: "/baseInfoStat/statisticsPlace/getStatisticsImportantPlaceColumn.action?orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val()+"&typeTableName=IMPORTANTPLACE",
		moduleName:"重点场所区域分布图",
		quantity:'个数'	
	});
	enableOrDisableColumn(1);
}

function getmonth(){
	$.ajax({
		async: false,
		url: "/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}


function setOptionsWhenShowInfo(name, orgId){
	if(name.indexOf("安全生产重点") != -1){
		title='安全生产重点';
		url = '${(path)!}/baseinfo/siteinfo/enterprise/enterpriseListForStatistics.jsp?isSearch=1&keyType=safetyProductionKey&orgIdForStat='+orgId;
	}else if(name.indexOf("消防安全重点") != -1){
		title='消防安全重点';
		url = '${(path)!}/baseinfo/siteinfo/fireSafety/fireSafetyEnterpriseListForStatistics.jsp?isSearch=1&keyType=fireSafetyKey&orgIdForStat='+orgId;
	}else if(name.indexOf("治安重点") != -1){
		title='治安重点';
		url = '${(path)!}/baseinfo/siteinfo/enterprise/securityEnterpriseListForStatistics.jsp?isSearch=1&keyType=securityKey&orgIdForStat='+orgId;
	}else if(name.indexOf("学校") != -1){
		title='学校';
		url = '${(path)!}/baseinfo/siteinfo/school/schoolListForStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}else if(name.indexOf("其他场所") != -1){
		title='其他场所';
		url = '${(path)!}/baseinfo/siteinfo/otherLocale/otherLocaleListForStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}
}


</script>