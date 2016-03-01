<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchAutonomyTeamForm">
<pop:token />
	<input type="hidden" name="searchServiceManageTeamVo.teamClazz" value='<s:property value="@com.tianque.baseInfo.serviceTeamManage.vo.SearchServiceManageTeamVo@autonomousOrganization"/>'/>
	<div style="display: none;">
		mode:<input id="mode" type="text" name="mode" value="${mode}"/> 
		autonomyTeamOrgId:<input id="autonomyTeamOrgId" type="text"	name="serviceManageTeam.organization.id"		value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
		serviceManageTeamId:<input type="text" name="serviceManageTeam.id" value="${serviceManageTeam.id }" /> 	
	</div>
	
	<div id="search-condition-form" title="自治组织查询" class="container container_24">
	
		<div class="grid_4 lable-right">
			<label class="form-lbl">组织名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" id="conditionName" name="searchServiceManageTeamVo.name" class="form-txt" maxlength="20"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">组织类别：</label>
		</div>
		<div class="grid_8">
			<select id="conditionTeamType" name="searchServiceManageTeamVo.teamType" class="form-select">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
			</select>
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
			<input type="text" id="conditionTeamCountMin" name="searchServiceManageTeamVo.teamCountMin" class='form-txt {required:true,messages:{required:"请输入组织名称"}}' />
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMax" name="searchServiceManageTeamVo.teamCountMax" class='form-txt {digits:true,messages:{digits:"请输入整数"}}' />
		</div>
		<!-- 
		 -->
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){

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
	
});
</script>
