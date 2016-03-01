<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="受理事件处理" class="container container_24">
<form id="maintainForm" method="post" action="${path }/issue/issueBusinessManage/conceptIssue.action">
		<input type="hidden" name="issueLog.dealOrg.id" id="dealOrgId" value="${issueLog.dealOrg.id }" />
		<input type="hidden" name="issueLog.dealType" id="dealType" value="${issueLog.dealType }" />
		<input type="hidden" name="issueLog.issue.id" id="issueId" value="${issueLog.issue.id }" />
		<input type="hidden" name="issueLog.id" id="issueLogId" value="${issueLog.id }">
		
		<div class="grid_8 lable-right">
			<label class="form-lbl">
			<s:if test='"concept".equals(mode)'>承办人</s:if>
			<s:if test='"read".equals(mode)'>阅读人</s:if>
			<s:if test='@com.tianque.issue.state.IssueOperate@COMMENT.code == dealType'>上报人</s:if>
			：</label>
		</div>
		<div class="grid_15">
			<input type="text" id="dealUserName" name="issueLog.dealUserName" maxlength="20" value="${issueLog.dealUserName }" class="form-txt" />
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>  
		<div class='clearLine'></div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_15">
			<input type="text" id="mobile" name="issueLog.mobile" maxlength="11" value="${issueLog.mobile }" class="form-txt" />
		</div>
        <div class="grid_1">
            <em class="form-req">*</em>
        </div> 
		<div class='clearLine'></div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	<s:if test='"concept".equals(mode)'>
		$("#maintainForm").attr("action","${path }/issue/issueBusinessManage/conceptIssue.action");
	</s:if>
	<s:if test='"read".equals(mode)'>
		$("#maintainForm").attr("action","${path }/issue/issueBusinessManage/readIssue.action");
	</s:if>
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.issueLogId){
	           	 		$.messageBox({
							message:data,
							level:"error"							
				 		});
	           	 		$("#issueDialog").dialog("close");
	            		return;
	            	}
					<s:if test='"concept".equals(mode)'>
						$.messageBox({message:"已经成功受理该事件处理!"});
						$("#issueList").addRowData(data.issueLogId,data,"after",$("#issueLogId").val());
						$("#issueList").setSelection(data.issueLogId);
					</s:if>
					<s:if test='"read".equals(mode)'>
					    $.messageBox({message:"已经成功阅读该事件处理!"});
					</s:if>
					$("#issueList").deleteSubGridByRowId($("#issueLogId").val());
					$("#issueList").delRowData($("#issueLogId").val());
					refreshMyIssueCount();
                    $(".message").click();
					$("#issueDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交数据时发生错误");
			  	}
			});
		},
		rules:{
			"issueLog.dealUserName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"issueLog.mobile":{
				required:true,
				mobile:true
			}
		},
		messages:{
			"issueLog.undertaker":{
				required:"请输入承办人",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("至少需要输入{0}个字符")
			},
			"issueLog.mobile":{
				required:"请输入联系人手机",
				mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			}
		}
	});
})
</script>