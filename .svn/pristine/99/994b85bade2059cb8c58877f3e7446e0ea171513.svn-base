<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
	<div id="houseCodeDiv1" <s:if test="!houseInfos.isHaveHouse">style="display: none;"</s:if>>
		<div class="grid_4 lable-right">
			<label class="form-lb1">房屋编号：</label>
		</div>
		<div class="grid_10">
		<s:if test="houseInfos.houseCode!=null">
			<input type="text" id="houseCode"  name="houseInfos.houseCode" value="${houseInfos.houseCode}"  maxlength="50" style="width: 99%"
				class="form-txt " />
		</s:if>
		<s:else>
			<input type="text" id="houseCode"  name="houseInfos.houseCode" value="${houseInfos.houseCode}"  maxlength="50" style="width: 99%"
				class="form-txt {isCodeValidate:true,messages:{isCodeValidate:'只能输入英文和数字'}}" />
		</s:else>
		</div>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div id="houseCodeDiv2" <s:if test="!houseInfos.isHaveHouse">style="display: none;"</s:if>>
		<div class="grid_4 lable-right">
					<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_3" style="display: none;">
			<select name="houseInfos.addressType.id" id="currentAddressType" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE" defaultValue="${houseInfos.addressType.id}" showInternalId="true" notNull="true"  />
			</select>
			<input type="hidden" name="houseInfos.id" id="houseId" value="${houseInfos.id}"/>
			<input type="text" id="community"  name="houseInfos.community" value="${houseInfos.community}" />
		</div><!-- 
		<div id="building" >
			<div class="grid_3">
				<input type="text" id="community"  name="houseInfos.community"  maxlength="20"  value="${houseInfos.community}" style="width: 93%" 
				class="form-txt {community:true,messages:{community:'房屋地址不能为空'}}" />
			</div>
			<div class="grid_2">
				<label class="form-lbl">&nbsp;小区</label>
			</div>
			<div class="grid_2">
				<input type="text" id="block"  name="houseInfos.block"  maxlength="8"  value="${houseInfos.block}" style="width: 93%"	class="form-txt" />
			</div>
			<div class="grid_1">
			     <label class="form-lbl">&nbsp;幢</label>
			</div>
			<div class="grid_2">
				 <input type="text" id="unit"  name="houseInfos.unit"  maxlength="6"  value="${houseInfos.unit}" style="width: 93%"	class="form-txt" />
			</div>
			<div class="grid_2">
				<label class="form-lbl">&nbsp;单元</label>
			</div>
			<div class="grid_2">
				<input type="text" id="room"  name="houseInfos.room"  maxlength="10"  value="${houseInfos.room}" style="width: 93%"	class="form-txt" />
			</div>
			<div class="grid_1">
				<label class="form-lbl">&nbsp;室</label>
			</div>
		</div>
	</div>
	<div id="otherAddress" class="grid_15" style="display:none">
			<input type="text" id="currentAddress" name="houseInfos.address"  maxlength="50"  value="${houseInfos.address }" style="width: 99%"
				class="form-txt {currentAddress:true,messages:{currentAddress:'房屋地址不能为空'}}" 
			/>
	</div> -->
	<div class="grid_7">
		<input type="text"  id="_address" name="houseInfos.address"  maxlength="50"  value="${houseInfos.address}"
			class="form-txt {currentAddress:true,messages:{currentAddress:'请输入房屋准确地址'}}" onblur="$('#community').val(this.value)" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">房产证地址：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="houseInfos.houseAddress" id="houseAddress" maxlength="60" value="${houseInfos.houseAddress}"
			class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:60,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'房产证地址至少需要输入2个字符',maxlength:'房产证地址最多需要输入60个字符'}}" />
	</div>
<script>

function chooseCurrentAddressType(){
	var currentAddressType = $("#currentAddressType").find("option:selected").attr("internalId");
	if(currentAddressType==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
		$("#building").show();
		$("#otherAddress").hide();
	}else if(currentAddressType==<s:property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
		$("#building").hide();
		$("#otherAddress").show();
	}
}

function procCurrentAddressType(data) {
	$("#houseId").val(data.id);
	//$("#addressTypeId").val(data.addressType.id);
	$("#currentAddressType").val(data.addressType.id);
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			$("#building").show();
			$("#otherAddress").hide();
			$("#community").val(data.community);
			$("#block").val(data.block);
			$("#unit").val(data.unit);
			$("#room").val(data.room);
			$("#address").val(data.community+"小区"+data.block+"幢"+data.unit+"单元"+data.room+"室");
		} else {
			$("#otherAddress").show();
			$("#currentAddress").val(data.address);
			$("#building,#community,#block,#unit,#room").hide();
		}
}

$(function(){
	jQuery.validator.addMethod("currentAddress", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
			var livingHouse=value;
			return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
		}
		return true;
	});

	jQuery.validator.addMethod("community", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			var livingHouse=$("#community").val();
			return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
		}
		return true;
	});
	

	
	function businessHouseToCurrentAddress(){
		var currentAddressType = $("#currentAddressType").find("option:selected").attr("internalId");
		if(currentAddressType==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
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
			$("#community,#block,#unit,#room").val("");
		}
	}
	
	function renderHouseInfoFromCBUR(house){
		$("#community").val(house.community);
		$("#block").val(house.block);
		$("#unit").val(house.unit);
		$("#room").val(house.room);
		$("#houseCode").val(house.houseCode);
		businessHouseToCurrentAddress();
		$("#houseId").val(house.houseId);
	}

	function renderHouseInfoFromADD(house){
		$("#community,#block,#unit,#room").val("");
		$("#currentAddress").val(house.address);
		$("#houseCode").val(house.houseCode);
		$("#houseId").val(house.houseId);
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
	
	function initActualHouse() {
		$.ajax({
			async: false ,
			url:"${path }/sysadmin/propertyManage/findPropertyDictByDomainName.action",
		   	data:{
				"propertyDomain.domainName":"住房类别"
	        },
			success:function(responseData){
	    	   if(responseData!=null&&responseData.length>0){
	        	   for(var i=0;i<responseData.length;i++){
	            	   var data = responseData[i];
	            	   if(data.internalId==<s:property value="@com.tianque.domain.property.HouseInfoType@ACTUAL_HOUSE"/>){
	        			   $("#actualHouseTypeId").val(data.id);
	        	   		}
	        	   }
				}
	        }
		});
	}
	
	function renderHouseInfoForHouseCode(house){
		$("#houseId").val(house.houseId);
		//$("#addressTypeId").val(house.addressType.id);
		$("#currentAddressType").val(house.addressType.id);
		$("#community").val(house.community);
		$("#block").val(house.block);
		$("#unit").val(house.unit);
		$("#room").val(house.room);
		procCurrentAddressType(house);
		$("#houseCode").val(house.houseCode);
	}
	

	$("#block").houseAutoComplete({
		searchLevel:"block",
		select:function(event,ui){
			renderHouseInfoFromCBUR(ui.item);
		},
		postData: {
			community :function(){return $("#community").val();}
		}
	});

	$("#unit").houseAutoComplete({
		searchLevel:"unit",
		select:function(event,ui){
			renderHouseInfoFromCBUR(ui.item);
		},
		postData: {
			community :function(){return $("#community").val();},
			block :function(){return $("#block").val();}
		}
	});

	$("#room").houseAutoComplete({
		searchLevel:"room",
		select:function(event,ui){
			renderHouseInfoFromCBUR(ui.item);
		},
		postData: {
			community :function(){return $("#community").val();},
			block :function(){return $("#block").val();},
			unit :function(){return $("#unit").val();}
		}
	});
	
// 	$("#houseCode").houseAutoComplete({
// 		searchLevel:"houseCode",
// 		select:function(event,ui){
// 			renderHouseInfoForHouseCode(ui.item);
// 		}
// 	});
	
	$("#currentAddressType").change(chooseCurrentAddressType);
	//$("#houseCode").change(searchHoseInfoByCode);
	
	initCurrentAddress();
	initActualHouse();

	$("#currentAddressType option:eq(1)").attr('selected', true);
	$("#addressTypeId").val($("#currentAddressType").find("option:selected").attr("id"));
});
</script>