<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<jsp:include page="/includes/baseInclude.jsp" />

<input type="hidden" id="imageUrl" value="${leaderTeams.imageUrl }" />
<div id="dialog-form" title="修改个人信息" class="container container_24" style="overflow:hidden;background:#fff;">
	<div class="grid_5 heightAuto uploadImageBox">
		<div class="imgBox">
			<s:if test="null!=leaderTeams.imageUrl && ''!=leaderTeams.imageUrl">
			<img id="imgUrl" src="${leaderTeams.imageUrl}"></img>
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
			<div class="uploadfileBtn" id="uploadfileBtnId" ><span>上传照片</span><input id="uploadImg" type="file" name="header" /></div>
		</form>
	</div>

	<form name="updateDetailsForm" method="post" action="${path }/baseinfo/villageProfile/addLeaderTeam.action" id="updateDetailsForm">
		<pop:token /> 
		<div class="grid_19 heightAuto rightPanelBox">
			<pop:token />
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" value="${leaderTeams.id}" name="leaderTeams.id" />
			<input type="hidden" value="${leaderTeams.orgCode}" name="leaderTeams.orgCode" id="orgCode"/>
			<input type="hidden" value="${leaderTeams.orgId}" name="leaderTeams.orgId" id="orgId"/>
			<input id="headerUrl" type="hidden" value="${leaderTeams.imageUrl}" name="leaderTeams.imageUrl" />
			<div class="clearfix">
				<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">姓名：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" name="leaderTeams.name" maxlength="20"
					id="name" value="${leaderTeams.name}"  <s:if test='"view".equals(mode)'>readonly</s:if>/>
			</div>
			<div class="grid_1"></div>

			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">性别：</label>
			</div>
			<div class="grid_6">
				<select name="leaderTeams.gender" id="gender" class="form-select" <s:if test='"view".equals(mode)'>readonly</s:if> >
					<option value="">请选择</option>
					<option value="0" <s:if test="0== leaderTeams.gender">selected</s:if> >男</option>
					<option value="1" <s:if test="1== leaderTeams.gender">selected</s:if>>女</option>
					<option value="2" <s:if test="2== leaderTeams.gender">selected</s:if>>未知</option>
				</select>
			</div>
			<div class="grid_3"></div>
			</div>
			<div class="clearline"></div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">职务：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" maxlength="30"  <s:if test='"view".equals(mode)'>readonly</s:if>
					name="leaderTeams.duty" id="duty" value="${leaderTeams.duty}" />
			</div>
			<div class="grid_1"></div>		
			 <div class="grid_4 lable-right">
				&nbsp;
			</div>
			<div class="grid_6">
				&nbsp;
			</div>
			<div class="grid_1"></div>
			<div class="clearline"></div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">简介：</label>
			</div>
			<div class="grid_18 heightAuto">
				<textarea id="introduction" name="leaderTeams.introduction" maxlength="1000"
					defaultvalue="" class="form-txt" <s:if test='"view".equals(mode)'>readonly</s:if> style="height:220px;">${leaderTeams.introduction}</textarea>
			</div>
		</div>
	</form>
</div>
<div id="scanHeaderImage"></div>
<script type="text/javascript">

function clearFile(){
	   $("#uploadfileBtnId").html("");
	   $("#uploadfileBtnId").html('<span>上传照片</span><input id="uploadImg" type="file" name="header" />');
	   $("#uploadImg").change(function(){
			$("#maintainUrlForm").submit();
		});
	   
}


function addLesderInfoDiv(data){
	var gender = "";
	if(data.gender=='0'){
		gender="(男)";
	}else if(data.gender=='1'){
		gender="(女)";
	}else{
		gender="(未知)";
	}
	var imageUrl = "/resource/system/images/admin.png";
	if(data.imageUrl){
		imageUrl=data.imageUrl;
	}
	var size = $("#leaderGroup li").size();
	if(size==0){
		size = 1;
	}else{
		size +=1;
	}
	var leaderInfoDiv =
              '<li data-id="'+data.id+'" data-index="'+size+'" id="leaderInfo'+data.id+'">'+
					'<div class="imgBox">'+
						'<img src="'+imageUrl+'"></img>'+
					'</div>'+
					'<div class="smallText">'+
						'<span class="name">'+data.name+gender+'</span>'+
						'<strong class="post">'+data.duty+'</strong></br>'+
						'<p class="info">'+data.introduction+'</p>'+
					'</div>'+
					'<div class="leaderGroup-buttons" name="leaderOperate" lid="'+data.id+'">';
	if($("#updateLeaderTeamP").val()){
		leaderInfoDiv+='<a href="javascript:;" class="base-button" name="update"><span>编辑</span></a>';
	}
	if($("#deleteLeaderTeamP").val()){
		leaderInfoDiv+='<a href="javascript:;" class="base-button" name="delete"><span>删除</span></a>';
	}
	leaderInfoDiv+='</div></li>';
	return $(leaderInfoDiv);
}
$(function(){
	if($("#imageUrl").val()=='' || $("#imageUrl").val()==null){
		$("#deleteHeaderImageBox").hide();
	}else{
		$("#deleteHeaderImageBox").show();
	}
	$("#updateDetailsForm").formValidate({
		promptPosition :"bottomLeft",
		submitHandler: function(form) {
			$("#orgId").val(getCurrentOrgId());
			if($("#mode").val()=='edit'){
				updateDetailsForm.action="${path }/baseinfo/villageProfile/updateLeaderTeam.action?<s:property value='@com.tianque.core.util.FormTokenHelper@TOKEN_NAME'/>=<pop:token ajax='true'/>";
			}
			$(form).ajaxSubmit({
				success:function(data){
					if(data.id){
						if($("#mode").val()=='edit'){
							var div = addLesderInfoDiv(data);
							$("#leaderInfo"+data.id).replaceWith(div);
							$.messageBox({message:"编辑成功"});	
							positionPanel();	
						}else{
							var div = addLesderInfoDiv(data);
							var size = $("#leaderGroup li").size();
							if(size == 0){
								$("#leaderGroup").html(div);
							}else{
								$("#leaderGroup li").last().after(div);
							}
							$.messageBox({message:"添加成功"});
							positionPanel();
						}
						$("#leaderTeamMaintanceDialog").dialog("close");
					}else{
						$.messageBox({message:data,level: "error"	});
					}
				}
			});
			return false;
			},
			rules: {
			"leaderTeams.name": {
				required: true,
				exculdeParticalChar:true
			},
			"leaderTeams.duty" :{
				required: true,
				exculdeParticalChar:true
			},
			"leaderTeams.gender" :{
				required: true
			},
			"leaderTeams.introduction" :{
				required: true,
				maxlength:1000
			},
			"leaderTeams.sort" :{
				nonNegativeInteger:true
			}
		},
		messages: {
			"leaderTeams.name": {
				required: "姓名不能为空！",
				exculdeParticalChar:"不能输入非法字符"
			},
			"leaderTeams.duty" :{
				required: "职务不能为空！",
				exculdeParticalChar:"不能输入非法字符"
			},
			"leaderTeams.gender" :{
				required: "性别不能为空！"
			},
			"leaderTeams.introduction":{
				required: "简介不能为空！",
				maxlength:$.format("简介最多输入{0}个字符")
			},
			"leaderTeams.sort":{
				nonNegativeInteger:"排序值必须为0或者正整数！"  
			}
		}
	});

	$("#maintainUrlForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success: function(data){
					if(data == false || data == "false" ){
			        	 $.messageBox({message:"上传失败，请上传正确格式的图像",level: "error"});
			        	 clearFile();
		            	 return;
				     }
					$("#scanHeaderImage").createDialog({
						width: 610,
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
</script>