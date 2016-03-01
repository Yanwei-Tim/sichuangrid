<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainFirstDirectory" action="javascript:void(0);" method="post">
		<input type="hidden" id="parentId" value="${dailyDirectory.parentDailyDirectory.id}" name="dailyDirectory.parentDailyDirectory.id" />
		<input type="hidden" id="dailyYearId" value="${dailyDirectory.dailyYear.id}" name="dailyDirectory.dailyYear.id" />
		<input type="hidden" id="dailyDirectoryId" value="${dailyDirectory.id}" name="dailyDirectory.id" />
		<input type="hidden" id="timeLimit" value="${dailyDirectory.timeLimit}" name="dailyDirectory.timeLimit" />
		<input type="hidden" id="effectiveDays" value="${dailyDirectory.effectiveDays}" name="dailyDirectory.effectiveDays" />
		<input type="hidden" id="deadlineType" value="${dailyDirectory.deadlineType}" name="dailyDirectory.deadlineType" />
		<input type="hidden" id="deadlineDate" value="<s:date name="dailyDirectory.deadlineDate" format="yyyy-MM-dd"/>"  name="dailyDirectory.deadlineDate"/>
		<input type="hidden" id="deadlineStart" value="${dailyDirectory.deadlineStart}" name="dailyDirectory.deadlineStart" />
		<input type="hidden" id="deadlineEnd" value="${dailyDirectory.deadlineEnd}" name="dailyDirectory.deadlineEnd" />
		<s:if test='null!=dailyDirectory.type and "add".equals(mode) and null!=domainList'>
			<div class="grid_6 lable-right"><label><font color='red'>*</font>台账目录名称：</label></div>
			<div class="grid_18">
		  	   <select id="fullName"  name="dailyDirectory.fullName" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" style="250px">
					<s:iterator value="domainList" var="domain">
						<option value='<s:property value="#domain.displayName"/>'><s:property value="#domain.displayName"/></option>
					</s:iterator>
			   </select>
			</div>
			<div class="grid_6 lable-right"><label><font color='red'>*</font>目录类型：</label></div>
			 
			<div class="grid_18" >
			  <s:if test="@com.tianque.domain.property.DailyDirectoryTypes@SERVICE_MANAGEMENT_NAME==dailyDirectory.typeName">
			       <select id="directoryReportType" name="dailyDirectory.type.id" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select">
			  </s:if>
			  <s:else>
			  	   <input type="hidden" value="${dailyDirectory.type.id}" name="dailyDirectory.type.id" />
			  	   <select id="directoryReportType" name="dailyDirectory.directoryReportType.id" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" style="250px">
			  </s:else>
					<s:iterator value="domainList" var="domain">
						<option value='<s:property value="#domain.id"/>'><s:property value="#domain.displayName"/></option>
					</s:iterator>
				   </select>
			</div>
		</s:if>
		<s:else>
		    <div class="grid_6 lable-right"><label><font color='red'>*</font>台账目录名称：</label></div>
			<div class="grid_18">
				<input type="text" id="fullName" value="${dailyDirectory.fullName}" name="dailyDirectory.fullName" style="width:99.2%" 
				<s:if test='"view".equals(mode)'>disabled</s:if> maxlength="30" class="form-txt" />
			</div>
		</s:else>
		
		<s:if test='null!=dailyDirectory.type and "add".equals(mode)'>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_6 lable-right">
				<label>附 件：</label>
			</div>
			<div id="custom-queue" class="grid_18 heightAuto" style="height: 60px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9;margin-top:8px;"></div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5" >&nbsp;</div>
			<div class='grid_5' style="margin-top:6px" >
				<input id="custom_file_upload" disabled name="uploadFile" type="file" />
				<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none;margin-top;"></select>
			</div>
		</s:if>
		<s:else>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_6 lable-right">
				<label>附 件：</label>
			</div>
			<div id="custom-queue" class="grid_18 heightAuto" style="height: 60px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9;margin-top:8px;"></div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5" >&nbsp;</div>
			<div class='grid_5' style="margin-top:6px" >
				<input id="custom_file_upload" disabled name="uploadFile" type="file" />
				<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none;margin-top;"></select>
			</div>
		</s:else>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	initAttachFileUploader(); // 初始化附件上传工具
	<s:if test='!"add".equals(mode)'>
		fillIssueAttenchFiles();// 填充附件
	</s:if>
	
	jQuery.validator.addMethod("countExsistedSubDirectory", function(value, element){
		if(value==null||value==undefined||value==""){return true}		
		$.ajax({
			async:false,
			url: "${path}/daily/dailyDirectoryManage/countExsistedSubDirectory.action",
			data:{
				"dailyDirectory.fullName":$("#fullName").val(),
				"parentId":$("#parentId").val(),
				"dailyDirectory.id":$("#dailyDirectoryId").val(),
				"dailyYearId":$("#dailyYearId").val()
			},
			success:function(data){
				if(data > 0){
					existed = false;
				}else{
					existed = true;
				}
			}
		});
		return existed;
	});
	
	$("#maintainFirstDirectory").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			 directoryAjaxSubmit();
		},
		rules:{
			"dailyDirectory.fullName":{
				<s:if test='null!=dailyDirectory.type and "add".equals(mode) and null!=domainList'>
					required:false,
				</s:if>
				<s:else>
					required:true,
					minlength:2,
					maxlength:90,
				</s:else>
				<s:if test='"add".equals(mode)'>
					countExsistedSubDirectory:true
				</s:if>
				<s:if test='"edit".equals(mode)'>
					countExsistedSubDirectory:false
				</s:if>
			}
		},
		messages:{
			"dailyDirectory.fullName":{
				required:"请输入目录名称",
				minlength:$.format("目录名称最少需要输入{0}个字符"),
				maxlength:$.format("目录名称最多只能输入{0}个字符"),
				countExsistedSubDirectory:"台帐目录名称已经存在!"
			}
		}
	});

	<s:if test='"add".equals(mode)'>
		$("#maintainFirstDirectory").attr("action","${path}/daily/dailyDirectoryManage/addDailyDirectory.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#maintainFirstDirectory").attr("action","${path}/daily/dailyDirectoryManage/updateDailyDirectory.action");
	</s:if>

});

function initAttachFileUploader(){
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});
	$("#attachFileNames").empty();
}

function fillIssueAttenchFiles(){
	<s:if test="dailyDirectory!=null && dailyDirectory.getDailyDirectoryAttachFiles()!=null && dailyDirectory.getDailyDirectoryAttachFiles().size()>0">
	    <s:iterator value="dailyDirectory.getDailyDirectoryAttachFiles()" var="obj">
		    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='#obj.fileId'/>",
		     filename:"<s:property value='#obj.fileName'/>",
		     link:"${path }/daily/dailyDirectoryManage/downLoadAttachFile.action?keyId=<s:property value='#obj.fileId'/>",   	     	 
			 onRemove:function(){removeAttach("<s:property value='#obj.fileName' escape='false'/>")}
		     });
		    $("<option>").attr("id","<s:property value='#obj.fileId'/>").val("<s:property value='#obj.fileId'/>,<s:property value='#obj.fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#attachFileNames"));
	    </s:iterator>
	</s:if>
}

function removeAttach(fileName){
	$("input[name='file']").removeAttr("disabled");
	$("#attachFileNames").find("option:contains("+fileName+")").remove();
}

function directoryAjaxSubmit(){

	$("#maintainFirstDirectory").ajaxSubmit({
        success: function(data){
			if(!data.id){
				$.messageBox({message:data,level: "error"});
				return;
			}
			var listId = $("#dailyDirectoryList");
			<s:if test='"add".equals(mode)'>
				if(data.parentId == 0){
					setPlace(data);
				}else{
					listId.addChildNode(data.id,data.parentId,data);
				}
				var level = listId.getRowData(data.id).level;
				if(isNaN(level)){
					level = $($(level)).text();
				}
				var _str = "td[aria-describedby='dailyDirectoryList_fullName']";
				$("#"+data.id+"").find(_str).prepend('<div class="tree-wrap tree-wrap-ltr" style="width:'+(18+18*level)+'px;"><div style="left:'+(18*level)+'px;" class="ui-icon ui-icon-radio-off tree-leaf treeclick"></div></div>');
				$("#"+data.parentId).find(_str).find(".treeclick").unbind("click").click(function(){
					if($("#"+data.parentId).find(_str).find(".ui-icon-triangle-1-e").length == 0){
						$(this).addClass("ui-icon-triangle-1-e").addClass("tree-minus").removeClass("ui-icon-triangle-1-s").removeClass("tree-plus");
						hideRowData(listId.getDataIDs(),data.parentId);
					}else if($("#"+data.parentId).find(_str).find(".ui-icon-triangle-1-s").length == 0){
						$(this).addClass("ui-icon-triangle-1-s").addClass("tree-plus").removeClass("ui-icon-triangle-1-e").removeClass("tree-minus");
						showRowData(listId.getDataIDs(),data.parentId);
					}
				});
				listId.setSelection(data.id);
				$("#dailyDirectoryList").trigger("reloadGrid");
				$.messageBox({message:"成功保存新台账目录信息!"});
			</s:if>
			<s:if test='"edit".equals(mode)'>
				$("#"+data.id).find("td[aria-describedby=dailyDirectoryList_fullName]")[0].childNodes[1].nodeValue = data.fullName;
				var attachFileStr = attachFileFormatter("", "", data);
				$("#"+data.id).find("td[aria-describedby=dailyDirectoryList_dailyDirectoryAttachFiles]").html(attachFileStr);
				$("#dailyDirectoryList").trigger("reloadGrid");
				$.messageBox({message:"成功保存台账目录修改信息!"});
			</s:if>
			attachFileShow();
			$("#dailyDirectoryDialog").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
			$.messageBox({message:"提交错误",level: "error"	});
 	   }
 	});
}

function setPlace(data){
	var datas = $("#dailyDirectoryList").getGridParam("data");
	var indexId = 0;
	var placeId = 0;
	$.each(datas,function(a,b){
		var _level = b.level;
		if(isNaN(_level)){
			_level = $($(level)).text();
		}
		if(parseInt(_level) == 0 && b.indexId > indexId){
			indexId = b.indexId;
			placeId = b.id;
		}
		return;
	});
	$("#dailyDirectoryList").addChildNode(data.id,null,data);
	$("#"+placeId).before($("#"+data.id));
	$("#dailyDirectoryList").setCell(data.id,"level",0);
	$("#dailyDirectoryList").setCell(placeId,"indexId",indexId+1);
}

function hideRowData(ids,parentId){
	for(var i=0;i<ids.length;i++){
		var rowDataId = $("#dailyDirectoryList").getRowData(ids[i]).parentId;
		if(rowDataId == parentId){
			$("#"+ids[i]).hide();
			hideRowData(ids,ids[i]);
		}
	}
	return ;
}

function showRowData(ids,parentId){
	var _str = "td[aria-describedby='dailyDirectoryList_fullName']";
	for(var i=0;i<ids.length;i++){
		var rowDataId = $("#dailyDirectoryList").getRowData(ids[i]).parentId;
		if(rowDataId == parentId){
			if($("#"+parentId).find(_str).find(".ui-icon-triangle-1-e").length == 0){
				$("#"+ids[i]).show();
				showRowData(ids,ids[i]);
			}
		}
	}
	return ;
}
</script>