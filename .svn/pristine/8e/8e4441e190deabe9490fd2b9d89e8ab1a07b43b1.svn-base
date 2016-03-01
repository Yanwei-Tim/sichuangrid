<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<form id="searchServiceTeamForm">
<pop:token />
	<input id="mode" type="hidden" name="mode" value="${mode}"/> 
	<input id="teamClazzId" type="hidden" name="teamClazzId" value="${teamClazzId }" /> 	
	<input id="teamOrgId" type="hidden"	name="serviceManageTeam.organization.id" value="<s:property value="@com.tianque.util.ThreadVariable@getOrganization().id"/>" />
	
	<div id="search-condition-form" title="服务团队查询" class="container container_24">
	
		<div class="grid_4 lable-right">
	   		<label class="form-lb1">注销状态：</label>
	   	</div>
	   	<div class="grid_8">
			<select id="conditionLogOutState" class="form-select" >
				<option value="-1">全部</option>
				<option value="0" selected="selected">未注销</option>
				<option value="1">已注销</option>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">团队名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionName" name="searchServiceManageTeamVo.name" class="form-txt" maxlength="20"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">所属团队：</label>
		</div>
		<div class="grid_8">
			<select id="conditionTeamClass" name="searchServiceManageTeamVo.teamClazz" class="form-select">
				<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" defaultValue="${teamClazzId}" 
	   				reletionId="teamTypeId" reletionName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" id="conditionTeamClass" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">团队类别：</label>
		</div>
		<div class="grid_8">
			<select id="teamTypeId" name="searchServiceManageTeamVo.teamType" class="form-select" ></select>
	  	</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">成立时间：从</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionEstablishDateStart" name="searchServiceManageTeamVo.establishDateStart" class="form-txt" readonly="readonly"/>
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionEstablishDateEnd" name="searchServiceManageTeamVo.establishDateEnd" class="form-txt" readonly="readonly" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">成员人数：从</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMin" name="searchServiceManageTeamVo.teamCountMin" class="form-txt" />
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMax" name="searchServiceManageTeamVo.teamCountMax" class="form-txt" />
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	
	if("${teamClazzId }"){
		$("#conditionTeamClass").attr("disabled","disabled");
	}
	//成立日期开始
	$('#conditionEstablishDateStart').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionEstablishDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	//成立日期结束
	$('#conditionEstablishDateEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionEstablishDateStart").datepicker("option", "maxDate",date);
			}
		}
	});

	$("#searchServiceTeamForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
			"searchServiceManageTeamVo.teamCountMin":{
				number:true,
				min:0,
				max:999999
			},
			"searchServiceManageTeamVo.teamCountMax":{
				number:true,
				min:0,
				max:999999
			}
		},
		messages:{
			"searchServiceManageTeamVo.teamCountMin":{
				number:"成员人数只能输入正数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			},
			"searchServiceManageTeamVo.teamCountMax":{
				number:"成员人数只能输入正数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			}
		}
	});
	
});
</script>
