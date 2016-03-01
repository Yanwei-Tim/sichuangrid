<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="台账处理" class="container container_24">
<form id="issueDealForm" method="post" action="${path }/threeRecordsIssue/ledgerSteadyWorkIssueManage/dealIssue.action">
<pop:token />
		<input type="hidden" name="operation.dealOrg.id" id="dealOrgId" value="${operation.dealOrg.id }" />
		<input type="hidden" name="operation.dealOrg.orgInternalCode" id="dealOrgInternalCode" value="${operation.dealOrg.orgInternalCode }" />
		<input type="hidden" name="operation.ledgerId" id="ledgerId" value="${operation.ledgerId}" />
		<input type="hidden" name="keyId" id="keyId" value="${keyId }" />
		<input type="hidden" name="dealCode" id="dealCode" value="${dealCode }" />
		<pop:token />
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">
			<s:if test='@com.tianque.plugin.account.state.ThreeRecordsIssueOperate@CONCEPT.code == dealCode'>承办人</s:if>
			<s:if test='@com.tianque.plugin.account.state.ThreeRecordsIssueOperate@SUPPORT.code == dealCode'>承办人</s:if> 
			<s:if test='@com.tianque.plugin.account.state.ThreeRecordsIssueOperate@READ.code == dealCode'>阅读人</s:if>
			<s:if test='@com.tianque.plugin.account.state.ThreeRecordsIssueOperate@REPORT_TO.code == dealCode'>上报人</s:if>
			：</label>
		</div>
		<div class="grid_16">
			<input type="text" id="dealUserName" name="operation.dealUserName" maxlength="20" value="${operation.dealUserName}" class="form-txt" readonly="readonly" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_16">
			<input type="text" id="mobile" name="operation.mobile" value="${operation.mobile}" maxlength="11" class="form-txt" />
		</div>
		<div class='clearLine'></div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#issueDealForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data==null || !data.issueStepId){
	           	 		$.messageBox({message:data,	level:"error"});
	            		return;
	            	}
					var dealType = $("#dealCode").val();
					
					if(dealType == <s:property value='@com.tianque.plugin.account.state.ThreeRecordsIssueOperate@CONCEPT.code'/>){
						$.messageBox({message:"已经成功受理该条信息！"});
						//受理操作后 仍然显示该台账的详情页面
    					$(".issueList li[issueStepId='"+$("#keyId").val()+"']").removeClass('current').trigger("click");
    					$(".issueList li[issueStepId='"+$("#keyId").val()+"'] .handleLink").html('办理中').css("color","#FF00FF");
    					onLoadDelay();
					}
					if(typeof(issueForBenchDialog) != 'undefined' ){
                    	$("#issueForBenchDialog").dialog("close");
                    	$("#maxIssueListForNeed").trigger("reloadGrid");
    				}else{
    					$("#issueDialog").dialog("close");
            		}
					
					//刷新用户待办事项数量
					getMessageByUser();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交数据时发生错误");
			  	}
			});
		},
		rules:{
			"operation.dealUserName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"operation.mobile":{
				required:true,
				mobile:true
			}
		},
		messages:{
			"operation.dealUserName":{
				required:"请输入承办人",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("至少需要输入{0}个字符")
			},
			"operation.mobile":{
				required:"请输入联系人手机",
				mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			}
		}
	});
})
</script>