<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div>
    <form action="${path}/serviceList/otherSituationManageManage/addOtherSituationManage.action?" method="post" id="maintainForm">
    <pop:token/>
    <input type="hidden" name="mode" id="mode" value="${mode}" />
	<input type="hidden" name="otherSituationManage.id"  value="${otherSituationManage.id}" />
	<input type="hidden" name="otherSituationManage.organization.id"  value="${otherSituationManage.organization.id}" />
	<input type="hidden" name="otherSituationManage.isSign"  value="${otherSituationManage.isSign}" />
	<div class="container container_24"> 
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">时间：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' name='otherSituationManage.inputTime' value='<s:date name="otherSituationManage.inputTime" format="yyyy-MM-dd HH:mm:ss"/>' class="form-txt" readonly="readonly" />
		</div>
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">地点：</label>
		</div>
		<div class="grid_8 lable-left">
			<input name='otherSituationManage.address' value='${otherSituationManage.address}' title="${otherSituationManage.address}" maxlength="40" class="form-txt {required:true,messages:{required:'地点不能为空'}}" <s:if test='"view".equals(mode)'>readonly="readonly"</s:if> />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">详细情况：</label>
		</div>
		<div class="grid_8 ">
			<input name='otherSituationManage.details' value='${otherSituationManage.details}' title="${otherSituationManage.details}" maxlength="50" class="form-txt {required:true,messages:{required:'详细情况不能为空'}}" <s:if test='"view".equals(mode)'>readonly="readonly"</s:if> />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" name="otherSituationManage.remake" maxlength="300"  cols="" class="form-txt" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> >${otherSituationManage.remake}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
    	<select id="attachFileNames" name="otherSituationManage.attachFileNames" multiple="multiple" style="width:200px;display:none"></select>
    	<input type="hidden" id="deleteAttachIds" name="otherSituationManage.deleteAttachIds" />
    	<input name="isSubmit" id="isSubmit" type="hidden" value="">
    </div>
    </form>
    <div class="container container_24 filePanel">
    		<br/>
			<div class="grid_4 lable-right">
				<label class="form-lbl">附件上传：</label>
			</div>
			<div id="attachFilesDiv" class="grid_19 heightAuto">
				<s:if test='!"view".equals(mode)'>
				<input id="custom_file_upload" name="uploadFile" type="file" />
				</s:if>
				<div id="custom-queue" style="clear:both;border:1px solid #ccc;overflow-x:hidden;height:100px;"></div>
			</div>
			<div class='clearLine'>&nbsp;</div>
	</div>
	<br/>
	<s:if test='"view".equals(mode)'>
	<div class="container container_24"> 
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">网格员姓名：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='createUser' value='${otherSituationManage.createUser}'  readonly="readonly"  class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">电话：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='createUser' value='${otherSituationManage.telephone}'  readonly="readonly"  class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收人：</label>
		</div>
		<div class="grid_8 ">
			<input name='otherSituationManage.signPeople' value='${otherSituationManage.signPeople}' readonly="readonly"  class="form-txt"/>
		</div>
		
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收日期：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' name="otherSituationManage.signDate" value='<s:date name="otherSituationManage.signDate" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"  class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">签收意见：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" id="signContent" name="otherSituationManage.signContent"  cols="" class="form-txt" disabled="disabled" >${otherSituationManage.signContent}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
	</div>
	</s:if>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		submitHandler: function(form){
			 $("#maintainForm").ajaxSubmit({
					success : function(data) {
						if(!data.id){
	           	 			$.messageBox({ 
								level: "error",
								message:data
				 			});
	            			return;
						}
						if("add"=="${mode}"){
							$.messageBox({message:"新增成功！"});
						}
						$("#serviceListDialog").dialog('close');
						$("#otherSituationManageList").trigger("reloadGrid");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
	});
	
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});
	
});
	
<s:if test='!"add".equals(mode)'>	
	<s:if test="otherSituationManage.photos!=null && otherSituationManage.photos.size > 0">
		<s:iterator value="otherSituationManage.photos" var="file">
		    var itemHtml = '<div id="item'+'${file.id}'
				+'" class="uploadifyQueueItem completed">';
			<s:if test='!"view".equals(mode)'>
				itemHtml=itemHtml+'<div class="cancel"><a onclick="deleteAttach(${file.id})"><img src="${path}/resource/external/uploadify/cancel.png"></a></div>';
			</s:if>
			itemHtml=itemHtml+'<a href="'+'${path}/serviceList/otherSituationManageManage/downLoadAttachFile.action?attachFileId=${file.id}'
			+'" target="_blank"><span class="fileName">'+'${file.name}'+'</span></a></div>';
			$("#custom-queue").append($(itemHtml));
		</s:iterator>
	</s:if>	
</s:if>
function deleteAttach(attachId){
	$("#item"+attachId).remove();
	var str=$("#deleteAttachIds").val()+","+attachId;
	$("#deleteAttachIds").val(str);
}
</script>