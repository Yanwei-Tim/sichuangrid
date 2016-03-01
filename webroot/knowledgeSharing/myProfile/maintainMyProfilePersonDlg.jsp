<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="资料维护" class="container container_24">
	 <form id="myProfile-form" method="post" action="" >
	     <input name="isSubmit" id="isSubmit" type="hidden" value="">
	     <input type="hidden" name="myProfile.resourcePoolType.id" value="${myProfile.resourcePoolType.id}">
	     <input id="mode" type="hidden" name="mode" value="${mode}" />
	     <input id="myProfile.id" type="hidden" name="myProfile.id" value="${id }" >
	    <div class="grid_4 lable-right">
	        <em class="form-req">*</em>
			<label >名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="myProfile.name" class="form-txt" maxlength="150"
			<s:if test='"view".equals(mode)'>disabled="disabled"</s:if> id="myProfile_name" value="${myProfile.name}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>



	    <div class="grid_4 lable-right">
			<label >撰文时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="myProfile.releaseTime" class="form-txt" maxlength="20"
			<s:if test='"view".equals(mode)'>disabled="disabled"</s:if>
			readonly id="myProfile_releaseTime" value='<s:date name="myProfile.releaseTime" format="yyyy-MM-dd"/>' />
			
		</div>
	    <div class="grid_1">&nbsp;</div>

	    <div class="grid_4 lable-right">
	        <em class="form-req">*</em>
			<label >作者：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="myProfile.releaseUnit" class="form-txt" maxlength="50" style="width: 91%"
			<s:if test='"view".equals(mode)'>disabled="disabled"</s:if> id="myProfile_fileNo" value="${myProfile.releaseUnit}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	

		

		<div class="grid_4 lable-right">
			<label >内容：</label>
		</div>
		<div class="grid_19 heightAuto">
			<textarea name="myProfile.content" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> style="width: 96.5%"
			id="myProfile_content" class="form-txt">${myProfile.content}</textarea>
		</div>
		<input type="hidden" name="orgIds" id="orgIds" value=""/>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"> </select>
	
   	</form>
  
   	<div class="filePanel">
		   	<div class="grid_4 lable-right">
				<label class="form-lbl">附件上传：</label>
			</div>

			<div id="attachFilesDiv" class="grid_19 heightAuto">
				<div id="custom-queue" class="ui-widget-border" style="height:100px"></div>
				<s:if test='!"view".equals(mode)'>
					<input id="custom_file_upload" name="uploadFile" type="file" />
				</s:if>
			</div>

	</div>
	<div class='clearLine'>&nbsp;</div>

</div>
<style>
	#custom-queue{width:548px; height: 60px;overflow-y: auto;overflow-x: hidden;}
	#myProfile_content{height:70px;*height:60px;}
	.filePanel{position:absolute;top:155px;left:10px;width:720px;}
</style>
<script type="text/javascript">
$("#myProfile_releaseTime").datePicker({
	yearRange: '1900:2030',
	dateFormat: 'yy-mm-dd',
	maxDate:'+0d'
});
function emptyObject(){
	$("#myProfile-form")[0].reset();
	$("#attachFileNames").html("");
	$("#custom-queue").html("");
	$("#editorDate").val(new Date());
	$("#overdueDate").hide();
}
$(document).ready(function(){
	<s:if test='"add".equals(mode)'>	
	   $("#myProfile-form").attr("action","${path}/resourcePool/myProfileManage/addMyProfile.action");
    </s:if>
    
    <s:if test='"edit".equals(mode)'>	
	   $("#myProfile-form").attr("action","${path}/resourcePool/myProfileManage/updateMyProfile.action");
    </s:if>
    $("#myProfile-form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		    $("#myProfile-form").ajaxSubmit({
				success:function(data){
		    	if(data!=null&&!data.id){
		             $.messageBox({message:data,level: "error"});
		             return;
		          }
	                if("add"==$("#mode").val()){
		                
	                	 $.messageBox({message:"文件新增成功！"});
	                	 if($("#isSubmit").val()=="true"){
	                		 $("#myProfileList").trigger("reloadGrid");
	                		 $("#myProfileDailog").dialog("close");
	                	 }else{
	                		 $("#myProfileList").trigger("reloadGrid");
	                		 emptyObject();
	                	 }
	                	 
	                }
	                if("edit"==$("#mode").val()){
	                	$("#myProfileList").setRowData(data.id,data);
	                	$.messageBox({message:"修改成功!"});
	    			    $("#myProfileDailog").dialog("close");
	    			    $("#myProfileList").trigger("reloadGrid");
	                }

				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
		      			alert("提交数据时发生错误");
	   		    }
			});  
		},
		rules:{
			"myProfile.name":{
				required:true,
				minlength:2,
				maxlength:150
		   },
		  "myProfile.releaseUnit":{
			   required:true,
			maxlength:50
		  },
		  "myProfile.fileNo":{
			maxlength:30
		  },
		  "myProfile.documentSubject":{
			  maxlength:50
		  }
		},
		messages:{
			"myProfile.name":{
				required:"请输入名称",
				minlength:$.format("名称最少需要输入{2}个字符"),
				maxlength:$.format("名称最多只能输入{25}个字符")
		    },
			"myProfile.releaseUnit":{
		    	required:"请输入作者",
				maxlength:$.format("作者最多只能输入{50}个字符")
			},
			"myProfile.fileNo":{
				maxlength:$.format("内容最多只能输入{50}个字")
			},
			"myProfile.documentSubject":{
				maxlength:$.format("主题最多只能输入{50}个字")
			}
		}
	});
    $('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		
		selectInputId : "attachFileNames"
	});
    fillFile();

});
function fillFile(){
	<s:if test="myProfile.myProfileAttachFile!=null && myProfile.myProfileAttachFile.size > 0">
    <s:iterator value="myProfile.myProfileAttachFile">
    $("#custom-queue").addUploadFileValue({
          id:"<s:property value='id'/>",
          filename:"<s:property value='fileName'/>",
          link:"${path}/resourcePool/myProfileManage/downloadMyProfileAttachFile.action?myProfileAttachFile.id=<s:property value='id'/>",
          <s:if test='"view".equals(mode)'>
          	showCloseButton:false,
          </s:if>
          onRemove:function(id){deleteMyProfileAttachFile(id)}
	});
    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
    </s:iterator>
</s:if>
}
function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}
function deleteMyProfileAttachFile(id){
	$.ajax({
		url:"${path}/resourcePool/myProfileManage/deleteMyProfileAttachFile.action?myProfileAttachFile.id="+id,
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