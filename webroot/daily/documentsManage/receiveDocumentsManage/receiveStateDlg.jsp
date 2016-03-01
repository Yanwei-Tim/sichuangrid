<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@page import="java.util.Date"%>
<div class="content" style='*overflow:hidden;'>
<form id="receiveStateForm" action="${path}/documents/receiveDocumentsManage/receiveDocuments.action">
	<input id="selectedIds" type="hidden" name="selectedIds" value="${selectedIds}"/>
	<div class="grid_5 lable-right" >
	<label style="margin-right: 10px;">签收时间：</label>
	</div>
	
	<div class="grid_5">
	<input id="signDate" style="margin-top: 5px;width: 250px;" type="text" name="userHasDocuments.signDate" readonly="readonly" value='<s:date name="userHasDocuments.signDate" format="yyyy-MM-dd HH:mm:ss"/>'/>
	</div>
	
	<div class='clearLine'></div>
	
	<div  class="grid_5 lable-right">
	<label style="margin-left: 10px;margin-left: 11px;">签收人：</label>
	</div>
	
	<div class="grid_5">
	<input id="receiveUser" style="margin-top: 5px;margin-left: 11px;width: 250px;"  name="userHasDocuments.signer" type="text" readonly="readonly"  value="<s:property value='@com.tianque.core.util.ThreadVariable@getSession().getUserRealName()'/>" style="margin-left: 11px"/>
	</div>
	
	<div class='clearLine'></div>
	
	<div class="grid_5 lable-right">
	<label>签收回执：</label>
	</div>
	
	<div class="grid_8">
	<textarea rows="3" style="margin-left: 10px;width:250px;height: auto;" name="userHasDocuments.signContent" id="receiptContent" class="form-txt {maxlength:100,messages:{maxlength:$.format('签收回执最多输入{0}个字符')}}"></textarea>
	</div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#receiveStateForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data!=true){
						$.messageBox({message:"签收失败！",level:"error"});
						return;
					}
					$.messageBox({message:"签收成功！"});
					$("#receiveDocumentsMaintanceDialog").dialog("close");
					$("#receiveDocumentsList").setRowData(data.documentId,data);
					$("#receiveDocumentsList").trigger("reloadGrid"); 
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交数据时发生错误");
				}
			});
		},
		rules:{
		},
		messages:{
		}
	});
})
</script>

