<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchUnemployedPeopleForm">
<pop:token />
<div id="search-condition-form" title="失业人员信息查询"
	class="container container_24">
	<div class="grid_5 lable-right">
		<label class="form-lbl">关注状态：</label>
	</div>
	<div class="grid_7">
		<select id="isLock" name="searchUnemployedPeopleVo.isEmphasis" class="form-txt">
			<option value="-1" selected="selected">全部</option>
			<option value="0">现在关注</option>
			<option value="1">曾经关注</option>
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">死亡状态：</label>
	</div>
   	<div class="grid_7">
		<select id="isDeathState" name="searchUnemployedPeopleVo.isDeath" class="form-txt">
			<option value="-1" selected="selected">全部</option>
			<option value="0">未死亡</option>
			<option value="1">已死亡</option>
		</select>
	</div>
	<div class='clearLine'></div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" maxlength="18"
			name="searchUnemployedPeopleVo.idCardNo" class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchUnemployedPeopleVo.name"
			class="form-txt" maxlength="20" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">出生日期：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchUnemployedPeopleVo.birthdayStart"
			readonly="readonly" class="form-txt" id="conditionStartBirthday"/>
	</div>
	<div class="grid_1 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchUnemployedPeopleVo.birthdayEnd"
			readonly="readonly" class="form-txt" id="conditionEndBirthday"/>
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">性别：</label>
	</div>
	<div class="grid_7">
		<select name="searchUnemployedPeopleVo.gender" class="form-txt">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@GENDER" />
		</select>
	</div>


	<div class="grid_5 lable-right">
		<label class="form-lbl">人员类型：</label>
	</div>
	<div class="grid_7">
		<select name="searchUnemployedPeopleVo.unemployedPeopleType"
			class="form-txt">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYEDPEOPLETYPE" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">失业时间：</label>
	</div>
	<div class="grid_3">
		<input type="text"
			name="searchUnemployedPeopleVo.unemploymentDateStart"
			readonly="readonly" class="form-txt" id="conditionSRD"/>
	</div>
	<div class="grid_1 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchUnemployedPeopleVo.unemploymentDateEnd"
			readonly="readonly" class="form-txt" id="conditionERD" />
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lb1">失业原因：</label>
	</div>
	<div class="grid_19">
		<select name="searchUnemployedPeopleVo.unemploymentReason"
			class="form-txt">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYMENTREASON" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">登记证号：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchUnemployedPeopleVo.registerNumber"
			class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">职称：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchUnemployedPeopleVo.title" id="title"
			class="form-txt" maxlength="10" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">技术等级： </label>
	</div>
	<div class="grid_7">
		<select name="searchUnemployedPeopleVo.technologyLevel"
			class="form-txt" id="technologyLevel">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@TECHNOLOGYLEVEL" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">参加工作时间：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchUnemployedPeopleVo.enterWorkDateStart"
			readonly="readonly" class="form-txt" id="enterWorkDateStart"/>
	</div>
	<div class="grid_1 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchUnemployedPeopleVo.enterWorkDateEnd"
			readonly="readonly" class="form-txt" id="enterWorkDateEnd"/>
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">原单位名称： </label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchUnemployedPeopleVo.originalWorkUnit"
			id="originalWorkUnit" class="form-txt" maxlength="20">
	</div>
	
	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchUnemployedPeopleVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchUnemployedPeopleVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">原单位类型：</label>
	</div>
	<div class="grid_7">
		<input type="text"
			name="searchUnemployedPeopleVo.originalWorkUnitType"
			id="originalWorkUnitType" class="form-txt" maxlength="20">
	</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">拟就业意向： </label>
	</div>
	<div class="grid_19" style="height: 100px">
		<pop:PropertyDictMultiCheckbox name="employmentIntentionIds"
			column="3"
			domainName="@com.tianque.domain.property.PropertyTypes@EMPLOYMENTINTENTION"/>
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">拟参加培训意向： </label>
	</div>
	<div class="grid_19" style="height: 60px">
		<pop:PropertyDictMultiCheckbox name="trainingIntentionIds" column="3"
			domainName="@com.tianque.domain.property.PropertyTypes@TRAININGINTENTION"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
			<div class="grid_4 lable-right">
				<label class="form-lbl">血型：</label>
			</div>
			<div class="grid_8">
				<select name="searchUnemployedPeopleVo.bloodType" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
				</select>
			</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">民族：</label>
			</div>
			<div class="grid_8">
				<select name="searchUnemployedPeopleVo.nation" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@NATION" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">政治面貌：</label>
			</div>
			<div class="grid_8">
				<select name="searchUnemployedPeopleVo.politicalBackground" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">婚姻状况：</label>
			</div>
			<div class="grid_8">
				<select name="searchUnemployedPeopleVo.maritalState" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"
						defaultValue="${population.maritalState.id}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文化程度：</label>
			</div>
			<div class="grid_8">
				<select name="searchUnemployedPeopleVo.schooling" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_8">
				<select name="searchUnemployedPeopleVo.career" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@CAREER" />
				</select>
			</div>
			<div class="grid_6 lable-right">
				<label class="form-lb1">工作单位或就读学校：</label>
			</div>
			<div class="grid_18">
				<input type="text" name="searchUnemployedPeopleVo.workUnit" maxlength="50"
					class="form-txt" />
			</div>

			<div class="clearLine">&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">身高：</label>
			</div>
			<div class="grid_8">
				<input type="text" name="searchUnemployedPeopleVo.minStature" class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="searchUnemployedPeopleVo.maxStature" class="form-txt" />
			</div>
			<div class="grid_1">cm</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">宗教信仰：</label>
			</div>
			<div class="grid_8">
				<select name="searchUnemployedPeopleVo.faith" class="form-txt">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@FAITH" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">电子邮箱：</label>
			</div>
			<div class="grid_8">
				<input type="text" name="searchUnemployedPeopleVo.email" maxlength="50"
					class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">联系手机：</label>
			</div>
			<div class="grid_8">
				<input type="text" name="searchUnemployedPeopleVo.mobileNumber" maxlength="11"
					class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">固定电话：</label>
			</div>
			<div class="grid_8">
				<input type="text" name="searchUnemployedPeopleVo.telephone" maxlength="20"
					class=" form-txt" />
			</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">户籍地：</label>
			</div>
			<div class="grid_5">
				<select name="searchUnemployedPeopleVo.province" class="form-txt" id="conditionProvince"></select>
			</div>
			<div class="grid_1">
				<label class="form-lbl">省</label>
			</div>
			<div class="grid_5">
				<select name="searchUnemployedPeopleVo.city" class="form-txt" id="conditionCity"></select>
			</div>
			<div class="grid_1">
				<label class="form-lbl">市</label>
			</div>
			<div class="grid_5">
				<select name="searchUnemployedPeopleVo.district" class="form-txt"  id="conditionDistrict"></select>
			</div>
			<div class="grid_1">
				<label class="form-lbl">县</label>
			</div>
			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">户籍详址：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchUnemployedPeopleVo.nativePlaceAddress" maxlength="50"
					class="form-txt" style="width: 99%" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">常住地址：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchUnemployedPeopleVo.currentAddress" maxlength="50"
					class="form-txt" style="width: 99%" />
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(document).ready(
			function() {
				
				$("#showMoreBtn").toggle(
					function(){
						$("#unemployedPeopleDialog").dialog( "option" , {height:450});
						$("#showMoreCtt").show();
						$(this).addClass("cur").text("隐藏更多条件");
					},
					function(){
						$("#unemployedPeopleDialog").dialog( "option" , {height:330} );
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
									$("#conditionSRD").datepicker("option",
											"minDate", date);
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
									$("#conditionERD").datepicker("option",
											"maxDate", date);
								}
							}
						});

				$('#enterWorkDateStart').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#enterWorkDateEnd")
											.datepicker("option", "minDate",
													date);
								}
							}
						});

				$('#enterWorkDateEnd').datePicker(
						{
							onSelect : function(dateText, inst) {
								if (dateText != null && dateText != '') {
									var dateUnit = dateText.split('-');
									var date = new Date(dateUnit[0],
											dateUnit[1] - 1, dateUnit[2]);
									$("#enterWorkDateStart")
											.datepicker("option", "maxDate",
													date);
								}
							}
						});

			});
</script>
