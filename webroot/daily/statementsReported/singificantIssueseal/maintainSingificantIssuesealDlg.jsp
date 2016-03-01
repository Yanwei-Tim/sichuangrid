<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="dialog-form" class="container container_24">
	<s:if test='!"view".equals(mode)'>
	<form id="dailyLog-form" method="post" action="">
	</s:if>
	<input id="mode" name="mode" type="hidden" value="${mode}" />
	<input id="significantIssuedealId" name="significantIssuedealWorkingRecord.id" type="hidden" value="${significantIssuedealWorkingRecord.id}" />
	<input id="dailyDirectoryId" name="dailyDirectory.id" type="hidden" value="${dailyDirectory.id}" />
	<div>
		<div class="grid_4 lable-right">
   			<label class="form-lb1">时间：</label>
   		</div>
   		<div class="grid_4" >
   			<input type="text" name="significantIssuedealWorkingRecord.investigationDate" id="investigationDate" maxlength="32"
  				readonly <s:if test='"view".equals(mode)'>disabled='true'</s:if> value='<s:date name="significantIssuedealWorkingRecord.investigationDate" format="yyyy-MM-dd" />'
  				class="form-txt" />
   		</div>
   		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>

   		<div class="grid_4 lable-right">
			<label class="form-lbl">地点（单位）：</label>
		</div>
		<div class="grid_10">
			<input type="text" name="significantIssuedealWorkingRecord.address" maxlength="30" id="address" class="form-txt"
				<s:if test='"view".equals(mode)'>readonly</s:if> value="${significantIssuedealWorkingRecord.address}"/>
		</div>
		<div class="grid_1">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</div>

		<div class="grid_4 lable-right">
   			<label class="form-lb1">责任领导：</label>
   		</div>
   		<div class="grid_4" >
   			<input type="text" name="significantIssuedealWorkingRecord.accountabilityLeading" id="accountabilityLeading" maxlength="20" 
   				<s:if test='"view".equals(mode)'>readonly="readonly"</s:if> value='${significantIssuedealWorkingRecord.accountabilityLeading}'
  					class="form-txt" />
   		</div>
   		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>

   		<div class="grid_4 lable-right">
			<label class="form-lbl">责任单位：</label>
		</div>
		<div class="grid_10">
			<input type="text" name="significantIssuedealWorkingRecord.accountabilityUnit" maxlength="30" id="accountabilityUnit" class="form-txt"
				<s:if test='"view".equals(mode)'>readonly="readonly"</s:if> value="${significantIssuedealWorkingRecord.accountabilityUnit}"/>
		</div>
		<div class="grid_1">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</div>

		<div class='clearLine'></div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">矛盾纠纷简况：</label>
		</div>
		<div class="grid_19  heightAuto">
			<textarea name="significantIssuedealWorkingRecord.significantIssuedealReason" rows="5"  style="width: 98%;margin-top:10px;border:1px solid #7F9DB9;"
					<s:if test='"view".equals(mode)'>readonly="readonly"</s:if> id="significantIssuedealReason" >${significantIssuedealWorkingRecord.significantIssuedealReason}</textarea>
		</div>
		<div class="grid_1">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</div>
		<div class='clearLine'></div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">调处情况：</label>
		</div>
		<div class="grid_19 heightAuto">
			<textarea name="significantIssuedealWorkingRecord.remarks" rows="5"  style="width: 98%;margin-top:10px;border:1px solid #7F9DB9;"
					<s:if test='"view".equals(mode)'>readonly="readonly"</s:if> id="remark" >${significantIssuedealWorkingRecord.remarks}</textarea>
		</div>
		<div class='clearLine'></div>
	</div>
	<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	<s:if test='!"view".equals(mode)'>
	</form>
	</s:if>
</div>
<div id="attachFilesDiv" style="position:absolute;top:270px;left:118px;">
	<div id="custom-queue"  style="width: 494px; height: 60px;overflow-y: auto;overflow-x: hidden;margin-bottom:8px" class="ui-widget-border"></div>
	<s:if test='!"view".equals(mode)'>
	<input id="custom_file_upload" name="uploadFile" type="file" />
	</s:if>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});
	$("#attachFileNames").empty();
	fillFile();
	$('#investigationDate').datePicker({
		yearRange: '1900:2030',
   		dateFormat: 'yy-mm-dd',
           maxDate:'+0d'});
	
	<s:if test='"add".equals(mode)'>
	$("#dailyLog-form").attr("action","${path}/daily/significantIssuedealManage/addSignificantIssuedeal.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
	$("#dailyLog-form").attr("action","${path}/daily/significantIssuedealManage/updateSignificantIssuedeal.action");
	</s:if>

	<s:if test='!"view".equals(mode)'>
	$("#dailyLog-form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
         $(form).ajaxSubmit({
             success: function(data){
		        	 if(!data.id){
		            	 $.messageBox({
							message:data,
							level: "error"
						 });
		             	return;
		             }
        	   		 <s:if test='"add".equals(mode) || "copy".equals(mode) '>
		   		 		$("#significantIssuedealList").addRowData(data.id,data,"first");
					    $.messageBox({message:"成功新增重大矛盾纠纷调处报告!"});
					    $("#significantIssuedealList").setSelection(data.id);
				     </s:if>
				     <s:if test='"edit".equals(mode)'>
				     	$("#significantIssuedealList").delRowData(data.id);
				     	$("#significantIssuedealList").addRowData(data.id,data,"first");
				        $("#significantIssuedealList").setRowData(data.id,data);
					    $.messageBox({message:"成功保存重大矛盾纠纷调处报告!"});
					    $("#significantIssuedealList").setSelection(data.id);
				     </s:if>
				     disputeAttachFileShow();
				     $("#dailyLogMaintanceDialog").dialog("close");
					 $("#significantIssuedealList").trigger("reloadGrid");

	      	   },
	      	 error: function(XMLHttpRequest, textStatus, errorThrown){
		      	      alert("提交错误");
		      	   }
	       });
		},
		rules:{
				"significantIssuedealWorkingRecord.address":{
					required:true,
					minlength:2,
					maxlength:30
					},
				"significantIssuedealWorkingRecord.investigationDate":{
					required:true
				},
				"significantIssuedealWorkingRecord.accountabilityLeading":{
					required:true,
					minlength:2,
					maxlength:20

				},
				"significantIssuedealWorkingRecord.accountabilityUnit":{
					required:true,
					minlength:2,
					maxlength:30
				},
				"significantIssuedealWorkingRecord.remarks":{
					maxlength:300
				},
				"significantIssuedealWorkingRecord.significantIssuedealReason":{
					required:true,
					minlength:2,
					maxlength:300
				}
			},
		messages:{
				"significantIssuedealWorkingRecord.address":{
					required:$.format("请输入地点（单位）"),
					minlength:$.format("地点（单位）至少需要输入{0}个字符"),
					maxlength:$.format("地点（单位）最多需要输入{0}个字符")
					},
				"significantIssuedealWorkingRecord.investigationDate":{
					required:"请选择日期"
				},
				"significantIssuedealWorkingRecord.accountabilityLeading":{
					required:"请输入责任领导",
					minlength:$.format("责任领导至少需要输入{0}个字符"),
					maxlength:$.format("责任领导最多需要输入{0}个字符")
				},
				"significantIssuedealWorkingRecord.accountabilityUnit":{
					required:"请输入责任单位",
					minlength:$.format("责任单位至少需要输入{0}个字符"),
					maxlength:$.format("责任单位最多需要输入{0}个字符")
				},
				"significantIssuedealWorkingRecord.remarks":{
					maxlength:$.format("调处情况最多需要输入{0}个字符")
				},
				"significantIssuedealWorkingRecord.significantIssuedealReason":{
					required:"请输入矛盾纠纷简况及原因",
					minlength:$.format("矛盾纠纷简况及原因至少需要输入{0}个字符"),
					maxlength:$.format("矛盾纠纷简况及原因简况最多需要输入{0}个字符")
				}
			}
	});
	</s:if>
});

function fillFile(){
	<s:if test="significantIssuedealWorkingRecord !=null && significantIssuedealWorkingRecord.significantIssuedealAttachFiles!=null && significantIssuedealWorkingRecord.significantIssuedealAttachFiles.size > 0">
        <s:iterator value="significantIssuedealWorkingRecord.significantIssuedealAttachFiles">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/daily/significantIssuedealManage/downLoadAttachFile.action?disputeAttachFiles.id=<s:property value='id'/>",
	          <s:if test='"view".equals(mode)'>
	          	showCloseButton:false,
	          </s:if>
	          onRemove:function(id){deleteSingificantIssuesAttachFile(id)}
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
        </s:iterator>
	</s:if>
}

function removeAttach(fileName){
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}

function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}

function deleteSingificantIssuesAttachFile(id){
	$.ajax({
		url:"${path}/daily/significantIssuedealManage/deleteSignificantIssuedealAttachFiles.action?disputeAttachFiles.id="+id,
		type:'GET',
		dataType:'json',
		success : function(_data){
			if(_data==true){
				removeAttachById(id);
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
}
</script>