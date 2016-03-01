<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchPositiveInfoForm">
	<pop:token />
	<div id="dialog-form" title="刑释人员" class="container container_24">
	<div class="grid_5 lable-right">
		<label class="form-lbl">关注状态：</label>
	</div>
	<div class="grid_7">
             <select id="isLock" name="searchPositiveInfoVo.isEmphasis" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">现在关注</option>
                 <option value="1">曾经关注</option>
             </select>
          </div>
         	<div class="grid_5 lable-right">
		<label class="form-lbl">死亡状态：</label>
	</div>
	<div class="grid_7">
             <select id="isDeath" name="searchPositiveInfoVo.isDeath" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">正常</option>
                 <option value="1">已死亡</option>
             </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchPositiveInfoVo.idCardNo" id="positiveInfo.idCardNo" maxlength="18"   class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchPositiveInfoVo.name" id="positiveInfo.name" maxlength="20"   class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">人员类型：</label></div>
	<div class="grid_7"><select name="searchPositiveInfoVo.positiveInfosType.id" id="type" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITIVEINFO" />
		</select>
	</div>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_7">
	    <input id="positiveInfo.mobileNumber" type="text" name="searchPositiveInfoVo.mobileNumber" class="form-txt"  maxlength="11"/>
	</div>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">原罪名（错）：</label>
	</div>
	<div class="grid_7">
	    <input id="positiveInfo.caseReason" type="text" name="searchPositiveInfoVo.caseReason" class="form-txt"  maxlength="50"/>
	</div>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">原判刑期：</label>
	</div>
	<div class="grid_7">
	    <input id="positiveInfo.imprisonmentDate" type="text" name="searchPositiveInfoVo.imprisonmentDate" class="form-txt"  maxlength="16"/>
	</div>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">原职业：</label>
	</div>
	<div class="grid_7">
	    <select name="searchPositiveInfoVo.agoProfession.id" id="positiveInfo.agoProfession.id" class="form-txt">
	        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${positiveInfo.agoProfession.id}" />
	    </select>
	</div>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">劳教(服刑)场所：</label>
	</div>
	<div class="grid_7">
	    <input id="positiveInfo.laborEduAddress" type="text" name="searchPositiveInfoVo.laborEduAddress" class="form-txt"  maxlength="50"/>
	</div>
	 
	 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchPositiveInfoVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchPositiveInfoVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">文化程度：</label>
	</div>
	<div class="grid_7">
	    <select name="searchPositiveInfoVo.schooling.id" class="form-txt">
	        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
	    </select>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
		<div class="grid_5 lable-right"><label class="form-lbl">解教（刑释）日期：</label></div>
		<div class="grid_7" id="birthday">
			<input type="text" name="searchPositiveInfoVo.releaseOrBackDate" id="positiveInfo.releaseOrBackDate" value='' class="form-txt" readonly="readonly" ></input>
		</div>
		<div class="grid_5 lable-right"><label class="form-lbl"> 至：</label></div>
		<div class="grid_7" id="birthday">
			<input type="text" name="searchPositiveInfoVo.endReleaseOrBackDate" id="positiveInfo.endReleaseOrBackDate" value='' class="form-txt" readonly="readonly" ></input>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">安置日期 ：</label>
		</div>
		<div class="grid_7" id="resettlementDate">
			<input type="text" name="searchPositiveInfoVo.resettlementDate" id="positiveInfo.resettlementDate" value='' class="form-txt" readonly="readonly"  />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl"> 至：</label>
		</div>
		<div class="grid_7" id="resettlementDate">
			<input type="text" name="" id="positiveInfo.endResettlementDate" value='' class="form-txt" readonly="readonly"  />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">重犯日期 ：</label>
		</div>
		<div class="grid_7" id="crimeDate">
			<input type="text" name="searchPositiveInfoVo.crimeDate" id="positiveInfo.crimeDate" value='' class="form-txt" readonly="readonly"  />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl"> 至：</label>
		</div>
		<div class="grid_7" id="resettlementDate">
			<input type="text" name="searchPositiveInfoVo.endCrimeDate" id="positiveInfo.endCrimeDate" value='' class="form-txt" readonly="readonly"  />
		</div>
		<div class="grid_5 lable-right"><label class="form-lbl"> 出生日期：</label></div>
		<div class="grid_7" id="birthday"><input type="text" name="searchPositiveInfoVo.birthday" id="positiveInfo.birthday" value='<fmt:formatDate value="${positiveInfo.birthday}" type="date" pattern="yyyy-MM-dd" />' class="form-txt" readonly/></div>
		<div class="grid_5 lable-right"><label class="form-lbl"> 至：</label></div>
		<div class="grid_7" id="birthday"><input type="text" name="searchPositiveInfoVo.endBirthday" id="positiveInfo.endBirthday" value='<fmt:formatDate value="${positiveInfo.birthday}" type="date" pattern="yyyy-MM-dd" />' class="form-txt" readonly/></div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_7">
			<select name="searchPositiveInfoVo.gender.id" id="searchPositiveInfoVo.gender.id" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"  />
			</select>
		</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">职业：</label>
		</div>
		<div class="grid_7">
		    <select name="searchPositiveInfoVo.career.id" id="positiveInfo.career.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${positiveInfo.career.id}" />
		    </select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
		<label class="form-lbl">户籍地：</label></div>
		<div class="grid_5">
			<select name="searchPositiveInfoVo.province" id="province" class="form-txt"></select>
		</div>
		<div class="grid_1">
			省
		</div>
		
		<div class="grid_5">
			<select name="searchPositiveInfoVo.city" id="city" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			市
		</div>
		
		<div class="grid_5">
			<select name="searchPositiveInfoVo.district" id="district" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			县
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">户籍详址：</label>
		</div>
		<div class="grid_19">
			<input id="positiveInfo.nativePlaceAddress" style="width:99%" type="text" name="searchPositiveInfoVo.nativePlaceAddress" class="form-txt"  maxlength="50" />
		</div>
		
		<div class="grid_5 lable-right">
		    <label class="form-lbl">工作单位或就读学校：</label>
		</div>
		<div class="grid_19">
		    <input id="positiveInfo.workUnit" type="text" name="searchPositiveInfoVo.workUnit" class="form-txt" style="width:99%" maxlength="50"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right"><label class="form-lbl">常住地址：</label></div>
		<div class="grid_19">
			<input id="positiveInfo.currentlyAddress" style="width:99%" type="text" name="searchPositiveInfoVo.currentlyAddress" class="form-txt" maxlength="50" />
		</div>
	</div>
</div>	
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#positiveInfoDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#positiveInfoDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	var idCard = $("#searchByIdcard").val();
	if(idCard=="请输入身份证号码"){
		$("#positiveInfo\\.idCardNo").val("");
	}else{
		$("#positiveInfo\\.idCardNo").val(idCard);
	}
	threeSelect({
		province:"province",
		city:"city",
		district:"district",
		provinceValue:$('#nativeProvinceValue').val(),
		cityValue:$('#nativeCityValue').val(),
		districtValue:$('#nativeDistrictValue').val()
	});

	$('#positiveInfo\\.birthday').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$('#positiveInfo\\.endBirthday').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$('#positiveInfo\\.releaseOrBackDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$('#positiveInfo\\.endReleaseOrBackDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$('#positiveInfo\\.resettlementDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$('#positiveInfo\\.endResettlementDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$('#positiveInfo\\.crimeDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$('#positiveInfo\\.endCrimeDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
});

</script>


