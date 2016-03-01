<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="${path}/common/orgSelectedComponent.jsp?plateName=party"/>
			<div class="ui-widget autosearch" id="basic-input">
				<input class="basic-input" type="text" value="请输入服务项目名称" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入服务项目名称':this.value;" onfocus="value=(this.value=='请输入服务项目名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchServiceProject">
				<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
			</pop:JugePermissionTag>
			<span class="lineBetween "></span>
			<pop:JugePermissionTag ename="addServiceProject">
				<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="updateServiceProject">
				<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteServiceProject">
				<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="viewServiceProject">
				<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
			</pop:JugePermissionTag>
			<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="serviceprojectList">
		</table>
		<div id="serviceprojectListPager"></div>
	</div>
	<div id="serviceprojectDialog"></div>
	<div id="noticeDialog"></div>
	<div id="serviceprojectMaintanceDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 450;

// Formatter
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateServiceproject'><a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteServiceproject'><a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}


// 改变组织机构树时加载列
function onOrgChanged(orgId,isgrid){
	var initParam = {
		"organization.id": orgId,
		"searchServiceProjectVo.projectName":''
	}
	$("#serviceprojectList").setGridParam({
 		url:'${path}/serviceprojectManage/findServiceProjectPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#serviceprojectList").setPostData(initParam);
	$("#serviceprojectList").trigger("reloadGrid");
}


function callback(){
	var dfop = {
		path:'${path}',
		orgLevel:'<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>',
		viewServiceproject:'<pop:JugePermissionTag ename="viewServiceProject">true</pop:JugePermissionTag>'
	}
	TQ.serviceProjectList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/doubleRegActivities/serviceProjectManage/serviceProjectList.js',
	callback: callback
})
</script>