<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="事件处理领导批示" class="container container_24">

<form id="singleContentDealForm" method="post" action="${path}/issues/issueManage/dealIssue.action">
		<input type="hidden" name="operation.dealOrg.id" id="dealOrgId" value="${operation.dealOrg.id }" />
		<input type="hidden" name="operation.issue.id" id="issueId" value="${operation.issue.id }" />
		<input type="hidden" name="keyId" id="keyId" value="${keyId }" />
		<input type="hidden" name="dealCode" id="dealCode" value="${dealCode }" />
		<pop:token />
    <div class="grid_5 lable-right">
    	<em class="form-req">*</em>
		<label class="form-lbl">
			<s:if test='@com.tianque.issue.state.IssueOperate@INSTRUCT.code == dealCode'>批示人：</s:if>
			<s:if test='@com.tianque.issue.state.IssueOperate@URGENT.code == dealCode'>加急人：</s:if>
		</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="dealUserName" name="operation.dealUserName" maxlength="20" value="${operation.dealUserName}" class="form-txt" readonly="readonly" />
	</div>
	
	<div class="grid_5 lable-right">
		<em class="form-req">*</em><label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="mobile" name="operation.mobile" value="${operation.mobile}" maxlength="11" class="form-txt" />
	</div>
	 <div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">
			<s:if test='@com.tianque.issue.state.IssueOperate@INSTRUCT.code == dealCode'>批示内容：</s:if>
			<s:if test='@com.tianque.issue.state.IssueOperate@URGENT.code == dealCode'>加急意见：</s:if>
		</label>
	</div>
	<div class="grid_19 heightAuto">
		<textarea name="operation.content" class="form-txt" rows="9"  id="content" ></textarea>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_22" style="position:absolute;top:260px;left:50px;">
		<hr/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_12 lable-right" style="position:absolute;top:270px;left:30px;">
		<em class="form-req">数据一旦保存，将无法修改，请您仔细检查输入。</em>
	</div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#singleContentDealForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data == null || !data.issueStepId){
	           	 		$.messageBox({message:data,level:"error"});
	            		return;
	            	}
	            	if( $("#dealCode").val()== <s:property value="@com.tianque.issue.state.IssueOperate@URGENT.code"/>){
	            		$.messageBox({message:"已成功加急该事件处理信息!"});
		            }else{
		            	$.messageBox({message:"已成功批示该事件处理信息!"});
			        }
	            	setTimeout(reloadIssue,1000);
	            	try {
	                	$("div[id='issueDialog']:eq(1)").dialog("close");		                	
	                	$("div[id='issueDialog']:eq(0)").dialog("close");
	                } catch (e) {}
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
				minlength:2,
				maxlength:20
			},
			"operation.mobile":{
				required:true,
                mobile:true
			},
			"operation.content":{
				required:true,
				//fateson add  批注内容 1000个字
				maxlength:1000
			}
		},
		messages:{
			"operation.dealUserName":{
				required:"请输入处理人",
				exculdeParticalChar:"处理人只能输入字母，数字和中文字符",
				minlength:$.format("处理人至少需要输入{0}个字符"),
				maxlength:$.format("处理人最多只能输入{0}个字符")
			},
			"operation.mobile":{
				required:"请输入联系人手机",
                mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			},
			"operation.content":{
				<s:if test='@com.tianque.issue.state.IssueOperate@INSTRUCT.code == dealCode'>required:"请输入批示内容",
				maxlength:"批注内容最多1000个字"</s:if>
				<s:if test='@com.tianque.issue.state.IssueOperate@URGENT.code == dealCode'>required:"请输入加急内容",
				maxlength:"加急内容最多1000个字"</s:if>
			}
		}
	});
});
</script>