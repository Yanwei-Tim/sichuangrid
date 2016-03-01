<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchMentalPatientForm">
<pop:token />
<div id="search-condition-form" title="严重精神障碍患者查询" class="container container_24">
	<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
	</div>
	<div class="grid_7">
            <select id="isLock" name="searchMentalPatientVo.isEmphasis" class="form-txt">
                <option value="-1" selected="selected">全部</option>
                <option value="0">现在关注</option>
                <option value="1">曾经关注</option>
            </select>
            </div>
          <div class="grid_5 lable-right">
		  <label class="form-lbl">死亡状态：</label>
	</div>
	<div class="grid_7">
             <select id="isDeath" name="searchMentalPatientVo.isDeath" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">正常</option>
                 <option value="1">已死亡</option>
             </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
    	<input type="text" id="conditionIdCardNo" name="searchMentalPatientVo.idCardNo" maxlength="18" class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionName" name="searchMentalPatientVo.name" class="form-txt" maxlength="20"/>
	</div>
	<div class="grid_5 lable-right">
        <label class="form-lbl">出生日期 ：</label>
    </div>
    <div class="grid_7">
        <input type="text" id="startBirthday" name="searchMentalPatientVo.startBirthday" readonly="readonly" class="form-txt" />
    </div>
    <div class="grid_5 lable-right" >
        <label class="form-lbl">至：</label>
    </div>
    <div class="grid_7">
        <input type="text" id="endBirthday" name="searchMentalPatientVo.endBirthday" readonly="readonly" class="form-txt" />
    </div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">性别：</label>
	</div>
	<div class="grid_7">
		<select id="conditionGender" class="form-txt" name="searchMentalPatientVo.genderId">
   			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
 		</select>
 	</div>
 	<div  class="grid_5 lable-right">
        <label class="form-lbl">危险程度：</label>
    </div>
    <div class="grid_7">
        <select id="conditionDangerLevel" name="searchMentalPatientVo.dangerLevelId" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MENTALPATIENT_DANGEROUS_LEVEL"/>
        </select>
    </div>
    <div class='clearLine'></div>
    <div  class="grid_5 lable-right">
        <label class="form-lbl">精神病类型：</label>
    </div>
    <div class="grid_7">
        <select id="psychosisType" name="searchMentalPatientVo.psychosisTypeId" class="form-txt">
            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PSYCHOSIS_TYPES"/>
        </select>
    </div>
    <div class='clearLine'></div>
 	<div class="grid_5 lable-right">
        <label class="form-lbl">是否接受过治疗：</label>
    </div>
    <div  class="grid_7">
    <!--  
        <select  id="conditionIsTreat" name="searchMentalPatientVo.isTreat"  style="width:145px">
            <option value=""></option>
            <option value="true">是</option>
            <option value="false">否</option>
        </select>
    -->
    <s:select name="searchMentalPatientVo.hasIsTreatType.code" listKey="code" list="@com.tianque.search.vo.QuickFilterType@DEFAULT_BOOLEAN_STATE_FILTER_TYPES" ></s:select>    
    </div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">康复机构：</label>
	</div>
	<div class="grid_7">
    	<input type="text" id="conditionCureDepartment" name="searchMentalPatientVo.cureDepartment" maxlength="18" class="form-txt" />
	</div>
 	<div class="grid_5 lable-right">
  		<label class="form-lbl">户籍地：</label>
  	</div>
  	<div class="grid_6">
		<select id="conditionProvince" name="searchMentalPatientVo.nativeProvince" class="form-txt"></select>
 	</div>
  	<div class="grid_1 lable-right">
  		<label class="form-lbl">省</label>
  	</div>
  	<div class="grid_5">
		<select id="conditionCity" name="searchMentalPatientVo.nativeCity" class="form-txt"></select>
	</div>
  	<div class="grid_1 lable-right">
  		<label class="form-lbl">市</label>
  	</div>
  	<div class="grid_5">
		<select id="conditionDistrict" name="searchMentalPatientVo.nativeDistrict"  class="form-txt"></select>
 	</div>
  	<div class="grid_1 lable-right">
  		<label class="form-lbl">县</label>
  	</div>
  	
  	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchMentalPatientVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchMentalPatientVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
   	<div class='clearLine'></div>

			
  	
  	<div class='clearLine'></div>
 	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
		    <div class="grid_5 lable-right">
		 		<label class="form-lbl">户籍详址：</label>
		 	</div>
		  	<div class="grid_19" >
			    <input type="text" id="conditionNativePlaceAddress" name="searchMentalPatientVo.nativePlaceAddress"  maxlength="50" class="form-txt" />
		  	</div>
			<div class="grid_5 lable-right">
		  		 <label class="form-lbl">常住地址：</label>
		 	</div>
		  	<div class="grid_19" >
			    <input type="text" id="conditionCurrentAddress" name="searchMentalPatientVo.currentAddress"  maxlength="50" class="form-txt" />
		  	</div>
	       <div  class="grid_5 lable-right">
	        	<label class="form-lbl">民族：</label>
		    </div>
		    <div class="grid_7">
		        <select id="conditionNation" name="searchMentalPatientVo.nation.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION"/>
		        </select>
		    </div>
		    <div  class="grid_5 lable-right">
	        	<label class="form-lbl">政治面貌：</label>
		    </div>
		    <div class="grid_7">
		        <select id="conditionPoliticalBackground" name="searchMentalPatientVo.politicalBackground.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND"/>
		        </select>
		    </div>
	       <div  class="grid_5 lable-right">
	        	<label class="form-lbl">婚姻状况：</label>
		    </div>
		    <div class="grid_7">
		        <select id="conditionMaritalState" name="searchMentalPatientVo.maritalState.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"/>
		        </select>
		    </div>
	       <div  class="grid_5 lable-right">
	        	<label class="form-lbl">文化程度：</label>
		    </div>
		    <div class="grid_7">
		        <select id="conditionSchooling" name="searchMentalPatientVo.schooling.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
		        </select>
		    </div>
	       <div  class="grid_5 lable-right">
	        	<label class="form-lbl">职业：</label>
		    </div>
		    <div class="grid_7">
		        <select id="conditionCareer" name="searchMentalPatientVo.career.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"/>
		        </select>
		    </div>
	        <div class="grid_5 lable-right">
			    <label class="form-lbl">工作单位或就读学校：</label>
			</div>
			<div class="grid_7">
			    <input type="text" id="conditionWorkUnit" name="searchMentalPatientVo.workUnit" maxlength="50" class="form-txt" />
		  	</div>
	          <div class="grid_5 lable-right">
			    <label class="form-lbl">身高 ：</label>
			</div>
			 <div class="grid_7">
		        <input type="text" id="conditionStartStature" name="searchMentalPatientVo.startStature"  class="form-txt" />
		    </div>
		    <div class="grid_5 lable-right" >
		        <label class="form-lbl">至：</label>
		    </div>
		    <div class="grid_7">
		        <input type="text" id="conditionEndStature" name="searchMentalPatientVo.endStature" class="form-txt" />
		    </div>
      	    <div  class="grid_5 lable-right">
	        	<label class="form-lbl">血型：</label>
		    </div>
		    <div class="grid_7">
		        <select id="conditionBloodType" name="searchMentalPatientVo.bloodType.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE"/>
		        </select>
		    </div>
		     <div  class="grid_5 lable-right">
	        	<label class="form-lbl">宗教信仰：</label>
		    </div>
		    <div class="grid_7">
		        <select id="conditionFaith" name="searchMentalPatientVo.faith.id" class="form-txt">
		            <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH"/>
		        </select>
		    </div>
       		<div class="grid_5 lable-right">
		        <label class="form-lbl">电子邮件：</label>
		    </div>
		    <div class="grid_7">
		        <input type="text" id="conditionEmail" name="searchMentalPatientVo.email" maxlength="11" class="form-txt" />
		    </div>
			<div  class="grid_5 lable-right">
		    	<label class="form-lbl">联系电话：</label>
		    </div>
		    <div class="grid_7">
		        <input type="text" id="conditionTelephone" name="searchMentalPatientVo.telephone" maxlength="15" class="form-txt" />
		    </div>
		  	
		    <div class="grid_5 lable-right">
		        <label class="form-lbl">联系手机：</label>
		    </div>
		    <div class="grid_7">
		        <input type="text" id="conditionMobileNumber" name="searchMentalPatientVo.mobileNumber" maxlength="11" class="form-txt" />
		    </div>
		    <div class="clearLine">&nbsp;</div>
        </div>		
 </div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#mentalPatientDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#mentalPatientDialog").dialog( "option" , {height:330} );
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

	$('#startBirthday').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endBirthday").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endBirthday').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#startBirthday").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>