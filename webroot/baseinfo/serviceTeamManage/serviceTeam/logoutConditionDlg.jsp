<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>

<div id="logout-form" class="container container_24">
   	<form id="logoutForm" method="post" action="${path}/baseinfo/serviceTeamManage/updateEmphasiseById.action">
   	<pop:token />
   		<input type="hidden" name="selectedIds" id="selectedIds" value="<%=request.getParameter("selectedIds")%>" />
	   	<input type="hidden" name="serviceManageTeam.logOut" id="logOutValue" value="<%=request.getParameter("logOut")%>" />
   		<input type="hidden" name="serviceManageTeam.logOutTime" id="logOutTimeValue" value="" />

	    <div class="grid_7 lable-right">
	     	<em class="form-req">*</em>
	        <label class="form-lbl">注销时间：</label>
	    </div>
	    <div class="grid_16">
	        <input type="text" name="logOutTime" id="logOutTime"  disabled="disabled" class="form-txt" maxlength="20" />
	    </div>
	    <div class='clearLine'></div>
	    <div class="grid_7 lable-right">
	    	<em class="form-req">*</em>
	        <label class="form-lbl">注销原因：</label>
	    </div>

	    <div class="grid_16">
			<input type="text" name="serviceManageTeam.logOutReason" id="logOutReason" value="${serviceManageTeam.logOutReason}" maxlength="20"
			class="form-txt {required:true,maxlength:20,messages:{required:'请输入注销原因',maxlength:$.format('备注最多只能输入{0}个字符')}}"  />
	    </div>
	</form>
</div>
<script type="text/javascript">


$(function(){
	var t=document.getElementById("logOutTime");
	d=new Date();
	t.value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();

	t.onfocus=function(){
		var d=new Date();
		t.value=d.getHours()+":"+d.getMinutes();
	}
	$("#logOutTimeValue").val($("#logOutTime").val());
	$("#logoutForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data){
	       	 			$.messageBox({
							message:data,
							evel: "error"
			 			});
	        			return;
					}
					if(notRun.length>0){
						$.messageBox({level:'warn',message:"除选中的红色数据外,其余"+document.title+"注销成功"});
					}else{
						$.messageBox({message:document.title+"注销成功"});
					}
					$("#_serviceTeamList").trigger("reloadGrid");
					$("#_serviceTeamDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
		      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{},
		messages:{}
	});

});

</script>

