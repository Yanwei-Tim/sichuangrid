<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="findTemplatesByOrgId" var="findTemplatesByOrgId" namespace="/evaluate/evaluateManage" >
	<s:param name="evaluate.organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
	<s:param name="rows" value="5"></s:param>
</s:action>
<s:bean name="java.util.Date" id="date"></s:bean>
<s:date var="formateDate" nice="false" name="#date" format="yyyy"/>
<form id="maintainRuleSettingsForm" action="${path}/evaluate/evaluateManage/copyStandardEvaluate.action" method="post">
	<div class="ui-corner-all" id="nav">
			<select name="evaluate.year" id="evaluateyear">
				<option value="<s:property value="@java.lang.Long@parseLong(#formateDate)"/>">
					<s:property value="@java.lang.Long@parseLong(#formateDate)"/>年
				</option>
				<option value="<s:property value="@java.lang.Long@parseLong(#formateDate)-1"/>">
					<s:property value="@java.lang.Long@parseLong(#formateDate)-1"/>年
				</option>
					<option value="<s:property value="@java.lang.Long@parseLong(#formateDate)+1"/>">
					<s:property value="@java.lang.Long@parseLong(#formateDate)+1"/>年
				</option>
			</select>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标题:</label>
			<input type="text" style="width: 170px" maxlength="32" name="evaluate.title" id="evaluatetitle"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;考核模板:
			
			<select id="templates" style="width:240px">
				<option value="">不使用模板</option>
				<s:iterator value="#findTemplatesByOrgId.pageInfo.result" var="evaluate">
			   		<option value="<s:property value="#evaluate.id"/>"><s:property value="#evaluate.title"/></option>
				</s:iterator>
		   	</select>
		<!--
		<pop:JugePermissionTag ename="addEvaluteRules">
			<a id="copy" href="javascript:void(0)">新建</span></a>
		</pop:JugePermissionTag>
		 -->
	</div>
	
	<div class="clear"></div>
	<div id="rulesDialog"></div>
	<div id="rulesCopyDialog"></div>
	<div style="width: 100%;">
		<table id="itemsList"></table>
	</div>
	<div class="ui-widget-border heightAuto grade">
		<div class="ui-widget-border ui-widget-border-on grade_right">
			<div><span>总分：</span><span id="standardEvaluateTotalScore"></span></div>
		</div>
	</div>
	<div class="grade_left">
			<div class="container container_24 heightAuto">
				<div class="grid_24 heightAuto ">
					<div class="grid_3 lable-right">
					<label class="form-lbl">
					<em class="form-req">*</em>
						<s:property value="#findTemplatesByOrgId.great.name" />：
					</label>
					</div>
					<div class="grid_8">
						<input type="text" style="width: 50px" readonly class="form-txt" name="great.startScore" id="score1" title="请输入降序数据（例如）1000 至 800" class="dialogtip form-txt"/>
						<em class="form-req">*&nbsp;</em>至<input type="text" style="width: 50px" class="form-txt" name="great.endScore" id="score2" title="请输入降序数据（例如）1000 至 800" class="dialogtip form-txt" /><em class="form-req">(含)</em>
					</div>
					<div class="grid_4 lable-right">
					<label class="form-lbl">
					<em class="form-req">*</em>
						<s:property value="#findTemplatesByOrgId.good.name" />：
					</label>
					</div>
					<div class="grid_8">
						<input type="text" style="width: 50px" readonly class="form-txt" name="good.startScore" id="score3" title="请输入降序数据（例如）800 至 700" class="dialogtip form-txt"/>
						<em class="form-req">*&nbsp;</em>至<input type="text" style="width: 50px" class="form-txt" name="good.endScore" id="score4" title="请输入降序数据（例如）800 至 700" class="dialogtip form-txt" /><em class="form-req">(含)</em>
					</div>
					<div class='clearLine'></div>
					<div class="grid_3 lable-right">
					<em class="form-req">*</em>
						<label class="form-lbl"><s:property value="#findTemplatesByOrgId.qualified.name" />：</label>
					</div>
					<div class="grid_8">
						<input type="text" style="width: 50px" readonly class="form-txt" name="qualified.startScore" id="score5" title="请输入降序数据（例如）700 至 600" class="dialogtip form-txt"/>
						<em class="form-req">*&nbsp;</em>至<input type="text" style="width: 50px" class="form-txt" name="qualified.endScore" id="score6" title="请输入降序数据（例如）700 至 600" class="dialogtip form-txt"/><em class="form-req">(含)</em>
					</div>
					<div class="grid_4 lable-right">
					<em class="form-req">*</em>
						<label class="form-lbl"><s:property value="#findTemplatesByOrgId.failed.name" />：</label>
					</div>
					<div class="grid_8">
						<input type="text" style="width: 50px" readonly class="form-txt" name="failed.startScore" id="score7" title="请输入降序数据（例如）600 至 0" class="dialogtip form-txt"/>
						<em class="form-req">*&nbsp;</em>至<input type="text" style="width: 50px" readonly class="form-txt" name="failed.endScore" id="score8" title="请输入降序数据（例如）600 至 0" class="dialogtip form-txt"/><em class="form-req">(含)</em>
					</div>
				</div>
			</div>
		</div>
</form>
<script type="text/javascript">
var arrayTrueFalse = new Array(true , true , true , true , true , true , true , true);
var arrayTrueFalse2 = new Array(true , true ,true , true , true , true , true, true);
var arrayMessage = new Array("","","","","","","","");
var arrayMessage2 = new Array("","","","","","","","");

function callback(){
    var dfop = {
    }
    TQ.createStandardEvaluateDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/evaluate/createStandardEvaluateDlg.js',
    callback: callback
})

</script>