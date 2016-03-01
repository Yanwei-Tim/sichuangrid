<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getEvaluateById" var="getEvaluateById"
	namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.id" value="#parameters.id"></s:param>
</s:action>
<div class="ui-corner-all" id="nav">
<input type="text" style="width: 36px"
	readonly 
	value="<s:property value='#getEvaluateById.evaluate.year' />" />&nbsp;年
<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标题：</label>
<input type="text"
	style="width: 200px; align: center" readonly
	 maxlength="16" value="${param.title}" />
</div>
<div class="ui-corner-all" id="nav">
<div class="container container_24" style="height: 70px">
<div class="grid_3 lable-right"><label class="form-lbl" id="label1"><s:property
	value="#getEvaluateById.great.name" />：</label></div>
<div class="grid_6"><input type="text" style="width: 50px"
	class="form-txt" readonly id="score1"
	name="great.startScore" readonly="readonly"
	value="<s:property value="#getEvaluateById.great.startScore" />" />
至 <input type="text" style="width: 50px" class="form-txt" id="score2"
	name="great.endScore" readonly
	value="<s:property value="#getEvaluateById.great.endScore" />" /><em
	class="form-req">(含)</em></div>
<div class="grid_3 lable-right"><label class="form-lbl"> <s:property
	value="#getEvaluateById.good.name" />： </label></div>
<div class="grid_6"><input type="text" style="width: 50px"
	class="form-txt" id="score3" name="good.startScore" readonly
	value="<s:property value="#getEvaluateById.good.startScore" />" />
至 <input type="text" style="width: 50px" class="form-txt" id="score4"
	name="good.endScore" readonly
	value="<s:property value="#getEvaluateById.good.endScore" />" /><em
	class="form-req">(含)</em></div>
<div class='clearLine'></div>
<div class="grid_3 lable-right"><label class="form-lbl"><s:property
	value="#getEvaluateById.qualified.name" />：</label></div>
<div class="grid_6"><input type="text" style="width: 50px"
	class="form-txt" id="score5" name="qualified.startScore"
	readonly
	value="<s:property value="#getEvaluateById.qualified.startScore" />" />
至 <input type="text" style="width: 50px" class="form-txt" id="score6"
	name="qualified.endScore" readonly
	value="<s:property value="#getEvaluateById.qualified.endScore" />" /><em
	class="form-req">(含)</em></div>
<div class="grid_3 lable-right"><label class="form-lbl"><s:property
	value="#getEvaluateById.failed.name" />：</label></div>
<div class="grid_6"><input type="text" style="width: 50px"
	class="form-txt" id="score7" name="failed.startScore"
	readonly
	value="<s:property value="#getEvaluateById.failed.startScore" />" />
至 <input type="text" readonly style="width: 50px" id="score8"
	readonly class="form-txt" name="failed.endScore"
	value="<s:property value="#getEvaluateById.failed.endScore" />" /><em
	class="form-req">(含)</em></div>
<div class="grid_2 lable-right"><label class="form-lbl">总分：</label>
</div>
<div class="grid_4" id="standardEvaluateTotalScore"></div>
<input type="hidden" name="evaluate.id"
	value="<s:property value="#parameters.evaluateId" />"
	readonly /></div>
</div>
<div class='clearLine'></div>
<div style="width: 100%">
<table id="itemsList" style="width: 100%"></table>
</div>

<script type="text/javascript">
<pop:formatterProperty name="ruleType" domainName="@com.tianque.domain.property.PropertyTypes@DETAILED_RULE_TYPE" />
function ruleTypeDataFormatter(el,options,rowData){
	return ruleTypeData[rowData.ruleType];
}
function calculateTotalScore(){
	var totalScore=0;
	var checkboxs=$("#itemsList tr.ui-row-ltr");
	$.each(checkboxs,function(i,n){
		if($(n).attr("id")){
			var rowData = $("#itemsList").getRowData($(n).attr("id"));
			if(rowData.treelevel==1 || $(rowData.treelevel).text()==1){
				if("" != rowData.standardScore && null != rowData.standardScore){
					totalScore=totalScore+parseFloat(rowData.standardScore);
				}
			}
		}
	})
	$("#standardEvaluateTotalScore").text(totalScore);
}
function getTreeGrid(){
	$("#itemsList").jqTreeGrid({
	   	 url: "${path}/evaluate/evaluateManage/getDetailedRulesByEvaluateIdByEncrypt.action?evaluate.id=<s:property value='#parameters.id'/>",
		 height : 320,
		 width : 820,
	   	 colModel:[
	   		{name:"content",label:"考核评估细则",sortable:false},
	   		{name:"standardScore",label:"标准分",width:10,sortable:false},
	   		{name:"ruleType",label:"类型",width:20,sortable:false,formatter: ruleTypeDataFormatter},
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
$(document).ready(function(){
	if($("#label1").text() == "：" || $("#label1").text() == null ){
		$.messageBox({ message:"此标准已经不存在!" , level:"error"});
		$("#evaluateDialog").dialog("close");
		$("#evaluateList").trigger("reloadGrid");
	}
	getTreeGrid();
});
</script>