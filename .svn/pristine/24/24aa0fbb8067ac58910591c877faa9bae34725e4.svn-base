<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>

<div id="dialog-form" title="网格成员维护" class="container container_24">
	<div class="grid_5 heightAuto uploadImageBox">
		<div class="imgBox">
			<s:if test="null!=gridTeam.imageUrl && ''!=gridTeam.imageUrl">
			<img id="imgUrl" src="${gridTeam.imageUrl}"></img>
			</s:if>
			<s:else>
			<img id="imgUrl" src="${resource_path}/resource/system/images/avatar.jpg"></img>
			</s:else>
			<s:if test="!'view'.equals(mode)">
				<div class="shadow" id="deleteHeaderImageBox" style="display: none">
					<div class="bgc"></div>
					<div  id="deleteHeaderImage" class="deleteHeaderImage">删除头像</div>
				</div>
			</s:if>
		</div>
		<s:if test="!'view'.equals(mode)">
			<form id="maintainUrlForm" method="post" action="${path}/imageUpload/uploadImg.action" enctype="multipart/form-data" name="maintainUrlForm">
				<div class="uploadfileBtn" id="uploadfileBtnId" ><span>上传照片</span><input id="uploadImg" type="file" name="header" /></div>
			</form>
		</s:if>
	</div>
	
	 <form id="maintainForm" method="post" action="${path }/baseinfo/gridTeamManage/maintainGridTeamInfo.action" >
	 	<pop:token /> 
		<div class="grid_19 heightAuto rightPanelBox">
			<pop:token />
			<input type="hidden" name="mode" id="mode" value="${mode}" />
         	<input name="gridTeam.id"  type="hidden" value="${gridTeam.id}" />
         	<%-- <input name="gridTeam.activatedTime"  type="hidden"  value="${gridTeam.activatedTime}" /> --%>
         	<input name="gridTeam.isActivated"  type="hidden" value="${gridTeam.isActivated}" />
         	<input name="gridTeam.user.id"  type="hidden" value="${gridTeam.user.id}" />
         	<input type="hidden" id="OrgGridId" name="gridTeam.organization.id" value="${gridTeam.organization.id}" />
         	<input id="headerUrl" type="hidden" value="${gridTeam.imageUrl}" name="gridTeam.imageUrl" />

		<div class="grid_4 lable-right">
			<label class="form-lbl">所属区域： </label>
		</div>
		<div class="grid_20 heightAuto">
		    <input  name="gridTeam.organization.fullOrgName"  class="form-txt" style="float:left;" <s:if test="'add'.equals(mode)">value="${organization.orgName }"</s:if> <s:if test="'edit'.equals(mode)||'view'.equals(mode)">value="${gridTeam.organization.fullOrgName }"</s:if> disabled="disabled"/>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">姓名： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="gridTeam.memeberName" maxlength="10" class="form-txt {required:true,messages:{required:'姓名必须填写'}}" style="float:left;" value="${gridTeam.memeberName }" <s:if test="'view'.equals(mode)">disabled="disabled"</s:if>/>
  		</div>
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">身份证号码： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="gridTeam.idCardNo" id="idCardNo" maxlength="20" class="form-txt {required:true,idCard:true,messages:{required:'身份证号码必须填写',idCard:'请输入一个合法的身份证号码'}}" style="float:left;" value="${gridTeam.idCardNo}" <s:if test="'view'.equals(mode)">disabled="disabled"</s:if>/>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">性别： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="" id="gender" class="form-txt {required:true,messages:{required:'性别必须选择'}}" style="width: 192px;" disabled>
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${gridTeam.gender.id }"/>
		    </select>
		    <input type="hidden" name="gridTeam.gender.id" id="gridTeamGender" value="${gridTeam.gender.id}"/>
  		</div>
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">出生年月： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="gridTeam.birthDate" id="birthDate"  class="form-txt {required:true,messages:{required:'出生年月必须选择'}}" style="float:left;" value="<s:date name='gridTeam.birthDate' format='yyyy-MM-dd'/>" readonly="readonly" <s:if test="'view'.equals(mode)">disabled</s:if> />
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="gridTeam.phoneNumber" maxlength="15"  class="form-txt {telephone:true,messages:{telephone:$.format('联系电话不合法，只能输数字和横杠(-)')}}" style="float:left;" value="${gridTeam.phoneNumber }" <s:if test="'view'.equals(mode)">disabled="disabled"</s:if>/>
  		</div>
  		
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">所属网格： </label>
		</div>
		<div class="grid_8">
		    <input type="text" id="gridName" value="${gridTeam.organization.orgName }" <c:if test="${gridTeam.user.id!=null||mode=='view'}">readonly="readonly"</c:if>  class="form-txt" />
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">系统未定义网格： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input type="text" readonly="readonly" id="userDefinedGridName" name="gridTeam.userDefinedGridName" value="${gridTeam.userDefinedGridName}" <c:if test="${gridTeam.user.id!=null}">disabled="disabled"</c:if> class="form-txt" />
  		</div>
  		<label class="form-lbl form-req">当网格在系统组织机构中不存在时填写 </label>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">专兼职情况： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="gridTeam.positionType.id" class="form-txt {required:true,messages:{required:'专兼职情况必须选择'}}" style="width: 192px;" <s:if test="'view'.equals(mode)">disabled</s:if>>
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GRID_POSITIONTYPE" defaultValue="${gridTeam.positionType.id }"/>
		    </select>
  		</div>
  		
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">政治面貌： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="gridTeam.politicalBackground.id" class="form-select {required:true,messages:{required:'政治面貌必须选择'}}" style="width: 192px;" <s:if test="'view'.equals(mode)">disabled</s:if>>
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${gridTeam.politicalBackground.id }"/>
		    </select>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">文化程度： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="gridTeam.education.id" class="form-txt {required:true,messages:{required:'文化程度必须选择'}}" style="width: 192px;" <s:if test="'view'.equals(mode)">disabled</s:if>>
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${gridTeam.education.id}"/>
		    </select>
  		</div>

  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">登记时间： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="gridTeam.registeredDate"  id="registeredDate" class="form-txt {required:true,messages:{required:'登记时间必须选择'}}" style="float:left;" value="<s:date name='gridTeam.registeredDate' format='yyyy-MM-dd'/>" readonly="readonly" <s:if test="'view'.equals(mode)">disabled</s:if>/>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		
  		<div class="grid_4 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">激活状态： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="gridTeam.isActivated"  id="isActivated" class="form-txt" style="float:left;" value='<s:if test="gridTeam.isActivated">已激活</s:if><s:else>未激活</s:else>' disabled="disabled"/>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		
  		<div class="grid_4 lable-right">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_20 heightAuto">
   			<textarea rows="5" cols="77" name="gridTeam.remark" id="comments"  maxlength="100" class="form-txt" <s:if test="'view'.equals(mode)">disabled</s:if>>${gridTeam.remark }</textarea>
   		</div>
   		</div>
	</form>
</div>	
<script type="text/javascript">
$(document).ready(function(){
	// 初始发生化网格 ,如果为系统中网格员则不用加载机构菜单树
	if(!"${gridTeam.user.id}" && "${mode}"!="view"){
		initOrgSelector();
	}
	$("#userDefinedGridName").on("focus",function(){
		//当所属网格为社区层级时,才能手动填写 自定义网格表单项
		$.ajax({
			async: false,
			url: "${path }/sysadmin/orgManage/isVillageOrg.action",
			data:{
				"organization.id" : $("#OrgGridId").val()
			},
			success:function(responseData){
				if(responseData){
					$("#userDefinedGridName").attr("readonly",false);
				}else{
					$("#userDefinedGridName").val("");
					$("#userDefinedGridName").attr("readonly",true);
				}
			}
		});
	})
	
	$("#idCardNo").blur(function(){
		fillGenderByIdCardNo($("#idCardNo").val(),"gender","gridTeamGender",true);
		if($("#idCardNo").val()!=null && $("#idCardNo").val()!=""){
			$("#birthDate").val(getBirthDayTextFromIdCard($("#idCardNo").val()));
		}
	});
	$('#registeredDate').datePicker({
				yearRange:'1930:2030',
				dateFormat:'yy-mm-dd',
		    	maxDate:'+0d'
	});
	
	$("#maintainForm").formValidate({
				promptPosition: "bottomLeft",
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
				        	 
				        	 <c:if test="${mode=='add'}">
				        	 $.messageBox({message:"成功保存信息!"});
				        	 </c:if>
				        	 <c:if test="${mode=='edit'}">
				        	 $.messageBox({message:"成功保存修改信息!"});
				        	 </c:if>
						     $("#gridMemeberDialog").dialog("close");
						     $("#gridMemeberList").trigger("reloadGrid");
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
	$("#gridName").on("click",function(){
		if($("#gridName").val()=="请选择..."){
			$("#gridName").val("");
		}
	});
	//头像相关代码
	if($("#headerUrl").val()=='' || $("#headerUrl").val()==null){
		$("#deleteHeaderImageBox").hide();
	}else{
		$("#deleteHeaderImageBox").show();
	}
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

function fullOrgId(){
	if($("#OrgGridId").val()==""){
		var orgId=getCurrentOrgId();
		$("#OrgGridId").val(""+orgId);
	}
}

function getBirthDayTextFromIdCard(idCard){
	if(idCard!=null&&idCard.length==18){
		idCard=idCard.substring(6,14);
		if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
			return "";
		}else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
			return "";
		}else{
			return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
		}
	}else if(idCard!=null&&idCard.length==15){
		idCard=idCard.substring(6,12);
		if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
			return "";
		}else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
			return "";
		}else{
			return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
		}
	}
	return "";
}

function fillGenderByIdCardNo(idCardNo,dictFieldName,fieldName,isDict){
    var sex;
    if(idCardNo==null||idCardNo=="" || typeof(idCardNo)=="undefined"){
        return;
    }
    if(idCardNo.length!=15 && idCardNo.length!=18){
        return;
    }

    if (15 == idCardNo.length) { //15位身份证号码
        if (parseInt(idCardNo.charAt(14) / 2) * 2 != idCardNo.charAt(14))
            sex = '男';
        else
            sex = '女';
     }
    if (18 == idCardNo.length) { //18位身份证号码
        if (parseInt(idCardNo.charAt(16) / 2) * 2 != idCardNo.charAt(16))
           sex = '男';
        else
          sex = '女';
    }
    showGender(sex,dictFieldName,fieldName,isDict);
}

function showGender(gender,dictFieldName,fieldName,isDict){
    if(isDict){
        $("#"+dictFieldName + " option").each(function(value){
            if($(this).text()==gender)  {
                $(this).attr("selected",true);
                $("#"+fieldName).val($(this).val())
            }
        });
    }
}

function initOrgSelector(){
	var tree=$("#gridName").treeSelect({
		width:192,
		nodeUrl:"/sysadmin/orgManage/ajaxOrgsForExtTree.action",
		allOrg:false,
		emptyText:'请选择...',
		rootId:getCurrentOrgId(),
		isRootSelected:false,
		inputName:"gridTeam.organization.id",
		changeFun:function(){ return setOrgLevelCheckboxState($.getSelectedNode(areaTree).attributes.orgLevelInternalId);},
		loadCom:function(){
			$.setTreeValue(getGrid(),tree); 
		}
	});
}

	//按层级发送组织树
	/* var tree=$("#gridName").treeSelect({
		width:246,
		nodeUrl:"/sysadmin/orgManage/ajaxOrgsForExtTree.action",
		allOrg:false,
		isRootSelected:false,
		rootId:getCurrentOrgId(),
		showFunctionalOrg:true,
		directlySupervisor:true,
		emptyText:'请选择...',
		changeFun:function(){ return setOrgLevelCheckboxState($.getSelectedNode(areaTree).attributes.orgLevelInternalId);},
		loadCom:function(){
			$.setTreeValue(getGrid(),tree); 
		}
	}); */
	
	//$("#gridName").attr("readonly",false);
	
	function getGrid(){
		if("${gridTeam.userDefinedGridName}"==""){
			return "${gridTeam.organization.id}";
		}
		return "${gridTeam.userDefinedGridName}";
	}
	
	
	function clearFile(){
	   $("#uploadfileBtnId").html("");
	   $("#uploadfileBtnId").html('<span>上传照片</span><input id="uploadImg" type="file" name="header" />');
	   $("#uploadImg").change(function(){
			$("#maintainUrlForm").submit();
		});
	}
</script>