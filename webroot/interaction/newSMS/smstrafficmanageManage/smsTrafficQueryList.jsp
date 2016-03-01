<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="center-left" style="top:2px;" >
	<div>
		<div class="ui-widget">
			<input id="org_autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>

<div class="center-right">
	<div class="content">
		<input type="hidden" id="orgName" name="OrgName" value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().orgName"/>"  class="searchText" />
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="orgTrafficList"> </table>
<!-- 			<div id="orgTrafficListPager"></div> -->
		</div>
	</div>
</div>
<div id="deptTrafficDialog"></div>
<div id="trafficDialog"></div>


<script type="text/javascript">
function setOperator(orgId){
	var smstrafficmanage = $("#orgTrafficList").getRowData(orgId);
	$("#trafficDialog").createDialog({
		width:240,
		height:200,
		title:'设置流量',
	 	url:PATH+'/smstrafficmanageManage/dispatchOperate.action?mode=set&smstrafficmanage.orgId='+smstrafficmanage.encryptId,
		buttons: {
	   		"保存" : function(event){
	   			$("#maintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function callback(){
    var dfop = {
    		orgTypeCurrent:'<s:property value="#getFullOrgById.organization.orgType.internalId"/>'
    }
    TQ.smsTrafficQueryList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/newSMS/smstrafficmanageManage/smsTrafficQueryList.js',
    callback: callback
})
</script>