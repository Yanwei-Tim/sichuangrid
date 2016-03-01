<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="hiddenDanger" class="container container_24">
		<form action="${path}/baseInfo/hiddenDangerManage/addHiddenDanger.action" method="post" id="maintainFormForhiddenDangerAdd">
	
	
	    <div id="perUuid"></div>
		
	 	<input type="hidden" name="hiddenDanger.organization.orgName" id="orgName" value="${hiddenDanger.organization.orgName }"/>
	 	<input type="hidden" name="hiddenDanger.id" id="hiddenDangerId" value="${hiddenDanger.id }"/>
	
		<input id="organizationId"	type="hidden" name="hiddenDanger.organization.id" value="${hiddenDanger.organization.id}" />
		<div class="grid_6 lable-right">
			<label class="form-lbl">发生日期：</label>
		</div>
		<div class="grid_12">
			<input type="text" name="hiddenDanger.discoverDate" id="discoverDate" class="form-txt"
		value="<s:date name="hiddenDanger.discoverDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: white"/>
		
		</div>
			<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">地点：</label>
		</div>
		<div class="grid_12">
			<input type="text" id="address" name="hiddenDanger.address" class="form-txt" value="${hiddenDanger.address}" maxlength="30"/>
		</div>
			<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">异常类型：</label>
		</div>
		<div class="grid_12">
		  <select name="" class="form-txt" id="exceptionType" onchange="setExceptionType(value)" <s:if test="!'all'.equals(exceptionType)">disabled="true"</s:if>>
		  <pop:PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE" defaultValue="${hiddenDanger.exceptionType.id}" />
			</select>
			
			<input type="hidden" name="hiddenDanger.exceptionType.id" value=""/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">异常情况：</label>
		</div>
		<div class="grid_12 heightAuto" >
		  <textarea rows="4" name="hiddenDanger.exceptionSituation"  cols="" class="form-txt">${hiddenDanger.exceptionSituation}</textarea>
		</div>
			<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_12 heightAuto">
            <textarea rows="4" name="hiddenDanger.remark"  cols="" class="form-txt">${hiddenDanger.remark}</textarea>
        </div>
        
        <div class='clearLine'>&nbsp;</div>
        <div class="filePanel cf">
		<div class="grid_6 lable-right">
			<label class="form-lbl">附件：
				<select id="attachFileNames" name="attachFileNames" multiple="multiple" style="width:200px;display:none"></select>
			</label>
		</div>
		<div class="grid_12 heightAuto" style='margin-top:10px;'>
			<div id="custom-queue_proKey" ></div>
			<input id="custom_file_upload_proKey"  name="uploadFile" type="file"  readonly="readonly" disabled="disabled"/>
		</div>
	</div>
		
	<%-- <div class='clearLine'>&nbsp;</div>
			<div id="subMaintanceDialog"></div>
			<select id="attachFileNames" name="attachFileNames" multiple="multiple" style="display:none"></select>
	
	<div class='clearLine'>&nbsp;</div> --%>
	</form>
	<!-- <div class='clearLine'>&nbsp;</div>
	<div class="filePanel">
		<div class="grid_6 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div id="attachFilesDiv" class="grid_12 heightAuto">
			<div id="custom-queue" class="ui-widget-border"></div>
			
			<input id="custom_file_upload" name="uploadFile" type="file" />
			
		</div>
	</div>
	<div class='clearLine'>&nbsp;</div> -->
	
	
	 </div>	
<script type="text/javascript">



function removeAttachById(fname,id){
	$("#"+fname).find("option[id="+id+"]").remove();
}


function fillFile(){
	<c:if test="${hiddenDanger!=null}">
		<c:if test="${hiddenDanger.hiddenDangerAnnexFiles !=null && fn:length(hiddenDanger.hiddenDangerAnnexFiles)>0}">
			<c:forEach items="${hiddenDanger.hiddenDangerAnnexFiles}" var="hiddenDangerAnnexFile">
		        $("#custom-queue_proKey").addUploadFileValue({
			        id:"${hiddenDangerAnnexFile.id}",
			        filename:"${hiddenDangerAnnexFile.fileName}",
			    	link:"${path}/baseInfo/hiddenDangerManage/downLoadAttachFile.action?keyId=${hiddenDangerAnnexFile.id}",
			        onRemove:function(id){removeAttachById('attachFileNames',id)}
				});
		        $("<option>").attr("id","${hiddenDangerAnnexFile.id}").val("${hiddenDangerAnnexFile.id},${hiddenDangerAnnexFile.fileName}").attr("selected",true).appendTo($("#attachFileNames"));
		     </c:forEach>
	     </c:if>
	</c:if>
}

<s:if test="hiddenDanger.id!=null">
	$("#maintainFormForhiddenDangerAdd").attr("action","${path}/baseInfo/hiddenDangerManage/updateHiddenDanger.action");
</s:if>

function setExceptionType(value){
	$("input[name='hiddenDanger.exceptionType.id']").val(value);
} 

$(document).ready(function(){

	var $exceptionType = "<s:property value='@com.tianque.plugin.taskList.constants.Constants@getHiddenDangerMap(exceptionType)' />";
	var $option = $("#exceptionType option");
	for(var i=0;i<$option.length;i++){
		if($($option[i]).text() === $exceptionType){
			$("#exceptionType option").eq(i).attr("selected",true);
			$("input[name='hiddenDanger.exceptionType.id']").val($("#exceptionType option").eq(i).val());
		}
	}
	
	$('#custom_file_upload_proKey').uploadFile({
		queueID:"custom-queue_proKey",
		selectInputId:"attachFileNames"
	});
	
	 fillFile();
	
	/* $('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});
	function removeAttach(fileName){
		$("input[name='file']").removeAttr("disabled");
		$("#attachFileNames").find("option:contains("+fileName+")").remove();
	}
	 */
	
	$("#maintainFormForhiddenDangerAdd").formValidate({
		submitHandler: function(form){
		
		$(form).ajaxSubmit({
			async:false,
			success : function(data) {
				if (!data) {
					$.messageBox({
						message : "新增修改失败!",
						level : "error"
					});
					return;
				}
	
				$.messageBox({message:"新增修改成功!"}); 

				$("#hiddenDangerList").trigger("reloadGrid");
				$("#hiddenDangerDialog").dialog("close");	
				 
				 
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"hiddenDanger.exceptionSituation":{
				maxlength:250
				},
			"hiddenDanger.exceptionType.id":{
				required:true
					},
			"hiddenDanger.address":{
				required:true,
				maxlength:50
				},
			 "hiddenDanger.remark":{
					maxlength:200
					}
		},
		messages:{	
			"hiddenDanger.exceptionSituation":{
				maxlength:$.format("异常情况最多输入{0}个")
			
			},
			"hiddenDanger.exceptionType.id":{
				required:"请输入异常情况"
					},
			"hiddenDanger.address":{
				required:"请输入发生地址",
				maxlength:$.format("地址最多输入{0}个")
				},
			"hiddenDanger.remark":{
				maxlength:$.format("备注最多输入{0}个")
					}
		}
	});
	
});



</script>