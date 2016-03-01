<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style type="text/css">
	.issueTableList{border-top:1px solid #ccc;border-right: 1px solid #ccc;width:100%;}
	.issueTableList td{background:#EFEFF0;height:18px; padding:0 0 0 10px;border:1px solid #ccc;border-top:none;border-right:none;border-collapse:collapse;line-height:20px; word-break:break-all; word-warp:break-word;}
	.issueTableList .issueTitle{width:15%;font:bold 12px/20px "microsoft yahei"; color:#000; padding:0 0 0 5px;}
	.issueTableList .text{width:25%;}
	.issueTableList .issueContable{width:auto;}
</style>
<div class="container container_24">
	<form id="workingRecordForm" method="post" action="${path}/workingRecord/workingRecordManage/dispatchBusiness.action?dialogName=workingRecordDialog">
		<s:if  test='!"view".equals(mode)'>
			<pop:token/>
		</s:if>
	    <input type="hidden" id="mode" name="mode" value="${mode}"/>
	    <input type="hidden" id="dailyDirectoryId" name="workingRecord.dailyDirectory.id" <s:if test='"add".equals(mode)'>value="${dailyDirectory.id}"</s:if><s:if test='"edit".equals(mode)'>value="${workingRecord.dailyDirectory.id}"</s:if> />
		<input type="hidden" id="workingRecordId" name="workingRecord.id" value="${workingRecord.id}"/>
		
		<!--value="${workingRecord.id }"
		<input id="parentIndate" type="text"  name="parentIndate" value="${parentIndate}" />
		<input id="serverDate" type="text"   name="serverDate" value="<s:date name="serverDate" format="yyyy-MM-dd"/>" />
		-->
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">请选择类型：</label>
		</div>
		<div class="grid_20">
			<select name="workingRecord.dailyLogType.id" id="workingRecrodType" class='form-txt {required:true,messages:{required:"请选择类型"}}' tabindex="1" <s:if test="fromIssue">disabled</s:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" defaultValue="${workingRecord.dailyLogType.id}"/>
			</select>
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none;"></select>
		<div id="includeDiv"></div>
	</form>
	<div class="grid_4 lable-right"></div>
		<div class="grid_20" style="width:80%;">
		<div id="custom-queue" style="height:80px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
		<input id="custom_file_upload"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" />
	</div>
</div>
<div id="temp">
	<div id="div1" style="display: none;">
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">会议名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.name" id="workingRecordName" class='form-txt {required:true,maxlength:100,messages:{required:"请输入会议名称",maxlength:$.format("会议名称不能多于{0}个字符")}}' value="${workingRecord.name}"/>
		</div>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">会议时间：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.dealDate" id="meetingWorkingRecordDealDate"
			readonly="readonly" value='<s:date name="workingRecord.dealDate" format="yyyy-MM-dd" />' class='form-txt {required:true,messages:{required:"请选择时间"}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">会议地点：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.proceedSite" id="workingRecordProceedSite" value="${workingRecord.proceedSite}" class='form-txt {maxlength:50,messages:{maxlength:$.format("会议地点不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">参与人员：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.participant" id="workingRecordParticipant" value="${workingRecord.participant}" class='form-txt {maxlength:20,messages:{maxlength:$.format("参与人员不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">会议主题：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.subject" id="workingRecordSubject" value="${workingRecord.subject}" class='form-txt {maxlength:200,messages:{maxlength:$.format("会议主题不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">会议内容：</label>
		</div>
		<div class="grid_20 heightAuto" style="margin-bottom:10px\9;">
			<textarea id="workingRecordContent" rows="3" name="workingRecord.content" title="请输入会议内容"
			class='form-txt {maxlength:1000,messages:{maxlength:$.format("会议内容不能多于{0}个字符")}}'>${workingRecord.content}</textarea>
		</div>
	</div>
		
	<div id="div2" style="display: none;">
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">文件名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.name" id="workingRecordName" class='form-txt {required:true,maxlength:100,messages:{required:"请输入文件名称",maxlength:$.format("文件名称不能多于{0}个字符")}}' value="${workingRecord.name}"/>
		</div>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">发文时间：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.dealDate" id="fileDealDate"
			readonly="readonly" value='<s:date name="workingRecord.dealDate" format="yyyy-MM-dd" />' class='form-txt {required:true,messages:{required:"请选择时间"}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">发文单位：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.workingUnit" id="workingRecordWorkingUnit" value="${workingRecord.workingUnit}" class='form-txt {maxlength:200,messages:{maxlength:$.format("发文单位不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">文件号：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.fileNo" id="workingRecordFileNo" value="${workingRecord.fileNo}" class='form-txt {maxlength:50,messages:{maxlength:$.format("文件号不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">文件主题：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.subject" id="workingRecordSubject" value="${workingRecord.subject}" class='form-txt {maxlength:200,messages:{maxlength:$.format("文件主题不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">文件内容：</label>
		</div>
		<div class="grid_20 heightAuto" style="margin-bottom:10px\9;">
			<textarea id="workingRecordContent" rows="3" name="workingRecord.content" title="请输入文件内容"
			class='form-txt {maxlength:1000,messages:{maxlength:$.format("文件内容不能多于{0}个字符")}}'>${workingRecord.content}</textarea>
		</div>
	</div>
		
	<div id="div3" style="display: none;">
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">活动名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.name" id="workingRecordName" class='form-txt {required:true,maxlength:100,messages:{required:"请输入活动名称",maxlength:$.format("活动名称不能多于{0}个字符")}}' value="${workingRecord.name}"/>
		</div>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">活动时间：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.dealDate" id="activityRecordDealDate"
			readonly="readonly" value='<s:date name="workingRecord.dealDate" format="yyyy-MM-dd" />' class='form-txt {required:true,messages:{required:"请选择时间"}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">活动地点：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.proceedSite" id="workingRecordProceedSite" value="${workingRecord.proceedSite}" class='form-txt {maxlength:50,messages:{maxlength:$.format("活动地点不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">参与人员：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.participant" id="workingRecordParticipant" value="${workingRecord.participant}" class='form-txt {maxlength:100,messages:{maxlength:$.format("参与人员不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">参与单位：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.workingUnit" id="workingRecordWorkingUnit" value="${workingRecord.workingUnit}" class='form-txt {maxlength:200,messages:{maxlength:$.format("参与单位不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">活动主题：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.subject" id="workingRecordSubject" value="${workingRecord.subject}" class='form-txt {maxlength:200,messages:{maxlength:$.format("活动主题不能多于{0}个字符")}}'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">活动内容：</label>
		</div>
		<div class="grid_20 heightAuto" style="margin-bottom:10px\9;">
			<textarea id="workingRecordContent" rows="3" name="workingRecord.content" title="请输入活动内容"
			class='form-txt {maxlength:1000,messages:{maxlength:$.format("活动内容不能多于{0}个字符")}}'>${workingRecord.content}</textarea>
		</div>
	</div>
		
	<div id="div4" style="display: none;">
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">文档名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.name" id="workingRecordName" class='form-txt {required:true,maxlength:100,messages:{required:"请输入文档名称",maxlength:$.format("文档名称不能多于{0}个字符")}}' value="${workingRecord.name}"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">文档内容：</label>
		</div>
		<div class="grid_20 heightAuto">
			<s:if test="fromIssue">
				${workingRecord.content}
			</s:if>
			<s:else>
				<textarea id="workingRecordContent" rows="3" name="workingRecord.content" title="请输入文档内容" 
				class='form-txt {maxlength:1000,messages:{maxlength:$.format("文档内容不能多于{0}个字符")}}'>${workingRecord.content}</textarea>
			</s:else>
		</div>
	</div>
		
	<div id="div5" style="display: none;">
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="workingRecord.name" id="workingRecordName" class='form-txt {required:true,maxlength:100,messages:{required:"请输入名称",maxlength:$.format("名称不能多于{0}个字符")}}' value="${workingRecord.name}"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">内容：</label>
		</div>
		<div class="grid_20 heightAuto" style="margin-bottom:10px\9;">
			<textarea id="workingRecordContent" rows="3" name="workingRecord.content" title="请输入内容"
			class='form-txt {maxlength:1000,messages:{maxlength:$.format("内容不能多于{0}个字符")}}'>${workingRecord.content}</textarea>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	function setDatePicker(){
		$('form #meetingWorkingRecordDealDate').datePicker({
			yearRange : '1900:2030',
			dateFormat : 'yy-mm-dd',
			maxDate : '+0d'
		});
	
		$('form #fileDealDate').datePicker({
			yearRange : '1900:2030',
			dateFormat : 'yy-mm-dd',
			maxDate : '+0d'
		});
		$('form #activityRecordDealDate').datePicker({
			yearRange : '1900:2030',
			dateFormat : 'yy-mm-dd',
			maxDate : '+0d'
		});
	}

	function fillFile(){
		<s:if test="workingRecord.dailyLogAttachFile!=null && workingRecord.dailyLogAttachFile.size>0">
			<s:iterator value="workingRecord.dailyLogAttachFile">
				$("#custom-queue").addUploadFileValue({
			          id:"<s:property value='id'/>",
			          filename:"<s:property value='fileName'/>",
			          link:"${path}/workingRecord/workingRecordManage/downloadDailyLogAttachFile.action?dailyLogAttachFile.id=<s:property value='id'/>",
			          onRemove:function(id){deleteDailyLogAttachFile(id)}
				});
	        	$("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
	        </s:iterator>
		</s:if>
	}

	function deleteDailyLogAttachFile(id){
		$.ajax({
			url:"${path}/workingRecord/workingRecordManage/deleteDailyLogAttachFile.action?dailyLogAttachFile.id="+id,
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

	function removeAttachById(id){
		$("#attachFileNames").find("option[id="+id+"]").remove();
	}
	
	<s:if test='"add".equals(mode)'>
		$("#workingRecrodType").get(0).selectedIndex=0;
		var index=$("#workingRecrodType").get(0).selectedIndex; 
		var div=$("#div"+index).clone(true);
		$("#includeDiv").empty();
		$("#includeDiv").append(div);
		$("form div").show();
		setDatePicker();
	</s:if>
	<s:if test='"edit".equals(mode)'>
		var index=$("#workingRecrodType").get(0).selectedIndex; 
		var div=$("#div"+index).clone(true);
		$("#includeDiv").empty();
		$("#includeDiv").append(div);
		$("form div").show();
		setDatePicker();
	</s:if>
	
	$("#workingRecrodType").change(function(){
		var index=$("#workingRecrodType").get(0).selectedIndex; 
		var div=$("#div"+index).clone();
		$("#includeDiv").empty();
		$("#includeDiv").append(div);
		$("form div").show();
		setDatePicker();
	});
	
	$("#workingRecordForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(!data.id){
           	 			$.messageBox({message:data,level: "error"});
            			return;
					}
         			if("add" == $("#mode").val()){
						$("#workingRecordList").trigger("reloadGrid");
						$.messageBox({message:"成功添加工作台账!"});
         			}
         			if("edit" == $("#mode").val()){
						$("#workingRecordList").trigger("reloadGrid");
						$.messageBox({message:"成功修改工作台账!"});
         			}
         			$("#workingRecordDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
      	  	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});

	$('#custom_file_upload').uploadFile({
		queueID:"custom-queue",
		selectInputId:"attachFileNames"
	});

	fillFile();
});
</script>