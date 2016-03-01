<%@ page import="java.util.*"  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
	.spanSize{
		font-size: 14px;
	}
	.spanTop{
		top:10px;
		
	}
</style>
<div class="gisPop clearfix">
	<div>
		
		<s:if test="objectList[2].imageUrl == null">
			<img src="/resource/system/images/avatar.jpg" width="80" height="70">
		</s:if>
		<s:else>
			<img src="${objectList[2].imageUrl}" width="80" height="70">
		</s:else>
		<div style="float: right;margin-top: 15px;">
			<span class="spanSize" style="margin-left: -90px;">${objectList[2].memeberName}</span>
			<span class="spanSize" style="margin-left:15px;">（${objectList[2].positionType.displayName}）</span>	
		</div>
		
	</div>
	<div style="margin-top: 5px;">
		<a style="margin-left: 20px;" href="javascript:getGridTeamInfoById(${objectList[2].id})">点击详情</a>
		<span style="font-size:15px;margin-left: 30px;">电话：</span>${objectList[2].phoneNumber}
	</div>
</div>





