<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<link rel="stylesheet" href="${resource_path}/resource/external/tipswindown/tipswindown.css" type="text/css" media="all" />
<script type="text/javascript" src="${resource_path}/resource/external/tipswindown/tipswindown.js"></script>
<div id="nav" class="ui-corner-all">
		<input type="hidden" id="orgType" name="orgType" value="${orgType}"/>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
		<pop:JugePermissionTag ename="exportIssueDealStat">
			<a id="export" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
</div>

<div class="content" style="overflow:hidden;overflow-y:auto;position:relative;">
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="issueDealStatList"> </table>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#issueDealStatList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"org.id", key:true,hidden:true},
	        {name:'org.orgName',sortable:false,label:'名称',width:220},
	        {name:'monthNomalDealingCount', sortable:false, label:'本月正常在办',width:100},
	        {name:'monthOvertimeDealingCount', sortable:false, label:'本月超期在办',width:100},
	        {name:'monthNomalCompletedCount', sortable:false, label:'本月办结',width:100},
	        {name:'monthOvertimeCompletedCount', sortable:false, label:'本月超期办结',width:100},
	        {name:'yearCompletedCount', sortable:false, label:'本年累计办结',width:100},
	        {name:'totalCompletedCount', sortable:false, label:'累计办结',width:100}
		],
	   	rowNum:-1,
	    pager:false
	});

	$("#search").click(function(){
		reloadReport();
	});	

	$("#export").click(function(){
		var url="${path}/issueDealStat/exportRealTimeStatToExcel.action?orgType="+getCurrentOrgType()+"&queryOrgId="+getCurrentOrgId();
		downloadFile(url);
	});
		
	reloadReport();
});

function downloadFile(url){  
    var elemIF = document.createElement("iframe");  
    elemIF.src = url;  
    elemIF.style.display = "none";  
    document.body.appendChild(elemIF);  
}

function reloadReport(){
	onOrgChanged(getCurrentOrgId());
}

function onOrgChanged(orgId,isgrid){
    $.ajax({
        url:"${path}/statAnalyse/statRegradedPointManage/nextOrgLevelNameByOrgId.action",
        data:{
    			"targeOrgId":function(){return orgId;}
            },
        success:function(data){
                $("#issueDealStatAdminsName").html(data+"事件处理实时办理统计");
                if (needShowFunctionOrgMenu(data)){
                    $("#issueDealStatFunMenu").show();
                    $("#issueDealRealtimeStatFunMenu").show();
                }else{
                    $("#issueDealStatFunMenu").hide();
                    $("#issueDealRealtimeStatFunMenu").hide();
                }
            }
    });
	$("#issueDealStatList").setGridParam({
		url:"${path}/issueDealStat/realtimeStat.action",
		datatype: "json",
		page:1
	});
	$("#issueDealStatList").setPostData({
    	"orgType":getCurrentOrgType(),
    	"queryOrgId":orgId
   	});
	$("#issueDealStatList").trigger("reloadGrid");
}

function getCurrentOrgType(){
	return $("#orgType").val();
}
function needShowFunctionOrgMenu(orgLevel){
	return orgLevel!="村(社区)" && orgLevel!="片格";
}
</script>