<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="interactionAcc" style="display: none;">
 	<pop:JugePermissionTag ename="interactionManagement">
	 	<div>
		  	<h1><a href="javascript:void(0)" class="blurMenuClass"><s:property value="#request.name"/></a></h1>
			<dl>
				<pop:JugePermissionTag ename="smsManagement">
					<dd><a id="smsManagement" href="javascript:void(0)" onclick="asyncInteractionOpen(this,'${path}/interaction/sms/smsSideBar.jsp')"><span><s:property value="#request.name"/></span></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="pmManagement">
					<dd><a id="pmManagement" href="javascript:void(0)" onclick="asyncInteractionOpen(this,'${path}/interactive/personnerlMessageTragetManage/getUnReadCounts.action')"><span><s:property value="#request.name"/></span></a></dd>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="contactManagement">
					<dd><a id="contactManagement" href="javascript:void(0)" onclick="asyncInteractionOpen(this,'${path}/interaction/contact/contactSideBar.jsp')"><span><s:property value="#request.name"/></span></a>&nbsp;</dd>
				</pop:JugePermissionTag>
			</dl>
	 	</div>
  	</pop:JugePermissionTag>
  	<pop:JugePermissionTag ename="knowledgeSharingManagement">
		<div>
	 		<h1 align="left"><a href="javascript:void(0)" id="knowledgeSharingManagement" ><s:property value="#request.name" /></a></h1>
	 		<dl>
				<pop:JugePermissionTag ename="directorySettingManagement">
					<dd><a id="directorySettingManagement" href="javascript:void(0)" onclick="asyncInteractionOpen(this,'${path}/knowledgeSharing/directorySetting/directorySettingList.jsp')"><span><s:property value="#request.name"/></span></a></dd>
				</pop:JugePermissionTag>
			</dl>
	 	</div>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="resourcePoolManagement">
		<div>
	 		<h1><a href="javascript:void(0)" id="resourcePoolManagement" class="dailylogOrgTreeClass"><s:property value="#request.name"/></a></h1>
	 		<dl>
				<div id="orgTree-select" style="overflow: auto;height:300px;">
					<div id="dailyLog_org_tree_navigation" style="padding:10px 0 0 10px;"></div>
				</div>
			</dl>
	 	</div>
	</pop:JugePermissionTag>
	
</div>

<script>
var orgTree = "";
function tiggerFirstMenu(){
	var tiggerMenu='<s:property value='#parameters.tiggerMenu'/>';
	if(tiggerMenu==null||tiggerMenu==''){
		$('#interactionAcc > div > dl > dd:first > a').click();
	}else{
		$("a[id='"+tiggerMenu+"']").click();
	}
}
function asyncInteractionOpen(docObj,url){
	asyncOpen(docObj,url);
	$("#interactionAcc div dl dd>a").removeClass("accordion-click");
	$("#accordion div dl dd>a").removeClass("accordion-click");
	$(docObj).addClass("accordion-click");
}


function orgAfterChangNode(){
	if($("#dailyLog_org_tree_navigation").is(":visible")){
		var orgId=$.getSelectedNodeId(orgTree);
		$("#contentDiv").html("");
		$(".subnav").children().remove();
		$(".path").children().remove();
		$("#contentDiv").children().remove();
		$(".path").show();
		$(".path").load("/interaction/interactionTitle.jsp",function(){
			$("#currentPosition").text("资料库");
			$("#parentPosition").text("资料库");
		});
		$(".submenu").hide();
		
		$("#contentDiv").load("${path}/interaction/resourcePool/resourcePoolTree.jsp?orgId="+orgId);
		
		$("#interactionAcc div dl dd>a").removeClass("accordion-click");
	}
}
$(function() {
	$("#interactionAcc").show();
	$("#interactionAcc").accordionFunction("h1","div");

	$("#interactionAcc dl dd").mouseover(function(){
		$(this).addClass("accordion-hov");
	}).mouseout(function(){
		$(this).removeClass("accordion-hov");
	});
	$("#interactionAcc a").focus( function () {
		$(this).blur();
	});
	

	$(".blurMenuClass").click(function(){
		$(this).parent().parent().find("dl dd a:first").click();
	});

	if($.contains(document.body,document.getElementById("dailyLog_org_tree_navigation"))){
		orgTree = $("#dailyLog_org_tree_navigation").initTree({isRootSelected:true,excludeRoot:false,rootId:"<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>"});
		$.addClick(orgTree,orgAfterChangNode);
	}

	$(".dailylogOrgTreeClass").click(function(){
		$.selectRootNode(orgTree);
		var orgId=$.getSelectedNodeId(orgTree);
		$("#contentDiv").html("");
		$(".subnav").children().remove();
		$(".path").children().remove();
		$("#contentDiv").children().remove();
		$(".path").show();
		$(".path").load("/interaction/interactionTitle.jsp",function(){
			$("#currentPosition").text("资料库");
			$("#parentPosition").text("资料库");
		});
		$(".submenu").hide();
		
		$("#contentDiv").load("${path}/interaction/resourcePool/resourcePoolTree.jsp?orgId="+orgId);
		
		$("#interactionAcc div dl dd>a").removeClass("accordion-click");
		
		 document.title = $(this).text();
	});

	$("#orgTree-select").height($(".ui-layout-west").height()-$("#interactionAcc h1.ui-accordion-header").size()*$("#interactionAcc h1.ui-accordion-header:first").height()-10);

	var jsflag='<s:property value='#parameters.urlflag'/>';
	if(jsflag==undefined || jsflag=='' || !$("#"+jsflag)[0]){
		tiggerFirstMenu();
		$('#interactionAcc > div > dl > dd:first > a').click();
	}else {
		$.selectMapMenu({
			id:'interactionAcc',
			jsflag:jsflag
		});
	}
});

</script>