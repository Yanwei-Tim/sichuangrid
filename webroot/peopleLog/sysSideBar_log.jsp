<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div id="workaccordion">
	<div>
		<h1><a id="myMenu" href="javascript:void(0)" onclick="mineMenu()">我的日志</a></h1>
		<dl id="myMenuList">
				<dd><a href="javascript:void(0)" id="mineIssue" onclick="asyncOpen(this,'${path}/peopleLog/myLog/myLogList.jsp');"><span>我的日志</span></a></dd>
				<pop:JugePermissionTag ename="myCommentLogManagement">
				<dd><a href="javascript:void(0)" onclick="issueAsyncOpen(this,'${path}/peopleLog/myComment/myCommentList.jsp','normalState')"><span>我的点评</span></a></dd>
				</pop:JugePermissionTag>
		</dl>
	</div>

	<pop:JugePermissionTag ename="subLogManagement">
	<div>
		<h1><a  href="javascript:void(0)" onclick="test();">下辖日志</a>
		<input id="subLog" type="hidden" onclick="asyncOpenPeopleLog(this,'${path}/peopleLog/subLog/subLogList.jsp');">
		</h1>
		<dl>
			<div id="orgTree-select" style="overflow: auto;_width:160px;">
				<div id="issue_org_tree_navigation" style="padding:10px 0 0 10px;"></div>
			</div>
		</dl>
	</div>
	</pop:JugePermissionTag>

</div>
<input id="currentOrgId" type="hidden" value="${USER_ORG_ID}" />

<script defer>
	var orgtreeIsExist =($("#issue_org_tree_navigation").length>0);
	var orgtreeIsGrid = false;
	<s:if test="#getFullOrgById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION&&#getFullOrgById.organization.orgLevel.internalId<=@com.tianque.domain.property.OrganizationLevel@GRID">
		orgtreeIsGrid =true;
	</s:if>
	var issueTree ;

	$(function() {
		$("#orgTree-select").height($("#orgTree-select").parents(".ui-layout-west").outerHeight()-64);

		$("#workaccordion").show();
		$("#workaccordion").accordionFunction("h1","div");
		$('#workaccordion > div > dl > dd:first > a').click();

		if(orgtreeIsExist&& !orgtreeIsGrid){
			<s:if test="#getFullOrgById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION">
			issueTree = $("#issue_org_tree_navigation").initAdministrativeTree({isRootSelected:true,shouldJugeMultizones:true});
			</s:if>
			<s:elseif test="#getFullOrgById.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG">
			issueTree = $("#issue_org_tree_navigation").initFunctionalTree();
			</s:elseif>
			$.addClick(issueTree,issueAfterChangNode);
		}
	});

	function mineMenu(){
		$("#currentOrgId").val(${USER_ORG_ID});
		$("#mineIssue").removeClass("accordion-click");
		$("#mineIssue").click();
		if(orgtreeIsExist && !orgtreeIsGrid && $.getSelectedNode(issueTree))
			$.getSelectedNode(issueTree).unselect();
	}


function test(){
       $("#currentOrgId").val(${USER_ORG_ID});
		$("#subLog").removeClass("accordion-click");
		$("#subLog").click();
}
function onOrgChanged(){

}
	function asyncOpenPeopleLog(srcObj, url){
		if(orgtreeIsExist&& !orgtreeIsGrid){
			$.selectRootNode(issueTree);
		}
		$("#subLog").removeClass("accordion-click");
		asyncOpen(srcObj, url);
		$("#parentPosition").text("民情日志");
		$("#currentPosition").text("下辖日志");
		//
	}

	function issueAfterChangNode(node){
		$("#currentOrgId").val(node.attributes.id);
		onOrgChanged.call(null,node.attributes.id);
		transTreeView(node);
	}
	function transTreeView(node){

	}


</script>