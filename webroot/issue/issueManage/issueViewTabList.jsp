<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<style>
#myAuditDelayCount {border-radius: 5px;cursor: pointer;position: absolute;color: #fff;font: bold 11px/1.5em '';top: 6px;right: 9px;background: #F68300;padding: 0 5px;z-index: 1;}
#myVerificationCount {border-radius: 5px;cursor: pointer;position: absolute;color: #fff;font: bold 11px/1.5em '';top: 6px;right: 9px;background: #F68300;padding: 0 5px;z-index: 1;}
#myGradeDelayCount {border-radius: 5px;cursor: pointer;position: absolute;color: #fff;font: bold 11px/1.5em '';top: 6px;right: 9px;background: #F68300;padding: 0 5px;z-index: 1;
}
</style>
<input type="hidden" id="isMyIssue" />
<input type="hidden" name="selectNodeId" id="selectNodeId" />
<input type="hidden" name="selectnodeLevel" id="selectnodeLevel" />
<input type="hidden" name="selectnodeType" id="selectnodeType" />
<input type="hidden" name="selectNodeName" id="selectNodeName" />
<div id="tabList">
	<ul>
		<pop:JugePermissionTag ename="needIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=need&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="等待本级处理的事件列表">待办</a> </li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="checkPendingIssueListManagement">
			<li id="checkPending"><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=checkPending&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="等待本级审核是否批准延期的事件列表，可查看延期情况">待审核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="myAuditDelayCount"></label></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="verificationIssueListManagement">
			<li id="verification"><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=verification&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="事件已结案但是未验证">待验证&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="myVerificationCount"></label></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="checkGradeIssueListManagement">
			<li id="checkGrade"><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=checkGrade&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="由本级新增，并且已验证通过的事件列表，可进行考核打分">待评分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="myGradeDelayCount"></label></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="followIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=follow&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="本层级曾经处理过且事件还未完成的事件列表">待跟进</a> </li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="doneIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=done&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="本层级曾经处理过的事件列表，包括办理中的以及已完成的事件，可进行查看查询">已办</a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="completedIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=completed&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="由本层级新增，并且已由本层级验证通过的事件列表，可进行查看查询">已办结</a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="submitIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=submit&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="由本层级上报的事件列表，可进行查看查询">上报</a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="assignIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=assign&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="由上级领导交办给本层级办理的事件列表，可进行查看查询">上级交办</a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="skipIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=skip&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="流转到本层级的越级事件，可进行查看查询">越级</a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="publicltyCassDoneIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=publicltyCassDone&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="设置为宣传案例的事件">宣传案例</a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="timeOutIssueListManagement">
			<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=timeOut&orgLevel=<s:property value='#parameters.orgLevel'/>&keyId=<s:property value='#parameters.keyId'/>&functionalOrgType=<s:property value='#parameters.functionalOrgType'/>" title="红黄牌督办的事件">超时事件</a></li>
		</pop:JugePermissionTag>
		</ul>
</div>
<script>

$(function() {
	var isAuditDelay= '<s:property value="#parameters.isAuditDelay"/>';
	var isMyIssue = $("#orgMenuList a").eq(0).hasClass("cur");
	$("#isMyIssue").val(isMyIssue);
	if(isAuditDelay!='' && isAuditDelay!=null){
		$( "#tabList" ).tabs({ selected: isAuditDelay ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		},beforeActivate : function(event, ui) {
			beforeTabsActiveFun(event,ui);
		}});
	}else{
		$( "#tabList" ).tabs({ selected: 0 ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		},beforeActivate : function(event, ui) {
			beforeTabsActiveFun(event,ui);
		}});
	}
	refreshIssueCounts();
});
//刷新页签统计数量
function refreshIssueCounts(){
	var orgLevelInternalId = '<s:property value="#parameters.orgLevel"/>';
	var functionalOrgType = '<s:property value="#parameters.functionalOrgType"/>';
	$.ajax({
		async : true,
		url : PATH+"/issues/issueApplyDelay/getJurisdictionsAuditDelayCount.action",
		data :{"orgLevelInternalId":orgLevelInternalId,"orgId":USER_ORG_ID,"functionalOrgType":functionalOrgType},
		success : function(data) {
			if(!isNaN(data)){
				if(data>99){
					$("#myAuditDelayCount").html("99+");
				}else{
					$("#myAuditDelayCount").html(data);
				}
			}else{
				$("#myAuditDelayCount").html(0);
				$.messageBox({level:'warn',message:"获取下辖待审核数量出错"});
			}
		}
	 });
	$.ajax({
		async : true,
		url : PATH+"/issues/issueManage/getJurisdictionsGradeDelayCount.action",
		data :{"orgLevelInternalId":orgLevelInternalId,"keyId":USER_ORG_ID,"functionalOrgType":functionalOrgType},
		success : function(data) {
			if(!isNaN(data)){
				if(data>99){
					$("#myGradeDelayCount").html("99+");
				}else{
					$("#myGradeDelayCount").html(data);
				}
			}else{
				$("#myGradeDelayCount").html(0);
				$.messageBox({level:'warn',message:"获取下辖待评分数量出错"});
			}
		}
	 });
	 $.ajax({
		async : true,
		url : PATH+"/issues/issueManage/getJurisdictionsVerificationCount.action",
		data :{"orgLevelInternalId":orgLevelInternalId,"keyId":USER_ORG_ID,"functionalOrgType":functionalOrgType},
		success : function(data) {
			if(!isNaN(data)){
				if(data>99){
					$("#myVerificationCount").html("99+");
				}else{
					$("#myVerificationCount").html(data);
				}
			}else{
				$("#myVerificationCount").html(0);
				$.messageBox({level:'warn',message:"获取下辖待验证数量出错"});
			}
		}
	 });
}

function beforeTabsActiveFun(event,ui){
	$("#orgMenuList li>a").each(function(){
		var href=$(this).attr('baseUrl');
		var indexOf=href.indexOf("\?");
		if(indexOf>0){
			indexOf=href.indexOf("isAuditDelay");
			if(indexOf>0){
				var hrefArr=href.split("isAuditDelay");
				href=hrefArr[0];
				if(hrefArr[1].indexOf("\&")>0){
					href+="isAuditDelay="+ui.newTab.index();
					href+=hrefArr[1].substring(hrefArr[1].indexOf("\&"));
				}else{
					href+="isAuditDelay="+ui.newTab.index();
				}
			}else{
				href+="&isAuditDelay="+ui.newTab.index();
			}
		}
		else
			href+="?isAuditDelay="+ui.newTab.index();
		$(this).attr('baseUrl',href);
	});	
	
	$("#orgMenuList dl>a").each(function(){
		var href=$(this).attr('baseUrl');
		var indexOf=href.indexOf("\?");
		if(indexOf>0){
			indexOf=href.indexOf("isAuditDelay");
			if(indexOf>0){
				var hrefArr=href.split("isAuditDelay");
				href=hrefArr[0];
				if(hrefArr[1].indexOf("\&")>0){
					href+="isAuditDelay="+ui.newTab.index();
					href+=hrefArr[1].substring(hrefArr[1].indexOf("\&"));
				}else{
					href+="isAuditDelay="+ui.newTab.index();
				}
			}else{
				href+="&isAuditDelay="+ui.newTab.index();
			}
		}
		else
			href+="?isAuditDelay="+ui.newTab.index();
		$(this).attr('baseUrl',href);
	});		
}

/**
function callback(){
    var dfop = {
    	isAuditDelay:'<s:property value="#parameters.isAuditDelay"/>',
    	orgLevelInternalId:'<s:property value="#parameters.orgLevel"/>',
    	functionalOrgType:'<s:property value="#parameters.functionalOrgType"/>'
    }
    TQ.issueViewTabList(dfop);
}

$.cacheScript({
    url:'/resource/getScript/issue/issueManage/issueViewTabList.js',
    callback: callback
})
*/
</script>