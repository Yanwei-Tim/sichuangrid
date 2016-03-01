<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div>
    <form action="${path}/serviceList/otherSituationManageManage/signOtherSituationManage.action?" method="post" id="maintainForm">
    <input type="hidden" name="mode" id="mode" value="${mode}" />
	<input type="hidden" name="otherSituationManage.id"  value="${otherSituationManage.id}" />
	<input type="hidden" name="otherSituationManage.organization.id"  value="${otherSituationManage.organization.id}" />
	<div class="container container_24"> 
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">时间：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' value='<s:date name="otherSituationManage.inputTime" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"  class="form-txt"/>
		</div>
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">地点：</label>
		</div>
		<div class="grid_8 lable-left">
			<input value='${otherSituationManage.address}' title="${otherSituationManage.address}" readonly="readonly"  class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">网格员姓名：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='createUser' value='${otherSituationManage.createUser}'  readonly="readonly"  class="form-txt"/>
		</div>
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">详细情况：</label>
		</div>
		<div class="grid_8 ">
			<input value='${otherSituationManage.details}' title="${otherSituationManage.details}" readonly="readonly"  class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" name="otherSituationManage"  cols="" class="form-txt" disabled="disabled" >${otherSituationManage.remake}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
		<br/>
		
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收人：</label>
		</div>
		<div class="grid_8 ">
			<input name='otherSituationManage.signPeople' value='${otherSituationManage.signPeople}' readonly="readonly"  class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收日期：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' name="otherSituationManage.signDate" value='<s:date name="otherSituationManage.signDate" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"  class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">签收意见：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" id="signContent" name="otherSituationManage.signContent" maxlength="300" cols="" class="form-txt" >${otherSituationManage.signContent}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
    </div>
    </form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		submitHandler: function(form){
			 $("#maintainForm").ajaxSubmit({
					success : function(data) {
						if(!data.id){
	           	 			$.messageBox({ 
								level: "error",
								message:data
				 			});
	            			return;
						}
						$.messageBox({message:"签收成功！"});
						$("#serviceListDialog").dialog('close');
						$("#otherSituationManageList").trigger("reloadGrid");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
	});
});
</script>