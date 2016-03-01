<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchOptimalObjectForm">
	<pop:token />
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
 		<div class="grid_7">
			<select id="isLock" name="searchOptimalObjectVo.isEmphasis" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
	   	<div class="grid_7">
			<select id="isDeathState" name="searchOptimalObjectVo.isDeath" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">未死亡</option>
				<option value="1">已死亡</option>
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchOptimalObjectVo.idCardNo" id="conditionIdCardNo" class="form-txt" maxlength="18"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" name="searchOptimalObjectVo.name" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">出生日期 从：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionBirthday" name="searchOptimalObjectVo.birthday" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">至：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endConditionBirthday" name="searchOptimalObjectVo.endBirthday" readonly="readonly" class="form-txt" />
        </div>
       	<div class="grid_5 lable-right">
			<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_7">
			<select id="conditionGender" name="searchOptimalObjectVo.gender.id" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
			</select>
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">劳动能力： </label>
        </div>
        <div class="grid_7">
            <select id="conditionLaborAbility" name="searchOptimalObjectVo.laborAbility.id" class="form-txt">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" />
            </select>
        </div>
        <div class="grid_5 lable-right">
			<label class="form-lbl">优待证号：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionOptimalCardNo" name="searchOptimalObjectVo.optimalCardNo" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">优抚类型： </label>
        </div>
        <div class="grid_7">
            <select id="conditionOptimalCardType" name="searchOptimalObjectVo.optimalCardType.id" class="form-txt">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE" />
            </select>
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">生活能力：</label>
        </div>
        <div class="grid_7">
            <select id="conditionAbilityLiving" name="searchOptimalObjectVo.abilityLiving.id" class="form-txt">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@VIABILITY" />
            </select>
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">供养情况：</label>
        </div>
        <div class="grid_7">
            <select id="conditionSupportSituation" name="searchOptimalObjectVo.supportSituation.id" class="form-txt">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS" />
            </select>
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">就业情况：</label>
        </div>
        <div class="grid_7">
            <select id="conditionEmploymentSituation" name="searchOptimalObjectVo.employmentSituation.id" class="form-txt">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EMPLOYMENT_STATUS" />
            </select>
        </div>
        <div class="grid_5 lable-right">
			<label class="form-lbl">户籍地： </label>
		</div>
		<div class="grid_7">
			<select id="conditionProvince" name="searchOptimalObjectVo.province" class="form-txt"></select>
		</div>

        <div class="grid_5 lable-right">
            <label class="form-lbl">月生活费 从：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionMonthLivingExpenses" name="searchOptimalObjectVo.monthLivingExpenses" class="form-txt" />
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">至：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endMonthLivingExpenses" name="searchOptimalObjectVo.endMonthLivingExpenses" class="form-txt" />
        </div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">户籍地详址：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="conditionNativePlaceAddress" name="searchOptimalObjectVo.nativePlaceAddress" class="form-txt" />
		</div>

        <div class="clearLine">&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">常住地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="conditionCurrentAddress" name="searchOptimalObjectVo.currentAddress" class="form-txt" />
		</div>
		 
	 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchOptimalObjectVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchOptimalObjectVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
        <div class='clearLine'></div>
        <div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
			<div id="showMoreCtt" style="display: none">
				<div class="grid_5 lable-right">
					<label class="form-lbl">民族：</label>
				</div>
				<div class="grid_7">
		            <select id="conditionNation" name="searchOptimalObjectVo.nation.id" class="form-txt">
		                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" />
		            </select>
		        </div>
		        <div class="grid_5 lable-right">
					<label class="form-lbl">政治面貌：</label>
				</div>
				<div class="grid_7">
		            <select id="conditionPoliticalbackground" name="searchOptimalObjectVo.politicalbackground.id" class="form-txt">
		                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
		            </select>
		        </div>
		        <div class="grid_5 lable-right">
					<label class="form-lbl">婚姻状况：</label>
				</div>
				<div class="grid_7">
		            <select id="conditionMaritalstate" name="searchOptimalObjectVo.maritalstate.id" class="form-txt">
		                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
		            </select>
		        </div>
		        <div class="grid_5 lable-right">
					<label class="form-lbl">文化程度：</label>
				</div>
				<div class="grid_7">
		            <select id="conditionSchooling" name="searchOptimalObjectVo.schooling.id" class="form-txt">
		                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
		            </select>
		        </div>
		        <div class="grid_5 lable-right">
					<label class="form-lbl">职业：</label>
				</div>
				<div class="grid_7">
				 	<select id="conditionCareer" name="searchOptimalObjectVo.career.id" class="form-txt">
		               <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" />
		            </select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">工作单位或就读学校：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionWorkUnit" name="searchOptimalObjectVo.workUnit" class="form-txt" />
				</div>
				<div class="grid_5 lable-right">
		            <label class="form-lbl">身高 从：</label>
		        </div>
		        <div class="grid_7">
		            <input type="text" id="conditionStature" name="searchOptimalObjectVo.stature" class="form-txt" />
		        </div>
		        <div class="grid_5 lable-right" >
		            <label class="form-lbl">至：</label>
		        </div>
		        <div class="grid_7">
		            <input type="text" id="conditionEndStature" name="searchOptimalObjectVo.endStature" class="form-txt" />
		        </div>
		        <div class="grid_5 lable-right">
					<label class="form-lbl">血型：</label>
				</div>
				<div class="grid_7">
		            <select id="conditionBloodtype" name="searchOptimalObjectVo.bloodtype.id" class="form-txt">
		                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
		            </select>
		        </div>
		        <div class="grid_5 lable-right">
					<label class="form-lbl">宗教信仰：</label>
				</div>
				<div class="grid_7">
		            <select id="conditionFaith" name="searchOptimalObjectVo.faith.id" class="form-txt">
		                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" />
		            </select>
		        </div>
		        <div class="grid_5 lable-right">
					<label class="form-lbl">电子邮箱：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionEmail" name="searchOptimalObjectVo.email" class="form-txt" />
				</div>

				<div class="grid_5 lable-right">
		            <label class="form-lbl">联系手机：</label>
		        </div>
		        <div class="grid_7">
		            <input type="text" id="conditionMobileNumber" name="searchOptimalObjectVo.mobileNumber" class="form-txt" maxlength="11" />
		        </div>
		        <div class="grid_5 lable-right">
		            <label class="form-lbl">固定电话：</label>
		        </div>
		        <div class="grid_7">
		            <input type="text" id="conditionTelephone" name="searchOptimalObjectVo.telephone" class="form-txt" maxlength="15" />
		        </div>
	        </div>
	   </div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#optimalObjectDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#optimalObjectDialog").dialog( "option" , {height:330} );
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

	$('#conditionBirthday').datePicker({
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

	$('#endConditionBirthday').datePicker({
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

});
</script>
