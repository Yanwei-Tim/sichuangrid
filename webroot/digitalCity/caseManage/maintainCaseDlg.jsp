<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
<form id="issueMaintainForm" method="post" action="">
		<input id="id" name="id" type="hidden" value="${id}" />
		<input id="issue.id" name="issue.id" type="hidden" value="${issue.id}" />
		<input id="serialNumber" name="issue.serialNumber" type="hidden" value="${issue.serialNumber}" />
		<input id="issue.occurOrg.id" name="issue.occurOrg.id" type="hidden" value="${issue.occurOrg.id}" />
		<input id="relatePeopleCount" name="issue.relatePeopleCount" type="hidden" value="${issue.relatePeopleCount}" />
		<input id="sourceKind" name="issue.sourceKind.id" type="hidden" value="${issue.sourceKind.id}" />
		<input id="issueKind" name="issue.issueKind.id" type="hidden" value="${issue.issueKind.id}" />
		
		<div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_20 form-left">
		    	<input type="text" id="issue.subject" name="issue.subject" maxlength="50" value="${issue.subject}" class="form-txt" />
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
    		<div class='clearLine'></div>
			
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_8 form-left">
				<input type="text" id="issueOrgName" name="issueOrgName" value="${issue.occurOrg.orgName}" class="form-txt" />
    		</div>
    		<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
    		
    		<div class="grid_3 lable-right">
				<label class="form-lbl">发生时间：</label>
			</div>
			<div class="grid_8 form-left">
				<input type="text" id="occurrence-time" name="issue.occurDate" class="form-txt"
				value="<s:date name="issue.occurDate" format="yyyy-MM-dd"/>" readonly />
			</div>
            <div class="grid_1">
                <em class="form-req">*</em>
            </div>
			<div class='clearLine'></div>		
			
			<div class="grid_3 lable-right">
				<div id="lab1">
	  				<label class="form-lbl">发生地点：</label>
	  			</div>
	  		</div>
    		<div class="grid_20 form-left heightAuto" id="div1">
    			<input type="text" id="issue_occurLocation" style="width: 99%" name="issue.occurLocation" value="${issue.occurLocation}" maxlength="50" class="form-txt" />
    		</div>
            <div class="grid_1">
                <em class="form-req">*</em>
            </div>
			<div class='clearLine'></div>		
			
			
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">报告人：</label>
	  		</div>
    		<div class="grid_20 form-left">
				<input type="text" id="issue.mainCharacters" style="width: 99%" name="issue.mainCharacters" maxlength="30" value="${issue.mainCharacters}" class="form-txt" />
    		</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>
			<div class='clearLine'>&nbsp;</div>	
			
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件大类：</label>
			</div>
			<div class="grid_8 multipeSelect">
				<s:select list="mianTypes" listKey="id" listValue="domainName" id="typeDomain" name="issueTypeDomainId" value="issueTypeDomainId" onchange="initIssueTypes()"/>
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件小类：</label>
			</div>
			<div class="grid_8 multipeSelect">
				<select id="issueType" name="issueTypeId"></select>
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>
	
			<div class='clearLine'>&nbsp;</div>	
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件简述：</label>
			</div>
			<div class="heightAuto" style="display:inline;float:left;width:80%;">
    			<textarea rows="5" cols="" id="issueContent" name="issue.issueContent"
    				class="form-txt" style="height:150px;">${issue.issueContent}</textarea>
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>   
			<div class='clearLine'>&nbsp;</div>
		</div>
		
</form>
</div>

<script type="text/javascript">
var types=new Array();
<s:iterator var="domain" value="mianTypes">
	types[${domain.id}]=new Array();
	<s:iterator var="type" value="issueTypes.get(#domain.id)" status="st">
		types[${domain.id}][${st.index}]=new Array();
		types[${domain.id}][${st.index}][0]=${type.id};
		types[${domain.id}][${st.index}][1]='<s:property value="issueTypeName" escape="false"/>';
	</s:iterator>
</s:iterator>

$(document).ready(function(){

	initIssueTypes();
	
	<s:if test='"add".equals(mode) || "edit".equals(mode)'>
		var tree=$("#issueOrgName").treeSelect({
			inputName:"issue.occurOrg.id"
		});
		<s:if test='"edit".equals(mode)'>
			$.setTreeValue(${issue.occurOrg.id},tree); 
		</s:if>
	</s:if> 


	jQuery.validator.addMethod("checkOccurOrgId", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		return checkOccurOrgIsTown();
	});
	
	<s:if test='"add".equals(mode)'>
		$("#issueMaintainForm").attr("action","${path}/callCenter/issueManage/addCase.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#issueMaintainForm").attr("action","${path}/callCenter/issueManage/updateCase.action");
	</s:if>

	$("#issueMaintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
						if(!data.issueId){
	               	 		$.messageBox({
								message:data,
								level:"error"							
					 		});
	                		return;
	                	}
						$("#issueDialog").dialog("close");
						data["occurOrg.orgName"] = data.occurOrg.orgName;
						$("#issueList").trigger("reloadGrid");
						$("#issueList").setSelection(data.issueLogId);	
						$.messageBox({message:"已经成功保存该事件处理信息!"});
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		},
		rules:{
			"issue.subject":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"issue.mainCharacters":{
				required:true,
				exculdeParticalChar:true
			},
			"issue.occurLocation":{
				required:true
			},
			"issue.occurDate":{
				required:true
			},
			"issueOrgName":{
				checkOccurOrgId:true
			},
			"issue.issueContent":{
				required:true
			}
		},
		messages:{
			"issue.subject":{
				required:"请输入事件名称",
				exculdeParticalChar:"事件名称只能输入字母，数字和中文字符",
				minlength:$.format("事件名称至少需要输入{0}个字符")
			},
			"issue.mainCharacters":{
                required:"请输入报告人",
				exculdeParticalChar:"报告人只能输入字母，数字和中文字符"
			},
			"issue.occurLocation":{
				required:"请输入发生地点"
			},
			"issue.occurDate":{
				required:"请选择发生时间"
			},
			"issueOrgName":{
				checkOccurOrgId:"发生网格无法选择乡镇(街道)以上级别！",
				position:"right"
			},
			"issue.issueContent":{
				required:"请输入事件简述"
			}
		}
	});

});


$('#occurrence-time').datePicker({
	yearRange:'1930:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'+0y'
});

function initIssueTypes(){
	var type=types[$("#typeDomain").val()];
	$("#issueType").html("");
	for (var index=0;index<type.length;index++){
		$("#issueType").append("<option value="+type[index][0]+">"+type[index][1]+"</option>"); 
	}
	$("#issueType").val(<s:property value="issueTypeId" />);
}


function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 


function checkOccurOrgIsTown(){
	var bol = false;
	$.ajax({
		async:false,
		url:"${path}/issue/issueManage/checkOccurOrgId.action",
		data:{
			"issueNew.occurOrg.id":$("#occurOrgId").val()
		},
		success:function(responseData){
			bol = responseData;
		}
	});
	return bol;
}
</script>