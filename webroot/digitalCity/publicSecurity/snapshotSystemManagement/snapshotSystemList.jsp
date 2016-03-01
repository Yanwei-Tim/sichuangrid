<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="${path}/common/orgSelectedComponent.jsp" />
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入抓拍系统编号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入抓拍系统编号':this.value;" onfocus="value=(this.value=='请输入抓拍系统编号')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchSnapshotSystem">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="addSnapshotSystem">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteSnapshotSystem">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
		 <pop:JugePermissionTag ename="cancelAttendedSnapshotSystem">
			<a id="cancelEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-CancelAttention"></strong>取消关注</span></a>
		</pop:JugePermissionTag> <pop:JugePermissionTag ename="attendedSnapshotSystem">
			<a id="reEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-refocusOn"></strong>重新关注</span></a>
		</pop:JugePermissionTag>
		</div>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="transferSnapshotSystem">
			<a id="transfer" href="javascript:void(0)"><span>转移</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="snapshotSystemList">
		</table>
		<div id="snapshotSystemListPager"></div>
	</div>
	<div id="snapshotSystemDialog"></div>
	<div id="noticeDialog"></div>
	<div id="snapshotSystemMaintanceDialog"></div>
	<div id="gissnapshotSystemDialog"></div>
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
		"searchSnapshotSystemVo.isEmphasis":0,
		"searchSnapshotSystemVo.snapshotNo":''
	}
	$("#snapshotSystemList").setGridParam({
 		url:'${path}/snapshotSystemManage/findSnapshotsystemPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#snapshotSystemList").setPostData(initParam);
	$("#snapshotSystemList").trigger("reloadGrid");
}

function viewSnapshotSystem(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#snapshotSystemList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#snapshotSystemDialog").createDialog({
		width:dialogWidth,
		height:400,
		title:"查看抓拍系统信息",
 		url:'${path}/digitalCity/publicSecurity/publicSecurityViewTag.jsp?publicSecurityType=<s:property value="@com.tianque.publicSecurity.constant.PublicSecurityType@SNAPSHOTSYSTEM"/>&mode=view&id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function callback(){
    var dfop = {
    	hasUpdateSnapshotSystem:'<pop:JugePermissionTag ename="updateSnapshotSystem">true</pop:JugePermissionTag>',
    	hasDeleteSnapshotSystem:'<pop:JugePermissionTag ename="deleteSnapshotSystem">true</pop:JugePermissionTag>',
    	useOrgDepartmentNo:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getDepartmentNo()"/>',
    	publicSecurityType:'<s:property value="@com.tianque.publicSecurity.constant.PublicSecurityType@SNAPSHOTSYSTEM"/>',
    	townOrganization:'<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>',
    	hasViewSnapshotSystem:'<pop:JugePermissionTag ename="viewSnapshotSystem">true</pop:JugePermissionTag>',
    	moduleCName:'${moduleCName}'
    }
    TQ.snapshotSystemList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/digitalCity/publicSecurity/snapshotSystemManagement/snapshotSystemList.js',
    callback: callback
})
</script>