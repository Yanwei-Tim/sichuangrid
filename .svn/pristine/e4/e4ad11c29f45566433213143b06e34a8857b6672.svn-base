<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchActualHouseForm" method="post">
<pop:token />
<div class="container container_24">
	<div class="grid_5 lable-right">
		<label class="form-lbl">房屋编号：</label>
	</div>
	<div class="grid_19">
    	<input type="text" name="searchHouseInfoVo.houseCode" id="conditionHouseCode" class="form-txt" maxlength="18" value=""/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">房屋地址：</label>
	</div>
	<div class="grid_19">
    	<input type="text" name="searchHouseInfoVo.address" id="conditionAddress" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">产权人类型：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionPropertyTypes" name="searchHouseInfoVo.propertyTypes.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">产权人名称：</label>
	</div>
	<div class="grid_7">
    	<input type="text" name="searchHouseInfoVo.name" id="conditionName" style="width:92%" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">产权人证件类型：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionCertificateType" name="searchHouseInfoVo.certificateType.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">产权人证件号码：</label>
	</div>
	<div class="grid_7">
    	<input type="text" name="searchHouseInfoVo.certificateNumbe" id="conditionCertificateNumbe" style="width:92%" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">房屋用途：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionHouseUses" name="searchHouseInfoVo.houseUses.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">建成年份：</label>
	</div>
	<div class="grid_1 lable-right">
        <label class="form-lbl"> 从</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="conditionBuiltYearFrom" name="searchHouseInfoVo.builtYearFrom" class="form-txt" />
    </div>
    <div class="grid_1" align="center">
        <label class="form-lbl">至&nbsp;</label>
    </div>
    <div class="grid_2">
        <input type="text" id="endConditionBuiltYearEnd" name="searchHouseInfoVo.builtYearEnd" class="form-txt" />
    </div>
    <div class="grid_5 lable-right">
		<label class="form-lbl">房屋结构：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionHouseStructure" name="searchHouseInfoVo.houseStructure.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">本户建筑面积：</label>
	</div>
	<div class="grid_1 lable-right">
        <label class="form-lbl"> 从</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="conditionHouseAreaFrom" name="searchHouseInfoVo.houseAreaFrom" class="form-txt" />
    </div>
    <div class="grid_1" align="center">
        <label class="form-lbl">至&nbsp;</label>
    </div>
    <div class="grid_2">
        <input type="text" id="endConditionHouseAreaEnd" name="searchHouseInfoVo.houseAreaEnd" class="form-txt" />
    </div>
    <div class="grid_1 lable-right">m<sup>2</sup></div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">是否出租：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionIsRentalHouse" name="searchHouseInfoVo.isRentalHouse" class="form-txt">
            <option selected></option>
            <option value="1" >是</option>
            <option value="0" >否</option>
        </select>
	</div>
		
	<div class="grid_5 lable-right">
   			<label class="form-lb1">住房用途：</label>
   	</div>
   	<div class="grid_7">
		<select id="houseUsesId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" />
		</select>
	</div>
   	
	<div class="grid_5 lable-right">
   			<label class="form-lb1">住房类型：</label>
   	</div>
   	<div class="grid_7">
	   	<select	id="houseIsRentalHouse" class="form-txt">
			<option value="">全部</option>
			<option value="1">出租房</option>
			<option value="0">非出租房</option>
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">居住人数：</label>
	</div>
	<div class="grid_7">
    	<input type="text" name="searchHouseInfoVo.memberNum" id="conditionMemberNum" style="width:92%" class="form-txt" maxlength="18"/>
	</div>
	
    <div class='clearLine'></div>
    <div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
			<div class="grid_5 lable-right">
				<label class="form-lbl">建筑物名称：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionBuildingName" name="searchHouseInfoVo.buildingName" class="form-txt" />
			</div>
	        <div class="grid_5 lable-right">
				<label class="form-lbl">建筑物用途：</label>
			</div>
			<div class="grid_7">
	            <select id="conditionBuildingUses" name="searchHouseInfoVo.buildingUses.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BUILDING_USES" />
	            </select>
	        </div>
	        <div class="grid_5 lable-right">
				<label class="form-lbl">物业管理单位名称：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionPropertyName" name="searchHouseInfoVo.propertyName" class="form-txt" />
			</div>
	        <div class="grid_5 lable-right">
				<label class="form-lbl">房屋来源：</label>
			</div>
			<div class="grid_3">
	            <select name="searchHouseInfoVo.houseSource.id" class="form-txt" >
						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" showInternalId="true" />
				</select>
	        </div>
	        <div class="grid_4">
	            <select id="conditionOwnProperty" name="searchHouseInfoVo.ownProperty.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@OWN_PROPERTY" />
	            </select>
	        </div>
	        <div class="grid_5 lable-right">
				<label class="form-lbl">房屋凭证：</label>
			</div>
			<div class="grid_7">
				<select id="conditionHousingVouchers" name="searchHouseInfoVo.housingVouchers.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSING_VOUCHERS" />
	            </select>
			</div>
	        <div class="grid_5 lable-right">
				<label class="form-lbl">房屋权证号：</label>
			</div>
			<div class="grid_7">
	            <input type="text" id="conditionHouseRightNumber" name="searchHouseInfoVo.houseRightNumber" class="form-txt" />
	        </div>
	        <div class="grid_4 lable-right">
				<label class="form-lbl">发证时间：</label>
			</div>
			<div class="grid_1 lable-left">
		        <label class="form-lbl"> 从</label>
		    </div>
		    <div class="grid_3 lable-left">
		        <input type="text" id="conditionHouseRightNumberDateFrom" name="searchHouseInfoVo.houseRightNumberDateFrom" class="form-txt" />
		    </div>
		    <div class="grid_1" align="center">
		        <label class="form-lbl">至&nbsp;&nbsp;</label>
		    </div>
		    <div class="grid_3">
		        <input type="text" id="endConditionHouseRightNumberDateEnd" name="searchHouseInfoVo.houseRightNumberDateEnd" class="form-txt" />
		    </div>
		    <div class="grid_5 lable-right">
				<label class="form-lbl">土地凭证：</label>
			</div>
			<div class="grid_7">
				<select id="conditionLandDocuments" name="searchHouseInfoVo.landDocuments.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LAND_DOCUMENTS" />
	            </select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">土地权证号：</label>
			</div>
			<div class="grid_7">
	            <input type="text" id="conditionLandRightsNumbe" name="searchHouseInfoVo.landRightsNumbe" class="form-txt" />
	        </div>
	        <div class="grid_4 lable-right">
				<label class="form-lbl">发证时间：</label>
			</div>
			<div class="grid_1 lable-left">
		        <label class="form-lbl"> 从</label>
		    </div>
		    <div class="grid_3 lable-left">
		        <input type="text" id="conditionLandRightsNumbeDateFrom" name="searchHouseInfoVo.landRightsNumbeDateFrom" readonly class="form-txt" />
		    </div>
		    <div class="grid_1" align="center">
		        <label class="form-lbl">至&nbsp;&nbsp;</label>
		    </div>
		    <div class="grid_3">
		        <input type="text" id="endConditionLandRightsNumbeDateEnd" name="searchHouseInfoVo.landRightsNumbeDateEnd" readonly class="form-txt" />
		    </div>
        </div>
   </div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#actualHouseDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#actualHouseDialog").dialog( "option" , {height:400} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);

	$('#conditionHouseRightNumberDateFrom').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionHouseRightNumberDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionHouseRightNumberDateEnd').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionHouseRightNumberDateFrom").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionLandRightsNumbeDateFrom').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionLandRightsNumbeDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionLandRightsNumbeDateEnd').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionLandRightsNumbeDateFrom").datepicker("option", "maxDate",date);
			}
		}
	});

});
</script>
