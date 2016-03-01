<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<link rel="stylesheet" href="${resource_path}/resource/external/tipswindown/tipswindown.css" type="text/css" media="all" />
<script type="text/javascript" src="${resource_path}/resource/external/tipswindown/tipswindown.js"></script>
<div id="nav" class="ui-corner-all">
		<input type="hidden" id="orgType" name="orgType" value="${orgType}"/>
        <select name="queryYear" id="queryYear" onchange="onChangeYear()" style="float:left;">
            <s:iterator begin="maxYear" end="minYear" var="year" step="-1">
            	<s:if test='#year==defaultYear'>
	                <option value="${year}" selected>${year }</option>
            	</s:if>
            	<s:else>
	                <option value="${year }">${year }</option>
            	</s:else>
            </s:iterator>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="queryMonth" onchange="">
            <s:iterator begin="defaultMonth" end="1" var="month" step="-1">
            	<s:if test='#month==defaultMonth'>
	                <option value="${month}" selected>${month }</option>
            	</s:if>
            	<s:else>
	                <option value="${month }">${month }</option>
            	</s:else>
            </s:iterator>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
        <pop:JugePermissionTag ename="searchIssueDealStat">
		    <a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="generatedIssueDealStat">
			<a id="produceDate" href="javascript:void(0)"><span>生成数据</span></a>
		</pop:JugePermissionTag>
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
	        {name:'org.orgName',sortable:false,label:'名称',width:150},
	        {name:'monthAddCount', sortable:false, label:'本月立案',width:60},
	        {name:'yearAddCount', sortable:false, label:'本年累计立案',width:80},
	        {name:'totalAddCount', sortable:false, label:'累计立案',width:60},
	        {name:'monthNomalDealingCount', sortable:false, label:'本月正常在办',width:80},
	        {name:'monthOvertimeDealingCount', sortable:false, label:'本月超期在办',width:80},
	        {name:'monthNomalCompletedCount', sortable:false, label:'本月办结',width:60},
	        {name:'monthOvertimeCompletedCount', sortable:false, label:'本月超期办结',width:80},
	        {name:'yearCompletedCount', sortable:false, label:'本年累计办结',width:80},
	        {name:'totalCompletedCount', sortable:false, label:'累计办结',width:60},
	        {name:'monthComplededAddCount', sortable:false, label:'本月已办结立案',width:90},
	        {name:'yearComplededAddCount', sortable:false, label:'本年累计已办结立案',width:120},
	        {name:'totalComplededAddCount', sortable:false, label:'累计已办结立案',width:90},
	        {name:'totalOrgYearCompleteRata', sortable:false, label:'年度办结率',width:80},
	        {name:'totalOrgCompleteRata', sortable:false, label:'累计办结率',width:80}
		],
	   	rowNum:-1,
	    pager:false
	});

	$("#search").click(function(){
		reloadReport();
	});	
	
	$("#produceDate").click(function(event){
		$.confirm({
			title:"系统提示",
			message:"确定重新生成事件处理办理统计数据？",
			width:400,
			okFunc:replaceIssueDealStat
		});
	});
	
	$("#export").click(function(){
		var url="${path}/issueDealStat/exportToExcel.action?orgType="+getCurrentOrgType()+"&queryOrgId="+getCurrentOrgId()+"&queryYear="+getSelectYear()+"&queryMonth="+getSelectMonth();
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
                $("#issueDealStatAdminsName").html(data+"事件处理办理统计");
                $("#issueRealTimeDealStatAdminsName").html(data+"事件处理实时办理统计");
                if (needShowFunctionOrgMenu(data)){
                    $("#issueDealStatFunMenu").show();
                }else{
                    $("#issueDealStatFunMenu").hide();
                    $("#issueDeal").click();
                }
            }
    });
	$("#issueDealStatList").setGridParam({
		url:"${path}/issueDealStat/monthStat.action",
		datatype: "json",
		page:1
	});
	$("#issueDealStatList").setPostData({
    	"orgType":getCurrentOrgType(),
    	"queryOrgId":orgId,
    	"queryYear":getSelectYear(),
    	"queryMonth":getSelectMonth()
   	});
	$("#issueDealStatList").trigger("reloadGrid");
}

function getCurrentOrgType(){
	return $("#orgType").val();
}
function getSelectYear(){
	return $("#queryYear").val();
}
function getSelectMonth(){
	return $("#queryMonth").val();
}
function needShowFunctionOrgMenu(orgLevel){
	return orgLevel!="村(社区)" && orgLevel!="片格";
}
function replaceIssueDealStat(){
	tipsWindown("正在生成数据需要较长时间，请耐心等待！","img:"+RESOURCE_PATH+"/resource/external/tipswindown/images/progressbar.gif","280","20",",false","","true","img");
	$("#replace").buttonDisable();
	$.ajax({
		url:"${path}/issueDealStat/rebuildStatInfo.action",
		data:{
	    	"queryOrgId":getCurrentOrgId(),
	    	"queryYear":getSelectYear(),
	    	"queryMonth":getSelectMonth()
		},
		success:function(data){
			$("#windownbg").remove();
			$("#windown-box").fadeOut("slow",function(){$(this).remove();});
			if(data == true){
				$.messageBox({message:"已经重新生成统计数据!"});
				$("#replace").buttonEnable();
				reloadReport();
			}else{
				$.messageBox({ message:"重新生成统计数据失败!",level: "error"});
				$("#replace").buttonEnable();
			}
		},
   	    error: function(XMLHttpRequest, textStatus, errorThrown){
			$("#windownbg").remove();
			$("#windown-box").fadeOut("slow",function(){$(this).remove();});
    	}
	});
}

function onChangeYear () {
	if (${defaultYear} != getSelectYear()) {
		$('#queryMonth option').remove();
		<s:iterator begin="12" end="1" var="newMonth" step="-1">
        	$("#queryMonth").append("<option value='${newMonth }'>"+${newMonth }+"</option>");
    	</s:iterator>
	} else {
		$('#queryMonth option').remove();
        <s:iterator begin="defaultMonth" end="1" var="month" step="-1">
	    	<s:if test='#month==defaultMonth'>
	    		$("#queryMonth").append("<option value='${month }' selected>"+${month }+"</option>");
	    	</s:if>
	    	<s:else>
	            $("#queryMonth").append("<option value='${month }'>"+${month }+"</option>");
	    	</s:else>
	    </s:iterator>
	}
}
</script>