<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="emphasis-form" class="container container_24">
   	<form id="_serviceTeamMemberLogoutForm" method="post" action="${path}/baseinfo/serviceTeamMemberManage/logout.action">
   	<pop:token />
   		<input type="hidden" name="serviceTeamMemberDetails.id"  value='<s:property value='#parameters.memberId'/>'/>
		<input type="hidden" name="serviceTeamMemberDetails.teamId"  value='<s:property value='#parameters.teamId'/>'/>

	    <div class="grid_5 lable-right">
	    	<label class="form-lbl">注销时间：</label>
	    </div>
	    <div class="grid_19">
	    	<input type="text"  id="logOutDate" name="serviceTeamMemberDetails.shiftDutyDate"  readonly="readonly" class="dialogtip form-txt"  maxlength="20" />
	    </div>
	    
	    <div class='clearLine'></div>
	    <div class="grid_5 lable-right">
	    	<em class="form-req">*</em>
	     	<label class="form-lbl">注销原因：</label>
	    </div>
	    <div class="grid_19">
			<input type="text" name="serviceTeamMemberDetails.shiftDutyReason"  maxlength="20"
			class="form-txt {required:true,maxlength:20,messages:{required:'请输入注销原因',maxlength:$.format('注销原因最多只能输入{0}个字符')}}"  />
	    </div>
	</form>
</div>

<script type="text/javascript">

$(document).ready(function() {
	var d=new Date();
	$("#logOutDate").val(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate());
	
	$("#_serviceTeamMemberLogoutForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		 $(form).ajaxSubmit({
			success : function(data) {
				if(data) {
					$("#holdServiceTeamList").delRowData('<s:property value='#parameters.teamId'/>');
					$("#_serviceTeamMemberLogoutDialog").dialog("close");
					$.messageBox({
						message:"服务成员注销成功"
					});
				} else {
       	 			$.messageBox({
						message:"注销失败",
						evel: "error"
		 			});
        			return;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{},
		messages:{}
	});
})
</script>

