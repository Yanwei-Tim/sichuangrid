<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
	.richImg{width:550px;height:100px;}
</style>
<div id="item" class="container container_24">
	<form action="" method="post" id="maintainForm">
		<input type="hidden" name="item.id" value="${item.id }" />
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">事项编号：</label>
		</div>
		<div class="grid_7">                  
			<input type="text" id="itemNumber" name="item.itemNumber" value="${item.itemNumber }" style="width: 99%" 
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入事项编号",exculdeParticalChar:"不能输入非法字符",minlength:$.format("事项编号至少需要输入{0}个字符"),maxlength:$.format("事项编号最多需要输入{0}个字符")}}' maxlength="20"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">事项名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="itemName" name="item.itemName" value="${item.itemName }" style="width: 99%" 
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:30,messages:{required:"请输入事项名称",exculdeParticalChar:"不能输入非法字符",minlength:$.format("事项名称至少需要输入{0}个字符"),maxlength:$.format("事项名称最多需要输入{0}个字符")}}' maxlength="30"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">承诺时限(天)：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="item.promiseTime" id="promiseTime" value="${item.promiseTime }" class='form-txt {required:true,stature:true,messages:{required:"请输入承诺时限",stature:"请输入不大于300的正整数"}}' maxlength="3"/>
		</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">法定时限(天)：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="item.legalTime" id="legalTime" value='${item.legalTime }' class='form-txt {required:true,stature:true,messages:{required:"请输入法定时限",stature:"请输入不大于300的正整数"}}' maxlength="3"/>
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">事项类别：</label>
		</div>
		<div class="grid_4">
			<select name="item.matterKind.id" id="matterKind_id" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ITEM_MATTER_KIND" defaultValue="${item.matterKind.id}" />
			</select>
		</div>

		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">是否收费项目：</label>
		</div>
		<div class="grid_3">
			<input type="checkbox" name="item.Fees" <s:if test="item.Fees==true">checked</s:if> id="isFees" value="true"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">收费标准(元)：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="item.standardFees" <s:if test="item == null || item.Fees!=true">readonly</s:if><s:else>value="${item.standardFees }"</s:else> id="standardFees" maxlength="10"  class='form-txt {positiveInteger:true,messages:{positiveInteger:"请输入正整数"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">备注：</label>
   		</div>
   		<div class="grid_18 heightAuto">
   			<textarea name="item.remark" id="remark" class="richImg {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${item.remark }</textarea>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">法定依据：</label>
   		</div>
   		<div class="grid_18 heightAuto">
   			<textarea name="item.legalBasis" id="legalBasis" class="richImg {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${item.legalBasis }</textarea>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">办事指南：</label>
   		</div>
   		<div class="grid_18 heightAuto">
   			<textarea name="item.lawGuide" id="lawGuide" class="richImg {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${item.lawGuide }</textarea>
   		</div>
   		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
   		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	</form>
	<div style="margin-left: 124px;">
		<div  id="custom-queue" style="width: 552px; height:70px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#remark').richImg();
	$('#legalBasis').richImg();
	$('#lawGuide').richImg();
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
         	$(form).ajaxSubmit({
         		success: function(data){
					if(!data.id){
	               	 	$.messageBox({
							message:data,
							level: "error"
					 	});
                		return;
                	}
                	if("${mode}" == "add"){
						$("#itemList").addRowData(data.id,data);
						$("#itemList").setSelection(data.id);
						$.messageBox({message:"事项信息新增成功!"});
                	}else{
                		 $("#itemList").setRowData(data.id,data);
    					 $.messageBox({message:"事项信息修改成功!"});
                	}
					 $("#itemDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      	alert("提交错误");
	      	   	}
      	  	});
		},
		rules:{
		},
		messages:{
		}
	});

	<s:if test="itemAttachmentList!=null && itemAttachmentList.size > 0">
	    <s:iterator value="itemAttachmentList">
		    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='id'/>",
		     filename:"<s:property value='fileName'/>",
		     link:"${path }/approval/itemManage/downLoadActualFile.action?itemAttachmentId=${id}",   	     	 
			 onRemove:function(){removeAttach("<s:property value='fileName' escape='false'/>")}
		     });
		    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#attachFileNames"));
	    </s:iterator>
	</s:if>
	
	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/approval/itemManage/addItem.action");
	</s:if>
	<s:else>
		$("#maintainForm").attr("action","${path}/approval/itemManage/updateItem.action");
	</s:else>
});

function removeAttach(fileName){
	$("input[name='file']").removeAttr("disabled");
	$("#attachFileNames").find("option:contains("+fileName+")").remove();
}

$("#isFees").click(function(event){
	if($("#isFees").attr("checked")){
		$("#standardFees").removeAttr("readonly");
	}else{
		 $("#standardFees").attr("readonly", true);
		$("#standardFees").val("");
	}
});
</script>