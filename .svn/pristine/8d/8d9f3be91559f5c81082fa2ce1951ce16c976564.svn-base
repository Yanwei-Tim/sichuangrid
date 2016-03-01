<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#addPeopleItem{color:blue;padding:0 0 0 5px;}
.delItemBox{display:none;}
.delItemBox a{padding:0 10px;color:#f60;}
</style>
<div id="dialog-form" title="事件类型越级规则设置" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="dailogName" id="dailogName" value="${dailogName}" />
		<input type="hidden" id="issueSkipruleOrgId" name="issueSkiprule.orgId" value="${issueSkiprule.orgId}"/>
		<input type="hidden" name="issueSkiprule.id" id="issueSkipruleId" value="${issueSkiprule.id}" />
		<s:if test="@com.tianque.core.util.DialogMode@ADD_MODE.equalsIgnoreCase(mode)">
			<pop:token />
		</s:if>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">事件大类：</label>
	 	</div>
		<div class="grid_7">
			<select id="issueTypeDomainId" name="issueSkiprule.issueTypeDomainId" 
			class='form-select {required:true,messages:{required:"请选择事件大类"}}'>
		   		<pop:IssueTypeReleationSelectTag name=
		   		"contradiction,resolveTheContradictions,securityPrecautions,specialPopulations,socialConditions,policiesAndLaws,emergencies,otherManage" 
		   		defaultValue="${issueSkiprule.issueTypeDomainId}" 
		   		reletionId="issueTypeId" isOperateDiv="0" id="issueTypeDomainId" defaultTypeValue="${issueSkiprule.issueTypeId }"/>
			</select>
		</div>
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">事件子类：</label>
	 	</div>
		<div class="grid_7">
			<select id="issueTypeId" name="issueSkiprule.issueTypeId" class='form-select {required:true,messages:{required:"请选择事件子类"}}' disabled></select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<!-- ================================================================================================================== -->
		
	 	
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">紧急等级：</label>
	 	</div>
		<div class="grid_7">
<%-- <select name="issueSkiprule.issueUrgentLevel.id" id="issueUrgentLevel" class='form-select {required:true,messages:{required:"请选择紧急等级"}}' >
				<option  value='${issueSkiprule.issueUrgentLevel.id}'>${issueSkiprule.issueUrgentLevel.displayName}</option>
</select>  --%>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().organization.orgLevel.displayName=='县（区）'">
			<select name="issueSkiprule.issueUrgentLevel.id" class='form-select {required:true,messages:{required:"请选择紧急等级"}}' id="org" >
		   		<option value='' id=''>请选择</option>
				<option value='${issueSkiprule.issueUrgentLevel.id}' id='' <s:if test="issueSkiprule.issueUrgentLevel.displayName==高">selected</s:if> internalId=''>高</option>
			</select>
		</s:if>
		
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().organization.orgLevel.displayName=='乡镇（街道）'">
			<select name="issueSkiprule.issueUrgentLevel.id" class='form-select {required:true,messages:{required:"请选择紧急等级"}}' id="org" >
		   		<option value='' id=''>请选择</option>
				<option value='${issueSkiprule.issueUrgentLevel.id}' id='' <s:if test="issueSkiprule.issueUrgentLevel.displayName==中">selected</s:if> internalId=''>中</option>
			</select>
		</s:if>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">上报层级：</label>
	 	</div>
		<div class="grid_7">

<%-- <select name="issueSkiprule.submitLevel.id" id="submitLevel" class='form-select {required:true,messages:{required:"请选择上报层级"}}'  >
		   		<option value='${issueSkiprule.submitLevel.id}'>${issueSkiprule.submitLevel.displayName}></option>
			</select> --%>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().organization.orgLevel.displayName=='县（区）'">
			<select name="issueSkiprule.submitLevel.id" class='form-select {required:true,messages:{required:"请选择上报层级"}}' id="org" >
		   		<option value='' id=''>请选择</option>
				<option value='${issueSkiprule.submitLevel.id}' id='' <s:if test="issueSkiprule.submitLevel.displayName==县（区）">selected</s:if> internalId=''>县（区）</option>
			</select>
		</s:if>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().organization.orgLevel.displayName=='乡镇（街道）'">
			<select name="issueSkiprule.submitLevel.id" class='form-select {required:true,messages:{required:"请选择上报层级"}}' id="org" >
		   		<option value='' id=''>请选择</option>
				<option value='${issueSkiprule.submitLevel.id}' id='' <s:if test="issueSkiprule.submitLevel.displayName==乡镇（街道）">selected</s:if> internalId=''>乡镇（街道）</option>
			</select>
		</s:if>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">是否启用：</label>
	 	</div>
		<div class="grid_7">
			<select name="issueSkiprule.status" class='form-select {required:true,messages:{required:"请选择启用状态"}}' id="issueSkipruleStatus" >
		   		<option value="">请选择</option>
		   		<option value="<pop:static value="@com.tianque.issue.constants.IssueSkipRuleStatus@STATUS_ENABLE"/>" <c:if test="${issueSkiprule.status == 1  }">selected="selected"</c:if>>启用</option>
		   		<option value="<pop:static value="@com.tianque.issue.constants.IssueSkipRuleStatus@STATUS_DISABLE"/>"<c:if test="${issueSkiprule.status == 0  }">selected="selected"</c:if>>不启用</option>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<hr>
		<!-- ================================================================================================================== -->
	 	<!--
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">抄送单位：</label>
	 	</div>
		<div class="grid_20">
			<input type="hidden" id="selectOrgIdHid"/>
	 		<input type="hidden" id="selectOrgNameHid"/>
			<input type="hidden" name="issueSkiprule.ccOrgIds" id="issueSkipruleCcOrgIds"   class="form-txt" value="${issueSkiprule.ccOrgIds}"/>
			<input type="text" name="issueSkiprule.ccOrgNames" id="issueSkipruleCcOrgNames" readOnly  class="form-txt" value="${issueSkiprule.ccOrgNames}"/>
		
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		 -->
		<!-- ================================================================================================================== -->
		<div class="grid_4 lable-right">
			<label class="form-lbl">短信提醒：</label>
	 	</div>
	 	<div class='clearLine'>&nbsp;</div>
		<div class="grid_24 form-left heightAuto">
   			<ul class="issuePeopleItems" id="issuePeopleItems">
	   			<c:choose>
				    <c:when test="${issueMessageRemindList != null }">
				     	<s:iterator value="issueMessageRemindList" status="imrl">
			    			<li style="clear:both">
								<div class="grid_4 lable-right">姓名：</div>
								<div class="grid_6">
									<input type="text" id="contactsName<s:property value='#imrl.index'/>" 
									name="issueMessageRemindList[<s:property value='#imrl.index'/>].contactsName" 
									value="<s:property value='contactsName'/>" title="<s:property value='contactsName'/>" maxlength='20'
									class="form-txt {dynamicRequired:'#contactsMobile<s:property value='#imrl.index'/>',minlength:2,maxlength:20,isLawful:true,messages:{dynamicRequired:'请输入姓名',minlength:'姓名需要输入2个字符',maxlength:'姓名最多需要输入20个字符',isLawful:'您输入了非法脚本请重新输入!'}}" />
								</div>
								<div class="grid_4 lable-right">联系手机：</div>
								<div class="grid_6">
									<input type="text" id="contactsMobile<s:property value='#imrl.index'/>" 
									name="issueMessageRemindList[<s:property value='#imrl.index'/>].contactsMobile" 
									value="<s:property value='contactsMobile' />" maxlength='11'
									class="form-txt {dynamicRequired:'#contactsName<s:property value='#imrl.index'/>',mobile:true,messages:{dynamicRequired:'请输入联系手机',mobile:'手机号码输入只能是以1开头的11位数字'}}" />
								</div>
								<div class="grid_4 delItemBox"><a href="javascript:;" class="delPeopleItem">删除</a></div>
							</li>
			    		</s:iterator>
				    </c:when>
				    <c:otherwise>
				    	<li style="clear:both">
		    				<div class="grid_4 lable-right">姓名：</div>
							<div class="grid_6">
								<input type="text" id="contactsName0" 
								name="issueMessageRemindList[0].contactsName" 
								value="" maxlength='20'
								class="form-txt {dynamicRequired:'#contactsMobile0',minlength:2,maxlength:20,isLawful:true,messages:{dynamicRequired:'请输入姓名',minlength:'姓名需要输入2个字符',maxlength:'姓名最多需要输入20个字符',isLawful:'您输入了非法脚本请重新输入!'}}" />
							</div>
							<div class="grid_4 lable-right">联系手机：</div>
							<div class="grid_6">
								<input type="text" id="contactsMobile0" 
								name="issueMessageRemindList[0].contactsMobile" 
								value="" maxlength='11'
								class="form-txt {dynamicRequired:'#contactsName0',mobile:true,messages:{dynamicRequired:'请输入联系手机',mobile:'手机号码输入只能是以1开头的11位数字'}}" />
							</div>
							<c:choose>
		    				<c:when test="${'view'!=mode }">
						     	<div class="grid_4 delItemBox"><a href="javascript:;" class="delPeopleItem">删除</a></div>
						    </c:when>
						    <c:otherwise>
						    	<div class="grid_4"></div>
						    </c:otherwise>
						    </c:choose>
		    			</li>
				    </c:otherwise>
				</c:choose>
   			</ul>
   			<c:choose>
			    <c:when test="${mode != 'view' }">
			     <div class="grid_4"><a href="javascript:;" id="addPeopleItem">+其他联系人</a></div>
			    </c:when>
			    <c:otherwise>
			    <div class="grid_4"></div>
			    </c:otherwise>
			</c:choose>
   		</div>
	</form>
	
</div>
<script type="text/javascript">

$(document).ready(function(){
	/*
	var orgTree=$("#org").treeSelect({
		rootId:$("#issueSkipruleOrgId").val(),
		isRootSelected:false,
		inputName:"issueSkiprule.submitOrgId",
		treeFunc:"initTree"
	});
	if("add"!=$("#mode").val()){
		$.setTreeValue($("#submitOrgId").val(),orgTree); 
	}
	
	$("#issueSkipruleCcOrgNames").click(function(event){
		$("#issueSkipruleMaintanceDialog").createDialog({
			width:300,
			height:400,
			title:'抄送单位选择',
	 		url:'${path}/issue/issueSkipruleManage/selectOrgDlg.jsp',
			buttons: {
		   		"确定" : function(event){
		   			$("#issueSkipruleCcOrgNames").val($("#selectOrgNameHid").val());
					$("#issueSkipruleCcOrgIds").val($("#selectOrgIdHid").val());
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	*/
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
					<c:if test='${"edit"==mode}'>
						$("#issueSkipruleList").setRowData(data.id,data);
				    	$.messageBox({message:"越级规则修改成功!"});
				    	
					</c:if>
					<c:if test='${"add"==mode}'>
						$("#issueSkipruleList").addRowData(data.id,data,"first");
						$.messageBox({message:"越级规则新增成功!"});
						//whetherStartIssueSkipRule(data.id);
					</c:if>
					$("#"+$("#dailogName").val()).dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
<c:if test='${"add"==mode}'>
    $("#maintainForm").attr("action","${path}/issueSkipruleManage/addIssueSkiprule.action");
</c:if>
<c:if test='${"edit"==mode}'>
    $("#maintainForm").attr("action","${path}/issueSkipruleManage/updateIssueSkiprule.action");
</c:if>

	/* if("view"==$("#mode").val()){
		$()
	    $("#dialog-form input").attr("disabled","disabled");
	    $("#dialog-form select").attr("disabled","disabled");
	} */
	if("edit"==$("#mode").val()){
		$("#dialog-form select").attr("disabled","disabled");
		$("#issueSkipruleStatus").removeAttr("disabled");
		$("#issuePeopleItems li").find(".delItemBox").show();
		$("#issuePeopleItems li").find(".delItemBox").last().hide();
	}

	$("#addPeopleItem").click(function(){
		var sum=$("#issuePeopleItems li").size()-1;
		
		var temp='<li style="clear:both">\
			<div class="grid_4 lable-right">姓名：</div>\
			<div class="grid_6"><input type="text" id="contactsName'+(sum+1)+'" name="issueMessageRemindList['+(sum+1)+'].contactsName" value="" class="itemName form-txt {dynamicRequired:\'#contactsMobile'+(sum+1)+'\',messages:{dynamicRequired:\'请输入姓名\'}}" /></div>\
			<div class="grid_4 lable-right">联系手机：</div>\
			<div class="grid_6"><input type="text" id="contactsMobile'+(sum+1)+'" name="issueMessageRemindList['+(sum+1)+'].contactsMobile" value="" maxlength="11" class="itemTel form-txt {dynamicRequired:\'#contactsName'+(sum+1)+'\',mobile:true,messages:{dynamicRequired:\'请输入联系手机\',mobile:\'手机号码输入只能是以1开头的11位数字\'}}" /></div>\
			<div class="grid_4 delItemBox" style="display:none"><a href="javascript:;" class="delPeopleItem">删除</a></div>\
			</li>';
		$("#issuePeopleItems li").find(".delItemBox").show().end().eq(sum).after(temp);
	});
	$("#issuePeopleItems").delegate(".delPeopleItem","click",function(){
		$(this).parent().parent().find("input").val("");
		$(this).parent().parent().hide();
		//$(this).closest("li").remove();
	})
	
});

//已停用，将状态放在新增页面中去了
function whetherStartIssueSkipRule(ruleId){
	$("#enableIssueSkipruleDialog").createDialog({
		width:330,
		height:190,
		title:"提示",
		url:"${path}/issue/issueSkipruleManage/whetherStartIssueSkiprule.jsp?ruleId="+ruleId
	});
}

</script>


