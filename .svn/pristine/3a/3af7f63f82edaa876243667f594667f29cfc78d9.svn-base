<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="老年人信息查询" class="container container_24">
	<form id="searchElderlyPeopleForm">
		<pop:token />
		<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
		<div class="grid_7">
			<select id="isLock" name="searchElderlyPeopleVo.isEmphasis" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
	   	<div class="grid_7">
			<select id="isDeathState" name="searchElderlyPeopleVo.isDeath" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">未死亡</option>
				<option value="1">已死亡</option>
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionIdCardNo" maxlength="18"
				class="form-txt" name="searchElderlyPeopleVo.idCardNo"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" class="form-txt" maxlength="20" name="searchElderlyPeopleVo.name"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">老年证号：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionElderlyPeopleId" class="form-txt" name="searchElderlyPeopleVo.elderPeopleId"
				maxlength="20" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">人员类型：</label>
		</div>
		<div class="grid_7">
			<select id="conditionpeopleType" class="form-txt" name="searchElderlyPeopleVo.peopleType">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">当前状况：</label>
		</div>
		<div class="grid_7">
			<select id="conditionCurrentStatus" class="form-txt" name="searchElderlyPeopleVo.currentStatus">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">居住情况：</label>
		</div>
		<div class="grid_7">
			<select id="conditionLiveStatus" class="form-txt" name="searchElderlyPeopleVo.liveStatus">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LIVE_STATUS" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">配偶情况：</label>
		</div>
		<div class="grid_7">
			<select id="conditionPO" class="form-txt" name="searchElderlyPeopleVo.spouseStatus">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPOUSE_STATUS" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">经济来源：</label>
		</div>
		<div class="grid_7">
			<select id="conditionJJ" class="form-txt" name="searchElderlyPeopleVo.incomeSource">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INCOME_SOURCE" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">离退休日期：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionSRD" class="form-txt" name="searchElderlyPeopleVo.retireDateStart" readonly />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionERD" class="form-txt" name="searchElderlyPeopleVo.retireDateEnd" readonly />
		</div>
		<div class="clearLine">&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">离退休单位：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionYU" class="form-txt" name="searchElderlyPeopleVo.retireUnitAndDuty" maxlength="30" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">原单位职务：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionZhiwu" name="searchElderlyPeopleVo.zhiwu" class="form-txt"
				maxlength="50" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">退休金：</label>
		</div>
		<div class="grid_7">
			<input id="conditionSPension" type="text" class="form-txt"
				style="text-align: right;" maxlength="9" name="searchElderlyPeopleVo.minPension" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="conditionEPension" name="searchElderlyPeopleVo.maxPension" class="form-txt"  />
		</div>
		<div class="grid_1">(元)</div>
		
	 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchElderlyPeopleVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchElderlyPeopleVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
			<div id="showMoreCtt" style="display: none">
				<div class="grid_5 lable-right">
					<label class="form-lbl">性别：</label>
				</div>
				<div class="grid_7">
					<select id="conditionGender" class="form-txt" name="searchElderlyPeopleVo.gender">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@GENDER" />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">血型：</label>
				</div>
				<div class="grid_7">
					<select id="conditionBT" class="form-txt" name="searchElderlyPeopleVo.bloodType">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
					</select>
				</div>

				<div class="grid_5 lable-right">
					<label class="form-lbl">民族：</label>
				</div>
				<div class="grid_7">
					<select id="conditionNation" class="form-txt" name="searchElderlyPeopleVo.nation">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@NATION" />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">政治面貌：</label>
				</div>
				<div class="grid_7">
					<select id="conditionPB" class="form-txt" name="searchElderlyPeopleVo.politicalBackground">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">婚姻状况：</label>
				</div>
				<div class="grid_7">
					<select id="conditionMS" class="form-txt" name="searchElderlyPeopleVo.maritalState">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"
							 />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">文化程度：</label>
				</div>
				<div class="grid_7">
					<select id="conditionSC" class="form-txt" name="searchElderlyPeopleVo.schooling">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">职业：</label>
				</div>
				<div class="grid_7">
					<select id="conditionCareer" class="form-txt" name="searchElderlyPeopleVo.career">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@CAREER" />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">工作单位或就读学校：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionWorkUnit" maxlength="50" name="searchElderlyPeopleVo.workUnit"
						class="form-txt" />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">出生日期：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionStartBirthday" readonly="readonly" name="searchElderlyPeopleVo.birthdayStart"
						class="form-txt" />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">至：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionEndBirthday" readonly="readonly" name="searchElderlyPeopleVo.birthdayEnd"
						class="form-txt" />
				</div>
				<div class="clearLine">&nbsp;</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">身高：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionMin" class="form-txt" name="searchElderlyPeopleVo.minStature"/>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">至：</label>
				</div>
				<div class="grid_6">
					<input type="text" id="conditionMax" class="form-txt" name="searchElderlyPeopleVo.maxStature"/>
				</div>
				<div class="grid_1">cm</div>
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">宗教信仰：</label>
				</div>
				<div class="grid_7">
					<select id="conditionFaith" class="form-txt" name="searchElderlyPeopleVo.faith">
						<pop:OptionTag
							name="@com.tianque.domain.property.PropertyTypes@FAITH" />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">电子邮箱：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionEmail" maxlength="50" name="searchElderlyPeopleVo.email"
						class="form-txt" />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">联系手机：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionMobileNumber" maxlength="11" name="searchElderlyPeopleVo.mobileNumber"
						class="form-txt" />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">固定电话：</label>
				</div>
				<div class="grid_7">
					<input type="text" id="conditionTelephone" maxlength="20" name="searchElderlyPeopleVo.telephone"
						class=" form-txt" />
				</div>
				
				<div class="grid_5 lable-right">
					<label class="form-lbl">户籍地：</label>
				</div>
				<div class="grid_5">
					<select id="conditionProvince" class="form-select" name="searchElderlyPeopleVo.province"></select>
				</div>
				<div class="grid_1">
					<label class="form-lbl">省</label>
				</div>
				<div class="grid_5">
					<select id="conditionCity" class="form-select" name="searchElderlyPeopleVo.city"></select>
				</div>
				<div class="grid_1">
					<label class="form-lbl">市</label>
				</div>
				<div class="grid_5">
					<select id="conditionDistrict" class="form-select" name="searchElderlyPeopleVo.district"></select>
				</div>
				<div class="grid_1">
					<label class="form-lbl">县</label>
				</div>
				<div class='clearLine'></div>

				<div class="grid_5 lable-right">
					<label class="form-lbl">户籍详址：</label>
				</div>
				<div class="grid_19">
					<input type="text" id="conditionNativePlaceAddress" maxlength="50" name="searchElderlyPeopleVo.nativePlaceAddress"
						class="form-txt" style="width: 99%" />
				</div>
				<div class='clearLine'></div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">常住地址：</label>
				</div>
				<div class="grid_19">
					<input type="text" id="conditionCurrentAddress" maxlength="50" name="searchElderlyPeopleVo.currentAddress"
						class="form-txt" style="width: 99%" />
				</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {
				
				$("#showMoreBtn").toggle(
					function(){
						$("#elderlyPeopleDialog").dialog( "option" , {height:450});
						$("#showMoreCtt").show();
						$(this).addClass("cur").text("隐藏更多条件");
					},
					function(){
						$("#elderlyPeopleDialog").dialog( "option" , {height:330} );
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
					province : 'conditionProvince',
					city : 'conditionCity',
					district : 'conditionDistrict'
				});

				$('#conditionStartBirthday').datePicker(
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

				$('#conditionEndBirthday').datePicker(
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

				$('#conditionSRD').datePicker(
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

				$('#conditionERD').datePicker(
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

				$('#conditionExpectedStartDatedue').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionExpectedEndDatedue")
											.datepicker("option", "minDate",
													date);
								}
							}
						});

				$('#conditionExpectedEndDatedue').datePicker(
						{
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionExpectedStartDatedue")
											.datepicker("option", "maxDate",
													date);
								}
							}
						});

				$('#conditionRegisterStartDate').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							maxDate : '+0d',
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionRegisterEndDate").datepicker(
											"option", "minDate", date);
								}
							}
						});

				$('#conditionRegisterEndDate').datePicker(
						{
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#conditionRegisterStartDate")
											.datepicker("option", "maxDate",
													date);
								}
							}
						});
			});
</script>
