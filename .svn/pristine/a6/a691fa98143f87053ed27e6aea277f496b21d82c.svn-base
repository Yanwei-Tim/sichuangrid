<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
	<input id="mode" type="hidden" name="mode" value="${(mode)!}" />
	<input type="hidden" name="contextId" value="${(contextId)!}" />
	<input id="locationId"	type="hidden" name="location.id" value="${(location.id)!}" />
	<input name="isSubmit" id="isSubmit" type="hidden" value="">
<input type="hidden" name="location.isEmphasis" value="${(location.isEmphasis)!}" />
<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${(ajaxUrl)!}">
<input id="businessHouse" type="hidden" value="723-2"/>
<input id="other" type="hidden" value=""/>
<input id="ownliveHouse" type="hidden" value=""/>
<input id="rentalHouse" type="hidden" value=""/>
<input id="companyHouse" type="hidden" value=""/>
<input id="idleHouse" type="hidden" value=""/>
<input id="otherHouse" type="hidden" value=""/>
<input name="cacheId.locationId" type="hidden" value="${(cacheId.locationId)! }">

<div class="container container_24" >
	<div id="actualHouse" class="container container_24">
			<div class="grid_4 lable-right">
				<label class="form-lb1">出租用途3：</label>
			</div>
			<div class="grid_7">
				<select name="location.usage.id" id="usage" class="form-txt" >
 					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" defaultValue="${(location.usage.id)!}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">租赁备案证号：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.houseFileNum" id="houseFileNum" maxlength="70" value="${(location.houseFileNum)!}"
					class="form-txt {minlength:2,maxlength:70,messages:{minlength:'租赁备案证号至少需要输入2个字符',maxlength:'租赁备案证号最多需要输入70个字符'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">出租人类型：</label>
			</div>
			<div class="grid_7">
				<select name="location.lessorType.id" id="lessorType" class="form-txt" >
 					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@LESSOR_TYPE" defaultValue="${(location.lessorType.id)!}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lb1">出租人：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.rentalPerson" id="name" maxlength="20" value="${(location.rentalPerson)!}"
					class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'出租人至少需要输入2个字符',maxlength:'出租人最多需要输入20个字符'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">证件类型：</label>
			</div>
			<div class="grid_7">
				<select name="location.rentalCertificateType.id" id="certificateType" class="form-txt" >
 					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" showInternalId=true defaultValue="${(location.rentalCertificateType.id)!}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">证件号码：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.rentalCertificateNumbe" id="certificateNumbe" maxlength="20" value="${(location.rentalCertificateNumbe)!}"
					class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'证件号码至少需要输入2个字符',maxlength:'证件号码最多需要输入20个字符'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">联系电话：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.rentalTelephone" id="propertyPersonTel" maxlength="20" value="${(location.rentalTelephone)!}"
					class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">联系手机：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.rentalMobileNumber" id="propertyPersonMobile" maxlength="11" value="${(location.rentalMobileNumber)!}"
					class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
	 			<label class="form-lb1">地址：</label>
	 		</div>
	 		<div class="grid_18">
	 			<input type="text" id="lessorAddress"  name="location.lessorAddress" value="${(location.lessorAddress)!}"  maxlength="60" style="width: 99%"
	 				class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:60,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'地址至少需要输入2个字符',maxlength:'地址最多需要输入60个字符'}}"  />
	 		</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lb1">隐患程度：</label>
			</div>
			<div class="grid_7">
				<select name="location.hiddenDangerLevel.id" id="hiddenDangerLevel" class="form-txt {required:true,maxlength:30,messages:{required:'隐患程度不能为空'}}" >
 					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" showInternalId=true defaultValue="${(location.hiddenDangerLevel.id)!}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">管理类别：</label>
			</div>
			<div class="grid_7">
				<select name="location.mangerTypes.id" id="mangerTypes" class="form-txt" >
 					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@MANGER_TYPES" showInternalId=true defaultValue="${(location.mangerTypes.id)!}" />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">出租间数：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.roomNumber" id="roomNumber" maxlength="9" value="${(location.roomNumber)!}"
					class="form-txt {positiveInteger:true,messages:{positiveInteger:'出租间数为正整数'}}" />
			</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1">出租时间：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.lessorDate" id="lessorDate" maxlength="50" value="<@s.date name="location.lessorDate" format="yyyy-MM-dd" />" class="form-txt" readonly/>
			</div>
				<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">月租金：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.rentMonth" id="rentMonth" maxlength="9" value="${(location.rentMonth)!}"
					class="form-txt {number:true,messages:{number:'请输入合法的数字'}}" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lb1"></label>
			</div>
			<div class="grid_20">
				<input type="checkbox" id="isSignGuarantee" name="location.isSignGuarantee" value="1"   <@s.if test='1==location.isSignGuarantee'>checked="checked"</@s.if> />
				<label class="form-check-text">是否签订治安责任保证书 </label>&nbsp;

				<input type="checkbox" id="isSafetyChannel" name="location.isSafetyChannel" value="1"   <@s.if test='1==location.isSafetyChannel'>checked="checked"</@s.if> />
				<label class="form-check-text">有无安全通道</label>&nbsp;

				<input type="checkbox" id="isFireChannels" name="location.isFireChannels" value="1"   <@s.if test='1==location.isFireChannels'>checked="checked"</@s.if> />
				<label class="form-check-text">有无消防通道</label>
			</div>
			<div class='clearLine'>&nbsp;</div>
	</div>
</div>
<script type="text/javascript">
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
$(function(){
	// 房屋地址
	function chooseCurrentAddressType(){
		initActualHouseCurrentAddress();
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

	// 根据房屋编号自动获取实有房屋信息
	function searchHoseInfoByCode(){
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/actualHouseManage/getlocationByHouseCode.action",
		   	data:{
				"searchlocationVo.houseCode":$('#houseCode').val()
	        },
			success:function(responseData){
				if(null != responseData) {
					procCurrentAddressType(responseData);
				} else {
					$("#address").val("");
					$("#community").val("");
					$("#block").val("");
					$("#unit").val("");
					$("#room").val("");
				}
			}
		});
	}
	function procCurrentAddressType(responseData) {
		$("#locationId").val(responseData.id);
		$("#addressTypeId").val(responseData.addressType.id);
		$("#currentAddressType").val(responseData.addressType.id);
		if($("#currentAddressType").find("option:selected").attr("internalId")==<@s.property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			$("#building").show();
			$("#otherAddress").hide();
			$("#community").val(responseData.community);
			$("#block").val(responseData.block);
			$("#unit").val(responseData.unit);
			$("#room").val(responseData.room);
			$("#address").val(responseData.community+"小区"+responseData.block+"幢"+responseData.unit+"单元"+responseData.room+"室");
		} else {
			$("#building").hide();
			$("#otherAddress").show();
			$("#address").val(responseData.address);
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
		}
	}

	$("#currentAddressType").change(chooseCurrentAddressType);
	$("#houseCode").change(searchHoseInfoByCode);
	chooseCurrentAddressType();

	$("#houseCode").houseAutoComplete({
		searchLevel:"houseCode",
		select:function(event,ui){
			renderlocationForHouseCode(ui.item);
		}});

	function renderlocationForHouseCode(house){
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

	$('#lessorDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#lessorDate").datepicker("option", "minDate",date);
			}
		}
	});
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
})

jQuery.validator.addMethod("community", function(value, element){
	var businessHouse = $("#businessHouse").val();
	if(businessHouse!=null&& $("#currentAddressType").val()== businessHouse.split("-")[0]){
		if(businessHouse.split("-")[1]==<@s.property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
			if(value==null||value==undefined||value==""){
    			return false;
    		}else{
    			return true;
    		}
		}
	}
  return true;
});

jQuery.validator.addMethod("address", function(value, element){
	var other = $("#other").val();
	if(other!=null&& $("#currentAddressType").val()== other.split("-")[0]){
		if(other.split("-")[1]==<@s.property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
			if(value==null||value==undefined||value==""){
    			return false;
    		}else{
    			return true;
    		}
		}
	}
  return true;
});

jQuery.validator.addMethod("exsistedHouseCode", function(value, element){
	var flag =true;
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:"${path}/baseinfo/rentalHouseManage/hasDuplicatelocation.action",
	   	data:{
		"location.houseCode":$('#houseCode').val(),
		"orgId":$('#currentOrgId').val(),
		"mode":$('#mode').val(),
		"location.id":$("#mode").val() == "add"?"-1":$('#locationId').val()
        },
		success:function(responseData){
			flag = !eval(responseData);
		}
	});
	return flag;
});

function initActualHouseCurrentAddress(){
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
            	   if(data.internalId==<@s.property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
        			   $("#businessHouse").val(data.id+"-"+data.internalId);
        	   		}else{
        	   		   $("#other").val(data.id+"-"+data.internalId);
        	   		}
        	   }
			}
        }
	});
}
</script>