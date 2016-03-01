<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<style> 
#note{position:absolute;width:162px;padding:1px;background:#eee;border:1px solid #ccc;left:1%;z-index:9999;display:none;} 
</style> 
<div id="note" align='center'><span style="font-family:微软雅黑;font-size:14px;font-weight:normal;font-style:normal;text-decoration:none;color:#CC0000; text-align:center;" ><span id='date'></span></span></div> 
<div class="center-left" style="overflow:hidden;">
	<div class="propertyDomainList" style="overflow:hidden;width:186px;">
		请选择台账目录：
		<select id="dailyYear" name="dailyYear">
			<s:iterator value="dailyYears">
				<option value="${id}">${name}</option>
			</s:iterator>
		</select>
	</div>
	<div class="orgTree">
		<div id="dailyDirectoryTree"></div>
	</div>
	<input type="hidden" id="dailyDirectoryId" value="" />
	<input type="hidden" id="currentOrganizationId" value="${currentOrganization.id}"/>
</div>
<div class="center-right" id="dailyLogListContainer">


</div>
<div id="dailyLogMaintanceDialog"></div>
<input id="orgLevelForDailyYearTree" type="hidden" value="${orgLevel}" />
<input id="userOrgLevelForDailyYearTree" type="hidden" value="${userOrgLevel}" />
<input id="orgCode" type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().orgInternalCode"/>" />
<input id="orgId" type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>" />
<input id="menuType" type="hidden"/>
<input id="displayLevel" type="hidden" value="true"/>

<script type="text/javascript">
var accountTree;
var dailyDirectoryType;
var reportTypeInternalId;
var innerTitle;
var dailyDirectoryId;
var _listTableHeight;
var menuType = 'myWorkingRecordManagement';
function mousePos(e){
	var e = e||window.event;
	var x; 
	var y;
	return {
		x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
		y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
    };
};
function over(e,date,dateType){
	if (dateType == false) { 
		$("#date").text("目录有效期为："+date+" 天");
	} else {
		$("#date").text("截止日期:"+date);
	}
	var y = mousePos(e).y-130;
	var x = mousePos(e).x;
	if(!$('#note').is(':visible')) { 
		$('#note').css({display:'block', top:'-'+y+'px'}).animate({top:'+'+y}, 0, function(){}); 
	} 
}; 
	
function out(e) {
	var y = mousePos(e).y-130;
	var x = mousePos(e).x;
	$('#note').animate({top:'0'}, 0, function(){$(this).css({display:'none', top:'-'+y+'px'}); 
	}); 
} 
$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").height();
	$(".orgTree").height(centerHeight-80);
	$(".center-right").height(centerHeight-60);
	$("#menuType").val(menuType);
	changeYear($("#dailyYear").val());
	var ids = $('[id=dailyLogMaintanceDialog]');
	if (ids.length > 1) {
		for (var i = 0; i < ids.length - 1; i++) {
			$(ids[i]).empty();
			$(ids[i]).remove();
			$(ids[i]).parent().empty();
			$(ids[i]).parent().remove();
		}
	}
	$("#dailyYear").change(function(){
		changeYear($("#dailyYear").val());
	});
});
function loadDailyLogList(menuType,url){
	$("#dailyLogListContainer").load(url,function(){
		var dialogBoo="<s:property value="#parameters.dialog" />";
		if(dialogBoo=="true"){
			$(".center-right").height(400);
			$("#dailyLogList").setGridHeight(300);
			$("#dailyLogList").setGridWidth(660);
		}
	});
}
function afterDailyDirectoryChangeNode(node){
	$(".tip-yellowsimple").remove();
	$("#baseLine").nextAll(".ui-dialog").remove();
	dailyDirectoryType = node.attributes.internalId;
	reportTypeInternalId = node.attributes.reportTypeInternalId;
	dailyDirectoryId = node.id;
	innerTitle = node.attributes.text;
	//innerTitle = $(node.attributes.text).text();
	if("" == innerTitle){
		innerTitle = node.attributes.text;
	}
	var type = node.attributes.typeId;
	if(type > 0){
		$("#dailyDirectoryId").val(node.attributes.id);
		loadDailyLogList(menuType,"${path}/daily/statementsReportedManage/findStatementsReportedList.action?dailyDirectory.id="+node.attributes.id);
		return ;
	}
	$("#dailyDirectoryId").val(node.attributes.id);
	loadDailyLogList(menuType,"${path}/daily/workingRecord/workingRecordList.jsp?menuType="+menuType);
}
function changeYear(yearId){
	$(".x-tree-root-ct:first",$("#dailyDirectoryTree")).remove(); //删除根目录
	if(yearId == null || yearId == ""){
		$("#dailyDirectoryTree").append("没有可以显示的目录树！");
	}else{
		accountTree=$("#dailyDirectoryTree").initDailydirectoryTree({dailyYearId:yearId,afterNodeExpanded:function(node){
			node.eachChild(function(child){
				showFile(child.id)
			})
		}});
		$.addClick(accountTree,afterDailyDirectoryChangeNode);
	}
}
function showFile(id){
	$.ajax({
		url:"${path}/daily/dailyDirectoryManage/getFullDailyDirectoryById.action?dailyDirectory.id="+id,
		success:function(dailyDirectory){
			if(dailyDirectory.dailyDirectoryAttachFiles.length==0){
				return;
			}
			var popMoreData = [];
			for(var j = 0; j < dailyDirectory.dailyDirectoryAttachFiles.length; j++){
				popMoreData[j]={
					id:dailyDirectory.dailyDirectoryAttachFiles[j].fileId, 
					url:'${path}/daily/dailyDirectoryManage/downLoadAttachFile.action?keyId='+dailyDirectory.dailyDirectoryAttachFiles[j].fileId, 
					text:dailyDirectory.dailyDirectoryAttachFiles[j].fileName
				};
			}
			$("#dailyDirectory_"+id).popUpMore({data: popMoreData});
		}
	});
}
</script>