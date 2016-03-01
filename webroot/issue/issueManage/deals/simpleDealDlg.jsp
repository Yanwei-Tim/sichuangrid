<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="事件处理" class="container container_24">
<form id="issueDealForm" method="post" action="${path }/issues/issueManage/dealIssue.action">
<pop:token />
		<input type="hidden" name="operation.dealOrg.id" id="dealOrgId" value="${operation.dealOrg.id }" />
		<input type="hidden" name="operation.issue.id" id="issueId" value="${operation.issue.id }" />
		<input type="hidden" name="keyId" id="keyId" value="${keyId }" />
		<input type="hidden" name="dealCode" id="dealCode" value="${dealCode }" />
		<pop:token />
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">
			<s:if test='@com.tianque.issue.state.IssueOperate@CONCEPT.code == dealCode'>承办人</s:if>
			<s:if test='@com.tianque.issue.state.IssueOperate@READ.code == dealCode'>阅读人</s:if>
			<s:if test='@com.tianque.issue.state.IssueOperate@REPORT_TO.code == dealCode'>上报人</s:if>
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
					
					if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@CONCEPT.code'/>){
						$.messageBox({message:"已经成功受理该事件处理!"});
						//受理操作后 仍然显示该事件的详情页面
    					$(".issueList li[issueStepId='"+$("#keyId").val()+"']").removeClass('current').trigger("click");
    					$(".issueList li[issueStepId='"+$("#keyId").val()+"'] .handleLink").html('办理中').css("color","#FF00FF");
    					setTimeout(reloadIssue,1000);
					}else if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@READ.code'/>){					
					    $.messageBox({message:"已经成功阅读该事件处理!"});
					}else if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@REPORT_TO.code'/>){
				    	$.messageBox({message:"已经成功将该事件上报指挥中心!"});
				    	setTimeout(reloadIssue,1000);
					}
					if(typeof(issueForBenchDialog) != 'undefined' ){
                    	$("#issueForBenchDialog").dialog("close");
                    	$("#maxIssueListForNeed").trigger("reloadGrid");
    				}else{
    					$("#issueDialog").dialog("close");
            		}
					
					if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@READ.code'/>){
						viewIssue();
						setTimeout(reloadIssue,1000);
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