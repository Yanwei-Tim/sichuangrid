<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<jsp:include page="/includes/baseInclude.jsp" />

<input type="hidden" id="imageUrl" value="${newVillageLeaderTeam.imageUrl }" />
<div id="dialog-form" title="修改个人信息" class="container container_24" style="overflow:hidden;background:#fff;">
	<div class="grid_5 heightAuto uploadImageBox">
		<div class="imgBox">
			<s:if test="null!=newVillageLeaderTeam.imageUrl && ''!=newVillageLeaderTeam.imageUrl">
			<img id="imgUrl" src="${newVillageLeaderTeam.imageUrl}"></img>
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

	<form name="updateDetailsForm" method="post" action="${path}/baseinfo/basicInfomationManage/maintainLeaderTeam.action" id="updateDetailsForm">
		<pop:token /> 
		<div class="grid_19 heightAuto rightPanelBox">
			<pop:token />
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" value="${newVillageLeaderTeam.id}" name="newVillageLeaderTeam.id" />
			<input type="hidden" value="${newVillageLeaderTeam.organization.orgInternalCode}" name="newVillageLeaderTeam.organization.orgInternalCode" id="orgCode"/>
			<input type="hidden" value="${newVillageLeaderTeam.organization.id}" name="newVillageLeaderTeam.organization.id" id="orgId"/>
			<input id="headerUrl" type="hidden" value="${newVillageLeaderTeam.imageUrl}" name="newVillageLeaderTeam.imageUrl" />
			<div class="clearfix">
				<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">姓名：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" name="newVillageLeaderTeam.name" maxlength="10"
					id="name" value="${newVillageLeaderTeam.name}"  <s:if test='"view".equals(mode)'>readonly</s:if>/>
			</div>
			<div class="grid_1"></div>

			<div class="grid_6 lable-right">
				&emsp;&emsp;<em class="form-req">*</em>
				<label class="form-label">性别：</label>
			</div>
			<div class="grid_6">
				<select name="newVillageLeaderTeam.gender" id="gender" class="form-select" <s:if test='"view".equals(mode)'>readonly</s:if> >
					<option value="">请选择</option>
					<option value="0" <s:if test="0== newVillageLeaderTeam.gender">selected</s:if> >男</option>
					<option value="1" <s:if test="1== newVillageLeaderTeam.gender">selected</s:if>>女</option>
					<option value="2" <s:if test="2== newVillageLeaderTeam.gender">selected</s:if>>未知</option>
				</select>
			</div>
			<div class="grid_1"></div>
			</div>
			
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">年龄：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" name="newVillageLeaderTeam.age"
					id="age" value="${newVillageLeaderTeam.age}" maxlength="3" <s:if test='"view".equals(mode)'>readonly</s:if>/>
			</div>
			<div class="grid_1"></div>
			
			<div class="grid_6 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">文化程度：</label>
			</div>
			<div class="grid_6">
				<select name="newVillageLeaderTeam.educationDegree.id" id="" class="form-txt">
		    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"  defaultValue="${newVillageLeaderTeam.educationDegree.id }"/>
	</select>
			</div>
			<div class="grid_1"></div>
			
			<div class="grid_4 lable-right">
				<!-- <em class="form-req">*</em> -->
				<label class="form-label">职务：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" maxlength="10"  <s:if test='"view".equals(mode)'>readonly</s:if>
					name="newVillageLeaderTeam.duty" id="duty" value="${newVillageLeaderTeam.duty}" />
			</div>
			<div class="grid_1"></div>	
			
			<div class="grid_6 lable-right">
				<label class="form-label">联系方式：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" maxlength="20"  <s:if test='"view".equals(mode)'>readonly</s:if>
					name="newVillageLeaderTeam.contactWay" id="contactWay" value="${newVillageLeaderTeam.contactWay}" />
			</div>
			<div class="grid_1"></div>
				
			<div class="clearline"></div>
			<div class="grid_4 lable-right">
				<!-- <em class="form-req">*</em> -->
				<label class="form-label">简介：</label>
			</div>
			<div class="grid_20 heightAuto">
				<textarea id="introduction" name="newVillageLeaderTeam.introduction" maxlength="1000"
					defaultvalue="" class="form-txt" <s:if test='"view".equals(mode)'>readonly</s:if> style="height:220px;">${newVillageLeaderTeam.introduction}</textarea>
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
	var duty = data.duty;
	var introduction=data.introduction;
	if(duty==null || duty==undefined){
		duty="";
	}
	if(introduction==null || introduction==undefined){
		introduction="";
	}
	var leaderInfoDiv =
              '<li data-id="'+data.id+'" data-index="'+size+'" id="leaderInfo'+data.id+'">'+
					'<div class="imgBox">'+
						'<img src="'+imageUrl+'"></img>'+
					'</div>'+
					'<div class="smallText">'+
						'<span class="name"><span class="post">姓名：</span>'+data.name+gender+'</span>'+
						'<span class="name"><span class="post">年龄：</span>'+data.age+'</span>'+
						'<span class="post">职务：</span>'+duty+'</br>'+
						'<p class="info"><span class="post">介绍:</span>'+introduction+'</p>'+
					'</div>'+
					'<div class="leaderGroup-buttons" name="leaderOperate" lid="'+data.id+'">';
		leaderInfoDiv+='<a href="javascript:updateLeaderTeam('+data.id+');" class="base-button" name="update"><span>编辑</span></a>';
		leaderInfoDiv+='<a href="javascript:deletLeaderTeam('+data.id+');" class="base-button" name="delete"><span>删除</span></a>';
	leaderInfoDiv+='</div></li>';
	return $(leaderInfoDiv);
}

$(function(){
	if($("#imageUrl").val()=='' || $("#imageUrl").val()==null){
		$("#deleteHeaderImageBox").hide();
	}else{
		$("#deleteHeaderImageBox").show();
	}
	if("view"=="${mode}"){
		$("#schooling").attr("disabled","true");
	}
	
	$("#updateDetailsForm").formValidate({
		promptPosition :"bottomLeft",
		submitHandler: function(form) {
			$("#orgId").val(getCurrentOrgId());
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
			"newVillageLeaderTeam.name": {
				required: true,
				exculdeParticalChar:true
			},
			"newVillageLeaderTeam.age": {
				required: true,
				digits:true,
				min: 0,
				max: 999
			},
			"newVillageLeaderTeam.duty" :{
				exculdeParticalChar:true
			},
			"newVillageLeaderTeam.gender" :{
				required: true
			},
			"newVillageLeaderTeam.educationDegree.id" :{
				required: true
			},
			"newVillageLeaderTeam.contactWay" :{
				telephone:true
			},
			"newVillageLeaderTeam.introduction" :{
				maxlength:1000
			},
			"newVillageLeaderTeam.sort" :{
				nonNegativeInteger:true
			}
		},
		messages: {
			"newVillageLeaderTeam.name": {
				required: "姓名不能为空！",
				exculdeParticalChar:"不能输入非法字符"
			},
			"newVillageLeaderTeam.age": {
				required: "年龄不能为空！",
				digits:"请输入合法的年龄:整数"
			},
			"newVillageLeaderTeam.duty" :{
				exculdeParticalChar:"不能输入非法字符"
			},
			"newVillageLeaderTeam.gender" :{
				required: "性别不能为空！"
			},
			"newVillageLeaderTeam.introduction":{
				maxlength:$.format("简介最多输入{0}个字符")
			},
			"newVillageLeaderTeam.educationDegree.id":{
				required:"文化程度不能为空"  
			},
			"newVillageLeaderTeam.contactWay":{
				telephone:"电话不合法，只能输数字和横杠(-)"  
			},
			"newVillageLeaderTeam.sort":{
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