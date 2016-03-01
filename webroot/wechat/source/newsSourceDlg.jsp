<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<div  class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token></pop:token>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>所属层级：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="weChatSource.org.orgName" class="form-txt"
				value="${weChatSource.org.orgName}" readonly /> <input type="hidden"
				name="weChatSource.org.id" class="form-txt" value="${weChatSource.org.id}" />
	
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>微信号：</label>
		</div>
		<div class="grid_19">
		<input value="${weChatSource.weChatUserId }" type="hidden" id="weChatUserId"/>
			<select name="weChatSource.weChatUserId" class='form-txt' id="weChatSourceWeChatUserId"
				style="width: 510px" readonly>
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
		<div class="grid_19">
			<input type="hidden" name="weChatSource.sourceTypeDict.id"
				value="${weChatSource.sourceTypeDict.id}" /> <input type="text"
				class='form-txt' readonly
				value='<pop:PropertyDictViewTag defaultValue="${weChatSource.sourceTypeDict.id}" name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:PropertyDictViewTag>' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材描述：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="weChatSource.sourceDescription"
				value="${weChatSource.sourceDescription }"
				class='form-txt {required:true,maxlength:100,messages:{required:"请输入素材描述",maxlength:$.format("素材描述最多可以输入{0}个字符")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">标题：</label>
		</div>
		<div class="grid_19" id="titleDiv">
			<input type="text" name="weChatSource.title" value="${weChatSource.title }" class='form-txt {required:true,maxlength:50,messages:{required:"请输入标题",maxlength:$.format("标题最多可以输入{0}个字符")}}'  />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">链接地址：</label>
		</div>
		<div class="grid_19" id="urlDiv">
                <input type="text" name="weChatSource.url"  value="${weChatSource.url }"  class='form-txt {required:true,isUrl:true,maxlength:400,messages:{required:"请输入链接地址",isUrl:"请输入正确的网址",maxlength:$.format("链接地址最多可以输入{0}个字符")}}'  />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">描述：</label>
		</div>
		<div class="grid_19 heightAuto">
	    	<textarea id="Description" name="weChatSource.description"  onkeyup="charlength(value)" style='height:100px;width: 97%' class='form-txt {required:true,maxlength:600,messages:{required:"请输入回复内容",maxlength:$.format("回复内容最多可以输入{0}个字符")}}'>${weChatSource.description }</textarea>
		</div>
	    <div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl"></label>
		</div>
		<div class="grid_19 heightAuto">
			描述已输入<span id="counter" style="line-height: 30px; padding-left: 5px;font-style:normal;">0</span>个字（注意：内容不要超过600字。)
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">图文页面的内容：</label>
		</div>
		<div class="grid_19 heightAuto">
	    	<textarea id="content" name="weChatSource.content"  style='height:100px;width: 97%' class='form-txt {required:true,maxlength:2000,messages:{maxlength:$.format("图文消息页面的内容最多可以输入{0}个字符")}}'>${weChatSource.content }</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		 <select id="attachFileNames" name="weChatSource.path" multiple="multiple" style="display: none"></select>
	   	 <input type="hidden" name="weChatSource.id" value="${weChatSource.id}"/>
		 <input type="hidden" name="weChatSource.org.orgInternalCode" class="form-txt" value="${weChatSource.org.orgInternalCode}" />
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
			<span style="color:red">*图片支持:</span>	<span  style="line-height: 30px; padding-left: 5px;font-style:normal;font-size:13px">jpg/1MB</span>
			</div>
	</div>
	
</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
document.getElementById("counter").innerText = $("#Description").val().length;
//计算文字长度
	function charlength(value){
		var a=value.length;
		document.getElementById("counter").innerText=a;
	 }
	
$(document).ready(function(){
	//附件上传(图片)
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames",
		multi:false,
		maxFileUpload:1,
		queueSizeLimit :1,
		removeAction:"${path}/weChat/inbox/delelteAttachFile.action",
		fileExt        : "*.jpg",
		fileDesc       : '图片',
		sizeLimit:1048576
	});
	$("#attachFileNames").empty();
	 <s:if test='"updateSource".equals(mode)'>
		 $("#maintainForm").attr("action", "${path}/weChatSourceManage/updateWeChatSource.action");
	</s:if>
	<s:else>
			$("#maintainForm").attr("action","${path}/weChatSourceManage/addWeChatSource.action");
	</s:else>
	//提交
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				if ($("#weChatSourceWeChatUserId").val() == "请选择微信号"|| $("#weChatSourceWeChatUserId").val() == "") {
						$.messageBox({level : "warn",message : "请选择要绑定的微信号"});
				       return false;
			     }
				  <s:if test='!"updateSource".equals(mode)'>
				if($("#attachFileNames").val()==""||$("#attachFileNames").val()==null){
					 $.messageBox({message:"请上传图片！",level: "warn"});
					 return false ;
				}
				</s:if>
		    $(form).ajaxSubmit({
		         success: function(data){
		        	 if (data == true|| data == "true") {
							$("#WeChatSourceMaintanceDialog").dialog("close");
						    <s:if test='"updateSource".equals(mode)'>
							  $.messageBox({message : "图文素材修改成功!"});
						   </s:if>
						   <s:else>
						      $.messageBox({message : "图文素材添加成功!"});
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
