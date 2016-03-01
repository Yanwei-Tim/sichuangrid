<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchNurturesWomenForm">
<pop:token />
	<div id="search-condition-form" title="育妇查询" class="container container_24">
		<div class="grid_6 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
		<div class="grid_7">
            <select id="isLock" name="searchNurturesWomenVo.isEmphasis" class="form-txt">
                <option value="-1" selected="selected">全部</option>
                <option value="0">现在关注</option>
                <option value="1">曾经关注</option>
            </select>
		</div>
        <div class="grid_4 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
		<div class="grid_7">
            <select id="isDeath" name="searchNurturesWomenVo.isDeath" class="form-txt">
                <option value="-1" selected="selected">全部</option>
                <option value="0">正常</option>
                <option value="1">已死亡</option>
            </select>
		</div>
		<div class="grid_6 lable-right"><label>身份证号码：</label></div>
		<div class="grid_7"><input type="text" id="idCardNo"
			maxlength="18" class="form-txt" name="searchNurturesWomenVo.idCardNo" />
		</div>
		<div class="grid_4 lable-right"><label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7"><input type="text" id="name" maxlength="20"
			class="form-txt" name="searchNurturesWomenVo.name" /></div>
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">出生日期从：</label>
		</div>
		<div class="grid_7" id="birthdayDiv"><input type="text"
			id="birthdayStart" class="form-txt"
			name="searchNurturesWomenVo.birthdatStart" /></div>
		<div class="grid_4 lable-right">&nbsp;至：</div>
		<div class="grid_7" id="birthdayDiv"><input type="text"
			id="birthdayEnd" class="form-txt"
			name="searchNurturesWomenVo.birthdatEnd" /></div>
		<div class='clearLine'></div>
		<div class="grid_6 lable-right"><label class="form-lbl">子女数从：</label>
		</div>
		<div class="grid_7" id="childNumberDiv"><input type="text"
			id="childNumberStart" maxlength="2" class="form-txt"
			name="searchNurturesWomenVo.childNumberStart" /></div>
		<div class="grid_4 lable-right">&nbsp;至：</div>
		<div class="grid_7" id="childNumberDiv"><input type="text"
			id="childNumberEnd" maxlength="2" class="form-txt"
			name="searchNurturesWomenVo.childNumberEnd" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lb1">夫妻双方独生子女情况：</label>
		</div>
		<div class="grid_7"><select
			name="searchNurturesWomenVo.onechildOfCouple" id="onechildOfCouple"
			class="form-txt">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@ONE_CHILD_SITUATION" />
		</select></div>
		<div class="grid_4 lable-right"><label class="form-lb1">是否未婚先孕：</label></div>
		<div class="grid_7"><select id="isSingleChildCard"
			class="form-txt" name="searchNurturesWomenVo.isUnmarriedPregnant">
			<option></option>
			<option value="1">是</option>
			<option value="0">否</option>
		</select></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">独生子女领证情况：</label>
		</div>
		<div class="grid_7"><select id="isSingleChildCard"
			class="form-txt" name="searchNurturesWomenVo.isSingleChildCard">
			<option></option>
			<option value="0">有领</option>
			<option value="1">未领</option>
		</select></div>
		<div class="grid_4 lable-right"><label class="form-lbl">独生子女证号：</label>
		</div>
		<div class="grid_7"><input type="text" id="singleChildCardno"
			maxlength="50" class="form-txt"
			name="searchNurturesWomenVo.singleChildCardno" /></div>
		<div class='clearLine'></div>
		<div class="grid_6 lable-right"><label class="form-lbl">独生子女领证时间从：</label>
		</div>
		<div class="grid_7" id="licenseTimeDiv"><input type="text"
			id="singleChildCardIssueStart" class="form-txt"
			name="searchNurturesWomenVo.singleChildCardIssueStart" /></div>
		<div class="grid_4 lable-right">&nbsp;至：</div>
		<div class="grid_7" id="licenseTimeDiv"><input type="text"
			id="singleChildCardIssueEnd" class="form-txt"
			name="searchNurturesWomenVo.singleChildCardIssueEnd" /></div>
		<div class='clearLine'></div>
		<div class="grid_6 lable-right"><label class="form-lbl">领证情况：</label>
		</div>
		<div class="grid_7"><select id="licenseSituation" class="form-txt"
			name="searchNurturesWomenVo.licenseSituation">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@LICENSE_SITUATION" />
		</select></div>
		<div class="grid_4 lable-right"><label>生育服务证号：</label></div>
		<div class="grid_7"><input type="text" id="fertilityServicesNo"
			maxlength="50" class="form-txt"
			name="searchNurturesWomenVo.fertilityServicesNo" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">领证时间从：</label>
		</div>
		<div class="grid_7" id="licenseTimeDiv"><input type="text"
			id="licenseTimeStart" class="form-txt"
			name="searchNurturesWomenVo.licenseTimeStart" /></div>
		<div class="grid_4 lable-right">&nbsp;至：</div>
		<div class="grid_7" id="licenseTimeDiv"><input type="text"
			id="licenseTimeEnd" class="form-txt"
			name="searchNurturesWomenVo.licenseTimeEnd" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lb1">发证单位：</label>
		</div>
		<div class="grid_7"><input type="text" id="certifiedUnit"
			class="form-txt" name="searchNurturesWomenVo.certifiedUnit" /></div>
		<div class="grid_4  lable-right"><label class="form-lb1">是否征收:</label>
		</div>
		<div class="grid_7"><select id="isSingleChildCard"
			class="form-txt" name="searchNurturesWomenVo.isLevied">
			<option></option>
			<option value="1">是</option>
			<option value="0">否</option>
		</select></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label>避孕方法：</label></div>
		<div class="grid_18"><input type="text" id="contraceptiveMethod"
			maxlength="50" class="form-txt" style="width: 99%"
			name="searchNurturesWomenVo.contraceptiveMethod" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">避孕起始时间从：</label>
		</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="contraceptiveTimeStart" class="form-txt"
			name="searchNurturesWomenVo.contraceptiveTimeStart" /></div>
		<div class="grid_4 lable-right">&nbsp;至：</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="contraceptiveTimeEnd" class="form-txt"
			name="searchNurturesWomenVo.contraceptiveTimeEnd" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lb1">婚育证编号：</label>
		</div>
		<div class="grid_7"><input type="text" id="maternityCardNo"
			maxlength="50" class="form-txt"
			name="searchNurturesWomenVo.maternityCardNo" /></div>
		<div class="grid_4 lable-right"><label class="form-lb1"> <!-- <input  type="checkbox" id="isMaternityCard" class="dialogtip" value="1" name="searchNurturesWomenVo.isMaternityCard"/>
				 --> 是否有婚育证:</label></div>
		
		<div class="grid_7"><select id="isSingleChildCard"
			class="form-txt" name="searchNurturesWomenVo.isMaternityCard">
			<option></option>
			<option value="1">是</option>
			<option value="0">否</option>
		</select></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lb1">发放单位：</label>
		</div>
		<div class="grid_18"><input type="text" id="maternityCardUnit"
			maxlength="50" class="form-txt" style="width: 99%"
			name="searchNurturesWomenVo.maternityCardUnit" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">发放时间从：</label>
		</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="maternityCardIssueTimeStart" class="form-txt"
			name="searchNurturesWomenVo.maternityCardIssueTimeStart" /></div>
		<div class="grid_4 lable-right">至：</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="maternityCardIssueTimeEnd" class="form-txt"
			name="searchNurturesWomenVo.maternityCardIssueTimeEnd" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">有效期从：</label>
		</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="maternityCardValidityTimeStart" class="form-txt"
			name="searchNurturesWomenVo.maternityCardValidityTimeStart" /></div>
		<div class="grid_4 lable-right">&nbsp;至：</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="maternityCardValidityTimeEnd" class="form-txt"
			name="searchNurturesWomenVo.maternityCardValidityTimeEnd" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">检查时间：</label>
		</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="maternityCardCheckTimeStart" class="form-txt"
			name="searchNurturesWomenVo.maternityCardCheckTimeStart" /></div>
		<div class="grid_4 lable-right">&nbsp;至：</div>
		<div class="grid_7" id="contraceptiveTimeDiv"><input type="text"
			id="maternityCardCheckTimeEnd" class="form-txt"
			name="searchNurturesWomenVo.maternityCardCheckTimeEnd" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">户籍地：</label>
		</div>
		<div class="grid_5"><select id="province" class="form-txt"
			name="searchNurturesWomenVo.nativeProvince">
		
		</select></div>
		<div class="grid_1 lable-right"><label class="form-lbl">省&nbsp;&nbsp;</label>
		</div>
		<div class="grid_5"><select id="city" class="form-txt"
			name="searchNurturesWomenVo.nativeCity">
		
		</select></div>
		<div class="grid_1 lable-right"><label class="form-lbl">市&nbsp;&nbsp;</label>
		</div>
		<div class="grid_5"><select id="district" class="form-txt"
			name="searchNurturesWomenVo.nativeDistrict">
		
		</select></div>
		<div class="grid_1 lable-right"><label class="form-lbl">县&nbsp;&nbsp;</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">户籍地详址：</label>
		</div>
		<div class="grid_18"><input type="text" id="nativePlaceAddress"
			maxlength="50" class="form-txt" style="width: 99%"
			name="searchNurturesWomenVo.nativePlaceAddress" /></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right"><label class="form-lbl">常住地址：</label>
		</div>
		<div class="grid_18"><input type="text" id="currentAddress"
			maxlength="50" class="form-txt" style="width: 99%"
			name="searchNurturesWomenVo.currentAddress" /></div>
		<div class='clearLine'></div>
		<div class="grid_6 lable-right"><label>丈夫身份证号码：</label></div>
		<div class="grid_7"><input type="text" id="manIdCardNo"
			maxlength="18" class="form-txt"
			name="searchNurturesWomenVo.manIdCardNo" /></div>
		<div class="grid_4 lable-right"><label class="form-lbl">丈夫姓名：</label>
		</div>
		<div class="grid_7"><input type="text" id="manName" maxlength="20"
			class="form-txt" name="searchNurturesWomenVo.manName" /></div>
	
		 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="服务成员：" name="memberLabelName"/>
			<jsp:param value="searchNurturesWomenVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="服务记录：" name="recordLabelName"/>
			<jsp:param value="searchNurturesWomenVo.hasServiceTeamRecord" name="recordSelectName"/>
		</jsp:include>
		
		<div style="height: 40px;"></div>
		<div class='clearLine'></div>
		<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
			<div id="showMoreCtt" style="display: none">
				<div class="grid_6 lable-right"><label class="form-lbl">民族：</label>
				</div>
				<div class="grid_7"><select id="nation" class="form-txt"
					name="searchNurturesWomenVo.nation">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" />
				</select></div>
				<div class="grid_4 lable-right"><label class="form-lbl">政治面貌：</label>
				</div>
				<div class="grid_7"><select id="politicalBackground"
					class="form-txt" name="searchNurturesWomenVo.politicalBackground">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
				</select></div>
				<div class='clearLine'></div>
				<div class="grid_6 lable-right"><label class="form-lbl">婚姻状况：</label>
				</div>
				<div class="grid_7"><select id="maritalState" class="form-txt"
					name="searchNurturesWomenVo.maritalState">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
				</select></div>
				<div class="grid_4 lable-right"><label class="form-lbl">文化程度：</label>
				</div>
				<div class="grid_7"><select id="schooling" class="form-txt"
					name="searchNurturesWomenVo.schooling">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
				</select></div>
				<div class='clearLine'></div>
				<div class="grid_6 lable-right"><label class="form-lbl">工作单位或就读学校：</label>
				</div>
				<div class="grid_7"><input type="text" id="workUnit"
					maxlength="50" class="form-txt" name="searchNurturesWomenVo.workUnit" />
				</div>
				<div class="grid_4 lable-right"><label class="form-lbl">职业：</label>
				</div>
				<div class="grid_7"><select id="career" class="form-txt"
					name="searchNurturesWomenVo.career">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" />
				</select></div>
				<div class='clearLine'></div>
				<div class="grid_6 lable-right"><label class="form-lbl">身高从：</label>
				</div>
				<div class="grid_7" id="statureDiv"><input type="text"
					id="statureStart" maxlength="3" class="form-txt"
					name="searchNurturesWomenVo.statureStart" /></div>
				<div class="grid_4 lable-right">&nbsp;至：</div>
				<div class="grid_7" id="statureDiv"><input type="text"
					id="statureEnd" maxlength="3" class="form-txt"
					name="searchNurturesWomenVo.statureEnd" /></div>
				<div class='clearLine'></div>
				<div class="grid_6 lable-right"><label class="form-lbl">血型：</label>
				</div>
				<div class="grid_7"><select id="bloodType" class="form-txt"
					name="searchNurturesWomenVo.bloodType">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
				</select></div>
				<div class="grid_4 lable-right"><label class="form-lbl">宗教信仰：</label>
				</div>
				<div class="grid_7"><select id="faith" class="form-txt"
					name="searchNurturesWomenVo.faith">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" />
				</select></div>
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_6 lable-right"><label class="form-lbl">电子邮箱：</label>
				</div>
				<div class="grid_7"><input type="text" id="email" maxlength="50"
					class="form-txt" name="searchNurturesWomenVo.email" /></div>
				<div class="grid_4 lable-right"><label class="form-lbl">联系手机：</label>
				</div>
				<div class="grid_7"><input type="text" id="mobilNumber"
					maxlength="11" class="form-txt"
					name="searchNurturesWomenVo.mobileNumber" /></div>
				
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_6 lable-right"><label class="form-lbl">初婚时间从：</label>
				</div>
				<div class="grid_7" id="firstMarriageTimeDiv"><input type="text"
					id="firstMarriageTimeStart" class="form-txt"
					name="searchNurturesWomenVo.firstMarriageTimeStart" /></div>
				<div class="grid_4 lable-right">&nbsp;至：</div>
				<div class="grid_7" id="firstMarriageTimeDiv"><input type="text"
					id="firstMarriageTimeEnd" class="form-txt"
					name="searchNurturesWomenVo.firstMarriageTimeEnd" /></div>
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_6 lable-right"><label class="form-lbl">离婚时间从：</label>
				</div>
				<div class="grid_7" id="marriageRegistrationTimeDiv"><input
					type="text" id="marriageRegistrationTimeStart" class="form-txt"
					name="searchNurturesWomenVo.marriageRegistrationTimeStart" /></div>
				<div class="grid_4 lable-right">&nbsp;至：</div>
				<div class="grid_7" id="marriageRegistrationTimeDiv"><input
					type="text" id="marriageRegistrationTimeEnd" class="form-txt"
					name="searchNurturesWomenVo.marriageRegistrationTimeEnd" /></div>
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_6 lable-right"><label class="form-lbl">最近再婚时间从：</label>
				</div>
				<div class="grid_7" id="marriageOrDivorceTimeDiv"><input
					type="text" id="marriageOrDivorceTimeStart" class="form-txt"
					name="searchNurturesWomenVo.marriageOrDivorceTimeStart" /></div>
				<div class="grid_4 lable-right">&nbsp;至：</div>
				<div class="grid_7" id="marriageOrDivorceTimeDiv"><input
					type="text" id="marriageOrDivorceTimeEnd" class="form-txt"
					name="searchNurturesWomenVo.marriageOrDivorceTimeEnd" /></div>
				<div class='clearLine'>&nbsp;</div>	
		</div>
	</div>
</form>
<script>
$("#birthdayStart,#birthdayEnd").datepickers(); 
$("#licenseTimeStart,#licenseTimeEnd").datepickers();
$("#contraceptiveTimeStart,#contraceptiveTimeEnd").datepickers();
$("#firstMarriageTimeStart,#firstMarriageTimeEnd").datepickers();
$("#marriageRegistrationTimeStart,#marriageRegistrationTimeEnd").datepickers();
$("#marriageOrDivorceTimeStart,#marriageOrDivorceTimeEnd").datepickers();

$("#singleChildCardIssueStart,#singleChildCardIssueEnd").datepickers();
$("#maternityCardValidityTimeStart,#maternityCardValidityTimeEnd").datepickers();
$("#maternityCardIssueTimeStart,#maternityCardIssueTimeEnd").datepickers();
$("#maternityCardCheckTimeStart,#maternityCardCheckTimeEnd").datepickers();


$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#nurturesWomenDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#nurturesWomenDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
    
	threeSelect({province:'province',
		city:'city',
		district:'district'
		});
})
</script>