<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="dialog-form" title="修改个人信息" class="container container_24" style="overflow:hidden;background:#fff;">
    <div class="grid_6 heightAuto uploadImageBox" style="padding-bottom:10px;">
   		<div class="imgBox">
	   		<s:if test="null!=fourTeamsOrg.headImage && ''!=fourTeamsOrg.headImage">
	   			<img id="imgUrl" src="${fourTeamsOrg.headImage}"></img>
	   		</s:if>
	   		<s:else>
	   			<img id="imgUrl" src="${resource_path}/resource/system/images/avatar.jpg"></img>
	   		</s:else>
	   			<div class="shadow" id="deleteHeaderImageBox" style="display: none">
					<div class="bgc"></div>
					<div  id="deleteHeaderImage" class="deleteHeaderImage">删除头像</div>
				</div>
   		</div>
	    <form id="maintainUrlForm" method="post" action="${path}/imageUpload/uploadImg.action" enctype="multipart/form-data" name="maintainUrlForm">
	   		<div class="uploadfileBtn" id="uploadfileBtnId"><span>上传照片</span><input id="uploadImg" type="file" name="header" /></div>
	    </form>
    </div>

    <form name="updateDetailsForm"  method="post" action="${path }/fourTeamsOrgManage/editFourTeamsOrg.action" id="updateDetailsForm">
    	<div class="grid_18 heightAuto rightPanelBox">
    		<input type="hidden" value="${fourTeamsOrg.id}" name="fourTeamsOrg.id" id="fourTeamsOrgId_value"/>
    		<input id="headerUrl" type="hidden" value="${fourTeamsOrg.headImage}" name="fourTeamsOrg.headImage"/>
	    	
	    	<div class="grid_4 lable-right"><em class="form-req">*</em>队伍:</div>
			<div class="grid_16 form-left">
		   		<input type="text" id="" name="fourTeamsOrg.teamTitle" maxlength="16" value="${fourTeamsOrg.teamTitle}" class="form-txt {required:true,isLawful:true,exculdeParticalChar:true,minlength:2,maxlength:16, messages:{required:'请输入队伍名称',isLawful:'您输入了非法脚本，请重新输入！',exculdeParticalChar:'不能输入非法字符',minlength:$.format('名称至少需要输入{0}个字符'),maxlength:$.format('名称最多需要输入{0}个字符')}}" title="请输入16字以内的文字"/>
			</div>
    		<div class='clearLine'></div>
    		<div class="grid_4 lable-right"><em class="form-req">*</em>姓名:</div>
			<div class="grid_16 form-left">
		   		<input type="text" id="" name="fourTeamsOrg.userName" maxlength="6" value="${fourTeamsOrg.userName}" class="form-txt {required:true,isLawful:true,exculdeParticalChar:true,minlength:2,maxlength:6, messages:{required:'请输入姓名',isLawful:'您输入了非法脚本，请重新输入！',exculdeParticalChar:'不能输入非法字符',minlength:$.format('名称至少需要输入{0}个字符'),maxlength:$.format('名称最多需要输入{0}个字符')}}" title="请输入6字以内的文字"/>
			</div>
    		<div class='clearLine'></div>
    		<div class="grid_4 lable-right"><em class="form-req">*</em>职务:</div>
			<div class="grid_16 form-left">
		   		<input type="text" id="" name="fourTeamsOrg.position" maxlength="20" value="${fourTeamsOrg.position}" class="form-txt {required:true,isLawful:true,exculdeParticalChar:true,minlength:2,maxlength:20, messages:{required:'请输入职务',isLawful:'您输入了非法脚本，请重新输入！',exculdeParticalChar:'不能输入非法字符',minlength:$.format('名称至少需要输入{0}个字符'),maxlength:$.format('名称最多需要输入{0}个字符')}}" title="请输入20字以内的文字"/>
			</div>
    		<div class='clearLine'></div>
    		<div class="grid_4 lable-right"><em class="form-req"></em>手机:</div>
			<div class="grid_16 form-left">
		   		<input type="text" id="" name="fourTeamsOrg.phoneNumber" maxlength="11" value="${fourTeamsOrg.phoneNumber}" class="form-txt {mobile:true,messages:{mobile:'联系手机输入只能是以1开头的11位数字'}}" title="请输入11位以1开头的联系手机  例如：13988888888"/>
			</div>
    		<div class='clearLine'></div>
    		
    	</div>
    	
    </form>
</div>
<script type="text/javascript">
$(document).ready(function(){

	function clearFile(){
		   $("#uploadfileBtnId").html("");
		   $("#uploadfileBtnId").html('<span>上传照片</span><input id="uploadImg" type="file" name="header" />');
		   $("#uploadImg").change(function(){
				$("#maintainUrlForm").submit();
			});
	}
	$(document).ready(function(){
		if($("#headerUrl").val()=='' || $("#headerUrl").val()==null){
			$("#deleteHeaderImageBox").hide();
		}else{
			$("#deleteHeaderImageBox").show();
		}
		$("#updateDetailsForm").formValidate({
	            promptPosition :"bottomLeft",
	            submitHandler: function(form) {
	                $("#updateDetailsForm").ajaxSubmit({
	                	async:false,
	                    success:function(data){
		                   if(data == true || data =='true'){
		                	   $.messageBox({
									message:"操作成功！",
									level:"success"
			                    });
		                	 $("#editFourTeamsOrgDlg").dialog("close");
						  	 refreshData(${fourTeamsOrg.id});
			                }else{
			                	$.messageBox({
									message:data,
									level:"error"
			                    });
					        }
	                    }
	                });
	            },
	            rules: {
	               
	            },
	            messages: {
	              
	            }
	        });

		$("#maintainUrlForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
			$(form).ajaxSubmit({
				async:false,
				success: function(data){
						 if(data == false || data == "false" ){
				        	 $.messageBox({message:"上传失败，请上传正确格式的图像",level: "error"});
				        	 clearFile();
			            	 return;
					     }
						$("#scanHeaderImage").createDialog({
							width: 600,
							height: 600,
							title:"头像截图",
							url:'${path}/scanUserHeaderImg.jsp?imgUrl='+data,
							close:function(){
								$("#uploadImg").val("");
								clearFile();
							},
							buttons: {
								"保存" : function(event){
									$("#scanHeaderImageForm").submit();
								},
								"关闭" : function(){
									clearFile();
									$(this).dialog("close");
								}
							}
						});
					}
				})
			},
			rules:{
				"header":{
					checkFullFile:true,
					checkFileExtName:true
				}
			},
			messages:{
				"header":{
					checkFullFile:"图片为空不能上传",
					checkFileExtName:"图片只能为 jpg,gif格式的"
				}
			}
		});
		$("#uploadImg").change(function(){
			$("#maintainUrlForm").submit();
		});
		$("#deleteHeaderImage").click(function(){
			$("#imgUrl").attr("src","/resource/system/images/avatar.jpg");
			$("#headerUrl").val("");
			$("#deleteHeaderImageBox").hide();
		});
		jQuery.validator.addMethod("checkFullFile", function(value, element){
			if($("#uploadImg").val() == null || $("#uploadImg").val()==""){
				$.messageBox({message:"图片为空不能上传",level: "error"});
				return false;
			 }
			return true;
		});
		jQuery.validator.addMethod("checkFileExtName", function(value, element){
			if($("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length) != "jpg"
				&& $("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length) != "JPG"
				&& $("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length)!="gif"
				&& $("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length) != "GIF"){
				$.messageBox({message:"图片只能为 jpg,gif格式的",level: "error"});
				return false;
			 }
			return true;
		});
	});
});
</script>