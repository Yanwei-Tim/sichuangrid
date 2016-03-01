<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getEvaluateAllYears" var="getEvaluateAllYears" namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<div class="ui-corner-all" id="nav">
	<div class="grid_8">
		<select id="selectYear">
			<s:iterator value="#getEvaluateAllYears.evaluateYears">
 				 <option value='<s:property />'><s:property /></option>
			</s:iterator>
	   	</select>
	</div>
	<div class="grid_8">
		<select id="selectState">
		   	<option value="0">全部状态</option>
		   	<option value='<s:property value="@com.tianque.service.state.EvaluateState@ACTIVE" />'>未上报</option>
		   	<option value='<s:property value="@com.tianque.service.state.EvaluateState@REPORT" />'>已上报</option>
	   	</select>
	</div>
	<pop:JugePermissionTag ename="urgeEvaluate">
		<a id="urge" href="javascript:void(0)"><span>催报</span></a>
	</pop:JugePermissionTag>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="evaluateList"></table>
	<div id="evaluateListPager"></div>
</div>
<div id="evaluateDialog"></div>
<div id="evaluateMaintanceDialog"></div>
<script type="text/javascript">
function callback(){
    var dfop = {
    	organizationId:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>'
    }
    TQ.lowerEvaluateResultList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/lowerEvaluateResultList.js',
    callback: callback
})

</script>