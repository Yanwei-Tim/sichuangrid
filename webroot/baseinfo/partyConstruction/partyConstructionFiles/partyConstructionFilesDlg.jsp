<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="partyConstructionFiles" class="container container_24">
	<form method="post" id="maintainForm">
		<div id="perUuid"></div>
		<input id="mode" type="hidden" name="mode" value="${mode}" /> <input
			id="partyConstructionFilesID" type="hidden"
			name="partyConstructionFiles.id"
			value="${partyConstructionFiles.id }"> <input id="orgId"
			type="hidden" name="partyConstructionFiles.organization.id"
			value="${organizationId}" />
		<input id="leibie1" type="hidden" name="mode" value="${partyConstructionFiles.dalei}" />
		<input id="leibie2" type="hidden" name="mode" value="${partyConstructionFiles.xiaolei}" />
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text" id="commonOrgName"
				name="partyConstructionFiles.organization.orgName"
				value="${partyConstructionFiles.organization.orgName}"
				style="width: 99%" class="form-txt" readonly />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">标题：</label>
		</div>
		<div class="grid_20">
			<input type="text" id="ptitle" name="partyConstructionFiles.title"
				value="${partyConstructionFiles.title}" style="width: 99%"
				class="form-txt " maxlength="50" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">大类：</label>
		</div>
		<div class="grid_8">
			<input id="fileDalei" name="partyConstructionFiles.dalei" type="hidden"/>
			<select id="dalei" name="" disabled	class="form-txt ">
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">小类：</label>
		</div>
		<div class="grid_8">
			<select id="xiaolei" name="partyConstructionFiles.xiaolei"
				class="form-txt ">
				</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">附件描述：</label>
		</div>
		<div class="grid_20">
			<textarea rows="4" name="partyConstructionFiles.describe"
				id="describe" class="form-txt" style="width: 99%">${partyConstructionFiles.describe }</textarea>
		</div>
		<div class="grid_24 "></div>
		<div class="grid_24 "></div>

		<select style="width: 200px; display: none" id="pfileName"
			name="partyConstructionFiles.fileName">
		</select>
	</form>
	<div class="grid_4 lable-right">
		<p>
			<em class="form-req">*</em>党建文件：
		</p>
	</div>
	<div class="grid_20 ">
		<input id="fileToUpload" type="file" name="upload" /><span
			style="color: red" id="error"></span>
		<p id="filename"></p>
	</div>
</div>

<script type="text/javascript">

<s:if test='"add".equals(mode)'>
$("#maintainForm").attr("action","${path}/baseinfo/partyConstructionFilesManage/addPartyConstructionFiles.action" );
</s:if>
<s:if test='"edit".equals(mode)'>
$("#maintainForm").attr("action","${path}/baseinfo/partyConstructionFilesManage/editPartyConstructionFiles.action");
</s:if>
var index=0;
for(var i=0;i<leibie.length;i++){
	if(leibie[i][0]==$("#leibie1").val()){
	$("#dalei").append("<option value="+leibie[i][0]+" selected>"+leibie[i][0]+"</option>");
	index=i;
	}else{
	$("#dalei").append("<option value="+leibie[i][0]+" >"+leibie[i][0]+"</option>");
	}
}

	for(var i=1;i<leibie[index].length;i++){
		if(leibie[index][i]==$("#leibie2").val()){
		$("#xiaolei").append("<option value="+leibie[index][i]+" selected>"+leibie[index][i]+"</option>");
		}else{
			$("#xiaolei").append("<option value="+leibie[index][i]+" >"+leibie[index][i]+"</option>");
		}
	}


$(document).ready(function(){
	
	
	$('#fileToUpload').uploadFile({
		queueID : 'filename',
		multi:false,
		selectInputId:'pfileName'
	});
     <s:if test='"add".equals(mode)'>
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id" : $("#currentOrgId").val()
		},
		success:function(responseData){
			$("#commonOrgName").val(responseData);
		}
	});
	</s:if>
	 <s:if test='"edit".equals(mode)'>
	 $("#filename").addUploadFileValue({
         id:"<s:property value='partyConstructionFiles.id'/>",
         filename:"<s:property value='partyConstructionFiles.fileName'/>",
         link:"${path }/baseinfo/partyConstructionFilesManage/downloadFile.action?partyConstructionFiles.id=<s:property value='partyConstructionFiles.id'/>",
         onRemove:function(id){$("#pfileName").find("option[id="+id+"]").remove();}
	});
   $("<option>").attr("id","<s:property value='partyConstructionFiles.id'/>").val("<s:property value='partyConstructionFiles.fileName' escape='false'/>").attr("selected",true).appendTo($("#pfileName"));
  
	 </s:if>
	jQuery.validator.addMethod("exsistedTitle", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/partyConstructionFilesManage/hasDuplicatePartyConstructionFiles.action",
		   	data:{
				"partyConstructionFiles.title":$('#ptitle').val(),
				"organizationId":$('#orgId').val(),
				"mode":$('#mode').val(),
				"partyConstructionFiles.id":$('#partyConstructionFilesID').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			var name=$("#pfileName option").length;
			if(name==0){
				$("#error").html("附件必须上传");
				return;
			}
			
		 $(form).ajaxSubmit({
			 success : function(data) {
				if (!data.id) {
					$.messageBox({
						message : data,
						level : "error"
					});
					return;
				}
				<s:if test='"add".equals(mode)'>
					 $.messageBox({message:"新党建文件信息新增成功！"});
			       //  doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
			    </s:if>
			    <s:if test='"edit".equals(mode)'>
			        $.messageBox({message:"新党建文件信息修改成功！"});
			     //    doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
			    </s:if>
			    document.location.reload();
			    $("#partyConstructionFilesDialog").dialog("close");
			    onOrgChanged(getCurrentOrgId(),isGrid());
			    
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"partyConstructionFiles.title":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:100,
				exsistedTitle:true
			}
			
		},
		messages:{
			"partyConstructionFiles.title":{
				required:"请输入标题",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("标题至少需要输入{0}个字符"),
				maxlength:$.format("标题最多需要输入{0}个字符"),
				exsistedTitle:"标题已存在，请重新输入"
			}
		}
	
	});
	
	$("#dalei").change(function(){
		$("#fileDalei").val($("#dalei").val());
		$("#xiaolei option").remove(); 
		var selected=$("#dalei").val();
		for(var i=0;i<leibie.length;i++){
			if(selected==leibie[i][0]){
				for(var j=1;j<leibie[i].length;j++){
					$("#xiaolei").append("<option value="+leibie[i][j]+">"+leibie[i][j]+"</option>");
				}
				return;
			}
		}
	});
	$("#fileDalei").val($("#dalei").val());

});

</script>