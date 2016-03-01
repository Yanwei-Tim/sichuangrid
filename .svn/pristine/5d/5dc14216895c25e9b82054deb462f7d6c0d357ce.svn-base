<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
	div.terms {
		width:552px;
		height:50px;
		border:1px solid #7F9DB9;
		background:#ffffff;
		padding:6px;
		overflow:auto;
	}
</style>

<div id="search-condition-form" title="事件处理信息查询" class="container container_24">
<form id="searchIssueForm" method="post" action="">
       	<input type="hidden" name="searchIssueVo.status" id="conditionStatus"  />
        <input id="conditionOccurOrgId" name="searchIssueVo.occurOrg.id" type="hidden" />
		<input id="keyId" name="searchIssueVo.targeOrgId" type="hidden" value="${keyId}" />
		<input id="fourTeamsIssueStatusType" name="statusType" type="hidden" value="">
   		<div>
   			<div class="grid_3 lable-right">
				<label class="form-lbl">事件类型：</label>
			</div>
			<c:choose>
			<c:when test="${empty selectedIssueType}">
				<div class="grid_10 lable-right">
					<select id="issueDomain" name="searchIssueVo.issueTypeDomain.id" class="form-select">
				   		<pop:IssueTypeReleationSelectTag name=
				   		"contradiction,resolveTheContradictions,securityPrecautions,specialPopulations,socialConditions,policiesAndLaws,emergencies,otherManage" 
				   		defaultValue="${issueHasType.issueTypeDomain.id}" 
				   		reletionId="issueTypeId" isOperateDiv="0" id="issueDomain" defaultTypeValue="${issueHasType.id }"/>
					</select>
				</div>
			</c:when>
			<c:otherwise>
				<div class="grid_10 lable-right">
					<select id="issueDomain" name="searchIssueVo.issueTypeDomain.id" class="form-select">
				   		<pop:IssueTypeReleationSelectTag name= "${selectedIssueType}"	 			 
				   		defaultValue="${issueHasType.issueTypeDomain.id}" 
				   		reletionId="issueTypeId" isOperateDiv="0" id="issueDomain" notNull="true" defaultTypeValue="${issueHasType.id }"/>
					</select>
				</div>
			</c:otherwise>
			</c:choose>
			<div class="grid_10 lable-right">
				<select id="issueTypeId" name="issueType" class="form-select" disabled></select>
			</div>
			<div class='clearLine'></div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">服务单号：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="serialNumber" name="searchIssueVo.serialNumber" maxlength="30" value="" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_5 form-left">
				 <input type="text" id="issueOccurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
    		</div>
    		<div class='clearLine'></div>
    		<div class="grid_3 lable-right">
				<label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="conditionSubject" name="searchIssueVo.subject" maxlength="50" value="" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件规模：</label>
			</div>
			<div class="grid_5 lable-right">
				<select class="form-txt" id="conditionIssueKind" name="searchIssueVo.issueKind.id">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${searchIssueVo.issueKind.id}"></pop:OptionTag>
				</select>
			</div>
			<div class='clearLine'></div>
    		<!-- 主要当时人经过改造后此字段以废除改用一个单独的人员表
    		<div class="grid_3 lable-right">
				<label class="form-lbl">主要当事人：</label>
			</div>
			<div class="grid_12 form-left">
				<input type="text" id="conditionMainCharacters" name="searchIssueVo.mainCharacters" maxlength="20" value="" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			 -->
			<div class="grid_3 lable-right">
				<label class="form-lbl">涉及人数：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionRelatePeopleMinCount" name="searchIssueVo.relatePeopleMinCount" maxlength="6" value=""
					class="form-txt" style="text-align:right;" />
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">到：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionRelatePeopleMaxCount" name="searchIssueVo.relatePeopleMaxCount" maxlength="8"class="form-txt" style="text-align:right;" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">发生时间：</label>
			</div>
			<div class="grid_5 form-left">
			    <input type="text" id="conditionOccurFrom" name="searchIssueVo.occurFrom" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">到：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionOccurEnd" name="searchIssueVo.occurEnd" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<%--已办结事件的查询才有结案时间这个查询条件 --%>
			<c:if test="${tag=='completed' }">
				<div class="grid_3 lable-right">
					<label class="form-lbl">结案时间：</label>
				</div>
				<div class="grid_5 form-left">
				    <input type="text" id="conditionEndTimeFrom" name="searchIssueVo.endTimeFrom" value="" readonly="readonly" class="form-txt" />
				</div>
				<div class="grid_2 lable-right">
					<label class="form-lbl">到：</label>
				</div>
				<div class="grid_5 form-left">
					<input type="text" id="conditionEndTimeEnd" name="searchIssueVo.endTimeEnd" value="" readonly="readonly" class="form-txt" />
				</div>
				<div class='clearLine'></div>
			</c:if>							
			<div class="grid_3 lable-right">
				<label class="form-lbl">发生地点：</label>
			</div>
			<div class="grid_21 form-left">
				<input type="text" id="conditionOccurLocation" name="searchIssueVo.occurLocation" value="" maxlength="50" class="form-txt" />
			</div>
			<div class='clearLine'>&nbsp;</div>
	</div>
  </form>
</div>

<script type="text/javascript">
if("${selectedIssueType}" != ""){
	$("#issueDomain").change();
}
$(document).ready(function(){
	$("#fourTeamsIssueStatusType").val($("#_fourtemsIssueStatusType").val());
	$("ul.zc-sf li label").click(function(){
		$(this).parent().find("input").click();
	})
	$("#conditionOccurFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurEnd").datepicker("option","minDate",date);
			}
		}
    }); 
	
	$("#conditionOccurEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
    	timeFormat: 'HH:mm:ss',
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurFrom").datepicker("option", "maxDate",date);
			}
		}
	});
	
	$("#conditionEndTimeFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
   		timeFormat: 'HH:mm:ss',
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionEndTimeEnd").datepicker("option","minDate",date);
			}
		}
    }); 

	$("#conditionEndTimeEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionEndTimeFrom").datepicker("option", "maxDate",date);
			}
		}
	});
	
	$("#searchIssueForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
			"searchIssueVo.relatePeopleMinCount":{
				positiveInteger:true,
				min:0,
				max:999999
			},
			"searchIssueVo.relatePeopleMaxCount":{
				positiveInteger:true,
				min:0,
				max:999999
			}
		},
		messages:{
			"searchIssueVo.relatePeopleMinCount":{
				positiveInteger:"涉及人数只能输入正整数",
				min:"涉及人数最小输入0",
				max:"涉及人数最大输入999999"
			},
			"searchIssueVo.relatePeopleMaxCount":{
				positiveInteger:"涉及人数只能输入正整数",
				min:"涉及人数最小输入0",
				max:"涉及人数最大输入999999"
			}
		}
	});

	$("#orgName").click(function(event){
		$("#noticeDialog").createDialog({
			width: 300,
			height: 350,
			title:'请选择一个部门',
			url:'${path}/common/organizationSelector.jsp',
			buttons: {
				"确定" : function(){
					closeDialog();
				},
				"取消" : function(){
					$("#noticeDialog").dialog("close");
				}
			}
		});
	});
	$("#issueOccurOrgSelector").one("click", function(){
		initOccurOrgSelector();
	});
});



function setZone(selectOrgId,selectOrgName){
	$("#conditionOccurOrgId").val(selectOrgId);
	$("#orgName").val(selectOrgName);
}

function initOccurOrgSelector(){
	var tree=$("#issueOccurOrgSelector").treeSelect({
		inputName:"searchIssueVo.occurOrg.id"
	});
}

</script>