<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="floatingPopulationDiv" class="container container_24">
	<form id="actualHouseForm" method="post" action="${path }/baseinfo/houseInfoForPopulation/maintainHouseInfoForPopulation.action">
		<pop:token />
		<input type="hidden" name="houseInfos.houseId" value="${houseInfos.id }"/>
		<input type="hidden" name="houseInfosId" value="${houseInfos.id }"/>
		<input type="hidden" name="type" value="${type}"/>
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="populationType" name="populationType" value="${populationType }"/>
		<input type="hidden" id="dailogName" name="dailogName" value="${dailogName }"/>
		<input id="houseInfoOrganizationId" type="hidden" name="houseInfos.organization.id" value="${houseInfos.organization.id }"/>
		<input type="hidden" name="id" value="${id }"/>
		
		<jsp:include page="/baseinfo/commonPopulation/commonAddressForActualPopulation.jsp"></jsp:include>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">房屋用途：</label>
		</div>
		<div class="grid_7">
			<select name="houseInfos.houseUses.id" id="houseUses" class="form-txt" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" defaultValue="${houseInfos.houseUses.id}"  />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">产权人类型：</label>
		</div>
		<div class="grid_7">
			<select name="houseInfos.propertyTypes.id" id="propertyTypes" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES" defaultValue="${houseInfos.propertyTypes.id}"  />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">产权人名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="houseInfos.name" id="name" maxlength="20" value="${houseInfos.name}"
				class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'产权人名称至少需要输入2个字符',maxlength:'产权人名称最多需要输入20个字符'}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lb1">证件类型：</label>
		</div>
		<div class="grid_7">
			<select name="houseInfos.certificateType.id" id="certificateType" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" showInternalId="true" defaultValue="${houseInfos.certificateType.id}" />
		</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">证件号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="houseInfos.certificateNumbe" id="certificateNumbe" maxlength="20" value="${houseInfos.certificateNumbe}"
				class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'证件号码至少需要输入2个字符',maxlength:'证件号码最多需要输入20个字符'}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lb1">产权人电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="houseInfos.propertyPersonTel" id="propertyPersonTel" maxlength="20" value="${houseInfos.propertyPersonTel}" title="请输入由数字和-组成的联系电话,例如：0577-88888888或者88888888"
				class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="houseInfos.propertyPersonMobile" id="propertyPersonMobile" maxlength="11" value="${houseInfos.propertyPersonMobile}"
				class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		</div>
		<div class="grid_7 ">
			<input type="checkbox" id="isRentalHouse"  name="houseInfos.isRentalHouse" value="true" <s:if test="houseInfos.isRentalHouse">checked</s:if>/>
			<label class="form-lb1">是否出租房</label>
		</div>
		<div id="rentalHouseInfoDiv" style="display: none;">
			<div class="grid_4 lable-right">
				<label class="form-lb1">出租用途：</label>
			</div>
			<div class="grid_7">
				<select name="houseInfo.usage.id" id="usage" class="form-txt" >
						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" defaultValue="${houseInfo.usage.id}"  />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lb1">出租房类别：</label>
			</div>
			<div class="grid_7">
				<select name="houseInfo.rentalType.id" id="rentalTypes" class='form-txt {required:true,messages:{required:"请选择出租房类别"}}' >
 					 <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE" defaultValue="${houseInfo.rentalType.id}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">出租房性质：</label>
			</div>
			<div class="grid_7">
				<select name="houseInfo.rentalHouseProperty.id" id="rentalHousePropertys" class="form-txt" >
                  <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_PROPERTY" defaultValue="${houseInfo.rentalHouseProperty.id}" />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lb1">隐患程度：</label>
			</div>
			<div class="grid_7">
				<select name="houseInfo.hiddenDangerLevel.id" id="hiddenDangerLevel" class='form-txt {required:true,messages:{required:"请选择隐患程度"}}' >
 					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" showInternalId="true" defaultValue="${houseInfo.hiddenDangerLevel.id}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lb1">房东姓名：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="houseInfo.rentalPerson" id="name" maxlength="25" value="${houseInfo.rentalPerson}"
					class="form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:25,messages:{required:'请输入房东姓名',exculdeParticalChar:'不能输入非法字符',minlength:'房东姓名至少需要输入2个字符',maxlength:'房东姓名最多需要输入25个字符'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">联系电话：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="houseInfo.rentalTelephone" id="rentalTelephone" maxlength="20" value="${houseInfo.rentalTelephone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888或者88888888"
					class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">联系手机：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="houseInfo.rentalMobileNumber" id=" " maxlength="11" value="${houseInfo.rentalMobileNumber}"
					class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
	 			<label class="form-lb1">出租人地址：</label>
	 		</div>
	 		<div class="grid_18">
	 			<input type="text" id="lessorAddress"  name="houseInfo.lessorAddress" value="${houseInfo.lessorAddress}"  maxlength="60" style="width: 99%"
	 				class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:60,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'地址至少需要输入2个字符',maxlength:'地址最多需要输入60个字符'}}"  />
	 		</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_18" style="height: 120px">
			<textarea rows="4" name="houseInfos.remark" id="remark" class="form-txt {minlength:2,maxlength:300,messages:{minlength:'备注至少需要输入2个字符',maxlength:'备注最多需要输入300个字符'}}" style="width: 99%">${houseInfos.remark }</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">
$(function(){

	function checkedRentalHouse(doc){
		if(doc.attr("checked")){
			$("#rentalHouseInfoDiv").show();
		}else{
			$("#rentalHouseInfoDiv").hide();
		}
	}
	checkedRentalHouse($("#isRentalHouse"));
	$("#isRentalHouse").click(function(){
		checkedRentalHouse($(this));
	})

	$('#houseRightNumberDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#houseRightNumberDate").datepicker("option", "minDate",date);
			}
		}
	});

	
	$('#certificateType').change(function(){
		if($("#certificateType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.LettingcertificateType@IDENTITY_CARD"/>) {
			$("#certificateNumbe").rules("add", {
				idCard: true,
				messages:{idCard:'请输入一个合法的身份证号码'}
			});
		}else{
			$("#certificateNumbe").rules("remove","idCard");
		}
	});
	
	$('#landRightsNumbeDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#landRightsNumbeDate").datepicker("option", "minDate",date);
			}
		}
	});
	
	$("#actualHouseForm").formValidate({
		submitHandler: function(form) {
			<s:if test="'add'.equals(mode)">
			$("#houseInfoOrganizationId").val($("#currentOrgId").val());
			</s:if>
			$(form).ajaxSubmit({
				async: false,
				success: function(data){
					if("<s:property value='#parameters.dailogName[0]'/>"=="unsettledPopulationMaintanceDialog"){
						$("#unsettledPopulationList").trigger("reloadGrid");
					 }else if("<s:property value='#parameters.dailogName[0]'/>"=="overseaPersonnelMaintanceDialog"){
						 $("#overseaPersonnelList").trigger("reloadGrid");
						}
				},
				error:function(data){
				}
			});
		},
		rules:{},
		messages:{},
		ignore:':hidden'
	});

})



</script>