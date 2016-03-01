<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>

<div id="workaccordion" style="display: none;">
 	
 	<div>
 	<pop:JugePermissionTag ename="myDailyLog">
 		<h1><a href="javascript:void(0)" class="blurMenuClass"><s:property value="#request.name"/></a></h1>
 			</pop:JugePermissionTag>
 		<dl>
 		<pop:JugePermissionTag ename="myDailyLogConfig">
 			<dd><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/workingRecord/dailyYearManage/findDailyYearsByParentOrgId.action', 'dailyDirectory')"><span><s:property value="#request.name"/></span></a></dd>
 		  	</pop:JugePermissionTag>
 		  	<pop:JugePermissionTag ename="areaDailyLogSubInfo">
 			<dd><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/workingRecord/dailyYearManage/findIssueSubmitInfoByParentOrgId.action', 'dailyDirectory')"><span><s:property value="#request.name"/></span></a></dd>
 			</pop:JugePermissionTag>
 		</dl>
 	</div>

  	
  	<pop:JugePermissionTag ename="areaDailyLog">
  	<div>
 		<h1><a href="javascript:void(0)" class="dailylogOrgTreeClass"><s:property value="#request.name"/></a></h1>
 		<dl>
			<div id="orgTree-select" style="overflow: auto;height:300px;">
				<div id="dailyLog_org_tree_navigation" style="padding:10px 0 0 10px;"></div>
			</div>
		</dl>
 	</div>
 	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="areaDailyDirectoryConfig">
	<div>
 		<h1><a href="javascript:void(0)" class="blurMenuClass"><s:property value="#request.name"/></a></h1>
 		<dl>
 			<dd><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/daily/dailyYearManage/findDailyDirectoryListByMakedOrgId.action')"><span><s:property value="#request.name"/></span></a></dd>
 		</dl>
 	</div>
 	</pop:JugePermissionTag>
 	
 	<pop:JugePermissionTag ename="workDiaryManagement">
 	<div>
 		<h1><a href="javascript:void(0)" class="blurMenuClass"><s:property value="#request.name"/></a></h1>
 		<dl>
 			<dd><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/workingRecord/workDiary/workDiaryList.jsp')"><span><s:property value="#request.name"/></span></a></dd>
 		</dl>
 	</div>
 	</pop:JugePermissionTag>
</div>
<input id="currentOrgId" type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>" />

<script>
var orgTree = "";
$(document).ready(function(){
	initMenu();
	$(".blurMenuClass").click(function(){
		$(this).parent().parent().find("dl dd a:first").click();
		var node = $.getSelectedNode(orgTree);
		if(node!=undefined && node!=null){
			node.unselect();
		}
	});
	$(".dailylogOrgTreeClass").click(function(){
		$.selectRootNode(orgTree);
		$("#workaccordion div dl dd>a").removeClass("accordion-click");
	});
	$('#workaccordion > div > dl > dd:first > a').click();
	if($.contains(document.body,document.getElementById("dailyLog_org_tree_navigation"))){
		orgTree = $("#dailyLog_org_tree_navigation").initTree({isRootSelected:false,excludeRoot:true,rootId:"<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>"});
		$.addClick(orgTree,orgAfterChangNode);
	}
	$("#orgTree-select").height($(".ui-layout-west").height()-$("#workaccordion h1.ui-accordion-header").size()*$("#workaccordion h1.ui-accordion-header:first").height());
});

function initMenu(){
	$("#workaccordion").show();
	$("#workaccordion").accordionFunction("h1","div");
	$("#workaccordion dl dd").mouseover(function(){
		$(this).addClass("accordion-hov");
	}).mouseout(function(){
		$(this).removeClass("accordion-hov");
	});
}

function orgAfterChangNode(){
	
	var orgId=$.getSelectedNodeId(orgTree);
	$(".subnav").children().remove();
	$(".path").children().remove();
	$("#contentDiv").children().remove();
	$(".path").show();
	$(".path").load("/workingRecord/dailylogTitle.jsp");
	$(".submenu").hide();
	$("#contentDiv").load("${path}/workingRecord/dailyYearManage/findDailyYearsByParentOrgId.action?currentOrganization.id="+orgId);
	$("#workaccordion div dl dd>a").removeClass("accordion-click");
}

</script>