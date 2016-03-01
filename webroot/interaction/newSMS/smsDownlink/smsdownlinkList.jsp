<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<select id="isRead"  class="basic-input" >
				<option  value="">全部短信</option>
				<option  value="0">未阅读短信</option>
				<option  value="1">已阅读短信</option>
			</select>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入发送者手机号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入发送者手机号':this.value;" onfocus="value=(this.value=='请输入发送者手机号')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="smsdownlinkList">
		</table>
		<div id="smsdownlinkListPager"></div>
	</div>
	<div id="smsdownlinkDialog"></div>
	<div id="noticeDialog"></div>
	<div id="smsdownlinkMaintanceDialog"></div>
</div>
<script type="text/javascript">
function callback(){
    var dfop = {
    		
    }
    TQ.smsdownlinkList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/newSMS/smsDownlink/smsdownlinkList.js',
    callback: callback
})

</script>