<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="" class="container container_24">
	<form id="issueMaintainForm" method="post" action="${path}/approval/approvalItemManage/auditItemPassToIssue.action">
		<input id="occurOrgId" name="issue.occurOrg.id" type="hidden" value="${approvalItem.organization.id}" />
		<input  name="approvalItem.name" type="hidden" value="${approvalItem.name}" />
		<input name="approvalItem.idCardNo" type="hidden" value="${approvalItem.idCardNo}" />
		<input name="approvalItem.mobileNumber" type="hidden" value="${approvalItem.mobileNumber}" />
		<input name="approvalItem.currentAddress" type="hidden" value="${approvalItem.currentAddress}" />
		<input name="approvalItem.remark" type="hidden" value="${approvalItem.remark }">
		<input type="hidden" name="approvalItem.item.id" value="${approvalItem.item.id }"/>
		<input type="hidden" name="approvalItem.status"  value="${approvalItem.status }" />
		<input type="hidden" name="approvalItem.approvalNumber" value="${approvalItem.approvalNumber }">
		<input type="hidden" name="approvalItem.organization.id" value="${approvalItem.organization.id }">
		<input type="hidden" name="approvalItem.id" value="${approvalItem.id }">
		<input type="hidden" name="issue.id"  value="${issue.id}" />
		<div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_13 form-left">
		    	<input type="text" id="issueSubject" name="issue.subject" maxlength="50" value="${issue.subject}" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
				<em class="form-req">*</em><label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_4 form-left">
				<input type="text" id="issueOccurOrgSelector" name="selectOrgName" value="" class="form-txt" />
    		</div>
    		<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">发生地点：</label>
			</div>
			<div class="grid_13 form-left">
		    	<input type="text" id="issueLocation" name="issue.occurLocation" maxlength="50" value="${issue.occurLocation}" class="form-txt" />
			</div>
    		<div class="grid_3 lable-right">
                <em class="form-req">*</em><label class="form-lbl">发生时间：</label>
			</div>
			<div class="grid_4 form-left">
				<input type="text" id="occurDate" name="issue.occurDate" class="form-txt" value="<s:date name="issue.occurDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly />
			</div>
			<div class='clearLine'></div>		
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">主要当事人：</label>
	  		</div>
    		<div class="grid_13 form-left">
				<input type="text" id="issueMainCharacters" name="issue.mainCharacters" maxlength="30" value="${issue.mainCharacters}" class="form-txt" />
    		</div>
    		<div class="grid_3"></div>
			<div class="grid_4">
				<input id="issueImportant" name="issue.important" type="checkbox" value="true" <s:if test="issue.important">checked="checked"</s:if> />
				<label class="form-check-text" for="issueImportant">是否重大事件 </label>
			</div>
			<div class='clearLine'>&nbsp;</div>	
			<div class="grid_4 lable-right">
				<label class="form-lbl">事件规模：</label>
			</div>
			<div class="grid_4 form-left">
				<pop:PropertyDictSelectTag id="issueKind" name="issue.issueKind.id" defaultValue="${issue.issueKind.id}" domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" cls="form-select"></pop:PropertyDictSelectTag>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">涉及人数：</label>
			</div>
			<div class="grid_4 form-left">
				<input type="text" id="issueRelatePeopleCount" name="issue.relatePeopleCount" maxlength="6" value="${issue.relatePeopleCount}" class="form-txt" style="text-align:right;" />
			</div>
    		<div class="grid_1">(人)</div>
    		<div class="grid_3"></div>
			<div class="grid_4">
				<input id="issueIsEmergency" name="issue.isEmergency" type="checkbox" value="true" <s:if test="issue.isEmergency">checked="checked"</s:if> />
				<label class="form-check-text" for="issueIsEmergency">是否紧急事件 </label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件类型：</label>
			</div>
			<div class="grid_6 lable-left">
				<input name="issueType" id="issueType" value="服务审批" class="form-txt" disabled>
			</div >
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件简述：</label>
			</div>
			<div class="grid_19 heightAuto" >
    			<textarea rows="3" cols="" id="issueContent" name="issue.issueContent" class="form-txt" style="height:86px;">${issue.issueContent}</textarea>
			</div>
		</div>
		<div id="subMaintanceDialog"></div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="display:none"></select>
	</form>
   	<div class="filePanel">
	   	<div class="grid_4 lable-right"><label class="form-lbl">附件上传：</label></div>
		<div id="attachFilesDiv" class="grid_19 heightAuto" >
			<div id="custom-queue" class="ui-widget-border" style="height:100px;width:620px;overflow-y: auto;overflow-x: hidden;"></div>
			<input id="custom_file_upload" name="uploadFile" type="file" />
		</div>
	</div>
</div>

<script type="text/javascript">

function getDefaultOccurOrg(){
	<s:if test="null!=issue.occurOrg && null!=issue.occurOrg.id">
		return "${issue.occurOrg.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}

//用来判断附件是否全部上传完成
var attachFileUploadComplete = false ;
var flag=false;

$(document).ready(function(){
	
	initOccurDateSelector();  // 初始化发生日期
	
	initAttachFileUploader(); // 初始化附件上传组件
	
	initOccurOrgSelector(); // 初始化发生网格
	
	fillIssueAttenchFiles(); // 填充附件
	
	bindFormValidation();	//  表单校验
	
	if(<s:property value='"editIssue".equals(mode)'/>){
		flag=true;
	}

	function initOccurDateSelector(){
		if($("#ui-datepicker-div")[0]){
			$("body").append("<div id='ui-datepicker-div' />")
		}
		$('#occurDate').dateTimePicker({
			yearRange:'1930:2060',
			timeFormat: 'HH:mm:ss',
			maxDate:'+0y'
		});
	}

	function initAttachFileUploader(){
		$('#custom_file_upload').uploadFile({
			queueID : 'custom-queue',
			selectInputId : "attachFileNames",
			onAllComplete:function(){attachFileUploadComplete = true ;}
		});
		$("#attachFileNames").empty();
	}

	function initOccurOrgSelector(){
		var tree=$("#issueOccurOrgSelector").treeSelect({
			inputName:"issue.occurOrg.id"
		});
		$.setTreeValue("${issue.occurOrg.id}",tree); 
	}
	
	function fillIssueAttenchFiles(){
		
		<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
		    <s:iterator value="issueAttachFiles">
		    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='id'/>",
		     filename:"<s:property value='fileName'/>",
		     link:"${path }/issues/issueManage/downLoadAttachFile.action?keyId=<s:property value='id'/>&issueMode=<s:property value='issueMode'/>",   	     	 
			 onRemove:function(){removeAttach("<s:property value='fileName' escape='false'/>")}
		     });
		    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#attachFileNames"));
		    </s:iterator>
		    attachFileUploadComplete=true;
		</s:if>
	}

	function removeAttach(fileName){
		$("input[name='file']").removeAttr("disabled");
		$("#attachFileNames").find("option:contains("+fileName+")").remove();
	}
		
	function bindFormValidation(){
		$("#issueMaintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				if(!attachFileUploadComplete && $("#attachFileNames").val()!=null && $("#attachFileNames").val()!=''){
					$.messageBox({level:'warn',message:"附件还未上传完成，请稍候!"});
					return  ;
				}
				$(form).ajaxSubmit({
					success:function(data){
							if(data==null && !data.id){
								$.messageBox({message:data,level:"error"});
								return  ;
							}
							if(flag){
								$.messageBox({message:"已经成功将该事件信息保存到系统中!"});
								reloadIssue();
								$("#issueDialog").dialog("close");
							}else{
		                		$.messageBox({message:"转入事件成功!"});
								$("#itemToIssueDlg").dialog("close");
							}
							
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		  	}
				});
			},
			rules:{
				"issue.subject":{
					required:true,
					titleStr:true,
					rangelength:[2,50]
				},
				"issue.occurLocation":{
					required:true,
					addressStr:true,
					maxlength:50
				},
				"issue.occurDate":{
					required:true
				},
				"issue.relatePeopleCount":{
					digits:true,
					range:[0,999999]
				},
				"issue.mainCharacters":{
					maxlength:30,
					multiNames:true
				},
				"issue.relatePeopleCount":{
					digits:true,
					range:[0,999999]
				},
				"issue.issueContent":{
					required:true
				}
			},
			messages:{
				"issue.subject":{
					required:"请为该事件填写一个不小于2-50个字的主题",
					titleStr:"事件主题只能输入中英文、数字、引号、括号、空格、书名号、减号等字符",
					rangelength:$.format("事件主题不能小于{0}个或大于{1}个字符")
				},
				"issue.occurLocation":{
					required:"请为该事件填写一个不超过50个字符的事发地点",
					maxlength:$.format("事发地点不能大于{0}个字符"),
					addressStr:"事发地点只能输入中英文、数字、括号、空格、减号、#号等字符"
				},
				"issue.occurDate":{
					required:"请输入该事件的发生日期"
				},
				"issue.relatePeopleCount":{
					digits:"涉及人数只能输入1到999999之间的整数",
					range:$.format("涉及人数只能输入{0}到{1}之间的整数")
				},
	            "issue.mainCharacters":{
					maxlength:$.format("主要当事人不能大于{0}个字符"),
					multiNames:"主要当事人中只能输入中英文、数字、逗号、顿号等字符"
	            },
				"issue.relatePeopleCount":{
					digits:"涉及人数只能输入1到999999之间的整数",
					range:$.format("涉及人数只能输入{0}到{1}之间的整数")
				},
				"issue.issueContent":{
					required:"请输入事件简述"
				}
			}
		});
	}
});


</script>