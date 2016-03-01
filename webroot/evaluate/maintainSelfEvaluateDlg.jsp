<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<s:action  var="getDetailedRuleById" executeResult="false" name="getDetailedRuleById" namespace="/evaluate/detailedRuleManage">
	<s:param name="detailedRule.id" value="#parameters.detailedRuleId[0]"></s:param>
</s:action>
<s:bean name="java.util.Date" id="date"></s:bean>
<s:date name="#date" format="yyyy" var="year" nice="false"/>
<form id ="maintainForm" action="/evaluate/evaluateManage/selfAssessment.action" method="post">
	<input id="selectedDaily" name="selectedDailyIds" type="hidden"></input>
	<input name="detailedRule.id" value="${getDetailedRuleById.detailedRule.id}" type="hidden"></input>
	<div class="container container_24" >
			<div class="grid_4 lable-right">
				<label class="form-lbl">细则描述：</label>
			</div>
			<div class="grid_20 heightAuto">
				<textarea style="height:100px;" name="detailedRule.content" class="form-txt" disabled="disabled">${getDetailedRuleById.detailedRule.content }</textarea>
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">类别：</label>
			</div>
			<div class="grid_20">
				<select name="detailedRule.ruleType.id" class="form-select" disabled="disabled">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETAILED_RULE_TYPE" defaultValue="${getDetailedRuleById.detailedRule.ruleType.id}" />
				</select>
			</div>
			<div class='clearLine'></div>
			
			<div class="grid_4 lable-right">
			<em class="form-req">*</em>
				<label class="form-lbl">自评分：</label>
			</div>
			<div class="grid_20">
				<input id="detailedRuleSelfAssessmentScore" class="form-txt" name="detailedRule.selfAssessmentScore" value="${getDetailedRuleById.detailedRule.selfAssessmentScore}"/>
			</div>
			
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">自评说明：</label>
			</div>
			<div class="grid_20 heightAuto">
				<textarea class="form-txt" id="summary" name="detailedRule.summary" style="height:70px;" maxlength="60">${getDetailedRuleById.detailedRule.summary }</textarea>
			</div>
	</div>
</form>
<script>

function getDetailedRuleById(selfDoc,id){
	$.ajax({
		async:false,
		url: "${path}/evaluate/detailedRuleManage/getDetailedRuleById.action",
		data:{
			"detailedRule.id" : id
		},
		success:function(data){
			$("#selectedDaily").val("");
			$("input[name='detailedRule.id']").val(data.id);
			$("textarea[name='detailedRule.content']").val(data.content);
			if(data.selfAssessmentScore){
				$("input[name='detailedRule.selfAssessmentScore']").val(data.selfAssessmentScore);
			}else{
				$("input[name='detailedRule.selfAssessmentScore']").val("");
			}
			if(data.summary){
				$("textarea[name='detailedRule.summary']").val(data.summary);
			}else{
				$("textarea[name='detailedRule.summary']").val("");
			}
			$("select[name='detailedRule.ruleType.id']").val(data.ruleType.id);
			selfDoc.setSelection(data.id);
		}
	});	
}

function getPrevDetailedRuleId(rowid){
	var selfDoc = $("#itemsList");
	var prevRow=$("#" + rowid).prev();
	if(prevRow.hasClass("jqgfirstrow")){
		return -1;
	}
	var prevRowData = selfDoc.getRowData($(prevRow).attr("id"));
	if(!validateScoreButton(prevRowData.id)){
		return getPrevDetailedRuleId(prevRowData.id);
	}else{
		return prevRowData.id;
	}
}
var isComplete = false;
function toggleMaintainSelfButton(){
	var buttons = $(".ui-dialog-buttonset",$("#evaluateDialog").next()).children();
	var rowId = $("#itemsList").getGridParam("selrow");
	//alert(rowId);
	if(getPrevDetailedRuleId(rowId)!=-1){
		$(buttons[0]).button("enable");
	}else{
		$(buttons[0]).button("disable");
	}
	if(getNextDetailedRuleId(rowId)!=-1){
		$(buttons[1]).find("span").text("保存并继续");
		isComplete = false;
	}else{
		$(buttons[1]).find("span").text("完成");
	}
	isOverParent();
}

function previousDetailedRule(rowid){
	var selfDoc = $("#itemsList");
	var prevId = getPrevDetailedRuleId(rowid);
	getDetailedRuleById(selfDoc, prevId);
	toggleMaintainSelfButton();
}

function getNextDetailedRuleId(rowid){
	var selfDoc = $("#itemsList");
	var selectedRow = $("#"+rowid,selfDoc);
	var nextRow=selectedRow.next();
	if( $(nextRow).attr("id")==null || $(nextRow).attr("id")==undefined || $(nextRow).attr("id")=="undefined" ){ return -1; }
	var nextRowData = selfDoc.getRowData($(nextRow).attr("id"));
	if(!validateScoreButton(nextRowData.id)){
		return getNextDetailedRuleId(nextRowData.id);
	}else{
		return nextRowData.id;
	}
}

function nextDetailedRule(rowid) {
	var selfDoc = $("#itemsList");
	var nextId = getNextDetailedRuleId(rowid);
	selectedRowId = nextId;
	getDetailedRuleById(selfDoc, nextId);
	toggleMaintainSelfButton();
}

function updateRowData(rowData){
	var rowId = $("#itemsList").getGridParam("selrow");
	var dialyLogTypes;
	$.ajax({
		async:false,
		url:'${path}/evaluate/detailedRuleManage/findDialyLogTypesByDetailedRuleId.action',
		data:{"detailedRule.id":rowId},
		success:function(data){
			dialyLogTypes=data;
		}
	});
	$("#itemsList").setRowData(rowId,{dialyLogTypes:dialyLogTypes, selfAssessmentScore:rowData.selfAssessmentScore, summary:rowData.summary});
	
}

function isOverstepStandardScore(value, element){
	var rowData = $("#itemsList").getRowData($("#itemsList").getGridParam("selrow"));
	var standardScore = parseFloat($("#itemsList").getRowData(rowData.parentId).standardScore);
	$.each($("tr",$("#itemsList")),function(i,n){
		if($(n).attr("id") && $(n).attr("id")!=rowData.id){
			var eachRowData = $("#itemsList").getRowData($(n).attr("id"));
			if(eachRowData.parentId==rowData.parentId && eachRowData.selfAssessmentScore!=""){
				standardScore = standardScore - Math.abs(parseFloat(eachRowData.selfAssessmentScore))
				if(standardScore < 0){
					return false;
				}
			}
		}
	});
	return (standardScore + parseFloat(value))>=0;
}

function isOverParent(){
	var rowData = $("#itemsList").getRowData($("#itemsList").getGridParam("selrow"));
	var parentScore = parseFloat($("#itemsList").getRowData(rowData.parentId).selfAssessmentScore);
	if(parentScore == 0 || parentScore == "0"){
		$.messageBox({message:"上层分数已经扣完!",level:"error"});
		return true;
	}
	return false;
}

function isPositiveNumber(value, element){
	if(parseFloat(value) <= 0){
		return true;
	}
	return false;
}
jQuery.validator.addMethod("overstepStandardScore", isOverstepStandardScore);
jQuery.validator.addMethod("isPositiveNumber", isPositiveNumber);
$(document).ready(function(){
	$("#detailedRuleSelfAssessmentScore").focus();
	$("#summary").bind('input propertychange', function() {  
        var maxLength = $(this).attr('maxlength');  
        if ($(this).val().length > maxLength) {  
            $(this).val($(this).val().substring(0, maxLength));  
        }  
	})  
	toggleMaintainSelfButton();
	if($($(".ui-dialog-buttonset",$("#evaluateDialog").next()).children()[1]).find("span").text()=="完成"){
		isComplete = true;
	}
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data && !data.id){
	       	 			$.messageBox({
							message:data,
							evel: "error"
			 			});
	        			return;
					}
					updateRowData(data);
					if($($(".ui-dialog-buttonset",$("#evaluateDialog").next()).children()[1]).find("span").text()=="保存并继续"){
						nextDetailedRule($("#itemsList").jqGrid('getGridParam','selrow'));
					}
					if($($(".ui-dialog-buttonset",$("#evaluateDialog").next()).children()[1]).find("span").text()=="完成"){
						if(isComplete){
							$("#evaluateDialog").dialog("close");
						}
						isComplete = true;
					}
					updateTotalSelfAssessmentScore();
					$("#itemsList").trigger("reloadGrid");
					//进行判断是否可以上报
				}
			});
		},
		rules:{
			"detailedRule.selfAssessmentScore" : {
				required:true,
				maxlength:6,
				nonPositiveInteger:true,
				overstepStandardScore:true
			},
			"detailedRule.summary" : {
				maxlength:100
			}
		},
		messages:{
			"detailedRule.selfAssessmentScore" : {
				required: "自评分必须填写",
				maxlength: "自评分不能大于6个字符",
				nonPositiveInteger: "自评分必须输入非正整数",
				overstepStandardScore:"扣掉分数超过上一级细则的总分"
			},
			"detailedRule.summary" : {
				maxlength:"自评分数不能超过100个字符"
			}
		}
	});
	
});
</script>