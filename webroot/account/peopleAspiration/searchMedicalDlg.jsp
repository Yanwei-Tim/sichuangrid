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

<div id="search-condition-form" title="台账处理信息查询" class="container container_24">
<form id="searchIssueForm" method="post" action="">
       	<input type="hidden" name="searchVo.status" id="conditionStatus"  />
        <input id="conditionOccurOrgId" name="searchVo.occurOrg.id" type="hidden" />
        	<c:if test="${param.tag eq 'support' }"><input type="hidden" id="isSupported" name="searchVo.isSupported" value="1" /></c:if>
        <c:if test="${param.tag eq 'need' }"><input type="hidden" id="isSupported" name="searchVo.isSupported" value="0" /></c:if>
        <c:if test="${param.tag eq 'notice' }"><input type="hidden" id="isSupported" name="searchVo.isSupported" value="2" /></c:if>
		<input id="keyId" name="searchVo.targeOrgId" type="hidden" value="${keyId}" />
   		<div>
   			<div class="grid_4 lable-right">
				<label class="form-lbl">建卡类型：</label>
			</div>
			<div class="grid_7">
			<select name="searchVo.createTableType.id" id="createTableType" class='form-txt' >
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE"  defaultValue="${searchVo.createTableType.id}" />
			</select>
			</div>
			<div class='clearLine'></div>
			 <div class="grid_4 lable-right">
			<label class="form-lbl">姓名：</label>
		 	</div>
			<div class="grid_7">
				<input type="text" name="searchVo.name" id="name"  maxlength="20"  value="${searchVo.name}"
					class='form-txt'/>
			</div>
		 	<div class="grid_4 lable-right">
				<label class="form-lbl">身份证：</label>
		 	</div>
			<div class="grid_7">
				<input type="text" name="searchVo.idCardNo" id="idCardNo"  maxlength="18"  value="${searchVo.idCardNo}"
						class="form-txt"/>
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">服务单号：</label>
			</div>
			<div class="grid_7 form-left">
		    	<input type="text" id="serialNumber" name="searchVo.serialNumber" maxlength="30" value="" class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_7 form-left">
				 <input type="text" id="issueOccurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
    		</div>
    		<div class='clearLine'></div>
    		
    		<div class="grid_4 lable-right">
			<label class="form-lbl">反映群体：</label>
		 	</div>
			<div class="grid_7 ">
				<select name="searchVo.appealHumanType.id" id="appealHumanType" class='form-txt' >
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_APPEAL_HUMAN_TYPE" defaultValue="${searchVo.appealHumanType.id}" />
				</select>
			</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">服务联系人：</label>
		 	</div>
			<div class="grid_6">
				<input type="text" name="searchVo.serverContractor" id="serverContractor"  maxlength="20" class="form-txt" value="${searchVo.serverContractor}"/>
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">涉及人数：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="conditionRelatePeopleMinCount" name="searchVo.relatePeopleMinCount" maxlength="6" value=""
					class="form-txt" style="text-align:right;" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">到：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="conditionRelatePeopleMaxCount" name="searchVo.relatePeopleMaxCount" maxlength="8"class="form-txt" style="text-align:right;" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_4 lable-right">
				<label class="form-lbl">登记时间：</label>
			</div>
			<div class="grid_7 form-left">
			    <input type="text" id="conditionOccurFrom" name="searchVo.occurFrom" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">到：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="conditionOccurEnd" name="searchVo.occurEnd" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<%--已办结事件的查询才有结案时间这个查询条件 --%>
			<c:if test="${tag=='completed' }">
				<div class="grid_4 lable-right">
					<label class="form-lbl">结案时间：</label>
				</div>
				<div class="grid_7 form-left">
				    <input type="text" id="conditionEndTimeFrom" name="searchVo.endTimeFrom" value="" readonly="readonly" class="form-txt" />
				</div>
				<div class="grid_4 lable-right">
					<label class="form-lbl">到：</label>
				</div>
				<div class="grid_7 form-left">
					<input type="text" id="conditionEndTimeEnd" name="searchVo.endTimeEnd" value="" readonly="readonly" class="form-txt" />
				</div>
				<div class='clearLine'></div>
			</c:if>							
	</div>
  </form>
</div>

<script type="text/javascript">
if("${selectedIssueType}" != ""){
	$("#issueDomain").change();
}
$(document).ready(function(){
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
			"searchVo.relatePeopleMinCount":{
				positiveInteger:true,
				min:0,
				max:999999
			},
			"searchVo.relatePeopleMaxCount":{
				positiveInteger:true,
				min:0,
				max:999999
			}
		},
		messages:{
			"searchVo.relatePeopleMinCount":{
				positiveInteger:"涉及人数只能输入正整数",
				min:"涉及人数最小输入0",
				max:"涉及人数最大输入999999"
			},
			"searchVo.relatePeopleMaxCount":{
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
		inputName:"searchVo.occurOrg.id"
	});
}

</script>