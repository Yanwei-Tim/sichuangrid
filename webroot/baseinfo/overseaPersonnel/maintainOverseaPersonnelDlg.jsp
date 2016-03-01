<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="commonPopulation" class="container container_24">
	<form id="maintainForm" method="post" action="${path}/baseinfo/overseaPersonnelManage/maintainOverseaPersonnelBaseInfo.action" >
	<pop:token />
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input type="hidden" name="populationType" id="populationType" value="${populationType}" />
		<input id="populationId" type="hidden" name="overseaPersonnel.id" value="${overseaPersonnel.id}" />
		<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>" />
		<input id="populationOrganizationId" type="hidden" name="overseaPersonnel.organization.id" value="${overseaPersonnel.organization.id }" />
		<input id="_imgUrl" name="overseaPersonnel.imgUrl" type="hidden" value="${overseaPersonnel.imgUrl}"/>
		<input id="houseTypeId" type="hidden" name="houseInfo.houseType.id" value="${houseType}" />
		<input id="logOut"	type="hidden" name="overseaPersonnel.logOut" value="${overseaPersonnel.logOut}" />
		<input id="businessHouse" type="hidden" value=""/>
		<input id="other" type="hidden" value=""/>
		<c:if test="${dailogName=='householdStaffPopulationDialog'}">
			<input id="population_outGone" name="overseaPersonnel.outGone" value="${overseaPersonnel.outGone }" type="hidden" />
		</c:if>
			<div class="grid_5 lable-right">
				<label class="form-lbl">所属网格：</label>
			</div>
			<div class="grid_19">
		    	<input type="text" name="" id="overseaPersonnelOrgName" value="${overseaPersonnel.organization.orgName}"
	  				readonly <c:if test='${mode=="view"}'>disabled="disabled"</c:if> class="form-txt" style="width: 99%"/>
			</div>

			<div class='clearLine'></div>

		 	<div class="grid_5 lable-right">
			<c:if test='${mode!="view"}'><em class="form-req">*</em></c:if>
				<label class="form-lbl">英文名：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="overseaPersonnel.englishName" id="englishName" maxlength="50"
  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.englishName}"
  				 class="form-txt {required:true,notChineseOnly:true,minlength:true,maxlength:true,isLawful:true,messages:{required:'请输入英文名',notChineseOnly:'英文名不能输入中文!',minlength:'英文名至少需要输入2个字符',maxlength:'英文名最多需要输入20个字符',isLawful:'您输入了非法脚本请重新输入!'}}" />
			</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">姓名：</label>
			</div>
			<div class="grid_8">
		    	<input type="text" name="overseaPersonnel.name" id="name" maxlength="20"
  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.name}"
  				 class="form-txt {exculdeParticalChar:true,minlength:true,maxlength:true,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'姓名至少需要输入2个字符',maxlength:'姓名最多需要输入20个字符'}}" />
			</div>
	<div class='clearLine'></div>

			<div class="grid_5 lable-right">
			<c:if test='${mode!="view"}'><em class="form-req">*</em></c:if>
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_7">
				<select name="overseaPersonnel.gender.id" id="overseaPersonnelGender" class="form-txt {required:true,messages:{required:'请选择性别'}}" <c:if test='${mode=="view"}'>disabled="disabled"</c:if>>
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${overseaPersonnel.gender.id}"/>
 				</select>
 			</div>
 			<div class="grid_4 lable-right">
				<label class="form-lbl">出生日期：</label>
			</div>
			<div class="grid_8" id="birthdayDiv">
	   			<input type="text" name="overseaPersonnel.birthday" id="birthday" maxlength="32" readonly value='<s:date name="overseaPersonnel.birthday" format="yyyy-MM-dd" />' class="form-txt" />
	   		</div>

			<div class='clearLine'></div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">联系手机：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="overseaPersonnel.mobileNumber" id="overseaPersonnelMobileNumber" maxlength="11"
	  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.mobileNumber}" title="请输入11位以1开头的联系手机"
	  					class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">固定电话：</label>
			</div>
			<div class="grid_8">
		    	<input type="text" name="overseaPersonnel.telephone" id="overseaPersonnelTelephone" maxlength="20"
	  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.telephone}" title="请输入由数字和-组成的联系电话"
					class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">国籍：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="overseaPersonnel.nationality" id="overseaPersonnelNationality" maxlength="20"
  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.nationality}"
  				title=""
  				class="form-txt {guoji:true,exculdeParticalChar:true,messages:{guoji:'只能输入中文和英文',exculdeParticalChar:'不能输入非法字符'}}" />
			</div>
				<div class="grid_4 lable-right">
 			<c:if test='${mode!="view"}'><em class="form-req"  id="certificateTypeid"   >*</em></c:if>
				<label class="form-lbl">证件种类：</label>
			</div>
			<div class="grid_8">
		    	<select name="overseaPersonnel.certificateType.id" id="overseaPersonnelCertificateType" class="form-txt {required:true,messages:{required:'请选择证件种类'}}" <c:if test='${mode=="view"}'>disabled="disabled"</c:if>>
		    		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" defaultValue="${overseaPersonnel.certificateType.id}" />
		    	</select>
			</div>
			<div class='clearLine'></div>
 			<div class="grid_5 lable-right">
			<c:if test='${mode!="view"}'><em class="form-req">*</em></c:if>
				<label class="form-lbl">证件号码：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="overseaPersonnel.certificateNo" id="overseaPersonnelCertificateNo" maxlength="18"
  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.certificateNo}" title="请输入证件号码"
  					class="form-txt {required:true,exculdeParticalChar:true,exsistedIdCard:true,minlength:true,maxlength:true,messages:{required:'请输入证件号码',exculdeParticalChar:'不能输入非法字符',exsistedIdCard:'该证件号码已经存在，请重新输入',minlength:'证件号码至少需要输入2个字符',maxlength:'证件号码最多需要输入18个字符'}}" />
			</div>
 			<div class="grid_4 lable-right">
				<label class="form-lbl">邮箱：</label>
			</div>
			<div class="grid_8">
			    <input type="text" name="overseaPersonnel.mail" id="overseaPersonnelMail" maxlength="20"
	  				<c:if test='${mode=="view"}'>readonly</c:if> value="${overseaPersonnel.mail}" class="form-txt {validateMail:true,messages:{validateMail:'邮箱输入有误'}}" />
			</div>
 			<div class='clearLine'></div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">证件有效期起：</label>
			</div>
			<div class="grid_7">
	   	   		 <input type="text" name="overseaPersonnel.certificateStrartDay" id="certificateStrartDay" readonly class="form-txt" value='<s:date name="overseaPersonnel.certificateStrartDay" format="yyyy-MM-dd" />' />
	   		</div>
	   		<div class="grid_4 lable-right">
				证件有效期至：
			</div>
			<div class="grid_8">
	   	   		<input type="text" name="overseaPersonnel.certificateEndDay" id="certificateEndDay" readonly class="form-txt {certificateEndDayFormatter:true,messages:{certificateEndDayFormatter:'证件有效期截止时间不能小于起始时间'}}" value='<s:date name="overseaPersonnel.certificateEndDay" format="yyyy-MM-dd " />' />
	   		</div>
			<div class='clearLine'></div>
			<div id="hasHouseInfo">
				<jsp:include page="/baseinfo/commonPopulation/commonSearchAddress.jsp">
					<jsp:param value="overseaPersonnel" name="name"/>
				</jsp:include>
			</div>
   			<div class='clearLine'></div>
   			<div class="grid_5 lable-right">
				<label class="form-lbl">流入原因：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="overseaPersonnel.inflowReason" id="inflowReason" maxlength="40"
	  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.inflowReason}" style="width: 99%"
 					class="form-txt {exculdeParticalChar:true,minlength:true,maxlength:true,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'流入原因至少需要输入2个字符',maxlength:'流入原因最多需要输入40个字符'}}" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_8">
				<select name="overseaPersonnel.profession.id" id="overseaPersonnelProfession" class="form-txt" <c:if test='${mode=="view"}'>disabled="disabled"</c:if>>
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PROFESSION_TYPE" defaultValue="${overseaPersonnel.profession.id}"/>
 				</select>
 			</div>
 			<!--  
 			<div class='clearLine'></div>
 			<div class="grid_4 lable-right">
				<label class="form-lbl">户籍详址：</label>
			</div>
			<div class="grid_18">
			    <input type="text" name="overseaPersonnel.nativePlaceAddress" id="overseaPersonnelNativePlaceAddress" maxlength="50"
	  				<c:if test='${mode=="view"}'>readonly</c:if> value="<s:property escape="true" value="overseaPersonnel.nativePlaceAddress"/>" class="form-txt {exculdeParticalChar:true,minlength:true,maxlength:true,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'户籍祥址至少需要输入2个字符',maxlength:'户籍祥址最多需要输入50个字符'}}" style="width: 99%"/>
			</div>
			-->
 			<div class='clearLine'></div>
 			<div class="grid_6 lable-right">
				<label class="form-lbl">工作单位或就读学校：</label>
			</div>
			<div class="grid_18">
		    	<input type="text" name="overseaPersonnel.workUnit" id="overseaPersonnelworkUnit" maxlength="50"
	  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="${overseaPersonnel.workUnit}" style="width: 99%"
	  					class="form-txt {exculdeParticalChar:true,minlength:true,maxlength:true,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'工作单位或就读学校至少需要输入2个字符',maxlength:'工作单位或就读学校最多需要输入50个字符'}}" />
			</div>
 			<div class='clearLine'></div>
 			<div class="grid_5 lable-right">
				<label class="form-lbl">抵达时间：</label>
			</div>
			<div class="grid_7" id="arriveTime">
		    	<input type="text" name="overseaPersonnel.arrivalTime" id="overseaPersonnelArrivaTime" maxlength="50" readonly
	  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value='<s:date name="overseaPersonnel.arrivalTime" format="yyyy-MM-dd"/>' class="form-txt" style="width: 99%" />
			</div>

	    	<div class="grid_4 lable-right">
				<label class="form-lbl">离开时间：</label>
			</div>
			<div class="grid_8" id="leaveTime">
		    	<input type="text" name="overseaPersonnel.leaveTime" id="overseaPersonnelLeaveTime" maxlength="50" readonly
	  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> value="<s:date name="overseaPersonnel.leaveTime" format="yyyy-MM-dd"/>" 		    		
	  				class="form-txt {leavelDayFormatter:true,messages:{leavelDayFormatter:'离开时间不能小于抵达时间'}}"  style="width: 99%"/>
			</div>


 			<div class='clearLine'></div>
 				<div class="grid_5 lable-right">
				<label class="form-lbl">备注：</label>
			</div>
			<div class="grid_19" style="height: 120px">
    			<textarea name="overseaPersonnel.remark" id="overseaPersonnelRemark" maxlength="200";
	  				<c:if test='${mode=="view"}'>disabled="disabled"</c:if> class="form-txt {exculdeParticalChar:true,minlength:true,maxlength:true,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'备注至少需要输入2个字符',maxlength:'备注最多需要输入200个字符'}}" style="height: 7em;width: 99%;" >${overseaPersonnel.remark}</textarea>
			</div>
			<div class='clearLine'></div>
   		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	</form>
	<div id="populationDialogJsp"></div>
</div>

<script type="text/javascript">

$("#certificateStrartDay").datePicker({
	yearRange: '1900:2030',
	dateFormat: 'yy-mm-dd'
    });
$('#certificateEndDay').datePicker({
	yearRange: '1900:2030',
	dateFormat: 'yy-mm-dd'
    });
$('#birthday').datePicker({
	yearRange: '1900:2030',
	dateFormat: 'yy-mm-dd',
    maxDate:'+0d'
    });
$("#overseaPersonnelArrivaTime").datePicker({
	yearRange: '1900:2030',
	dateFormat: 'yy-mm-dd',
	maxDate:'+0d'
    });
$("#overseaPersonnelLeaveTime").datePicker({
	yearRange: '1900:2030',
	dateFormat: 'yy-mm-dd'
    });

function selectedTrue(inputType, textName){
	for(var i = 0; i < inputType.length; i++){
		if(inputType[i].text==textName){
			inputType[i].selected=true;
		}
	}
}
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}
function getCommonPopulation(idCardNo){
	if( idCardNo.length != null && idCardNo.length == 18){
		ajaxForCommonPopulation(idCardNo);
	}else if( idCardNo.length != null && idCardNo.length == 15){
		ajaxForCommonPopulation(idCardNo);
	}
}
function ajaxForCommonPopulation(idCardNo){
	$.ajax({
		async:false,
		url: "${path}/commonPopulation/commonPopulationManage/getCommonPopulationByIdCardNo.action",
		data:{
			"commonPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			manageCommonPopulation(responseData);
		}
	});
}
function manageCommonPopulation(responseData){
	//省市县的选中
	threeSelect({
		province:'province',
		city:'city',
		district:'district',
		provinceValue:responseData.province,
		cityValue:responseData.city,
		districtValue:responseData.district
	});

}
var idleYouthOrgTree="";
function isGridForTreeSelect(){
	if(idleYouthOrgTree != ""){
		return $.getSelectedNode(idleYouthOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}

function initCurrentAddress(){
	$.ajax({
		async: false ,
		url:"${path }/sysadmin/propertyManage/findPropertyDictByDomainName.action",
	   	data:{
		"propertyDomain.domainName":"现居住址类型"
        },
		success:function(responseData){
    	   if(responseData!=null&&responseData.length>0){
        	   for(var i=0;i<responseData.length;i++){
            	   var data = responseData[i];
            	   if(data.internalId==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
        			   $("#businessHouse").val(data.id+"-"+data.internalId);
        	   		}else{
        	   		   $("#other").val(data.id+"-"+data.internalId);
        	   		}
        	   }
			}
        }
	});
	chooseCurrentAddressType();
}

function changeOrgId(){
	$("#organizationId").val($("#orgId").val());
}

function renderHouseInfoFromCBUR(house){
	$("#community").val(house.community);
	$("#block").val(house.block);
	$("#unit").val(house.unit);
	$("#room").val(house.room);
	businessHouseToCurrentAddress();
	$("#houseId").val(house.houseId);
}

function renderHouseInfoFromADD(house){
	$("#community").val("");
	$("#block").val("");
	$("#unit").val("");
	$("#room").val("");
	$("#currentAddress").val(house.address);
	$("#houseId").val(house.houseId);
}
jQuery.validator.addMethod("validateMail", function(value, element){
	if(value == ""){
		return true;
	}
	var regex = /^([a-za-z0-9]+[_|_|.]?)*[a-za-z0-9]+@([a-za-z0-9]+[_|_|.]?)*[a-za-z0-9]+.[a-za-z]{2,3}$/;
	if(!regex.test(value))
	{
		return false;
	}
	return true;
});

$(document).ready(function(){
	populationHasHouseInfoChanged();
	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	};

	$("#houseCode").change(searchHoseInfoByCode);

	// 根据房屋编号自动获取实有房屋信息
	function searchHoseInfoByCode(){
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action",
		   	data:{
				"searchHouseInfoVo.houseCode":$('#houseCode').val(),
				"searchHouseInfoVo.houseTypeId":$('#houseTypeId').val()
	        },
			success:function(data){
				if(data){
					procCurrentAddressType(data);
					$.each(data, function(key, value) {
						if(null != value) {
							$("input[name='overseaPersonnel."+key+"']").val(value);
						} else {
							$("input[name='overseaPersonnel."+key+"']").val("");
						}
					});
					$("#houseInfoOrgName").val(data.organization.orgName);
				} else {
					$("input[name^='houseInfo.']:visible",$("#floatingPopulationform")).val("");
				}
				if(null == data) {
					$("#currentAddress").val("");
					$("#community").val("");
					$("#block").val("");
					$("#unit").val("");
					$("#room").val("");
				}
			}
		});
	}

	function procCurrentAddressType(data) {
		$("#houseId").val(data.id);
		$("#addressTypeId").val(data.addressType.id);
		$("#currentAddressType").val(data.addressType.id);
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			$("#building").show();
			$("#otherAddress").hide();
			$("#community").val(data.community);
			$("#block").val(data.block);
			$("#unit").val(data.unit);
			$("#room").val(data.room);
			$("#currentAddress").val(data.community+"小区"+data.block+"幢"+data.unit+"单元"+data.room+"室");
		} else {
			$("#building").hide();
			$("#otherAddress").show();
			$("#currentAddress").val(data.address);
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
		}
	}

	$("#houseCode").houseAutoComplete({
		searchLevel:"houseCode",
		select:function(event,ui){
			renderHouseInfoForHouseCode(ui.item);
		}});

	function renderHouseInfoForHouseCode(house){
		$("#houseId").val(house.houseId);
		$("#addressTypeId").val(house.addressType.id);
		$("#currentAddressType").val(house.addressType.id);
		$("#community").val(house.community);
		$("#block").val(house.block);
		$("#unit").val(house.unit);
		$("#room").val(house.room);
		procCurrentAddressType(house);
		$("#houseCode").val(house.houseCode);
	}

	$("#community").houseAutoComplete({
		searchLevel:"community",
		select:function(event,ui){
			renderHouseInfoFromCBUR(ui.item);
		}});

	$("#block").houseAutoComplete({
		searchLevel:"block",
		select:function(event,ui){
			renderHouseInfoFromCBUR(ui.item);
		},
		postData: {
			community :function(){return $("#community").val();}
			}});

	$("#unit").houseAutoComplete({
		searchLevel:"unit",
		select:function(event,ui){
			renderHouseInfoFromCBUR(ui.item);
		},
		postData: {
			community :function(){return $("#community").val();},
			block :function(){return $("#block").val();}
			}});

	$("#room").houseAutoComplete({
		searchLevel:"room",
		select:function(event,ui){
			renderHouseInfoFromCBUR(ui.item);
		},
		postData: {
			community :function(){return $("#community").val();},
			block :function(){return $("#block").val();},
			unit :function(){return $("#unit").val();}
			}});

	$("#currentAddress").houseAutoComplete({
		searchByAddress:true,
		select:function(event,ui){
			renderHouseInfoFromADD(ui.item);
		}});

	threeSelect({province:'province',
		city:'city',
		district:'district',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});
	$(".dialogtip").inputtip();
	$("#currentAddressType").change(chooseCurrentAddressType);
	initCurrentAddress();

	function searchHouse(searchByAddress,searchLevel){
		$.ajax({
			async: false ,
			url:"${path }/baseinfo/houseAutoComplete/findSingleHousesIdByAddressLib.action",
		   	data:{
				 "orgId":getCurrentOrgId(),
				 "searchByAddress":searchByAddress,
				 "searchType":searchLevel,
				 "community":function(){return $("#community").val()},
				 "block":function(){return $("#block").val()},
				 "unit":function(){return $("#unit").val()},
				 "searchCondition":function(){return searchByAddress?$("#currentAddress").val(): $("#room").val();}
	        },
			success:function(responseData){
	    		if(responseData==null||responseData==undefined||responseData==""||responseData=="null"){
	    			$("#houseId").val("");
		    	}else{
		        	$("#houseId").val(responseData);
			    }
	        }
		});
	}

	jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		if(isGridForTreeSelect()){
			return true;
		}else{
			return false;
		}

	});

	jQuery.validator.addMethod("currentAddress", function(value, element){
		var other = $("#other").val();
		if(other!=null&& $("#currentAddressType").val()== other.split("-")[0]){
			if(other.split("-")[1]==<s:property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
				if(value==null||value==undefined||value==""){
	    			return false;
	    		}else{
	    			return true;
	    		}
			}
		}
	  return true;
	});

	jQuery.validator.addMethod("community", function(value, element){
		//var businessHouse = $("#businessHouse").val();
		//if(businessHouse!=null&& $("#currentAddressType").val()== businessHouse.split("-")[0]){
			if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
				var livingHouse=$("#community").val();
				return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
			}
		//}
	  return true;
	});

	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		var certificateType   = $("#overseaPersonnelCertificateType").val();
		if(certificateType==null||certificateType==undefined||certificateType==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/overseaPersonnelManage/hasDuplicateOverseaPersonnel.action",
		   	data:{
				"overseaPersonnel.certificateNo":$('#overseaPersonnelCertificateNo').val(),
				"overseaPersonnel.certificateType.id":$('#overseaPersonnelCertificateType').val(),
				"orgId":$('#populationOrganizationId').val(),
				"mode":$('#mode').val(),
				"overseaPersonnel.id":$('#populationId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});

	jQuery.validator.addMethod("certificateEndDayFormatter", function(value, element){
		if($("#certificateEndDay").val()<$("#certificateStrartDay").val()){
			return false;
		}
		return true;
	});

	
	jQuery.validator.addMethod("guoji", function(value, element){
		var patrn=/^[A-Za-z\u4e00-\u9fa5]*$/;
		if (!patrn.exec(value)) return false;
		return true  
	});
	
	jQuery.validator.addMethod("leavelDayFormatter",function(value,element){
		if($("#overseaPersonnelLeaveTime").val()<$("#overseaPersonnelArrivaTime").val()){
			return false;
		}
		return true;
	});
	resetBirthdayField($("#birthday").val());
	$('#idCardNo').blur(idCardChanged);
		$("#maintainForm").formValidate({
			submitHandler: function(form) {
				$("#_imgUrl").val($("#imgUrl").val());
				businessHouseToCurrentAddress();
	         	$(form).ajaxSubmit({
	         	 async : false,
	             success: function(data){
	                     if((!data.id)||(data.id+""=="undefined")){
	                    	  $.messageBox({message:data,level: "error"});
	                    	  return false;
	                    }
	                   
						if($("#populationHasHouseInfo").val()=="false"){
	                     if("add" == $("#mode").val()){
	                    	 if($("#logOut").val()!="1"){

	        					 $("#overseaPersonnelList").addRowData(data.id,data,"first");
	        					 $("#overseaPersonnelList").setSelection(data.id);
	        				 }
	        				 $("#overseaPersonnelList").trigger("reloadGrid");
	                    	 $.messageBox({message:"境外人员信息新增成功！"});
	                     }
	                     if("edit" == $("#mode").val()){
	                    	 if(data.death == true || data.death == "true"){
	        					 if($("#logOut").val()=="0"){
	        						 $("#overseaPersonnelList").delRowData(data.id+"");
	        					 }else{
	        						 $("#overseaPersonnelList").setRowData(data.id,data);
	        						 $("#"+data.id+"").css('color','#778899');
	        					 }
	        				 }
	        				 $("#overseaPersonnelList").trigger("reloadGrid");
	                    	 $("#overseaPersonnelList").setRowData(data.id,data);
	        				 $.messageBox({message:"境外人员信息修改成功！"});
	                     }
						}
						  $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.encryptId,data);
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
			ignore:":hidden",
			rules:{
				
			},
			messages:{
				
			}
		});

<c:if test='${mode=="add"}'>
    $("#populationOrganizationId").val($("#currentOrgId").val());
	$.ajax({
		async:false,
		url:"${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id" : $("#currentOrgId").val()
		},
		success:function(responseData){
			$("#overseaPersonnelOrgName").val(responseData);
		}
	});
</c:if>

	<c:if test='${dialog != null}'>
		idleYouthOrgTree = $("#commonPopulationOrgName").treeSelect({
			inputName:"overseaPersonnel.organization.id",
			url:"/sysadmin/orgManage/loadTownForExtTree.action",
			onSelect:changeOrgId
		});
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
			async:false,
			url:url,
			success:function(data){
				$.searchChild(idleYouthOrgTree,data);
			}
		});
	</c:if>

	
	// fateson add  使用js 去掉必填控件 
	//$("#certificateTypeid").hide();
	//$("#overseaPersonnelCertificateType").attr("class","form-txt");
	
	
});

<c:if test='${modeType=="add_path"}'>
function setZone(selectOrgId,selectOrgName){
		$("#orgId").val(selectOrgId);
		$("#organizationId").val(selectOrgId);
		$("#commonPopulationOrgName").val(selectOrgName);
	}
</c:if>

function idCardChanged(){
	var text=$('#idCardNo').val();
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
	text=$('#idCardNo').val();
	getCommonPopulation(text);
}

function resetBirthdayField(text){
	if (text!="" && $('#idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='overseaPersonnel.birthday' id='birthday' readonly class='form-txt' value='"+text+"'/>");
	}
	$('#birthday').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'
        });
	
}
function businessHouseToCurrentAddress(){
	var businessHouse = $("#businessHouse").val();
	var other = $("#other").val();
	var currentAddressType = $("#currentAddressType").val();
	if(currentAddressType==businessHouse.split("-")[0]){
		var community = $("#community").val();
		var block = $("#block").val();
		var unit = $("#unit").val();
		var room = $("#room").val();
		var currentAddress ="";

		if(community!=null&&community!=undefined&&community!=""){
			currentAddress += community+"小区";
		}
		if(block!=null&&block!=undefined&&block!=""){
			currentAddress += block+"幢";
		}
		if(unit!=null&&unit!=undefined&&unit!=""){
			currentAddress += unit+"单元";
		}
		if(room!=null&&room!=undefined&&room!=""){
			currentAddress += room+"室";
		}
		 $("#currentAddress").val(currentAddress);
	}else{
		$("#community").val("");
		$("#block").val("");
		$("#unit").val("");
		$("#room").val("");
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

function isHelpPersonnel(){
	var flag=false;
	$.ajax({
		async:false,
		url: "${path }/baseinfo/helpPersonnel/getHelpPersonnelByIdAndPlace.action",
		data:{
			"personnelId":$("#idleYouthId").val(),
			"personnelType":$("#keyType").val()
		},
		success:function(responseData){
			if(responseData.length>0){
				flag=true;
			}
		}
	});
	return flag;
}

function chooseCurrentAddressType(){
	var businessHouse = $("#businessHouse").val();
	var other = $("#other").val();
	var currentAddressType = $("#currentAddressType").val();
	if(currentAddressType==businessHouse.split("-")[0]){
		$("#building").css("display","block");
		$("#otherAddress").css("display","none");
	}else if(currentAddressType==other.split("-")[0]){
		$("#building").css("display","none");
		$("#otherAddress").css("display","block");
	}else{
		$("#building").css("display","none");
		$("#otherAddress").css("display","none");
	}
}
if(document.getElementById("jl_add3") != null && document.getElementById("jl_add3") != "null"){
	if($("#mode").val()== "add"){
		document.getElementById("jl_add3").style.display = "none";
	}else{
		if($("#population_outGone").val() == true || $("#population_outGone").val() == "true"){
			document.getElementById("jl_add3").style.display = "block";
		}else{
			document.getElementById("jl_add3").style.display = "none";
		}
	}
}
jQuery.validator.addMethod("exculdeParticalChar", validatorSpecialWord);

function validatorSpecialWord(value,element){
	if(value==null||value==undefined||value==""){return true}
	var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
	return this.optional(element)||!pattern.test(value) ; 
}

</script>