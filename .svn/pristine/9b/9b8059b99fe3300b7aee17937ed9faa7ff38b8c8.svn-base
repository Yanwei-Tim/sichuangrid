<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<input type="hidden" name="organization.id" value="${newVillageBasic.organization.id }"/>
<input type="hidden" name="newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<div class="zt_tabs_style">
	<div id="chartsTabs">
		<ul>
			<s:if test="0==isPlaning">
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=0&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}">基本信息</a></li>
			</s:if>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=1&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">新村建设信息</a></li>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=2&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">产业发展信息</a></li>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=3&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">基础设施建设</a></li>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=4&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">公共服务</a></li>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=5&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">环境整治</a></li>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=6&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">基层组织建设</a></li>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=7&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">资金投入</a></li>
			<li><a href="${path }/baseinfo/newVillageBasicManage/dispatchOpearteForMaintain.action?dataType=8&organization.id=${organization.id}&newVillageBasic.id=${newVillageBasic.id}&mode=${mode}&isPlaning=${isPlaning}">农民经济收入</a></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	// 列表信息 和 柱图 饼图   外层容器计算高度
	$.sigmaReportLayout();
	$.loadingComp("close");
})


function enableOrDisableColumn(i){
	if(isGrid()){
		$("#chartsTabs").tabs("select", 2);
		$("#chartsTabs").tabs("disable", 0);
		$("#chartsTabs").tabs("disable", 1);
	}else{
		$("#chartsTabs").tabs("enable", 0);
		$("#chartsTabs").tabs("enable", 1);
	}
}
</script>