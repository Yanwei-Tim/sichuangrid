<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>

<div id="workaccordion" >
 	<pop:JugePermissionTag ename="popedomEvaluate">
	 	<div>
			<h1><a href="javascript:void(0)" class="evaluateTreeClass"><s:property value="#request.name"/></a></h1>
			<dl>
				<div id="evaluate-select" style="overflow: auto;height:200px;">
					<div id="evaluate_tree_navigation" style="padding:10px 0 0 10px;"></div>
				</div>
			</dl>
		</div>
  	</pop:JugePermissionTag>
  	
	<pop:JugePermissionTag ename="higherEvaluate">
	<div>
	  	<h1><a href="javascript:void(0)" class="blurMenuClass"><s:property value="#request.name"/></a></h1>
		<dl>
			<pop:JugePermissionTag ename="selfEvaluate">
			<dd><a href="javascript:void(0)" onclick="otherAsyncOpen(this,'${path}/evaluate/selfEvaluate.jsp','selfEvaluate')"><span><s:property value="#request.name"/></span></a></dd>
		 	</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="myEvaluate">
			<dd><a href="javascript:void(0)" onclick="otherAsyncOpen(this,'${path}/evaluate/myEvaluateResultList.jsp','myEvaluate')"><span><s:property value="#request.name"/></span></a></dd>
		 	</pop:JugePermissionTag>
		 	<pop:JugePermissionTag ename="areaReportSituation">
		 		<dd><a href="javascript:void(0)" onclick="otherAsyncOpen(this,'${path}/evaluate/lowerEvaluateResultList.jsp','lowerEvaluate')"><span><s:property value="#request.name"/></span></a></dd>
		 	</pop:JugePermissionTag>
		 </dl>
 	</div>
 	</pop:JugePermissionTag>
 	<pop:JugePermissionTag ename="standardEvaluateManage">
	 	<div>
	 		<h1><a href="javascript:void(0)" class="blurMenuClass"><s:property value="#request.name"/></a></h1>
	 		<dl>
				<pop:JugePermissionTag ename="standardEvaluateList">
	 			<!-- <li><a href="javascript:void(0)" onclick="otherAsyncOpen(this,'${path}/evaluate/rulesSetting.jsp','rulesSetting')"><span><s:property value="#request.name"/></span></a></li> -->
	 				<dd><a href="javascript:void(0)" onclick="otherAsyncOpen(this,'${path}/evaluate/standardEvaluateList.jsp','standardEvaluate')"><span><s:property value="#request.name"/></span></a></dd>
	 			</pop:JugePermissionTag>
	 		</dl>
	 	</div>
 	</pop:JugePermissionTag>
</div>

<script>
var orgEvaluateTree ;
function otherAsyncOpen(srcObj,url,title){
	if(!$(srcObj).hasClass("accordion-click")){
		$(".subnav").children().remove();
		$(".path").children().remove();
		$("#contentDiv").children().remove();
		$(".path").show();
		$(".submenu").hide();
		$(".path").load("/evaluate/evaluateTitle.jsp?title="+title);
		asyncOpen(srcObj,url);
		$("#workaccordion div dl dd>a").removeClass("accordion-click");
		$(srcObj).addClass("accordion-click");
	}
}
function childOrgAsyncOpen(){
	var orgId=$.getSelectedNodeId(orgEvaluateTree);
	$(".subnav").children().remove();
	$(".path").children().remove();
	$("#contentDiv").children().remove();
	$(".path").hide();
	$(".submenu").show();
	$(".subnav").load("/evaluate/topSideBar.jsp?orgId="+orgId);
	$("#workaccordion div dl dd>a").removeClass("accordion-click");
}
function initMenu(){
	$("#workaccordion").show();
	$("#workaccordion").accordionFunction("h1","div");
	$("#workaccordion ul li").mouseover(function(){
		$(this).addClass("accordion-hov");
	}).mouseout(function(){
		$(this).removeClass("accordion-hov");
	});
	$("#workaccordion a").focus(function(){
		$(this).blur();
	});
}
$(function(){
	if($("#evaluate_tree_navigation").length > 0){
		orgEvaluateTree = $("#evaluate_tree_navigation").initAdministrativeTree({isRootSelected:true,excludeRoot:true,rootId:"<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>"});
	}
	initMenu();
	$(".blurMenuClass").click(function(){
		$(this).parent().parent().find("dl dd a:first").click();
		if(orgEvaluateTree){
			var node = $.getSelectedNode(orgEvaluateTree);
			if(node!=undefined && node!=null){
				node.unselect();
			}
		}
	});
	if(orgEvaluateTree){
		$.addClick(orgEvaluateTree, childOrgAsyncOpen);

		$(".evaluateTreeClass").click(function(){
			$.selectRootNode(orgEvaluateTree);
			$("#workaccordion div dl dd>a").removeClass("accordion-click");
		});
	}else{
		$($(".blurMenuClass").parent().parent().find("a")[1]).click();
	}
	$("#evaluate-select").height($(".ui-layout-west").height()-$("#workaccordion h1.ui-accordion-header").size()*$("#workaccordion h1.ui-accordion-header:first").height()-10);
});


</script>