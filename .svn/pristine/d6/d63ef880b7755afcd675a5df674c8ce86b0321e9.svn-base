<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getEvaluateById" var="getEvaluateById" namespace="/evaluate/evaluateManage">
	<s:param name="evaluate.id" value="#parameters.evaluateId"></s:param>
</s:action>
<s:bean name="java.util.Date" id="date"></s:bean>
<s:date var="formateDate" nice="false" name="#date" format="yyyy" />
<div class="content">
<form id="standForm" action="${path }/evaluate/evaluateManage/updateScoresStand.action">
	<div class="ui-corner-all" id="nav">
	 	<select name="evaluate.year" id="evaluateyear">
			<option
				value="<s:property value="@java.lang.Long@parseLong(#formateDate)"/>">
			<s:property value="@java.lang.Long@parseLong(#formateDate)" />年</option>
			<option
				value="<s:property value="@java.lang.Long@parseLong(#formateDate)-1"/>">
			<s:property value="@java.lang.Long@parseLong(#formateDate)-1" />年</option>
			<option
				value="<s:property value="@java.lang.Long@parseLong(#formateDate)+1"/>">
			<s:property value="@java.lang.Long@parseLong(#formateDate)+1" />年</option>
	 	</select> 
		<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标题：</label>
		<input id="evaluate-title" type="text" style="width: 200px; align: center" name="evaluate.title" maxlength="32" value="${param.title}" />
	</div>
	
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addDetailedRule">
			<a id="add-firstDetailedRule" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增一级细则</span></a>
			<a id="add-detailedRule" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增子细则</span></a>
		</pop:JugePermissionTag> 
		<pop:JugePermissionTag ename="updateDetailedRule">
			<a id="update-detailedRule" href="javascript:void(0)"><span><strong class="ui-ico-xg"></strong>修改</span></a>
		</pop:JugePermissionTag> 
		<pop:JugePermissionTag ename="deleteDetailedRule">
			<a id="delete-detailedRule" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag> 
		<pop:JugePermissionTag ename="viewDetailedRule">
			<a id="view-detailedRule" href="javascript:void(0)"><span><strong class="ui-ico-cakan"></strong>查看</span></a>
		</pop:JugePermissionTag>
	</div>
	
	<div style="width: 100%;">
		<table id="itemsList" style="width: 100%;"></table>
	</div>
	
	<div class="ui-widget-border heightAuto grade">
		<div class="ui-widget-border ui-widget-border-on grade_right">
			<div><span>总分：</span><span id="standardEvaluateTotalScore"></span></div>
		</div>
	</div>
	
	
	<div class="ui-corner-all" id="nav">
		<div class="container container_24" style="height: 70px">
		<div class='clearLine'></div>
			<div class="grid_3 lable-right">
				<label class="form-lbl"><s:property value="#getEvaluateById.great.name" />：</label>
			</div>
		<div class="grid_6">
			<em class="form-req">*</em>
			<input type="text" style="width: 50px" readonly class="form-txt" name="great.startScore" value="<s:property value='#getEvaluateById.great.startScore' />" id="score1" />
			<em class="form-req">*</em> 至 
			<input type="text" style="width: 50px" class="form-txt" name="great.endScore" value="<s:property value='#getEvaluateById.great.endScore' />" id="score2" title="请输入降序数据（例如）1000 至 800" class="dialogtip form-txt" />
			<em class="form-req">(含)</em>
		</div>
	
	
		<div class="grid_3 lable-right">
			<label class="form-lbl"> <s:property value="#getEvaluateById.good.name" />： </label>
		</div>
		<div class="grid_6">
			<em class="form-req">*</em>
			<input type="text" style="width: 50px" class="form-txt" name="good.startScore" value="<s:property value='#getEvaluateById.good.startScore' />" id="score3" readonly />
			<em class="form-req">*</em> 至
			<input type="text" style="width: 50px" class="form-txt" name="good.endScore" value="<s:property value='#getEvaluateById.good.endScore' />" id="score4" />
			<em class="form-req">(含)</em>
		</div>
		
		
		<div class='clearLine'></div>
		<div class="grid_3 lable-right">
			<label class="form-lbl"><s:property	value="#getEvaluateById.qualified.name" />：</label>
		</div>
		<div class="grid_6">
			<em class="form-req">*</em>
			<input type="text" style="width: 50px" class="form-txt" name="qualified.startScore"	value="<s:property value='#getEvaluateById.qualified.startScore' />" id="score5" readonly />
			<em class="form-req">*</em> 至
			<input type="text" style="width: 50px" class="form-txt" name="qualified.endScore" value="<s:property value='#getEvaluateById.qualified.endScore' />" id="score6" />
			<em class="form-req">(含)</em>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl"><s:property value="#getEvaluateById.failed.name" />：</label>
		</div>
		<div class="grid_6">
			<em class="form-req">*</em>
			<input type="text" style="width: 50px" class="form-txt" name="failed.startScore" value="<s:property value='#getEvaluateById.failed.startScore' />" id="score7" readonly />
			<em class="form-req">*</em> 至
			<input type="text" style="width: 50px" readonly class="form-txt" name="failed.endScore" value="<s:property value='#getEvaluateById.failed.endScore' />" id="score8" />
			<em class="form-req">(含)</em>
		</div>
		
		<input type="hidden" id="scoreSum" name="evaluate.templateTotalScore" value="" /> 
		<input type="hidden" name="evaluate.id"	value="<s:property value="#parameters.evaluateId" />" />
		</div>
	</div>
	</form>
</div>
<script type="text/javascript">
<pop:formatterProperty name="ruleType" domainName="@com.tianque.domain.property.PropertyTypes@DETAILED_RULE_TYPE" />
var arrayTrueFalse = new Array(true , true , true , true , true , true , true , true);
var arrayTrueFalse2 = new Array(true , true ,true , true , true , true , true, true);
var arrayMessage = new Array("","","","","","","","");
var arrayMessage2 = new Array("","","","","","","","");
var isFirst = false;

function reload()
{
	isFirst = false;
	$("#itemsList").setPostData({"evaluate.id":${param.evaluateId}});
	$("#itemsList").setGridParam({url:PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action"});
	$("#itemsList").trigger("reloadGrid");
}

function callback(){
    var dfop = {
    	evaluateId:'<s:property value="#parameters.evaluateId"/>',
    	evaluateYear:'<s:property value="#getEvaluateById.evaluate.year"/>',
    	paramEvaluateId:'${param.evaluateId}'
    }
    TQ.updateStandardEvaluateDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/updateStandardEvaluateDlg.js',
    callback: callback
})


</script>