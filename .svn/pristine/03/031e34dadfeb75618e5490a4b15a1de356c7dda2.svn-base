<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="${path}/common/orgSelectedComponent.jsp" />
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入卡口编号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入卡口编号':this.value;" onfocus="value=(this.value=='请输入卡口编号')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchBayonet">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="addBayonet">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteBayonet">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
		 <pop:JugePermissionTag ename="cancelAttendedBayonet">
			<a id="cancelEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-CancelAttention"></strong>取消关注</span></a>
		</pop:JugePermissionTag> <pop:JugePermissionTag ename="attendedBayonet">
			<a id="reEmphasise" href="javascript:void(0)"><span><strong
				class="ui-ico-refocusOn"></strong>重新关注</span></a>
		</pop:JugePermissionTag>
		</div>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="transferBayonet">
			<a id="transfer" href="javascript:void(0)"><span>转移</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="bayonetList">
		</table>
		<div id="bayonetListPager"></div>
	</div>
	<div id="bayonetDialog"></div>
	<div id="noticeDialog"></div>
	<div id="bayonetMaintanceDialog"></div>
	<div id="gisbayonetDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 600;
var dialogHeight = 320;
var gisType="2D";
//改变组织机构树时加载列表
function onOrgChanged(orgId,isgrid){
	var initParam = {
		"organization.id":orgId,
		"searchBayonetVo.isEmphasis":0,
		"searchBayonetVo.bayonetNo":''
	}
	$("#bayonetList").setGridParam({
 		url:'${path}/bayonetManage/findBayonetPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#bayonetList").setPostData(initParam);
	$("#bayonetList").trigger("reloadGrid");
}

function viewBayonet(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#bayonetList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#bayonetDialog").createDialog({
		width:dialogWidth,
		height:400,
		title:"查看卡口信息",
 		url:'${path}/digitalCity/publicSecurity/publicSecurityViewTag.jsp?publicSecurityType=<s:property value="@com.tianque.publicSecurity.constant.PublicSecurityType@BAYONET"/>&mode=view&id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function callback(){
    var dfop = {
    	hasUpdateBayonet:'<pop:JugePermissionTag ename="updateBayonet">true</pop:JugePermissionTag>',
    	hasDeleteBayonet:'<pop:JugePermissionTag ename="deleteBayonet">true</pop:JugePermissionTag>',
    	useOrgDepartmentNo:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getDepartmentNo()"/>',
    	publicSecurityType:'<s:property value="@com.tianque.publicSecurity.constant.PublicSecurityType@BAYONET"/>',
    	townOrganization:'<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>',
    	hasViewBayonet:'<pop:JugePermissionTag ename="viewBayonet">true</pop:JugePermissionTag>',
    	moduleCName:'${moduleCName}'
    }
    TQ.bayonetList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/digitalCity/publicSecurity/bayonetManagement/bayonetList.js',
    callback: callback
})
</script>