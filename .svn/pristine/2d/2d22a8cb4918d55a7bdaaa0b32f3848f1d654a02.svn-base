<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="populationQueryFrom">

<div id="householdStaff" title="户籍人口" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">户籍人口<a href="javascript:;">click1</a></h3>
	<div class="textBack clearfix">
		<div class="clearfix">
			<div class="grid_4 lable-right">
				<label class="form-lbl">户口号：</label>
			</div>
			<div class="grid_8">
				<input type="text" name="populationQueryVo.searchHouseholdStaffVo.accountNumber"
					id="householdStaffVo.accountNumber" maxlength="20" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">是否外出：</label>
			</div>
			<div class="grid_8">
				<select id="outGone" class="form-txt" name="populationQueryVo.searchHouseholdStaffVo.outGone">
			   			<option></option>
						<option value="true">是</option>
			   			<option value="false">否</option>
			 	</select>
			</div>
		</div>
		<div class="clearfix">
			<div class="grid_4 lable-right">
				<label class="form-lbl">外出原因：</label>
			</div>
			<div class="grid_8">
				<select name="populationQueryVo.searchHouseholdStaffVo.outReasonsId" class="form-txt">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@OUT_REASON" />
				</select>
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">外出时间：</label>
			</div>
			<div class="grid_1">从</div>
			<div class="grid_3">
				<input type="text" class="form-txt" maxlength="20" id="searchHouseholdStaffVo_reasonsDateStart"
					name="populationQueryVo.searchHouseholdStaffVo.reasonsDateStart" readonly/>
			</div>
			<div class="grid_1 textTo">至</div>
			<div class="grid_3">
				<input type="text" class="form-txt" maxlength="20" id="searchHouseholdStaffVo_reasonsDateEnd"
					name="populationQueryVo.searchHouseholdStaffVo.reasonsDateEnd" readonly/>
			</div>
		</div>
		<div class="clearfix">
			<div class="grid_4 lable-right">
				<label class="form-lbl">户籍地：</label>
			</div>
			<div class="grid_5">
				<select name="populationQueryVo.searchHouseholdStaffVo.province"
					id="householdStaffVo_province" class="form-txt"></select>
			</div>
			<div class="grid_1">省</div>
		
			<div class="grid_6">
				<select name="populationQueryVo.searchHouseholdStaffVo.city" id="householdStaffVo_city"
					class="form-txt"></select>
			</div>
			<div class="grid_1">市</div>
		
			<div class="grid_6">
				<select name="populationQueryVo.searchHouseholdStaffVo.district" id="householdStaffVo_district" class="form-txt"></select>
			</div>
			<div class="grid_1">县</div>
		</div>
	</div>

</div>

<div id="floatingPopulation" title="流动人口信息查询"
	class="container container_24" style="display: none">
	<h3 class="personalCatagory">流动人口<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class="grid_4 lable-right">
			<label class="form-lbl">流入原因：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchFloatingPopulationVo.inflowingReason"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@INFLOWING_REASON" />
			</select>
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lbl">流入时间：</label>
		</div>
		<div class="grid_1">从</div>
		<div class="grid_3">
			<input type="text" id="conditionInflowingStartDate"
				name="populationQueryVo.searchFloatingPopulationVo.InflowingStartDate" class="form-txt"
				readonly="readonly" />
		</div>
		<div class="grid_1 lable-right">
			<label class="form-lbl textTo">至</label>
		</div>
		<div class="grid_3">
			<input type="text" id="conditionInflowingEndDate" name="populationQueryVo.searchFloatingPopulationVo.InflowingEndDate"
				class="form-txt" readonly="readonly" />
		</div>
	
	
		<div class="grid_4 lable-right">
			<label class="form-lbl">婚育证明情况：</label>
		</div>
		<div class="grid_8">
			<select id="outGone" class="form-txt" name="populationQueryVo.searchFloatingPopulationVo.hasMarriedProve">
		   			<option></option>
					<option value="1" >有</option>
		   			<option value="0" >无</option>
		 	</select>
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lbl">登记证情况：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchFloatingPopulationVo.RegistrationType"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@REGISTRATIONTYPE" />
			</select>
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lbl">预计到期日期：</label>
		</div>
		<div class="grid_1 lable-right">
			<label class="form-lbl">从：</label>
		</div>
		<div class="grid_3">
			<input type="text" id="conditionExpectedStartDatedue"
				name="populationQueryVo.searchFloatingPopulationVo.ExpectedStartDatedue" class="form-txt"
				readonly="readonly" />
		</div>
		<div class="grid_1 lable-right">
			<label class="form-lbl">至</label>
		</div>
		<div class="grid_3">
			<input type="text" id="conditionExpectedEndDatedue" name="populationQueryVo.searchFloatingPopulationVo.ExpectedEndDatedue"
				class="form-txt" readonly="readonly" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">暂住处所：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchFloatingPopulationVo.stayLocationTypeId"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@RESIDENT_PREMISES" />
			</select>
		</div>
	</div>
</div>
<div id="unsettledPopulation" title="未落户信息"
	class="container container_24" style="display: none">
	<h3 class="personalCatagory">未落户信息<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class="grid_4 lable-right">
			<label class="form-lbl">未落户时间：</label>
		</div>
		<div class="grid_1">从</div>
		<div class="grid_3">
			<input type="text"  id="conditionUnsettedTimeStart" name="populationQueryVo.searchUnsettledPopulation.UnsettedTimeStart"
				class="form-txt" readonly="readonly" />
		</div>
		<div class="grid_1 lable-right">
			<label class="form-lbl">至</label>
		</div>
		<div class="grid_3">
			<input type="text" id="conditionUnsettedTimeEnd"  name="populationQueryVo.searchUnsettledPopulation.UnsettedTimeEnd"
				class="form-txt" readonly="readonly" />
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lbl">未落户原因：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchUnsettledPopulation.unSettedReason.id"
				class="form-select" id="conditionUnSettedReason">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@UNSETTEDREASON"
					defaultValue="${unsettledPopulation.unSettedReason.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">持证种类：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchUnsettledPopulation.certificateType.id"
				class="form-select" id="conditionCertificateType">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@CERTIFICATEHOLD_TYPE"
					defaultValue="${unsettledPopulation.certificateType.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">持证编号：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="populationQueryVo.unsettledPopulation.certificateNo"
				id="conditionCertificateNo" class="form-txt" />
		</div>
	</div>
</div>
<div class='clearLine'>&nbsp;</div>
<div id="positiveInfo" title="刑释解救人员" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">刑释解救人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class="grid_4 lable-right">
			<label class="form-lbl">人员类型：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchPositiveInfoVo.positiveInfosType.id" id="type"
				class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITIVEINFO" />
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">是否累犯：</label>
		</div>
		<div class="grid_8">
			<select id="outGone" class="form-txt" name="populationQueryVo.searchPositiveInfoVo.isRepeat">
		   			<option></option>
					<option value="1" >是</option>
		   			<option value="0" >否</option>
		 	</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">解教（刑释）日期：</label>
		</div>
		<div class="grid_1 lable-right textTo">
			<label class="form-lbl">从</label>
		</div>
		<div class="grid_5" id="birthday">
			<input type="text" id="searchPositiveInfoVo_releaseOrBackDate" name="populationQueryVo.searchPositiveInfoVo.releaseOrBackDate"
				id="positiveInfo.releaseOrBackDate" value='' class="form-txt"
				readonly="readonly"></input>
		</div>
		
		<div class="grid_1 lable-right textTo">
			<label class="form-lbl">至</label>
		</div>
		<div class="grid_5 lable-right" id="birthday">
			<input type="text" id="searchPositiveInfoVo_endreleaseOrBackDate" name="populationQueryVo.searchPositiveInfoVo.endReleaseOrBackDate"
				id="positiveInfo.endReleaseOrBackDate" value='' class="form-txt"
				readonly="readonly"></input>
		</div>
	</div>
</div>
<div class='clearLine'>&nbsp;</div>
<div id="rectificativePerson" title="社区矫正人员"
	class="container container_24" style="display: none">
	<h3 class="personalCatagory">社区矫正人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">刑法执行类别：</label>
		</div>
		<div class="grid_8">
			<select id="conditionExecuteType"
				name="populationQueryVo.searchRectificativePersonVo.executeTypeId" class="form-select">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@EXECUTE_TYPE" />
			</select>
		</div>
	</div>
</div>
<div class='clearLine'>&nbsp;</div>
<div id="druggy" title="吸毒人员" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">吸毒人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class="grid_4 lable-right">
			<label class="form-lbl">戒毒情况：</label>
		</div>
		<div class="grid_8">
			<select id="conditionDetoxicateCase"
				name="populationQueryVo.searchDruggyVo.detoxicateCase.id" class="form-select">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CASE" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">吸毒状态：</label>
		</div>
		<div class="grid_8">
			<select id="detoxicateCondition"
				name="populationQueryVo.searchDruggyVo.detoxicateCondition.id" class="form-select">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CONDITION" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">吸毒原因： </label>
		</div>
		<div class="grid_8">
			<select id="conditionDrugReason" name="populationQueryVo.searchDruggyVo.drugReason.id"
				class="form-select">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DRUG_REASON" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">是否服美沙酮戒毒：</label>
		</div>
		<div class="grid_7">
			<select id="conditionDrugSource" name="populationQueryVo.searchDruggyVo.isAdanon"
				class="form-select">
					<option></option>
					<option value="1" >是</option>
		   			<option value="0" >否</option>
			</select>
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="mentalPatient" title="严重精神障碍患者" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">严重精神障碍患者<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">危险程度：</label>
		</div>
		<div class="grid_8">
			<select id="conditionDangerLevel"
				name="populationQueryVo.searchMentalPatientVo.dangerLevelId" class="form-select">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@MENTALPATIENT_DANGEROUS_LEVEL" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">是否接受过治疗：</label>
		</div>
		<div class="grid_6">
			<select id="conditionIsTreat" name="populationQueryVo.searchMentalPatientVo.isTreat"
				style="width: 170px">
				<option value=""></option>
				<option value="true">是</option>
				<option value="false">否</option>
			</select>
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="idleYouth" title="重点青少年" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">重点青少年<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class="grid_4 lable-right clearfix">
			<label class="form-lbl">人员类型：</label>
		</div>
		<div class="grid_20 heightAuto clearfix" id="conditionStaffTypeId">
			<pop:PropertyDictMultiCheckbox name="staffTypeIds"
				column="3"
				domainName="@com.tianque.domain.property.PropertyTypes@IDLEYOUTH_STAFF_TYPE" />
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="superiorVisit" title="重点上访人员" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">重点上访人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">上访状态：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchSuperiorVisitVo.visitState.id" class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@SUPERIOR_VISIT_STATUS" />
			</select>
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="dangerousGoodsPractitioner" title="危险品从业人员"
	class="container container_24" style="display: none">
	<h3 class="personalCatagory">危险品从业人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">危险从业类别：</label>
		</div>
		<div class="grid_8">
			<select
				name="populationQueryVo.searchDangerousGoodsPractitionerVo.dangerousWorkingType.id"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@DANGEROUS_WORKING_TYPE" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">从业资格证书：</label>
		</div>
		<div class="grid_8">
			<input type="text"
				name="populationQueryVo.searchDangerousGoodsPractitionerVo.workingCertificate"
				maxlength="20" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">从业资格证书号：</label>
		</div>
		<div class="grid_7">
			<input type="text"
				name="populationQueryVo.searchDangerousGoodsPractitionerVo.workingCertificateNo"
				maxlength="20" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">有效期：</label>
		</div>
		<div class="grid_1 lable-right textTo">
			<label class="form-lbl">从</label>
		</div>
		<div class="grid_3 lable-right">
			<input type="text"
				name="populationQueryVo.searchDangerousGoodsPractitionerVo.startAvailableDate"
				id="conditionStartAvailableDate" readonly="readonly" class="form-txt" />
		</div>
		<div class="grid_1 lable-right textTo">
			<label class="form-lbl">至</label>
		</div>
		<div class="grid_3 lable-right">
			<input type="text"
				name="populationQueryVo.searchDangerousGoodsPractitionerVo.endAvailableDate"
				id="conditionEndAvailableDate" readonly="readonly" class="form-txt" />
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="elderlyPeople" title="老年人" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">老年人<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">人员类型：</label>
		</div>
		<div class="grid_8">
			<select id="conditionpeopleType" class="form-txt" name="populationQueryVo.searchElderlyPeopleVo.peopleType">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">目前情况：</label>
		</div>
		<div class="grid_8">
			<select id="conditionCurrentStatus" class="form-txt" name="populationQueryVo.searchElderlyPeopleVo.currentStatus">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">居住情况：</label>
		</div>
		<div class="grid_8">
			<select id="conditionLiveStatus" class="form-txt" name="populationQueryVo.searchElderlyPeopleVo.liveStatus">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LIVE_STATUS" />
			</select>
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="handicapped" title="残疾人" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">残疾人<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">残疾类别：</label>
		</div>
		<div class="grid_8">
			<select id="disabilityType" class="form-txt" name="populationQueryVo.searchHandicappedVo.disabilityType">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@DISABILITY_TYPE" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">残疾等级：</label>
		</div>
		<div class="grid_8">
			<select id="disabilityId" class="form-txt" name="populationQueryVo.searchHandicappedVo.disabilityId" >
				   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" />
			</select>
		</div><div class="grid_4 lable-right">
			<label class="form-lbl">是否有残疾证：</label>
		</div>
		<div class="grid_8">
			<s:select style="width:195px;" name="populationQueryVo.searchHandicappedVo.hasDisabilityCardType.code" listKey="code" list="@com.tianque.search.vo.QuickFilterType@DEFAULT_BOOLEAN_STATE_FILTER_TYPES" ></s:select>
		 </div>
		 <div class="grid_4 lable-right">
			<label class="form-lbl">劳动能力 ：</label>
		</div>
		<div class="grid_8">
			<select id="workProfileId" class="form-txt" name="populationQueryVo.searchHandicappedVo.workProfileId">
				  		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" />
				</select>
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="optimalObject" title="优抚对象" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">优抚对象<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class="grid_4 lable-right">
            <label class="form-lbl">优抚类型： </label>
        </div>
        <div class="grid_8">
            <select id="conditionOptimalCardType" name="populationQueryVo.searchOptimalObjectVo.optimalCardType.id" class="form-select">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE" />
            </select>
        </div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="aidNeedPopulation" title="需救助人员" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">需救助人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<label class="form-lbl">救助原因：</label>
			</div>
			<div class="grid_8">
	            <select  id="conditionAidReason" class="form-txt" name="populationQueryVo.searchAidNeedPopulationVo.aidReason.id" >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@AIDRREASON"/>
				</select>
	        </div>
		<div class="grid_4 lable-right">
		<label class="form-check-text">是否低保户： </label>
		</div>
		<div class="grid_8">
			<s:select style="width:195px;" id="isLowHouseholds" name="populationQueryVo.searchAidNeedPopulationVo.isLowHouseholds.code" listKey="code" list="@com.tianque.search.vo.QuickFilterType@DEFAULT_BOOLEAN_STATE_FILTER_TYPES" ></s:select>
		 </div>
	 </div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id="unemployedPeople" title="失业人员" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">失业人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">人员类型：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchUnemployedPeopleVo.unemployedPeopleType"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYEDPEOPLETYPE" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">失业时间：</label>
		</div>
		<div class="grid_1 lable-right textTo">
			<label class="form-lbl">从</label>
		</div>
		<div class="grid_3">
			<input type="text"
				name="populationQueryVo.searchUnemployedPeopleVo.unemploymentDateStart"
				readonly="readonly" class="form-txt" id="conditionSRD"/>
		</div>
		<div class="grid_1 lable-right textTo">
			<label class="form-lbl">至</label>
		</div>
		<div class="grid_3">
			<input type="text" name="populationQueryVo.searchUnemployedPeopleVo.unemploymentDateEnd"
				readonly="readonly" class="form-txt" id="conditionERD" />
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lbl">失业原因：</label>
		</div>
		<div class="grid_13">
			<select name="populationQueryVo.searchUnemployedPeopleVo.unemploymentReason"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYMENTREASON" />
			</select>
		</div>
	</div>
</div>

<div id="nurturesWomen" title="育妇" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">育妇<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="clearfix">
			<div class="grid_4 lable-right">
				<label class="form-lbl">子女数：</label>
			</div>
			<div class="grid_1 lable-right textTo">
				<label class="form-lbl">从</label>
			</div>
			<div class="grid_3" id="childNumberDiv">
		    	<input type="text" id="childNumberStart" maxlength="2" class="form-txt {isDigit:true,messages:{isDigit:'请输入小于10的正整数'}}"  name="populationQueryVo.searchNurturesWomenVo.childNumberStart"/>
			</div>
			<div class="grid_1 lable-right textTo">
				至
			</div>
			<div class="grid_3" id="childNumberDiv">
		    	<input type="text" id="childNumberEnd" maxlength="2" class="form-txt {isDigit:true,messages:{isDigit:'请输入小于10的正整数'}}" name="populationQueryVo.searchNurturesWomenVo.childNumberEnd"/>
			</div>
			
			<div class="grid_4 lable-right">
				<label class="form-lbl">领证情况：</label>
			</div>
			<div class="grid_8">
				<select id="licenseSituation" class="form-txt" name="populationQueryVo.searchNurturesWomenVo.licenseSituation">
		   			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LICENSE_SITUATION" />
		 		</select>
		 	</div>
	 	</div>
	 	<div class="textBack clearfix">
		 	<div class="grid_4 lable-right">
				<label class="form-lbl">是否征收：</label>
			</div>	
			<div class="grid_8">
					<select id="isSingleChildCard" class="form-txt" name="populationQueryVo.searchNurturesWomenVo.isLevied">
			   			<option></option>
						<option value="1" >是</option>
			   			<option value="0" >否</option>
			 		</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">是否未婚先孕：</label></div>
			<div class="grid_8">
				<select id="isSingleChildCard" class="form-txt" name="populationQueryVo.searchNurturesWomenVo.isUnmarriedPregnant">
			   			<option></option>
						<option value="1" >是</option>
			   			<option value="0" >否</option>
			 		</select>
			</div>
		</div>
		<div class="textBack clearfix">
			<div class="grid_4 lable-right">
				<label class="form-lbl">是否有婚育证：</label>
			 </div>
			<div class="grid_8">
					<select id="isSingleChildCard" class="form-txt" name="populationQueryVo.searchNurturesWomenVo.isMaternityCard">
			   			<option></option>
						<option value="1" >是</option>
			   			<option value="0" >否</option>
			 		</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">有效期：</label>
			</div>
			<div class="grid_1 lable-right textTo">
				<label class="form-lbl">从</label>
			</div>
			<div class="grid_3" id="contraceptiveTimeDiv">
		    	<input type="text" id="maternityCardIssueTimeStart"    class="form-txt"  name="populationQueryVo.searchNurturesWomenVo.maternityCardIssueTimeStart"/>
			</div>					   
			<div class="grid_1 lable-right textTo">
				至
			</div>
			<div class="grid_3" id="contraceptiveTimeDiv">
		    	<input type="text" id="maternityCardIssueTimeEnd"    class="form-txt" name="populationQueryVo.searchNurturesWomenVo.maternityCardIssueTimeEnd" />
			</div>
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id=aidspopulation title="艾滋病人员" class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">艾滋病人员<a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">感染途径：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchAidspopulationsVo.infectway"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.constant.PropertyTypes@INFECT_WAY" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">犯罪类型：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchAidspopulationsVo.crimetype"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@CRIMETYPE" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">违法情况：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchAidspopulationsVo.violationsofthelaw"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@VIOLATIONSOFTHELAW" />
			</select>
		</div>
	</div>
</div>

<div class='clearLine'>&nbsp;</div>
<div id=otherAttentionPersonnel title="其他人员 " class="container container_24"
	style="display: none">
	<h3 class="personalCatagory">其他人员 <a href="javascript:;">click</a></h3>
	<div class="textBack clearfix">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchOtherAttentionPersonnelVo.gender.id"
				class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"  />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">职业：</label>
		</div>
		<div class="grid_8">
			<select name="populationQueryVo.searchOtherAttentionPersonnelVo.career.id"
				class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"  />
			</select>
		</div>
	
	</div>
</div>
</form>
<script type="text/javascript">
var dialogName = "<s:property value='#parameters'/>";


$(document).ready(function(){
	
	function personalListDisplay(id){
		$(id).each(function(index){
			var $catagoryChoice=$(this).find("a");
			var $catagoryContent=$(this).next();
			$catagoryChoice.click(function(){
				if($catagoryContent.is(":visible")){
					$catagoryContent.hide();
				}else{
					$catagoryContent.show();
				}
			})
		})
	}
	var personalListDisplay=personalListDisplay(".personalCatagory");
	
	$("input:checked").each(function(){
		$("#"+$(this).val()).show();
	});
	threeSelect({
		province : 'householdStaffVo_province',
		city : 'householdStaffVo_city',
		district : 'householdStaffVo_district'
	});
	
	$("#searchHouseholdStaffVo_reasonsDateStart,#searchHouseholdStaffVo_reasonsDateEnd").datepickers();
	
	$('#conditionInflowingStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionInflowingEndDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#conditionInflowingEndDate').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionInflowingStartDate").datepicker("option", "maxDate",date);
			}
		}
	});
	$('#conditionExpectedStartDatedue').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpectedEndDatedue").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionExpectedEndDatedue').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpectedStartDatedue").datepicker("option", "maxDate",date);
			}
		}
	});
	$('#conditionUnsettedTimeStart').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionUnsettedTimeEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionUnsettedTimeEnd').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionUnsettedTimeStart").datepicker("option", "maxDate",date);
			}
		}
	});
	
	$('#searchPositiveInfoVo_releaseOrBackDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#searchPositiveInfoVo_endreleaseOrBackDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#searchPositiveInfoVo_endreleaseOrBackDate').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#searchPositiveInfoVo_releaseOrBackDate").datepicker("option", "maxDate",date);
			}
		}
	});
	$("#conditionStartAvailableDate, #conditionEndAvailableDate").datePicker({
		yearRange: '1970:2030',
		defaultDate: "+1d",
		onSelect: function( selectedDate ) {
			var option = this.id == "conditionStartAvailableDate" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
			$("#conditionStartAvailableDate, #conditionEndAvailableDate").not( this ).datepicker( "option", option, date);
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
	
	$("#maternityCardIssueTimeStart,#maternityCardIssueTimeEnd").datepickers();

	jQuery.validator.addMethod("isDigit", function(value, element){
		if(value!=null && value.trim().length>0){

			var reg=/^[0-9]*[0-9][0-9]*$/;
			if(!reg.exec(value)){
				return false;
			}
			if(value<0 || value>9){
				return false
			}
		}
		return true;
	});
	$("#populationQueryFrom").formValidate({
		submitHandler: function(form) {
			search();
		},
		rules:{

		},
		messages:{},
		ignore:':hidden'
	});
	function search(){
		$("#searchResultList").setGridParam({
			url:"${path}/integrativeQuery/populationIntegrativeQuery/queryPopulation.action",
			mtype:"post",
			datatype: "json",
			page:1
		});
		
		
		var commJson= maintainFormData();
	
	//	$("#searchResultList").setPostData($.extend(dataJson));
		$("#searchResultList").setPostData($.extend({"currOrgId":$("#currOrgId").val()},populationQueryFromData(),commJson));

		$("#searchResultList").trigger("reloadGrid");
	}
	function maintainFormData(){
		return serializeObjectNew("maintainForm");
	}
	function populationQueryFromData(){
		var data = $("#populationQueryFrom").serializeObject({
					excludeWhenNull:["populationQueryVo.searchHouseholdStaffVo.outGone",
					                 "populationQueryVo.searchFloatingPopulationVo.hasMarriedProve",
					                 "populationQueryVo.searchPositiveInfoVo.isRepeat",
					                 "populationQueryVo.searchDruggyVo.isAdanon",
					                 "populationQueryVo.searchMentalPatientVo.isTreat",]
		});
		
		return data;
		
	}
	function serializeObjectNew(id){
		
		 var o = {};
		    var a = $("#"+id+"").serializeArray();
		    $.each(a, function() {
		        if (o[this.name]) {
		            if (!o[this.name].push) {
		                o[this.name] = [ o[this.name] ];
		            }
		            o[this.name]=o[this.name]+","+this.value;
		        } else {
		            o[this.name] = this.value || '';
		        }
		    });
		    return o;
	}
	
});



</script>
