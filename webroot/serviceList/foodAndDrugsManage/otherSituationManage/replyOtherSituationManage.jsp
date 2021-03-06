<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div>
    <form action="${path}/serviceList/otherSituationManageManage/replyOtherSituationManage.action?" method="post" id="maintainForm">
    <input type="hidden" name="mode" id="mode" value="${mode}" />
	<input type="hidden" name="otherSituationManage.id"  value="${otherSituationManage.id}" />
	<input type="hidden" name="otherSituationManage.organization.id"  value="${otherSituationManage.organization.id}" />
	<div class="container container_24"> 
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">回复时间：</label>
		</div>
		<div class="grid_8 lable-left">
			<input name='otherSituationManage.reply.replyDate' value='<s:date name="reply.replyDate" format="yyyy-MM-dd HH:mm:ss"/>'  readonly="readonly"  class="form-txt"/>
		</div>
		
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">回复人：</label>
		</div>
		<div class="grid_8 lable-left">
			<input name='otherSituationManage.reply.replyPeople' value='${reply.replyPeople}'  readonly="readonly"  class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">处理情况：</label>
		</div>
		<div class="grid_20 heightAuto"  >
            <textarea rows="4" name="otherSituationManage.reply.replyContent" maxlength="300"  cols="" class="form-txt" <s:if test='"viewReply".equals(mode)'>disabled="disabled"</s:if> >${reply.replyContent}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div><br/>
		<select id="attachFileNames" name="otherSituationManage.attachFileNames" multiple="multiple" style="width:200px;display:none"></select>
    	<input name="isSubmit" id="isSubmit" type="hidden" value="">
    </div>
    </form>
    <div class="container container_24 filePanel">
			<div class="grid_4 lable-right">
				<label class="form-lbl">附件上传：</label>
			</div>
			<div id="attachFilesDiv" class="grid_19 heightAuto">
				<s:if test='!"viewReply".equals(mode)'>
				<input id="custom_file_upload" name="uploadFile" type="file" />
				</s:if>
				<div id="custom-queue-reply" style="clear:both;border:1px solid #ccc;overflow-x:hidden;height:100px;"></div>
			</div>
	</div>
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
						$.messageBox({message:"回复成功！"});
						$("#serviceListDialog").dialog('close');
						$("#otherSituationManageList").trigger("reloadGrid");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
	});
});

$('#custom_file_upload').uploadFile({
	queueID : 'custom-queue-reply',
	selectInputId : "attachFileNames"
});

<s:if test='"viewReply".equals(mode)'>	
<s:if test="reply.replyAttchs!=null && reply.replyAttchs.size > 0">
	<s:iterator value="reply.replyAttchs" var="file">
	    var itemHtml = '<div id="item'+'${file.id}'
			+'" class="uploadifyQueueItem completed">'+'<a href="'+'${path}/serviceList/otherSituationManageManage/downLoadReplyActualFile.action?attachFileId=${file.id}'
			+'" target="_blank"><span class="fileName">'+'${file.name}'+'</span></a></div>';
	    $("#custom-queue-reply").append($(itemHtml));
	</s:iterator>
</s:if>	
</s:if>

</script>