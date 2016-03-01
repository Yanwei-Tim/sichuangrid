<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
.xheLayout{width:533px !important;*width:519px !important;background:#ccc;}
</style>
<div class="container container_24">
	<form id="maintainForm" method="post"	action="${path}/peopleLog/peopleLogManage/maintainPeopleLog.action" >
		<input type="hidden" name="mode" id="mode" value="${mode}"/>
		<input type="hidden" name="peopleLog.id" value="${peopleLog.id}" id="peopleLogId"/>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
		<input type="hidden" name="peopleLog.isAttachment" id="isAttachment">	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1"> 所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text"    readonly value="${peopleLog.organization.orgName}" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 日志所属人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="peopleLog.belonger" id="belonger" value="${peopleLog.belonger}" class="form-txt {required:true,maxlength:30,messages:{required:'请输入日志所属人',maxlength:'最多只能输入30字符'}}"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 时间：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="peopleLog.publishDate" id="publishDate" value='<s:date name="peopleLog.publishDate" format="yyyy-MM-dd"/>' maxlength="18" readonly class="form-txt {required:true,messages:{required:'请输入日志时间'}}" style="width:93%;"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lb1">地点：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="peopleLog.address" id="address" value="${peopleLog.address}" maxlength="50"  class="form-txt "/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 标题：</label>
		</div>
		<div class="grid_20" style='margin-bottom:8px;'>
			<input type="text" name="peopleLog.title" id="peopleLogTitle" value="${peopleLog.title}" class="form-txt {required:true,maxlength:20,exculdeParticalChar:true,messages:{required:'请输入日志标题',maxlength:'最多只能输入20字符',exculdeParticalChar:'不能输入非法字符'}}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 内容：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea name="peopleLog.contents" id="contents" class="form-txt {required:true,isLawful:true,messages:{required:'请输入日志内容',isLawful:'您输入了非法脚本，请重新输入'}}" style="height:120px;">${peopleLog.contents}</textarea>
		</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		<label class="form-lb1">总结：</label>
	</div>
	
	<div class="grid_20 heightAuto">
	<textarea id=summary name="peopleLog.summary" maxlength="300" class="form-txt" style="height:90px">${peopleLog.summary}</textarea>
    </div>
    
    <select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	</form>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="filePanel">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div class="grid_20" style='*margin-top:10px;'>
			<div id="custom-queue" style="width:533px;*width:519px;height:65px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" />
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	function isAttachFileValue(){
		if($("#attachFileNames").val()){
			$("#isAttachment").val(true);
		}else{
			$("#isAttachment").val(false);
		}
	}
	
	$("#belonger")[0].focus();
	$('#contents').richImg();
	$('#publishDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
    	maxDate:'+0d',
    	onSelect: function(dateText, inst) {
    		if(dateText!=null&&dateText!=''){
    			var dateUnit=dateText.split('-');
    			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2])
    			}
    		}
	});

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			isAttachFileValue();
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
           	 				message:data,
							level: "error"
			 			});
            			return;
					}
				 if("add"==$("#mode").val()){
					$("#peopleLogList").setSelection(data.id);
					if($("#isSubmit").val()=="true"){
				 		 $("#peopleLogDialog").dialog("close");
			 		}else{
					    emptyObject();
					}
		   			$.messageBox({message:" 民情日志新增成功！"});
		   			if(typeof(statistics) != 'undefined'){
		   				statistics();
		   			}
		   			if($("#peopleLogTabs").attr("id") && $("#peopleLogTabs").getTabId($("#peopleLogTabs").tabs("option","selected"))=="myPeopleLog"){
		   				myLogstatistics();
		   			}
		   			if(typeof(peopleLogListMax) != 'undefined'){
		   				myLogstatistics();
		   				$("#peopleLogListMax").trigger("reloadGrid");
		   			}
		   			$("#peopleLogList").trigger("reloadGrid");
				 }
				 if("edit"==$("#mode").val()){
					 $("#peopleLogList").setRowData(data.id,data);
						$("#updateDialog").dialog("close");
			   			$.messageBox({message:" 民情日志修改成功！"});
			   			$("#peopleLogList").trigger("reloadGrid");
					 }
				 	document.title = $("#myPeopleLogManagement").text();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"peopleLog.summary":{
				required:false,
				maxlength:300}
		},
		messages:{
			"builddatas.phone":{
				required:"请输入日志总结",
				maxlength:$.format("日志总结最多需要输入{0}个字符")}
		}
	});
	
	function emptyObject(){
		$("#belonger").val("");
		$("#publishDate").val("");
		$("#address").val("");
		$("#peopleLogTitle").val("");
		$("#contents").val("");
		$("#summary").val("");
		clearAttachments();
	}
	
	function clearAttachments(){
		$("#attachFileNames").html("");
		$.each($("#custom-queue div"),function(i,c){
			$(c).remove();
		});
	}
	
	//附件
	$('#custom_file_upload').uploadFile({
		queueID:"custom-queue",
		selectInputId:"attachFileNames"
	});
	fillFile();
});

function fillFile(){
	<s:if test="peopleLog.peopleLogFiles!=null && peopleLog.peopleLogFiles.size>0">
		<s:iterator value="peopleLog.peopleLogFiles">
	        $("#custom-queue").addUploadFileValue({
		        id:"<s:property value='id'/>",
		        filename:"<s:property value='fileName'/>",
		    	link:"${path}/peopleLog/peopleLogManage/downloadPeopleLogAttachFile.action?peopleLogAttachFile.id=<s:property value='id'/>",
		        onRemove:function(id){deletePeopleLogAttachFile(id)}
			});
	        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
	     </s:iterator>
	</s:if>
}

function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}

function deletePeopleLogAttachFile(id){
	$.ajax({
		url:"${path}/peopleLog/peopleLogManage/deletePeopleLogAttachFile.action?peopleLogAttachFile.id="+id,
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
