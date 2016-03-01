<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="center-left" style="height:95%;">
	<div class="propertyDomainList">
		<ul id="propertiesDomain">
		<c:choose>
			<c:when test='${fn:length(myGroups)>0}'>
				<c:forEach items="${myGroups }" id="myGroups" var="myGroup">
			    	<li><a href="javascript:void(0)" class="myGroup" name="${myGroup.id}">${myGroup.name}</a></li>
				</c:forEach>
			</c:when>
			<c:otherwise>
	         	<li id="noGroup"><span style="color:red;">暂无群组</span></li>
			</c:otherwise>
		</c:choose>
		</ul>
	</div>
</div>

<div class="center-right" id="myGroup"></div>

<script type="text/javascript">

$(document).ready(function(){
	initMyGroupMenu();
	if($("#propertiesDomain>li a").length>0){
		$("#propertiesDomain>li a:first").click();
	}else{
		dispatchMyGroup(0);

	}
});

function dispatchMyGroup(id){
	if(id==undefined){
		id=0;
	}
	$("#myGroup").load("${path}/contact/myGroupManage/dispatchMyGroup.action?myGroup.id="+id);
}

function removeMyGroup(id){
	var targetMyGroup=$("#propertiesDomain>li a[name="+id+"]");
	var nextSelectMyGroup=targetMyGroup.parent().next().find(".myGroup");
	if(nextSelectMyGroup.length<1){
		nextSelectMyGroup=targetMyGroup.parent().prev().find(".myGroup");
	}
	nextSelectMyGroup.addClass("select").click();
	targetMyGroup.parent().remove();
}

function addMyGroup(jsonData){
	var targetMyGroup=$("<a>").attr("href","#").addClass("myGroup").attr("name",jsonData.id)
			.text(jsonData.name);
	$("<li>").append(targetMyGroup).appendTo("#propertiesDomain");

	initMyGroupMenu();
	$("#propertiesDomain>li a").removeClass("select");
	targetMyGroup.addClass("select");
}

function updateMyGroup(jsonData){
	$("#propertiesDomain>li a[name="+jsonData.id+"]").text(jsonData.name);
}

function initMyGroupMenu(){
	$("#propertiesDomain>li a").click(function(){
		$("#propertiesDomain>li a").removeClass("select");
		dispatchMyGroup($(this).attr("name"));
		$(this).addClass("select");
	});
}

</script>
