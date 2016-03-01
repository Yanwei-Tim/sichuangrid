<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<input type="hidden" id="fourTeamsIssueSkipruleOrgId" name="fourTeamsIssueSkiprule.orgId" value="${fourTeamsIssueSkiprule.orgId}"/>
		<input type="hidden" name="fourTeamsIssueSkiprule.id" id="fourTeamsIssueSkipruleId" value="${fourTeamsIssueSkiprule.id}" />
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">事件大类：</label>
	 	</div>
		<div class="grid_7">
			<select id="issueTypeDomainId" name="fourTeamsIssueSkiprule.issueTypeDomainId" 
			class='form-select {required:true,messages:{required:"请选择事件大类"}}'>
		   		<pop:IssueTypeReleationSelectTag name=
		   		"resolveTheContradictions,securityPrecautions,emergencies,otherManage" 
		   		defaultValue="${fourTeamsIssueSkiprule.issueTypeDomainId}" 
		   		reletionId="issueTypeId" isOperateDiv="0" id="issueTypeDomainId" defaultTypeValue="${fourTeamsIssueSkiprule.issueTypeId }"/>
			</select>
		</div>
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">事件子类：</label>
	 	</div>
		<div class="grid_7">
			<select id="issueTypeId" name="fourTeamsIssueSkiprule.issueTypeId" class='form-select {required:true,messages:{required:"请选择事件子类"}}' disabled></select>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">紧急等级：</label>
	 	</div>
		<div class="grid_7">
			<select name="fourTeamsIssueSkiprule.issueUrgentLevel.id" id="issueUrgentLevel" class='form-select {required:true,messages:{required:"请选择紧急等级"}}' >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@URGENT_LEVEL" defaultValue="${fourTeamsIssueSkiprule.issueUrgentLevel.id}" />
			</select>
			
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">上报层级：</label>
	 	</div>
		<div class="grid_7">
			<select name="fourTeamsIssueSkiprule.submitLevel.id" class='form-select {required:true,messages:{required:"请选择上报层级"}}' id="org" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" defaultValue="${fourTeamsIssueSkiprule.submitLevel.id}" />
			</select>
		
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">短信提醒：</label>
	 	</div>
	 	<div class='clearLine'>&nbsp;</div>
		<div class="grid_24 form-left heightAuto">
   			<ul class="issuePeopleItems" id="issuePeopleItems">
   			<s:if test='fourTeamsIssueMessageRemindList!=null'>
   			<s:iterator value="fourTeamsIssueMessageRemindList" status="imrl">
    			<li style="clear:both">
    				<div class="grid_4 lable-right">姓名：</div>
    				<div class="grid_6">
	    				<input type="text" id="contactsName<s:property value='#imrl.index'/>" maxlength="20" 
	    				name="fourTeamsIssueMessageRemindList[<s:property value='#imrl.index'/>].contactsName" 
	    				value="<s:property value='contactsName'/>" 
	    				class="form-txt {dynamicRequired:'#contactsMobile<s:property value='#imrl.index'/>',messages:{dynamicRequired:'请输入姓名'}}" />
    				</div>
    				<div class="grid_4 lable-right">联系手机：</div>
    				<div class="grid_6">
	    				<input type="text" id="contactsMobile<s:property value='#imrl.index'/>" 
	    				name="fourTeamsIssueMessageRemindList[<s:property value='#imrl.index'/>].contactsMobile" 
	    				value="<s:property value='contactsMobile' />" maxlength='11'
	    				class="form-txt {dynamicRequired:'#contactsName<s:property value='#imrl.index'/>',mobile:true,messages:{dynamicRequired:'请输入联系手机',mobile:'手机号码输入只能是以1开头的11位数字'}}" />
    				</div>
    				<div class="grid_4 delItemBox"><a href="javascript:;" class="delPeopleItem">删除</a></div>
    			</li>
    		</s:iterator>
    		</s:if>
    		<s:else>
			    <li style="clear:both">
    				<div class="grid_4 lable-right">姓名：</div>
    				<div class="grid_6">
	    				<input type="text" id="contactsName0" maxlength="20"
	    				name="fourTeamsIssueMessageRemindList[0].contactsName" 
	    				value="" 
	    				class="form-txt {dynamicRequired:'#contactsMobile0',messages:{dynamicRequired:'请输入姓名'}}" />
    				</div>
    				<div class="grid_4 lable-right">联系手机：</div>
    				<div class="grid_6">
	    				<input type="text" id="contactsMobile0" 
	    				name="fourTeamsIssueMessageRemindList[0].contactsMobile" 
	    				value="" maxlength='11'
	    				class="form-txt {dynamicRequired:'#contactsName0',mobile:true,messages:{dynamicRequired:'请输入联系手机',mobile:'手机号码输入只能是以1开头的11位数字'}}" />
    				</div>
    				<s:if test='!"view".equals(mode)'>
    				<div class="grid_4 delItemBox"><a href="javascript:;" class="delPeopleItem">删除</a></div>
		    		</s:if>
		    		<s:else>
		    		<div class="grid_4"></div>
		    		</s:else>
    			</li>
			</s:else>
   			</ul>
   			<s:if test='!"view".equals(mode)'>
  				<div class="grid_4"><a href="javascript:;" id="addPeopleItem">+其他联系人</a></div>
    		</s:if>
    		<s:else>
    			<div class="grid_4"></div>
    		</s:else>
   			
   		</div>
	</form>
	
</div>
<script type="text/javascript">

$(document).ready(function(){
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
					<s:if test='"edit".equals(mode)'>
						$("#fourTeamsIssueSkipruleList").setRowData(data.id,data);
				    	$.messageBox({message:"越级规则修改成功!"});
				    	
					</s:if>
					<s:if test='"add".equals(mode)'>
						$("#fourTeamsIssueSkipruleList").addRowData(data.id,data,"first");
						$.messageBox({message:"越级规则新增成功!"});
					
					</s:if>
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
<s:if test='"add".equals(mode)'>
    $("#maintainForm").attr("action","${path}/fourTeamsIssueSkipruleManage/addFourTeamsIssueSkiprule.action");
</s:if>
<s:if test='"edit".equals(mode)'>
    $("#maintainForm").attr("action","${path}/fourTeamsIssueSkipruleManage/updateFourTeamsIssueSkiprule.action");
</s:if>

	if("view"==$("#mode").val()){
	    $("#dialog-form input").attr("disabled","disabled");
	    $("#dialog-form select").attr("disabled","disabled");
	}
	if("edit"==$("#mode").val()){
		$("#dialog-form select").attr("disabled","disabled");
		$("#issuePeopleItems li").find(".delItemBox").show();
		$("#issuePeopleItems li").find(".delItemBox").last().hide();
	}

	$("#addPeopleItem").click(function(){
		var sum=$("#issuePeopleItems li").size()-1;
		
		var temp='<li style="clear:both">\
			<div class="grid_4 lable-right">姓名：</div>\
			<div class="grid_6"><input type="text" id="contactsName'+(sum+1)+'" maxlength="20" name="fourTeamsIssueMessageRemindList['+(sum+1)+'].contactsName" value="" class="itemName form-txt {dynamicRequired:\'#contactsMobile'+(sum+1)+'\',messages:{dynamicRequired:\'请输入姓名\'}}" /></div>\
			<div class="grid_4 lable-right">联系手机：</div>\
			<div class="grid_6"><input type="text" id="contactsMobile'+(sum+1)+'" name="fourTeamsIssueMessageRemindList['+(sum+1)+'].contactsMobile" value="" maxlength="11" class="itemTel form-txt {dynamicRequired:\'#contactsName'+(sum+1)+'\',mobile:true,messages:{dynamicRequired:\'请输入联系手机\',mobile:\'手机号码输入只能是以1开头的11位数字\'}}" /></div>\
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

</script>


