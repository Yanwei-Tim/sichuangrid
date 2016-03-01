<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<s:action name="getEvaluateAllYears" var="getEvaluateAllYears" namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>

<div class="ui-corner-all" id="nav">
	<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
		<label class="form-lbl" style=" display:none">年度：</label>
		<select name="evaluate.year" id="getEvaluateYear">
			<s:iterator value="#getEvaluateAllYears.evaluateYears">
 				 <option value='<s:property />'><s:property />年</option>
			</s:iterator>
			<option value="">所有年份</option>
		</select>
	</div>
	<pop:JugePermissionTag ename="searchEvaluateHistory">
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
	</pop:JugePermissionTag>
	<span class="lineBetween "></span>
	
	<pop:JugePermissionTag ename="viewEvaluateHistory">
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
	</pop:JugePermissionTag>
	<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="evaluateList"> </table>
	<div id="evaluateListPager"></div>
</div>
<div id="evaluateDialog"></div>
<script type="text/javascript">
function callback(){
    var dfop = {
    	evaluateState:'<s:property value="@com.tianque.service.state.EvaluateState@PIGEONHOLE"/>',
    	viewEvaluateHistory:'<pop:JugePermissionTag ename="viewEvaluateHistory">true</pop:JugePermissionTag>'
    }
    TQ.evaluateResultList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/evaluateResultList.js',
    callback: callback
})


</script>