<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="${path}/common/orgSelectedComponent.jsp" />
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入监控视频编号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入监控视频编号':this.value;" onfocus="value=(this.value=='请输入监控视频编号')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchVideoSystem">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="addVideoSystem">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteVideoSystem">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
		<pop:JugePermissionTag ename="cancelAttendedVideoSystem">
			<a id="cancelEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-CancelAttention"></strong>取消关注</span></a>
		</pop:JugePermissionTag> <pop:JugePermissionTag ename="attendedVideoSystem">
			<a id="reEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-refocusOn"></strong>重新关注</span></a>
		</pop:JugePermissionTag>
		</div>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	<%-- 	<a id="dataUpdate" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>通道更新</span></a> --%>
		<pop:JugePermissionTag ename="transferVideoSystem">
			<a id="transfer" href="javascript:void(0)"><span>转移</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="videoSystemList">
		</table>
		<div id="videoSystemListPager"></div>
	</div>
	<div id="videoSystemDialog"></div>
	<div id="noticeDialog"></div>
	<div id="videoSystemMaintanceDialog"></div>
	<div id="gisvideoSystemDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 600;
var dialogHeight = 320;
var gisType="2D";
var useOrgDepartmentNo = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getDepartmentNo()'/>";
// 改变组织机构树时加载列表
function onOrgChanged(orgId,isgrid){
	var initParam = {
		"organization.id":orgId,
		"searchVideoSystemVo.isEmphasis":0,
		"searchVideoSystemVo.videoNo":''
	}
	$("#videoSystemList").setGridParam({
 		url:'${path}/videoSystemManage/findVideosystemPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#videoSystemList").setPostData(initParam);
	$("#videoSystemList").trigger("reloadGrid");
}

function viewVideoSystem(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#videoSystemList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#videoSystemDialog").createDialog({
		width:dialogWidth,
		height:400,
		title:"查看监控视频信息",
 		url:'${path}/digitalCity/publicSecurity/publicSecurityViewTag.jsp?publicSecurityType=<s:property value="@com.tianque.publicSecurity.constant.PublicSecurityType@VIDEOSYSTEM"/>&mode=view&id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function callback(){
    var dfop = {
    	hasUpdateVideoSystem:'<pop:JugePermissionTag ename="updateVideoSystem">true</pop:JugePermissionTag>',
    	hasDeleteVideoSystem:'<pop:JugePermissionTag ename="deleteVideoSystem">true</pop:JugePermissionTag>',
    	useOrgDepartmentNo:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getDepartmentNo()"/>',
    	publicSecurityType:'<s:property value="@com.tianque.publicSecurity.constant.PublicSecurityType@VIDEOSYSTEM"/>',
    	townOrganization:'<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>',
    	hasViewVideoSystem:'<pop:JugePermissionTag ename="viewVideoSystem">true</pop:JugePermissionTag>',
    	moduleCName:'${moduleCName}'
    }
    TQ.videoSystemList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/digitalCity/publicSecurity/videoSystemManagement/videoSystemList.js',
    callback: callback
})
</script>