<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	request.setAttribute("type",request.getParameter("type"));
 %> 
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="userState">
				<select id="isEmphasise" class="basic-input">
					<option value="-1">全部</option>
					<option value="0" selected="selected">现在关注</option>
					<option value="1">曾经关注</option>
				</select>
			</div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入党组织报到名称" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入党组织报到名称':this.value;" onfocus="value=(this.value=='请输入党组织报到名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchPartyOrgReport">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addPartyOrgReport">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePartyOrgReport">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePartyOrgReport">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewPartyOrgReport">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a href="javascript:;" class="nav-dropdownBtn" id="setEmphasise"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
		 <pop:JugePermissionTag ename="cancelAttendedPartyOrgReport">
			<a id="cancelEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-CancelAttention"></strong>取消关注</span></a>
		</pop:JugePermissionTag> <pop:JugePermissionTag ename="attendedPartyOrgReport">
			<a id="reEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-refocusOn"></strong>重新关注</span></a>
		</pop:JugePermissionTag>
		</div>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="partyorgReportList">
		</table>
		<div id="partyorgReportListPager"></div>
	</div>
	<div id="partyorgReportDialog"></div>
	<div id="noticeDialog"></div>
	<div id="partyorgReportMaintanceDialog"></div>
	<div id="claimProjectNameDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="partyOrgType" domainName="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"></pop:formatterProperty>
var dialogWidth = 800;
var dialogHeight = 500;
var type="${type}";

// Formatter
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updatePartyOrgReport'><a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deletePartyOrgReport'><a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}

//改变组织机构树时加载列
function onOrgChanged(orgId,isgrid){
	var initParam = {
		"organization.id": orgId,
		"searchPartyOrgReportVo.isEmphasis":$('#isEmphasise option:selected').val(),
		"searchPartyOrgReportVo.type":type
		
	}
	$("#partyorgReportList").setGridParam({
 		url:'${path}/partyorgReportManage/findPartyorgReportPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#partyorgReportList").setPostData(initParam);
	$("#partyorgReportList").trigger("reloadGrid");
}
function callback(){
	var dfop = {
		path:'${path}',
		viewPartyOrgReport:'<pop:JugePermissionTag ename="viewPartyOrgReport">true</pop:JugePermissionTag>'
	}
	TQ.partyOrgReportList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/doubleRegActivities/partyorgReportManage/partyOrgReportList.js',
	callback: callback
}) 
	

</script>