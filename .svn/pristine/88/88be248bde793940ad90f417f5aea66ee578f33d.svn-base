<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="accordion" style="display: none;">
 	<pop:JugePermissionTag ename="commandCenterManagement">
 	<div>
	  	<h1><a href="javascript:void(0)"><s:property value="#request.name"/></a></h1>
		<ul>
			<pop:JugePermissionTag ename="commandCenterCaseManagement">
				<li><a id="commandCenterCaseManagement" href="javascript:void(0)" onclick="asyncOpen(this,'${path}/commandCenter/informationList.jsp')"><span><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="doingCommandCenterCaseManagement">
				<li><a id="doingCommandCenterCaseManagement" href="javascript:void(0)" onclick="asyncOpen(this,'${path}/commandCenter/commandCenterList.jsp')"><span><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="DoneCommandCenterManagement">
				<li><a id="DoneCommandCenterManagement" href="javascript:void(0)" onclick="asyncOpen(this,'${path}/commandCenter/doneCommandCenterList.jsp')"><span><s:property value="#request.name"/></span></a>&nbsp;</li>
			</pop:JugePermissionTag>
		</ul>
 	</div>
  	</pop:JugePermissionTag>
</div>

<script>
function tiggerFirstMenu(){
	var tiggerMenu='<s:property value='#parameters.tiggerMenu'/>';
	if(tiggerMenu==null||tiggerMenu==''){
		$('#accordion > div > ul > li:first > a').click();
	}else{
		$("a[id='"+tiggerMenu+"']").click();
	}
}
$(function() {
	$("#accordion").show();
	$("#accordion").accordionFunction("h1","div");

	$("#accordion ul li").mouseover(function(){
		$(this).addClass("accordion-hov");
	}).mouseout(function(){
		$(this).removeClass("accordion-hov");
	});
	$("#accordion a").focus( function () {
		$(this).blur();
	});
	tiggerFirstMenu();
});

</script>