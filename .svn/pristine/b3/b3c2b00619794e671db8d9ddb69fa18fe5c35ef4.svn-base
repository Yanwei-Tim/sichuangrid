<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="新增目录" class="container container_24">
	 <form id="resourceDirectory-form" method="post"	action="" >
	    <input id="mode" type="hidden" name="mode" value="${mode}" />
	    <input name="isSubmit" id="isSubmit" type="hidden" value="">
	    <s:if test='"add".equals(mode)'>
		   <input id="level" type="hidden" name="directorySetting.parentPersonalDirectory.level" value="${parentDirect.level}" >
		    <input id="id" type="hidden" name="directorySetting.parentPersonalDirectory.id" value="${parentDirect.id}" >
		    <input id="directoryType" type="hidden" name="directorySetting.directoryType" value="${parentDirect.directoryType}"> 
	    </s:if>
	    <s:else>
	         <input id="currentId" type="hidden" name="directorySetting.id" value="${directorySetting.id }">
	    </s:else>
	    
	    <br>
		 <div class="grid_3 lable-right">
		 <em class="form-req">*</em>
			<label>名称：</label>
		 </div>
		<div class="grid_19">
			<input type="text" name="directorySetting.name" <s:if test='"view".equals(mode)'>readonly</s:if>
			class='form-txt {required:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'
				id="directorySettingDetail-name" maxlength="15" value="${directorySetting.name}"/>
				
			</div>	
	 </form>
			
</div>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test='"add".equals(mode)'>
	$("#resourceDirectory-form").attr("action","${path}/resourcePool/directorySettingManage/addDirectorySetting.action");
    </s:if>
    <s:if test='"edit".equals(mode)'>
	$("#resourceDirectory-form").attr("action","${path}/resourcePool/directorySettingManage/updateDirectorySetting.action");
    </s:if>

	$("#resourceDirectory-form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		    $(form).ajaxSubmit({
				success:function(data){
				if(data==null){
					  $.messageBox({message:"系统出错",level: "error"});
					  return;
				}	
					
		    	if(data!=null&&!data.id){
		             $.messageBox({message:data,level: "error"});
		             return;
		          }
	                if("add"==$("#mode").val()){
		                 //$("#directorySettingTree").addChildNode(data.id,data.parentPersonalDirectory.id,data);
	                	 $.messageBox({message:"目录新增成功！"});
	                	 if($("#isSubmit").val()=="true"){
	                		 $("#directorySettingDailog").dialog("close");
	                	 }else{
	                		 $(".form-txt").val("");
	                		 $("#directorySettingTree").jqTreeGrid('clearGridData',false);
	                	 }
	                	 
	                }
	                if("edit"==$("#mode").val()){
	                
	                	//$("#directorySettingTree").setCell(data.id,"name",data.name);
	                	
	    			    $.messageBox({message:"修改成功!"});
	    			    $("#directorySettingDailog").dialog("close");
		            }
	                $("#directorySettingTree").trigger("reloadGrid");
	                $("#directorySettingTree").resetSelection();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
		      			alert("提交数据时发生错误");
	   		    }
			});  
		},
		rules:{
			"directorySetting.name":{
				required:true,
				minlength:2,
				maxlength:25
		   }
		},
		messages:{
			"directorySetting.name":{
				required:"请输入名称",
				minlength:$.format("名称最少需要输入{0}个字符"),
				maxlength:$.format("名称最多只能输入{0}个字符")
		    }
		}
	});

	//禁用回车
	$(document).keypress(function(e){
	    if (e.which == 13){
	        return false;
	    }
	});

});

</script>