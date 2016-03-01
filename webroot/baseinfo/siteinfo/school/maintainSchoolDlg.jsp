<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="schoolInfo" class="container container_24">
	<s:if test='"edit".equals(mode)'>
		<form id="maintainForm" method="post"
			action="${path}/baseinfo/schoolManage/updateSchoolAction.action">
	</s:if>
	<s:if test='"add".equals(mode) || "copy".equals(mode) '>
		<form id="maintainForm" method="post"
			action="${path}/baseinfo/schoolManage/addSchoolAction.action">
	</s:if>
	<div id="perUuid"></div>
	<input id="keyType" type="hidden" name="keyType" value="${keyType}">
	<s:if test='"add".equals(mode)'>
		<pop:token />
	</s:if>
	<input id="placeTypeName" type="hidden" name="placeTypeName"
		value="${placeTypeName}"> <input id="modeType" type="hidden"
		name="modeType" value="${modeType}"> <input id="orgId"
		type="hidden" name="orgId" value="${orgId}"> <input
		type="hidden" id="mode" name="mode" value="${mode}" /> <input
		type="hidden" name="school.organization.orgInternalCode"
		value="<s:property value='school.organization.orgInternalCode'/>" />
	<input type="hidden" name="school.orgInternalCode"
		value="<s:property value='school.orgInternalCode'/>" /> <input
		type="hidden" name="school.organization.id" id="schoolOrganizationId"
		value="<s:property value='school.organization.id'/>" /> <input
		type="hidden" id="schoolId" name="school.id" value="${school.id}" />
	<input type="hidden" name="school.createUser"
		value="${school.createUser}" /> <input name="isSubmit" id="isSubmit"
		type="hidden" value=""> <input type="hidden"
		name="school.createDate"
		value='<s:date name="school.createDate" format="yyyy-MM-dd HH:mm:ss"/>' />
	<input id="_imgUrl" type="hidden" name="school.imgUrl"
		value="${school.imgUrl}" />

	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em> <label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="orgName" id="schoolOrgName" value="" readonly
			class="form-txt" style="width: 99%" />
	</div>
	<div class="grid_1"></div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em> <label class="form-lbl">学校名称：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="name" maxlength="30" name="school.chineseName"
			value="${school.chineseName}"
			class="form-txt {required:true,exculdeParticalChar:true,exsistedCompanyName:true,messages:{required:'请输入学校名称',exculdeParticalChar:'不能输入非法字符',exsistedCompanyName:'学校名称在该网格下已经存在，请重新输入'}}"
			style="width: 99%" />
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em> <label class="form-lbl">学校地址：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="school.address" maxlength="50"
			id="schoolAddress" value="${school.address}" class="form-txt"
			style="width: 99%" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em> <label class="form-lbl">学校性质：</label>
	</div>
	<div class="grid_7">
		<select name="school.kind.id" id="school-kind-id" class="form-select">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY"
				defaultValue="${school.kind.id}" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<em class="form-req">*&nbsp;</em> <label class="form-lbl">校长：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="school.president" id="school-president"
			maxlength="20" value="${school.president}" class="form-txt" />
	</div>

	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em> <label class="form-lbl">学校类型：</label>
	</div>
	<div class="grid_7">
		<select name="school.type.id" id="school-type-id" class="form-select"
			title="托管机构:在正常8小时以外从事学生托管的机构">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE"
				defaultValue="${school.type.id}" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">关注程度：</label>
	</div>
	<div class="grid_7">
		<select name="school.attentionExtent.id" id="school-attentionExtent"
			class="form-txt"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT"
				defaultValue="${school.attentionExtent.id}" />
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">英文名称：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="schoolEnglishName" maxlength="30"
			name="school.englishName" value="${school.englishName}"
			class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">学校网址：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="school.webSite" maxlength="30"
			style="ime-mode: disabled" id="schoolWebSite"
			value="${school.webSite}" class="form-txt  dialogtip"
			title="请输入以http://开头的学校网址!例:http://www.xxx.com">
	</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		<label class="form-lbl">法制副校长：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="school.personLiable" id="school-personLiable"
			maxlength="20" value="${school.personLiable}" class="form-txt">
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">在校人数：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="school.atSchoolHeadcount" maxlength="5"
			id="school-atSchoolHeadcount" value="${school.atSchoolHeadcount}"
			class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		<label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="school.personLiableTelephone" maxlength="15"
			id="school-personLiableTelephone"
			value="${school.personLiableTelephone}" class="form-txt  dialogtip"
			title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="school.personLiableMobileNumber"
			maxlength="11" id="school-personLiableMobileNumber"
			value="${school.personLiableMobileNumber}"
			class="form-txt  dialogtip" title="请输入11位以1开头的手机号码!例如：13988888888" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">传真：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="schoolFax" maxlength="15" name="school.fax"
			value='${school.fax}' class="form-txt  dialogtip"
			title="请输入由数字和-组成的传真号码!例如：0577-88888888" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">电子邮箱：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="school.email" id="school-email"
			maxlength="30" style="ime-mode: disabled" value="${school.email}"
			class="form-txt  dialogtip"
			title="电子邮箱是通过网络电子邮局为网络客户提供的网络交流电子信息空间。电子邮箱具有存储和收发电子信息的功能，是因特网中最重要的信息交流工具 例如：youname@youcompany.com" />
	</div>
	<div class="grid_1"></div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		<label class="form-lbl">周边情况：</label>
	</div>
	<div class="grid_19">
		<textarea name="school.remark" id="school-remark" class="form-txt"
			rows="5" style="width: 99%">${school.remark}</textarea>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<s:if test='!"view".equals(mode)'>
		</form>
	</s:if>
</div>

<script type="text/javascript">
var schoolOrgTree="";
function isGridForTreeSelect(){
	if(schoolOrgTree != ""){
		return $.getSelectedNode(schoolOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
function changeOrgId(){
$("#orgId").val($("#schoolOrganizationId").val());
}

if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}

$(document).ready(function(){
	<s:if test='#parameters.dialog != null'>
		schoolOrgTree = $("#schoolOrgName").treeSelect({
		inputName:"school.organization.id",
		url:"/sysadmin/orgManage/loadTownForExtTree.action",
		onSelect:changeOrgId
	});
		//$.setTreeValue($("#orgId").val(),tree);
		var bol = false;
		$.ajax({
			async:false,
			url:"${path}/issue/issueManage/checkOccurOrgId.action",
			data:{
				"issueNew.occurOrg.id":${USER_ORG_ID}
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		var url = "";
		if(bol){
			url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIdsWhenRootIsTown.action?organization.id="+$("#orgId").val()
		}else{
			url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#orgId").val()
		}
		$.ajax({
			url:url,
			success:function(data){
				$.searchChild(schoolOrgTree,data);
			}
		});
	</s:if>

	//验证名称唯一性
	jQuery.validator.addMethod("exsistedCompanyName", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:'${path}/baseinfo/schoolManage/hasDuplicateSchoolName.action',
		   	data:{
				"school.chineseName": $("#name").val(),
				"school.organization.id" : $("#orgId").val(),
				"mode": $("#mode").val(),
				"school.id":$("#schoolId").val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});

	$("#tabs").tabFunction({ cache: true });
	$(".dialogtip").inputtip();
// 	if($("#schoolId").val()==null || $("#schoolId").val()==""){
// 		$("#floorpersons").hide();
// 	}
// 	if(!ispersonInCharge()){
// 		$("#floorpersons").hide();
// 	}
		$.ajax({
			async: false,
			url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#orgId").val()
			},
			success:function(responseData){
				$("#schoolOrgName").val(responseData);
			}
		});
		
//英文名称非法字符验证
jQuery.validator.addMethod("isSchoolEnglishName", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/[\u4e00-\u9fa5]+/;
	if (patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});

//手机11位
jQuery.validator.addMethod("isMobileNumber", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^1[0-9]\d{9}$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});

//在校人数只能是数字且不能为负数
jQuery.validator.addMethod("isAtSchoolHeadcount", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^[0-9]*[1-9][0-9]*$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});

jQuery.validator.addMethod("isGridOrganization", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	if(isGridForTreeSelect()){
		return true;
	}else{
		return false;
	}
});

if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
	$("#img").attr("src","${path }/"+$("#_imgUrl").val());
};

<s:if test='!"view".equals(mode)'>
$("#maintainForm").formValidate({
promptPosition: "bottomLeft",
submitHandler: function(form) {
	 $("#_imgUrl").val($("#imgUrl").val());
	 $(form).ajaxSubmit({
		 async : false,
	     success: function(data){
             if(!data.id){
            	 $.messageBox({
					message:data,
					level: "error"
				 });
             	return;
             }
             $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
	   },
	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      alert("提交错误");
	   }
	  });
},
rules: {
	"orgName":{
		isGridOrganization:true
	},
	"school.chineseName" :{
		required: true,
	    minlength: 2,
		maxlength: 30,
		exculdeParticalChar :true
	},
	"school.kind.id":{
		required: true
	},
	"school.address":{
		required: true,
		maxlength: 50,
		exculdeParticalChar :true
	},
	"school.type.id":{
		required: true
	},///英文名称
	"school.englishName":{
		 maxlength: 30,
		 notChineseOnly:true,
		 isDigitAndStr:true
	},
	"school.personLiableTelephone":{
		telephone : true
	},
	"school.personLiableMobileNumber":{
		digits:true,
		isMobileNumber:true,
		minlength:11
	},
	"school.email":{
          email:true,
          maxlength: 30
	},
	"school.president":{
		required: true,
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"school.contactName":{
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"school.fax":{
		maxlength: 15,
		telephone:true
	},
	"school.remark":{
		maxlength: 200
	},
	"school.webSite":{
		maxlength: 30,
		url:true
	},
	"school.personLiable":{
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"school.atSchoolHeadcount":{
		isAtSchoolHeadcount : true
	}
},
messages: {
	"orgName":{
		isGridOrganization:"网格只能属于片组片格"
	},
	"school.chineseName" :{
		required: "请输入学校名称!",
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar : "学校名称不能包含特殊字符"
	},
	"school.kind.id":{
		required:"请输入学校性质!"
	},
	"school.address":{
		required: "请输入学校地址!",
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"学校地址不能包含特殊字符"
	},
	"school.type.id":{
		required:"请输入学校类型!"
	},
	"school.englishName":{
	    maxlength  : $.format("最多只能输入{0}个字符"),
	    notChineseOnly:"英文名不能输入中文!",
	    isDigitAndStr :"英文名称不能输入非法字符!"
	},
	"school.personLiableTelephone":{
		telephone:"联系电话不合法，只能输数字和横杠(-)"
	},
	"school.personLiableMobileNumber":{
	    digits:"手机只能输入数字字符",
	    isMobileNumber :"手机号码只能输入以1开头的11为数字!",
		minlength:$.format("手机只能输入11位数字字符")
	},
	"school.email":{
        email:"电子邮箱格式输入有误!",
        maxlength  : $.format("最多只能输入{0}个字符")
	},
	"school.president":{
		required: "请输入校长!",
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"校长不能包含特殊字符"
	},
	"school.contactName":{
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"联系人不能包含特殊字符"
	},
	"school.fax":{
	    maxlength  : $.format("最多只能输入{0}个字符"),
	    telephone : "传真号码不合法!"
	},
	"school.remark":{
	    maxlength  : $.format("最多只能输入{0}个字符")
	},
	"school.webSite":{
	    maxlength  : $.format("最多只能输入{0}个字符"),
	    url : "网址输入有误!"
	},
	"school.personLiable":{
		minlength  : $.format("至少需要{0}个字符"),
		maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"法制副校长输入不能包含特殊字符"
	},
	"school.atSchoolHeadcount":{
		isAtSchoolHeadcount :"在校人数只能输入整数数字!"
	}
}

});
</s:if>
<s:if test='"add_path".equals(modeType)'>
$("#schoolOrgName").click(function(event){
	$("#noticeDialog").createDialog({
		width: 300,
		height: 350,
		title:'请选择一个部门',
		url:'${path}/common/organizationSelector.jsp',
		buttons: {
			"确定" : function(){
				closeDialog();
			},
			"取消" : function(){
				$("#noticeDialog").dialog("close");
			}
		}
	});
});
</s:if>
});
<s:if test='"add_path".equals(modeType)'>
function setZone(selectOrgId,selectOrgName){
	$("#orgId").val(selectOrgId);
	$("#schoolOrgName").val(selectOrgName);
}
</s:if>

function ispersonInCharge(){
	var flag=false;
	$.ajax({
		async:false,
		url: "${path }/baseinfo/personInCharegeManage/getPersonInCharegeByIdAndPlace.action",
		data:{
			"placeId":$("#schoolId").val(),
			"placeType":$("#keyType").val()
		},
		success:function(responseData){
			if(responseData.length>0){
				flag=true;
			}
		}
	});
	return flag;
}

function emptyObject(){
	$("#schoolId").val("");
	$("#name").val("");
	$("#schoolEnglishName").val("");
	$("#school-kind-id").val("");
	$("#schoolAddress").val("");
	$("#school-type-id").val("");
	$("#schoolWebSite").val("");
	$("#school-atSchoolHeadcount").val("");
	$("#school-president").val("");
	$("#schoolFax").val("");
	$("#school-email").val("");
	$("#school-personLiable").val("");
	$("#school-personLiableTelephone").val("");
	$("#school-personLiableMobileNumber").val("");
	$("#school-remark").val("");
}
</script>
