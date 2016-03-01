<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:bean name="java.util.Date" id="date"></s:bean>
<s:date var="formateDate" nice="false" name="#date" format="yyyy" />
<s:action name="getEvaluateAllYears" var="getEvaluateAllYears" namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
	<s:param name="isChildOrgGetYears" value="true"></s:param>
</s:action>
<s:action name="findSelfEvaluatesByOrgIdAndYearAndState" var="getEvaluateResultByOrgId" namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
	<s:param name="evaluate.year" value="@java.lang.Long@parseLong(#formateDate)"></s:param>
</s:action>
<div class="content">
	<input id="evaluateState" type="hidden" value="<s:property value="#getEvaluateResultByOrgId.evaluate.state"/>"/>
	<div class="ui-corner-all" id="nav">
		<div class="grid_2 lable-right">
			<label class="form-lbl">考核年度：</label>
			<select name="evaluate.year" id="getEvaluateYear">
				<s:iterator value="#getEvaluateAllYears.evaluateYears">
	 				 <option value='<s:property />'><s:property />年</option>
				</s:iterator>
			</select>
			<label class="form-lbl">标题：</label>
			<select name="evaluate.id" id="getEvaluatesTitle">
				<s:iterator value="#getEvaluateResultByOrgId.evaluates" var="e">
					<option value="<s:property value='#e.id'/>"><s:property value="#e.title" /></option>
				</s:iterator>
			</select>
		</div>
		<pop:JugePermissionTag ename="selfScore">
			<a id="selfScore" href="javascript:void(0)"><span>自评</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="scoreReport">
			<a id="scoreReport" href="javascript:void(0)"><span>上报</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="width: 100%;">
		<table id="itemsList"></table>
		<div id="itemsListPager"></div>
	</div>
	<div id="evaluateDialog"></div>
</div>
<script type="text/javascript">

var selectedRowId;
function validateScoreButton(id){
	var data = $("#itemsList").getRowData(id);
	if(data.leaf && "" == data.standardScore){
		return true;
	}
	return false;
}

//toggleButtons();
function updateTotalSelfAssessmentScore(){
	var trs = $("#itemsList").find("tr");
	var resultData = 0;
	for(var i=1; i<trs.length; i++){
		var rowData = $("#itemsList").getRowData($(trs[i]).attr("id"));
		if((rowData.treelevel==1||parseFloat($(rowData.treelevel).text())==1)
				&& rowData.selfAssessmentScore!=""
					&& rowData.selfAssessmentScore != null
						&& rowData.selfAssessmentScore!=undefined){
			resultData = resultData + parseFloat($("#itemsList").getRowData($(trs[i]).attr("id")).selfAssessmentScore);
		}
	}
	if(resultData!=0){
		$("#selfAssessmentTotalScore").text(resultData);
	} else {
		$("#selfAssessmentTotalScore").text(0);
	}
}
function callback(){
    var dfop = {
    	evaluateState:'<s:property value="#getEvaluateResultByOrgId.evaluate.state" />',
    	evaluateStateValue:'<s:property value="@com.tianque.service.state.EvaluateState@REPORT" />',
    	organizationId:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>',
    	evaluateId:'<s:property value="#getEvaluateResultByOrgId.evaluate.id" />',
    	evaluateYear:'<s:property value="#getEvaluateResultByOrgId.evaluate.year"/>',
    	nullEvaluate:'<s:property value="#getEvaluateResultByOrgId.evaluate==null"/>',
    	templateTotalScoreValue:'<s:property value="#getEvaluateResultByOrgId.evaluate.templateTotalScore"/>'
    	
    }
    TQ.selfEvaluate(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/selfEvaluate.js',
    callback: callback
})

</script>