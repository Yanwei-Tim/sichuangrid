<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="残疾人信息查询"
	class="container container_24">
	<form id="searchHandicappedForm">
	<pop:token />
	<div class="grid_5 lable-right">
		<label class="form-lbl">关注状态：</label>
	</div>
				<div class="grid_7">
		<select id="isLock" name="searchHandicappedVo.isEmphasis" class="form-txt">
			<option value="-1" selected="selected">全部</option>
			<option value="0">现在关注</option>
			<option value="1">曾经关注</option>
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">死亡状态：</label>
	</div>
   	<div class="grid_7">
		<select id="isDeathState" name="searchHandicappedVo.isDeath" class="form-txt">
			<option value="-1" selected="selected">全部</option>
			<option value="0">未死亡</option>
			<option value="1">已死亡</option>
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="idCardNo" maxlength="18"
			class="form-txt" name="searchHandicappedVo.idCardNo" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="name" name="searchHandicappedVo.name" class="form-txt" maxlength="20" />
	</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lb1">出生日期：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="birthdayStart" name="searchHandicappedVo.birthdayStart" class="form-txt" readonly />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="birthdayEnd" name="searchHandicappedVo.birthdayEnd" class="form-txt" readonly />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">性别：</label>
	</div>
	<div class="grid_7">
		<select id="genderId" class="form-txt" name="searchHandicappedVo.genderId">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@GENDER" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">残疾原因：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="disabilityReason" class="form-txt"
			maxlength="20" name="searchHandicappedVo.disabilityReason" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">残疾时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="disabilityDateStart" class="form-txt" name="searchHandicappedVo.disabilityDateStart" readonly />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="disabilityDateEnd" name="searchHandicappedVo.disabilityDateEnd" class="form-txt" readonly />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">残疾类别：</label>
	</div>
	<div class="grid_7">
		<select id="disabilityType" class="form-txt" name="searchHandicappedVo.disabilityType" onchange="disabilityTypeChange()">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@DISABILITY_TYPE" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">残疾等级：</label>
	</div>
	<div class="grid_7">
		<select id="disabilityId" class="form-txt" name="searchHandicappedVo.disabilityId"
		    <c:if test='${mode=="view"}'>disabled='true'</c:if> >
		   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" />
		</select>
		<select id="disabilityIdHide" style="display: none;">
		   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">是否有残疾证：</label>
	</div>
	<div class="grid_7">
		<s:select cssClass="form-txt" name="searchHandicappedVo.hasDisabilityCardType.code" listKey="code" list="@com.tianque.search.vo.QuickFilterType@DEFAULT_BOOLEAN_STATE_FILTER_TYPES" ></s:select>
	 </div>
	<%-- <div class="grid_7">
		<select id="hasDisabilityCard" class="form-txt" name="searchHandicappedVo.hasDisabilityCard">
				<option value=""></option>
			   	<option value="true">持有残疾证</option>
			   	<option value="false">未持有残疾证</option>
		</select>
	</div> --%>
	<div class="grid_5 lable-right">
		<label class="form-lbl">残疾证号：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="disabilityCardNo" class="form-txt" maxlength="30" name="searchHandicappedVo.disabilityCardNo" />
	</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lb1">劳动能力 ：</label>
	</div>
	<div class="grid_7">
		<select id="workProfileId" class="form-txt" name="searchHandicappedVo.workProfileId"
	    	<c:if test='${mode=="view"}'>disabled='true'</c:if> >
	  		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">技能特长 ：</label>
	</div>
	<div class="grid_7">
		<select id="skillProfileId" class="form-txt" name="searchHandicappedVo.skillProfileId"
	    	<c:if test='${mode=="view"}'>disabled='true'</c:if> >
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SKILLS_AND_SPECIALITIES" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">户籍地：</label>
	</div>
	<div class="grid_5">
		<select id="province" class="form-txt" name="searchHandicappedVo.province"></select>
	</div>
	<div class="grid_1">
		<label class="form-lbl">省</label>
	</div>
	<div class="grid_5">
		<select id="city" class="form-txt" name="searchHandicappedVo.city"></select>
	</div>
	<div class="grid_1">
		<label class="form-lbl">市</label>
	</div>
	<div class="grid_5">
		<select id="district" class="form-txt" name="searchHandicappedVo.district"></select>
	</div>
	<div class="grid_1">
		<label class="form-lbl">县</label>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">户籍详址：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="nativePlaceAddress" maxlength="50"
			class="form-txt" style="width: 99%" name="searchHandicappedVo.nativePlaceAddress"/>
	</div>
	<div class='clearLine'></div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">常住地址：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="currentAddress" maxlength="50"
			class="form-txt" style="width: 99%" name="searchHandicappedVo.currentAddress"/>
	</div>
	 
	 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchHandicappedVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchHandicappedVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
	<div class='clearLine'>&nbsp;</div>	
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
			<div class="grid_5 lable-right">
				<label class="form-lbl">民族：</label>
			</div>
			<div class="grid_7">
				<select id="nation" class="form-txt" name="searchHandicappedVo.nation">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@NATION" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">政治面貌：</label>
			</div>
			<div class="grid_7">
				<select id="politicalBackground" class="form-txt" name="searchHandicappedVo.politicalBackground">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">婚姻状况：</label>
			</div>
			<div class="grid_7">
				<select id="maritalState" class="form-txt" name="searchHandicappedVo.maritalState">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"
						defaultValue="${population.maritalState.id}" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">文化程度：</label>
			</div>
			<div class="grid_7">
				<select id="schooling" class="form-txt" name="searchHandicappedVo.schooling">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_7">
				<select id="career" class="form-txt" name="searchHandicappedVo.career">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@CAREER" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">工作单位或就读学校：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="workUnit" maxlength="50" name="searchHandicappedVo.workUnit"
					class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">身高：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="minStature" class="form-txt" name="searchHandicappedVo.minStature" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="maxStature" class="form-txt" name="searchHandicappedVo.maxStature"/>
			</div><div class="grid_1">cm</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">血型：</label>
			</div>
			<div class="grid_7">
				<select id="bloodType" class="form-txt" name="searchHandicappedVo.bloodType">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">宗教信仰：</label>
			</div>
			<div class="grid_7">
				<select id="faith" class="form-txt" name="searchHandicappedVo.faith">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@FAITH" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">电子邮箱：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="email" maxlength="50" name="searchHandicappedVo.email"
					class="form-txt" /></div>

			<div class="grid_5 lable-right">
				<label class="form-lbl">联系手机：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="mobileNumber" maxlength="11" name="searchHandicappedVo.mobileNumber"
					class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">固定电话：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="telephone" maxlength="20" name="searchHandicappedVo.telephone"
					class=" form-txt" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class='clearLine'></div>
		</div>
	</form>
</div>
<script type="text/javascript">
function disabilityTypeChange(){
	var _txt = $("#disabilityType option:selected").text();
	$("#disabilityId").empty().append("<option value=''>请选择</option>");
	$("#disabilityIdHide option:gt(0)").each(function (){
		if(_txt == '请选择' || $(this).text().indexOf(_txt) != -1){
			$("#disabilityId").append("<option value='"+$(this).val()+"' id='"+$(this).attr('id')+"' internalid=''>"+$(this).text()+"</option>");
		}
	});
}
	$(document).ready(
			function() {
			$("#showMoreBtn").toggle(
				function(){
					$("#handicappedDialog").dialog( "option" , {height:450});
					$("#showMoreCtt").show();
					$(this).addClass("cur").text("隐藏更多条件");
				},
				function(){
					$("#handicappedDialog").dialog( "option" , {height:330} );
					$("#showMoreCtt").hide();
					$(this).removeClass("cur").text("显示更多条件");
				}
			);
				
				var idCard = $("#searchByIdcard").val();
				if (idCard == "请输入身份证号码") {
					$("#conditionIdCardNo").val("");
				} else {
					$("#conditionIdCardNo").val(idCard);
				}
				threeSelect({
					province : 'province',
					city : 'city',
					district : 'district'
				});

				$('#birthdayStart').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							maxDate : '+0d',
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionEndBirthday").datepicker(
											"option", "minDate", date);
								}
							}
						});

				$('#birthdayEnd').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							maxDate : '+0d',
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionStartBirthday").datepicker(
											"option", "maxDate", date);
								}
							}
						});

				$('#disabilityDateStart').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							maxDate : '+0d',
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionSRD").datepicker(
											"option", "minDate", date);
								}
							}
						});

				$('#disabilityDateEnd').datePicker(
						{
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionERD")
											.datepicker("option", "maxDate",
													date);
								}
							}
						});

			
			});
</script>
