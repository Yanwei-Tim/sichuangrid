<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="issueDealForm" method="post" action="${path}/commandCenterManage/dealIssue.action">
		<input type="hidden" name="operation.dealOrg.id" id="dealOrgId" value="${operation.dealOrg.id }" />
		<input type="hidden" name="operation.issue.id" id="issueId" value="${operation.issue.id }" />
		<input type="hidden" name="keyId" id="keyId" value="${keyId }" />
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">承办人：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="dealUserName" name="operation.dealUserName" maxlength="20" value="${operation.dealUserName}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="mobile" name="operation.mobile" value="${operation.mobile}" maxlength="11" class="form-txt" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">操作类型：</label>
		</div>
		<div class="grid_4">
			<s:select name="dealCode" list="canDoList" onchange="dealTypeChanged()" listKey="code" listValue="desc" emptyOption="true" id="dealCode"/>
		</div>
		<div id="transferToDiv">
			<div class="grid_4 lable-right">
				<label class="form-lbl">给：</label>
			</div>
			<div class="grid_4">
				<input type="radio" name="transferToType" id="transferToAdmin" value="true" checked onclick="transferToTypeChange(this)"/>
				<label for="transferToAdmin"><s:text name="issue.dealIssue.orglable.name"/></label>
			</div>
			<div class="grid_4">
				<input type="radio" name="transferToType" id="transferToFun" value="false" onclick="transferToTypeChange(this)"/>
				<label for="transferToFun">职能部门</label>
			</div>
		</div>
		<div class='clearLine'></div>
		<div id="transferTargetDiv">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">主办单位：</label>
			</div>
			<div style="display:inline;float:left;width:53.00%;">
				<div style="display:inline;float:left;width:85.00%;">
					<input id="transferTo" type="text" name="operation.targeOrg.id" class="form-txt" />
				</div>
				<div style="display:inline;float:left;width:10.00%;">
					<input type="button" style="width:40px;height:25px"  value="查询" id="selectTransferTo" />
				</div>
			</div>
			<div class="clearLine"></div>
			<div id="dealDeadlineDiv">
				<div class="grid_4 lable-right"><label class="form-lbl">办理截止时间：</label></div>
				<div class="grid_5 form-left">
				    <input type="text" id="dealDeadline" name="operation.dealDeadline" readonly="readonly" class="form-txt" />
				</div>
			</div>
			<div class="clearLine"></div>
			<div id="tellToSelectorDiv" style="margin-top:10px;">
				<div class="grid_4 lable-right">
					<label class="form-lbl">抄告：</label>
				</div>
				<div style="display:inline;float:left;width:53.00%;">
					<div style="display:inline;float:left;width:85.00%;">
						<input id="tellToSelector" name="tellOrgIds" class="form-txt"/>
					</div>
					<div style="display:inline;float:left;width:10.00%;">
						<input type="button" style="width:40px;height:25px"  value="查询" id="selecteTellTo" />
					</div>
				</div>
			</div>
			<div class='clearLine'></div>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">办理意见：</label>
		</div>
		<div style="display:inline;float:left;height:250px;width:53.00%;">
			<textarea name="operation.content" style="width:180px;" rows="14" cols="80" class="form-txt" id="content" ></textarea>
		</div>
		<div class='clearLine'></div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	</form>
	<div style="position:absolute;top:75px;left:480px;">
		<div id="custom-queue" style="width: 180px; height: 223px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
	<div id="orgSelectDialog"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	initAttachFileUploader();
	initTargetAutocomplete();
	initTellsAutocomplete();
	initTargetSelectorAction();
	initTellSelectorAction();
	dealTypeChanged();

	$("#dealDeadline").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		minDate:"+0d"
		});
	
	$("#issueDealForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data==null || !data.issueStepId){
	           	 		$.messageBox({message:data,level:"error"});
	            		return;
	            	}

					$.messageBox({message:"已经成功分流该事件!"});
					
					$("#informationDialog").dialog("close");
					$("#issueList").trigger("reloadGrid");
					$("#informationList").trigger("reloadGrid");
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
			},
			"dealType":{
				required:true
			},
			"targeOrg":{
				required:function(){
					return $("#dealType").val() != <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
						&& $("#dealType").val() != <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>;
				}
			},
			"operation.content":{
				required:true
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
			},
			"dealType":{
				required:"请选择操作类型"
			},
			"targeOrg":{
				required:"请输入目标人"
			},
			"operation.content":{
				required:"请输入办理意见"
			}
		}
	});

	function getSelectedOrgIds(){
		return $("#tellToSelector").val()+","+$("#transferTo").val();
	}

	
	function createOrgSearchDialog(searchUrl,inputId,isMultiselect){
		$("#orgSelectDialog").createDialog({
			width:550,
			height:430,
			title:'选择部门',
			url: searchUrl,
			postData:{adminTarget:function(){
									return targetIsAdmin();
								  },
						dealCode :$("#dealCode").val(),
						exceptIds:function(){
									return getSelectedOrgIds();
								  }
				},
			buttons: {
				"确定" : function(event){
					fillOrgInputer(inputId,isMultiselect);
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		
	}
	function initTargetSelectorAction(){
		$("#selectTransferTo").click(function(event){
			createOrgSearchDialog("${path}/issues/issueManage/dispatch.action?mode=searchTarget&keyId="+$("#keyId").val(),"transferTo",false);
		});
	}

	function initTellSelectorAction(){
		$("#selecteTellTo").click(function(event){
			createOrgSearchDialog("${path}/issues/issueManage/dispatch.action?mode=searchTells&keyId="+$("#keyId").val(),"tellToSelector",true);
		});
	}

	function initAttachFileUploader(){
		$('#custom_file_upload').uploadFile({
			queueID : 'custom-queue',
			selectInputId : "attachFileNames"});
		$("#attachFileNames").empty();
	}
	
	function initTargetAutocomplete(){
		$("#transferTo").personnelComplete({
			url:"${path}/issues/issueManage/searchTransferTarget.action?mode=auto&keyId="+$("#keyId").val(),
			multiple: false,
			postDataFunction: function(){
			    return {adminTarget:function(){
									return targetIsAdmin();
					    			},
					    dealCode :$("#dealCode").val(),
					    exceptIds:function(){
									return getSelectedOrgIds();
									}};
			}
		});
	};
	
	function initTellsAutocomplete(){
		$("#tellToSelector").personnelComplete({
			url:"${path}/issues/issueManage/searchTellTarget.action?mode=auto&keyId="+$("#keyId").val(),
			postDataFunction: function(){
			    return {adminTarget:function(){
							return targetIsAdmin();
    						},
    					dealCode :$("#dealCode").val(),
				    	exceptOrgIds:function(){
								return getSelectedOrgIds();
				}};
			}
		});
	};
});

function targetIsAdmin(){
	return $("#transferToAdmin").attr("checked")=="checked";
}


function dealTypeChanged(){
	var operateCode = $("#dealCode").val();
	if(    operateCode == ""
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>){
		clearTargetOrg();
		setTransferToVisiabled(false);
		setTellsVisiabled(false);
	}else{
		setTransferToVisiabled(true);
		transferToTypeChange();
		setTellsVisiabled(true);
	}
}

function clearTargetOrg(){
	$("#transferTo").clearPersonnelComplete();
	$("#tellToSelector").clearPersonnelComplete();
}

function setTransferToVisiabled(visiabled){
	if (visiabled){
		$("#selectTransferTo").show();
		$("#transferToDiv").show();
		$("#transferTargetDiv").show();
	}else{
		$("#selectTransferTo").hide();
		$("#transferToDiv").hide();
		$("#transferTargetDiv").hide();
	}
}

function setTellsVisiabled(visiabled){
	if (visiabled){
		$("#selecteTellTo").show();
		$("#tellToSelectorDiv").show();
	}else{
		$("#selecteTellTo").hide();
		$("#tellToSelectorDiv").hide();
	}
}

function canTells(){
	$.ajax({
		url:"${path}/issues/issueManage/hasTellTarget.action?keyId="+$("#keyId").val(),
		data:{dealCode    :$("#dealCode").val(),
			  exceptOrgIds:$("#transferTo").val(),
			  adminTarget :function(){
								return targetIsAdmin();
							}
			} ,
		async:false,
		type:'post',
		success:function(data){
			return data && data=="true";
		}
	});
}

function autoFillUniqueTrget(){
	$.ajax({
		url:"${path}/issues/issueManage/getUniqueTrgetOrg.action?keyId="+$("#keyId").val(),
		data:{dealCode    :$("#dealCode").val(),
			  adminTarget :function(){
								return targetIsAdmin();
							}
			} ,
		async:false,
		type:'post',
		success:function(data){
			if(data.value){
				$("#transferTo").setPersonnelCompleteVal({
						value:data.value,label:data.label,desc:data.desc
					});
			}
		}
	});
}

function transferToTypeChange(radio){
	clearTargetOrg();
	autoFillUniqueTrget();
}

</script>