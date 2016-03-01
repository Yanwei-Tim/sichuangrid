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
	.on_btn{
     margin-top:5px;
    display:inline-block;
    *zoom:1;
    *display:inline;
    height:20px;
    line-height:20px;
    width:100px;
    text-align:center;
   	background: -moz-linear-gradient(top, #f6f8f7, #dcdcdc);
	background: -webkit-gradient(linear, left top, left bottom, from(#f6f8f7), to(#dcdcdc));
	filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='#f6f8f7', EndColorStr='#dcdcdc');
	border:1px solid #b4b4b4;
    cursor:pointer;
}

/* issueType css */
#searchIssueForm #ITD_dlg{width:590px;left:101px;top:159px;}

</style>
<link href="${resource_path}/resource/css/serviceWork-AddEventtype.css" rel="stylesheet" type="text/css"/>
<div id="search-condition-form" title="事件处理信息查询" class="container container_24">
<form id="searchIssueForm" method="post" action="">
       	<input type="hidden" name="searchIssueVo.status" id="conditionStatus"  />
        <input id="conditionOccurOrgId" name="searchIssueVo.occurOrg.id" type="hidden" />
		<input id="keyId" name="searchIssueVo.targeOrgId" type="hidden" value="${keyId}" />
   		<div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="conditionSubject" name="searchIssueVo.subject" maxlength="50" value="" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_5 form-left">
				 <input type="text" id="issueOccurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
    		</div>
    		<div class='clearLine'></div>
    		<div class="grid_3 lable-right">
				<label class="form-lbl">事件规模：</label>
			</div>
			<div class="grid_5 form-left">
				<pop:PropertyDictSelectTag id="conditionIssueKind" name="searchIssueVo.issueKind.id"
					domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" cls="form-select"></pop:PropertyDictSelectTag>
			</div>
			<div class="grid_1"></div>
			<div class="grid_6">
				<label class="form-lbl">是否重点关注事件：</label>	
				<select id="conditionImportant" name="searchIssueVo.important" class="form-select" style="width:auto;">
					<option value=""></option>
					<option value="true">是</option>
					<option value="false">否</option>
				</select>
			</div>
			<div class="grid_1"></div>
			  <div class="grid_5">
					<label class="form-lbl">是否紧急事件：</label>	
					<select id="isEmergency" name="searchIssueVo.isEmergency" class="form-select" style="width:auto;">
						<option value=""></option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
 			 
 		 <div class='clearLine'>&nbsp;</div>
 			 
    		<div class="grid_3 lable-right">
	  			<label class="form-lbl">服务单号：</label>
	  		</div>
    		<div class="grid_5 form-left">
				<input type="text" id="conditionSerialNumber" name="searchIssueVo.serialNumber" maxlength="30" value="" class="form-txt" />
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
			<div class="grid_3 lable-right">
				<label class="form-lbl">发生地点：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionOccurLocation" name="searchIssueVo.occurLocation" value="" maxlength="50" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">录入时间  从：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionInputFrom" name="searchIssueVo.inputFrom" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">到 ：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionInputEnd" name="searchIssueVo.inputEnd" value="" readonly="readonly" class="form-txt" />
			</div>	
 					
			<div class='clearLine'>&nbsp;</div>							
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
			<div class="grid_3 lable-right">
				<label class="form-lbl">主要当事人：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionMainCharacters" name="searchIssueVo.mainCharacters" maxlength="20" value="" class="form-txt" />
			</div>
			<div class='clearLine'>&nbsp;</div>	
			<%--已办结事件的查询才有结案时间这个查询条件 --%>
			<s:if test="'completed'.equals(tag)">
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
			</s:if>							
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件类型：</label>
			</div>
			<div class="grid_21 heightAuto" style='margin:4px 0 2px;'>
	                <div id="issueTypeDescription" name="typeNames"></div>
	                <div class="rightBar" id="ITD_rightBar">
	                    <a href="javascript:;" class="down"></a>
	                </div>
	            </div>
	            <div id="ITD_dlg" style='display:none;width:590px;left:101px;top:159px;'>
	            
	              <div class="chooseDlgTop" style="position:relative;">
            		<a href="javascript:;" id="objectSelectBoxClose" class="close">我选好了</a>
					<h3 class="title">请选择事件类型：（可以选多个）</h3>
				  </div>
				  
			        <!-- issueTypeNames 是事件的大类名称 -->
            <s:iterator value="issueTypeNames" var="issueTypeName" status="st">
                <!-- 遍历每个大类中小类  issueTypes是Map key是每个大类的名称(issueTypeName.typeName) value是该大类中包含的小类(List) -->
                <s:if test="issueTypes[#issueTypeName.typeName].size()>0">
					<div class="multipeSelect">
                       <s:if test="!#issueTypeName.typeName.equals('一站审批')">
                            <input style='display:none;' id="issueTypeSelector${st.index}" name="" type="checkbox" class="category" <s:if test="issueHasTypeDomainName.contains(#issueTypeName.typeName)">checked</s:if> />
                            <label class="form-check-text" for="issueTypeSelector${st.index}">${issueTypeName.typeName}</label>
                        </s:if>
                        <ul  class="zc-sf">
                            <s:iterator value="issueTypes[#issueTypeName.typeName]" var="type" >
                                <li>
                                    <label>
                                        <input name="selectedTypes" type="checkbox" value='${type.id}' <s:if test="issue.issueTypes.contains(#type)">checked</s:if> />${type.issueTypeName}
                                    </label>
                                </li>
                            </s:iterator>
                        </ul>
					</div>
                </s:if>
            </s:iterator>
			      </div>
			<div class='clearLine'>&nbsp;</div>
			
	</div>
  </form>
</div>
<script type="text/javascript" src="${resource_path}/resource/js/typeSelect.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 初始化事件种类 
	$.typeSelect(); 
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
	
	$("#conditionInputFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionInputEnd").datepicker("option","minDate",date);
			}
		}
    }); 

	$("#conditionInputEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionInputFrom").datepicker("option", "maxDate",date);
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
				number:true,
				min:0,
				max:999999
			},
			"searchIssueVo.relatePeopleMaxCount":{
				number:true,
				min:0,
				max:999999
			}
		},
		messages:{
			"conditionRelatePeopleMinCount":{
				number:"涉及人数只能输入正数",
				min:"涉及人数最小输入0",
				max:"涉及人数最大输入999999"
			},
			"conditionRelateMaxAmount":{
				number:"涉及人数只能输入正数",
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