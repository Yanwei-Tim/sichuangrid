<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchSuperiorVisitForm">
<pop:token />
<div id="search-condition-form" title="重点上访人员信息查询" 	class="container container_24">
	<div class="grid_5 lable-right">
		<label class="form-lbl">关注状态：</label>
	</div>
	<div class="grid_7">
		 <select id="isLock" name="searchSuperiorVisitVo.isEmphasis" class="form-txt">
		     <option value="-1" selected="selected">全部</option>
		     <option value="0">现在关注</option>
		     <option value="1">曾经关注</option>
		 </select>
    </div>
    <div class="grid_5 lable-right">
		<label class="form-lbl">死亡状态：</label>
	</div>
	<div class="grid_7">
	    <select id="isDeath" name="searchSuperiorVisitVo.isDeath" class="form-txt">
	        <option value="-1" selected="selected">全部</option>
	        <option value="0">正常</option>
	        <option value="1">已死亡</option>
	    </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" maxlength="18"
			name="searchSuperiorVisitVo.idCardNo" class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchSuperiorVisitVo.name"
			class="form-txt" maxlength="20" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出生日期：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchSuperiorVisitVo.birthdayStart"
			readonly="readonly" class="form-txt" id="conditionStartBirthday"/>
	</div>
	<div class="grid_1 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchSuperiorVisitVo.birthdayEnd"
			readonly="readonly" class="form-txt" id="conditionEndBirthday"/>
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">性别：</label>
	</div>
	<div class="grid_7">
		<select name="searchSuperiorVisitVo.gender.id" class="form-txt">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@GENDER" />
		</select>
	</div>
	
	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchSuperiorVisitVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchSuperiorVisitVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">上访状态：</label>
	</div>
	<div class="grid_7">
		<select name="searchSuperiorVisitVo.visitState.id" class="form-txt">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@SUPERIOR_VISIT_STATUS" />
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
			<div class="grid_5 lable-right">
				<label class="form-lbl">血型：</label>
			</div>
			<div class="grid_7">
				<select name="searchSuperiorVisitVo.bloodType.id" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
				</select>
			</div>

			<div class="grid_5 lable-right">
				<label class="form-lbl">民族：</label>
			</div>
			<div class="grid_7">
				<select name="searchSuperiorVisitVo.nation.id" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@NATION" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">政治面貌：</label>
			</div>
			<div class="grid_7">
				<select name="searchSuperiorVisitVo.politicalBackground.id" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">婚姻状况：</label>
			</div>
			<div class="grid_7">
				<select name="searchSuperiorVisitVo.maritalState.id" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"
						defaultValue="${population.maritalState.id}" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">文化程度：</label>
			</div>
			<div class="grid_7">
				<select name="searchSuperiorVisitVo.schooling.id" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_7">
				<select name="searchSuperiorVisitVo.career.id" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@CAREER" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">工作单位或就读学校：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="searchSuperiorVisitVo.workUnit" maxlength="50"
					class="form-txt" />
			</div>

			<div class="clearLine">&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">身高：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="searchSuperiorVisitVo.minStature" class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_6">
				<input type="text" name="searchSuperiorVisitVo.maxStature" class="form-txt" />
			</div>
			<div class="grid_1">cm</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">宗教信仰：</label>
			</div>
			<div class="grid_7">
				<select name="searchSuperiorVisitVo.faith.id" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@FAITH" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">电子邮箱：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="searchSuperiorVisitVo.email" maxlength="50"
					class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">联系手机：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="searchSuperiorVisitVo.mobileNumber" maxlength="11"
					class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">固定电话：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="searchSuperiorVisitVo.telephone" maxlength="20"
					class=" form-txt" />
			</div>

			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍地：</label>
			</div>
			<div class="grid_5">
				<select name="searchSuperiorVisitVo.province" class="form-txt" id="conditionProvince"></select>
			</div>
			<div class="grid_1">
				<label class="form-lbl">省</label>
			</div>
			<div class="grid_5">
				<select name="searchSuperiorVisitVo.city" class="form-txt" id="conditionCity"></select>
			</div>
			<div class="grid_1">
				<label class="form-lbl">市</label>
			</div>
			<div class="grid_5">
				<select name="searchSuperiorVisitVo.district" class="form-txt"  id="conditionDistrict"></select>
			</div>
			<div class="grid_1">
				<label class="form-lbl">县</label>
			</div>
			<div class='clearLine'></div>

			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍详址：</label>
			</div>
			<div class="grid_19">
				<input type="text" name="searchSuperiorVisitVo.nativePlaceAddress" maxlength="50"
					class="form-txt" style="width: 99%" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">常住地址：</label>
			</div>
			<div class="grid_19">
				<input type="text" name="searchSuperiorVisitVo.currentAddress" maxlength="50"
					class="form-txt" style="width: 99%" />
			</div>
		</div>
	</div>
</form>
<script>
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#superiorVisitDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#superiorVisitDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
	var idCard = $("#searchByIdcard").val();
	if(idCard=="请输入身份证号码"){
		$("#conditionIdCardNo").val("");
	}else{
		$("#conditionIdCardNo").val(idCard);
	}
	threeSelect({
		province:'conditionProvince',
		city:'conditionCity',
		district:'conditionDistrict'
	});

	$('#conditionStartBirthday').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionBirthday").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionEndBirthday').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionBirthday").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionFerretOutDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endConditionFerretOutDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionFerretOutDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionFerretOutDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionDrugfristDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endConditionDrugfristDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionDrugfristDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionDrugfristDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$("#state").change(function(){
		if($("#state").val()=='1'){
			$("#isLock").css("display","inline");
			$("#isDeath").css("display","none");
		}else{
			$("#isLock").css("display","none");
			$("#isDeath").css("display","inline");
		}
	});
	//add by zhanL at 2013/08/12 18:38
	var conditions = '${conditions}';
	if(conditions = 'undefined') {
		conditions = '';
	}
	if(checkIdcard(conditions)){
		$('input[name="searchSuperiorVisitVo.idCardNo"]').val(conditions);
	}else{
		$('input[name="searchSuperiorVisitVo.name"]').val(conditions);
	}
});
</script>
