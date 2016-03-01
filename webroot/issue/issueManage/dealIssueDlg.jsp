<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<div id="dialog-form" title="办理事件处理" class="container container_24">
<form id="maintainForm" method="post" action="${path }/issue/issueBusinessManage/dealIssue.action"><pop:token />
		<pop:token />
		<input type="hidden" name="issueLog.dealOrg.id" id="dealOrgId" value="${issueLog.dealOrg.id }" />
		<input type="hidden" name="issueLog.issue.id" id="issueId" value="${issueLog.issue.id }" />
		<input type="hidden" name="stepId" id="stepId" value="${stepId }" />
		<div class="grid_4 lable-right">
			<label class="form-lbl">承办人：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dealUserName" name="issueLog.dealUserName" maxlength="20" value="${issueLog.dealUserName}" class="form-txt" />
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>  
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="mobile" name="issueLog.mobile" value="${issueLog.mobile}" maxlength="11" class="form-txt" />
		</div>
        <div class="grid_1">
            <em class="form-req">*</em>
        </div>  
		<div class='clearLine'></div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">操作类型：</label>
		</div>
		<div class="grid_7">
			<s:select name="dealType" list="canDoList" onchange="changeDealType()" listKey="code" listValue="desc" emptyOption="true"/>
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>
		<div class='clearLine'></div>
		<div id="transferToDiv">
				<div class="grid_4 lable-right">
					<label class="form-lbl">交办给：</label>
				</div>
				<div class="grid_5">
					<input type="radio" name="transferToType" id="transferToAdmin" value="true" checked onclick="transferToTypeChange(this)"/><label for="targeOrgSubmitForwardTypeAdmin"><s:text name="issue.dealIssue.orglable.name"/></label>
				</div>
				<div class="grid_5">
					<input type="radio" name="transferToType" id="transferToFun" value="false" onclick="transferToTypeChange(this)"/><label for="targeOrgSubmitForwardTypeFun">职能部门</label>
				</div>
				<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">主办单位：</label>
			</div>
			<div style="display:inline;float:left;width:53.00%;">
				<div style="display:inline;float:left;width:85.00%;">
					<input id="transferTo" type="text" name="issueLog.targeOrg.id" class="form-txt" />
				</div>
				<div style="display:inline;float:left;width:10.00%;">
					<input type="button" style="width:40px;height:25px"  value="查询" id="selectTransferTo" />
				</div>
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>
			<div class="clearLine"></div>
			<div id="tellToSelectorDiv">
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
			<label class="form-lbl">办理意见：</label>
		</div>
		<div style="display:inline;float:left;height:250px;line-height:250px;width:53.00%;">
			<textarea name="issueLog.content" rows="14" cols="80" class="form-txt" id="content" ></textarea>
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div> 
		<div class='clearLine'></div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
</form>
	<div style="position:absolute;top:75px;left:480px;">
		<div id="custom-queue" style="width: 180px; height: 223px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
	<div id="attachFilesComponent"></div>
	<div id="targeOrgSelectDialog"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});
	$("#attachFileNames").empty();

	$("#selectTransferTo").hide();
	$("#selecteTellTo").hide();
	
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
					var dealType = $("#dealType").val();
					if(dealType != <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>){
						$("#issueList").deleteSubGridByRowId($("#stepId").val());
						$("#issueList").delRowData($("#stepId").val());
		     			$.messageBox({message:"已经成功办理该事件处理!"});
		     			$("#issueDialog").dialog("close");
		     			setTimeout(reloadIssue,1000);
						disableButtons();
					}else{
						$("#issueList").toggleSubGridRow($("#stepId").val());
						$.messageBox({message:"已经成功办理该事件处理!"});
						$("#issueDialog").dialog("close");
					}
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
			"issueLog.content":{
				required:true
			}
		},
		messages:{
			"issueLog.dealUserName":{
				required:"请输入承办人",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("至少需要输入{0}个字符")
			},
			"issueLog.mobile":{
				required:"请输入联系人手机",
				mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			},
			"dealType":{
				required:"请选择操作类型"
			},
			"targeOrg":{
				required:"请输入目标人"
			},
			"issueLog.content":{
				required:"请输入办理意见"
			}
		}
	});

	
	$("#transferTo").personnelComplete({
		url:"${path}/issue/issueBusinessManage/searchTransferTarget.action?stepId="+$("#stepId").val(),
//		url: "${path}/issue/issueManage/searchTargeOrg/searchTargeOrgForAutoComplete.action?issueDealType=1001",
		multiple: false,
		postDataFunction: function(){
		    return {searchAdmin:function(){
			    				if ($("#transferToAdmin").attr("checked")){
			    					return true;
			    				}else{
				    				return false;
				    			}},
				    dealType :$("#dealType").val(),
				    exceptOrgIds:$("#tellToSelector").val()};
		}
	});
	
	$("#tellToSelector").personnelComplete({
		url:"${path}/issue/issueBusinessManage/searchTellTarget.action?stepId="+$("#stepId").val(),
		postDataFunction: function(){
		    return {
			    dealType :$("#dealType").val(),
			    exceptOrgIds:$("#transferTo").val()};
		}
	});

	transferToDivInit();

});
var bol = true;


function changeDealType(){
	var dealType = $("#dealType").val();
	$("#transferTo").clearPersonnelComplete();
	$("#tellToSelector").clearPersonnelComplete();
	if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
		|| dealType == <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>
		|| dealType == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>){
		$("#transferToDiv").hide();
		$("#transferToSelectorDiv").hide();
		$("#tellToSelectorDiv").hide();
	}else{
		$("#transferToDiv").show();
		$("#transferToSelectorDiv").show();
		$("#tellToSelectorDiv").show();
		transferToTypeChange($("#transferToAdmin").val());
	}
}

function transferToDivInit(){
	$("#transferToDiv").hide();
	$("#transferToSelectorDiv").hide();
	$("#tellToSelectorDiv").hide();
}

function transferToTypeChange(radio){
	$("#transferTo").clearPersonnelComplete();
	$("#tellToSelector").clearPersonnelComplete();
	if ($("#transferToAdmin").attr("checked")=="checked"){
		if ($("#dealType").val()!=<s:property value='@com.tianque.issue.state.IssueOperate@GIVETO.code'/>){
			$.ajax({
				url:"${path}/issue/issueBusinessManage/getUniqueTrgetAdminOrg.action?stepId="+$("#stepId").val()+"&dealType="+$("#dealType").val(),
				async:false,
				type:'post',
				success:function(data){
					if(!data.value){
					}else{
						$("#transferTo").setPersonnelCompleteVal({
	   						value:data.value,label:data.label,desc:data.desc
	   					});
						$("#transferTo").attr("readonly",true);
					}
				}
			});
		}else{
			$("#transferTo").removeAttr("readonly");
		}
	}else{
		$("#transferTo").removeAttr("readonly");
	}
}

function attachUploaded(id, fileName, responseJSON){
	$("<option>").attr("id",id).val(fileName).attr("selected",true).appendTo($("#attachFileNames"));
}

function removeAttach(fileName){
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}
</script>