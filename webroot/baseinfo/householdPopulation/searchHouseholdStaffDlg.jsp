<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="户籍人口" class="container container_24">
<div class="grid_4 lable-right">
		<label class="form-lbl">注销状态：</label>
</div>
<div class="grid_8">
		<select id="logout" name="householdStaffVo.logout" class="form-txt">
			<option value="-1" selected="selected">全部</option>
 			<option value="0">未注销</option>
 			<option value="1">已注销</option>
		</select>
</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">死亡状态：</label>
</div>
<div class="grid_8">
	<select id="_isDeath"  name="householdStaffVo.isDeath" class="form-txt">
		<option value="-1" selected="selected">全部</option>
		<option value="0">正常</option>
		<option value="1">已死亡</option>
	</select>
</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">姓名：</label>
</div>
<div class="grid_8">
	<input type="text" name="householdStaffVo.name" id="householdStaffVo.name" maxlength="20"   class="form-txt" />
</div>

<div class="grid_4 lable-right">
	<label class="form-lbl">身份证号码：</label>
</div>
<div class="grid_8">
	<input type="text" name="householdStaffVo.idCardNo" id="householdStaffVo.idCardNo" maxlength="18"   class="form-txt" />
</div>


<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lbl">户口号：</label>
</div>
<div class="grid_8">
	<input type="text" name="householdStaffVo.accountNumber" id="householdStaffVo.accountNumber" maxlength="20"   class="form-txt" />
</div>

<div class="grid_4 lable-right">
	<label class="form-lbl">性别：</label>
	</div>
<div class="grid_8">
	<select name="householdStaffVo.gender" id="householdStaffVo.gender" class="form-txt" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>
<!-- <div class="grid_4 lable-right"> -->
<!-- 	<label class="form-lbl">人户状态：</label> -->
<!-- 	</div> -->
<!-- <div class="grid_8"> -->
<%-- 	<select name="householdStaffVo.residentStatus" id="householdStaffVo.residentStatus" class="form-txt" > --%>
<%-- 		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENT_STATUS" /> --%>
<%-- 	</select> --%>
<!-- </div> -->
		<div class="grid_4 lable-right">
			<label class="form-lbl">人户状态：</label>
		</div>
		<div class="grid_6">
			<select id="_residentStatus" name="householdStaffVo.residentStatus" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENT_STATUS" />
			</select>
		</div>
		
<div class="grid_6 lable-right">
	<label class="form-lbl">工作单位或就读学校：</label>
</div>
<div class="grid_8">
	<input type="text" name="householdStaffVo.workUnit" id="householdStaffVo.workUnit" maxlength="20"   class="form-txt" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl"> 出生日期：</label></div>
<div class="grid_8" id="birthdayStart"><input type="text" name="householdStaffVo.birthday" id="householdStaffVo.birthday" value='<s:date name="householdStaffVo.birthday" format="yyyy-MM-dd"/>' class="form-txt" readonly/></div>

<div class="grid_4 lable-right"><label class="form-lbl"> 至：</label></div>
<div class="grid_8" id="birthdayEnd"><input type="text" name="householdStaffVo.endBirthday" id="householdStaffVo.endBirthday" value='<s:date name="householdStaffVo.birthday" format="yyyy-MM-dd"/>' class="form-txt" readonly/></div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">是否外出：</label></div>
<div class="grid_8" id="outGoneBoolean">
<select id="householdStaffVo.outGoneBoolean"  name="householdStaffVo.outGoneBoolean" class="form-txt">
		<option value="" selected="selected">全部</option>
		<option value="1">是</option>
		<option value="0">否</option>
</select></div>

<div class="grid_4 lable-right"><label class="form-lbl">外出原因：</label></div>
<div class="grid_8" id="outReasonsId_div">
	<select id="householdStaffVo.outReasonsId" name="householdStaffVo.outReasonsId" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@OUT_REASON" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		<label class="form-lbl">外出去向：</label></div>
		<div class="grid_5">
			<select name="householdStaffVo.outProvince" id="householdStaffVo.outProvince" class="form-txt"></select>
		</div>
		<div class="grid_1">
			省
		</div>
		
		<div class="grid_5">
			<select name="householdStaffVo.outCity" id="householdStaffVo.outCity" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			市
		</div>
		
		<div class="grid_5">
			<select name="householdStaffVo.outDistrict" id="householdStaffVo.outDistrict" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			县
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right"><label class="form-lbl">注销时间：</label></div>
			<div class="grid_8" id="logoutDateStart"><input type="text" name="householdStaffVo.logoutDateStart" id="householdStaffVo.logoutDateStart" value='<s:date name="householdStaffVo.logoutDateStart" format="yyyy-MM-dd"/>' class="form-txt" readonly/></div>
			
			<div class="grid_4 lable-right"><label class="form-lbl"> 至：</label></div>
			<div class="grid_8" id="logoutDateEnd"><input type="text" name="householdStaffVo.logoutDateEnd" id="householdStaffVo.logoutDateEnd" value='<s:date name="householdStaffVo.logoutDateEnd" format="yyyy-MM-dd"/>' class="form-txt" readonly/></div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right"><label class="form-lbl">注销原因：</label></div>
			<div class="grid_20" id="logoutReason"><input type="text" name="householdStaffVo.logoutReason" id="householdStaffVo.logoutReason" class="form-txt"/></div>
 <div class='clearLine'></div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
	<div id="showMoreCtt" style="display: none">
		<div class="grid_4 lable-right">
	    	<label class="form-lbl">固定电话：</label>
		</div>
		<div class="grid_8" title="电话格式只支持数字和横线如：010-88888888">
		    <input id="householdStaffVo.telephone" type="text" name="householdStaffVo.telephone" class="form-txt" maxlength="15"/>
		</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_8">
		    <input id="householdStaffVo.mobileNumber" type="text" name="householdStaffVo.mobileNumber" class="form-txt"  maxlength="11"/>
		</div>
		<div class="clearLine">&nbsp;</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">曾用名：</label>
		</div>
		<div class="grid_8">
		    <input id="householdStaffVo.usedName" type="text" name="householdStaffVo.usedName" class="form-txt"  maxlength="11"/>
		</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">职业：</label>
		</div>
		<div class="grid_8">
			<select name="householdStaffVo.career" id="householdStaffVo.career" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">婚姻状况：</label>
		</div>
		<div class="grid_8">
			<select name="householdStaffVo.maritalState" id="householdStaffVo.maritalState" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
		    </select>
		</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">户口类别：</label>
		</div>
		<div class="grid_8">
			<select name="householdStaffVo.residenceType" id="householdStaffVo.residenceType" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">宗教信仰：</label>
		</div>
		<div class="grid_8">
			<select name="householdStaffVo.faith" id="householdStaffVo.faith" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" />
		    </select>
		</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">血型：</label>
		</div>
		<div class="grid_8">
			<select name="householdStaffVo.bloodType" id="householdStaffVo.bloodType" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">文化程度：</label>
		</div>
		<div class="grid_8">
		    <select name="householdStaffVo.schooling" id="householdStaffVo.schooling" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
		    </select>
		</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">政治面貌：</label>
		</div>
		<div class="grid_8">
			<select name="householdStaffVo.politicalBackground" id="householdStaffVo.politicalBackground" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		    <label class="form-lbl">民族：</label>
		</div>
		<div class="grid_8">
			<select name="householdStaffVo.nation" id="householdStaffVo.nation" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" />
		    </select>
		</div>
		<div class="grid_4 lable-right">
	    	<label class="form-lbl">电子邮箱：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="householdStaffVo.email" id="householdStaffVo.email" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		<label class="form-lbl">户籍地：</label></div>
		<div class="grid_5">
			<select name="householdStaffVo.province" id="householdStaffVo.province" class="form-txt"></select>
		</div>
		<div class="grid_1">
			省
		</div>
		
		<div class="grid_5">
			<select name="householdStaffVo.city" id="householdStaffVo.city" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			市
		</div>
		
		<div class="grid_5">
			<select name="householdStaffVo.district" id="householdStaffVo.district" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			县
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right"><label class="form-lbl">常住地址：</label></div>
		<div class="grid_20">
				<input id="householdStaffVo.currentAddress" style="width:99%" type="text" name="householdStaffVo.currentAddress" class="form-txt" width="1000"  maxlength="50" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">户籍详址：</label>
		</div>
		<div class="grid_20">
			<input id="householdStaffVo.nativePlaceAddress" style="width:99%" type="text" name="householdStaffVo.nativePlaceAddress" class="form-txt"  maxlength="50" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">临时居所：</label>
		</div>
		<div class="grid_20">
			<input id="householdStaffVo.otherAddress" style="width:99%" type="text" name="householdStaffVo.otherAddress" class="form-txt"  maxlength="50" />
		</div>
    </div>
</div>		
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#householdStaffPopulationDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#householdStaffPopulationDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	threeSelect({
		province:"householdStaffVo\\.province",
		city:"householdStaffVo\\.city",
		district:"householdStaffVo\\.district"
	});
	
	threeSelect({
		province:"householdStaffVo\\.outProvince",
		city:"householdStaffVo\\.outCity",
		district:"householdStaffVo\\.outDistrict"
	});
	$('#householdStaffVo\\.birthday').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});

	$('#householdStaffVo\\.endBirthday').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	
	$('#householdStaffVo\\.logoutDateStart').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});

	$('#householdStaffVo\\.logoutDateEnd').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	
	$("#isLock").change(function(){
		displaySelect();
		if($("#isLock").val()=='1'){
			$("#logout").css("display","inline");
		}else if($("#isLock").val()=='2'){
			$("#_isDeath").css("display","inline");
		}else{
			$("#_residentStatus").css("display","inline");
		}
	});
	
	function displaySelect(){
		$("#logout").css("display","none");
		$("#_isDeath").css("display","none");
		$("#_residentStatus").css("display","none");
	}
});

</script>


