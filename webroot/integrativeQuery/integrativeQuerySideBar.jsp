<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="accordion" style="display: none;">
 	<pop:JugePermissionTag ename="integrativeQueryManagement">
 	<div>
	  	<h1><a href="javascript:void(0)"><s:property value="#request.name"/></a></h1>
		<ul>
			<pop:JugePermissionTag ename="integrativeQueryPeopleManagement">
				<li><a id="integrativeQueryPeople" href="javascript:void(0)" onclick="asyncOpen(this,'${path}/integrativeQuery/population/searchFrom.jsp')"><span><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<%-- <pop:JugePermissionTag ename="pmManagement">
				<li><a id="pmManagement" href="javascript:void(0)" onclick="asyncOpen(this,'${path}/interactive/personnerlMessageTragetManage/getUnReadCounts.action')"><span><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="contactManagement">
				<li><a id="contactManagement" href="javascript:void(0)" onclick="asyncOpen(this,'${path}/interaction/contact/contactSideBar.jsp')"><span><s:property value="#request.name"/></span></a>&nbsp;</li>
			</pop:JugePermissionTag> --%>
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