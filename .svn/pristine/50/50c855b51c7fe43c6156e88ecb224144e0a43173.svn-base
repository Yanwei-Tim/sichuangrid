<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="newSocietyFederationInfo" class="container container_24">
	<form id="maintainForm" method="post" action="">
		<c:if test='${mode=="add"}'>
			<pop:token/>
		</c:if>
		<input id="mode" name="mode" type="hidden" value="${mode}" />
		<input id="keyType" name="keyType" type="hidden" value="${keyType}" />
		<input id="organizationId" name="organization.id" type="hidden" value="${organization.id}" />
		<input id="_imgUrl" name="newSocietyOrganizations.imgUrl" type="hidden" value="${newSocietyOrganizations.imgUrl}"/>
		<input id="newSocietyOrganizationsId" name="newSocietyOrganizations.id" type="hidden" value="${newSocietyOrganizations.id}" />
		<input id="placeTypeName" type="hidden" name="placeTypeName" value="${placeTypeName}">
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
		<input type="hidden" id="houseInfoBuildingId" name="newSocietyOrganizations.gisInfo.buildingId" value="${newSocietyOrganizations.gisInfo.buildingId}">
		<input type="hidden" id="houseInfoCenterX" name="newSocietyOrganizations.gisInfo.centerX" value="${newSocietyOrganizations.gisInfo.centerX}">
		<input type="hidden" id="houseInfoCenterY" name="newSocietyOrganizations.gisInfo.centerY" value="${newSocietyOrganizations.gisInfo.centerY}">

		<div class="grid_4 lable-right">
  			<em class="form-req">*</em>
  			<label class="form-lbl">所属区域：</label>
  		</div>
   		<div class="grid_18 form-left">
			<input type="text" id="orgName" name="orgName" value=""  readonly <c:if test='${mode=="view" }'>disabled='true'</c:if> class="form-txt" />
   		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">组织名称：</label>
		</div>
		<div class="grid_18 form-left">
	    	<input type="text" id="name" name="newSocietyOrganizations.name" maxlength="20"
	    	<c:if test='${mode=="view"}'>disabled='true'</c:if> value="${newSocietyOrganizations.name}" class="form-txt  {required:true,exculdeParticalChar:true,exsistedCompanyName:true,messages:{required:'请输入社会组织名称',exculdeParticalChar:'不能输入非法字符',exsistedCompanyName:'社会组织名称在该网格下已经存在，请重新输入'}}" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<%--<em class="form-req">*</em> --%>
			<label class="form-lbl">住所：</label>
		</div>
		<div class="grid_18 form-left">
			<input type="text" id="address" name="newSocietyOrganizations.address" maxlength="50"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.address}" class="form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

        <div class="grid_4 lable-right">
			<label class="form-lbl">主要职责：</label>
		</div>
		<div class="grid_18 form-left">
			<input type="text" id="mainResponsibilities" name="newSocietyOrganizations.mainResponsibilities" maxlength="50"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.mainResponsibilities}" class="form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<em class="form-req">*</em>
  			<label class="form-lbl">组织类别：</label>
  		</div>
		<div class="grid_7">
			<select id="type" name="newSocietyOrganizations.type.id" class="form-select form-txt">
		   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" defaultValue="${newSocietyOrganizations.type.id}" reletionId="subTypeId"
		   		reletionName="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" id="type" />
			</select>
   		</div>
<!-- 				            新字段		    		 -->
		<!-- <div class="grid_4 lable-right"> -->
  			<!-- <label class="form-lbl">组织子类别：</label> -->
  		<!-- </div> -->
					<!-- <div class="grid_7"> -->
						<%-- <select id="subTypeId" name="newSocietyOrganizations.subType.id" class="form-select form-txt"> --%>
<%--  						<c:if test='${mode=="view" }'>disabled="disabled"</c:if>>  --%>
<%--  					   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" defaultValue="${newSocietyOrganizations.subType.id}" />  --%>
  						<%-- </select> --%>
		    		<!-- </div> -->
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<em class="form-req">*</em>
<!-- 			  			<label class="form-lbl">法定代表人（负责人）：</label> -->
  			<label class="form-lbl">法定代表人：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="legalPerson" name="newSocietyOrganizations.legalPerson" maxlength="15"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.legalPerson}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">固定电话：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="legalPersonTelephone" name="newSocietyOrganizations.legalPersonTelephone" maxlength="15"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.legalPersonTelephone}" class="form-select form-txt dialogtip" title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<label class="form-lbl">联系手机：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="legalPersonMobileNumber" name="newSocietyOrganizations.legalPersonMobileNumber" maxlength="11"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.legalPersonMobileNumber}" class="form-select form-txt dialogtip" title="请输入11位以1开头的手机号码!例如:13988888888" />
		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">业务主管单位：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="chargeUnit" name="newSocietyOrganizations.chargeUnit" maxlength="50"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.chargeUnit}" class="form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<label class="form-lbl">登记证号码：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="registrationNumber" name="newSocietyOrganizations.registrationNumber" maxlength="30"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.registrationNumber}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">有效期：从</label>
  		</div>
		<div class="grid_3 form-left">
			<input type="text" id="validityStartDate" name="newSocietyOrganizations.validityStartDate" readonly
			<c:if test='${mode=="view" }'>disabled='true'</c:if>
			value="<fmt:formatDate value="${newSocietyOrganizations.validityStartDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" />
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">至&nbsp;</label>
  		</div>
  		<div class="grid_3 form-left">
			<input type="text" id="validityEndDate" name="newSocietyOrganizations.validityEndDate" readonly
			<c:if test='${mode=="view" }'>disabled='true'</c:if>
			value='<fmt:formatDate value="${newSocietyOrganizations.validityEndDate}" type="date" pattern="yyyy-MM-dd" />' class="form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<label class="form-lbl">成员数：</label>
  		</div>
		<div class="grid_6 form-left">
			<input type="text" id="personNum" name="newSocietyOrganizations.personNum" maxlength="50"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.personNum}" class="form-txt" />
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">党员人数：</label>
  		</div>
		<div class="grid_6 form-left">
			<input type="text" id="partyNum" name="newSocietyOrganizations.partyNum" maxlength="50"
			<c:if test='${mode=="view" }'>disabled='true'</c:if> value="${newSocietyOrganizations.partyNum}" class="form-txt" />
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
        <div class="clearLine"></div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">简 介：</label>
		</div>
		<div class="grid_18">
   			<textarea id="introduction"  name="newSocietyOrganizations.introduction"
   		 	<c:if test='${mode=="view" }'>disabled='true'</c:if> class="form-txt" style="height: 3em;">${newSocietyOrganizations.introduction}</textarea>
		</div>
        <div class="clearLine"></div>
        <br/>

		<div class="grid_4 lable-right">
			<label class="form-lbl">获得荣誉：</label>
		</div>
		<div class="grid_18">
   			<textarea id="honor"  name="newSocietyOrganizations.honor"
   		 	<c:if test='${mode=="view" }'>disabled='true'</c:if> class="form-txt" style="height: 3em;">${newSocietyOrganizations.honor}</textarea>
		</div>
        <div class="clearLine"></div>
        <br/>

		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_18">
   			<textarea id="remark" rows="3" name="newSocietyOrganizations.remark"
   		 	<c:if test='${mode=="view" }'>disabled='true'</c:if> class="form-txt" >${newSocietyOrganizations.remark}</textarea>
		</div>
	</form>
</div>
<script type="text/javascript">

$('#validityStartDate').datePicker({
	yearRange : '1900:2030',
	dateFormat : 'yy-mm-dd',
	maxDate : '+0d'

	});
$('#validityEndDate').datePicker({
	minDate:'+1d'

	});

$(document).ready(function(){
	$("#organizationId").val($("#currentOrgId").val());
	$(".dialogtip").inputtip();
	jQuery.validator.addMethod("partyNum", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var personNum = $("#personNum").val();
		if(personNum==null||personNum==undefined||personNum==""){return true}
		if(value>eval(personNum)){
			return false;
		}else{
			return true;
		}
	});
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	};

	//验证名称唯一性
	jQuery.validator.addMethod("exsistedCompanyName", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:'${path}/baseinfo/newSocietyOrganizationsManage/hasDuplicateNewSocietyOrganizationsName.action',
		   	data:{
				"newSocietyOrganizations.name": $("#name").val(),
				"newSocietyOrganizations.organization.id" :  $("#currentOrgId").val(),
				"mode": $("#mode").val(),
				"newSocietyOrganizations.id":$("#newSocietyOrganizationsId").val()
	        },
			success:function(responseData){
				if(responseData!=null && responseData!=true && responseData!=false){
					$.messageBox({
						message:responseData,
						level: "error"
						});
					return;
				}
				flag = !eval(responseData);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
 				$.messageBox({message:"提交错误",level: "error"});
  	   		}	        
		});
		return flag;
	});
	
<c:if test='${mode!="view"}'>
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$("#_imgUrl").val($("#imgUrl").val());
			$(form).ajaxSubmit({
				async : false,
				success:function(data){
	                 if(data!=null && data!=false && data!=true){
	               	 $.messageBox({
							message:data,
							level: "error"
						 });
	                	return;
	                } 
	                $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data); 
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
	     	      	alert("提交数据时发生错误");
	  	   		}
			});
		},
		rules:{
			"newSocietyOrganizations.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			/* 
			"newSocietyOrganizations.address":{
				required:true,
				minlength:2,
				maxlength:50
			},
			 */
			"newSocietyOrganizations.type.id":{
				required:true
			},
			"newSocietyOrganizations.legalPerson":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"newSocietyOrganizations.legalPersonTelephone":{
				telephone:true
			},
			"newSocietyOrganizations.legalPersonMobileNumber":{
				mobile:true
			},
			"newSocietyOrganizations.registrationNumber":{
				number:true
			},
			"newSocietyOrganizations.introduction":{
				maxlength:200
			},
			"newSocietyOrganizations.honor":{
				maxlength:200
			},
			"newSocietyOrganizations.remark":{
				maxlength:200
			},
			"newSocietyOrganizations.personNum":{
				number:true,
				digits:true,
				min:1,
				max:999999999
			},
			"newSocietyOrganizations.partyNum":{
				number:true,
				digits:true,
				partyNum:true,
				min:1,
				max:999999999
			},
			"newSocietyOrganizations.mainResponsibilities":{
				exculdeParticalChar:true,
				maxlength:50
			}
		},
		messages:{
			"newSocietyOrganizations.name":{
				required:"请输入社会组织名称",
				exculdeParticalChar:"名称只能输入字母，数字和中文字符",
				minlength:$.format("名称至少需要输入{0}个字符")
			},
			/* 
			"newSocietyOrganizations.address":{
				required:"请输入住所",
				minlength:$.format("住所至少需要输入{0}个字符"),
				maxlength:$.format("住所最多只能输入{0}个字符")
			},
			*/
			"newSocietyOrganizations.type.id":{
				required:"请选择社会组织类型"
			},
			"newSocietyOrganizations.legalPerson":{
				required:"请输入法定代表人",
				exculdeParticalChar:"法定代表人只能输入字母，数字和中文字符",
				minlength:$.format("法定代表人至少需要输入{0}个字符")
			},
			"newSocietyOrganizations.legalPersonMobileNumber":{
				mobile:$.format("手机号码输入只能是以1开头的11位数字")
			},
			"newSocietyOrganizations.legalPersonTelephone":{
				telephone:$.format("固定电话只能输数字和横杠(-)")
			},
			"newSocietyOrganizations.registrationNumber":{
				number:'登记证号码需要输入数字'
			},
			"newSocietyOrganizations.introduction":{
				maxlength:$.format("简 介最多只能输入{0}个字符")
			},
			"newSocietyOrganizations.honor":{
				maxlength:$.format("获得荣誉最多只能输入{0}个字符")
			},
			"newSocietyOrganizations.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			},
			"newSocietyOrganizations.personNum":{
				number: '成员数需要输入正整数',
				digits:'成员数需要输入正整数',
				min: '成员数需要输入正整数',
				max: '成员数最大999999999'
			},
			"newSocietyOrganizations.partyNum":{
				number: '党员人数需要输入正整数',
				digits:'党员人数需要输入正整数',
				partyNum:"党员人数不能大于成员数",
				min: '党员人数需要输入正整数',
				max: '党员人数最大输入999999999'
			},
			"newSocietyOrganizations.mainResponsibilities":{
				exculdeParticalChar:"主要职责只能输入字母，数字和中文字符",
				maxlength:$.format("主要职责最多只能输入{0}个字符")
			}
		}
	});
	<c:choose>
	<c:when test='${mode=="add"}'>
		$("#maintainForm").attr("action","${path}/baseinfo/newSocietyOrganizationsManage/addNewSocietyOrganizations.action");
	</c:when>
	<c:otherwise>
		$("#maintainForm").attr("action","${path}/baseinfo/newSocietyOrganizationsManage/updateNewSocietyOrganizations.action");
	</c:otherwise>
	</c:choose>
</c:if>
	$.ajax({
		async:false,
		url:"${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#currentOrgId").val()
		},
		success:function(responseData){
			$("#orgName").val(responseData);
		}
	});
});
function emptyObject(){
	$("#name").val("");
	$("#address").val("");
	$("#type\\.id").val("");
	$("#legalPerson").val("");
	$("#legalPersonTelephone").val("");
	$("#legalPersonMobileNumber").val("");
	$("#chargeUnit").val("");
	$("#registrationNumber").val("");
	$("#validityStartDate").val("");
	$("#validityEndDate").val("");
	$("#personNum").val("");
	$("#partyNum").val("");
	$("#introduction").val("");
	$("#honor").val("");
	$("#remark").val("");
}

function isLawful(value){
	if($("#newSocietyFederationInfo input[type='text']").val()=="vbscript" || value=="vbscript"){
		 $.messageBox({
				message:"您输入了非法脚本，请重新输入",
				level: "error"
			 });
		 return;
	}
}

</script>