<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchRentalHouseForm" method="post">
<pop:token />
<div class="container container_24">
	<div class="grid_5 lable-right">
		<label class="form-lbl">房屋编号：</label>
	</div>
	<div class="grid_19">
    	<input type="text" name="searchHouseInfoVo.houseCode" id="conditionHouseCode" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">房屋地址：</label>
	</div>
	<div class="grid_19">
    	<input type="text" name="searchHouseInfoVo.address" id="conditionAddress" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">隐患程度：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionHiddenDangerLevel" name="searchHouseInfoVo.hiddenDangerLevel.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出租用途：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionUsage" name="searchHouseInfoVo.usage.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出租人类型：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionLessorType" name="searchHouseInfoVo.lessorType.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LESSOR_TYPE" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出租人：</label>
	</div>
	<div class="grid_7">
    	<input type="text" name="searchHouseInfoVo.rentalPerson" id="conditionRentalPerson" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">登记时间：</label>
	</div>
	<div class="grid_7">
    	<input type="text" name="searchHouseInfoVo.createDate" id="conditionCreateDate" readonly="readonly" class="form-txt" maxlength="18"/>
	</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">租用间数：</label>
	</div>
	<div class="grid_7">
    	<input type="text" name="searchHouseInfoVo.roomNumber" id="conditionRoomNumber" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出租房性质：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionLettingHouseProperty" name="searchHouseInfoVo.rentalProperty.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_PROPERTY" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出租房类别：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionType" name="searchHouseInfoVo.type.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">限制人数：</label>
	</div>
	<div class="grid_1 lable-left">
        <label class="form-lbl">从</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="conditionLimitPersonsFrom" name="searchHouseInfoVo.limitPersonsFrom" class="form-txt" />
    </div>
    <div class="grid_2 lable-right">
        <label class="form-lbl">至&nbsp;&nbsp;</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="endConditionLimitPersonsTo" name="searchHouseInfoVo.limitPersonsTo" class="form-txt" />
    </div>
    <div class="grid_5 lable-right">
		<label class="form-lbl">实住人数：</label>
	</div>
	<div class="grid_1 lable-left">
        <label class="form-lbl">从</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="conditionRealityPersonsFrom" name="searchHouseInfoVo.realityPersonsFrom" class="form-txt" />
    </div>
    <div class="grid_2 lable-right">
        <label class="form-lbl">至&nbsp;&nbsp;</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="endConditionRealityPersonsTo" name="searchHouseInfoVo.realityPersonsTo" class="form-txt" />
    </div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">租赁备案证号：</label>
	</div>
	<div class="grid_7">
    	<input type="text" name="searchHouseInfoVo.houseFileNum" id="conditionHouseFileNum" class="form-txt" maxlength="18"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">管理类别：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionMangerTypes" name="searchHouseInfoVo.mangerTypes.id" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MANGER_TYPES" />
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出租时间：</label>
	</div>
	<div class="grid_1 lable-left">
        <label class="form-lbl">从</label>
    </div>
    <div class="grid_6 lable-left">
        <input type="text" id="conditionLessorDateFrom" name="searchHouseInfoVo.lessorDateFrom" readonly class="form-txt" />
    </div>
    <div class="grid_5 lable-right">
        <label class="form-lbl">至：</label>
    </div>
    <div class="grid_7 lable-left" >
        <input type="text" id="endConditionLessorDateEnd" name="searchHouseInfoVo.lessorDateEnd" readonly class="form-txt" />
    </div>
    <div class="grid_5 lable-right">
		<label class="form-lbl">出租间数：</label>
	</div>
	<div class="grid_1 lable-left">
        <label class="form-lbl">从</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="conditionRoomNumberFrom" name="searchHouseInfoVo.roomNumberFrom" class="form-txt" />
    </div>
    <div class="grid_2 lable-right">
        <label class="form-lbl">至&nbsp;&nbsp;</label>
    </div>
    <div class="grid_2 lable-left">
        <input type="text" id="endConditionRoomNumberEnd" name="searchHouseInfoVo.roomNumberEnd" class="form-txt" />
    </div>
    <div class="grid_5 lable-right">
		<label class="form-lbl">出租用途：</label>
	</div>
	<div class="grid_7">
		<select id="usageId" class="form-txt" >
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" />
		</select>
	</div>
	
    <div class="grid_5 lable-right">
		<label class="form-lbl">有无消防通道：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionIsFireChannels" name="searchHouseInfoVo.isFireChannels" class="form-txt">
    	    <option value="" selected></option>
            <option value="1">有</option>
            <option value="0">无</option>
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">有无安全通道：</label>
	</div>
	<div class="grid_7">
    	<select id="conditionIsSafetyChannel" name="searchHouseInfoVo.isSafetyChannel" class="form-txt">
    	   <option value="" selected></option>
            <option value="1">有</option>
            <option value="0">无</option>
        </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">注销状态：</label>
	</div>
	<div class="grid_7">
		<select id="isEmphas" name="searchHouseInfoVo.isEmphasis" class="form-txt">
			<option value="" selected="selected">全部</option>
			<option value="0">未注销</option>
			<option value="1">已注销</option>
		</select>
	</div>
	
	<div class="grid_6 lable-right">
		<label class="form-lbl">是否签治安责任保证书：</label>
	</div>
	<div class="grid_6">
    	<select id="conditionIsSignGuarantee" name="searchHouseInfoVo.isSignGuarantee" class="form-txt">
    	    <option value="" selected></option>
            <option value="1">是</option>
            <option value="0" >否</option>
        </select>
	</div>
	
	 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="治安负责人：" name="memberLabelName"/>
		<jsp:param value="searchHouseInfoVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="巡场记录：" name="recordLabelName"/>
		<jsp:param value="searchHouseInfoVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
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
			<div class="grid_4">
	            <select name="searchHouseInfoVo.houseSource.id" class="form-txt" >
						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" showInternalId="true" />
				</select>
	        </div>
	        <div class="grid_3">
	            <select id="conditionOwnProperty" name="searchHouseInfoVo.ownProperty.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@OWN_PROPERTY" />
	            </select>
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
		    	<input type="text" name="searchHouseInfoVo.name" id="conditionName" class="form-txt" maxlength="18"/>
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
				<label class="form-lbl">房屋结构：</label>
			</div>
			<div class="grid_7">
		    	<select id="conditionHouseStructure" name="searchHouseInfoVo.houseStructure.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
		        </select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">建成年份：</label>
			</div>
			<div class="grid_1 lable-left">
		        <label class="form-lbl"> 从</label>
		    </div>
		    <div class="grid_3 lable-left">
		        <input type="text" id="conditionBuiltYearFrom" name="searchHouseInfoVo.builtYearFrom" class="form-txt" />
		    </div>
		    <div class="grid_1" align="center">
		        <label class="form-lbl">至&nbsp;&nbsp;</label>
		    </div>
		    <div class="grid_2">
		        <input type="text" id="endConditionBuiltYearEnd" name="searchHouseInfoVo.builtYearEnd" class="form-txt" />
		    </div>
		    <div class="grid_5 lable-right">
				<label class="form-lbl">本户建筑面积：</label>
			</div>
			<div class="grid_1 lable-left">
		        <label class="form-lbl">从</label>
		    </div>
		    <div class="grid_2 lable-left">
		        <input type="text" id="conditionHouseAreaFrom" name="searchHouseInfoVo.houseAreaFrom" class="form-txt" />
		    </div>
		    <div class="grid_1" align="center">
		        <label class="form-lbl">至</label>
		    </div>
		    <div class="grid_2">
		        <input type="text" id="endConditionHouseAreaEnd" name="searchHouseInfoVo.houseAreaEnd" class="form-txt" />
		    </div>
		    <div class="grid_1">m<sup>2</sup></div>
	        <div class="grid_5 lable-right">
				<label class="form-lbl">月租金：</label>
			</div>
			<div class="grid_1 lable-left">
		        <label class="form-lbl"> 从</label>
		    </div>
		    <div class="grid_3 lable-left">
		        <input type="text" id="conditionRentMonthFrom" name="searchHouseInfoVo.rentMonthFrom" class="form-txt" />
		    </div>
		    <div class="grid_1" align="center">
		        <label class="form-lbl">至&nbsp;&nbsp;</label>
		    </div>
		    <div class="grid_2">
		        <input type="text" id="endConditionRentMonthEnd" name="searchHouseInfoVo.rentMonthEnd" class="form-txt" />
		    </div>
        </div>
   </div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#rentalHouseDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#rentalHouseDialog").dialog( "option" , {height:400} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	$('#conditionLessorDateFrom').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionLessorDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionLessorDateEnd').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionLessorDateFrom").datepicker("option", "maxDate",date);
			}
		}
	});
	
	$('#conditionCreateDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d'
	});

});
</script>
