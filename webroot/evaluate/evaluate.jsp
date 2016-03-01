<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:bean name="java.util.Date" id="date"></s:bean>
<s:date var="formateDate" nice="false" name="#date" format="yyyy" />
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getEvaluateAllYears" var="getEvaluateAllYears" namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>

<div class="ui-corner-all" id="nav">
	<jsp:include page="/common/orgSelectedComponent.jsp"/>
	
	<label class="form-lbl" style=" display:none">年度：</label>
	<select name="evaluate.year" id="getEvaluateYear">
		<s:iterator value="#getEvaluateAllYears.evaluateYears">
				 <option value='<s:property />'><s:property />年</option>
		</s:iterator>
	</select>
	<label class="form-lbl" style=" display:none">标题：</label>
	<select name="evaluate.id" id="getEvaluatesTitle">
		<option>请选择标题</option>
		<s:iterator value="#getReportedEvaluateResultByOrgId.evaluates" var="e">
			<option value="<s:property value='#e.id'/>"><s:property value="#e.title" /></option>
		</s:iterator>
	</select>
	<pop:JugePermissionTag ename="saveEvaluate">
		<a id="saveEvaluate" href="javascript:void(0)"><span>保存</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="pigeonholeEvaluate">
		<a id="pigeonholeEvaluate" href="javascript:void(0)"><span>归档</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="rollBackEvaluate">
		<a id="evaluateBack" href="javascript:void(0)"><span>回退</span></a>
	</pop:JugePermissionTag>
	<!-- <a id="viewDailyLog" href="javascript:void(0)"><span>查看台帐</span></a> -->

</div>
<div id="evaluateResultDailog"></div>
<form id="evaluateResultForm" action="${path }/evaluate/evaluateManage/saveDetailedRuleResult.action" method="post">
	<input type="hidden" name="evaluate.id" value="<s:property value="#getReportedEvaluateResultByOrgId.evaluate.id"/>" id="evaluateId">
	<div style="width: 100%;">
		<table id="itemsList"></table>
		<div id="itemsListPager"></div>
	</div>
</form>
<script type="text/javascript">
$("#getEvaluatesTitle").append("<option value='0'></option>");
<s:if test="#getReportedEvaluateResultByOrgId.evaluate==null||#getReportedEvaluateResultByOrgId.evaluate.state!=@com.tianque.service.state.EvaluateState@REPORT">
	//$("#saveEvaluate").buttonDisable();
	//$("#pigeonholeEvaluate").buttonDisable();
</s:if>
var isCorrectScore = true;
var validateSaveSuccess = false;
var width=650;
var height=370;
var firstEntry = 0;

function judgeScore(a){
	var score = $(a).val();
	//判断非正浮点数
	var strPositiveFloatingPoint = "^((-\d+(\.\d+)?)|(0+(\.0+)?))$";
	//非正整数
	var strNonPositiveInteger = "^((-\\d+)|(0+))$";
	var regPositiveFloatingPoint = new RegExp(strPositiveFloatingPoint);
	var regNonPositiveInteger = new RegExp(strNonPositiveInteger);
	if(!(regPositiveFloatingPoint.test(score) || regNonPositiveInteger.test(score))){
	  $.messageBox({level:"error",message:"请输入负整数进行扣分!"});
	  return false;
	}

	//判断扣去的分数是否超过两位
	if((parseFloat(score) + 100) < 0 ){
		$.messageBox({level:"error",message:"每条细则扣去的分数不要超过-100分!"});
		return false;
	}
	//判断所扣去的分数是否大于其父节点的分数
	var parentId = $("#itemsList").getRowData($(a).parent().parent().attr("id")).parentId;
	var parentStandardScore = $("#itemsList").getRowData(parentId).standardScore;
	if(parseFloat(parentStandardScore) < Math.abs(parseFloat(score))){
		$.messageBox({level:"error",message:"扣去分数不能大于上级标准分数!"});
		return false;
	}
	return true;
}

function saveEvaluateScores(){
	if(isCorrectScore){
		var scores = $("input[name='scores']");
		var nowSum = 0 ;
		for(var i=0;i<scores.length;i++){
			if(judgeScore(scores[i]) == false){
				return;
			}
			//判断是否所评分树总和要大于父节点的分数
			nowSum = nowSum + parseFloat($(scores[i]).val());
		}
		//判断扣去的总分;
		if(Math.abs(nowSum) > parseFloat($("#standardSum").text())){
			$.messageBox({level:"warn",message:"扣去分数总和请不要大于标准总分!"});
			return;
		}
		$.dialogLoading("open");
			$("#evaluateResultForm").ajaxSubmit({
				success:function(data){
				$.dialogLoading("close");
				$.messageBox({message:"已经成功保存!"});
				boo=true;
				$("#itemsList").trigger("reloadGrid");
			}
		})
	}
}

function addPigeonhole(){
	//归档前先保存评分和评分依据
	saveEvaluateScores();
	$.ajax({
		url:PATH+'/evaluate/evaluateManage/ispigeonholeEvaluateById.action?evaluate.id=' + $("#getEvaluatesTitle").val(),
		success:function(data){
			if(data == true){
				$.messageBox({ message:"文件归档成功"});
				var curMenu=$("#accordion a.cur");
				var baseUrl=curMenu.attr("baseUrl");
				var leaderUrl="";
				baseLoad(curMenu,baseUrl,leaderUrl);
				return;
			}
			$.messageBox({message:data,level: "error"});
        }
	});
}

function callback(){
    var dfop = {
    	evaluateId:'<s:property value="#getReportedEvaluateResultByOrgId.evaluate.id"/>',
    	templateTotalScore:'<s:property value="#getReportedEvaluateResultByOrgId.evaluate.templateTotalScore"/>',
    	selfAssessmentTotalScore:'<s:property value="#getReportedEvaluateResultByOrgId.evaluate.selfAssessmentTotalScore"/>',
    	totalScoreValue:'<s:property value="#getReportedEvaluateResultByOrgId.evaluate.totalScore"/>'
    
    }
    TQ.evaluate(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/evaluate.js',
    callback: callback
})

</script>