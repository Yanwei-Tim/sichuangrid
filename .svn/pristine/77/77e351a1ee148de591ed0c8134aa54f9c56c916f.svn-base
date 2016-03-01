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
	<div class="container container_24" style="height: 100px">
		<div class="grid_3 lable-right">
			<label class="form-lbl"><s:property value="#getEvaluateById.great.name" />：</label>
		</div>
		<div class="grid_6">
			<input type="text" style="width: 50px" class="form-txt" disabled="disabled" id="score1" name="great.startScore" readonly="readonly" value="<s:property value="#getEvaluateById.great.startScore" />"/>
			至
			<input type="text" style="width: 50px" class="form-txt" id="score2" name="great.endScore" readonly="readonly" value="<s:property value="#getEvaluateById.great.endScore" />" /><em class="form-req">(含)</em>
		</div>
		<div class="grid_3 lable-right">
		<label class="form-lbl">
			<s:property value="#getEvaluateById.good.name" />：
		</label>
		</div>
		<div class="grid_6">
				<input type="text" style="width: 50px" class="form-txt" id="score3" name="good.startScore" readonly="readonly" value="<s:property value="#getEvaluateById.good.startScore" />"/>
				至
				<input type="text" style="width: 50px" class="form-txt" id="score4" name="good.endScore" readonly="readonly" value="<s:property value="#getEvaluateById.good.endScore" />" /><em class="form-req">(含)</em>
		</div>
		<div class='clearLine'></div>
		<div class="grid_3 lable-right">
			<label class="form-lbl"><s:property value="#getEvaluateById.qualified.name" />：</label>
		</div>
		<div class="grid_6">
			<input type="text" style="width: 50px" class="form-txt" id="score5" name="qualified.startScore" readonly="readonly" value="<s:property value="#getEvaluateById.qualified.startScore" />" />
			至
			<input type="text" style="width: 50px" class="form-txt" id="score6" name="qualified.endScore" readonly="readonly" value="<s:property value="#getEvaluateById.qualified.endScore" />" /><em class="form-req">(含)</em>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl"><s:property value="#getEvaluateById.failed.name" />：</label>
		</div>
		<div class="grid_6">
			<input type="text" style="width: 50px" class="form-txt" id="score7" name="failed.startScore" readonly="readonly" value="<s:property value="#getEvaluateById.failed.startScore" />"/>
			至
			<input type="text" readonly="readonly" style="width: 50px" id="score8" disabled="disabled" class="form-txt" name="failed.endScore" value="<s:property value="#getEvaluateById.failed.endScore" />"/><em class="form-req">(含)</em>
		</div>
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
		<input type="hidden" name="evaluate.id" value="<s:property value="#parameters.evaluateId" />" readonly="readonly" />
	</div>
</div>
<div class='clearLine'></div>
<div style="width:100%">
		<table id="itemsList" style="width: 95%"></table>
</div>

<script type="text/javascript">
<pop:formatterProperty name="ruleType" domainName="@com.tianque.domain.property.PropertyTypes@DETAILED_RULE_TYPE" />
function ruleTypeDataFormatter(el,options,rowData){
	return ruleTypeData[rowData.ruleType];
}
function getTreeGrid(){
	$("#itemsList").jqTreeGrid({
	   	 url: "${path}/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action?evaluate.id=<s:property value='#parameters.id'/>",
		 height : 240,
		 width : 820,
	   	 colModel:[
	   		{name:"content",width:120,label:"考核评估细则",sortable:false},
	   		{name:"standardScore",label:"标准分",align:"center",width:15,sortable:false},
	   		{name:"selfAssessmentScore",label:"自评分",width:15,align:"center",sortable:false},
	   		{name:"score",label:"考核评分",align:"center",width:20},
	   		{name:"scoreReason",label:"评分依据",sortable:false,width:60},
	   		{name:"id", width:0	,key:true,sortable:false,hidden:true}
	   	 ],
	   	 treeReader : {
	   	   	level_field: "treelevel",
			parent_id_field: "parentId",
			leaf_field: "leaf",
			expanded_field:"expanded"
	   	 },
		loadComplete:function(){
			calculateTotalScore();
			$("#score1").val($("#standardEvaluateTotalScore").text());
			$("#score8").val(0);
		}
	
	});
}
function calculateTotalScore(){
	var totalScore=0;
	var evaluateScore=0;
	var selfEvaluateScore=0;
	var checkboxs=$("#itemsList tr.ui-row-ltr");
	checkboxs.each(function(i,n){
		if($(this).attr("id")){
			var rowData = $("#itemsList").getRowData($(this).attr("id"));
			if(rowData.treelevel==1 || $(rowData.treelevel).text()==1){
				if("" != rowData.standardScore && null != rowData.standardScore){
					totalScore=totalScore+parseFloat(rowData.standardScore);
					if("" !=rowData.score && null != rowData.score)
						evaluateScore=evaluateScore + parseFloat(rowData.score);
					if("" !=rowData.selfAssessmentScore && null != rowData.selfAssessmentScore)
						selfEvaluateScore = selfEvaluateScore + parseFloat(rowData.selfAssessmentScore)
				}
			}
		}
	})
	$("#standardEvaluateTotalScore").text(totalScore);
	$("#scoreEvaluateTotalScore").text(evaluateScore);
	$("#selfEvaluateTotalScore").text(selfEvaluateScore);
}
$(document).ready(function(){
	getTreeGrid();
});
</script>