<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="addClaimProject" class="container container_24">
		<input type="hidden" id="orgId"  value="organization.id"/>
		<input type="hidden" id="type" value="${param.type }">
		<input type="hidden" id="modeClaim" value="${param.mode }">
		<div class="btnbanner btnbannerData" >
			<div class="ui-widget autosearch">
			    <input type="text" value="快速搜索项目名称" id="_deptName" maxlength="24" class="basic-input" onblur="value=(this.value=='')?'快速搜索项目名称':this.value;" onfocus="value=(this.value=='快速搜索项目名称')?'':this.value;" style="width:300;"/>
			</div>
			<a href="javascript:;" id="_fastSearch" class="search"><span>搜索</span></a>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div style="width: 100%;">
			<table id="memberHasVolunteerJobsList"></table>
			<div id="memberHasVolunteerJobsListPager"></div>
		</div>
</div>

<script type="text/javascript">
function callback(){
    var dfop = {
    		path:'${path}',
        	orgId:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>'
    }
    TQ.addVolunteerJobs(dfop);
    
}
$.cacheScript({
    url:'/resource/getScript/partyBuilding/doubleRegActivities/memberEnroll/addVolunteerJobs.js',
    callback: callback
})
</script>