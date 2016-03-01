<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchHospitalForm">
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">医院名称：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchHospitalVo.hospitalName" id="hospitalName" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">医院地址：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchHospitalVo.address" id="conditionAddress" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">所属单位：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchHospitalVo.affiliatedUnit" id="conditionAffiliatedUnit" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">医院院长：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionManager" name="searchHospitalVo.manager" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionTelephone" name="searchHospitalVo.telephone" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionMobileNumber" name="searchHospitalVo.mobileNumber" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">电子邮箱：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionEmail" name="searchHospitalVo.email" class="form-txt" />
		</div>    
		
		<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="治安负责人：" name="memberLabelName"/>
			<jsp:param value="searchHospitalVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="巡场记录：" name="recordLabelName"/>
			<jsp:param value="searchHospitalVo.hasServiceRecord" name="recordSelectName"/>
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
		<div class="grid_4 lable-right">
			<label class="form-lbl">医院性质：</label>
		</div>
		<div class="grid_7">
			<select name="searchHospitalVo.kind.id" id="conditionKind"
				class="form-select">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND"
					defaultValue="" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">医院类型：</label>
		</div>
		<div class="grid_7">
			<select name="searchHospitalVo.type.id" id="conditionType"
				class="form-select">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE"
					defaultValue="" />
			</select>
		</div>
	</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){

	$("#showMoreBtn").toggle(
		function(){
			$("#internetBarDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#internetBarDialog").dialog( "option" , {height:320} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	$('#logOutTimeStart').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#logOutTimeStart").datepicker("option", "minDate",date);
			}
		}
	});

	$('#logOutTimeEnd').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#logOutTimeEnd").datepicker("option", "maxDate",date);
			}
		}
	});
	
});

</script>
