<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<div class="container container_24">
	<form id="maintainForm" method="post" action="">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>所属层级：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="weChatSource.org.orgName" class="form-txt"
				value="${weChatSource.org.orgName}" readonly /> <input type="hidden"
				name="weChatSource.org.id" class="form-txt" value="${weChatSource.org.id}" />

		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>微信号：</label>
		</div>
		<div class="grid_20">
			<select name="weChatSource.weChatUserId" class='form-txt' id="weChatUserId"
				style="width: 540px" readonly>
				<option value="请选择微信号">请选择微信号</option>
				<s:iterator value="listTencentUser">
					<s:if test="#request.weChatUserId==#request.weChatSource.weChatUserId">
						<option value="${weChatUserId }" selected>${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
							${name}]</option>
					</s:if>
					<s:else>
						<option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
							${name}]</option>
					</s:else>
				</s:iterator>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材类型：</label>
		</div>
		<div class="grid_20">
			<input type="hidden" name="weChatSource.sourceTypeDict.id"
				value="${weChatSource.sourceTypeDict.id}" /> <input type="text"
				class='form-txt' readonly
				value='<pop:PropertyDictViewTag defaultValue="${weChatSource.sourceTypeDict.id}" name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:PropertyDictViewTag>' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材描述：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="weChatSource.sourceDescription"
				value="${weChatSource.sourceDescription }"
				class='form-txt {required:true,maxlength:100,messages:{required:"请输入素材描述",maxlength:$.format("素材描述最多可以输入{0}个字符")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<select id="attachFileNames" name="weChatSource.path" multiple="multiple"
			style="display: none"></select>
				<input type="hidden" name="weChatSource.id" value="${weChatSource.id}"/>
				<input type="hidden" name="weChatSource.org.orgInternalCode" class="form-txt"
				value="${weChatSource.org.orgInternalCode}" />
	</form>
	<div class='clearLine'>&nbsp;</div>
	<div class="filePanel">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div id="attachFilesDiv" class="grid_20 heightAuto">
			<div id="custom-queue"></div>
			<input id="custom_file_upload" name="uploadFile" type="file" />
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl"></label>
			</div>
			<div class="grid_20 heightAuto">
			<span style="color:red">*语音支持:</span>	<span  style="line-height: 30px; padding-left: 5px;font-style:normal;font-size:13px">mp3/256KB</span>
			</div>
	</div>

</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
$(document).ready(function(){
	//附件上传(图片)
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames",
		multi:false,
		maxFileUpload:1,
		queueSizeLimit :1,
		removeAction:"${path}/weChat/inbox/delelteAttachFile.action",
		fileExt        : "*.mp3",
		fileDesc       : '语音*.mp3;',
		sizeLimit:262144
	});
	$("#attachFileNames").empty();
	 <s:if test='"updateSource".equals(mode)'>
		$("#maintainForm")
		.attr("action",
				"${path}/weChatSourceManage/updateWeChatSource.action");
	</s:if>
	<s:else>
	$("#maintainForm")
	.attr("action",
			"${path}/weChatSourceManage/addWeChatSource.action");
	</s:else>
	//提交
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			if ($("#weChatUserId").val() == "请选择微信号"|| $("#weChatUserId").val() == "") {
					$.messageBox({level : "warn",message : "请选择要绑定的微信号"});
			       return false;
		     }
			  <s:if test='!"updateSource".equals(mode)'>
			if($("#attachFileNames").val()==""||$("#attachFileNames").val()==null){
				 $.messageBox({message:"请上传语音！",level: "warn"});
				 return false ;
			}
			</s:if>
	    $(form).ajaxSubmit({
	         success: function(data){
	        	 if (data == true|| data == "true") {
						$("#WeChatSourceMaintanceDialog").dialog("close");
					    <s:if test='"updateSource".equals(mode)'>
						  $.messageBox({message : "语音素材修改成功!"});
					   </s:if>
					   <s:else>
					      $.messageBox({message : "语音素材添加成功!"});
					  </s:else>
					
						$("#weChatSourceList").trigger("reloadGrid");
					} else {
						$.messageBox({message : data,level : "error"});
					}	
	  	   },
	  	   error: function(XMLHttpRequest, textStatus, errorThrown){
	  	      alert("提交错误");
	  	   }
	  	  });
		}
	});
	
});

</script>


