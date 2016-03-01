<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<div id="search-condition-form" title="对接事件信息查询" class="container container_24">
<form id="searchIssueJointForm" method="post" action="">
	<input id="conditionOccurOrgId" name="issueJointVo.occurOrg.id" type="hidden" />
   		<div>
   			<div class="grid_3 lable-right">
				<label class="form-lbl">事件类型：</label>
			</div>
			<div class="grid_7">
			  <select name="issueJointVo.issueJointType.id" id="issueJointType" class="form-txt" >
		   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_JOINT_TYPE" defaultValue="${issueJointVo.issueJointType.id}" 
		   			reletionId="issueJointTypeSub" reletionName="@com.tianque.domain.property.PropertyTypes@ISSUE_JOINT_TYPE_SUB" id="issueJointType"/>
			</select>
		</div>
		<div class="grid_7">
			  <select name="issueJointVo.issueJointTypeSub.id" id="issueJointTypeSub" class="form-txt">
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_JOINT_TYPE_SUB" defaultValue="${issueJointVo.issueJointTypeSub.id}" />
			</select>
		</div>
			<div class='clearLine'></div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">服务单号：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="serialNumber" name="issueJointVo.serialNumber" maxlength="30" value="" class="form-txt" />
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
		    	<input type="text" id="conditionSubject" name="issueJointVo.subject" maxlength="50" value="" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件规模：</label>
			</div>
			<div class="grid_5 lable-right">
				<pop:PropertyDictSelectTag id="conditionIssueKind" name="issueJointVo.issueKind.id"
					domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" cls="form-select"></pop:PropertyDictSelectTag>
			</div>
			<div class='clearLine'></div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">涉及人数：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="relatePeopleMinCount" name="issueJointVo.relatePeopleMinCount" maxlength="6" value=""
					class="form-txt" style="text-align:right;" />
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">到：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="relatePeopleMaxCount" name="issueJointVo.relatePeopleMaxCount" maxlength="8"class="form-txt" style="text-align:right;" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">发生时间：</label>
			</div>
			<div class="grid_5 form-left">
			    <input type="text" id="occurDateStart" name="issueJointVo.occurDateStart" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">到：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="occurDateEnd" name="issueJointVo.occurDateEnd" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">发生地点：</label>
			</div>
			<div class="grid_21 form-left">
				<input type="text" id="conditionOccurLocation" name="issueJointVo.occurLocation" value="" maxlength="50" class="form-txt" />
			</div>
			<div class='clearLine'>&nbsp;</div>
	</div>
  </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#occurDateStart").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#occurDateEnd").datepicker("option","minDate",date);
			}
		}
    }); 
	
	$("#occurDateEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
    	timeFormat: 'HH:mm:ss',
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#occurDateStart").datepicker("option", "maxDate",date);
			}
		}
	});
	
	//参与人数的验证
	jQuery.validator.addMethod("validatorRelatePeopleMinCount",function(value,element){
		if(value==null || value=="" || typeof(value)=="undefined") return true;
		var relatePeopleMaxCount=$("#relatePeopleMaxCount").val();
		if( relatePeopleMaxCount!=null && relatePeopleMaxCount!="" && typeof(relatePeopleMaxCount)!="undefined" && relatePeopleMaxCount<value )return false;
		return true;
	});
	jQuery.validator.addMethod("validatorRelatePeopleMaxCount",function(value,element){
		if(value==null || value=="" || typeof(value)=="undefined") return true;
		var relatePeopleMinCount=$("#relatePeopleMinCount").val();
		if( relatePeopleMinCount!=null && relatePeopleMinCount!="" && typeof(relatePeopleMinCount)!="undefined" && relatePeopleMinCount>value )return false;
		return true;
	});
	
	$("#searchIssueJointForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
			"issueJointVo.relatePeopleMinCount":{
				positiveInteger:true,
				min:0,
				max:999999,
				validatorRelatePeopleMinCount:true
			},
			"issueJointVo.relatePeopleMaxCount":{
				positiveInteger:true,
				min:0,
				max:999999,
				validatorRelatePeopleMaxCount:true
			}
		},
		messages:{
			"issueJointVo.relatePeopleMinCount":{
				positiveInteger:"涉及人数只能输入正整数",
				min:"涉及人数最小输入0",
				max:"涉及人数最大输入999999",
				validatorRelatePeopleMinCount:"涉及人数最小值不能大于最大值"
			},
			"issueJointVo.relatePeopleMaxCount":{
				positiveInteger:"涉及人数只能输入正整数",
				min:"涉及人数最小输入0",
				max:"涉及人数最大输入999999",
				validatorRelatePeopleMaxCount:"涉及人数最小值不能大于最大值"
			}
		}
	});
	$("#issueOccurOrgSelector").one("click", function(){
		initOccurOrgSelector();
	});
});

function initOccurOrgSelector(){
	var tree=$("#issueOccurOrgSelector").treeSelect({
		inputName:"issueJointVo.occurOrg.id"
	});
}

</script>