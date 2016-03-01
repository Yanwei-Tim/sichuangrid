<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="locationInfo" class="container container_24">
	<form id="maintainForm" method="post"
		action="${path}/baseinfo/hospitalManage/saveHospital.action">
		<s:if test='"add".equals(mode)'>
			<pop:token />
		</s:if>
		<input id="modeType" type="hidden" name="modeType" value="${modeType}">
		<input id="organizationId"	type="hidden" name="location.organization.id" value="${location.organization.id }" />
		 <input type="hidden" id="mode" name="mode" value="${mode}" /> 
			<input type="hidden" id="locationId" name="location.id" value="${location.id}" /> 
			<input type="hidden" name="location.createUser" value="${location.createUser}" /> 
			<input name="isSubmit" id="isSubmit" type="hidden" value=""> 
			<input type="hidden" name="location.createDate" value='<s:date name="location.createDate" format="yyyy-MM-dd HH:mm:ss"/>' />
			<input id="_imgUrl" type="hidden" name="location.imgUrl"value="${location.imgUrl}" />

		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="orgName" id="locationOrgName" value=""
				readonly class="form-txt" style="width: 99%" />
		</div>
		<div class="grid_1"></div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">医院名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="name" maxlength="30"
				name="location.hospitalName" value="${location.hospitalName}"
				class="form-txt {required:true,exculdeParticalChar:true,exsistedCompanyName:true,messages:{required:'请输入医院名称',exculdeParticalChar:'不能输入非法字符',exsistedCompanyName:'医院名称在该网格下已经存在，请重新输入'}}"
				style="width: 99%" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">医院地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.address" maxlength="50"
				id="locationAddress" value="${location.address}" class="form-txt"
				style="width: 99%" />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">所属单位：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.affiliatedUnit"
				id="location-affiliatedUnit" maxlength="20"
				value="${location.affiliatedUnit}" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">医院性质：</label>
		</div>
		<div class="grid_7">
			<select name="location.kind.id" id="location-kind-id"
				class="form-select">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND"
					defaultValue="${location.kind.id}" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">医院类型：</label>
		</div>
		<div class="grid_7">
			<select name="location.type.id" id="location-type-id"
				class="form-select">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE"
					defaultValue="${location.type.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">关注程度：</label>
		</div>
		<div class="grid_7">
			<select name="location.attentionExtent.id"
				id="location-attentionExtent" class="form-txt"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT"
					defaultValue="${location.attentionExtent.id}" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="fsorm-lbl">医院院长：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.director" id="location-director"
				maxlength="20" value="${location.director}" class="form-txt">
		</div>

		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.telephone" maxlength="15"
				id="location-telephone" value="${location.telephone}"
				class="form-txt  dialogtip" title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.mobileNumber" maxlength="11"
				id="location-mobileNumber" value="${location.mobileNumber}"
				class="form-txt  dialogtip" title="请输入11位以1开头的手机号码!例如：13988888888" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">传真：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="locationFax" maxlength="15"
				name="location.fax" value='${location.fax}'
				class="form-txt  dialogtip" title="请输入由数字和-组成的传真号码!例如：0577-88888888" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">电子邮箱：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.email" id="location-email"
				maxlength="30" style="ime-mode: disabled" value="${location.email}"
				class="form-txt  dialogtip"
				title="电子邮箱是通过网络电子邮局为网络客户提供的网络交流电子信息空间。电子邮箱具有存储和收发电子信息的功能，是因特网中最重要的信息交流工具 例如：youname@youcompany.com" />
		</div>
		<div class="grid_1"></div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_19">
			<textarea name="location.remark" id="location-remark"
				class="form-txt" rows="5" style="width: 99%">${location.remark}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<s:if test='!"view".equals(mode)'>
	</form>
	</s:if>
</div>

<script type="text/javascript">
<s:if test='"add".equals(mode)'>
$("#organizationId").val($("#currentOrgId").val());
$.ajax({
	async: false,
	url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
	data:{
		"organization.id" : $("#currentOrgId").val()
	},
	success:function(responseData){
		$("#orgName").val(responseData);
	}
});
</s:if>
var locationOrgTree="";
function isGridForTreeSelect(){
	if(locationOrgTree != ""){
		return $.getSelectedNode(locationOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
function changeOrgId(){
$("#orgId").val($("#locationOrganizationId").val());
}

if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}

$(document).ready(function(){
	<s:if test='#parameters.dialog != null'>
		locationOrgTree = $("#locationOrgName").treeSelect({
		inputName:"location.organization.id",
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
				$.searchChild(locationOrgTree,data);
			}
		});
	</s:if>

	//验证名称唯一性
	jQuery.validator.addMethod("exsistedCompanyName", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:'${path}/baseinfo/hospitalManage/hasDuplicateHospitalLocation.action',
		   	data:{
				"location.hospitalName": $("#name").val(),
				"organizationId" : $("#organizationId").val(),
				"mode": $("#mode").val(),
				"location.id":$("#locationId").val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});

	$("#tabs").tabFunction({ cache: true });
	$(".dialogtip").inputtip();
// 	if($("#locationId").val()==null || $("#locationId").val()==""){
// 		$("#floorpersons").hide();
// 	}
// 	if(!ispersonInCharge()){
// 		$("#floorpersons").hide();
// 	}
		$.ajax({
			async: false,
			url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#locationOrgName").val(responseData);
			}
		});
		
//英文名称非法字符验证
jQuery.validator.addMethod("islocationEnglishName", function(value, element){
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
jQuery.validator.addMethod("isAtlocationHeadcount", function(value, element){
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
	"location.hospitalName" :{
		required: true,
	    minlength: 2,
		maxlength: 30,
		exculdeParticalChar :true
	},
	"location.kind.id":{
		required: false
	},
	"location.address":{
		required: true,
		maxlength: 50,
		exculdeParticalChar :true
	},
	"location.type.id":{
		required: false
	},///英文名称
	"location.telephone":{
		telephone : true
	},
	"location.mobileNumber":{
		digits:true,
		isMobileNumber:true,
		minlength:11
	},
	"location.email":{
          email:true,
          maxlength: 30
	},
	"location.president":{
		required: true,
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"location.contactName":{
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"location.fax":{
		maxlength: 15,
		telephone:true
	},
	"location.remark":{
		maxlength: 200
	},
	"location.personLiable":{
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	}
},
messages: {
	"orgName":{
		isGridOrganization:"网格只能属于片组片格"
	},
	"location.hospitalName" :{
		required: "请输入医院名称!",
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar : "医院名称不能包含特殊字符"
	},
	"location.kind.id":{
		required:"请输入医院性质!"
	},
	"location.address":{
		required: "请输入地址!",
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"医院地址不能包含特殊字符"
	},
	"location.type.id":{
		required:"请输入医院类型!"
	},
	"location.telephone":{
		telephone:"联系电话不合法，只能输数字和横杠(-)"
	},
	"location.mobileNumber":{
	    digits:"手机只能输入数字字符",
	    isMobileNumber :"手机号码只能输入以1开头的11为数字!",
		minlength:$.format("手机只能输入11位数字字符")
	},
	"location.email":{
        email:"电子邮箱格式输入有误!",
        maxlength  : $.format("最多只能输入{0}个字符")
	},
	"location.president":{
		required: "请输入校长!",
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"校长不能包含特殊字符"
	},
	"location.fax":{
	    maxlength  : $.format("最多只能输入{0}个字符"),
	    telephone : "传真号码不合法!"
	},
	"location.remark":{
	    maxlength  : $.format("最多只能输入{0}个字符")
	},
	"location.personLiable":{
		minlength  : $.format("至少需要{0}个字符"),
		maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"法制副校长输入不能包含特殊字符"
	}
}

});
</s:if>
<s:if test='"add_path".equals(modeType)'>
$("#locationOrgName").click(function(event){
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
	$("#locationOrgName").val(selectOrgName);
}
</s:if>

function ispersonInCharge(){
	var flag=false;
	$.ajax({
		async:false,
		url: "${path }/baseinfo/personInCharegeManage/getPersonInCharegeByIdAndPlace.action",
		data:{
			"placeId":$("#locationId").val(),
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
	$("#locationId").val("");
	$("#name").val("");
	$("#locationEnglishName").val("");
	$("#location-kind-id").val("");
	$("#locationAddress").val("");
	$("#location-type-id").val("");
	$("#locationWebSite").val("");
	$("#location-atlocationHeadcount").val("");
	$("#location-president").val("");
	$("#locationFax").val("");
	$("#location-email").val("");
	$("#location-personLiable").val("");
	$("#location-personLiableTelephone").val("");
	$("#location-personLiableMobileNumber").val("");
	$("#location-remark").val("");
}
</script>
