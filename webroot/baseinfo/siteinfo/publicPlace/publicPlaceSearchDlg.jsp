<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchPublicPlaceForm">
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">场所名称：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchPublicPlaceVo.unitName" id="conditionPlaceName" class="form-txt" style="width:99%" maxlength="50"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">场所地址：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchPublicPlaceVo.placeAddress" id="conditionPlaceAddress" class="form-txt" style="width:99%" maxlength="50"/>
		</div>

        <div class="grid_5 lable-right">
            <label class="form-lbl">场所类别：</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="conditionCategory" name="searchPublicPlaceVo.category"class="form-txt">
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">备案登记号码：</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="conditionRegistrationNumber" name="searchPublicPlaceVo.registrationNumber" class="form-txt ">
        </div>
        
        <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="治安负责人：" name="memberLabelName"/>
			<jsp:param value="searchPublicPlaceVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="巡场记录：" name="recordLabelName"/>
			<jsp:param value="searchPublicPlaceVo.hasServiceRecord" name="recordSelectName"/>
		</jsp:include>
		
	    <div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" class="form-txt" >
				<option value="" selected="selected">全部</option>
				<option value="false">现在关注</option>
				<option value="true">曾经关注</option>
			</select>
		</div>
	<div class='clearLine'></div>
    <div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
	<div id="showMoreCtt" style="display: none">
		<div class="grid_5 lable-right">
			<label class="form-lbl">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionManager" name="searchPublicPlaceVo.manager" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">场所等级：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionPlaceLevel" name="searchPublicPlaceVo.placeLevel" class="form-txt" />
		</div>
		       
	    <div class="grid_5 lable-right">
			<label class="form-lbl">领取执照时间：从</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchPublicPlaceVo.licenseDate" id="conditionLicenseDate" readonly="readonly" class="form-txt" maxlength="18"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">到&nbsp;&nbsp;</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchPublicPlaceVo.endLicenseDate" id="endConditionLicenseDate" readonly="readonly" class="form-txt" maxlength="18"/>
		</div>
		
		<div class="grid_5 lable-right">
            <label class="form-lbl">开业时间 ：从</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionOpeningDate" name="searchPublicPlaceVo.openingDate" readonly="readonly"  class="form-txt" maxlength="11" />
	   </div>
	   <div class="grid_5 lable-right">
            <label class="form-lbl">到&nbsp;&nbsp;</label>
        </div>
	   <div class="grid_7">
            <input type="text" id="endConditionOpeningDate" name="searchPublicPlaceVo.endOpeningDate" readonly="readonly" class="form-txt" maxlength="11" />
	   </div>
		
		
		<div class="grid_5 lable-right">
            <label class="form-lbl">总面积：从</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionTotalArea" name="searchPublicPlaceVo.totalArea" class="form-txt" maxlength="11" />
	   </div>
	   
	   <div class="grid_5 lable-right">
            <label class="form-lbl">到&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endConditionTotalArea" name="searchPublicPlaceVo.endTotalArea" class="form-txt" maxlength="11" />
	   </div>
		           
		 <div class="grid_5 lable-right">
			<label class="form-lbl">营业面积：从</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionOperationArea" name="searchPublicPlaceVo.operationArea" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">到&nbsp;&nbsp;</label>
		</div>
		<div class="grid_7">
			<input type="text" id="endConditionOperationArea" name="searchPublicPlaceVo.endOperationArea" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">包间数：从</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="conditionPrivateRoomCount" name="searchPublicPlaceVo.privateRoomCount"class="form-txt">
        </div>
        
        <div class="grid_5 lable-right">
            <label class="form-lbl">到&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="endConditionPrivateRoomCount" name="searchPublicPlaceVo.endPrivateRoomCount"class="form-txt">
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">核定人数：从</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionVouchedPeopleCount" name="searchPublicPlaceVo.vouchedPeopleCount" class="form-txt" maxlength="11" />
	   </div>
	   <div class="grid_5 lable-right">
            <label class="form-lbl">到&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endoCnditionVouchedPeopleCount" name="searchPublicPlaceVo.endVouchedPeopleCount" class="form-txt" maxlength="11" />
	   </div>
		               
		   <div class="grid_5 lable-right">
			<label class="form-lbl">通道口：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionPassageway" name="searchPublicPlaceVo.passageway" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">小件寄存处：</label>
        </div>
        <div class="grid_7">
        	<select name="searchPublicPlaceVo.cloakroom" id="conditionCloakroom" class="form-txt">
        		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM"></pop:OptionTag>
        	</select>
        	
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">内部结构：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionInnerStructure" name="searchPublicPlaceVo.innerStructure" class="form-txt" maxlength="11" />
	   </div>      
		<div class="grid_5 lable-right">
            <label class="form-lbl">周围环境：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionSurrounding" name="searchPublicPlaceVo.surrounding" class="form-txt" maxlength="11" />
	   </div>          
	   </div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){

	$("#showMoreBtn").toggle(
		function(){
			$("#publicPlaceDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#publicPlaceDialog").dialog( "option" , {height:320} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
	$('#conditionOpeningDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionOpeningDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#conditionLicenseDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionLicenseDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#endConditionOpeningDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOpeningDate").datepicker("option", "maxDate",date);
			}
		}
	});
	$('#endConditionLicenseDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionLicenseDate").datepicker("option", "maxDate",date);
			}
		}
	});


	
});

</script>
