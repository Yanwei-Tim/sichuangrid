<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24" >
	<div id="actualHouse" class="container container_24">		
		<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
		<input type="hidden" name="location.id" id="locationId" value="${(location.id)!}" />
		<input id="organizationId2"	type="hidden" name="organizationId" value="${(location.organization.id )!}" />
		<input id="organizationId"	type="hidden" name="location.organization.id" value="${(location.organization.id )!}" />
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<@s.if test='"updateRentalHouseTemp".equals(mode)'>
	 			<input type="text"  id="rentalHouseTempOrgName"  name="location.organization.orgName"  readonly  value="${(location.organization.orgName)!}" style="width: 99%"	class="form-txt" />
 			</@s.if><@s.else>
 				<input type="text"  id="locationOrgName"  name="location.organization.orgName"  readonly  value="${(location.organization.orgName)!}" style="width: 99%"	class="form-txt" />
 			</@s.else>
 		</div>
 		<div class='clearLine'>&nbsp;</div>
 		
 		<div class="grid_4 lable-right">
 			<label class="form-lb1">房屋编号：</label>
 		</div>
 			<div class="grid_10">
				<input type="text" id="houseCode"  name="location.houseCode"  value="${(location.houseCode)!}"  maxlength="50" style="width: 99%" class="form-txt " />
 			</div>
 		<div class="grid_4 lable-right">
 			<input type="checkbox" id="isRentalHouse"  name="location.isRentalHouse" value="true" <@s.if test="true==location.isRentalHouse">checked="checked"</@s.if>/>
 			<label class="form-lb1">是否出租房</label>
 		</div>
 		<div class='clearLine'>&nbsp;</div>
 		
 		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">房屋地址：</label>
		</div>
		<div class="grid_3">
			<input id="addressTypeId" type="hidden" name="location.addressType.id" value="${(location.addressType.id)!}" />
   			<select name="currentAddressType" id="currentAddressType" class="form-txt">
 				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE" showInternalId=true defaultValue="${(location.addressType.id)!}" notNull=true />
			</select>
		</div>
		<div id="building" >
 			<div class="grid_3">
 			 	<input type="text"   id="community"  name="location.community"  maxlength="20"  value="${(location.community)!}" style="width: 93%" class="form-txt {community:true,messages:{community:'该房屋地址不存在，请重新输入房屋编号'}}" />
 			</div>
 			<div class="grid_2">
 	   		 	<label class="form-lbl">小区</label>
 			</div>
 			<div class="grid_2">
	 			<input type="text"  id="block"   name="location.block"  maxlength="8"  value="${(location.block)!}" style="width: 93%"	class="form-txt" />
 			</div>
 			<div class="grid_1">
 		     	<label class="form-lbl">幢</label>
 			</div>
 			<div class="grid_2">
 			 	<input type="text"  id="unit"   name="location.unit"  maxlength="6"  value="${(location.unit)!}" style="width: 93%"	class="form-txt" />
 			</div>
 			<div class="grid_2">
 		 	 	<label class="form-lbl">单元</label>
 			</div>
 			<div class="grid_2">
 			 	<input type="text"  id="room"   name="location.room"  maxlength="10"  value="${(location.room)!}" style="width: 93%"	class="form-txt" />
 			</div>
 			<div class="grid_1">
 		 		<label class="form-lbl">室</label>
 			</div>
		</div>
			<div id="otherAddress" class="grid_15" style="display:none">
				<input type="text"  id="address"  name="location.address"  maxlength="50"  value="${(location.address )!}" style="width: 99%" class="form-txt " />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">建筑物名称：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.buildingName" id="buildingName" maxlength="100" value="${(location.buildingName)!}" class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">建筑物用途：</label>
			</div>
			<div class="grid_7">
				<select name="location.buildingUses.id" id="buildingUses" class="form-txt" >
				<option value="" selected="selected" >请选择</option>
 				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@BUILDING_USES" defaultValue="${(location.buildingUses.id)!}" notNull=true />
			</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">物管单位名称：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.propertyName" id="propertyName" maxlength="100" value="${(location.propertyName)!}" class="form-txt " />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">代表人/业主：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.houseOwner" id="houseOwner" maxlength="30" value="${(location.houseOwner)!}" class="form-txt " />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">身份证号码：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.houseOwnerIdCardNo" id="houseOwnerIdCardNo" maxlength="18" value="${(location.houseOwnerIdCardNo)!}" class="form-txt {idCard:true,messages:{idCard:'请输入一个合法的身份证号码'}}" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">代表人电话：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.telephone" id="telephone" maxlength="20" value="${(location.telephone)!}" title="请输入由数字和-组成的联系电话,例如：0577-88888888或者88888888" class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输入数字和横杠（-）")}}' />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">房屋户型：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.houseDoorModel" id="houseDoorModel" maxlength="30" value="${(location.houseDoorModel)!}" class="form-txt {minlength:2,maxlength:50,messages:{minlength:'房屋户型至少需要输入2个字符',maxlength:'房屋户型最多需要输入30个字符'}}" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">建成年份：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.builtYear" id="builtYear" maxlength="4" value="${(location.builtYear)!}" class="form-txt {number:true,min:1000,isLargerThanNow:true,messages:{number:'建成年份只能输入4位数字',min:'请输入一个大于1000的值',isLargerThanNow:'建成年份不能大于当前年份'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">本户建筑面积：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.houseArea" id="houseArea" maxlength="15" value="${(location.houseArea)!}" class="form-txt {decimal:true,range:[0,9999999.99],messages:{decimal:'请输入非负数 ，保留两位位小数点',range: '请输入0至9999999.99之间的非负数 '}}" maxlength="10" />
			</div><div class="grid_1">m<sup>2</sup></div>
			<div class="grid_3 lable-right">
				<label class="form-lb1">房屋结构：</label>
			</div>
			<div class="grid_7">
				<select name="location.houseStructure.id" id="houseStructure" class="form-txt" >
				    <option value="" selected="selected" >请选择</option>
		 			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" defaultValue="${(location.houseStructure.id)!}" notNull=true />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">房屋用途：</label>
			</div>
			<div class="grid_7">
				<select name="location.houseUses.id" id="houseUses" class="form-txt" >
				    <option value="" selected="selected" >请选择</option>
 					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" defaultValue="${(location.houseUses.id)!}" notNull=true />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">房屋来源：</label>
			</div>
			<div class="grid_3">
				<select name="location.houseSource.id" id="houseSource" class="form-txt" >
				    <option value="" selected="selected" >请选择</option>
 					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" showInternalId=true defaultValue="${(location.houseSource.id)!}" notNull=true />
				</select>
			</div>
			<div class="grid_4" id="ownPropertyChange">
				<select name="location.ownProperty.id" id="ownProperty" class="form-txt" >
				   <option value="" selected="selected" >请选择</option>
 				   <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@OWN_PROPERTY" defaultValue="${(location.ownProperty.id)!}" notNull=true />
				</select>
			</div>
			<div class="grid_4" id="rentalBuildingChange" style="display:none">
				<select name="location.rentalBuildings.id" id="rentalBuildings" class="form-txt" >
				<option value="" selected="selected" >请选择</option>
 				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@RENTAL_BUILDINGS" defaultValue="${(location.rentalBuildings.id)!}" notNull=true />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">房屋凭证：</label>
			</div>
			<div class="grid_7">
				<select name="location.housingVouchers.id" id="housingVouchers" class="form-txt" >
				<option value="" selected="selected" >请选择</option>
 				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSING_VOUCHERS" defaultValue="${(location.housingVouchers.id)!}" notNull=true />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">房屋权证号：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.houseRightNumber" id="houseRightNumber" maxlength="50" value="${(location.houseRightNumber)!}" class="form-txt {minlength:2,maxlength:80,messages:{minlength:'房屋权证号至少需要输入2个字符',maxlength:'房屋权证号最多需要输入80个字符'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">发证时间：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.houseRightNumberDate" id="houseRightNumberDate" maxlength="50" value="<@s.date name="location.houseRightNumberDate" format="yyyy-MM-dd" />" class="form-txt" readonly/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">土地凭证：</label>
			</div>
			<div class="grid_7">
				<select name="location.landDocuments.id" id="landDocuments" class="form-txt" >
				<option value="" selected="selected" >请选择</option>
 				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@LAND_DOCUMENTS" defaultValue="${(location.landDocuments.id)!}" notNull=true />
			</select>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">土地权证号：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.landRightsNumbe" id="landRightsNumbe" maxlength="30" value="${(location.landRightsNumbe)!}" class="form-txt {minlength:2,maxlength:80,messages:{minlength:'土地权证号至少需要输入2个字符',maxlength:'土地权证号最多需要输入30个字符'}}" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">发证时间：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.landRightsNumbeDate" id="landRightsNumbeDate" maxlength="50" value="<@s.date name="location.landRightsNumbeDate" format="yyyy-MM-dd" />" class="form-txt" readonly/>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">产权人类型：</label>
			</div>
			<div class="grid_7">
				<select name="location.propertyTypes.id" id="propertyTypes" class="form-txt" >
				<option value="" selected="selected" >请选择</option>
 				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES" defaultValue="${(location.propertyTypes.id)!}" notNull=true />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">产权人姓名：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.name" id="name" maxlength="20" value="${(location.name)!}" class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:30,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'产权人名称至少需要输入2个字符',maxlength:'产权人名称最多需要输入20个字符'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">证件类型：</label>
			</div>
			<div class="grid_7">
				<select name="location.certificateType.id" id="certificateType" class="form-txt" >
				<option value="" selected="selected" >请选择</option>
 				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" showInternalId=true defaultValue="${(location.certificateType.id)!}" notNull=true />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">证件号码：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.certificateNumbe" id="certificateNumbe" maxlength="20" value="${(location.certificateNumbe)!}" class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:50,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'证件号码至少需要输入2个字符',maxlength:'证件号码最多需要输入20个字符'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">产权人电话：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.propertyPersonTel" id="propertyPersonTel" maxlength="20" value="${(location.propertyPersonTel)!}" title="请输入由数字和-组成的联系电话,例如：0577-88888888或者88888888" class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输入数字和横杠（-）")}}' />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">联系手机：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.propertyPersonMobile" id="propertyPersonMobile" maxlength="11" value="${(location.propertyPersonMobile)!}" class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">备注：</label>
			</div>
			<div class="grid_18 heightAuto">
				<textarea rows="2" name="location.remark" id="remark" class="form-txt {minlength:2,maxlength:300,messages:{minlength:'备注至少需要输入2个字符',maxlength:'备注最多需要输入300个字符'}}" style="width: 98%">${(location.remark)! }</textarea>
			</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	procRentalHouseTab($("#isRentalHouse"));
	$("#isRentalHouse").click(function(){
		procRentalHouseTab($(this));
	})
	//是否添加标签页
	function procRentalHouseTab(doc){
		if(doc.attr("checked")){
			var dialogName = "<@s.property value='#parameters.dailogName[0]'/>";
			$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("出租房"));
			$("#"+dialogName).addTabToDialog(
					{
						title:"出租房",
						url:'${path}/plugin/dataManage/actualHouseTempManage/dispatch.action?mode=updateRentalHouse&dailogName=actualHouseTempDialog&tempClassName=actualHouseTemp&from=fromList&targetOrgId='+$("#targetClaimOrgId").val(),
						index:1
					}
				)
				if("${id}" == null){
					$("#"+dialogName).tabDialog('disable');
				}
		}else{
			var dialogName = "<@s.property value='#parameters.dailogName[0]'/>";
			$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("出租房"));
			
		}
	}
	$('#houseRightNumberDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d'
	});

	$('#landRightsNumbeDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d'
	});
	businessHouseToCurrentAddress();
	chooseCurrentAddressType();
	chooseCurrentAddressType();
	$("#building").mouseout(businessHouseToCurrentAddress);
	$("#addressTypeId").change(chooseCurrentAddressType);
	$("#houseSource").change(chooseCurrentAddressType);
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					  if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"
						 });
                     	return;
                     }
					},
				  error: function(XMLHttpRequest, textStatus, errorThrown){
      	      		alert("提交错误");
      	  		 }
			});
		},
		rules:{
			"location.address":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.remark":{
				maxlength:200
			}
		},
		messages:{
			"location.address":{
				exculdeParticalChar:"房屋信息地址只能输入字母，数字和中文字符",
				minlength:$.format("房屋信息地址至少需要输入{0}个字符"),
				maxlength:$.format("房屋信息地址最多只能输入{0}个字符")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}
		}
	});
});	
function businessHouseToCurrentAddress(){
	if($("#currentAddressType").find("option:selected").attr("internalId")=='<@s.property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>') {
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
		$("#address").val(currentAddress);
	} else {
		$("#community").val("");
		$("#block").val("");
		$("#unit").val("");
		$("#room").val("");
	}
}
	// 房屋地址
	function chooseCurrentAddressType(){
		$("#addressTypeId").val($("#currentAddressType").find("option:selected").attr("id"));
		if($("#currentAddressType").find("option:selected").attr("internalId")==<@s.property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			$("#building").show();
			$("#otherAddress").hide();
			$("#address").val("");
		} else {
			$("#building").hide();
			$("#otherAddress").show();
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
		}
	}

	// 房屋来源
	function chooseHouseSource(){
		if($("#houseSource").find("option:selected").attr("internalId")==<@s.property value="@com.tianque.domain.property.CurrentHouseSource@OWN_PROPERTY"/>) {
			$("#ownPropertyChange").show();
			$("#rentalBuildingChange").hide();
			$("#ownProperty").attr("disabled",false);
			$("#rentalBuildings").attr("disabled",true);
		} else {
			$("#ownPropertyChange").hide();
			$("#rentalBuildingChange").show();
			$("#ownProperty").attr("disabled",true);
			$("#rentalBuildings").attr("disabled",false);
		}
	}

	function procCurrentAddressType(data) {
		$("#houseId").val(data.id);
		$("#addressTypeId").val(data.addressType.id);
		$("#currentAddressType").val(data.addressType.id);
		if($("#currentAddressType").find("option:selected").attr("internalId")==<@s.property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			$("#building").show();
			$("#otherAddress").hide();
			$("#community").val(data.community);
			$("#block").val(data.block);
			$("#unit").val(data.unit);
			$("#room").val(data.room);
			$("#address").val(data.community+"小区"+data.block+"幢"+data.unit+"单元"+data.room+"室");
		} else {
			$("#building").hide();
			$("#otherAddress").show();
			$("#address").val(data.address);
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
		}
	}


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

	$("#certificateType").change(function(){
		if($("#certificateType").find("option:selected").attr("internalId")==<@s.property value="@com.tianque.domain.property.LettingcertificateType@IDENTITY_CARD"/>) {
			$("#certificateNumbe").rules("add", {
				idCard: true,
				messages:{idCard:'请输入一个合法的身份证号码'}
			});
		}else{
			$("#certificateNumbe").rules("remove","idCard");
		}
	});

jQuery.validator.addMethod("community", function(value, element){
	if(value==null||value==undefined||value==""){
  		return false;
  	}else{
  		return true;
  	}
});

jQuery.validator.addMethod("address", function(value, element){
	if(value==null||value==undefined||value==""){
  			return false;
  		}else{
  			return true;
  		}
});

jQuery.validator.addMethod("exsistedHouseCode", function(value, element){
	var flag =true;
	if($("#isUseFrom").val() == null || $("#isUseFrom").val() == "") {
		return flag;
	}
	if(value==null||value==undefined||value==""){return true;}
	$.ajax({
		async: false ,
		url:"${path}/baseinfo/actualHouseManage/hasDuplicateHouseInfo.action",
	   	data:{
		"location.houseCode":$('#houseCode').val(),
		"orgId":$('#currentOrgId').val(),
		"mode":$('#mode').val(),
		"location.houseType.id":$('#houseTypeId').val(),
		"location.id":$("#mode").val() == "add"?"-1":$('#locationId').val()
        },
		success:function(responseData){
			flag = !eval(responseData);
		}
	});
	return flag;
});

jQuery.validator.addMethod("isHouseRightNumberDate", function(value, element){
	if($('#houseRightNumberDate').val()!=null&&$('#houseRightNumberDate').val()!=""
			&& $('#landRightsNumbeDate').val()!=null&&$('#landRightsNumbeDate').val()!=""){
		if($('#landRightsNumbeDate').val() > $('#houseRightNumberDate').val()){
	           return false;
			}else {
				return true;
		}
	}
	return true;
});
jQuery.validator.addMethod("isLargerThanNow", function(value, element){
	if($('#builtYear').val()!=null&&$('#builtYear').val()!=""){
		if($('#builtYear').val()><@s.date name="@java.util.Calendar@getInstance().getTime()" format="yyyy"/>){
			return false;
		}
	}
	return true;
});
$("#houseCode").blur(function(){
		$.ajax({                             
			url:'${path}/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action',
			data:{"searchHouseInfoVo.organization.id":getCurrentOrgId(),"searchHouseInfoVo.houseCode":$('#houseCode').val()},
			async:false,
			success:function(data){
				if(data==null){
					$(':input','#maintainForm')
					 .not(':button, :submit, :reset, :hidden')
					 .not('#houseCode')
					 .not('#isRentalHouse')
					 .not('#locationOrgName')
					 .not('#mode')
					 .val('')
					 .removeAttr('disabled')
					 .removeAttr('checked')
					 .removeAttr('selected');
				}
				if(data!=null&&data.isRentalHouse){
					isHouseCodeExist=false;
				}else{
						isHouseCodeExist=true;
						$.each(data,function(i,n){
							if(n!=null&& n.id){
								$("#maintainForm select[name='location."+i+".id']:visible:not('#houseCode')").val(n.id);
							}else{
								$("#maintainForm [name='location."+i+"']:visible:not('#houseCode')").val(n);
							}
						});
				}
			}
		});

});
jQuery.validator.addMethod("isHouseCodeExist", function(value, element){
	var isHouseCodeExist=true;
		if($("#mode").attr("value")=="add"){
			$.ajax({
				url:'${path}/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action',
				data:{"searchHouseInfoVo.organization.id":getCurrentOrgId(),"searchHouseInfoVo.houseCode":$('#houseCode').val()},
				async:false,
				success:function(data){
					if(data!=null&&data.isRentalHouse){
						isHouseCodeExist=false;
					}else{
						isHouseCodeExist=true;
					}
				}
			});
		}
	return isHouseCodeExist;
});

jQuery.validator.addMethod("decimal", function(value, element) {
    var decimal = /^-?\d+(\.\d{1,2})?$/;
    if (value==""){
	     return true;
	 }
    if(value>=0){
    	return this.optional(element) || (decimal.test(value));
    }
    return false;
});
</script>