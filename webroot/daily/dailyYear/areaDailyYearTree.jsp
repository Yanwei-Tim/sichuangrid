<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="center-left" style="overflow:hidden;">
	<a href="javascript:;" class="globalOrgBtnMod" id="globalOrgBtnMod"><span id="globalDailyYear"></span></a>
	<hr/>
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
<input id="displayLevel" type="hidden" value="false"/>
<input id="childFirstParentOrg" type="hidden" value="${childFirstOrg.parentOrg.id}" />

<script type="text/javascript">
var tree;
var dailyDirectoryType;
var reportTypeInternalId;
var innerTitle;
var dailyDirectoryId;
var menuType = 'areaWorkingRecordManagement';
$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").height();
	$(".orgTree").height(centerHeight-108);
	$(".center-right").height(centerHeight-30);
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
	$("#globalOrgBtnMod.globalOrgBtnMod").click(function(){
		$("#globalOrgBox").createDialog({
			url:'${path}/sysadmin/orgManage/orgSelectComponent.action?id='+${organization.id},
			width:550,
			height:'auto',
			draggable:false,
			title:'辖区选择',
			buttons: {
				"确定" : function(event){
					var selectInput=$("#orgSelectInput");
					var orgLevelInternalId=selectInput.attr("orgLevelInternalId");
					var text=selectInput.attr("text");
					$("#currentOrgId").attr({
						"orgLevelInternalId":orgLevelInternalId,
						text:text,
						value:selectInput.val()
					});
					
					if(USER_ORG_ID==selectInput.val()){
						$.messageBox({message:"请选择一个下辖的层级！",level:'warn'});
						return false;
					}
					var curMenu=$("#accordion a.cur");
					var baseUrl=curMenu.attr("baseUrl");
					var leaderUrl=curMenu.attr("leaderUrl");
					baseUrl = baseUrl+"?currentOrganization.id="+selectInput.val();
					<%-- 此函数在baseJs.jsp 中 --%>
					baseLoad(curMenu,baseUrl,leaderUrl);
				},
				"取消" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
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
function getDailyDirectoryById(id, async){
	var returndata={};
	$.ajax({
		async : async,
		url:'/daily/dailyDirectoryManage/getFullDailyDirectoryById.action',
		
		data:{"dailyDirectory.id":id},
		success:function(dailyDirectory){
			returndata = $.extend(returndata, dailyDirectory)
		}
	});
	return returndata;
}
function afterNodeChanged(node){
	//var dailyDirectory = getDailyDirectoryById(node.id, false);
	//innerTitle=dailyDirectory.shortName;
	return "";
}
function afterDailyDirectoryChangeNode(node){
	$(".tip-yellowsimple").remove();
	dailyDirectoryType = node.attributes.internalId;
	reportTypeInternalId = node.attributes.reportTypeInternalId;
	dailyDirectoryId = node.id;
	$("#dailyDirectoryId").val(node.attributes.id);
	var type = node.attributes.typeId;
	if(type > 0){
		$("#dailyDirectoryId").val(node.attributes.id);
		loadDailyLogList(menuType,"${path}/daily/statementsReportedManage/findStatementsReportedList.action?dailyDirectory.id="+node.attributes.id+"&modeType=areaDailyYear");
		return ;
	}
	loadDailyLogList(menuType,"${path}/daily/workingRecord/workingRecordList.jsp?menuType="+menuType+"&orgId=${organization.id}");
	afterNodeChanged(node);
}
function changeYear(yearId){
	$(".x-tree-root-ct:first",$("#dailyDirectoryTree")).remove(); //删除根目录
	if(yearId == null || yearId == ""){
		$("#dailyDirectoryTree").append("没有可以显示的目录树！");
	}else{
		tree=$("#dailyDirectoryTree").initDailydirectoryTree({dailyYearId:yearId,afterNodeExpanded:function(node){
			node.eachChild(function(child){
				showFile(child.id)
			})
		}});
		$.addClick(tree,afterDailyDirectoryChangeNode);
	}
}
function showFile(id){
	$.ajax({
		url:"${path}/daily/dailyDirectoryManage/getFullDailyDirectoryById.action?dailyDirectory.id="+id,
		success:function(dailyDirectory){
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

$("#currentOrgId").attr({
	text:"${organization.orgName}",
	value:"${organization.id}"
});
</script>