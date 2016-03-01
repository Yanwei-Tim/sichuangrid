<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getEvaluateById" var="getEvaluateById" namespace="/evaluate/evaluateManage" >
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
	<s:param name="rows" value="5"></s:param>
	<s:param name="evaluate.id" value="#parameters.id"></s:param>
</s:action>
<div class="ui-corner-all" id="nav">
		<input type="text" style="width: 36px" disabled="disabled" value="<s:property value='#getEvaluateById.evaluate.year' />" />&nbsp;年
		<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标题：</label>
		<input type="text" style="width: 200px;align: center" disabled="disabled" maxlength="16" value="<s:property value='#getEvaluateById.evaluate.title' />"/>
</div>
<div class="ui-corner-all" id="nav">
	<div class="container container_24" style="height: 30px">
		<div class='clearLine'></div>
		<div class="grid_3 lable-right">
				<label class="form-lbl">标准总分：</label>
		</div>
		<div class="grid_2" id="standardEvaluateTotalScore">
		</div>
		<div class="grid_3 lable-right">
				<label class="form-lbl">考核总分：</label>
		</div>
		<div class="grid_2" id="scoreEvaluateTotalScore">
		</div>
		<div class="grid_3 lable-right">
				<label class="form-lbl">自评总分：</label>
		</div>	
		<div class="grid_2" id="selfEvaluateTotalScore">
		</div>
		<div class="grid_3 lable-right">
				<label class="form-lbl">成绩等级：</label>
		</div>
		<div class="grid_4"><s:property value='#getEvaluateById.evaluate.evaluateResult' /></div>
		<input type="hidden" name="evaluate.id" value="<s:property value="#parameters.evaluateId" />" readonly="readonly" />
	</div>
</div>
<div class='clearLine'></div>
<div id="evaluateResultDailog"></div>
<div style="width: 100%">
		<table id="itemsList" style="width: 100%"></table>
</div>

<script type="text/javascript">

<pop:formatterProperty name="ruleType" domainName="@com.tianque.domain.property.PropertyTypes@DETAILED_RULE_TYPE" />
function ruleTypeDataFormatter(el,options,rowData){
	return ruleTypeData[rowData.ruleType];
}

function callback(){
    var dfop = {
    	evaluateId:'<s:property value="#parameters.id"/>',
    	organizationId:'<s:property value="#parameters.orgId"/>'
    }
    TQ.viewYearsEvaluateResultDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/viewYearsEvaluateResultDlg.js',
    callback: callback
})


</script>