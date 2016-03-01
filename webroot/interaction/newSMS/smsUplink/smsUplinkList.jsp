<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<pop:JugePermissionTag ename="viewOtherSmsUplink">
				<a href="javascript:;" class="globalOrgBtnMod" id="smsOrgBtnMod"><span id="smsOrgBtn"></span></a>
				<span class="lineBetween firstDOM"></span>
			</pop:JugePermissionTag>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入发送者姓名" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入发送者姓名':this.value;" onfocus="value=(this.value=='请输入发送者姓名')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addSmsUplink">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新建短信</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="smsUplinkList">
		</table>
		<div id="smsUplinkListPager"></div>
	</div>
	<div id="smsUplinkDialog"></div>
	<div id="addresseeDialog"></div>
	<div id="noticeDialog"></div>
	<div id="smsUplinkMaintanceDialog"></div>
	
</div>
<script type="text/javascript">
function callback(){
    var dfop = {
    		changeOrg:"<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getId()'/>",
    		orgName:"<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()'/>"
    }
    TQ.smsUplinkList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/newSMS/smsUplink/smsUplinkList.js',
    callback: callback
})
</script>